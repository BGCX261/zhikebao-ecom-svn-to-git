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
import com.google.gwt.user.client.rpc.RemoteService;
import com.xyz.customer.client.mvc.CusModel;

@GWTRequestMapping("/cusservice")
public interface CusService extends RemoteService {

	  public PagingLoadResult<CusModel> getCuss(PagingLoadConfig loadConfig);
}
