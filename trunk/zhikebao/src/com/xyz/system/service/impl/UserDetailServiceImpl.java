package com.xyz.system.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.xyz.system.model.Authority;
import com.xyz.system.model.Shop;
import com.xyz.system.model.User;
import com.xyz.system.service.ISecurityService;
import com.xyz.system.service.IUserService;

/**
 * 实现SpringSecurity的UserDetailsService接口,实现获取用户Detail信息的回调函数.
 * @author Val
 */
@Service("userDetailsService")
@Transactional(readOnly = true)
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
	public ISecurityService securityManager;
    
	@Autowired @Qualifier("taobaoUserServ")
	public IUserService facade;

	/**
	 * 获取用户Detail信息的回调函数.
	 */
    @Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly = true)
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException, DataAccessException {

		User user = securityManager.findUserByLoginName(userName);
		if (user == null)
			throw new UsernameNotFoundException("用户" + userName + " 不存在");
		//查找本用户对应的淘宝用户
		if(user.getShopKey()!=null)
		{
			Shop tu = facade.findById(user.getShopKey());
			user.setShop(tu);
		}
		if(user.getZkbAuthorities()!=null&&user.getZkbAuthorities().size()>0)
		{
			List<Authority> la = user.getZkbAuthorities();
			String[] strs = new String[la.size()];
			for(int i=0;i<la.size();i++)
			{
				strs[i] = la.get(i).getRole();
			}
			user.setAuthes(strs);
		}
        return user;
	}
	
}
