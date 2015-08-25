/*
 * Ext GWT - Ext for GWT
 * Copyright(c) 2007-2009, Ext JS, LLC.
 * licensing@extjs.com
 * 
 * http://extjs.com/license
 */
package com.xyz.main.client.view;

import com.extjs.gxt.ui.client.Registry;
import com.extjs.gxt.ui.client.Style.Direction;
import com.extjs.gxt.ui.client.event.EventType;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.TabPanelEvent;
import com.extjs.gxt.ui.client.fx.FxConfig;
import com.extjs.gxt.ui.client.mvc.AppEvent;
import com.extjs.gxt.ui.client.mvc.Controller;
import com.extjs.gxt.ui.client.mvc.Dispatcher;
import com.extjs.gxt.ui.client.mvc.View;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.Dialog;
import com.extjs.gxt.ui.client.widget.TabItem;
import com.extjs.gxt.ui.client.widget.TabPanel;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.Window;
import com.xyz.resources.client.AppEvents;
import com.xyz.resources.client.pages.Page;
import com.xyz.resources.model.Entry;

public class MainView extends View {
	private ContentPanel center ;
	private TabPanel tabPanel;
	private Dialog task;

	public MainView(Controller controller) {
		super(controller);
	}

	public void initialize() {
		tabPanel = new TabPanel();
		tabPanel.setCloseContextMenu(true);
		tabPanel.setBorderStyle(false);
		tabPanel.setBodyBorder(false);
		tabPanel.setTabScroll(true);
		tabPanel.setAnimScroll(true);
		tabPanel.addListener(Events.Remove, new Listener<TabPanelEvent>() {
			public void handleEvent(TabPanelEvent be) {
				TabItem item = be.getItem();
				Entry entry = (Entry) item.getData("entry");
				Dispatcher.forwardEvent(AppEvents.HidePage, entry);
			}
		});
		tabPanel.addListener(Events.Select, new Listener<TabPanelEvent>() {
			public void handleEvent(TabPanelEvent be) {
				String token = History.getToken();
				Entry entry = (Entry) be.getItem().getData("entry");
				if (token != null && (!token.equals(entry.getId()))) {
					History.newItem(entry.getId(), false);
				}
				Dispatcher.forwardEvent(AppEvents.TabChange, entry);
			}
		});
		center = (ContentPanel) Registry.get(AppView.CENTER_PANEL);
		center.add(tabPanel);
	}

	public void onShowPage(Entry entry) {
		Page page = entry.get("page");
		if (page == null) {
			page = new Page(entry);
			entry.set("page", page);
		}

		TabItem item = tabPanel.findItem("page__" + page.getId(), false);
		if (item == null) {
			item = new TabItem();
			item.setData("entry", entry);
			item.setClosable(entry.isClosable());
			item.setId("page__" + page.getId());
			item.setText(entry.getName());
			item.setLayout(new FitLayout());
			item.add(page);
			tabPanel.add(item);
		}

		if (item != tabPanel.getSelectedItem()) {
			tabPanel.setSelection(item);
		}
	}

	protected void handleEvent(AppEvent event) {
		EventType type = event.getType();
		if (type == AppEvents.ShowPage) {
			Entry entry = event.getData();
			onShowPage(entry);
		} 
	}
}
