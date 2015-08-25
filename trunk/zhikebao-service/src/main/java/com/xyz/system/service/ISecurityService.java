package com.xyz.system.service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.xyz.system.model.Authority;
import com.xyz.system.model.Resource;
import com.xyz.system.model.User;
import com.xyz.util.Page;
import com.xyz.util.PropertyFilter;

public interface ISecurityService {

	// User Manager //
	public abstract User getUser(String id);
	
	public abstract void saveUser(User entity);
	
	public abstract void deleteUser(Long id);
	
	public abstract User findUserByLoginName(String loginName);

	public Page<User> queryUsers(Page<User> page, List<PropertyFilter> filters);
	
	public Map<Resource,List<Resource>> findMenus();

	public void update(User entity);
	
	public LinkedHashMap<String, String> getRequestMap() throws Exception;

	/**
	 * 根据角色名称查询角色权限
	 * @param authName
	 * @return
	 */
	public Authority findAuth(String authName);

	public void saveResource(Resource entity);

	public void saveAuth(Authority entity);
	
	public List<Authority> findAuthsByKeys(Set<String> keys);

	public Page<Resource> queryResources(Page<Resource> page, List<PropertyFilter> filters);
    /**
     * 查询所有的功能列表
     * @return
     */
	public List<Resource> queryAllResources();
	
	/**
	 * 根据key查询
	 */
	public Resource getResource(String id);

	public void delResource(String id);
	
	/**
	 * 分页查询岗位列表
	 * @param page
	 * @param filters
	 * @return
	 */
	public Page<Authority> queryAuthorities(Page<Authority> page, List<PropertyFilter> filters);

	/**
	 * 根据key查询岗位
	 */
	public Authority getAuthority(String id);

	public void delAuthority(String id);

	public void updateResource(Resource entity);

	public void updateAuth(Authority entity);
	
}