package com.xyz.resources.client;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.JavaScriptObject;
import com.xyz.resources.client.model.Status;

public class ZkbConstants {

	private static class ZkbConstantsInstance {
		private static ZkbConstants instance = GWT.create(ZkbConstants.class);
	}

	/**
	 * 返回单例的ZkbConstants对象.
	 */
	public static ZkbConstants get() {
		return ZkbConstantsInstance.instance;
	}

	// 客户类别编码
	public final static Long CODE_CUS_TYPE = 36L;

	/**
	 * 交易状态常量Map
	 */
	protected JavaScriptObject dataMap;

	protected native void loadCurrencyMap() /*-{
		this.@com.xyz.resources.client.ZkbConstants::dataMap = {
		":TRADE_NO_CREATE_PAY": "没有创建支付宝交易",
		":WAIT_BUYER_PAY": "等待买家付款",
		":WAIT_SELLER_SEND_GOODS": "买家已付款等待发货",
		":WAIT_BUYER_CONFIRM_GOODS": "卖家已发货等待确认收货",
		":TRADE_BUYER_SIGNED":"买家已签收",
		":TRADE_FINISHED":"交易成功",
		":TRADE_CLOSED":"交易关闭",
		":TRADE_CLOSED_BY_TAOBAO":"交易被淘宝关闭",
		":ALL_WAIT_PAY":"等待支付",
		":ALL_CLOSED":"交易关闭"
		};
	}-*/;

	public String getStatus(String code) {
		if (dataMap == null) {
			loadCurrencyMap();
		}
        return getEntry(code);
	}
	
	public List<Status> getStaList(){
		List<Status> ls = new ArrayList<Status>();
		ls.add(new Status("WAIT_BUYER_PAY","等待买家付款"));
		ls.add(new Status("WAIT_SELLER_SEND_GOODS", "买家已付款等待发货"));
		ls.add(new Status("WAIT_BUYER_CONFIRM_GOODS", "卖家已发货等待确认收货"));
		ls.add(new Status("TRADE_BUYER_SIGNED","买家已签收"));
		ls.add(new Status("TRADE_FINISHED","交易成功"));
		ls.add(new Status("TRADE_CLOSED","交易关闭"));
		ls.add(new Status("TRADE_CLOSED_BY_TAOBAO","交易被淘宝关闭"));
		ls.add(new Status("ALL_WAIT_PAY","等待支付"));
		ls.add(new Status("ALL_CLOSED","交易关闭"));
		ls.add(new Status("TRADE_NO_CREATE_PAY","没有创建支付宝交易"));
		return ls;
	}

	/**
	 * Directly reference an entry in the currency map JSO.
	 * 
	 * @param code
	 *            ISO4217 currency code
	 * @return currency data
	 */
	protected final native String getEntry(String code) /*-{
		return this.@com.xyz.resources.client.ZkbConstants::dataMap[':' + code];
	}-*/;

}
