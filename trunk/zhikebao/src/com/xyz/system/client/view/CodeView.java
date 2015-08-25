package com.xyz.system.client.view;

import java.util.Arrays;
import java.util.List;

import com.extjs.gxt.ui.client.Registry;
import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.data.BaseTreeLoader;
import com.extjs.gxt.ui.client.data.BeanModel;
import com.extjs.gxt.ui.client.data.BeanModelLookup;
import com.extjs.gxt.ui.client.data.BeanModelReader;
import com.extjs.gxt.ui.client.data.ModelData;
import com.extjs.gxt.ui.client.data.ModelKeyProvider;
import com.extjs.gxt.ui.client.data.RpcProxy;
import com.extjs.gxt.ui.client.data.TreeLoader;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.RowEditorEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.mvc.Dispatcher;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.store.Record;
import com.extjs.gxt.ui.client.store.TreeStore;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.Info;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.MessageBox;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.NumberField;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.extjs.gxt.ui.client.widget.grid.CellEditor;
import com.extjs.gxt.ui.client.widget.grid.ColumnConfig;
import com.extjs.gxt.ui.client.widget.grid.ColumnData;
import com.extjs.gxt.ui.client.widget.grid.ColumnModel;
import com.extjs.gxt.ui.client.widget.grid.Grid;
import com.extjs.gxt.ui.client.widget.grid.GridCellRenderer;
import com.extjs.gxt.ui.client.widget.grid.RowEditor;
import com.extjs.gxt.ui.client.widget.grid.EditorGrid.ClicksToEdit;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.extjs.gxt.ui.client.widget.toolbar.SeparatorToolItem;
import com.extjs.gxt.ui.client.widget.toolbar.ToolBar;
import com.extjs.gxt.ui.client.widget.treegrid.TreeGrid;
import com.extjs.gxt.ui.client.widget.treegrid.TreeGridCellRenderer;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.xyz.resources.model.BaseCode;
import com.xyz.system.client.General;
import com.xyz.system.client.GeneralServiceAsync;
import com.xyz.system.client.i18n.BaseMessages;

public class CodeView extends LayoutContainer {
	public CodeView() {
	}

	private BaseMessages msg;

	private GeneralServiceAsync service;

	private TreeLoader<BeanModel> loader;
	
	private RowEditor<ModelData> editor;

	@Override
	protected void onRender(Element parent, int index) {
		super.onRender(parent, index);
		msg = (BaseMessages) Registry.get(General.MESSAGE);
		service = (GeneralServiceAsync) Registry.get(General.BASESERVICE);

		setLayout(new FitLayout());

		// data proxy
		RpcProxy<List<BaseCode>> proxy = new RpcProxy<List<BaseCode>>() {
			@Override
			protected void load(Object loadConfig,
					AsyncCallback<List<BaseCode>> callback) {
				BaseCode parent = (BaseCode) loadConfig;
				Integer pid = 0;
				if (parent != null)
					pid = parent.getPid();
				service.getCodes(pid, callback);
			}
		};

		// tree loader
		loader = new BaseTreeLoader<BeanModel>(proxy,new BeanModelReader()) {
			@Override
			public boolean hasChildren(BeanModel parent) {
				BaseCode bc = (BaseCode)parent.getBean();
				return bc.isParent();
			}
		};

		final TreeStore<BeanModel> store = new TreeStore<BeanModel>(loader);

		ColumnConfig name = new ColumnConfig("name", msg.name(), 100);
		name.setRenderer(new TreeGridCellRenderer<BeanModel>());
		TextField<String> text = new TextField<String>();
		text.setAllowBlank(false);
		name.setEditor(new CellEditor(text));

		ColumnConfig inxCC = new ColumnConfig("inx", msg.inx(), 100);
		NumberField nf = new NumberField();
		nf.setPropertyEditorType(Integer.class);
		inxCC.setEditor(new CellEditor(nf));

		ColumnConfig hcCC = new ColumnConfig("hasChild", msg.hasChiled(), 100);
		hcCC.setRenderer(new GridCellRenderer<ModelData>() {
			@Override
			public Object render(ModelData model, String property,
					ColumnData config, int rowIndex, int colIndex,
					ListStore<ModelData> store, Grid<ModelData> grid) {
				boolean val = model.get(property);
				if (val)
					return "是";
				else
					return "";
			}

		});

		ColumnModel cm = new ColumnModel(Arrays.asList(name, inxCC, hcCC));

		ContentPanel cp = new ContentPanel();
		cp.setBodyBorder(false);
		cp.setHeading(msg.baseCode());
		cp.setButtonAlign(HorizontalAlignment.CENTER);
		cp.setLayout(new FitLayout());
		cp.setFrame(true);
		cp.setSize(600, 300);
		store.setKeyProvider(new ModelKeyProvider<BeanModel>() {
			
			@Override
			public String getKey(BeanModel model) {
				BaseCode bc = (BaseCode)model.getBean();
				// TODO Auto-generated method stub
				return bc.getPid().toString();
			}
		});
		final TreeGrid<BeanModel> tree = new TreeGrid<BeanModel>(store, cm);
		tree.setBorders(true);
		tree.getStyle().setLeafIcon(General.ICONS.item());
		tree.setSize(400, 400);
		tree.setAutoExpandColumn("name");
		tree.setTrackMouseOver(false);
		/**
		 * 工具按钮
		 */
		ToolBar toolBar = new ToolBar();
		toolBar.setAlignment(HorizontalAlignment.RIGHT);
		Button ctBtn = new Button(msg.create(), General.ICONS.add());
		ctBtn.addSelectionListener(new SelectionListener<ButtonEvent>() {
			@Override
			public void componentSelected(ButtonEvent ce) {
				final BeanModel bm = (BeanModel) tree.getSelectionModel().getSelectedItem();
				final BaseCode bc = (BaseCode)bm.getBean();
				int i = 0;
				if (bc != null) {
					i = bc.getChildren().size();
				}
				final BaseCode child = new BaseCode();
				child.setParent(bc);
				service.saveCode(child, new AsyncCallback<BaseCode>() {
					public void onFailure(Throwable caught) {
						Dispatcher.forwardEvent(Events.OnError, caught);
					}

					public void onSuccess(BaseCode code) {
						if(code.getParent()!=null)
						{
							bc.setParent(true);
							BeanModel bean = BeanModelLookup.get().getFactory(
	                    			BaseCode.class).createModel(code);
							if(!tree.isExpanded(bean))
								  tree.setExpanded(bean, true);
							store.add(bm,bean,false);
						}
						else
						{
							BeanModel bean = BeanModelLookup.get().getFactory(
	                    			BaseCode.class).createModel(child);
							store.insert(bean, 0, false);
						    editor.startEditing(0, true);
						}
					}
				});
			}
		});

		toolBar.add(ctBtn);
		toolBar.add(new SeparatorToolItem());

		// 删除
		Button delBtn = new Button(msg.delete(), General.ICONS.delete());
		delBtn.addSelectionListener(new SelectionListener<ButtonEvent>() {
			@Override
			public void componentSelected(ButtonEvent ce) {
				final BeanModel bm = (BeanModel) tree.getSelectionModel().getSelectedItem();
				if (bm != null) {
					final BaseCode bc = (BaseCode)bm.getBean();
					if (bc.isParent()) {
                        MessageBox.alert(msg.alert(), msg.cantDelete(), null);
					} else if(bc.getPid()==null||bc.getPid()<1)
					{
						store.remove(bm);
						store.rejectChanges();
					}else {
						service.delCode(bc, new AsyncCallback<Boolean>() {
							public void onFailure(Throwable caught) {
								Dispatcher.forwardEvent(Events.OnError, caught);
							}

							public void onSuccess(Boolean result) {
								store.remove(bm);
								BaseCode par = (BaseCode)bc.getParent();
                                BeanModel bean = BeanModelLookup.get().getFactory(
		                    			BaseCode.class).createModel(par);
								/*if(par!=null&&par.isLeaf())
									par.setIsParent(false);*/
								Info.display(msg.notice(), msg.success());
							}
						});
					}
				}
			}
		});
		toolBar.add(delBtn);
		toolBar.add(new SeparatorToolItem());
		toolBar.add(new Button(msg.config(), General.ICONS.plugin()));
		cp.setTopComponent(toolBar);

		editor = new RowEditor<ModelData>();
		editor.setClicksToEdit(ClicksToEdit.TWO);
		editor.addListener(Events.AfterEdit, new Listener<RowEditorEvent>() {
			public void handleEvent(RowEditorEvent be) {
				final Record rec = be.getRecord();
				Integer inx = (Integer)rec.get("inx");
				BeanModel bm = (BeanModel)rec.getModel();
				//code.set("inx", inx);
				rec.isEditing();
				service.saveCode((BaseCode)bm.getBean(), new AsyncCallback<BaseCode>() {
					public void onFailure(Throwable caught) {
						Dispatcher.forwardEvent(Events.OnError, caught);
					}

					public void onSuccess(BaseCode result) {
						store.commitChanges();
					}
				});
			}
		});
		tree.addPlugin(editor);

		cp.add(tree);

		add(cp);

	}

}
