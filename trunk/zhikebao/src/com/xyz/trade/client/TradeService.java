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
import com.google.gwt.user.client.rpc.RemoteService;
import com.xyz.resources.model.BaseCode;
import com.xyz.resources.model.Result;
import com.xyz.trade.model.Delivery;
import com.xyz.trade.model.LogisticCompany;
import com.xyz.trade.model.OrderModel;
import com.xyz.trade.model.ShipAddress;
import com.xyz.trade.model.TradeModel;

@GWTRequestMapping("/tradeservice")
public interface TradeService extends RemoteService {
  /**
   * 按条件查询交易列表
   * @param loadConfig
   * @param tm
   * @return
   */
  public PagingLoadResult<TradeModel> getTrades(PagingLoadConfig loadConfig,TradeModel tm);
  
  /**
   * 按条件查询发货地址列表
   * @param loadConfig
   * @param tm
   * @return
   */
  public PagingLoadResult<ShipAddress> getShipAddress(PagingLoadConfig loadConfig,ShipAddress sa);
  
  /**
   * 取得发货地址列表
   * @param loadConfig
   * @param tm
   * @return
   */
  public List<ShipAddress> getShipAddress();
  
  public List<BaseCode> getCusType();
  /**
   * 查询今天的交易情况
   * @return
   */
  public Map<String,String> getTodTraSta();
  /**
   * 为交易指定负责人
   * @param tradeKeys
   * @param userKey
   * @return
   */
  public boolean assignOwner(Integer[] tradeKeys,Integer userKey);
  
  /**
   * 查询交易订单
   */
  public List<OrderModel> getOrdersOfTrade(String tid);
  
  /**
   * 查询物流公司列表
   */
  public List<LogisticCompany> getLogisticCompanies();
  
  /**
   * 发货处理
   */
  public Result deliverySend(Delivery deli);
  
  /**
   * 保存发货地址
   */
  public ShipAddress saveShipAddress(ShipAddress sa);
  
  /**
   * 查询所有的物流公司，已选中和备选分开
   * @return
   */
  public Map<String,List<LogisticCompany>> getAllLogistics(Integer key);
  
  /**
   * 保存发货地址常用的物流公司
   */
  public boolean saveLogisticsOfShipAddress(Integer saId,List<Integer> logisIds);
}
