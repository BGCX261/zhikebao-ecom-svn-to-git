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

import com.xyz.main.client.widget.ViewModel;
import com.xyz.resources.client.model.Folder;
import com.xyz.resources.client.model.Item;
import com.xyz.system.client.mvc.UserModel;
import com.google.gwt.user.client.rpc.AsyncCallback;

public interface MainServiceAsync {

  public void getModels(AsyncCallback<ViewModel> callback);

  public void getItems(Folder folder, AsyncCallback<List<Item>> callback);
  
  public void validateSession(AsyncCallback<Boolean> callback);
  
  public void getUserNick(AsyncCallback<String> callback);

}
