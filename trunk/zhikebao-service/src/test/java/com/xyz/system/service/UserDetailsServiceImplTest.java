package com.xyz.system.service;

import java.util.List;

import org.easymock.EasyMock;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.xyz.system.dao.SecurityData;
import com.xyz.system.model.Authority;
import com.xyz.system.model.User;
import com.xyz.system.service.impl.SecurityManager;
import com.xyz.system.service.impl.UserDetailServiceImpl;
import com.xyz.util.ReflectionUtil;

/**
 * UserDetailsServiceImpl的单元测试用例, 测试Service层的业务逻辑. 
 * 
 * 使用EasyMock对UserManager进行模拟.
 * 
 * @author calvin
 */
public class UserDetailsServiceImplTest extends Assert {

	private UserDetailServiceImpl userDetailService = new UserDetailServiceImpl();
	private ISecurityService securityManager = null;

	@Before
	public void setUp() {
		securityManager = EasyMock.createNiceMock(ISecurityService.class);
		ReflectionUtil.setFieldValue(userDetailService, "securityManager", securityManager);
	}

	@After
	public void tearDown() {
		EasyMock.verify(securityManager);
	}

	@Test
	public void loadUserExist() {

		String authName = "A_ADMIN";

		User user = SecurityData.getRandomUser();
		Authority auth = new Authority();
		auth.setRole(authName);
		//user.getAuthorityKeys().add(auth);

		//录制脚本
		EasyMock.expect(securityManager.findUserByLoginName(user.getUsername())).andReturn(user);
		EasyMock.replay(securityManager);

		//执行测试
		UserDetails userDetails = userDetailService.loadUserByUsername(user.getUsername());

		//校验结果
		assertEquals(user.getUsername(), userDetails.getUsername());
		assertEquals(user.getPassword(), userDetails.getPassword());
		assertEquals(1, userDetails.getAuthorities().size());
		assertEquals(new GrantedAuthorityImpl(authName), ((List)userDetails.getAuthorities()).get(0));
	}

	@Test(expected = UsernameNotFoundException.class)
	public void loadUserNotExist() {
		//录制脚本
		EasyMock.expect(securityManager.findUserByLoginName("userNameNotExist")).andReturn(null);
		EasyMock.replay(securityManager);
		//执行测试
		userDetailService.loadUserByUsername("userNameNotExist");
	}
}