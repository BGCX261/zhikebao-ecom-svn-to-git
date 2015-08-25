package com.xyz.customer.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.jdo.JDOObjectNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.taobao.api.TaobaoRestClient;
import com.taobao.api.model.UserGetRequest;
import com.taobao.api.model.UserGetResponse;
import com.taobao.api.model.UsersGetRequest;
import com.xyz.customer.dao.ICustomerDao;
import com.xyz.customer.model.Customer;
import com.xyz.customer.service.ICustomerService;
import com.xyz.framework.log.Logger;
import com.xyz.system.dao.IBaseUserDao;
import com.xyz.system.model.Shop;
import com.xyz.system.model.User;
import com.xyz.util.DateUtil;
import com.xyz.util.Page;
import com.xyz.util.PropertyFilter;
import com.xyz.util.SpringSecurityUtil;
import com.xyz.util.TaobaoUtil;

@Service
public class CustomerService implements ICustomerService {
	@Autowired
	private ICustomerDao cdao;
	@Autowired
	private IBaseUserDao bud;

	@Override
	public void delete(Customer entity) {
		Logger.info(getClass(), "删除客户");
		try {
			cdao.delete(entity);
			Logger.info(getClass(), "delete successful");
		} catch (RuntimeException re) {
			Logger.error(getClass(), "delete failed");
			throw re;
		}
	}

	@Override
	public Page<Customer> findAll(Page<Customer> page) {
		Logger.info(getClass(), "finding all BossiniOrder instances");
		try {
			return cdao.findPage(page);
		} catch (RuntimeException re) {
			Logger.error(getClass(), "find all failed");
			throw re;
		}
	}

	@Override
	public Customer findById(String id) {
		Logger.info(getClass(), "finding Customer instance with id: " + id);
		try {
			Customer instance = cdao.get(id);
			return instance;
		} catch (JDOObjectNotFoundException re) {
			Logger.warn(getClass(), "没有找到指定Id的客户");
		}
		return null;
	}

	@Override
	public Page<Customer> findByProperty(String propertyName, Object value,
			Page p) {
		Logger.info(getClass(), "finding BossiniOrder instance with property: "
				+ propertyName + ", value: " + value);
		try {
			return cdao.findBy(p, propertyName, value);
		} catch (RuntimeException re) {
			Logger.error(getClass(), "find by property name failed");
			throw re;
		}
	}

	@Override
	public Page<Customer> findByStatus(Object status, Page<Customer> page) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<Customer> findManyByProperty(Object[] propertyName,
			Object[] value, Page page) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<Customer> query(Page<Customer> page, String sqlString) {
		try {
			return cdao.findPage(page, sqlString);
		} catch (RuntimeException re) {
			throw re;
		}
	}

	@Override
	public List<Object> query(String sqlString) {
		try {
			return cdao.find(sqlString);
		} catch (RuntimeException re) {
			throw re;
		}
	}

	@Override
	public Customer querySingle(String sqlString) {
		try {
			return cdao.findUnique(sqlString);
		} catch (RuntimeException re) {
			Logger.info(getClass(), "没有查询到结果");
		}
		return null;
	}

	@Override
	public void save(Customer entity) {
		cdao.save(entity);
	}

	@Override
	public Customer update(Customer entity) {
		Logger.info(getClass(), "updating Customer instance");
		try {
			cdao.update(entity);
			Logger.info(getClass(), "update successful");
			return entity;
		} catch (RuntimeException re) {
			Logger.error(getClass(), "update failed");
			throw re;
		}
	}

	@Override
	public Customer findByShopAndUser(String shopKey, String userKey) {
		Customer cus = null;
		try {
			cus = cdao
					.findUnique("shopKey=?1 and tbuser=?2 ", shopKey, userKey);
		} catch (Exception e) {
			Logger.info(getClass(), "客户没有找到--" + e.getStackTrace().toString());
			// e.printStackTrace();
		}
		return cus;
	}

	@Override
	public Page<Customer> queryPage(Page<Customer> page,
			List<PropertyFilter> filters) {
		Logger.info(getClass(), "根据卖家查询客户列表");
		Shop shop = SpringSecurityUtil.getShop();
		Assert.notNull(shop, "商家不能为空");
		try {
			filters.add(new PropertyFilter("EQ_shopKey",shop.getKey()));
			return cdao.findPage(page, filters);
		} catch (RuntimeException re) {
			Logger.error(getClass(), "find all failed");
			throw re;
		}
	}

	/**
	 * 根据昵称提取用户信息并创建客户
	 */
	public void drawCoustomer(Set<String> nicks) {
		// 提取订单中的客户信息
		System.out.println("自动获取淘宝交易订单任务"
				+ DateUtil.format(new Date(), "yyyy-MM-dd HH:mm:ss"));
		Shop shop = SpringSecurityUtil.getShop();
		Assert.notNull(shop, "商家不能为空");
		TaobaoRestClient client = TaobaoUtil.getService(shop);
		for (String nick : nicks) {
			try {
				nick = nick.trim();
				com.taobao.api.model.User user = bud.findUniqueBy("nick", nick);
				if (user == null) {
					UserGetRequest ugreq = new UserGetRequest();
					String publicFields = "nick,sex,buyer_credit,seller_credit,location.city,location.state,location.country,created,last_visit";
					ugreq.setFields(publicFields);
					ugreq.setNick(nick);
					UserGetResponse ugrsp = client.userGet(ugreq);
					if (ugrsp.isSuccess()) {
						user = ugrsp.getUser();
						if (user != null)
							bud.save(user);
					}
				}
				if (user == null || user.getKey() == null)
					return;
				Customer cus = findByShopAndUser(shop.getKey(), user.getKey());
				if (cus == null) {
					cus = new Customer();
					cus.setShopKey(shop.getKey());
					cus.setTbuserKey(user.getKey());
					save(cus);
				}
			} catch (Exception e) {
				Logger.error(getClass(), nick+"客户不能正常提取");
				e.printStackTrace();
			}
		}

	}
}
