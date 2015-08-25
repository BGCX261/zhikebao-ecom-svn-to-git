/*
 * Ext GWT - Ext for GWT
 * Copyright(c) 2007-2009, Ext JS, LLC.
 * licensing@extjs.com
 * 
 * http://extjs.com/license
 */
package com.xyz.main.client.mvc;

import com.extjs.gxt.ui.client.Registry;
import com.extjs.gxt.ui.client.event.EventType;
import com.extjs.gxt.ui.client.mvc.AppEvent;
import com.extjs.gxt.ui.client.mvc.Controller;
import com.extjs.gxt.ui.client.mvc.Dispatcher;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.xyz.main.client.Main;
import com.xyz.main.client.MainServiceAsync;
import com.xyz.main.client.view.MenuView;
import com.xyz.main.client.view.PortalModel;
import com.xyz.main.client.view.ViewModel;
import com.xyz.resources.client.AppEvents;

public class MenuController extends Controller {

  private MainServiceAsync service;
  private MenuView view;

  public MenuController() {
    registerEventTypes(AppEvents.Init);
    registerEventTypes(AppEvents.Navi);
    registerEventTypes(AppEvents.HidePage);
    registerEventTypes(AppEvents.TabChange);
  }

  public void initialize() {
    view = new MenuView(this);
    service = (MainServiceAsync) Registry.get(Main.SERVICE);
  }

  public void handleEvent(AppEvent event) {
    EventType t = event.getType();
    if (t == AppEvents.Init) {
    	onInit(event);
    }else if (t == AppEvents.Navi || t == AppEvents.HidePage || t == AppEvents.TabChange) {
       forwardToView(view, event);
    }
  }

  private void onInit(AppEvent event) {
	    forwardToView(view, event);
	    service.getModels(new AsyncCallback<ViewModel>() {
	      public void onFailure(Throwable caught) {
	        Dispatcher.forwardEvent(AppEvents.Error, caught);
	      }

	      public void onSuccess(ViewModel result) {
	        Dispatcher.forwardEvent(AppEvents.Navi, result);
	      }
	    });
	}
}
