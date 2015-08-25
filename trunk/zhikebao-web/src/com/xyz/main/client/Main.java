/*
 * Ext GWT - Ext for GWT
 * Copyright(c) 2007-2009, Ext JS, LLC.
 * licensing@extjs.com
 * 
 * http://extjs.com/license
 */
package com.xyz.main.client;

import java.util.ArrayList;
import java.util.List;

import com.extjs.gxt.ui.client.GXT;
import com.extjs.gxt.ui.client.Registry;
import com.extjs.gxt.ui.client.mvc.AppEvent;
import com.extjs.gxt.ui.client.mvc.Dispatcher;
import com.extjs.gxt.ui.client.util.Theme;
import com.extjs.gxt.ui.client.widget.Viewport;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.ServiceDefTarget;
import com.xyz.main.client.mvc.AppController;
import com.xyz.main.client.mvc.AppView;
import com.xyz.main.client.mvc.MainController;
import com.xyz.main.client.mvc.MenuController;
import com.xyz.main.client.widget.PortalModel;
import com.xyz.resources.client.AppEvents;
import com.xyz.resources.client.i18n.ZkMessages;
import com.xyz.resources.client.icons.Icons;
import com.xyz.resources.client.model.Entry;
import com.xyz.stock.client.ProductService;
import com.xyz.stock.client.ProductServiceAsync;

public class Main implements EntryPoint {
	
  public static final String MODEL = "model";
  private PortalModel model;

  public static final String SERVICE = "mainservice";
  
  public static final String MESSAGE = "messages";
  
  public static final Icons ICONS = GWT.create(Icons.class);
  
  public void onModuleLoad() {
    GXT.setDefaultTheme(Theme.BLUE, true);
    //后台服务列表
    regService();
    
    //创建i18n对象
    ZkMessages message = GWT.create(ZkMessages.class);  
    Registry.register(MESSAGE, message);
    
    Dispatcher dispatcher = Dispatcher.get();
    dispatcher.addController(new AppController());
    dispatcher.addController(new MenuController());
    dispatcher.addController(new MainController());
    
    dispatcher.dispatch(AppEvents.Init);
    GXT.hideLoadingPanel("loading");
    
    //初始化主界面
    model = new PortalModel();
    Registry.register(MODEL, model);
    String hash = Window.Location.getHash();

    showPage(model.findEntry("main"));

    Viewport v = Registry.get(AppView.VIEWPORT);
    v.layout(true);

    if (!"".equals(hash)) {
      hash = hash.substring(1);
      Entry entry = model.findEntry(hash);
      if (entry != null) {
        showPage(entry);
      }
    }
  }
  
   private void regService() {
	  MainServiceAsync service = (MainServiceAsync) GWT.create(MainService.class);
	  ServiceDefTarget endpoint = (ServiceDefTarget) service;
      String moduleRelativeURL = SERVICE;
      endpoint.setServiceEntryPoint(moduleRelativeURL);
      Registry.register(SERVICE, service);
   }
   
   public static void showPage(Entry entry) {
	    AppEvent appEvent = new AppEvent(AppEvents.ShowPage, entry);
	    appEvent.setHistoryEvent(true);
	    appEvent.setToken(entry.getId());
	    Dispatcher.forwardEvent(appEvent);
    }

/*   private void regProdService(List<ServiceMeta> lsm) {
	   for(ServiceMeta sm : lsm)
	   {
		  if(sm.getServiceName())
	    ProductServiceAsync service = (ProductServiceAsync) GWT.create(sm.getServiceClass());
	    ServiceDefTarget endpoint = (ServiceDefTarget) service;
	    String moduleRelativeURL = PRODSERVICE;
	    endpoint.setServiceEntryPoint(moduleRelativeURL);
	    Registry.register(PRODSERVICE, service);
	   }
  }
   
   private void regTradeService() {
	    TradeServiceAsync service = (TradeServiceAsync) GWT.create(TradeService.class);
	    ServiceDefTarget endpoint = (ServiceDefTarget) service;
	    String moduleRelativeURL = TRADESERVICE;
	    endpoint.setServiceEntryPoint(moduleRelativeURL);
	    Registry.register(TRADESERVICE, service);
 }*/
}
