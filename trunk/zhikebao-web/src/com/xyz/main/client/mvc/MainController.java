/*
 * Ext GWT - Ext for GWT
 * Copyright(c) 2007-2009, Ext JS, LLC.
 * licensing@extjs.com
 * 
 * http://extjs.com/license
 */
package com.xyz.main.client.mvc;

import java.util.List;

import com.extjs.gxt.ui.client.Registry;
import com.extjs.gxt.ui.client.event.EventType;
import com.extjs.gxt.ui.client.mvc.AppEvent;
import com.extjs.gxt.ui.client.mvc.Controller;
import com.extjs.gxt.ui.client.mvc.Dispatcher;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.xyz.main.client.Main;
import com.xyz.main.client.MainServiceAsync;
import com.xyz.resources.client.AppEvents;
import com.xyz.resources.client.model.Folder;
import com.xyz.resources.client.model.Item;

public class MainController extends Controller {

  private MainServiceAsync service;
  private MainView mainView;

  public MainController() {
    registerEventTypes(AppEvents.Init);
    registerEventTypes(AppEvents.ShowPage);
  }

  @Override
  public void handleEvent(AppEvent event) {
    EventType type = event.getType();
    if (type == AppEvents.ShowPage) {
    	forwardToView(mainView, event);
    } 
  }


  public void initialize() {
    service = (MainServiceAsync) Registry.get(Main.SERVICE);
    mainView = new MainView(this);
  }

}
