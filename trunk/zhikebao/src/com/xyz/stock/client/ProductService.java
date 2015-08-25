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
import com.google.gwt.user.client.rpc.RemoteService;
import com.xyz.product.model.Item;
import com.xyz.resources.model.Folder;

@GWTRequestMapping("/prodservice")
public interface ProductService extends RemoteService {

  public List<Folder> getCategories(String parentId);
  /**
   * 获取在售商品列表
   * @param loadConfig
   * @param tm
   * @return
   */
  public PagingLoadResult<Item> getOnsaleItems(PagingLoadConfig loadConfig,Item tm);

}
