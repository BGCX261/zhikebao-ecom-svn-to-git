/*
 * Ext GWT - Ext for GWT
 * Copyright(c) 2007-2009, Ext JS, LLC.
 * licensing@extjs.com
 * 
 * http://extjs.com/license
 */
package com.xyz.main.client.mvc;

import com.extjs.gxt.ui.client.event.EventType;
import com.extjs.gxt.ui.client.mvc.AppEvent;
import com.extjs.gxt.ui.client.mvc.Controller;
import com.xyz.main.client.view.MainView;
import com.xyz.resources.client.AppEvents;

public class MainController extends Controller {
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
    mainView = new MainView(this);
  }

}
