package com.xyz.base.dao;

import org.junit.Test;

import com.xyz.LocalDatastoreTestCase;
import com.xyz.base.model.BaseCode;
import com.xyz.base.service.IBaseService;
import com.xyz.framework.log.Logger;
import com.xyz.util.Page;

public class BaseDaoTest extends LocalDatastoreTestCase {

	@Test
	public void testSave() {
		IBaseService bs = (IBaseService)getAc().getBean("baseService");
		bs.save(new BaseCode());
		Page p = new Page(20);
		p = bs.findAll(p);
		Logger.debug("fff"+p.getResult().size());
	}

}
