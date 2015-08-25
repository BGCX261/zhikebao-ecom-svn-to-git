package com.xyz.product.service;

import com.xyz.LocalDatastoreTestCase;
import com.xyz.system.dao.UserDao;

import junit.framework.TestCase;

public class ProdServiceTest extends LocalDatastoreTestCase {

	public void testGetCategories() {
		IProdService ps = (IProdService)getAc().getBean("prodService");
		ps.getCategories("0");
	}

}
