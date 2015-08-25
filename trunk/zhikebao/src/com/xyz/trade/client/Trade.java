package com.xyz.trade.client;

import com.extjs.gxt.ui.client.Registry;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.ServiceDefTarget;
import com.xyz.resources.client.icons.Icons;
import com.xyz.trade.client.i18n.TradeMessages;
import com.xyz.trade.client.icons.TradeIcons;
import com.xyz.trade.model.TradeModel;

public class Trade implements EntryPoint {

	public static final String MODEL = "tradeModel";
    public static final String TRADESERVICE = "/zhikebao/tradeservice";
    public static final String MESSAGE = "trademessages";
	public static final Icons ICONS = GWT.create(TradeIcons.class);

	@Override
	public void onModuleLoad() {
        // 注册后台服务
		regTradeService();
        // 创建i18n对象
		TradeMessages message = GWT.create(TradeMessages.class);
		Registry.register(MESSAGE, message);
	}

	private void regTradeService() {
		TradeServiceAsync service = (TradeServiceAsync) GWT
				.create(TradeService.class);
		ServiceDefTarget endpoint = (ServiceDefTarget) service;
		String moduleRelativeURL = TRADESERVICE;
		endpoint.setServiceEntryPoint(moduleRelativeURL);
		Registry.register(TRADESERVICE, service);
	}

}
