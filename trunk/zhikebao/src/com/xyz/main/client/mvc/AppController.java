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
import com.extjs.gxt.ui.client.widget.MessageBox;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.xyz.main.client.Main;
import com.xyz.main.client.MainServiceAsync;
import com.xyz.main.client.view.AppView;
import com.xyz.resources.client.AppEvents;
import com.xyz.resources.client.i18n.ZkMessages;

public class AppController extends Controller {

	private AppView appView;
	private MainServiceAsync service;

	private ZkMessages msg;

	public AppController() {
		registerEventTypes(AppEvents.Init);
		registerEventTypes(AppEvents.Error);
		registerEventTypes(AppEvents.NoticeTask);
	}

	public void handleEvent(AppEvent event) {
		EventType type = event.getType();
		if (type == AppEvents.Init) {
			onInit(event);
		} else if (type == AppEvents.Error) {
			onError(event);
		}else if(type == AppEvents.NoticeTask) {
	    	forwardToView(appView, event);
	    }
	}

	public void initialize() {
		appView = new AppView(this);
		service = (MainServiceAsync) Registry.get(Main.SERVICE);
		msg = (ZkMessages) Registry.get(Main.MESSAGE);
	}

	protected void onError(AppEvent ae) {
		System.out.println("error: " + ae.<Object> getData());
	}

	private void onInit(AppEvent event) {
		forwardToView(appView, event);
		//开始重复轮询，延长会话时间并校验会话状态
		Timer timer = new Timer() {
			public void run() {
				service.validateSession(new AsyncCallback<Boolean>() {
					public void onSuccess(Boolean isSucess) {
						if (!isSucess) {
							MessageBox.alert(msg.alert(), "会话已过期，请重新验证", null);
							Window.Location.replace("/zhikebao/index");
						}else{
							//Dispatcher.forwardEvent(AppEvents.NoticeTask);
						}
					}

					public void onFailure(Throwable caught) {
						Dispatcher.forwardEvent(AppEvents.Error, caught);
					}
				});
			}
		};
		timer.scheduleRepeating(1000 * 60 * 1);
	}

}
