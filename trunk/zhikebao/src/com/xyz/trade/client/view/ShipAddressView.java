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
import com.extjs.gxt.ui.client.Style.LayoutRegion;
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
import com.extjs.gxt.ui.client.event.RowEditorEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.mvc.Dispatcher;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.store.Record;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.Info;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.Window;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.ComboBox;
import com.extjs.gxt.ui.client.widget.form.DateField;
import com.extjs.gxt.ui.client.widget.form.DualListField;
import com.extjs.gxt.ui.client.widget.form.FormPanel;
import com.extjs.gxt.ui.client.widget.form.ListField;
import com.extjs.gxt.ui.client.widget.form.NumberField;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.extjs.gxt.ui.client.widget.form.ComboBox.TriggerAction;
import com.extjs.gxt.ui.client.widget.form.FormPanel.LabelAlign;
import com.extjs.gxt.ui.client.widget.grid.CellEditor;
import com.extjs.gxt.ui.client.widget.grid.ColumnConfig;
import com.extjs.gxt.ui.client.widget.grid.ColumnData;
import com.extjs.gxt.ui.client.widget.grid.ColumnModel;
import com.extjs.gxt.ui.client.widget.grid.Grid;
import com.extjs.gxt.ui.client.widget.grid.GridCellRenderer;
import com.extjs.gxt.ui.client.widget.grid.RowEditor;
import com.extjs.gxt.ui.client.widget.grid.RowExpander;
import com.extjs.gxt.ui.client.widget.grid.EditorGrid.ClicksToEdit;
import com.extjs.gxt.ui.client.widget.layout.BorderLayoutData;
import com.extjs.gxt.ui.client.widget.layout.ColumnLayout;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.extjs.gxt.ui.client.widget.layout.FormData;
import com.extjs.gxt.ui.client.widget.layout.FormLayout;
import com.extjs.gxt.ui.client.widget.layout.RowLayout;
import com.extjs.gxt.ui.client.widget.table.NumberCellRenderer;
import com.extjs.gxt.ui.client.widget.toolbar.FillToolItem;
import com.extjs.gxt.ui.client.widget.toolbar.LabelToolItem;
import com.extjs.gxt.ui.client.widget.toolbar.PagingToolBar;
import com.extjs.gxt.ui.client.widget.toolbar.SeparatorToolItem;
import com.extjs.gxt.ui.client.widget.toolbar.ToolBar;
import com.google.gwt.i18n.client.NumberFormat;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.xyz.customer.client.CusServiceAsync;
import com.xyz.customer.client.Customer;
import com.xyz.customer.client.mvc.CusModel;
import com.xyz.resources.client.ZkbConstants;
import com.xyz.resources.client.pages.Template;
import com.xyz.resources.client.widget.AreaComboBox;
import com.xyz.resources.client.widget.AreaEditor;
import com.xyz.resources.model.Location;
import com.xyz.resources.model.Status;
import com.xyz.system.client.General;
import com.xyz.system.client.GeneralServiceAsync;
import com.xyz.system.model.User;
import com.xyz.trade.client.Trade;
import com.xyz.trade.client.TradeServiceAsync;
import com.xyz.trade.client.i18n.TradeMessages;
import com.xyz.trade.model.LogisticCompany;
import com.xyz.trade.model.ShipAddress;
import com.xyz.trade.model.TradeModel;

public class ShipAddressView extends LayoutContainer {

	private TradeServiceAsync service;
	private GeneralServiceAsync geneService;
	private CusServiceAsync cusService;
	private ContentPanel panel;
	private Grid<BeanModel> grid;
	private ListStore<BeanModel> store;
	private BasePagingLoader<PagingLoadResult<ShipAddress>> loader;

	// 查询表单绑定对象
	private ShipAddress tm;//
    //当前选中
	private ShipAddress curSa;
	//展开插件
	private RowExpander expander;
	
	private TradeMessages msg;

	public ShipAddressView() {
		service = (TradeServiceAsync) Registry.get(Trade.TRADESERVICE);
		msg = (TradeMessages) Registry.get(Trade.MESSAGE);

		panel = new ContentPanel();
		panel.setHeaderVisible(false);
		panel.setLayout(new RowLayout());
	}

	@Override
	protected void onRender(Element parent, int index) {
		super.onRender(parent, index);
		// 创建查询表单
		//createQueryForm();
		createExpander();
		setLayout(new FitLayout());
		add(panel);
	}

	private void createExpander() {
		// data proxy
		RpcProxy<PagingLoadResult<ShipAddress>> proxy = new RpcProxy<PagingLoadResult<ShipAddress>>() {
			@Override
			protected void load(Object loadConfig,
					AsyncCallback<PagingLoadResult<ShipAddress>> callback) {
				service.getShipAddress((PagingLoadConfig) loadConfig, tm, callback);
			}
		};
		loader = new BasePagingLoader<PagingLoadResult<ShipAddress>>(proxy,
				new BeanModelReader());

		final PagingToolBar pagingBar = new PagingToolBar(10);
		pagingBar.bind(loader);

		List<ColumnConfig> configs = new ArrayList<ColumnConfig>();
		StringBuilder sb = new StringBuilder();
		sb.append("<table>");
		sb.append("<p>").append(msg.sellogistcoms());
		sb.append(":<tpl for=\"ll\">");
		sb.append("<span>{#}. {companyName} </span>");
		sb.append("</tpl></p>");
		sb.append("</table>");

		XTemplate tpl = XTemplate.create(sb.toString());

		expander = new RowExpander();
		expander.setTemplate(tpl);
		expander.setHidden(false);
		configs.add(expander);

		ColumnConfig column = new ColumnConfig("sellerName","姓名", 100);
		TextField<String> text = new TextField<String>();
		text.setAllowBlank(false);
		column.setEditor(new CellEditor(text));
		configs.add(column);

		column = new ColumnConfig("sellerMobile", "手机号", 80);
		text = new TextField<String>();
		text.setMaxLength(11);
		text.setMinLength(11);
		column.setEditor(new CellEditor(text));
		configs.add(column);
		
		column = new ColumnConfig("sellerPhone","电话", 80);
		text = new TextField<String>();
		column.setEditor(new CellEditor(text));
		configs.add(column);
		
		column = new ColumnConfig("location.state","省份", 80);
		AreaComboBox state = new AreaComboBox("省份","location.state");
		//state.getLoader().load();
		AreaEditor ae = new AreaEditor(state);
		column.setEditor(ae);
		configs.add(column);
		
		column = new ColumnConfig("location.city","城市", 80);
		AreaComboBox city = new AreaComboBox("城市","location.city");
		city.setParent(state);
		city.setLazyRender(true);
		city.setForceSelection(true);
		ae = new AreaEditor(city);
		column.setEditor(ae);
		configs.add(column);
		
		column = new ColumnConfig("location.district","地区", 80);
		AreaComboBox district = new AreaComboBox("地区","location.district");
		district.setParent(city);
		ae = new AreaEditor(district);
		column.setEditor(ae);
		configs.add(column);

		column = new ColumnConfig("sellerAreaId","地区编码", 80);
		TextField<String> code = new TextField<String>();
		code.setAllowBlank(false);
		column.setEditor(new CellEditor(code));
		configs.add(column);
		
		column = new ColumnConfig("location.zip", "邮编", 80);
		TextField<String> zip = new TextField<String>();
		zip.setAllowBlank(false);
		column.setEditor(new CellEditor(zip));
		configs.add(column);
		//在选择之后自动填写编码
		state.setCode(code);
		//state.setZip(zip);
		city.setCode(code);
		city.setZip(zip);
		district.setCode(code);
		district.setZip(zip);
		
		column = new ColumnConfig("location.address", "地址", 120);
		text = new TextField<String>();
		text.setAllowBlank(false);
		column.setEditor(new CellEditor(text));
		configs.add(column);

		column = new ColumnConfig("", msg.edit(), 80);
		column.setRenderer(createEditIcon());
		configs.add(column);

		store = new ListStore<BeanModel>(loader);
		store.setKeyProvider(new ModelKeyProvider<BeanModel>() {
			public String getKey(BeanModel model) {
				ShipAddress sa = model.getBean();
				return sa.getPid().toString();
			}
		});

		ColumnModel cm = new ColumnModel(configs);

		ContentPanel cp = new ContentPanel();
		cp.setLayout(new FitLayout());
		cp.setHeaderVisible(false);
		//cp.setHeading(msg.tradeList());
		ToolBar toolBar = new ToolBar();
		toolBar.add(new LabelToolItem("发货地址列表"));
		toolBar.add(new FillToolItem());
		final RowEditor<BeanModel> re = new RowEditor<BeanModel>();
		re.setClicksToEdit(ClicksToEdit.TWO);
		re.addListener(Events.AfterEdit, new Listener<RowEditorEvent>() {
			public void handleEvent(RowEditorEvent be) {
				final Record rec = be.getRecord();
				BeanModel bm = (BeanModel)rec.getModel();
				final ShipAddress sa = (ShipAddress)bm.getBean();
				rec.isEditing();
				service.saveShipAddress(sa, new AsyncCallback<ShipAddress>() {
					public void onFailure(Throwable caught) {
						Dispatcher.forwardEvent(Events.OnError, caught);
					}
                    public void onSuccess(ShipAddress result) {
                    	sa.setPid(result.getPid());
                    	BeanModel bean = BeanModelLookup.get().getFactory(
        						ShipAddress.class).createModel(sa);
                    	store.update(bean);
                    	store.commitChanges();
					}
				});
			}
		});
		Button ctBtn = new Button(msg.create(), Trade.ICONS.add());
		ctBtn.addSelectionListener(new SelectionListener<ButtonEvent>() {
			@Override
			public void componentSelected(ButtonEvent ce) {
				ShipAddress sa = new ShipAddress();
				sa.setLocation(new Location());
				BeanModel bean = BeanModelLookup.get().getFactory(
						ShipAddress.class).createModel(sa);
				re.stopEditing(false);
				store.insert(bean, 0);
				re.startEditing(store.indexOf(bean), true);
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
		cp.setFrame(true);
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
		grid.addPlugin(re);
		grid.setAutoExpandColumn("location.address");
		grid.setAutoHeight(true);
		cp.add(grid);

		panel.add(cp);
	}

	private GridCellRenderer<BeanModel> createEditIcon() {
		GridCellRenderer<BeanModel> buttonRenderer = new GridCellRenderer<BeanModel>() {

			public Object render(final BeanModel model, String property,
					ColumnData config, final int rowIndex, final int colIndex,
					final ListStore<BeanModel> store, Grid<BeanModel> gd) {
				LayoutContainer lc = new LayoutContainer();
				lc.setLayout(new ColumnLayout());
				Button b = new Button("", Trade.ICONS.logisticadd(),
						new SelectionListener<ButtonEvent>() {
							@Override
							public void componentSelected(ButtonEvent ce) {
								Integer key = ce.getButton().getData("pid");
								BeanModel bm = store.findModel("pid",key);
								curSa = bm.getBean();
								grid.getSelectionModel().select(bm, true);
								createLogistWin();
							}
						});
				ShipAddress sa = (ShipAddress)model.getBean();
				b.setData("pid", sa.getPid());
				b.setToolTip("添加常用物流公司");
				lc.add(b,new com.extjs.gxt.ui.client.widget.layout.ColumnData(.4));
				return lc;
			}
		};
		return buttonRenderer;
	}
	
	/**
	 * 选择常用的物流公司
	 */
	private void createLogistWin()
	{
		final Window window = new Window();  
		window.setPosition(500, 150);
		window.setSize(500, 400);
		window.setPlain(true);  
		window.setModal(true);  
		window.setBlinkModal(true);  
		window.setHeading("选择常用物流公司");  
		window.setLayout(new FitLayout());  
		//可选的物流公司列表
		final ListStore<BeanModel> logisstore = new ListStore<BeanModel>();
		//已选的物流公司
		final ListStore<BeanModel> selecttestore= new ListStore<BeanModel>();
		
		// data proxy
	    service.getAllLogistics(curSa.getPid(),new AsyncCallback<Map<String,List<LogisticCompany>>>(){
            public void onFailure(Throwable caught) {
           		Dispatcher.forwardEvent(Events.OnError, caught);
			 }

			@Override
			public void onSuccess(Map<String,List<LogisticCompany>> result) {
				logisstore.removeAll();
				selecttestore.removeAll();
				List<LogisticCompany> ulr = result.get("unselect");
				
				if(ulr!=null&&ulr.size()>0)
				{
				   List<BeanModel> lbm = new ArrayList<BeanModel>();
				   for(LogisticCompany res : ulr)
				   {
					   BeanModel bean = BeanModelLookup.get().getFactory(
							   LogisticCompany.class).createModel(res);
					   lbm.add(bean);
				   }
				   logisstore.add(lbm);
				}
				List<LogisticCompany> slr = result.get("selected");
				if(slr!=null&&slr.size()>0)
				{
					   List<BeanModel> lbm = new ArrayList<BeanModel>();
					   for(LogisticCompany res : slr)
					   {
						   BeanModel bean = BeanModelLookup.get().getFactory(
								   LogisticCompany.class).createModel(res);
						   lbm.add(bean);
					   }
					   selecttestore.add(lbm);
				}
			}
	    });
 	   
	    FormPanel panel = new FormPanel();
	    panel.setButtonAlign(HorizontalAlignment.CENTER);

	    panel.addButton(new Button(msg.save(), new SelectionListener<ButtonEvent>() {  
	    	  @Override  
	    	   public void componentSelected(ButtonEvent ce) {  
	    		  List<BeanModel> lbm = selecttestore.getModels();
	    		  final List<LogisticCompany> ll = new ArrayList<LogisticCompany>();
	    		  List<Integer> logisIds = new ArrayList<Integer>();
	    		  if(lbm.size()<1)
	    			  return;
	    		  else 
	    		  {
	    			  for(BeanModel bm : lbm)
	    			  {
	    				  LogisticCompany lc = (LogisticCompany)bm.getBean();
	    				  ll.add(lc);
	    				  logisIds.add(lc.getPid());
	    			  } 
	    		  }
	    			  
	    		  Integer aid = curSa.getPid();
	    		  service.saveLogisticsOfShipAddress(aid,logisIds, new AsyncCallback<Boolean>() {
						public void onFailure(Throwable caught) {
							Dispatcher.forwardEvent(Events.OnError, caught);
						}

						public void onSuccess(Boolean result) {
							selecttestore.commitChanges();  
							Info.display(msg.notice(), msg.success());
							window.setVisible(false);
							BeanModel bm = grid.getSelectionModel().getSelectedItem();
							curSa.setLl(ll);
							bm = BeanModelLookup.get().getFactory(ShipAddress.class)
							.createModel(curSa);
							store.update(bm);
							expander.fireEvent(Events.Expand);
						}
					});
	    		  
	    	   }  
	     }));  
	    //panel.setHeading("");
	    panel.setHeaderVisible(false);
	    
	    final DualListField<BeanModel> lists = new DualListField<BeanModel>(){
	        protected void onRender(Element target, int index) {
	        	getMessages().setAddAll(msg.addAll());
		        getMessages().setAddSelected(msg.addSelected());
		        getMessages().setRemoveAll(msg.removeAll());
		        getMessages().setRemoveSelected(msg.removeSelected());
	            super.onRender(target, index);
	            setTitle(msg.resourcelist());
	            fromField.setHeight(360);
	            fromField.setWidth(300);
	            toField.setSize(300, 360);
	            toField.setWidth(300);
	            fromField.setFieldLabel(msg.assresource());
	           
	          }
	    };
	    lists.setHeight(360);
	    lists.setLabelStyle("font-weight:bold;direction: rtl!important;direction:inherit;writing-mode: tb-rl;");
	    lists.setFieldLabel("可选物流公司");
	    ListField<BeanModel> from = lists.getFromList();
	    from.setReadOnly(true);
	    from.setDisplayField("companyName");
	    from.setStore(logisstore);
	   
	    ListField<BeanModel> to = lists.getToList();
	    to.setDisplayField("companyName");
	    to.setStore(selecttestore);
	    panel.add(lists, new FormData("98%"));
	    /*curAuth = new HiddenField<String>();
	    panel.add(curAuth);*/
	    BorderLayoutData data = new BorderLayoutData(LayoutRegion.CENTER, 240);
	    window.add(panel,data);
	    window.setVisible(true);
	}

	/**
	 * 创建查询表单
	 */
	private void createQueryForm() {
		FormData formData = new FormData("95%");
		FormPanel formpanel = new FormPanel();

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
		tm = new ShipAddress();
		BeanModel bm = BeanModelLookup.get().getFactory(ShipAddress.class)
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
