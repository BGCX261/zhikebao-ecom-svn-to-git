package com.xyz.system.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.jdo.JDOHelper;
import javax.persistence.NoResultException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.util.Assert;

import com.xyz.framework.log.Logger;
import com.xyz.system.dao.IAuthorityDao;
import com.xyz.system.dao.IResourceDao;
import com.xyz.system.dao.IUserDao;
import com.xyz.system.model.Authority;
import com.xyz.system.model.Resource;
import com.xyz.system.model.User;
import com.xyz.system.service.ISecurityService;
import com.xyz.system.service.ServiceException;
import com.xyz.util.Page;
import com.xyz.util.PropertyFilter;
import com.xyz.util.SpringSecurityUtil;
import com.xyz.util.StringUtil;

/**
 * 安全相关实体的管理类, 包括用户,角色,资源与授权类.
 * 
 * @author calvin
 */
// Spring Service Bean的标识.
// 默认将类中的所有函数纳入事务管理.

public class SecurityManager implements ISecurityService {
	
	Md5PasswordEncoder mpe = new Md5PasswordEncoder();

	public IAuthorityDao adao;

	public IResourceDao rdao;

	@Autowired
	private IUserDao udao;

	// User Manager //
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.lvl.system.service.impl.IUserService#getUser(java.lang.String)
	 */
	public User getUser(String id) {
		return (User) udao.get(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.lvl.system.service.impl.IUserService#saveUser(com.lvl.system.model
	 * .User)
	 */
	public void saveUser(User entity) {
        entity.setPassword(mpe.encodePassword(entity.getPassword(), null));
		entity.setEnabled(true);
		entity.setAccountNonExpired(true);
		entity.setAccountNonLocked(true);
		entity.setCredentialsNonExpired(true);
		udao.save(entity);
	}

	public void update(User entity) {
		if(entity.getPassword()!=null&&entity.getPassword().length()<=16)
			entity.setPassword(mpe.encodePassword(entity.getPassword(), null));
		Logger.debug("测试一下Object Manager:"
				+ JDOHelper.getPersistenceManager(entity));

		udao.update(entity);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.lvl.system.service.impl.IUserService#deleteUser(java.lang.String)
	 */
	public void deleteUser(Long id) {
		if (id.equals(1)) {
			Logger.warn(getClass(), "操作员"
					+ SpringSecurityUtil.getCurrUsername() + "尝试删除超级管理员用户");
			throw new ServiceException("不能删除超级管理员用户");
		}
		try {
			udao.delete(id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.lvl.system.service.impl.IUserService#findUserByLoginName(java.lang
	 * .String)
	 */
	public User findUserByLoginName(String loginName) {
		User ou = null;
		try {
			ou = (User) udao.findUniqueBy("username", loginName);
		} catch (NoResultException e) {
			Logger.info(getClass(), "没有查询到用户");
		}
		return ou;
	}

	/**
	 * 根据角色名称查询角色权限
	 * 
	 * @param authName
	 * @return
	 */
	public Authority findAuth(String authName) {
		Authority au = (Authority) adao.findUniqueBy("role", authName);
		return au;
	}

	/**
	 * 检查用户名是否唯一.
	 * 
	 * @return loginName在数据库中唯一或等于orgLoginName时返回true.
	 */
	public boolean isLoginNameUnique(String loginName, String oldLoginName) {
		return udao.isPropertyUnique("username", loginName, oldLoginName);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.lvl.system.service.impl.IUserService#queryUsers(com.lvl.frame.dao
	 * .Page)
	 */
	public Page<User> queryUsers(Page<User> page, List<PropertyFilter> filters) {
		page.setOrderBy("dateModified");
		page.setOrder("desc");
		return udao.findPage(page, filters);
	}

	public List<Authority> findAuthsByKeys(Set<String> keys) {
		Assert.notEmpty(keys, "没有关联主键");
		return adao.findAuthsByKeys(keys);
	}

	public LinkedHashMap<String, String> getRequestMap() throws Exception {
		List<Resource> resourceList = rdao.getUrlResourceWithAuthorities();
		LinkedHashMap<String, String> requestMap = new LinkedHashMap<String, String>(
				resourceList.size());
		for (Resource resource : resourceList) {
			requestMap.put(resource.getValue(), resource.getAuthorities());
		}
		return requestMap;
	}

	/**
	 * 根据用户权限查询菜单
	 */
	public Map<Resource, List<Resource>> findMenus() {
		User user = SpringSecurityUtil.getCurrentUser();
		if (user == null) {
			Logger.error(getClass(), "未登录用户不能操作");
			return null;
		}
		Map<Resource, List<Resource>> usermenus = new HashMap<Resource, List<Resource>>();
		Map<Resource, List<Resource>> allmenus = rdao.getMenuMap();
		Set<String> sk = user.getAuthorityKeys();
		List<Authority> la = adao.findAuthsByKeys(sk);
		String[] aus = new String[la.size()];
		for (int i = 0; i < aus.length; i++) {
			aus[i] = la.get(i).getAuthority();
		}
		List<Resource> tmprs;
		// 将一级菜单排序后再输出
		List<Resource> parentList = new ArrayList(allmenus.keySet());
		Collections.sort(parentList);


		for (Resource r : parentList) {
			tmprs = new ArrayList<Resource>();
			List<Resource> or = (List<Resource>) allmenus.get(r);
			for (Resource c : or) {
				String as = c.getAuthorities();
				if(as==null||as.length()<1)
				   continue;
				String[] cus;
				if (as.indexOf(",") > 0)
					cus = as.split(",");
				else
					cus = new String[] { as };
				if (StringUtil.isHasSame(cus, aus))
					tmprs.add(c);
			}
			if (tmprs.size() > 0) {
				usermenus.put(r, tmprs);
			}
		}
		return usermenus;
	}

	/**
	 * 根据key查询
	 */
	public Resource getResource(String id) {
		return (Resource) rdao.get(id);
	}
	
	public void delResource(String id) {
		try {
			Resource res = rdao.get(id);
			rdao.delete(res);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/*
	 * 分页查询功能列表
	 */
	public Page<Resource> queryResources(Page<Resource> page, List<PropertyFilter> filters) {
		page.setOrderBy("parentId,position");
		filters.add(new PropertyFilter("EQ_deleted",false));
		return rdao.findPage(page, filters);
	}

	public IResourceDao getRdao() {
		return rdao;
	}

	public void setRdao(IResourceDao rdao) {
		this.rdao = rdao;
	}

	public void saveResource(Resource entity) {
		rdao.save(entity);
	}

	public void updateResource(Resource entity) {
		rdao.update(entity);
	}

	public void saveAuth(Authority entity) {
		adao.save(entity);
	}

	public void updateAuth(Authority entity) {
		adao.update(entity);
		Set<String> rkeys = entity.getResourceKeys();
		Map<Resource, List<Resource>> allmenus = rdao.getMenuMap();
		Set<Resource> keyset = allmenus.keySet();
		for (Resource r : keyset) {
			List<Resource> tmprs = (List<Resource>) allmenus.get(r);
			for (Resource c : tmprs) {
				if(rkeys!=null&&rkeys.contains(c.getKey()))
					continue;
				String authstr = c.getAuthorities();
				if(authstr!=null)
				{
					String ro = entity.getRole();
					if(authstr.indexOf(ro)<0)
						continue;
					String rex = ro+"\\,"+"|"+"\\,"+ro+"|"+ro;
					authstr = authstr.replaceFirst(rex, "");
					c.setAuthorities(authstr);
					updateResource(c);
				}
			}
		}
	}
	
	public void setAdao(IAuthorityDao adao) {
		this.adao = adao;
	}

	@Override
	public void delAuthority(String id) {
		try {
			Authority res = adao.get(id);
			adao.delete(res);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public Authority getAuthority(String id) {
		return (Authority) adao.get(id);
	}

	@Override
	public Page<Authority> queryAuthorities(Page<Authority> page,
			List<PropertyFilter> filters) {
		filters.add(new PropertyFilter("EQ_deleted",false));
		return adao.findPage(page, filters);
	}

	@Override
	public List<Resource> queryAllResources() {
		return rdao.find(" deleted = false and parentId > 1 ", " parentId,position ");
	}

}
