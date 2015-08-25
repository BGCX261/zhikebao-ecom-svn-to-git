/*
 * Ext GWT - Ext for GWT
 * Copyright(c) 2007-2009, Ext JS, LLC.
 * licensing@extjs.com
 * 
 * http://extjs.com/license
 */
package com.xyz.main.client;

import java.util.List;

import org.gwtwidgets.server.spring.GWTRequestMapping;

import com.xyz.customer.model.Guest;
import com.xyz.main.client.view.ViewModel;
import com.xyz.main.model.Shop;
import com.google.gwt.user.client.rpc.AsyncCallback;

public interface MainServiceAsync {

  public void getModels(AsyncCallback<ViewModel> callback);
/*
  public List<Item> getItems(Folder folder);*/
  
  public void validateSession(AsyncCallback<Boolean> callback);
  
  public void getUserNick(AsyncCallback<String> callback);
  
  public void getShopInfo(AsyncCallback<Shop> callback);
  
  public void getNewestGuests(AsyncCallback<List<Guest>> callback);

}
