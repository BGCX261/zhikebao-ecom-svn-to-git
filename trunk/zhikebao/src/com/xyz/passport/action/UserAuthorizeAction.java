/*
 * Generated by ActionName
 * Template path: templates/java/JavaClass.vtl
 */
package com.xyz.passport.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.xyz.system.model.Shop;
import com.xyz.system.service.IUserService;

/** 
 * ActionName
 * 创建日期: 08-19-2009
 * 
 * XDoclet definition:
 * 作者:Val
 */
@Controller 
public class UserAuthorizeAction{
	@Autowired @Qualifier("taobaoUserServ")
	IUserService facade ;
	/**
	 * 
	 * @return
	 */
	@RequestMapping("/userAuthorize.do")
	public String forward(HttpServletRequest request) {
		Shop shop = facade.findAll().get(0);
		request.setAttribute("IsAuthorize", shop.getIsAuthorize());
		return "login";
	}
}