package com.xyz.stock.client;

import com.extjs.gxt.ui.client.Registry;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.ServiceDefTarget;
import com.xyz.resources.client.icons.Icons;
import com.xyz.stock.client.i18n.StockMessages;
import com.xyz.stock.client.icons.StockIcons;

public class Stock implements EntryPoint {

    public static final String PRODSERVICE = "prodservice";
    public static final String MESSAGE = "stockmessages";
    public static final Icons ICONS = GWT.create(StockIcons.class);
    
	@Override
	public void onModuleLoad() {
		//注册后台服务
		regProdService();
		// 创建i18n对象
		StockMessages message = GWT.create(StockMessages.class);
		Registry.register(MESSAGE, message);
	}

	  private void regProdService() {
		ProductServiceAsync service = (ProductServiceAsync) GWT.create(ProductService.class);
	    ServiceDefTarget endpoint = (ServiceDefTarget) service;
	    String moduleRelativeURL = PRODSERVICE;
	    endpoint.setServiceEntryPoint(moduleRelativeURL);
	    Registry.register(PRODSERVICE, service);
	  }
}
