/*
 * Generated by ActionName
 * Template path: templates/java/JavaClass.vtl
 */
package com.xyz.passport.action;

import it.sauronsoftware.base64.Base64;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import com.xyz.framework.log.Logger;
import com.xyz.system.model.Authority;
import com.xyz.system.model.Shop;
import com.xyz.system.model.User;
import com.xyz.system.service.ISecurityService;
import com.xyz.system.service.IUserService;

/**
 */
@Controller
public class AuthorizeAction{
	/*
	 *    回调地址: 		http://localhost:8080/zhikebao/authorize
	 *      授权码:        正式环境: http://auth.open.taobao.com/authorize?appkey=12012509
	 * SessionKey:     正式测试环境: http://container.open.taobao.com/container?authcode=TOP-10f9f0ed66d866148fe4da9d150550a904nICvN4lVv0Toh7hr8H4Ubu93DtLGGN-END
	 */
	@Autowired @Qualifier("taobaoUserServ")
	private IUserService facade;
	@Autowired
	private ISecurityService sfacade ;
	/**
	 * 淘宝回调方法,获取SessionKey
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 */
	@RequestMapping("/authorize")
	public String execute(HttpServletRequest request, ModelMap model) 
	{
		boolean isNewUser = false;
		boolean isNewShop = false;
		String sessionKey = request.getParameter("top_session");
		String parameters = request.getParameter("top_parameters");
		String appkey = request.getParameter("top_appkey");
		String nickName = "";
		try {
			String paramStr = Base64.decode(parameters, "UTF-8");
			String[] params = StringUtils.delimitedListToStringArray(paramStr, "&");
			if(params!=null&&params.length>0)
			{
				for(String param : params)
				{
					String[] strs = StringUtils.delimitedListToStringArray(param,"="); 
					if(strs!=null&&strs.length>0&&"visitor_nick".equals(strs[0]))
					{
						nickName =strs[1];
						break;
					}
				}	
		    }
		} catch (Exception e) {
			Logger.error(getClass(), "不能转换参数串");
			e.printStackTrace();
		}
		Shop shop = facade.findByTbAccount(nickName);
		if(shop==null)
		{   
			isNewShop = true;
			shop = new Shop(appkey,"6b88cd63cbb81b19b014cd5bd8cc418e",true);
		}
		User user = sfacade.findUserByLoginName(nickName);
		if(user == null)
		{
			isNewUser = true;
			user = new User(nickName,sessionKey);
			user.setIsAdmin(true);
	    	List<Authority> ss = new ArrayList<Authority>();
	    	Authority auth1 = sfacade.findAuth("A_ADMIN");
	    	Authority auth2 = sfacade.findAuth("A_USER");
	    	ss.add(auth1);
	    	ss.add(auth2);
	    	user.setZkbAuthorities(ss);
		}
		 shop.setAppKey(appkey);
		 shop.setTbAccount(nickName)	;
		 shop.setIsAuthorize(true);
		 shop.setAuthorizeTime(new Date());
		 shop.setSessionKey(sessionKey);
		 user.setShop(shop);
		 user.setPassword("zhikebao");
		 //获取用户其他信息
		 //facade.findFromTaobao(taobaoUser);
		 if(isNewShop)
		 {
			 facade.saveShop(shop);
			 user.setShopKey(shop.getPid());
		}else if(isNewUser){
			 facade.update(shop);
			 user.setShopKey(shop.getPid());
		}else{
			 facade.update(shop);
		 }
		 user = sfacade.saveUser(user);
	     model.put("user", user);
	     return "forward:/zhikebao/index";	
	 }
}

