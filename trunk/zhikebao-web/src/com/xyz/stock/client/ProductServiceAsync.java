/*
 * Ext GWT - Ext for GWT
 * Copyright(c) 2007-2009, Ext JS, LLC.
 * licensing@extjs.com
 * 
 * http://extjs.com/license
 */
package com.xyz.stock.client;

import java.util.List;

import org.gwtwidgets.server.spring.GWTRequestMapping;

import com.extjs.gxt.ui.client.data.PagingLoadConfig;
import com.extjs.gxt.ui.client.data.PagingLoadResult;
import com.xyz.resources.client.model.Folder;
import com.xyz.stock.client.model.ItemModel;
import com.xyz.trade.client.model.TradeModel;
import com.google.gwt.user.client.rpc.AsyncCallback;

public interface ProductServiceAsync {

  public void getCategories(String parentId, AsyncCallback<List<Folder>> callback);
  /**
   * 获取在售商品列表
   * @param loadConfig
   * @param tm
   */
  public void getOnsaleItems(PagingLoadConfig loadConfig,ItemModel tm, AsyncCallback<PagingLoadResult<ItemModel>> callback);

}
