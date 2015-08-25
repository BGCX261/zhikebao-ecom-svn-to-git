package com.xyz.system.client.view;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.extjs.gxt.ui.client.Registry;
import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
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
import com.extjs.gxt.ui.client.widget.form.NumberField;
import com.extjs.gxt.ui.client.widget.form.SimpleComboBox;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.extjs.gxt.ui.client.widget.form.ComboBox.TriggerAction;
import com.extjs.gxt.ui.client.widget.grid.CellEditor;
import com.extjs.gxt.ui.client.widget.grid.CheckColumnConfig;
import com.extjs.gxt.ui.client.widget.grid.ColumnConfig;
import com.extjs.gxt.ui.client.widget.grid.ColumnModel;
import com.extjs.gxt.ui.client.widget.grid.Grid;
import com.extjs.gxt.ui.client.widget.grid.RowEditor;
import com.extjs.gxt.ui.client.widget.grid.EditorGrid.ClicksToEdit;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.extjs.gxt.ui.client.widget.toolbar.PagingToolBar;
import com.extjs.gxt.ui.client.widget.toolbar.SeparatorToolItem;
import com.extjs.gxt.ui.client.widget.toolbar.ToolBar;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.xyz.system.client.General;
import com.xyz.system.client.GeneralServiceAsync;
import com.xyz.system.client.i18n.BaseMessages;
import com.xyz.system.model.Resource;

public class ResourceView extends LayoutContainer {

	private BaseMessages msg;

	private GeneralServiceAsync service;

	public ResourceView() {

	}

	@Override
	protected void onRender(Element parent, int index) {
		super.onRender(parent, index);
		msg = (BaseMessages) Registry.get(General.MESSAGE);
		service = (GeneralServiceAsync) Registry.get(General.BASESERVICE);
		setLayout(new FitLayout());

		// 分页读取系统功能列表
		RpcProxy<PagingLoadResult<Resource>> proxy = new RpcProxy<PagingLoadResult<Resource>>() {
			@Override
			protected void load(Object loadConfig,
					AsyncCallback<PagingLoadResult<Resource>> callback) {
				service.getResources((PagingLoadConfig) loadConfig, callback);
			}
		};
		final BasePagingLoader<PagingLoadResult<Resource>> loader = new BasePagingLoader<PagingLoadResult<Resource>>(
				proxy,new BeanModelReader());
		final PagingToolBar pagingBar = new PagingToolBar(20);
		pagingBar.bind(loader);

		List<ColumnConfig> configs = new ArrayList<ColumnConfig>();

		ColumnConfig column = new ColumnConfig("name", msg.name(), 140);

		TextField<String> text = new TextField<String>();
		text.setAllowBlank(false);
		column.setEditor(new CellEditor(text));
		configs.add(column);

		column = new ColumnConfig("serial", msg.serial(), 100);
		NumberField nf = new NumberField();
		nf.setPropertyEditorType(Integer.class);
		column.setEditor(new CellEditor(nf));
		configs.add(column);

		column = new ColumnConfig("parentId", msg.parentId(), 100);
		nf = new NumberField();
		nf.setPropertyEditorType(Integer.class);
		column.setEditor(new CellEditor(nf));
		configs.add(column);

		final SimpleComboBox<String> combo = new SimpleComboBox<String>();
		combo.setForceSelection(true);
		combo.setTriggerAction(TriggerAction.ALL);
		combo.add("url");
		combo.add("menu");

		CellEditor editor = new CellEditor(combo) {
			@Override
			public Object preProcessValue(Object value) {
				if (value == null) {
					return value;
				}
				return combo.findModel(value.toString());
			}

			@Override
			public Object postProcessValue(Object value) {
				if (value == null) {
					return value;
				}
				return ((ModelData) value).get("value");
			}
		};

		column = new ColumnConfig("resourceType", msg.resourceType(), 100);
		column.setEditor(editor);
		configs.add(column);

		column = new ColumnConfig("position", msg.inx(), 80);
		nf = new NumberField();
		nf.setPropertyEditorType(Integer.class);
		column.setEditor(new CellEditor(nf));
		configs.add(column);

		column = new ColumnConfig("description", msg.desc(), 140);
		column.setEditor(new CellEditor(new TextField<String>()));
		configs.add(column);

		/*
		 * DateField dateField = new DateField();
		 * dateField.getPropertyEditor().setFormat(
		 * DateTimeFormat.getFormat("MM/dd/y"));
		 * 
		 * column = new ColumnConfig(); column.setId("available");
		 * column.setHeader("Available"); column.setWidth(95);
		 * column.setEditor(new CellEditor(dateField));
		 * column.setDateTimeFormat(DateTimeFormat.getMediumDateFormat());
		 * configs.add(column);
		 */
		column = new ColumnConfig("value", msg.value(), 100);
		text = new TextField<String>();
		column.setEditor(new CellEditor(text));
		configs.add(column);
		
		CheckColumnConfig checkColumn = new CheckColumnConfig("deleted", msg
				.delete(), 55);
		CellEditor checkBoxEditor = new CellEditor(new CheckBox());
		checkColumn.setEditor(checkBoxEditor);
		configs.add(checkColumn);

		final ListStore<BeanModel> store = new ListStore<BeanModel>(loader);
		ColumnModel cm = new ColumnModel(configs);

		ContentPanel cp = new ContentPanel();
		cp.setIcon(General.ICONS.table());
		cp.setHeading(msg.resource());
		cp.setFrame(true);
		cp.setSize(600, 300);
		cp.setLayout(new FitLayout());

		final RowEditor<BeanModel> re = new RowEditor<BeanModel>();
		re.setClicksToEdit(ClicksToEdit.TWO);
		re.addListener(Events.AfterEdit, new Listener<RowEditorEvent>() {
			public void handleEvent(RowEditorEvent be) {
				final Record rec = be.getRecord();
				BeanModel bm = (BeanModel)rec.getModel();
				final Resource res = (Resource)bm.getBean();
				rec.isEditing();
				service.saveResource(res, new AsyncCallback<Resource>() {
					public void onFailure(Throwable caught) {
						Dispatcher.forwardEvent(Events.OnError, caught);
					}
                    public void onSuccess(Resource result) {
                    	res.setPid(result.getPid());
                    	BeanModel bean = BeanModelLookup.get().getFactory(
 							   Resource.class).createModel(res);
                    	store.update(bean);
                    	store.commitChanges();
					}
				});
			}
		});
		final Grid<BeanModel> grid = new Grid<BeanModel>(store, cm);
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

		grid.setAutoExpandColumn("value");
		grid.setBorders(true);
		grid.addPlugin(checkColumn);
		grid.addPlugin(re);
		cp.add(grid);

		ToolBar toolBar = new ToolBar();
		toolBar.setAlignment(HorizontalAlignment.RIGHT);
		Button add = new Button(msg.create(), General.ICONS.add());
		add.addSelectionListener(new SelectionListener<ButtonEvent>() {
			@Override
			public void componentSelected(ButtonEvent ce) {
				Resource res = new Resource();
				res.setName("");
				res.setParentId(0);
				res.setSerial(0);
				res.setValue("");
				res.setDescription("");
				res.setDeleted(false);
				
				re.stopEditing(false);
				BeanModel bean = BeanModelLookup.get().getFactory(
						   Resource.class).createModel(res);
				store.insert(bean, 0);
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
                                            	Resource res = (Resource)r.getBean();
                                            	service.delResource(res.getPid(), new AsyncCallback<Boolean>() {
                        							public void onFailure(Throwable caught) {
                        								Dispatcher.forwardEvent(Events.OnError, caught);
                        							}

                        							public void onSuccess(Boolean result) {
                        								store.remove(r);
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
		add(cp);
	}
}
