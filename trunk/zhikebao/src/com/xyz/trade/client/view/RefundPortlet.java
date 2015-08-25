package com.xyz.trade.client.view;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.extjs.gxt.ui.client.Registry;
import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.Style.SortDir;
import com.extjs.gxt.ui.client.data.BasePagingLoadConfig;
import com.extjs.gxt.ui.client.data.BasePagingLoader;
import com.extjs.gxt.ui.client.data.BeanModel;
import com.extjs.gxt.ui.client.data.BeanModelReader;
import com.extjs.gxt.ui.client.data.PagingLoadConfig;
import com.extjs.gxt.ui.client.data.PagingLoadResult;
import com.extjs.gxt.ui.client.data.RpcProxy;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.GridEvent;
import com.extjs.gxt.ui.client.event.IconButtonEvent;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.mvc.Dispatcher;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.Header;
import com.extjs.gxt.ui.client.widget.button.Button;
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
import com.google.gwt.i18n.client.NumberFormat;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.xyz.resources.client.AppEvents;
import com.xyz.resources.client.ZkbConstants;
import com.xyz.trade.client.Trade;
import com.xyz.trade.client.TradeServiceAsync;
import com.xyz.trade.client.i18n.TradeMessages;
import com.xyz.trade.model.TradeModel;

public class RefundPortlet extends Portlet {
	private TradeMessages msg;
	
	private final  TradeServiceAsync service;
	
	private Header hd;

	public RefundPortlet() {
		service = (TradeServiceAsync) Registry.get(Trade.TRADESERVICE);
        msg = (TradeMessages) Registry.get(Trade.MESSAGE); 
        
		setHeading(msg.refund());
		configPanel(this);
		setLayout(new FitLayout());
		add(createGrid());
	}

	private Grid<BeanModel> createGrid() {
		final NumberFormat currency = NumberFormat.getCurrencyFormat();
	    final NumberCellRenderer<Grid<BeanModel>> numberRenderer = new NumberCellRenderer<Grid<BeanModel>>(currency);

	    GridCellRenderer<BeanModel> statusRen = new GridCellRenderer<BeanModel>() {
	        public String render(BeanModel model, String property, ColumnData config, int rowIndex, int colIndex,
	            ListStore<BeanModel> store, Grid<BeanModel> grid) {
	          String val = (String) model.get(property);
	          String sta = ZkbConstants.get().getStatus(val);
	          return sta;
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

		//读取今日的最新交易
	    RpcProxy<PagingLoadResult<TradeModel>> proxy = new RpcProxy<PagingLoadResult<TradeModel>>() {
          @Override
          protected void load(Object loadConfig, AsyncCallback<PagingLoadResult<TradeModel>> callback) {
        	  TradeModel tm = new TradeModel();//
        	  tm.setStatus("TRADE_CLOSED");
        	  service.getTrades((PagingLoadConfig) loadConfig,tm, callback);
          }
	     };
	      
	     BeanModelReader reader = new BeanModelReader();
	     final BasePagingLoader<PagingLoadResult<BeanModel>> loader = new BasePagingLoader<PagingLoadResult<BeanModel>>(proxy,reader);
	     
	     final PagingToolBar pagingBar = new PagingToolBar(8);
	     pagingBar.bind(loader);
	     
		List<ColumnConfig> configs = new ArrayList<ColumnConfig>();

		ColumnConfig column = new ColumnConfig(msg.dBuyerNick(),msg.lBuyerNick(),80);
	    configs.add(column);
   
	    column = new ColumnConfig("title",msg.lTitle(),80);
	    configs.add(column);

	    column = new ColumnConfig("totalFee","退款时间",80);
	    column.setAlignment(HorizontalAlignment.RIGHT);
	    column.setRenderer(gridNumber);
	    configs.add(column);

		ListStore<BeanModel> store = new ListStore<BeanModel>(loader);

		ColumnModel cm = new ColumnModel(configs);

		final Grid<BeanModel> g = new Grid<BeanModel>(store, cm);
		g.setAutoHeight(true);
		//添加翻页工具条
	    g.addListener(Events.Attach, new Listener<GridEvent<BeanModel>>() {
	        public void handleEvent(GridEvent<BeanModel> be) {
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
	    g.setAutoExpandColumn("title");
		g.setBorders(true);
		setBottomComponent(pagingBar);
		return g;
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
