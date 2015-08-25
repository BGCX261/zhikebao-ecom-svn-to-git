package com.xyz.trade.service;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.xyz.LocalDatastoreTestCase;
import com.xyz.framework.log.Logger;
import com.xyz.util.Page;
import com.xyz.util.PropertyFilter;

public class TradeServiceTest extends LocalDatastoreTestCase {
	
	ITradeService ps;

	@Test
	public void testQueryPage() {
		ps = (ITradeService)getAc().getBean("tradeService");
		Page page = new Page(20);
		List<PropertyFilter> filters = new ArrayList<PropertyFilter>();
		page = ps.queryPage(page, filters);
		Logger.debug("查询结果是:"+page.getTotalCount());
	}
	
	public void testQueryAll() {
		ps = (ITradeService)getAc().getBean("tradeService");
		Page page = new Page(20);
		page = ps.findAll(page);
		Logger.debug("查询结果是:"+page.getTotalCount());
	}
	
	@Override
	public void tearDown() throws Exception {
		// TODO Auto-generated method stub
		super.tearDown();
	}


}
