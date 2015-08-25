package com.xyz.customer.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.taobao.api.TaobaoRestClient;
import com.taobao.api.model.UserGetRequest;
import com.taobao.api.model.UserGetResponse;
import com.xyz.StaticValMethod;
import com.xyz.customer.dao.ICustomerDao;
import com.xyz.customer.dao.IGuestDao;
import com.xyz.customer.model.Customer;
import com.xyz.customer.model.Guest;
import com.xyz.customer.service.ICustomerService;
import com.xyz.framework.log.Logger;
import com.xyz.system.dao.IBaseUserDao;
import com.xyz.system.model.Shop;
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
	@Autowired
	private IGuestDao gdao;

	@Override
	public void delete(String key) {
		Logger.info(getClass(), "删除客户");
		try {
			cdao.delete(Integer.parseInt(key));
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
		} catch (Exception re) {
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
	public Customer findByShopAndUser(Integer shopKey, Integer userKey) {
		Customer cus = null;
		try {
			cus = cdao
					.findUnique("shopKey=?1 and tbuserKey =?2 ", shopKey, userKey);
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
			filters.add(new PropertyFilter("EQ_shopKey",shop.getPid()));
			return cdao.findPage(page, filters);
		} catch (RuntimeException re) {
			Logger.error(getClass(), "find all failed");
			re.printStackTrace();
			return null;
		}
	}

	/**
	 * 根据昵称提取用户信息并创建客户
	 */
	public void drawCoustomer(Set<String> nicks) {
		//清理数据
		List<Customer> lc = cdao.getAll();
		for(Customer c :lc)
			cdao.delete(c.getPid());
		List<com.taobao.api.model.User> lu = bud.getAll();
		for(com.taobao.api.model.User u :lu)
			bud.delete(u.getPid());
		Date now = DateUtil.getNow();
		// 提取订单中的客户信息
		System.out.println("自动获取淘宝交易订单任务"
				+ DateUtil.format(now, "yyyy-MM-dd HH:mm:ss"));
		Shop shop = SpringSecurityUtil.getShop();
		Assert.notNull(shop, "商家不能为空");
		TaobaoRestClient client = TaobaoUtil.getService(shop);
		for (String nick : nicks) {
			try {
				nick = nick.trim();
				com.taobao.api.model.User user = bud.findUniqueBy("nick", nick);
				//bud.delete(user.getPid());
				if (user == null) {
					UserGetRequest ugreq = new UserGetRequest();
					ugreq.setFields(StaticValMethod.FIELDS_TAOBAO_USER_GET);
					ugreq.setNick(nick);
					UserGetResponse ugrsp = client.userGet(ugreq);
					if (ugrsp.isSuccess()) {
						user = ugrsp.getUser();
						if (user != null)
							bud.save(user);
					}
				}
				if (user == null || user.getPid() == null)
					return;
				Customer cus = findByShopAndUser(shop.getPid(), user.getPid());
				//this.delete(cus.getPid());
				if (cus == null) {
					cus = new Customer();
					cus.setNick(user.getNick());
					cus.setShopKey(shop.getPid());
					cus.setTbuserKey(user.getPid());
					//未分配的客户
					cus.setOwnerId(0);
					save(cus);
				}
			} catch (Exception e) {
				Logger.error(getClass(), nick+"客户不能正常提取");
				e.printStackTrace();
			}
		}

	}
	
	/**
	 * 保存访客信息(潜在客户)
	 */
	public void save(Guest guest)
	{
		gdao.save(guest);
	}
	
	/**
	 * 分页查询访客(潜在客户)
	 * @param page
	 * @return
	 */
	public Page<Guest> queryGuests(Page<Guest> page)
	{
		Logger.info(getClass(), "根据卖家查询潜在客户列表");
		Shop shop = SpringSecurityUtil.getShop();
		List<PropertyFilter> filters = new ArrayList<PropertyFilter>();
		Assert.notNull(shop, "商家不能为空");
		try {
			//filters.add(new PropertyFilter("EQ_shopKey",shop.getPid()));
			page.setOrderBy(" dateModified asc ");
			page = gdao.findPage(page, filters);
			if(page.getResult()==null||page.getResult().size()<1)
			{
				initGuestData();
				page = gdao.findPage(page, filters);
			}			
			Guest gue = page.getResult().get(0);
			gue.setDateModified(DateUtil.getNow());
			gdao.update(gue);
			return page;
		} catch (RuntimeException re) {
			Logger.error(getClass(), "find all failed");
			re.printStackTrace();
			return null;
		}
	}
	
	private void initGuestData()
	{
		Guest guest = new Guest("alipublic00","浙江","杭州","IBM/ThinkPad SL410-55C T6670 2G 320G 独立显卡");
		save(guest);
		guest = new Guest("alipublic01","江苏","南京","华硕EeePC 1005HA-H N270 1G 160G 时尚贝壳机 限量特价～ ");
		save(guest);
		guest = new Guest("alipublic02","上海","上海","Thinkpad T400-R28%100好评 港行 网上可查 LED 闻名不如见面 ");
		save(guest);
		guest = new Guest("alipublic03","安徽","合肥","IBM/ThinkPad SL410-55C T6670 2G 320G 独立显卡");
		save(guest);
		guest = new Guest("alipublic04","江苏","苏州","越南第一排糖");
		save(guest);
		guest = new Guest("alipublic05","浙江","温州","闪蓝双侧边拉链收腿极致显瘦款帅气风格窄管牛仔裤 无货举报 ");
		save(guest);
		guest = new Guest("alipublic06","江苏","无锡","四季款★韩料雪花");
		save(guest);
		guest = new Guest("alipublic07","浙江","绍兴","北京动感地带神州行大众移动充值卡50元 不能充值QB当心骗子 ");
		save(guest);
		guest = new Guest("alipublic08","湖北","武汉","超人气甜美圣诞装");
		save(guest);
	}
}
