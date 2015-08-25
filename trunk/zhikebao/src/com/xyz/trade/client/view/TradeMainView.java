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
import com.extjs.gxt.ui.client.Style.Scroll;
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
import com.extjs.gxt.ui.client.event.ComponentEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.GridEvent;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.mvc.Dispatcher;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.VerticalPanel;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.ComboBox;
import com.extjs.gxt.ui.client.widget.form.DateField;
import com.extjs.gxt.ui.client.widget.form.FormPanel;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.extjs.gxt.ui.client.widget.form.ComboBox.TriggerAction;
import com.extjs.gxt.ui.client.widget.form.FormPanel.LabelAlign;
import com.extjs.gxt.ui.client.widget.form.FormPanel.Method;
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
import com.extjs.gxt.ui.client.widget.table.NumberCellRenderer;
import com.extjs.gxt.ui.client.widget.toolbar.FillToolItem;
import com.extjs.gxt.ui.client.widget.toolbar.LabelToolItem;
import com.extjs.gxt.ui.client.widget.toolbar.PagingToolBar;
import com.extjs.gxt.ui.client.widget.toolbar.SeparatorToolItem;
import com.extjs.gxt.ui.client.widget.toolbar.ToolBar;
import com.google.gwt.http.client.Request;
import com.google.gwt.http.client.RequestBuilder;
import com.google.gwt.http.client.RequestCallback;
import com.google.gwt.http.client.RequestException;
import com.google.gwt.http.client.Response;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.i18n.client.NumberFormat;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.xyz.customer.client.CusServiceAsync;
import com.xyz.customer.client.Customer;
import com.xyz.customer.client.mvc.CusModel;
import com.xyz.resources.client.ZkbConstants;
import com.xyz.resources.client.pages.Template;
import com.xyz.resources.model.Status;
import com.xyz.system.client.General;
import com.xyz.system.client.GeneralServiceAsync;
import com.xyz.system.model.User;
import com.xyz.trade.client.Trade;
import com.xyz.trade.client.TradeServiceAsync;
import com.xyz.trade.client.i18n.TradeMessages;
import com.xyz.trade.model.TradeModel;
import com.xyz.util.PropertyFilter;

public class TradeMainView extends LayoutContainer {

	private TradeServiceAsync service;
	private GeneralServiceAsync geneService;
	private CusServiceAsync cusService;
	private VerticalPanel panel;
	
	private ContentPanel gridPanel ;
	private Grid<BeanModel> grid;
	private ListStore<BeanModel> store;
	private BasePagingLoader<PagingLoadResult<TradeModel>> loader;
	private GridCellRenderer<BeanModel> gridNumber;
	private GridCellRenderer<BeanModel> statusRen;
	// 查询表单绑定对象
	private TradeModel tm;//
	//查询表单
	private FormPanel formpanel ;

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

		panel = new VerticalPanel();
		panel.setTableWidth("99%");
		//panel.setLayout(new FitLayout());
		//panel.setHeight(500);
	}

	@Override
	protected void onRender(Element parent, int index) {
		super.onRender(parent, index);
		// 创建查询表单
		createQueryForm();
		createExpander();
		setScrollMode(Scroll.AUTOY);
		//setLayout(new FitLayout());

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

		final PagingToolBar pagingBar = new PagingToolBar(10);
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
		
		column = new ColumnConfig("payTime", msg.lPayTime(), 200);
		column.setAlignment(HorizontalAlignment.RIGHT);
		column.setDateTimeFormat(DateTimeFormat.getMediumDateFormat());
		configs.add(column);

		column = new ColumnConfig("status", msg.lStatus(), 160);
		column.setRenderer(statusRen);
		configs.add(column);

		column = new ColumnConfig("pid", msg.edit(), 80);
		column.setRenderer(createEditIcon());
		configs.add(column);

		store = new ListStore<BeanModel>(loader);
		store.setKeyProvider(new ModelKeyProvider<BeanModel>() {
			public String getKey(BeanModel model) {
				return model.get("pid").toString();
			}
		});

		ColumnModel cm = new ColumnModel(configs);

		gridPanel = new ContentPanel();
		gridPanel.setLayout(new FitLayout());
		gridPanel.setHeaderVisible(false);
		gridPanel.setBorders(false);
		//gridPanel.setHeading(msg.tradeList());
		ToolBar toolBar = new ToolBar();
		toolBar.setAlignment(HorizontalAlignment.LEFT);
		//toolBar.add(Trade.ICONS.form());
		toolBar.add(new LabelToolItem(msg.tradeList()));
		toolBar.add(new FillToolItem());
		Button ctBtn = new Button("导出", Trade.ICONS.exportxls(),
				new SelectionListener<ButtonEvent>() {
			@Override
			public void componentSelected(ButtonEvent ce) {
				 formpanel.setAction("/zhikebao/exporttradexsl");
				 formpanel.setMethod(Method.POST);
				 formpanel.submit();
				 /*RequestBuilder builder = new RequestBuilder(RequestBuilder.POST, "/zhikebao/exporttradexsl");
                 String postData = "";
				    try {
				    	if(tm!=null)
				    	{
				    		if(tm.getBuyerNick()!=null&&tm.getBuyerNick().trim().length()>0)
				    			postData +="buyerNick="+tm.getBuyerNick().trim()+"&";
							if(tm.getCreated()!=null)
								postData +="created="+tm.getCreated()+"&";
							if(tm.getModified()!=null)
								postData +="modified="+tm.getModified()+"&";
							if(tm.getIid()!=null&&tm.getIid().trim().length()>0)
								postData +="tid="+tm.getIid().trim()+"&";
							if(tm.getStatus()!=null&&tm.getStatus().trim().length()>0)
								postData +="status="+tm.getStatus().trim()+"&";
							if(tm.getOwnerId()!=null&&tm.getOwnerId()>0)
								postData +="ownerId="+tm.getOwnerId()+"&";	
				    	}
				      Request response = builder.sendRequest(postData, new RequestCallback() {

				        public void onError(Request request, Throwable exception) {
				          // code omitted for clarity
				        }

				        public void onResponseReceived(Request request, Response response) {
				        	
				        	Window.alert(response.getText());
				        }
				      });
				    } catch (RequestException e) {
				      Window.alert("Failed to send the request: " + e.getMessage());
				    }*/
			}
		});
		toolBar.add(ctBtn);
		toolBar.add(new SeparatorToolItem());
		toolBar.add(new Button(msg.delete(), Trade.ICONS.delete()));
		gridPanel.setTopComponent(toolBar);
		gridPanel.setBottomComponent(pagingBar);

		//gridPanel.setIcon(Trade.ICONS.table());
		//gridPanel.setAnimCollapse(false);
		//gridPanel.setCollapsible(true);
		gridPanel.setFrame(true);
		grid = new Grid<BeanModel>(store, cm);
		// 添加翻页工具条
		grid.addListener(Events.Attach, new Listener<GridEvent<BeanModel>>() {
			public void handleEvent(GridEvent<BeanModel> be) {
				PagingLoadConfig config = new BasePagingLoadConfig();
				config.setOffset(0);
				config.setLimit(10);

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
		//grid.setHeight("60%");
		//grid.getView().setAutoFill(true);  
		gridPanel.add(grid);
		//gridPanel.setAutoHeight(true);
		
		panel.add(gridPanel);
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
				LayoutContainer lc = new LayoutContainer();
				lc.setLayout(new ColumnLayout());
				TradeModel tm = model.getBean();
				if(tm.getIsEditable())
				{
					Button b = new Button("", Trade.ICONS.edit(),
				       new SelectionListener<ButtonEvent>() {
							@Override
							public void componentSelected(ButtonEvent ce) {
								Integer key = ce.getButton().getData("pid");
								BeanModel bm = store.findModel(key.toString());
								TradeModel tm = (TradeModel)bm.getBean();
								String status = tm.getStatus();
								final TradeDetail td = new TradeDetail(tm);
								
								td.setVisible(true);
							}
						});
				   //b.setSize(50, 16);
				   b.setData("pid", model.get(property));
				   b.setToolTip(msg.edit());
				   lc.add(b,new com.extjs.gxt.ui.client.widget.layout.ColumnData(.3));
				}
				if(tm.getIsEditable()&&"WAIT_SELLER_SEND_GOODS".equals(tm.getStatus()))
				{
				   Button quitBtn = new Button("", Trade.ICONS.delete(),new SelectionListener<ButtonEvent>() {
						@Override
						public void componentSelected(ButtonEvent ce) {
							Integer key = ce.getButton().getData("pid");
							BeanModel bm = store.findModel(key.toString());
							TradeModel tm = (TradeModel)bm.getBean();
						}
					});
				   quitBtn.setData("pid", model.get(property));
                   quitBtn.setToolTip("当商家未发货,买家申请退款时,可关闭交易");
				   lc.add(quitBtn,new com.extjs.gxt.ui.client.widget.layout.ColumnData(.3));
				}
				
				return lc;
			}
		};
		return buttonRenderer;
	}

	/**
	 * 创建查询表单
	 */
	private void createQueryForm() {
		FormData formData = new FormData("95%");
		formpanel = new FormPanel();

		formpanel.setBorders(true);
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
		tStatus.setEditable(false);
		tStatus.setDisplayField("name");
		tStatus.setValueField("code");
		center.add(tStatus, formData);

		LayoutContainer right = new LayoutContainer();
		right.setStyleAttribute("paddingLeft", "10px");
		layout = new FormLayout();
		layout.setLabelAlign(LabelAlign.LEFT);
		right.setLayout(layout);

		DateField createdEnd = new DateField();
		createdEnd.setFieldLabel(msg.fromTo());
		createdEnd.setName("modified");
		right.add(createdEnd, formData);

		final ListStore<BeanModel> users = new ListStore<BeanModel>();
		geneService = (GeneralServiceAsync) Registry.get(General.BASESERVICE);
		geneService.getUsers(new AsyncCallback<List<User>>() {
			@Override
			public void onFailure(Throwable caught) {
				Dispatcher.forwardEvent(Events.OnError, caught);
			}
			@Override
			public void onSuccess(List<User> result) {
				List<BeanModel> lbm = new ArrayList<BeanModel>();
				if(result!=null)
				{
					for(User u : result)
					{
						BeanModel bean = BeanModelLookup.get().getFactory(
							   User.class).createModel(u);
						lbm.add(bean);
					}
				}
				users.add(lbm);
			}
		});

		final ComboBox<BeanModel> owner = new ComboBox<BeanModel>();
		owner.setName("ownerId");
		owner.setFieldLabel(msg.owner());
		owner.setStore(users);
		owner.setDisplayField("username");
		owner.setValueField("pid");
		owner.setTriggerAction(TriggerAction.ALL);
		owner.setTypeAhead(true);
		right.add(owner, formData);
		
		LayoutContainer btnLc = new LayoutContainer();
		btnLc.setStyleAttribute("paddingLeft", "10px");
		layout = new FormLayout();
		btnLc.setLayout(layout);
		btnLc.add(new Button(msg.query(), Trade.ICONS.search(),
				new SelectionListener<ButtonEvent>() {
			@Override
			public void componentSelected(ButtonEvent ce) {
				// 触发表单翻页事件
				grid.fireEvent(Events.Attach);
			}
		}));
		formpanel.add(left,
				new com.extjs.gxt.ui.client.widget.layout.ColumnData(.3));
		formpanel.add(center,
				new com.extjs.gxt.ui.client.widget.layout.ColumnData(.3));
		formpanel.add(right,
				new com.extjs.gxt.ui.client.widget.layout.ColumnData(.3));
		formpanel.add(btnLc,
				new com.extjs.gxt.ui.client.widget.layout.ColumnData(.1));

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
		    	  {
		    		  BeanModel bm =  owner.getValue();
		    		  User user = (User)bm.getBean();
		              return user.getPid();
		    	  }else
		    		return null;
		      }
		};
		queryFb.addFieldBinding(ownerBinding);
		formpanel.addListener(Events.Collapse, new Listener<ComponentEvent>() {
			public void handleEvent(ComponentEvent ce) {
				//ContentPanel cp = (ContentPanel)ce.getComponent();
				gridPanel.recalculate();
				grid.recalculate();
				gridPanel.setHeight("100%");
				gridPanel.layout(true);
			}
        });
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
