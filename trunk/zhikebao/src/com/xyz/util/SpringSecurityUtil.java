/**
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * 
 * $Id: SpringSecurityUtils.java 438 2009-09-07 14:59:59Z calvinxiu $
 */
package com.xyz.util;

import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.Assert;

import com.xyz.framework.log.Logger;
import com.xyz.system.model.Authority;
import com.xyz.system.model.Shop;
import com.xyz.system.model.User;



/**
 * SpringSecurity的工具类.
 * 
 * @author calvin
 */
public class SpringSecurityUtil {

	/**
	 * 取得当前用户的登录名,如果当前用户未登录则返回空字符串.
	 */
	public static String getCurrentUserName() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth == null)
			return "";
		return auth.getName();
	}

	/**
	 * 取得当前用户,返回值为SpringSecurity的User类及其子类, 如果当前用户未登录则返回null.
	 */
	@SuppressWarnings("unchecked")
	public static <T extends User> T getCurrentUser() {
		User user = null;
		SecurityContext ctx = SecurityContextHolder.getContext();
		
		if (ctx != null) {
			Authentication auth = ctx.getAuthentication();
			if (auth != null) {
				Object principal = auth.getPrincipal();
				if(principal!=null)
				{
					if (principal instanceof UserDetails) {
						user = (User) principal;
					} else {
						Logger.info(SpringSecurityUtil.class, "pricipal.toString()=="+principal.toString());
					}
				}else{
					Logger.info(SpringSecurityUtil.class,"当前没有登录用户");
				}
			} else {
				Logger.info(SpringSecurityUtil.class,"当前没有登录用户");
			}
		} else {
			Logger.info(SpringSecurityUtil.class,"不能得到SecurityContext ");
		}
		return (T) user;
	}
	/**
	 * 取得当前系统用户对应的淘宝用户,
	 * 返回值为TaobaoUser类及其子类, 如果当前用户未登录则返回null.
	 */
	@SuppressWarnings("unchecked")
	public static <T extends Shop> T getShop() {
		Shop shop = null;
		User user = getCurrentUser();
		Assert.notNull(user, "User不能为空");
		shop = user.getShop();
		return (T) shop;
	}
	
	/**
	 * 取得当前用户的登录名,如果当前用户未登录则返回null.
	 */
	public static String getCurrUsername() {
		String userName = "";
		SecurityContext ctx = SecurityContextHolder.getContext();
		
		if (ctx != null) {
			Authentication auth = ctx.getAuthentication();
			if (auth != null) {
				Object principal = auth.getPrincipal();
				if (principal instanceof UserDetails) {
					userName = ((UserDetails) principal).getUsername();
				} else {
					Logger.info(SpringSecurityUtil.class, "pricipal.toString()=="+principal.toString());
				}
			} else {
				Logger.info(SpringSecurityUtil.class,"auth is null!");
			}
		} else {
			Logger.info(SpringSecurityUtil.class,"ctx is   null");
		}

		return userName;

	}
	
	/**
	 * 取得当前登录用户的ID,如果当前用户未登录则返回null.
	 */
	public static Integer getUserId() {
		SecurityContext ctx = SecurityContextHolder.getContext();
		Integer userId = null;
		if (ctx != null) {
			Authentication auth = ctx.getAuthentication();
			if (auth != null) {
				Object principal = auth.getPrincipal();
				if (principal instanceof UserDetails) {
					userId = ((User) principal).getPid();
				} else {
					Logger.info(SpringSecurityUtil.class,"pricipal.toString()=="
							+ principal.toString());
				}
			} else {
				Logger.info(SpringSecurityUtil.class,"auth is null!");
			}
		} else {
			Logger.info(SpringSecurityUtil.class,"ctx is   null");
		}
       return userId;

	}
	
	/**
	 * 判断用户是否具有指定的权限
	 * 
	 * @param role
	 * @return
	 */
	public static boolean isHasAuth(String role) {
		User user = SpringSecurityUtil.getCurrentUser();
		if (user == null) {
			Logger.error(SpringSecurityUtil.class,"未登录用户不能操作");
			return false;
		}
		String[] la = user.getAuthes();
		for (String auth : la) {
			if (role.equals(auth))
				return true;
		}
		return false;
	}
	
}
