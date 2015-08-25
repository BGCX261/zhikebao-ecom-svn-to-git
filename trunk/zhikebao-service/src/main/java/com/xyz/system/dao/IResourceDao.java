package com.xyz.system.dao;

import java.util.List;
import java.util.Map;

import org.springmodules.cache.annotations.Cacheable;

import com.xyz.base.model.BaseCode;
import com.xyz.framework.data.IDataObject;
import com.xyz.system.model.Resource;

public interface IResourceDao extends IDataObject<Resource,String>{

	/**
	 * 查询URL类型的资源并预加载可访问该资源的授权信息.
	 */
	public abstract List<Resource> getUrlResourceWithAuthorities();

	/**
	 * 查询Menu类型的资源并预加载可访问该资源的授权信息.
	 */
	public abstract List<Resource> getMenuResourceWithAuthorities();

	/**
	 * 得到全部的菜单，并按层级关系整理成一个Map
	 */
	public abstract Map<Resource, List<Resource>> getMenuMap();


}