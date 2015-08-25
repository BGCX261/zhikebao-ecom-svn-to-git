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
import com.xyz.main.client.widget.ViewModel;
import com.xyz.resources.client.model.Folder;
import com.xyz.resources.client.model.Item;
import com.xyz.system.client.mvc.UserModel;

@GWTRequestMapping("/mainservice")
public interface MainService extends RemoteService {

  public ViewModel getModels();

  public List<Item> getItems(Folder folder);
  
  public Boolean validateSession();
  
  public String getUserNick();

}
