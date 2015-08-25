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
import com.xyz.system.client.mvc.Code;
import com.xyz.trade.client.model.Delivery;
import com.xyz.trade.client.model.LogisticCompany;
import com.xyz.trade.client.model.OrderModel;
import com.xyz.trade.client.model.TradeModel;

@GWTRequestMapping("/tradeservice")
public interface TradeService extends RemoteService {

  public PagingLoadResult<TradeModel> getTrades(PagingLoadConfig loadConfig,TradeModel tm);
  
  public List<Code> getCusType();
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
  public boolean assignOwner(String[] tradeKeys,String userKey);
  
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
  public boolean deliverySend(Delivery deli);
}
