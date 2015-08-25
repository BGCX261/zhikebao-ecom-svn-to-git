/*
 * Ext GWT - Ext for GWT
 * Copyright(c) 2007-2009, Ext JS, LLC.
 * licensing@extjs.com
 * 
 * http://extjs.com/license
 */
package com.xyz.stock.client;

import java.util.List;

import com.extjs.gxt.ui.client.data.PagingLoadConfig;
import com.extjs.gxt.ui.client.data.PagingLoadResult;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.xyz.product.model.Item;
import com.xyz.resources.model.Folder;

public interface ProductServiceAsync {

  public void getCategories(String parentId, AsyncCallback<List<Folder>> callback);
  /**
   * 获取在售商品列表
   * @param loadConfig
   * @param tm
   */
  public void getOnsaleItems(PagingLoadConfig loadConfig,Item tm, AsyncCallback<PagingLoadResult<Item>> callback);

}
