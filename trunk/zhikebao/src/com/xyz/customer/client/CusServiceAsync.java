/*
 * Ext GWT - Ext for GWT
 * Copyright(c) 2007-2009, Ext JS, LLC.
 * licensing@extjs.com
 * 
 * http://extjs.com/license
 */
package com.xyz.customer.client;

import org.gwtwidgets.server.spring.GWTRequestMapping;

import com.extjs.gxt.ui.client.data.PagingLoadConfig;
import com.extjs.gxt.ui.client.data.PagingLoadResult;
import com.xyz.customer.client.mvc.CusModel;
import com.google.gwt.user.client.rpc.AsyncCallback;

public interface CusServiceAsync {

	  public void getCuss(PagingLoadConfig loadConfig, AsyncCallback<PagingLoadResult<CusModel>> callback);
}
