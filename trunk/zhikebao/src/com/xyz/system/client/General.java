package com.xyz.system.client;

import com.extjs.gxt.ui.client.Registry;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.ServiceDefTarget;
import com.xyz.resources.client.icons.Icons;
import com.xyz.system.client.i18n.BaseMessages;
import com.xyz.system.client.icons.SysIcons;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class General implements EntryPoint {
	
	public static final SysIcons ICONS = GWT.create(SysIcons.class);
	
    public static final String BASESERVICE = "/zhikebao/baseservice";
	
	public static final String MESSAGE = "generalmessages";

	public void onModuleLoad() {
		
		// 创建i18n对象
		BaseMessages message = GWT.create(BaseMessages.class);
		Registry.register(MESSAGE, message);
		
		regBaseService();
		
	}
	
	  private void regBaseService() {
			GeneralServiceAsync service = (GeneralServiceAsync) GWT.create(GeneralService.class);
		    ServiceDefTarget endpoint = (ServiceDefTarget) service;
		    String moduleRelativeURL = BASESERVICE;
		    endpoint.setServiceEntryPoint(moduleRelativeURL);
		    Registry.register(BASESERVICE, service);
		  }
}
