/**
 * Copyright (c) 2005-2009 springside.org.cn
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * 
 * $Id: DefinitionSourceFactoryBean.java,v 1.1 2009/09/22 00:55:47 test Exp $
 */
package com.xyz.system.service.impl;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.access.intercept.DefaultFilterInvocationSecurityMetadataSource;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.access.intercept.RequestKey;
import org.springframework.security.web.util.AntUrlPathMatcher;
import org.springframework.security.web.util.UrlMatcher;
import org.springframework.stereotype.Service;

import com.xyz.system.service.ISecurityService;

/**
 * DefinitionSource工厂.
 * 由注入的resourceDetailService读取在数据库或其它地方中定义的URL-授权关系,
 * 提供LinkedHashMap<String, String>形式的URL及授权关系定义，
 * 并最终转为SpringSecurity所需的LinkedHashMap<RequestKey, ConfigAttributeDefinition>形式的定义.
 * 资源-授权查询服务
 * @see org.springframework.security.intercept.web.DefaultFilterInvocationDefinitionSource
 * @see ResourceDetailService
 * 
 * @author Val
 */
@Service("databaseDefinitionSource")
public class DefinitionSourceFactoryBean implements FactoryBean {
 
	@Autowired
    private ISecurityService securityManager;


	/**
	 * 返回注入了Ant Style的URLMatcher和ResourceDetailService提供的RequestMap的DefaultFilterInvocationDefinitionSource.
	 */
	public Object getObject() throws Exception {
		LinkedHashMap<RequestKey, Collection<ConfigAttribute>> requestMap = buildRequestMap();
		UrlMatcher matcher = getUrlMatcher();
		DefaultFilterInvocationSecurityMetadataSource metaSource = 	new DefaultFilterInvocationSecurityMetadataSource(matcher, requestMap);;
		
		return metaSource;
	}

	@SuppressWarnings("unchecked")
	public Class getObjectType() {
		return FilterInvocationSecurityMetadataSource.class;
	}

	public boolean isSingleton() {
		return true;
	}

	/**
	 * 提供Ant Style的URLMatcher.
	 */
	protected UrlMatcher getUrlMatcher() {
		return new AntUrlPathMatcher();
	}

	/**
	 * 将resourceDetailService提供LinkedHashMap<String, String>形式的URL及授权关系定义
	 * 转化为DefaultFilterInvocationDefinitionSource需要的LinkedHashMap<RequestKey, ConfigAttributeDefinition>形式.
	 */
	protected LinkedHashMap<RequestKey, Collection<ConfigAttribute>> buildRequestMap() throws Exception {
		LinkedHashMap<String, String> srcMap = securityManager.getRequestMap();
		LinkedHashMap<RequestKey, Collection<ConfigAttribute>> distMap = new LinkedHashMap<RequestKey, Collection<ConfigAttribute>>();

        for (Map.Entry<String, String> entry : srcMap.entrySet()) {
			RequestKey key = new RequestKey(entry.getKey(), null);
			if (StringUtils.isNotBlank(entry.getValue())) {
				distMap.put(key, SecurityConfig.createListFromCommaDelimitedString(entry.getValue()));
			}
		}

		return distMap;
	}
}
