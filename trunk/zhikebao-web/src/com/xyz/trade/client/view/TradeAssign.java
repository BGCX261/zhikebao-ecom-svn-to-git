/*
 * Ext GWT - Ext for GWT
 * Copyright(c) 2007-2009, Ext JS, LLC.
 * licensing@extjs.com
 * 
 * http://extjs.com/license
 */
package com.xyz.trade.client.view;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.extjs.gxt.ui.client.Registry;
import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.Style.SelectionMode;
import com.extjs.gxt.ui.client.Style.SortDir;
import com.extjs.gxt.ui.client.core.XTemplate;
import com.extjs.gxt.ui.client.data.BasePagingLoadConfig;
import com.extjs.gxt.ui.client.data.BasePagingLoader;
import com.extjs.gxt.ui.client.data.BeanModel;
import com.extjs.gxt.ui.client.data.BeanModelReader;
import com.extjs.gxt.ui.client.data.ModelKeyProvider;
import com.extjs.gxt.ui.client.data.PagingLoadConfig;
import com.extjs.gxt.ui.client.data.PagingLoadResult;
import com.extjs.gxt.ui.client.data.RpcProxy;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.GridEvent;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.mvc.Dispatcher;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.Dialog;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.grid.CheckBoxSelectionModel;
import com.extjs.gxt.ui.client.widget.grid.ColumnConfig;
import com.extjs.gxt.ui.client.widget.grid.ColumnData;
import com.extjs.gxt.ui.client.widget.grid.ColumnModel;
import com.extjs.gxt.ui.client.widget.grid.Grid;
import com.extjs.gxt.ui.client.widget.grid.GridCellRenderer;
import com.extjs.gxt.ui.client.widget.grid.RowExpander;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.extjs.gxt.ui.client.widget.layout.RowLayout;
import com.extjs.gxt.ui.client.widget.table.NumberCellRenderer;
import com.extjs.gxt.ui.client.widget.toolbar.PagingToolBar;
import com.extjs.gxt.ui.client.widget.toolbar.ToolBar;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.i18n.client.NumberFormat;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.xyz.resources.client.ZkbConstants;
import com.xyz.system.client.General;
import com.xyz.system.client.GeneralServiceAsync;
import com.xyz.system.client.mvc.Resource;
import com.xyz.system.client.mvc.UserModel;
import com.xyz.trade.client.Trade;
import com.xyz.trade.client.TradeServiceAsync;
import com.xyz.trade.client.i18n.TradeMessages;
import com.xyz.trade.client.model.TradeModel;

public class TradeAssign extends LayoutContainer {

	private TradeServiceAsync service;
	private GeneralServiceAsync geneService;
	private ContentPanel panel;
	private Grid<BeanModel> grid;
	private ListStore<BeanModel> store;
	private BasePagingLoader<PagingLoadResult<TradeModel>> loader;
	private GridCellRenderer<BeanModel> gridNumber;
	private GridCellRenderer<BeanModel> statusRen;

	private TradeMessages msg;

	public TradeAssign() {
		final NumberFormat currency = NumberFormat.getCurrencyFormat();
		final NumberFormat number = NumberFormat.getFormat("0.00");
		final NumberCellRenderer<Grid<BeanModel>> numberRenderer = new NumberCellRenderer<Grid<BeanModel>>(
				currency);
		service = (TradeServiceAsync) Registry.get(Trade.TRADESERVICE);
		msg = (TradeMessages) Registry.get(Trade.MESSAGE);

		statusRen = new GridCellRenderer<BeanModel>() {
			public String render(BeanModel model, String property,
					ColumnData config, int rowIndex, int colIndex,
					ListStore<BeanModel> store, Grid<BeanModel> grid) {
				String val = (String) model.get(property);
				String sta = ZkbConstants.get().getStatus(val);
				return sta;
			}
		};

		gridNumber = new GridCellRenderer<BeanModel>() {
			public String render(BeanModel model, String property,
					ColumnData config, int rowIndex, int colIndex,
					ListStore<BeanModel> store, Grid<BeanModel> grid) {
				return numberRenderer.render(null, property, model
						.get(property));
			}
		};

		panel = new ContentPanel();
		panel.setHeaderVisible(false);
		panel.setLayout(new RowLayout());
	}

	@Override
	protected void onRender(Element parent, int index) {
		super.onRender(parent, index);
		createExpander();
		setLayout(new FitLayout());
		add(panel);
	}

	private void createExpander() {
		
		// data proxy
		RpcProxy<PagingLoadResult<TradeModel>> proxy = new RpcProxy<PagingLoadResult<TradeModel>>() {
			@Override
			protected void load(Object loadConfig,
					AsyncCallback<PagingLoadResult<TradeModel>> callback) {
				//查询未分配的订单
				TradeModel tm = new TradeModel();
				tm.setOwnerId("0");
				service.getTrades((PagingLoadConfig) loadConfig, tm, callback);
			}
		};
		loader = new BasePagingLoader<PagingLoadResult<TradeModel>>(proxy,
				new BeanModelReader());

		final PagingToolBar pagingBar = new PagingToolBar(20);
		pagingBar.bind(loader);

		List<ColumnConfig> configs = new ArrayList<ColumnConfig>();
		StringBuilder sb = new StringBuilder();
		sb.append("<table>");
		sb.append("<tr><td>").append(msg.lPayment()).append(
				": {payment}</td><td>").append(msg.lNum()).append(
				": {num}</td></tr>");
		sb.append("<tr><td>").append(msg.lBuyerEmail()).append(
				": {buyerEmail}</td><td>").append(msg.lPayTime()).append(
				":{payTime}</span> </td></tr>");
		sb.append("<tr><td>").append(msg.lBuyerMessage()).append(
				":{buyerMessage}</td><td></td></tr>");
		sb.append("</table>");
		XTemplate tpl = XTemplate.create(sb.toString());

		RowExpander expander = new RowExpander();
		expander.setTemplate(tpl);
		configs.add(expander);

		ColumnConfig column = new ColumnConfig(msg.dBuyerNick(), msg
				.lBuyerNick(), 120);
		configs.add(column);

		column = new ColumnConfig("tid", msg.lTid(), 100);
		configs.add(column);

		column = new ColumnConfig("title", msg.lTitle(), 100);
		configs.add(column);

		column = new ColumnConfig("totalFee", msg.lTotalFee(), 100);
		column.setAlignment(HorizontalAlignment.RIGHT);
		column.setRenderer(gridNumber);
		configs.add(column);

		column = new ColumnConfig("status", msg.lStatus(), 160);
		column.setRenderer(statusRen);
		configs.add(column);

		column = new ColumnConfig("created", msg.lCreated(), 200);
		column.setAlignment(HorizontalAlignment.RIGHT);
		column.setDateTimeFormat(DateTimeFormat.getMediumDateTimeFormat());
		configs.add(column);
		
		CheckBoxSelectionModel<BeanModel> sm = new CheckBoxSelectionModel<BeanModel>(); 
		configs.add(sm.getColumn());  
		
		ColumnModel cm = new ColumnModel(configs);

		store = new ListStore<BeanModel>(loader);
		store.setKeyProvider(new ModelKeyProvider<BeanModel>() {
			public String getKey(BeanModel model) {
				return model.get("key");
			}
		});
		grid = new Grid<BeanModel>(store, cm);
		
		ContentPanel cp = new ContentPanel();
		cp.setLayout(new FitLayout());
		cp.setHeading(msg.tradeList());
		ToolBar toolBar = new ToolBar();
		toolBar.setAlignment(HorizontalAlignment.RIGHT);
		Button ctBtn = new Button(msg.assign(), Trade.ICONS.assign());
		ctBtn.addSelectionListener(new SelectionListener<ButtonEvent>() {
			@Override
			public void componentSelected(ButtonEvent ce) {
				List<BeanModel> lb = grid.getSelectionModel().getSelectedItems();
				if(lb==null||lb.size()<1)
				{
					Window.alert("请至少选择一个交易。");
					return;
				}
				Dialog dia = createSelDainlog();
				dia.show();
			}
		});
		toolBar.add(ctBtn);
		cp.setTopComponent(toolBar);
		cp.setBottomComponent(pagingBar);

		cp.setIcon(Trade.ICONS.table());
		cp.setAnimCollapse(false);
		cp.setCollapsible(true);

		// 添加翻页工具条
		grid.addListener(Events.Attach, new Listener<GridEvent<BeanModel>>() {
			public void handleEvent(GridEvent<BeanModel> be) {
				PagingLoadConfig config = new BasePagingLoadConfig();
				config.setOffset(0);
				config.setLimit(20);

				Map<String, Object> state = grid.getState();
				if (state.containsKey("offset")) {
					int offset = (Integer) state.get("offset");
					int limit = (Integer) state.get("limit");
					config.setOffset(offset);
					config.setLimit(limit);
				}
				if (state.containsKey("sortField")) {
					config.setSortField((String) state.get("sortField"));
					config.setSortDir(SortDir.valueOf((String) state
							.get("sortDir")));
				}
				loader.load(config);
			}
		});
		grid.setLoadMask(true);
		grid.setSelectionModel(sm);  
		grid.addPlugin(expander);
		grid.setAutoExpandColumn("title");
		// grid.getView().setAutoFill(true);
		grid.setAutoHeight(true);
	    grid.addPlugin(sm);  
		cp.add(grid);

		panel.add(cp);
	}
	
	public Dialog createSelDainlog()
	{
		final Dialog simple = new Dialog(); 
		simple.setSize(550, 300);
		simple.setModal(true);
		simple.setHeading("请选择员工：");  
		simple.setLayout(new FitLayout());
		//simple.setButtons(Dialog.OKCANCEL);
        final Grid<UserModel> usergrid = createGrid();
        ContentPanel userpanel = new ContentPanel();
        userpanel.setBodyBorder(false);
        
        userpanel.setLayout(new FitLayout());
        userpanel.add(usergrid);
        simple.add(userpanel);
		simple.add(usergrid);
		simple.getButtonBar().removeAll();
	    simple.getButtonBar().add(new Button(msg.confirm(), new SelectionListener<ButtonEvent>() {  
		       public void componentSelected(ButtonEvent ce) {  
		    	   UserModel um = usergrid.getSelectionModel().getSelectedItem();
		    	   if(um!=null)
		    	   {
		    		   String[] tradeKeys;
		    		   List<BeanModel> lb = grid.getSelectionModel().getSelectedItems();
		    		   if(lb==null||lb.size()<1)
					   {
							Window.alert("请至少选择一个交易。");
							return;
						}else{
							tradeKeys = new String[lb.size()];
							for(int i=0;i<lb.size();i++)
							{
								BeanModel bm = lb.get(i);
								tradeKeys[i] = ((TradeModel)bm.getBean()).getKey();
							}
						}
		    		   service.assignOwner(tradeKeys, um.getKey(), new AsyncCallback<Boolean>() {
                           public void onFailure(Throwable caught) {
                        	   Dispatcher.forwardEvent(Events.OnError, caught);
						   }
                           public void onSuccess(Boolean result) {
							     if(result)
							     {
							    	 Window.alert("分配成功。"); 
							    	 simple.hide(); 
							    	 loader.load();
							     }else{
							    	 Window.alert("分配失败。"); 
							     }
						    }
					});
		    	   }else{
		    		   Window.alert("请选择一个用户。");
		    	   }
		    	}  
		     }));
		   simple.getButtonBar().add(new Button(msg.cancel(), new SelectionListener<ButtonEvent>() {  
		          public void componentSelected(ButtonEvent ce) {  
		        	  simple.hide(); 
			        }  
			      }));  
		   return simple;
	}
	
	private Grid<UserModel> createGrid() {
		final Grid<UserModel> usergrid;
		// 用户列表store
		ListStore<UserModel> store;

		//翻页工具条
		PagingToolBar pagingBar;
		geneService = (GeneralServiceAsync) Registry.get(General.BASESERVICE);
		// 分页读取用户列表
		RpcProxy<PagingLoadResult<UserModel>> proxy = new RpcProxy<PagingLoadResult<UserModel>>() {
			@Override
			protected void load(Object loadConfig,
					AsyncCallback<PagingLoadResult<UserModel>> callback) {
				geneService.getUsers((PagingLoadConfig) loadConfig, callback);
			}
		};
		final BasePagingLoader<PagingLoadResult<UserModel>> loader = new BasePagingLoader<PagingLoadResult<UserModel>>(
				proxy);
		pagingBar = new PagingToolBar(20);
		pagingBar.bind(loader);
		
		List<ColumnConfig> configs = new ArrayList<ColumnConfig>();

		CheckBoxSelectionModel<UserModel> sm = new CheckBoxSelectionModel<UserModel>(); 
		configs.add(sm.getColumn());  
		
		ColumnConfig column = new ColumnConfig("username", msg.username(), 160);
		configs.add(column);

		column = new ColumnConfig("realName", msg.realName(), 100);
		configs.add(column);

		column = new ColumnConfig("title", msg.usertitle(), 100);
		configs.add(column);

		column = new ColumnConfig("phoneMobile", msg.phoneMobile(), 100);
		configs.add(column);

		column = new ColumnConfig("messengerId", msg.messengerId(), 90);
		configs.add(column);

		store = new ListStore<UserModel>(loader);
		
		ColumnModel cm = new ColumnModel(configs);

		usergrid = new Grid<UserModel>(store, cm);
		usergrid.getView().setEmptyText(msg.gridEmptyTxt());
		usergrid.addPlugin(sm);
		usergrid.setSelectionModel(sm);
		usergrid.setBorders(false);
		usergrid.setAutoExpandColumn("username");
		usergrid.setBorders(true);
		// 添加翻页工具条
		usergrid.addListener(Events.Attach, new Listener<GridEvent<Resource>>() {
			public void handleEvent(GridEvent<Resource> be) {
				PagingLoadConfig config = new BasePagingLoadConfig();
                config.setOffset(0);
				config.setLimit(20);

				Map<String, Object> state = usergrid.getState();
				if (state.containsKey("offset")) {
					int offset = (Integer) state.get("offset");
					int limit = (Integer) state.get("limit");
					config.setOffset(offset);
					config.setLimit(limit);
				}
				if (state.containsKey("sortField")) {
					config.setSortField((String) state.get("sortField"));
					config.setSortDir(SortDir.valueOf((String) state
							.get("sortDir")));
				}
				loader.load(config);
			}
		});
		usergrid.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
		return usergrid;
	}

}
