/*
 * Ext GWT - Ext for GWT
 * Copyright(c) 2007-2009, Ext JS, LLC.
 * licensing@extjs.com
 * 
 * http://extjs.com/license
 */
package com.xyz.main.client.mvc;

import java.util.Arrays;
import java.util.List;

import com.extjs.gxt.ui.client.Registry;
import com.extjs.gxt.ui.client.data.BaseTreeLoader;
import com.extjs.gxt.ui.client.data.LoadEvent;
import com.extjs.gxt.ui.client.data.Loader;
import com.extjs.gxt.ui.client.data.ModelData;
import com.extjs.gxt.ui.client.data.TreeLoader;
import com.extjs.gxt.ui.client.data.TreeModel;
import com.extjs.gxt.ui.client.data.TreeModelReader;
import com.extjs.gxt.ui.client.event.EventType;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.LoadListener;
import com.extjs.gxt.ui.client.event.TreePanelEvent;
import com.extjs.gxt.ui.client.mvc.AppEvent;
import com.extjs.gxt.ui.client.mvc.Controller;
import com.extjs.gxt.ui.client.mvc.Dispatcher;
import com.extjs.gxt.ui.client.mvc.View;
import com.extjs.gxt.ui.client.store.Store;
import com.extjs.gxt.ui.client.store.TreeStore;
import com.extjs.gxt.ui.client.util.IconHelper;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.button.IconButton;
import com.extjs.gxt.ui.client.widget.form.StoreFilterField;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.extjs.gxt.ui.client.widget.toolbar.ToolBar;
import com.extjs.gxt.ui.client.widget.treepanel.TreePanel;
import com.extjs.gxt.ui.client.widget.treepanel.TreePanel.TreeNode;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.xyz.main.client.Main;
import com.xyz.main.client.MainServiceAsync;
import com.xyz.main.client.widget.ViewModel;
import com.xyz.resources.client.AppEvents;
import com.xyz.resources.client.i18n.ZkMessages;
import com.xyz.resources.client.model.Category;
import com.xyz.resources.client.model.Entry;

public class MenuView extends View {

	private ContentPanel westPanel;
	private TreeStore<ModelData> store;
	private TreePanel<ModelData> tree;
	private TreeLoader<ModelData> loader;
	private ZkMessages msg;
	private MainServiceAsync service;

	private ToolBar toolBar;
	private StoreFilterField<ModelData> filter;

	public MenuView(Controller controller) {
		super(controller);
		service = (MainServiceAsync) Registry.get(Main.SERVICE);
	}

	protected void initialize() {
		
		filter = new StoreFilterField<ModelData>() {
			@Override
			protected boolean doSelect(Store<ModelData> store, ModelData parent,
					ModelData child, String property, String filter) {
				String name = child.get("name");
				name = name.toLowerCase();
				if (name.indexOf(filter.toLowerCase()) != -1) {
					return true;
				}
				return false;
			}
		};
		msg = (ZkMessages) Registry.get(Main.MESSAGE);
		westPanel = (ContentPanel) Registry.get(AppView.WEST_PANEL);
		//westPanel.setBorders(true);
		final StringBuilder headStr = new StringBuilder();
		
		service.getUserNick(new AsyncCallback<String>() {
			public void onSuccess(String nick) {
				if (nick!=null) {
					headStr.append(msg.menu());
					headStr.append(nick);
					headStr.append("回来。<a href='/j_spring_security_logout'>离开</a>");
					westPanel.setHeading(headStr.toString());
				}
			}
            public void onFailure(Throwable caught) {
				Dispatcher.forwardEvent(AppEvents.Error, caught);
			}
		});
		westPanel.setLayout(new FitLayout());
		toolBar = (ToolBar) westPanel.getTopComponent();
		IconButton filterBtn = new IconButton("icon-filter");
		filterBtn.setWidth(20);
		toolBar.add(filterBtn);
		toolBar.add(filter);

		loader = new BaseTreeLoader<ModelData>(new TreeModelReader<List<ModelData>>()) {
			@Override
		      public boolean hasChildren(ModelData parent) {
		        return parent instanceof Category;
		      }

		};
		
		store = new TreeStore<ModelData>(loader);
		filter.bind(store);

		tree = new TreePanel<ModelData>(store);
		tree.setBorders(true);
		tree.getStyle().setLeafIcon(IconHelper.createStyle("icon-list"));
		tree.setDisplayProperty("name");
		/**
		 * 为菜单项注册事件，点击菜单在右边主区域显示页面。
		 */
		tree.addListener(Events.OnClick,new Listener<TreePanelEvent<ModelData>>() {
	      		@Override
			public void handleEvent(TreePanelEvent<ModelData> event) {
	      	    ModelData sel = event.getItem();
		        if (sel!=null) {
		          TreeModel m = (TreeModel) sel;
		          if (m != null && m instanceof Entry) {
		             Main.showPage((Entry) m);
		          }else{
		        	 TreeNode node = event.getNode();
		        	 if(node!=null)
		        	 {
		        		 if(node.isExpanded())
		        		   node.setExpanded(false);
		        		 else
		        		   node.setExpanded(true); 
		        	 }
		        	 
		          }
		        }
			
		}
	    });
		
		//SelectionService.get().addListener(new SourceSelectionChangedListener(tree.getSelectionModel()));
	    //SelectionService.get().register(tree.getSelectionModel());
		
		westPanel.add(tree);
	}

	protected void handleEvent(AppEvent event) {
		EventType type = event.getType();
		if (type == AppEvents.HidePage) {
			tree.getSelectionModel().deselectAll();
		} else if (type == AppEvents.TabChange) {
			if (((Entry) event.getData()).getName() == "main") {
				tree.getSelectionModel().deselectAll();
			} else {
				tree.getSelectionModel().setSelection((List)Arrays.asList((Entry) event.getData()));
			}
		} else if (event.getType() == AppEvents.Navi) {
			ViewModel model = event.getData();
			model.loadEntries(model);
			if (model != null) {
				loader.addListener(Loader.Load, new LoadListener() {
					@Override
					public void loaderLoad(LoadEvent le) {
						loader.removeLoadListener(this);
					}
				});
				loader.load(model);
				/*ModelData md = store.getChild(0);
				if(md!=null)
				{
				    tree.setExpanded(md, true);
				}*/
				tree.expandAll();
			}
		}
	}
}
