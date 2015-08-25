package com.xyz.trade.client.i18n;

import com.xyz.system.client.i18n.BaseMessages;

public interface TradeMessages  extends BaseMessages {

	/**
	 * 页面标签对应
	 */
	String tradeList();	
	String tradeQuery();	
	String orderForm();
	String customer();
	String receiver();
	String lTid();
	String phone();
	String country();
	String lBuyerNick();
	String receiverName();
	String receiverState();
	String receiverCity();
	String receiverDistrict();
    String receiverAddress();
    String lTitle();
    String lCreated();
    String lStatus();
    String lBuyerMessage();
    String lPayment();
    String lNum();
    String lBuyerEmail();
    String lPayTime();
	
    String receiverZip();
	String tradeDesc();
	String type();
    String lBuyerIm();
    String cusName();
	String lShop();
    String lAdjustFee();
    String lLevel();
    String lTotalFee();
    String lSellerRate();

    /**
     * 数据模型对应
     */
    //买家昵称
	String dBuyerNick();
	String type_name();
    String recZip_name();
	String typeCb_name();
	String vShop_name();   
	String vAdjustFee_name();
	String vLevelCb_name();
	String vTotalFee_name();
	String receiverCity_name();
	String receiverName_name();
	String recAddress_name();
	String receiverState_name();
	String vsellerRateCb_name();
	String recDistrict_name();
}
