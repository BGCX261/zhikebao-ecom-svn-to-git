/*
 * Ext GWT - Ext for GWT
 * Copyright(c) 2007-2009, Ext JS, LLC.
 * licensing@extjs.com
 * 
 * http://extjs.com/license
 */
package com.xyz.stock.client.view;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.extjs.gxt.ui.client.Registry;
import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.Style.SortDir;
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
import com.xyz.stock.client.ProductServiceAsync;
import com.xyz.stock.client.Stock;
import com.xyz.stock.client.i18n.StockMessages;
import com.xyz.stock.client.model.ItemModel;

public class OnsaleItemView extends LayoutContainer {

	private  ProductServiceAsync service;
	private ContentPanel panel;
	private Grid<BeanModel> grid;
	private ListStore<BeanModel> store;
	private BasePagingLoader<PagingLoadResult<ItemModel>> loader;
	private GridCellRenderer<BeanModel> gridNumber;
	private GridCellRenderer<BeanModel> statusRen;
	// 查询表单绑定对象
	private ItemModel tm;//

	private ItemForm itemForm;
	private StockMessages msg;

	public OnsaleItemView() {
		service = (ProductServiceAsync) Registry.get(Stock.PRODSERVICE);
		final NumberFormat currency = NumberFormat.getCurrencyFormat();
		final NumberFormat number = NumberFormat.getFormat("0.00");
		final NumberCellRenderer<Grid<BeanModel>> numberRenderer = new NumberCellRenderer<Grid<BeanModel>>(
				currency);
		msg = (StockMessages) Registry.get(Stock.MESSAGE);

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
		RpcProxy<PagingLoadResult<ItemModel>> proxy = new RpcProxy<PagingLoadResult<ItemModel>>() {
			@Override
			protected void load(Object loadConfig,
					AsyncCallback<PagingLoadResult<ItemModel>> callback) {
				service.getOnsaleItems((PagingLoadConfig) loadConfig, tm, callback);
			}
		};
		loader = new BasePagingLoader<PagingLoadResult<ItemModel>>(proxy,
				new BeanModelReader());

		final PagingToolBar pagingBar = new PagingToolBar(16);
		pagingBar.bind(loader);

		List<ColumnConfig> configs = new ArrayList<ColumnConfig>();
		StringBuilder sb = new StringBuilder();
		sb.append("<table>");
		
		sb.append("</table>");
		XTemplate tpl = XTemplate.create(sb.toString());

		RowExpander expander = new RowExpander();
		expander.setTemplate(tpl);
		configs.add(expander);

		ColumnConfig column = new ColumnConfig("approveStatus", "状态", 120);
		configs.add(column);

		column = new ColumnConfig("title","宝贝标题 ", 100);
		configs.add(column);

		column = new ColumnConfig("price", "价格", 100);
		configs.add(column);

		column = new ColumnConfig("num", "数量", 100);
		configs.add(column);

		column = new ColumnConfig("dateModified", "修改时间", 200);
		column.setAlignment(HorizontalAlignment.RIGHT);
		column.setDateTimeFormat(DateTimeFormat.getShortDateFormat());
		configs.add(column);

		column = new ColumnConfig("delistTime", "下架时间", 200);
		column.setAlignment(HorizontalAlignment.RIGHT);
		column.setDateTimeFormat(DateTimeFormat.getMediumDateTimeFormat());
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
		cp.setHeading(msg.itemList());
		ToolBar toolBar = new ToolBar();
		toolBar.setAlignment(HorizontalAlignment.RIGHT);
		Button ctBtn = new Button(msg.create(), Stock.ICONS.add());
		ctBtn.addSelectionListener(new SelectionListener<ButtonEvent>() {
			@Override
			public void componentSelected(ButtonEvent ce) {
				ItemModel itemModel = new ItemModel();
				BeanModel bean = BeanModelLookup.get().getFactory(
						ItemModel.class).createModel(itemModel);
				itemForm = new ItemForm(service, store);
				itemForm.setModel(bean);
				itemForm.show();
			}
		});
		toolBar.add(ctBtn);
		toolBar.add(new SeparatorToolItem());
		toolBar.add(new Button(msg.delete(), Stock.ICONS.delete()));
		toolBar.add(new SeparatorToolItem());
		toolBar.add(new Button(msg.config(), Stock.ICONS.plugin()));
		cp.setTopComponent(toolBar);
		cp.setBottomComponent(pagingBar);

		cp.setIcon(Stock.ICONS.table());
		cp.setAnimCollapse(false);
		cp.setCollapsible(true);

		grid = new Grid<BeanModel>(store, cm);
		// 添加翻页工具条
		grid.addListener(Events.Attach, new Listener<GridEvent<BeanModel>>() {
			public void handleEvent(GridEvent<BeanModel> be) {
				PagingLoadConfig config = new BasePagingLoadConfig();
				config.setOffset(0);
				config.setLimit(16);

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
						itemForm = new ItemForm(service, store);
						itemForm.setModel(be.getModel());
						itemForm.setVisible(true);
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
				Button b = new Button("", Stock.ICONS.edit(),
						new SelectionListener<ButtonEvent>() {
							@Override
							public void componentSelected(ButtonEvent ce) {
								String key = ce.getButton().getData("key");
								/*itemForm = new StockForm(service, store);
								itemForm.setModel(store.findModel(key));
								itemForm.setVisible(true);*/
								BeanModel bm = store.findModel(key);
								ItemModel tm = (ItemModel)bm.getBean();
								//String status = tm.getStatus();
								/*final StockDetail td = new StockDetail(tm);
								
								td.setVisible(true);*/
							}
						});
				b.setData("key", model.get(property));
				b.setToolTip(msg.edit());
				toolBar.add(b);
				Button quitBtn = new Button("", Stock.ICONS.delete());
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
		formpanel.setIcon(Stock.ICONS.form());
		formpanel.setHeading(msg.itemQuery());
		formpanel.setLabelAlign(LabelAlign.LEFT);
		formpanel.setButtonAlign(HorizontalAlignment.CENTER);

		formpanel.setLayout(new ColumnLayout());

		LayoutContainer left = new LayoutContainer();
		left.setStyleAttribute("paddingRight", "10px");
		FormLayout layout = new FormLayout();
		layout.setLabelAlign(LabelAlign.LEFT);
		left.setLayout(layout);

		/*final ComboBox<CusModel> combo = createCusField();
		left.add(combo, formData);*/

		TextField<String> tid = new TextField<String>();
		tid.setFieldLabel("标题");
		tid.setName("title");
		left.add(tid, formData);

		LayoutContainer center = new LayoutContainer();
		center.setStyleAttribute("paddingLeft", "10px");
		layout = new FormLayout();
		layout.setLabelAlign(LabelAlign.LEFT);
		center.setLayout(layout);

		DateField created = new DateField();
		created.setFieldLabel("下架时间");
		created.setName("delistTime");
		center.add(created, formData);
		
		center.add(new Button(msg.query(), Stock.ICONS.search(),
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

		panel.add(formpanel);

		final FormBinding queryFb = new FormBinding(formpanel, true);
		tm = new ItemModel();
		BeanModel bm = BeanModelLookup.get().getFactory(ItemModel.class)
				.createModel(tm);
		queryFb.bind(bm);
		/*FieldBinding nickBinding = new FieldBinding(combo, "buyerNick") {
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
		queryFb.addFieldBinding(ownerBinding);*/
	}

	/*private ComboBox<CusModel> createCusField() {
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
	}*/
}
