package com.xyz.customer.client.mvc;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.extjs.gxt.ui.client.Registry;
import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.Style.SortDir;
import com.extjs.gxt.ui.client.data.BasePagingLoadConfig;
import com.extjs.gxt.ui.client.data.BasePagingLoader;
import com.extjs.gxt.ui.client.data.PagingLoadConfig;
import com.extjs.gxt.ui.client.data.PagingLoadResult;
import com.extjs.gxt.ui.client.data.RpcProxy;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.GridEvent;
import com.extjs.gxt.ui.client.event.IconButtonEvent;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.Header;
import com.extjs.gxt.ui.client.widget.button.ToolButton;
import com.extjs.gxt.ui.client.widget.custom.Portlet;
import com.extjs.gxt.ui.client.widget.grid.ColumnConfig;
import com.extjs.gxt.ui.client.widget.grid.ColumnData;
import com.extjs.gxt.ui.client.widget.grid.ColumnModel;
import com.extjs.gxt.ui.client.widget.grid.Grid;
import com.extjs.gxt.ui.client.widget.grid.GridCellRenderer;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.extjs.gxt.ui.client.widget.table.NumberCellRenderer;
import com.extjs.gxt.ui.client.widget.toolbar.PagingToolBar;
import com.extjs.gxt.ui.client.widget.toolbar.ToolBar;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.i18n.client.NumberFormat;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.xyz.customer.client.CusServiceAsync;
import com.xyz.customer.client.Customer;
import com.xyz.customer.client.i18n.CusMessages;
import com.xyz.resources.client.ZkbConstants;

public class CusPortlet extends Portlet {
	private CusMessages msg;
	
	private final  CusServiceAsync service;
	
	private Header hd;

	public CusPortlet() {
		service = (CusServiceAsync) Registry.get(Customer.CUSSERVICE);
        msg = (CusMessages) Registry.get(Customer.MESSAGE); 

		setHeading(msg.mycustomer());
		configPanel(this);
		setLayout(new FitLayout());
		setTopComponent(createToolbar());
		add(createGrid());
		setHeight(240);
	}

	private Grid<CusModel> createGrid() {
		final NumberFormat currency = NumberFormat.getCurrencyFormat();
	    final NumberCellRenderer<Grid<CusModel>> numberRenderer = new NumberCellRenderer<Grid<CusModel>>(currency);

	    GridCellRenderer<CusModel> statusRen = new GridCellRenderer<CusModel>() {
	        public String render(CusModel model, String property, ColumnData config, int rowIndex, int colIndex,
	            ListStore<CusModel> store, Grid<CusModel> grid) {
	          String val = (String) model.get(property);
	          String sta = ZkbConstants.get().getStatus(val);
	          return sta;
	        }
	      };
	      
		GridCellRenderer<CusModel> gridNumber = new GridCellRenderer<CusModel>() {
			public String render(CusModel model, String property,
					ColumnData config, int rowIndex, int colIndex,
					ListStore<CusModel> store, Grid<CusModel> grid) {
				return numberRenderer.render(null, property, model
						.get(property));
			}
		};

		//读取今日的最新交易
	    RpcProxy<PagingLoadResult<CusModel>> proxy = new RpcProxy<PagingLoadResult<CusModel>>() {
          @Override
          protected void load(Object loadConfig, AsyncCallback<PagingLoadResult<CusModel>> callback) {
        	  service.getCuss((PagingLoadConfig) loadConfig, callback);
          }
	     };
	      
	     final BasePagingLoader<PagingLoadResult<CusModel>> loader = new BasePagingLoader<PagingLoadResult<CusModel>>(proxy);
	     
	     final PagingToolBar pagingBar = new PagingToolBar(8);
	     pagingBar.bind(loader);
	     
		List<ColumnConfig> configs = new ArrayList<ColumnConfig>();

		ColumnConfig column = new ColumnConfig("nick",msg.cusName(),80);
	    configs.add(column);

	    column = new ColumnConfig("sex",msg.sex(),60);
	    column.setRenderer(new GridCellRenderer<CusModel>() {
	        public Object render(CusModel model, String property,
					ColumnData config, int rowIndex, int colIndex,
					ListStore<CusModel> store, Grid<CusModel> grid) {
	        	String str = model.get(property);
	        	if("m".equals(str))
				   return msg.male();
	        	if("f".equals(str))
	        	   return msg.famale();
	        	return "";
			}
		});
	    configs.add(column);
	    
	    column = new ColumnConfig("status",msg.status(),80);
	    configs.add(column);

	    column = new ColumnConfig("amount",msg.amount(),80);
	    column.setAlignment(HorizontalAlignment.RIGHT);
	    column.setRenderer(gridNumber);
	    configs.add(column);

	    column = new ColumnConfig("rank", msg.level(), 80);
	    column.setRenderer(statusRen);
	    configs.add(column);

	    column = new ColumnConfig("dateEntered", msg.creatime(),100);
	    column.setDateTimeFormat(DateTimeFormat.getShortDateFormat());
	    configs.add(column);

		ListStore<CusModel> store = new ListStore<CusModel>(loader);

		ColumnModel cm = new ColumnModel(configs);

		final Grid<CusModel> g = new Grid<CusModel>(store, cm);
		//添加翻页工具条
	    g.addListener(Events.Attach, new Listener<GridEvent<CusModel>>() {
	        public void handleEvent(GridEvent<CusModel> be) {
	          PagingLoadConfig config = new BasePagingLoadConfig();
	          config.setOffset(0);
	          config.setLimit(6);
	          
	          Map<String, Object> state = g.getState();
	          if (state.containsKey("offset")) {
	            int offset = (Integer)state.get("offset");
	            int limit = (Integer)state.get("limit");
	            config.setOffset(offset);
	            config.setLimit(limit);
	          }
	          if (state.containsKey("sortField")) {
	            config.setSortField((String)state.get("sortField"));
	            config.setSortDir(SortDir.valueOf((String)state.get("sortDir")));
	          }
	          loader.load(config);
	        }
	      });
	    g.setLoadMask(true);
	    g.setAutoExpandColumn("nick");
		g.setBorders(true);
		setBottomComponent(pagingBar);
		return g;
	}
	
	private ToolBar createToolbar(){
		ToolBar toolBar = new ToolBar();
	    toolBar.setAlignment(HorizontalAlignment.LEFT);
	    hd = new Header();
	    
	    toolBar.add(hd);
	    return toolBar;
	}

	private void configPanel(final ContentPanel panel) {
		panel.setCollapsible(true);
		panel.setAnimCollapse(false);
		panel.getHeader().addTool(new ToolButton("x-tool-gear"));
		panel.getHeader().addTool(
				new ToolButton("x-tool-close",
						new SelectionListener<IconButtonEvent>() {

							@Override
							public void componentSelected(IconButtonEvent ce) {
								panel.removeFromParent();
							}

						}));
	}

}
