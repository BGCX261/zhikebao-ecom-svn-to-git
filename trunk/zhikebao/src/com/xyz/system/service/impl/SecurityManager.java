package com.xyz.system.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.NoResultException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.xyz.FrameConstants;
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
	public User getUser(Integer id) {
		return (User) udao.get(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.lvl.system.service.impl.IUserService#saveUser(com.lvl.system.model
	 * .User)
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public User saveUser(User entity) {
		entity.setPassword(mpe.encodePassword(entity.getPassword(), null));
		entity.setIsEnabled(true);
		entity.setIsAccountNonExpired(true);
		entity.setIsAccountNonLocked(true);
		entity.setIsCredentialsNonExpired(true);
		entity.setDeleted(false);
		/*
		 * if(entity.getZkbAuthorities()!=null) { List<Authority> la = new
		 * ArrayList<Authority>(); for(Authority a : entity.getZkbAuthorities())
		 * { la.add(adao.getRef(a.getPid())); } entity.setZkbAuthorities(la); }
		 */
		return udao.update(entity);
	}

	public User update(User entity) {
		if (entity.getPassword() != null && entity.getPassword().length() <= 16)
			entity.setPassword(mpe.encodePassword(entity.getPassword(), null));

		return udao.update(entity);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.lvl.system.service.impl.IUserService#deleteUser(java.lang.String)
	 */
	public void deleteUser(Integer id) {
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
			/*
			 * List<User> lu = udao.findBy("username", loginName); for(User u :
			 * lu) udao.delete(u.getPid());
			 */
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
			List<Authority> la = resource.getAuthorities();
			String authstr = "";
			for (Authority au : la) {
				authstr += au.getAuthority() + ",";
			}
			if (authstr.length() > 0)
				authstr = authstr.substring(0, authstr.length() - 1);
			requestMap.put(resource.getValue(), authstr);
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
		List<Authority> la = user.getZkbAuthorities();

		List<Resource> tmprs;
		// 将一级菜单排序后再输出
		List<Resource> parentList = new ArrayList(allmenus.keySet());
		Collections.sort(parentList);

		for (Resource r : parentList) {
			tmprs = new ArrayList<Resource>();
			List<Resource> or = (List<Resource>) allmenus.get(r);
			label: for (Resource c : or) {
				List<Authority> as = c.getAuthorities();
				if (as == null || as.size() < 1)
					continue;
				for (Authority userauth : la) {
					for (Authority resauth : as) {
						if (userauth.equals(resauth)) {
							tmprs.add(c);
							continue label;
						}
					}
				}

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
	public Resource getResource(Integer id) {
		return (Resource) rdao.get(id);
	}

	public void delResource(Integer id) {
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
	public Page<Resource> queryResources(Page<Resource> page,
			List<PropertyFilter> filters) {
		page.setOrderBy("parentId,position");
		filters.add(new PropertyFilter("EQ_deleted", false));
		return rdao.findPage(page, filters);
	}

	/*
	 * 获得当前用户可以分配的功能列表
	 */
	public List<Resource> queryResources(List<PropertyFilter> filters) {
		List<Resource> lr = new ArrayList<Resource>();
		if (SpringSecurityUtil.isHasAuth(FrameConstants.SYS_AUTH_SYS_ADMIN)) {
			lr = queryAllResources();
		} else {
			Page<Authority> page = new Page<Authority>(20);
			page.setStart(0);
			page = queryAuthorities(page, new ArrayList<PropertyFilter>());

			Set<Resource> sr = new HashSet<Resource>();
			if (page.getResult() != null && page.getResult().size()>0) {
				List<Authority> la = page.getResult();
				for (Authority au : la) {
					if (au.getResources() != null)
						sr.addAll(au.getResources());
				}
				lr.addAll(sr);
			}
		}
		return lr;
	}

	public IResourceDao getRdao() {
		return rdao;
	}

	public void setRdao(IResourceDao rdao) {
		this.rdao = rdao;
	}

	public void saveResource(Resource entity) {
		if (entity.getPid() != null)
			rdao.update(entity);
		else
			rdao.save(entity);
	}

	public void updateResource(Resource entity) {
		rdao.update(entity);
	}

	public void saveAuth(Authority entity) {
		if (SpringSecurityUtil.isHasAuth(FrameConstants.SYS_AUTH_SYS_ADMIN)) {
			entity.setLevel(2);
		} else if (SpringSecurityUtil.isHasAuth(FrameConstants.SYS_AUTH_ADMIN)) {
			entity.setLevel(3);
		}
		adao.save(entity);
	}

	public void updateAuth(Authority entity) {
		adao.update(entity);
		/*
		 * List<Resource> rkeys = entity.getResources(); Map<Resource,
		 * List<Resource>> allmenus = rdao.getMenuMap(); Set<Resource> keyset =
		 * allmenus.keySet(); for (Resource r : keyset) { List<Resource> tmprs =
		 * (List<Resource>) allmenus.get(r); for (Resource c : tmprs) {
		 * if(rkeys!=null&&rkeys.contains(c.getPid())) continue; List<Authority>
		 * auths = c.getAuthorities(); if(auths!=null) {
		 * if(!auths.contains(entity)) continue; else auths.remove(entity);
		 * c.setAuthorities(auths); updateResource(c); } } }
		 */
	}

	public void setAdao(IAuthorityDao adao) {
		this.adao = adao;
	}

	@Override
	public void delAuthority(Integer id) {
		try {
			Authority res = adao.get(id);
			adao.delete(res);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public Authority getAuthority(Integer id) {
		return (Authority) adao.get(id);
	}

	@Override
	public Page<Authority> queryAuthorities(Page<Authority> page,
			List<PropertyFilter> filters) {
		User user = SpringSecurityUtil.getCurrentUser();
		if (SpringSecurityUtil.isHasAuth(FrameConstants.SYS_AUTH_SYS_ADMIN)) {
			filters.add(new PropertyFilter("EQ_deleted", false));
			page = adao.findPage(page, filters);
			return page;
		} else if (SpringSecurityUtil.isHasAuth(FrameConstants.SYS_AUTH_ADMIN)) {
			List<Authority> lauth = new ArrayList<Authority>();
			filters.add(new PropertyFilter("EQ_deleted", false));
			filters.add(new PropertyFilter("EQ_level", 2));
			page = adao.findPage(page, filters);
			int total = 0;
			int limit = 0;
			if (page.getResult() != null && page.getResult().size() > 0) {
				total += page.getTotalCount();
				List<Authority> pubAuths = page.getResult();
				for (Authority a : pubAuths) {
					a.setIsEditable(false);
					lauth.add(a);
					limit++;
				}
			}
			filters = new ArrayList<PropertyFilter>();
			filters.add(new PropertyFilter("EQ_deleted", false));
			filters.add(new PropertyFilter("EQ_createdBy", user.getPid()));
			page = adao.findPage(page, filters);
			if (page.getResult() != null && page.getResult().size() > 0) {
				total += page.getTotalCount();
				limit += page.getResult().size();
				lauth.addAll(page.getResult());
			}
			page.setTotalCount(total);
			page.setLimit(limit);
			page.setResult(lauth);
			return page;
		} else
			return null;
	}

	@Override
	public List<Resource> queryAllResources() {
		return rdao.find(" deleted = false and parentId > 1 ",
				" parentId,position ");
	}

}
