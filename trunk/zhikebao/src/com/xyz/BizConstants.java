package com.xyz;

import java.util.HashMap;
import java.util.Map;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.JavaScriptObject;

public class BizConstants {
        
	/*":TRADE_NO_CREATE_PAY": "没有创建支付宝交易",
	":WAIT_BUYER_PAY": "等待买家付款",
	":WAIT_SELLER_SEND_GOODS": "买家已付款等待发货",
	":WAIT_BUYER_CONFIRM_GOODS": "卖家已发货等待确认收货",
	":TRADE_BUYER_SIGNED":"买家已签收",
	":TRADE_FINISHED":"交易成功",
	":TRADE_CLOSED":"交易关闭",
	":TRADE_CLOSED_BY_TAOBAO":"交易被淘宝关闭",
	":ALL_WAIT_PAY":"等待支付",
	":ALL_CLOSED":"交易关闭"*/
	
	public static final String TRADE_STA_TRADE_NO_CREATE_PAY = "TRADE_NO_CREATE_PAY";
	
	public static final String TRADE_STA_WAIT_SELLER_SEND_GOODS = "WAIT_SELLER_SEND_GOODS";
	
	public static final String TRADE_STA_WAIT_BUYER_CONFIRM_GOODS = "WAIT_BUYER_CONFIRM_GOODS";
	
	public static final String TRADE_STA_TRADE_BUYER_SIGNED = "TRADE_BUYER_SIGNED";
	
	public static final String TRADE_STA_TRADE_FINISHED = "TRADE_FINISHED";
	
	public static final String TRADE_STA_TRADE_CLOSED = "TRADE_CLOSED";
}
