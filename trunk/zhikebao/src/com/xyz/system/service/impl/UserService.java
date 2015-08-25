package com.xyz.system.service.impl;

import java.util.List;

import javax.persistence.NoResultException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taobao.api.TaobaoApiException;
import com.taobao.api.TaobaoJsonRestClient;
import com.taobao.api.TaobaoRestClient;
import com.taobao.api.model.UserGetRequest;
import com.taobao.api.model.UserGetResponse;
import com.xyz.framework.log.Logger;
import com.xyz.system.dao.IBaseUserDao;
import com.xyz.system.dao.IShopDao;
import com.xyz.system.dao.IUserDao;
import com.xyz.system.model.Shop;
import com.xyz.system.model.User;
import com.xyz.system.service.IUserService;
import com.xyz.util.Page;

/**
 * Facade for entity TaobaoUser.
 * 
 * @see com.xyz.main.model.Shop.ejb.bean.TaobaoUser
 * @author sea
 */
@Service("taobaoUserServ")
public class UserService implements IUserService {
	// property constants
	public static final String APP_KEY = "appKey";
	public static final String APP_SECRET = "appSecret";
	public static final String TB_ACCOUNT = "tbAccount";
	public static final String EMAIL = "email";
	public static final String IS_AUTHORIZE = "isAuthorize";
	public static final String SESSION_KEY = "sessionKey";

	@Autowired
	public IShopDao sao;
	
	@Autowired
	private IBaseUserDao udao;

	/**
	 * Perform an initial save of a previously unsaved TaobaoUser entity. All
	 * subsequent persist actions of this entity should use the #update()
	 * method.
	 * 
	 * @param entity
	 *            TaobaoUser entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void saveShop(Shop entity) {
		Logger.info(this.getClass(), "saving TaobaoUser instance");
		try {
			sao.save(entity);
			Logger.info(this.getClass(),"save successful");
		} catch (RuntimeException re) {
			Logger.error(getClass(),"save failed");
			throw re;
		}
	}

	/**
	 * Delete a persistent TaobaoUser entity.
	 * 
	 * @param entity
	 *            TaobaoUser entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(Shop entity) {
		Logger.info(getClass(),"deleting TaobaoUser instance");
		try {
			sao.delete(entity);
			Logger.error(getClass(),"delete successful");
		} catch (RuntimeException re) {
			Logger.error(getClass(),"delete failed");
			throw re;
		}
	}

	/**
	 * Persist a previously saved TaobaoUser entity and return it or a copy of
	 * it to the sender. A copy of the TaobaoUser entity parameter is returned
	 * when the JPA persistence mechanism has not previously been tracking the
	 * updated entity.
	 * 
	 * @param entity
	 *            TaobaoUser entity to update
	 * @return TaobaoUser the persisted TaobaoUser entity instance, may not be
	 *         the same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public void update(Shop entity) {
		Logger.info(getClass(),"updating TaobaoUser instance");
		sao.update(entity);
	}
	
	public Shop findById(Object id) {
		Logger.info(getClass(),"finding TaobaoUser instance with id: " + id);
		try {
			Shop instance = sao.get(id);
			return instance;
		} catch (RuntimeException re) {
			Logger.error(getClass(),"find failed");
			throw re;
		}
	}

	/**
	 * Find all TaobaoUser entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the TaobaoUser property to query
	 * @param value
	 *            the property value to match
	 * @param rowStartIdxAndCount
	 *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
	 *            row index in the query result-set to begin collecting the
	 *            results. rowStartIdxAndCount[1] specifies the the maximum
	 *            number of results to return.
	 * @return List<TaobaoUser> found by query
	 */
	@SuppressWarnings("unchecked")
	public List<Shop> findByProperty(String propertyName,final Object value) {
		Logger.info(getClass(),"finding TaobaoUser instance with property: "
				+ propertyName + ", value: " + value);
		return sao.findBy(propertyName, value);
	}

	public List<Shop> findByAppKey(Object appKey) {
		return findByProperty(APP_KEY, appKey);
	}

	public List<Shop> findByAppSecret(Object appSecret,
			int... rowStartIdxAndCount) {
		return findByProperty(APP_SECRET, appSecret);
	}

	public Shop findByTbAccount(Object tbAccount) {
		Shop tu = null;
		try {
				tu = sao.findUniqueBy("tbAccount", tbAccount);
		} catch (NoResultException e) {
			Logger.info(getClass(), "没有查询到淘宝用户");
		}
		return tu;
	}

	public List<Shop> findByEmail(Object email,
			int... rowStartIdxAndCount) {
		return findByProperty(EMAIL, email);
	}

	public List<Shop> findByIsAuthorize(Object isAuthorize,
			int... rowStartIdxAndCount) {
		return findByProperty(IS_AUTHORIZE, isAuthorize);
	}
	
	public Shop getAuthorizeShop() {
		Page<Shop> page = new Page<Shop>(1);
		page.setOrderBy(" authorizeTime desc ");
		page = sao.findBy(page, "isAuthorize", true);
		if(page.getResult()!=null&&page.getResult().size()>0)
		   return page.getResult().get(0);
		else
		   return null;
	}
	
	public Page<Shop> getActiveShops() {
		Page<Shop> page = new Page<Shop>();
		page.setOrderBy(" authorizeTime ");
		page = sao.findBy(page, "isAuthorize", true);
		return page;
	}

	public List<Shop> findBySessionKey(Object sessionKey,
			int... rowStartIdxAndCount) {
		return findByProperty(SESSION_KEY, sessionKey);
	}

	/**
	 * Find all TaobaoUser entities.
	 * 
	 * @param rowStartIdxAndCount
	 *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
	 *            row index in the query result-set to begin collecting the
	 *            results. rowStartIdxAndCount[1] specifies the the maximum
	 *            count of results to return.
	 * @return List<TaobaoUser> all TaobaoUser entities
	 */
	@SuppressWarnings("unchecked")
	public List<Shop> findAll(final int... rowStartIdxAndCount) {
		Logger.info(getClass(),"finding all TaobaoUser instances");
		return sao.getAll();
	}

	@Override
	public Shop findByUser(User user) {
		Logger.info(getClass(),"finding a TaobaoUser instance by User");
		List<Shop> lt = sao.find("  user == :user ", user);
		if(lt!=null&&lt.size()>0)
			 return lt.get(0);
	    else
		     return null;
	}

	@Override
	public Shop findFromTaobao(Shop tbuser) {
		try {
			TaobaoRestClient client = new TaobaoJsonRestClient("http://gw.api.tbsandbox.com/router/rest",tbuser.getAppKey(), tbuser.getAppSecret());
			UserGetRequest req = new UserGetRequest();
			String publicFields = "nick";
			String privateFields = "type,auto_repost,promoted_type,status,consumer_protection";
			req.setFields(publicFields + "," + privateFields);
			UserGetResponse rsp = client.userGet(req, tbuser.getSessionKey());
			if(rsp.isSuccess())
			{
				tbuser.setTbAccount(rsp.getHeader("nick"));
				
			}
			return tbuser;
		} catch (TaobaoApiException e) {
			Logger.error(getClass(), "从淘宝获取信息失败");
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public com.taobao.api.model.User findByNick(String nick) {
		com.taobao.api.model.User user = null;
		try {
			user = udao.findUniqueBy("nick", nick);
		} catch (Exception e) {
			Logger.info(getClass(), "Nick:"+nick+"没有查到："+e.getStackTrace().toString());
			e.printStackTrace();
		}
		return user;
	}

	@Override
	public void saveUser(com.taobao.api.model.User entity) {
		udao.save(entity);
	}

	@Override
	public com.taobao.api.model.User findByKey(Integer key) {
		com.taobao.api.model.User user = null;
		try {
			user = udao.get(key);
		} catch (Exception e) {
			Logger.info(getClass(), "key:"+key+"没有查到："+e.getStackTrace().toString());
			e.printStackTrace();
		}
		return user;
	}


}