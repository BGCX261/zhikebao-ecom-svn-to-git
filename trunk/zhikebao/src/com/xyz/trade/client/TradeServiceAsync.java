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
import com.xyz.resources.model.BaseCode;
import com.xyz.resources.model.Result;
import com.xyz.trade.model.Delivery;
import com.xyz.trade.model.LogisticCompany;
import com.xyz.trade.model.OrderModel;
import com.xyz.trade.model.ShipAddress;
import com.xyz.trade.model.TradeModel;
import com.google.gwt.user.client.rpc.AsyncCallback;

public interface TradeServiceAsync {
  /**
   * 按条件查询交易列表
   * @param loadConfig
   * @param tm
   */
  public void getTrades(PagingLoadConfig loadConfig,TradeModel tm, AsyncCallback<PagingLoadResult<TradeModel>> callback);
  
  /**
   * 按条件查询发货地址列表
   * @param loadConfig
   * @param tm
   */
  public void getShipAddress(PagingLoadConfig loadConfig,ShipAddress sa, AsyncCallback<PagingLoadResult<ShipAddress>> callback);
  
  /**
   * 取得发货地址列表
   * @param loadConfig
   * @param tm
   */
  public void getShipAddress(AsyncCallback<List<ShipAddress>> callback);
  
  public void getCusType(AsyncCallback<List<BaseCode>> callback);
  /**
   * 查询今天的交易情况
   */
  public void getTodTraSta(AsyncCallback<Map<String,String>> callback);
  /**
   * 为交易指定负责人
   * @param tradeKeys
   * @param userKey
   */
  public void assignOwner(Integer[] tradeKeys,Integer userKey, AsyncCallback<Boolean> callback);
  
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
  public void deliverySend(Delivery deli, AsyncCallback<Result> callback);
  
  /**
   * 保存发货地址
   */
  public void saveShipAddress(ShipAddress sa, AsyncCallback<ShipAddress> callback);
  
  /**
   * 查询所有的物流公司，已选中和备选分开
   */
  public void getAllLogistics(Integer key, AsyncCallback<Map<String,List<LogisticCompany>>> callback);
  
  /**
   * 保存发货地址常用的物流公司
   */
  public void saveLogisticsOfShipAddress(Integer saId,List<Integer> logisIds, AsyncCallback<Boolean> callback);
}
