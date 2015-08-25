package com.xyz.system.client.view;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.extjs.gxt.ui.client.Registry;
import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.Style.LayoutRegion;
import com.extjs.gxt.ui.client.Style.SortDir;
import com.extjs.gxt.ui.client.data.BasePagingLoadConfig;
import com.extjs.gxt.ui.client.data.BasePagingLoader;
import com.extjs.gxt.ui.client.data.BeanModel;
import com.extjs.gxt.ui.client.data.BeanModelLookup;
import com.extjs.gxt.ui.client.data.BeanModelReader;
import com.extjs.gxt.ui.client.data.ModelData;
import com.extjs.gxt.ui.client.data.PagingLoadConfig;
import com.extjs.gxt.ui.client.data.PagingLoadResult;
import com.extjs.gxt.ui.client.data.RpcProxy;
import com.extjs.gxt.ui.client.event.BaseEvent;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.GridEvent;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.MessageBoxEvent;
import com.extjs.gxt.ui.client.event.RowEditorEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.mvc.Dispatcher;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.store.Record;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.Info;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.MessageBox;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.CheckBox;
import com.extjs.gxt.ui.client.widget.form.DualListField;
import com.extjs.gxt.ui.client.widget.form.FormPanel;
import com.extjs.gxt.ui.client.widget.form.HiddenField;
import com.extjs.gxt.ui.client.widget.form.ListField;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.extjs.gxt.ui.client.widget.grid.CellEditor;
import com.extjs.gxt.ui.client.widget.grid.CheckColumnConfig;
import com.extjs.gxt.ui.client.widget.grid.ColumnConfig;
import com.extjs.gxt.ui.client.widget.grid.ColumnData;
import com.extjs.gxt.ui.client.widget.grid.ColumnModel;
import com.extjs.gxt.ui.client.widget.grid.Grid;
import com.extjs.gxt.ui.client.widget.grid.GridCellRenderer;
import com.extjs.gxt.ui.client.widget.grid.RowEditor;
import com.extjs.gxt.ui.client.widget.grid.EditorGrid.ClicksToEdit;
import com.extjs.gxt.ui.client.widget.layout.BorderLayout;
import com.extjs.gxt.ui.client.widget.layout.BorderLayoutData;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.extjs.gxt.ui.client.widget.layout.FormData;
import com.extjs.gxt.ui.client.widget.toolbar.PagingToolBar;
import com.extjs.gxt.ui.client.widget.toolbar.SeparatorToolItem;
import com.extjs.gxt.ui.client.widget.toolbar.ToolBar;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.xyz.system.client.General;
import com.xyz.system.client.GeneralServiceAsync;
import com.xyz.system.client.i18n.BaseMessages;
import com.xyz.system.model.Authority;
import com.xyz.system.model.Resource;

public class AuthView  extends LayoutContainer {
	public AuthView() {
	}

	private BaseMessages msg;

	private GeneralServiceAsync service;
	//岗位列表
	private Grid<BeanModel> ga;
	//岗位store
	private ListStore<BeanModel> store ;
	//已选的功能
	private ListStore<BeanModel> astore;
	//可选的功能列表
	private ListStore<BeanModel> rstore ;
	//当前在编辑的岗位
	private HiddenField<String> curAuthId;
	
	//当前在编辑的岗位
	private Authority curAuth;
	
	private DualListField<BeanModel> resLists;
	
	@Override
	protected void onRender(Element parent, int index) {
		super.onRender(parent, index);
		msg = (BaseMessages) Registry.get(General.MESSAGE);
		service = (GeneralServiceAsync) Registry.get(General.BASESERVICE);
		BorderLayout bl = new BorderLayout();
		setLayout(bl);
		setBorders(false);
		createAuthGrid();
		createResBox();
		//初始化可选列表
		getAvaiAuthRes();
	}
	/**
	 * 创建岗位列表
	 */
	private void createAuthGrid()
	{
		// 分页读取系统岗位列表
		RpcProxy<PagingLoadResult<Authority>> proxy = new RpcProxy<PagingLoadResult<Authority>>() {
			@Override
			protected void load(Object loadConfig,AsyncCallback<PagingLoadResult<Authority>> callback) {
				service.getAuthorities((PagingLoadConfig) loadConfig, callback);
			}
		};
		final BasePagingLoader<PagingLoadResult<Authority>> loader = new BasePagingLoader<PagingLoadResult<Authority>>(
				proxy,new BeanModelReader());
		final PagingToolBar pagingBar = new PagingToolBar(20);
		pagingBar.bind(loader);

		List<ColumnConfig> configs = new ArrayList<ColumnConfig>();

		ColumnConfig column = new ColumnConfig("displayName", msg.name(), 80);

		TextField<String> text = new TextField<String>();
		text.setAllowBlank(false);
		column.setEditor(new CellEditor(text));
		configs.add(column);

		column = new ColumnConfig("role", msg.serial(), 80);
		text = new TextField<String>();
		text.setAllowBlank(false);
		column.setEditor(new CellEditor(text));
		configs.add(column);

	    final CheckColumnConfig checkColumn = new CheckColumnConfig("deleted", "选中", 55){
	    	@Override
	    	protected String getCheckState(ModelData model, String property, int rowIndex,
	    		      int colIndex)
	    	{
	           Authority auth = ((BeanModel)model).getBean();
         	   if(auth.getIsEditable())
         	   {   
         		 return "-on";
         	   }else
         	   {
         		  return "-disabled";
         	   }
	        }
	      };
		CellEditor checkBoxEditor = new CellEditor(new CheckBox());
		checkColumn.setEditor(checkBoxEditor);
		/*checkColumn.setRenderer(new GridCellRenderer<BeanModel>() {
               public Object render(BeanModel model, String property,
					ColumnData config, int rowIndex, int colIndex,
					ListStore<BeanModel> store, Grid<BeanModel> grid) {
            	   Authority auth = model.getBean();
            	   if(auth.getIsEditable())
            	   {   
            		 return new CheckBox();
            	   }else
            	   {
            		  return "默认";
            	   }
			}
		});*/
		configs.add(checkColumn);

		store = new ListStore<BeanModel>(loader);
		ColumnModel cm = new ColumnModel(configs);

		ContentPanel cp = new ContentPanel();
		cp.setIcon(General.ICONS.table());
		cp.setHeading("岗位职责定义");
		cp.setFrame(true);
		cp.setSize(600, 300);
		cp.setLayout(new FitLayout());

		final RowEditor<BeanModel> re = new RowEditor<BeanModel>();
		re.setClicksToEdit(ClicksToEdit.TWO);
		re.setEnabled(false);
		re.addListener(Events.AfterEdit, new Listener<RowEditorEvent>() {
			public void handleEvent(RowEditorEvent be) {
				final Record rec = be.getRecord();
				BeanModel bm = (BeanModel)rec.getModel();
				final Authority auth = bm.getBean();
				rec.isEditing();
				service.saveAuthority(auth, new AsyncCallback<Authority>() {
					public void onFailure(Throwable caught) {
						Dispatcher.forwardEvent(Events.OnError, caught);
					}
                    public void onSuccess(Authority result) {
                    	auth.setPid(result.getPid());
                    	BeanModel bean = BeanModelLookup.get().getFactory(
                    			Authority.class).createModel(auth);
                    	store.update(bean);
                    	store.commitChanges();
                    	re.setEnabled(false);
					}
				});
			}
		});
		ga = new Grid<BeanModel>(store, cm);
		// 添加翻页工具条
		ga.addListener(Events.Attach, new Listener<GridEvent<BeanModel>>() {
			public void handleEvent(GridEvent<BeanModel> be) {
				PagingLoadConfig config = new BasePagingLoadConfig();
                config.setOffset(0);
				config.setLimit(20);

				Map<String, Object> state = ga.getState();
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
		ga.setLoadMask(true);
		ga.addListener(Events.RowClick, new Listener<GridEvent<BeanModel>>() {

			@Override
			public void handleEvent(GridEvent<BeanModel> be) {
				//加载本岗位的职责列表
				int i = be.getRowIndex();
				if(i>=0)
				{
					BeanModel res =store.getAt(i);
					curAuth = res.getBean();
					curAuthId.setValue(curAuth.getPid().toString());
				    getAuthRes(curAuth.getPid());
				}
			}
			
		});
		ga.setAutoExpandColumn("displayName");
		ga.setBorders(true);
		ga.addPlugin(checkColumn);
		ga.addPlugin(re);
		cp.add(ga);

		ToolBar toolBar = new ToolBar();
		toolBar.setAlignment(HorizontalAlignment.RIGHT);
		Button add = new Button(msg.create(), General.ICONS.add());
		add.addSelectionListener(new SelectionListener<ButtonEvent>() {
			@Override
			public void componentSelected(ButtonEvent ce) {
				Authority auth = new Authority("","");
				auth.setDeleted(false);
				re.stopEditing(false);
				BeanModel bean = BeanModelLookup.get().getFactory(
            			Authority.class).createModel(auth);
				store.insert(bean, 0);
				re.setEnabled(true);
				re.startEditing(store.indexOf(bean), true);
			}
		});
		toolBar.add(add);
		toolBar.add(new SeparatorToolItem());
		toolBar.add(new Button(msg.delete(), General.ICONS.delete(),
				new SelectionListener<ButtonEvent>() {
					@Override
					public void componentSelected(ButtonEvent ce) {
						List<BeanModel> ls = store.findModels("deleted", true);
						for (final BeanModel r : ls) {
							MessageBox.confirm(msg.notice(), msg.confirmMsg(),
									new Listener<MessageBoxEvent>() {
										public void handleEvent(MessageBoxEvent ce) {
                                            Button btn = ce.getButtonClicked();
                                            if("yes".equals(btn.getItemId()))
                                            {
                                            	Authority auth = (Authority)r.getBean();
                                            	service.delAuthority(auth.getPid(), new AsyncCallback<Boolean>() {
                        							public void onFailure(Throwable caught) {
                        								Dispatcher.forwardEvent(Events.OnError, caught);
                        							}

                        							public void onSuccess(Boolean result) {
                        								store.remove(r);
                        								rstore.removeAll();
                        								astore.removeAll();
                        								Info.display(msg.notice(), msg.success());
                        							}
                        						});
                                            }else{
                                            	store.rejectChanges();
                                            }
									}
							});
						}
					}
				}));
		toolBar.add(new SeparatorToolItem());
		cp.setTopComponent(toolBar);
		cp.setBottomComponent(pagingBar);
		
		BorderLayoutData data = new BorderLayoutData(LayoutRegion.WEST, 240, 200,320);
		add(cp,data);
	}
	/**
	 * 为岗位分配功能
	 */
	private void createResBox()
	{
		 rstore = new ListStore<BeanModel>();
 	   
	    FormPanel panel = new FormPanel();
	    panel.setButtonAlign(HorizontalAlignment.CENTER);

	    panel.addButton(new Button(msg.save(), new SelectionListener<ButtonEvent>() {  
	    	  @Override  
	    	   public void componentSelected(ButtonEvent ce) {  
	    		  List<BeanModel> lbm = astore.getModels();
	    		  List<Resource> lr = new ArrayList<Resource>();
	    		  if(lbm.size()<1)
	    			  return;
	    		  else 
	    		  {
	    			  for(BeanModel bm : lbm)
	    			  {
	    				  lr.add((Resource)bm.getBean());
	    			  } 
	    		  }
	    			  
	    		  Integer aid = new Integer(curAuthId.getValue());
	    		  service.saveResOfAuth(aid,lr, new AsyncCallback<Boolean>() {
						public void onFailure(Throwable caught) {
							Dispatcher.forwardEvent(Events.OnError, caught);
						}

						public void onSuccess(Boolean result) {
							astore.commitChanges();  
							Info.display(msg.notice(), msg.success());
						}
					});
	    		  
	    	   }  
	     }));  
	    panel.setHeading(msg.authandres());
	    panel.setHeaderVisible(true);
	    
	    final DualListField<BeanModel> resLists = new DualListField<BeanModel>(){
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
	    resLists.setHeight(360);
	    resLists.setLabelStyle("font-weight:bold;direction: rtl!important;direction:inherit;writing-mode: tb-rl;");
	    resLists.setFieldLabel(msg.selectres());
	    ListField<BeanModel> from = resLists.getFromList();
	    from.setReadOnly(true);
	    from.setDisplayField("name");
	    from.setStore(rstore);
	   
	    ListField<BeanModel> to = resLists.getToList();
	    to.setDisplayField("name");
	    astore = new ListStore<BeanModel>();
	    to.setStore(astore);
	    panel.addText("请使用鼠标从左边列表拖动到右边，或者选中左边的功能项点击中间的按钮。选择好以后，请点下方保存按钮。");
	    panel.add(resLists, new FormData("98%"));
	    curAuthId = new HiddenField<String>();
	    panel.add(curAuthId);
	    BorderLayoutData data = new BorderLayoutData(LayoutRegion.CENTER, 240);
	    add(panel,data);
	}
	
	/**
	 * 当点击某一个岗位时，在右边显示本岗位的职责列表
	 * @param key
	 */
	private void getAvaiAuthRes()
	{
		// data proxy
	    service.getAvaiAuthRes(new AsyncCallback<List<Resource>>(){
            public void onFailure(Throwable caught) {
           		Dispatcher.forwardEvent(Events.OnError, caught);
			 }

			@Override
			public void onSuccess(List<Resource> ulr) {
				rstore.removeAll();
				if(ulr!=null&&ulr.size()>0)
				{
				   List<BeanModel> lbm = new ArrayList<BeanModel>();
				   for(Resource res : ulr)
				   {
					   BeanModel bean = BeanModelLookup.get().getFactory(
						   Resource.class).createModel(res);
					   lbm.add(bean);
				   }
				   rstore.add(lbm);
				}
			}
	    });
	}

	/**
	 * 当点击某一个岗位时，在右边显示本岗位的职责列表
	 * @param key
	 */
	private void getAuthRes(final Integer key)
	{
		//将选中的功能再放回可选列表
		rstore.add(astore.getModels());
		// data proxy
	    service.getResourcesOfAuth(key,new AsyncCallback<List<Resource>>(){
            public void onFailure(Throwable caught) {
           		Dispatcher.forwardEvent(Events.OnError, caught);
			 }

			@Override
			public void onSuccess(List<Resource> result) {
				astore.removeAll();
				List<Resource> slr = result;
				if(slr!=null&&slr.size()>0)
				{
					   List<BeanModel> lbm = new ArrayList<BeanModel>();
					   for(Resource res : slr)
					   {
						   BeanModel bean = BeanModelLookup.get().getFactory(
							   Resource.class).createModel(res);
						   
						   rstore.remove(rstore.findModel("pid", res.getPid()));
						   lbm.add(bean);
					   }
					astore.add(lbm);
				}
			}
	    });
	}

}
