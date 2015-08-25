/*
 * Generated by ActionName
 * Template path: templates/java/JavaClass.vtl
 */
package com.xyz.system.service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.view.json.MappingJacksonJsonView;

import com.xyz.framework.log.Logger;
import com.xyz.system.model.Shop;
import com.xyz.system.model.User;
import com.xyz.system.service.ISecurityService;
import com.xyz.system.service.IUserService;
import com.xyz.util.SpringSecurityUtil;

/** 
 * 对登录结果进行处理
 */
public class LoginHandler implements AuthenticationSuccessHandler,AuthenticationFailureHandler {
	@Autowired @Qualifier("taobaoUserServ")
	IUserService facade ;
	
	@Autowired
	ISecurityService sfacade ;
	/**
	 * 根据淘宝用户授权情况进入相应页面
	 * @param request
	 * @return
	 */
	@ResponseBody
	public void onAuthenticationSuccess(HttpServletRequest req,
			HttpServletResponse res, Authentication authentication)
			throws IOException, ServletException {
		User user = SpringSecurityUtil.getCurrentUser();
		MappingJacksonJsonView mav = new MappingJacksonJsonView();
		Map<String, Object> model = new HashMap<String, Object>();
		boolean isSuccess = false;
		try {
			if(user==null)
			{
				Logger.warn(getClass(), "当前没有登录，请先登录");
				mav.render(model, req, res);
				return;
			}
			if("admin".equals(user.getUsername()))
			{
				Shop shop = facade.getAuthorizeShop();
				user.setShop(shop);
				isSuccess = true;
				model.put("success", isSuccess);
				mav.render(model, req, res);
				return;
			}
			Shop shop = user.getShop();
			if(shop==null || (!shop.getIsAuthorize())){
				model.put("msg", "请使用店铺帐号先登录淘宝商城，再进入系统");
				Logger.warn(getClass(), "当前没有登录，请先登录");
				mav.render(model, req, res);
			}
			isSuccess = true;
			model.put("success", isSuccess);
			mav.render(model, req, res);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@ResponseBody
	public void onAuthenticationFailure(HttpServletRequest req,
			HttpServletResponse res, AuthenticationException exception)
			throws IOException, ServletException {
        MappingJacksonJsonView mav = new MappingJacksonJsonView();
		Map<String, Object> model = new HashMap<String, Object>();
		boolean isSuccess = false;
		model.put("success", isSuccess);
		try {
			mav.render(model, req, res);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return;
	}
}