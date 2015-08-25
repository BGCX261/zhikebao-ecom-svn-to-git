/*
 * Ext GWT - Ext for GWT
 * Copyright(c) 2007-2009, Ext JS, LLC.
 * licensing@extjs.com
 * 
 * http://extjs.com/license
 */
package com.xyz.system.client.view;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.extjs.gxt.ui.client.Registry;
import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.Style.Orientation;
import com.extjs.gxt.ui.client.Style.SelectionMode;
import com.extjs.gxt.ui.client.Style.SortDir;
import com.extjs.gxt.ui.client.binding.FormBinding;
import com.extjs.gxt.ui.client.data.BaseListLoader;
import com.extjs.gxt.ui.client.data.BasePagingLoadConfig;
import com.extjs.gxt.ui.client.data.BasePagingLoader;
import com.extjs.gxt.ui.client.data.BeanModel;
import com.extjs.gxt.ui.client.data.BeanModelLookup;
import com.extjs.gxt.ui.client.data.BeanModelReader;
import com.extjs.gxt.ui.client.data.ListLoadResult;
import com.extjs.gxt.ui.client.data.ListLoader;
import com.extjs.gxt.ui.client.data.ModelKeyProvider;
import com.extjs.gxt.ui.client.data.PagingLoadConfig;
import com.extjs.gxt.ui.client.data.PagingLoadResult;
import com.extjs.gxt.ui.client.data.RpcProxy;
import com.extjs.gxt.ui.client.event.BaseEvent;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.GridEvent;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.MessageBoxEvent;
import com.extjs.gxt.ui.client.event.SelectionChangedEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.mvc.Dispatcher;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.store.Store;
import com.extjs.gxt.ui.client.util.Format;
import com.extjs.gxt.ui.client.widget.CheckBoxListView;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.Info;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.MessageBox;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.CheckBox;
import com.extjs.gxt.ui.client.widget.form.Field;
import com.extjs.gxt.ui.client.widget.form.FieldSet;
import com.extjs.gxt.ui.client.widget.form.FormPanel;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.extjs.gxt.ui.client.widget.form.Validator;
import com.extjs.gxt.ui.client.widget.grid.CellEditor;
import com.extjs.gxt.ui.client.widget.grid.CheckColumnConfig;
import com.extjs.gxt.ui.client.widget.grid.ColumnConfig;
import com.extjs.gxt.ui.client.widget.grid.ColumnData;
import com.extjs.gxt.ui.client.widget.grid.ColumnModel;
import com.extjs.gxt.ui.client.widget.grid.Grid;
import com.extjs.gxt.ui.client.widget.grid.GridCellRenderer;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.extjs.gxt.ui.client.widget.layout.FormData;
import com.extjs.gxt.ui.client.widget.layout.FormLayout;
import com.extjs.gxt.ui.client.widget.layout.RowData;
import com.extjs.gxt.ui.client.widget.layout.RowLayout;
import com.extjs.gxt.ui.client.widget.table.NumberCellRenderer;
import com.extjs.gxt.ui.client.widget.toolbar.PagingToolBar;
import com.extjs.gxt.ui.client.widget.toolbar.SeparatorToolItem;
import com.extjs.gxt.ui.client.widget.toolbar.ToolBar;
import com.google.gwt.i18n.client.NumberFormat;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.xyz.system.client.General;
import com.xyz.system.client.GeneralServiceAsync;
import com.xyz.system.client.i18n.BaseMessages;
import com.xyz.system.model.Authority;
import com.xyz.system.model.Resource;
import com.xyz.system.model.User;

public class UserView extends LayoutContainer {

	private FormBinding formBindings;

	private BaseMessages msg;

	private GeneralServiceAsync service;
	
	private TextField<String> name;
	
	//private String errmsg;
	private TextField<String> pwd;
	
	private Grid<BeanModel> usergrid;
	//用户信息表单
	private FormPanel panel ;
	// 用户列表store
	private ListStore<BeanModel> store;

	// 用户岗位复选框
	private CheckBoxListView<BeanModel> view;
	//翻页工具条
	private PagingToolBar pagingBar;
	
	//当前User
	private User user;

	@SuppressWarnings("unchecked")
	@Override
	protected void onRender(Element parent, int index) {
		super.onRender(parent, index);
		msg = (BaseMessages) Registry.get(General.MESSAGE);
		service = (GeneralServiceAsync) Registry.get(General.BASESERVICE);
		setLayout(new FitLayout());

		ContentPanel cp = new ContentPanel();
		cp.setFrame(true);
		cp.setHeaderVisible(false);
		cp.setLayout(new RowLayout(Orientation.HORIZONTAL));

		ContentPanel leftcp = new ContentPanel();
		leftcp.setHeaderVisible(false);
		leftcp.setLayout(new FitLayout());
		createGrid();
		leftcp.add(usergrid);
		leftcp.setTopComponent(createToolbar());
		leftcp.setBottomComponent(pagingBar);
		cp.add(leftcp, new RowData(.6, 1));

		panel = createForm();
		formBindings = new FormBinding(panel, true);
		formBindings.setStore((Store) usergrid.getStore());
		formBindings.addListener(Events.Bind, new Listener<BaseEvent>() {
			@Override
			public void handleEvent(BaseEvent be) {
				pwd.setValue("");
				pwd.setAllowBlank(true);
			}
		});
		cp.add(panel, new RowData(.4, 1));
		add(cp);
	}

	private FormPanel createForm() {
		FormData formData = new FormData("95%");
		FormPanel panel = new FormPanel();
		panel.setHeaderVisible(false);
		panel.setButtonAlign(HorizontalAlignment.CENTER);
		//panel.addText("点击\"创建\"按钮添加新用户");
		FieldSet fieldSet = new FieldSet();
		fieldSet.setHeading(msg.userinfo());
		// fieldSet.setCollapsible(true);

		FormLayout layout = new FormLayout();
		layout.setLabelWidth(75);
		fieldSet.setLayout(layout);

		name = new TextField<String>();
		name.setName("username");
		name.setFieldLabel(msg.username());
		panel.add(name);
	
		fieldSet.add(name, formData);
		
		name.addListener(Events.OnFocus, new Listener<BaseEvent>(){
			@Override
			public void handleEvent(BaseEvent be) {
				user = new User();
				BeanModel bean = BeanModelLookup.get().getFactory(
						User.class).createModel(user);
				formBindings.bind(bean);
				name.setEnabled(true);
				name.setAllowBlank(false);
			  }
		    }
		);
		
		name.addListener(Events.OnBlur, new Listener<BaseEvent>(){
			@Override
			public void handleEvent(BaseEvent be) {
				String username = name.getValue();
				if (username != null) {
						service.checkUsername(username,new AsyncCallback<Boolean>() {
									public void onFailure(Throwable caught) {
										Dispatcher.forwardEvent(Events.OnError,caught);
									}
	                                public void onSuccess(Boolean result) {
										if (!result)
											name.forceInvalid(msg.userexist());
										else
											name.clearInvalid();
									}
								});			
					}
			       }
				}
		);

		TextField<String> realName = new TextField<String>();
		realName.setName("realName");
		realName.setFieldLabel(msg.realName());
		fieldSet.add(realName, formData);

		TextField<String> mobile = new TextField<String>();
		mobile.setName("phoneMobile");
		mobile.setFieldLabel(msg.phoneMobile());
		fieldSet.add(mobile, formData);

		panel.add(fieldSet);

		fieldSet = new FieldSet();
		fieldSet.setHeading(msg.pwdchange());
		fieldSet.setCollapsible(true);

		layout = new FormLayout();
		layout.setLabelWidth(75);
		fieldSet.setLayout(layout);

		pwd = new TextField<String>();
		pwd.setName("password");
        pwd.setMinLength(6);
		pwd.setMaxLength(16);
		pwd.setPassword(true);
		pwd.setValue("");
		pwd.setFieldLabel(msg.password());
		fieldSet.add(pwd, formData);

		final TextField<String> repwd = new TextField<String>();
		repwd.setPassword(true);
		repwd.setFieldLabel(msg.repassword());
		repwd.setValue("");
		fieldSet.add(repwd, formData);
		
		Validator pwdvailtor = new Validator() {
			public String validate(Field<?> field, String value) {
				String pwdstr = pwd.getValue();
				String repwdstr = repwd.getValue();
				if(pwdstr!= null&&(!pwdstr.equals(repwdstr)))
					return msg.pwderrmsg();
				if(repwdstr!=null&&(!repwdstr.equals(pwdstr)))
					return msg.pwderrmsg();
				return null;
			}
		};
		//pwd.setValidator(pwdvailtor);
		repwd.setValidator(pwdvailtor);

		panel.add(fieldSet);

		fieldSet = new FieldSet();
		fieldSet.setHeading(msg.userauths());
		fieldSet.setCollapsible(true);

		layout = new FormLayout();
		layout.setLabelWidth(75);
		fieldSet.setLayout(layout);
		fieldSet.add(createAuthsList());
		panel.add(fieldSet);
		ToolBar toolBar = new ToolBar();
		toolBar.setAlignment(HorizontalAlignment.LEFT);
		toolBar.add(createSaveBtn());
		toolBar.add(new Button(msg.cancel(),General.ICONS.user_delete(), new SelectionListener<ButtonEvent>() {
		      @Override
		      public void componentSelected(ButtonEvent ce) {
		    	  BeanModel um = usergrid.getSelectionModel().getSelectedItem();
		    	  User user = (User)um.getBean();
		         if(user!=null&&user.getPid()==null)
		        	 store.remove(um);
		    	 store.rejectChanges();
		      }
		    }));
		panel.setTopComponent(toolBar);
        return panel;
	}

	private void createGrid() {

		// 分页读取用户列表
		RpcProxy<PagingLoadResult<User>> proxy = new RpcProxy<PagingLoadResult<User>>() {
			@Override
			protected void load(Object loadConfig,
					AsyncCallback<PagingLoadResult<User>> callback) {
				service.getUsers((PagingLoadConfig) loadConfig, callback);
			}
		};
		final BasePagingLoader<PagingLoadResult<BeanModel>> loader = new BasePagingLoader<PagingLoadResult<BeanModel>>(
				proxy,new BeanModelReader());
		pagingBar = new PagingToolBar(20);
		pagingBar.bind(loader);
		final NumberFormat currency = NumberFormat.getCurrencyFormat();
		final NumberFormat number = NumberFormat.getFormat("0.00");
		final NumberCellRenderer<Grid<BeanModel>> numberRenderer = new NumberCellRenderer<Grid<BeanModel>>(
				currency);

		GridCellRenderer<BeanModel> change = new GridCellRenderer<BeanModel>() {

			public String render(BeanModel model, String property,
					ColumnData config, int rowIndex, int colIndex,
					ListStore<BeanModel> store, Grid<BeanModel> grid) {
				double val = (Double) model.get(property);
				String style = val < 0 ? "red" : "green";
				return "<span style='color:" + style + "'>"
						+ number.format(val) + "</span>";
			}
		};

		GridCellRenderer<BeanModel> gridNumber = new GridCellRenderer<BeanModel>() {
			public String render(BeanModel model, String property,
					ColumnData config, int rowIndex, int colIndex,
					ListStore<BeanModel> store, Grid<BeanModel> grid) {
				return numberRenderer.render(null, property, model
						.get(property));
			}
		};

		List<ColumnConfig> configs = new ArrayList<ColumnConfig>();

		ColumnConfig column = new ColumnConfig("username", msg.username(), 100);
		configs.add(column);

		column = new ColumnConfig("realName", msg.realName(), 100);
		configs.add(column);

		column = new ColumnConfig("title", msg.usertitle(), 100);
		configs.add(column);

		column = new ColumnConfig("phoneMobile", msg.phoneMobile(), 100);
		configs.add(column);

		column = new ColumnConfig("messengerId", msg.messengerId(), 90);
		configs.add(column);
		
		CheckColumnConfig checkColumn = new CheckColumnConfig("deleted", msg.delete(), 55);
		CellEditor checkBoxEditor = new CellEditor(new CheckBox());
		checkColumn.setEditor(checkBoxEditor);
		configs.add(checkColumn);

		store = new ListStore<BeanModel>(loader);
		store.setMonitorChanges(true);

		ColumnModel cm = new ColumnModel(configs);

		usergrid = new Grid<BeanModel>(store, cm);
		usergrid.getView().setEmptyText(msg.gridEmptyTxt());
		usergrid.addPlugin(checkColumn);  
		usergrid.setBorders(false);
		usergrid.setAutoExpandColumn("username");
		usergrid.setBorders(true);
		// 添加翻页工具条
		usergrid.addListener(Events.Attach, new Listener<GridEvent<BeanModel>>() {
			public void handleEvent(GridEvent<BeanModel> be) {
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
		usergrid.getSelectionModel().addListener(Events.SelectionChange,
				new Listener<SelectionChangedEvent<BeanModel>>() {
					public void handleEvent(SelectionChangedEvent<BeanModel> be) {
						if (be.getSelection().size() > 0) {
							BeanModel um = (BeanModel) be.getSelection().get(0);
					        formBindings.bind(um);
							name.setEnabled(false);
							pwd.setValue("");
							user = (User)um.getBean();
							List<Authority> la = user.getZkbAuthorities();
							//view.getChecked();
							view.clearState();
							if (la != null) {
								for (Authority k : la) {
									BeanModel bean = BeanModelLookup.get().getFactory(
											Authority.class).createModel(k);
									BeanModel bm = view.getStore().findModel(bean);
									view.setChecked(bm, true);
								}
							}
						} else {
							formBindings.unbind();
						}
					}
				});
	}

	/**
	 * 岗位选定列表
	 */
	private CheckBoxListView<BeanModel> createAuthsList() {
		RpcProxy<List<Authority>> proxy = new RpcProxy<List<Authority>>() {
			@Override
			protected void load(Object loadConfig,
					AsyncCallback<List<Authority>> callback) {
				service.getEnassignAuths(callback);
			}
		};

		ListLoader<ListLoadResult<Authority>> loader = new BaseListLoader<ListLoadResult<Authority>>(
				proxy,new BeanModelReader());
		ListStore<BeanModel> vstore = new ListStore<BeanModel>(loader);
		loader.load();

		view = new CheckBoxListView<BeanModel>() {
			@Override
			protected BeanModel prepareData(BeanModel model) {
				String s = model.get("displayName");
				model.set("shortName", Format.ellipse(s, 15));
				return model;
			}

		};
		vstore.setKeyProvider(new ModelKeyProvider<BeanModel>() {
			@Override
			public String getKey(BeanModel model) {
				String s = model.get("key");
				return s;
			}
		});
		view.setStore(vstore);
		view.setDisplayProperty("displayName");
		return view;
	}

	private Button createSaveBtn(){
		Button btn = new Button(msg.save(),General.ICONS.save(), new SelectionListener<ButtonEvent>() {  
	  	      @Override  
		      public void componentSelected(ButtonEvent ce) {  
	  	    	if(!panel.isValid())
	  	    	{
	  	    		Window.alert(msg.noticeformerr());
	  	    		return;
	  	    	}
	  	    	/*BeanModel bm = usergrid.getSelectionModel().getSelectedItem();
	  	    	if(bm==null)
	  	    		return ;*/
	  	    	final User um = user;
	  	    	if(um.getPid()==null&&pwd.getValue()==null)
	  	    	{
	  	    		Window.alert(msg.pwdrequired());
	  	    		return ;
	  	    	}
	  	    	List<BeanModel> lb = view.getChecked();
	  	    	List<Authority> ss = new ArrayList<Authority>();
	  	    	if(lb!=null&&lb.size()>0)
	  	    	{
	  	    		for(BeanModel abm : lb)
	  	    		{
	  	    			Authority auth = (Authority)abm.getBean();
	  	    			ss.add(auth);
	  	    		}
	  	    		um.setZkbAuthorities(ss);
	  	    	}
	  	    	/*um.setEnabled(true);
	  	    	um.setAccountNonExpired(true);
	  	    	um.setAccountNonLocked(true);
	  	    	um.setCredentialsNonExpired(true);*/
	  	    	 service.saveUser(um, new AsyncCallback<User>() {
						public void onFailure(Throwable caught) {
							Dispatcher.forwardEvent(Events.OnError, caught);
						}

						public void onSuccess(User result) {
							BeanModel bean = store.findModel("pid", result.getPid());
							if(bean==null)
							{	
								bean = BeanModelLookup.get().getFactory(
										   User.class).createModel(result);
							   store.insert(bean,0);
							}else{
								store.update(bean);
							}
							usergrid.getSelectionModel().select(true,bean);
							store.commitChanges();
							panel.reset();
							name.setEnabled(false);
							Info.display(msg.notice(), msg.success());
						}
					});
	  	      }    
		   }); 
		return btn;
   }
	
	/**
	 * 创建顶部工具条
	 */
	public ToolBar createToolbar()
	{
		ToolBar toolBar = new ToolBar();
		toolBar.setAlignment(HorizontalAlignment.RIGHT);
		Button add = new Button(msg.create(), General.ICONS.add());
		add.addSelectionListener(new SelectionListener<ButtonEvent>() {
			@Override
			public void componentSelected(ButtonEvent ce) {
				formBindings.unbind();
				user = new User();
				BeanModel bean = BeanModelLookup.get().getFactory(
						User.class).createModel(user);
				
				formBindings.bind(bean);
				name.setEnabled(true);
				name.setAllowBlank(false);
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
                                            	User user = (User)r.getBean();
                                            	service.delUser(user.getPid(), new AsyncCallback<Boolean>() {
                        							public void onFailure(Throwable caught) {
                        								Dispatcher.forwardEvent(Events.OnError, caught);
                        							}

                        							public void onSuccess(Boolean result) {
                        								store.remove(r);
                        								
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
		//toolBar.add(createSaveBtn());
		//toolBar.add(new SeparatorToolItem());
		
		
        return toolBar;
	}
}
