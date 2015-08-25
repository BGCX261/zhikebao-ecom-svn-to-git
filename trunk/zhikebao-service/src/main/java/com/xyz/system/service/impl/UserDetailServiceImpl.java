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
    private ISecurityService securityManager;
    
	@Autowired @Qualifier("taobaoUserServ")
	IUserService facade;

	/**
	 * 获取用户Detail信息的回调函数.
	 */
    @Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly = true)
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException, DataAccessException {

		User user = securityManager.findUserByLoginName(userName);
		if (user == null)
			throw new UsernameNotFoundException("用户" + userName + " 不存在");
		getAuthorities(user);
		//查找本用户对应的淘宝用户
		if(user.getShopKey()!=null)
		{
			Shop tu = facade.findById(user.getShopKey());
			user.setShop(tu);
		}
        return user;
	}
	
	/**
	 * 获得用户所有角色的权限和菜单列表
	 */
	public void getAuthorities(User u) {
		List<Authority> la = securityManager.findAuthsByKeys(u.getAuthorityKeys());
		List<GrantedAuthority> lg = new ArrayList<GrantedAuthority>();
		for(Authority a : la)
		{
			lg.add(a);
		}
		u.setAuthorities(lg);
	}
	
}
