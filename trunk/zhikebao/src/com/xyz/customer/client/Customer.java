package com.xyz.customer.client;

import com.extjs.gxt.ui.client.Registry;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.ServiceDefTarget;
import com.xyz.customer.client.i18n.CusMessages;
import com.xyz.customer.client.icons.CusIcons;
import com.xyz.resources.client.icons.Icons;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 * 客户管理模块
 */
public class Customer implements EntryPoint {
	
	public static final Icons ICONS = GWT.create(CusIcons.class);
	
    public static final String CUSSERVICE = "/zhikebao/cusservice";
	
	public static final String MESSAGE = "cusmessages";

	public void onModuleLoad() {
		
		// 创建i18n对象
		CusMessages message = GWT.create(CusMessages.class);
		Registry.register(MESSAGE, message);
		
		regCusService();
		
	}
	
	  private void regCusService() {
			CusServiceAsync service = (CusServiceAsync) GWT.create(CusService.class);
		    ServiceDefTarget endpoint = (ServiceDefTarget) service;
		    String moduleRelativeURL = CUSSERVICE;
		    endpoint.setServiceEntryPoint(moduleRelativeURL);
		    Registry.register(CUSSERVICE, service);
		  }
}
