package com.xyz.system.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.FlushModeType;

import com.google.appengine.api.datastore.Key;
import com.xyz.LocalDatastoreTestCase;
import com.xyz.framework.log.Logger;
import com.xyz.system.model.User;
import com.xyz.system.service.ISecurityService;

public class ResourceDaoTest extends LocalDatastoreTestCase {

	public void testGetMenuMap() {
		/*IResourceDao rd1 = (IResourceDao)getAc().getBean("resourceCache");
		CacheManager manager = (CacheManager)getAc().getBean("cacheManager");
		Logger.debug("一级菜单个数为："+rd1.getMenuMap().size());
		Logger.debug("缓存是否起作用："+rd1.getMenuMap().size());
		Logger.debug(StringUtils.arrayToDelimitedString(manager.getCacheNames(), ","));
		Cache test = manager.getCache("menuCache");
		Logger.debug(StringUtils.collectionToCommaDelimitedString(test.getKeys()));
		Assert.notNull(test, "没有找到缓存");*/
		//Logger.debug(getClass(), test.ge);
		ISecurityService ss = (ISecurityService)getAc().getBean("securityManager");
		User sale = new User("meimei","63a9f0ea7b",true,true,"user","valiant",true,null,null,"God","God",true,true);
		ss.saveUser(sale);
		String k = sale.getKey();
		//User us = udao.get(k);
		//Logger.debug(us.getNickName());
	}

}
