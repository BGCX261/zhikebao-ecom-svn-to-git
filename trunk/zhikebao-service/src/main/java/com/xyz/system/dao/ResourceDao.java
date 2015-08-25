package com.xyz.system.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.google.appengine.api.datastore.Key;
import com.xyz.framework.data.impl.JpaDao;
import com.xyz.framework.log.Logger;
import com.xyz.system.model.Resource;
/**
 * 受保护资源对象的泛型DAO.
 * 
 * @author val
 */
public class ResourceDao extends JpaDao<Resource, String> implements IResourceDao {

	/* (non-Javadoc)
	 * @see com.xyz.system.dao.IResourceDao#getUrlResourceWithAuthorities()
	 */
	@Transactional(propagation=Propagation.NOT_SUPPORTED ,readOnly=true)
	public List<Resource> getUrlResourceWithAuthorities() {
		List ls = find(" resourceType= "+Resource.URL_TYPE, " position ");
		return distinct(ls);
	}
	
	/* (non-Javadoc)
	 * @see com.xyz.system.dao.IResourceDao#getMenuResourceWithAuthorities()
	 */
	@Transactional(propagation=Propagation.NOT_SUPPORTED ,readOnly=true)
	public List<Resource> getMenuResourceWithAuthorities() {
		List ls = find(" resourceType="+Resource.MENU_TYPE, " position ");
		return distinct(ls);
	}
	
	/* (non-Javadoc)
	 * @see com.xyz.system.dao.IResourceDao#getMenuMap()
	 */
	@Transactional(propagation=Propagation.NOT_SUPPORTED ,readOnly=true)
	public Map<Resource,List<Resource>> getMenuMap() {
		Logger.info(getClass(), "查询菜单");
		Map<Resource,List<Resource>> menus = new HashMap<Resource,List<Resource>>();
		List<Resource> parentls = find(" resourceType="+Resource.MENU_TYPE+" and parentId=1 ", " position ");
		Assert.notEmpty(parentls, "没有菜单，请初始化数据");
		List<Resource> childls ;
		for(Resource p : parentls)
		{
			childls = find(" resourceType="+Resource.MENU_TYPE+" and parentId="+p.getSerial(), " position ");
			menus.put(p, childls);
		}
		return menus;
	}

}
