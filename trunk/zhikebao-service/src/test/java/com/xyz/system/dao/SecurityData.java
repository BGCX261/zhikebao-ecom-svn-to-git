package com.xyz.system.dao;

import org.apache.commons.lang.RandomStringUtils;

import com.xyz.system.model.Authority;
import com.xyz.system.model.Resource;
import com.xyz.system.model.User;

/**
 * 安全相关实体测试数据生成.
 * 
 * @author calvin
 */
public class SecurityData {

	public static User getRandomUser() {
		String userName = "admin";

		User user = new User();
		user.setUsername(userName);
		user.setRealName(userName);
		user.setPassword("passwd");

		return user;
	}
	public static Authority getRandomAuthority() {
		String authName = "A_" + random();

		Authority authority = new Authority();
		authority.setRole(authName);
		authority.setDisplayName(authName);

		return authority;
	}

	public static Resource getRandomResource() {
		Resource resource = new Resource();
		resource.setValue("Resource" + random());
		resource.setResourceType(Resource.URL_TYPE);
		resource.setPosition(100);

		return resource;
	}

	public static String random() {
		return RandomStringUtils.randomAlphanumeric(5);
	}
}
