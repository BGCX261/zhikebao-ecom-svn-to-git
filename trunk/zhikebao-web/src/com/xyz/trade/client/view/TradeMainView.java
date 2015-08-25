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
import com.extjs.gxt.ui.client.Style.SortDir;
import com.extjs.gxt.ui.client.binding.Converter;
import com.extjs.gxt.ui.client.binding.FieldBinding;
import com.extjs.gxt.ui.client.binding.FormBinding;
import com.extjs.gxt.ui.client.core.XTemplate;
import com.extjs.gxt.ui.client.data.BasePagingLoadConfig;
import com.extjs.gxt.ui.client.data.BasePagingLoader;
import com.extjs.gxt.ui.client.data.BeanModel;
import com.extjs.gxt.ui.client.data.BeanModelLookup;
import com.extjs.gxt.ui.client.data.BeanModelReader;
import com.extjs.gxt.ui.client.data.LoadEvent;
import com.extjs.gxt.ui.client.data.Loader;
import com.extjs.gxt.ui.client.data.ModelKeyProvider;
import com.extjs.gxt.ui.client.data.PagingLoadConfig;
import com.extjs.gxt.ui.client.data.PagingLoadResult;
import com.extjs.gxt.ui.client.data.PagingLoader;
import com.extjs.gxt.ui.client.data.RpcProxy;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.GridEvent;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.mvc.Dispatcher;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.ComboBox;
import com.extjs.gxt.ui.client.widget.form.DateField;
import com.extjs.gxt.ui.client.widget.form.FormPanel;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.extjs.gxt.ui.client.widget.form.ComboBox.TriggerAction;
import com.extjs.gxt.ui.client.widget.form.FormPanel.LabelAlign;
import com.extjs.gxt.ui.client.widget.grid.ColumnConfig;
import com.extjs.gxt.ui.client.widget.grid.ColumnData;
import com.extjs.gxt.ui.client.widget.grid.ColumnModel;
import com.extjs.gxt.ui.client.widget.grid.Grid;
import com.extjs.gxt.ui.client.widget.grid.GridCellRenderer;
import com.extjs.gxt.ui.client.widget.grid.RowExpander;
import com.extjs.gxt.ui.client.widget.layout.ColumnLayout;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.extjs.gxt.ui.client.widget.layout.FormData;
import com.extjs.gxt.ui.client.widget.layout.FormLayout;
import com.extjs.gxt.ui.client.widget.layout.RowLayout;
import com.extjs.gxt.ui.client.widget.table.NumberCellRenderer;
import com.extjs.gxt.ui.client.widget.toolbar.PagingToolBar;
import com.extjs.gxt.ui.client.widget.toolbar.SeparatorToolItem;
import com.extjs.gxt.ui.client.widget.toolbar.ToolBar;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.i18n.client.NumberFormat;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.xyz.customer.client.CusServiceAsync;
import com.xyz.customer.client.Customer;
import com.xyz.customer.client.mvc.CusModel;
import com.xyz.resources.client.ZkbConstants;
import com.xyz.resources.client.model.Status;
import com.xyz.resources.client.pages.Template;
import com.xyz.system.client.General;
import com.xyz.system.client.GeneralServiceAsync;
import com.xyz.system.client.mvc.UserModel;
import com.xyz.trade.client.Trade;
import com.xyz.trade.client.TradeServiceAsync;
import com.xyz.trade.client.i18n.TradeMessages;
import com.xyz.trade.client.model.TradeModel;

public class TradeMainView extends LayoutContainer {

	private TradeServiceAsync service;
	private GeneralServiceAsync geneService;
	private CusServiceAsync cusService;
	private ContentPanel panel;
	private Grid<BeanModel> grid;
	private ListStore<BeanModel> store;
	private BasePagingLoader<PagingLoadResult<TradeModel>> loader;
	private GridCellRenderer<BeanModel> gridNumber;
	private GridCellRenderer<BeanModel> statusRen;
	// 查询表单绑定对象
	private TradeModel tm;//

	private TradeForm tradeForm;
	private TradeMessages msg;

	public TradeMainView() {
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
		// 创建查询表单
		createQueryForm();
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

		column = new ColumnConfig("key", msg.edit(), 80);
		column.setRenderer(createEditIcon());
		configs.add(column);

		store = new ListStore<BeanModel>(loader);
		store.setKeyProvider(new ModelKeyProvider<BeanModel>() {
			public String getKey(BeanModel model) {
				return model.get("key");
			}
		});

		ColumnModel cm = new ColumnModel(configs);

		ContentPanel cp = new ContentPanel();
		cp.setLayout(new FitLayout());
		cp.setHeading(msg.tradeList());
		ToolBar toolBar = new ToolBar();
		toolBar.setAlignment(HorizontalAlignment.RIGHT);
		Button ctBtn = new Button(msg.create(), Trade.ICONS.add());
		ctBtn.addSelectionListener(new SelectionListener<ButtonEvent>() {
			@Override
			public void componentSelected(ButtonEvent ce) {
				TradeModel trade = new TradeModel();
				BeanModel bean = BeanModelLookup.get().getFactory(
						TradeModel.class).createModel(trade);
				tradeForm = new TradeForm(service, store);
				tradeForm.setModel(bean);
				tradeForm.show();
			}
		});
		toolBar.add(ctBtn);
		toolBar.add(new SeparatorToolItem());
		toolBar.add(new Button(msg.delete(), Trade.ICONS.delete()));
		toolBar.add(new SeparatorToolItem());
		toolBar.add(new Button(msg.config(), Trade.ICONS.plugin()));
		cp.setTopComponent(toolBar);
		cp.setBottomComponent(pagingBar);

		cp.setIcon(Trade.ICONS.table());
		cp.setAnimCollapse(false);
		cp.setCollapsible(true);

		grid = new Grid<BeanModel>(store, cm);
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

		grid.addPlugin(expander);
		grid.setAutoExpandColumn("title");
		// grid.getView().setAutoFill(true);
		grid.setAutoHeight(true);
		cp.add(grid);

		panel.add(cp);
		grid.addListener(Events.RowDoubleClick,
				new Listener<GridEvent<BeanModel>>() {
					public void handleEvent(GridEvent<BeanModel> be) {
						tradeForm = new TradeForm(service, store);
						tradeForm.setModel(be.getModel());
						tradeForm.setVisible(true);
					}
				});
	}

	private GridCellRenderer<BeanModel> createEditIcon() {
		GridCellRenderer<BeanModel> buttonRenderer = new GridCellRenderer<BeanModel>() {

			public Object render(final BeanModel model, String property,
					ColumnData config, final int rowIndex, final int colIndex,
					final ListStore<BeanModel> store, Grid<BeanModel> grid) {
				ToolBar toolBar = new ToolBar();
				toolBar.setAlignment(HorizontalAlignment.LEFT);
				toolBar.setBorders(false);
				toolBar.setShadow(false);
				Button b = new Button("", Trade.ICONS.edit(),
						new SelectionListener<ButtonEvent>() {
							@Override
							public void componentSelected(ButtonEvent ce) {
								String key = ce.getButton().getData("key");
								/*tradeForm = new TradeForm(service, store);
								tradeForm.setModel(store.findModel(key));
								tradeForm.setVisible(true);*/
								BeanModel bm = store.findModel(key);
								TradeModel tm = (TradeModel)bm.getBean();
								String status = tm.getStatus();
								final TradeDetail td = new TradeDetail(tm);
								
								td.setVisible(true);
							}
						});
				b.setData("key", model.get(property));
				b.setToolTip(msg.edit());
				toolBar.add(b);
				Button quitBtn = new Button("", Trade.ICONS.delete());
				toolBar.add(quitBtn);
				quitBtn.setToolTip("当商家未发货,买家申请退款时,可关闭交易");
				return toolBar;
			}
		};
		return buttonRenderer;
	}

	/**
	 * 创建查询表单
	 */
	private void createQueryForm() {
		FormData formData = new FormData("95%");
		FormPanel formpanel = new FormPanel();

		formpanel.setBorders(false);
		formpanel.setAnimCollapse(true);
		formpanel.setCollapsible(true);
		formpanel.setIcon(Trade.ICONS.form());
		formpanel.setHeading(msg.tradeQuery());
		formpanel.setLabelAlign(LabelAlign.LEFT);
		formpanel.setButtonAlign(HorizontalAlignment.CENTER);

		formpanel.setLayout(new ColumnLayout());

		LayoutContainer left = new LayoutContainer();
		left.setStyleAttribute("paddingRight", "10px");
		FormLayout layout = new FormLayout();
		layout.setLabelAlign(LabelAlign.LEFT);
		left.setLayout(layout);

		final ComboBox<CusModel> combo = createCusField();
		left.add(combo, formData);

		TextField<String> tid = new TextField<String>();
		tid.setFieldLabel(msg.lTid());
		tid.setName("tid");
		left.add(tid, formData);

		LayoutContainer center = new LayoutContainer();
		center.setStyleAttribute("paddingLeft", "10px");
		layout = new FormLayout();
		layout.setLabelAlign(LabelAlign.LEFT);
		center.setLayout(layout);

		DateField created = new DateField();
		created.setFieldLabel(msg.lCreated());
		created.setName("created");
		center.add(created, formData);

		ListStore<Status> statuses = new ListStore<Status>();
		statuses.add(ZkbConstants.get().getStaList());
		final ComboBox<Status> tStatus = new ComboBox<Status>();
		tStatus.setName("status");
		tStatus.setFieldLabel(msg.lStatus());
		tStatus.setStore(statuses);
		tStatus.setTriggerAction(TriggerAction.ALL);
		tStatus.setDisplayField("name");
		tStatus.setValueField("code");
		center.add(tStatus, formData);

		center.add(new Button(msg.query(), Trade.ICONS.search(),
				new SelectionListener<ButtonEvent>() {
					@Override
					public void componentSelected(ButtonEvent ce) {
						// 触发表单翻页事件
						grid.fireEvent(Events.Attach);
					}
				}));

		LayoutContainer right = new LayoutContainer();
		right.setStyleAttribute("paddingLeft", "10px");
		layout = new FormLayout();
		layout.setLabelAlign(LabelAlign.LEFT);
		right.setLayout(layout);

		DateField createdEnd = new DateField();
		createdEnd.setFieldLabel(msg.fromTo());
		createdEnd.setName("modified");
		right.add(createdEnd, formData);

		final ListStore<UserModel> users = new ListStore<UserModel>();
		geneService = (GeneralServiceAsync) Registry.get(General.BASESERVICE);
		geneService.getUsers(new AsyncCallback<List<UserModel>>() {
			@Override
			public void onFailure(Throwable caught) {
				Dispatcher.forwardEvent(Events.OnError, caught);
			}

			@Override
			public void onSuccess(List<UserModel> result) {
				users.add(result);
			}
		});

		final ComboBox<UserModel> owner = new ComboBox<UserModel>();
		owner.setName("ownerId");
		owner.setFieldLabel(msg.owner());
		owner.setStore(users);
		owner.setDisplayField("username");
		owner.setValueField("key");
		owner.setTypeAhead(true);
		right.add(owner, formData);

		formpanel.add(left,
				new com.extjs.gxt.ui.client.widget.layout.ColumnData(.3));
		formpanel.add(center,
				new com.extjs.gxt.ui.client.widget.layout.ColumnData(.3));
		formpanel.add(right,
				new com.extjs.gxt.ui.client.widget.layout.ColumnData(.3));

		panel.add(formpanel);

		final FormBinding queryFb = new FormBinding(formpanel, true);
		tm = new TradeModel();
		BeanModel bm = BeanModelLookup.get().getFactory(TradeModel.class)
				.createModel(tm);
		queryFb.bind(bm);
		FieldBinding nickBinding = new FieldBinding(combo, "buyerNick") {
		      @Override
		      protected Object onConvertFieldValue(Object value) {
		    	  if(combo.getValue()!=null)
		            return combo.getValue().getNick();
		    	  else
		    		 return null;
		      }
		};
		queryFb.addFieldBinding(nickBinding);
		FieldBinding statusBinding = new FieldBinding(tStatus, "status") {
		      @Override
		      protected Object onConvertFieldValue(Object value) {
		    	  if(tStatus.getValue()!=null)
		            return tStatus.getValue().getCode();
		    	  else
		    		return null;
		      }
		};
		queryFb.addFieldBinding(statusBinding);
		FieldBinding ownerBinding = new FieldBinding(owner, "ownerId") {
		      @Override
		      protected Object onConvertFieldValue(Object value) {
		    	  if(owner.getValue()!=null)
		            return owner.getValue().getKey();
		    	  else
		    		return null;
		      }
		};
		queryFb.addFieldBinding(ownerBinding);
	}

	private ComboBox<CusModel> createCusField() {
		cusService = (CusServiceAsync) Registry.get(Customer.CUSSERVICE);
		
		final ComboBox<CusModel> combo = new ComboBox<CusModel>();
		
		// data proxy
	      RpcProxy<PagingLoadResult<CusModel>> cproxy = new RpcProxy<PagingLoadResult<CusModel>>() {
	          @Override
	          protected void load(Object loadConfig, AsyncCallback<PagingLoadResult<CusModel>> callback) {
	        	  cusService.getCuss((PagingLoadConfig) loadConfig, callback);
	          }
	      };

		PagingLoader<PagingLoadResult<CusModel>> cloader = new BasePagingLoader<PagingLoadResult<CusModel>>(cproxy);

		cloader.addListener(Loader.BeforeLoad, new Listener<LoadEvent>() {
			public void handleEvent(LoadEvent be) {
				PagingLoadConfig plc = be.getConfig();
				plc.set("start",plc.get("offset"));
			}
		});

		ListStore<CusModel> cstore = new ListStore<CusModel>(cloader);

		combo.setFieldLabel(msg.lBuyerNick());
		combo.setName("buyerNick");
		combo.setDisplayField("nick");
		combo.setValueField("nick");
		combo.setTypeAhead(true);
		combo.setItemSelector("div.search-item");
		combo.setTemplate(Template.getCustomerTpl());
		combo.setStore(cstore);
		combo.setMinChars(2);
		combo.setHideTrigger(true);
		combo.setPageSize(10);
		return combo;
	}
}
