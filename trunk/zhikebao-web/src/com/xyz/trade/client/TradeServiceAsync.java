/*
 * Ext GWT - Ext for GWT
 * Copyright(c) 2007-2009, Ext JS, LLC.
 * licensing@extjs.com
 * 
 * http://extjs.com/license
 */
package com.xyz.trade.client;

import java.util.List;
import java.util.Map;

import org.gwtwidgets.server.spring.GWTRequestMapping;

import com.extjs.gxt.ui.client.data.PagingLoadConfig;
import com.extjs.gxt.ui.client.data.PagingLoadResult;
import com.xyz.system.client.mvc.Code;
import com.xyz.trade.client.model.Delivery;
import com.xyz.trade.client.model.LogisticCompany;
import com.xyz.trade.client.model.OrderModel;
import com.xyz.trade.client.model.TradeModel;
import com.google.gwt.user.client.rpc.AsyncCallback;

public interface TradeServiceAsync {

  public void getTrades(PagingLoadConfig loadConfig,TradeModel tm, AsyncCallback<PagingLoadResult<TradeModel>> callback);
  
  public void getCusType(AsyncCallback<List<Code>> callback);
  /**
   * 查询今天的交易情况
   */
  public void getTodTraSta(AsyncCallback<Map<String,String>> callback);
  /**
   * 为交易指定负责人
   * @param tradeKeys
   * @param userKey
   */
  public void assignOwner(String[] tradeKeys,String userKey, AsyncCallback<Boolean> callback);
  
  /**
   * 查询交易订单
   */
  public void getOrdersOfTrade(String tid, AsyncCallback<List<OrderModel>> callback);
  
  /**
   * 查询物流公司列表
   */
  public void getLogisticCompanies(AsyncCallback<List<LogisticCompany>> callback);
  
  /**
   * 发货处理
   */
  public void deliverySend(Delivery deli, AsyncCallback<Boolean> callback);
}
