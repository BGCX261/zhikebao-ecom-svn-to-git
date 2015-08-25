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

import com.google.gwt.user.client.rpc.RemoteService;
import com.xyz.customer.model.Guest;
import com.xyz.main.client.view.ViewModel;
import com.xyz.main.model.Shop;

@GWTRequestMapping("/mainservice")
public interface MainService extends RemoteService {

  public ViewModel getModels();
/*
  public List<Item> getItems(Folder folder);*/
  
  public Boolean validateSession();
  
  public String getUserNick();
  
  public Shop getShopInfo();
  
  public List<Guest> getNewestGuests();

}
