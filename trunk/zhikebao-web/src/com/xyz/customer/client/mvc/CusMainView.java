/*
 * Ext GWT - Ext for GWT
 * Copyright(c) 2007-2009, Ext JS, LLC.
 * licensing@extjs.com
 * 
 * http://extjs.com/license
 */
package com.xyz.customer.client.mvc;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.extjs.gxt.ui.client.Registry;
import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.Style.SortDir;
import com.extjs.gxt.ui.client.core.XTemplate;
import com.extjs.gxt.ui.client.data.BasePagingLoadConfig;
import com.extjs.gxt.ui.client.data.BasePagingLoader;
import com.extjs.gxt.ui.client.data.PagingLoadConfig;
import com.extjs.gxt.ui.client.data.PagingLoadResult;
import com.extjs.gxt.ui.client.data.RpcProxy;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.GridEvent;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.grid.ColumnConfig;
import com.extjs.gxt.ui.client.widget.grid.ColumnData;
import com.extjs.gxt.ui.client.widget.grid.ColumnModel;
import com.extjs.gxt.ui.client.widget.grid.Grid;
import com.extjs.gxt.ui.client.widget.grid.GridCellRenderer;
import com.extjs.gxt.ui.client.widget.grid.RowExpander;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
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
import com.xyz.customer.client.i18n.CusMessages;
import com.xyz.resources.client.ZkbConstants;
import com.xyz.trade.client.Trade;
import com.xyz.trade.client.view.TradeForm;

public class CusMainView extends LayoutContainer {

  private ContentPanel panel;
  private GridCellRenderer<CusModel> gridNumber;
  private GridCellRenderer<CusModel> statusRen;
  
  private CusMessages msg;

  public CusMainView() {
    final NumberFormat currency = NumberFormat.getCurrencyFormat();
    final NumberFormat number = NumberFormat.getFormat("0.00");
    final NumberCellRenderer<Grid<CusModel>> numberRenderer = new NumberCellRenderer<Grid<CusModel>>(currency);

    msg = (CusMessages) Registry.get(Customer.MESSAGE); 
    
    statusRen = new GridCellRenderer<CusModel>() {
      public String render(CusModel model, String property, ColumnData config, int rowIndex, int colIndex,
          ListStore<CusModel> store, Grid<CusModel> grid) {
        String val = (String) model.get(property);
        String sta = ZkbConstants.get().getStatus(val);
        return sta;
      }
    };

    gridNumber = new GridCellRenderer<CusModel>() {
      public String render(CusModel model, String property, ColumnData config, int rowIndex, int colIndex,
          ListStore<CusModel> store, Grid<CusModel> grid) {
        return numberRenderer.render(null, property, model.get(property));
      }
    };

    panel = new ContentPanel();
    panel.setHeaderVisible(false);
  }

  @Override
  protected void onRender(Element parent, int index) {
    super.onRender(parent, index);
    createExpander();
    add(panel);
  }

  private void createExpander() {
	  final  CusServiceAsync service = (CusServiceAsync) Registry.get(Customer.CUSSERVICE);

      // data proxy
      RpcProxy<PagingLoadResult<CusModel>> proxy = new RpcProxy<PagingLoadResult<CusModel>>() {
          @Override
          protected void load(Object loadConfig, AsyncCallback<PagingLoadResult<CusModel>> callback) {
        	  service.getCuss((PagingLoadConfig) loadConfig, callback);
          }
      };
      
    final BasePagingLoader<PagingLoadResult<CusModel>> loader = new BasePagingLoader<PagingLoadResult<CusModel>>(proxy);
    
    final PagingToolBar pagingBar = new PagingToolBar(20);
    pagingBar.bind(loader);
    
    List<ColumnConfig> configs = new ArrayList<ColumnConfig>();
    StringBuilder sb = new StringBuilder();
    sb.append("<table>");
    /*sb.append("<tr><td>").append(msg.lPayment()).append(": {payment}</td><td>").append(msg.lNum()).append(": {num}</td></tr>");
    sb.append("<tr><td>").append(msg.lBuyerEmail()).append(": {buyerEmail}</td><td>").append(msg.lPayTime()).append(":{payTime}</span> </td></tr>");
    sb.append("<tr><td>").append(msg.lBuyerMessage()).append(":{buyerMessage}</td><td></td></tr>" );
    */
    sb.append("</table>");
    XTemplate tpl = XTemplate.create(sb.toString());

    RowExpander expander = new RowExpander();
    expander.setTemplate(tpl);
    configs.add(expander);

    ColumnConfig column = new ColumnConfig("nick",msg.cusName(),150);
    configs.add(column);

    column = new ColumnConfig("sex",msg.sex(),100);
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
    
    column = new ColumnConfig("status",msg.status(),100);
    configs.add(column);

    column = new ColumnConfig("amount",msg.amount(),200);
    column.setAlignment(HorizontalAlignment.RIGHT);
    column.setRenderer(gridNumber);
    configs.add(column);

    column = new ColumnConfig("rank", msg.level(), 150);
    column.setRenderer(statusRen);
    configs.add(column);

    column = new ColumnConfig("dateEntered", msg.creatime(), 150);
    column.setAlignment(HorizontalAlignment.RIGHT);
    column.setDateTimeFormat(DateTimeFormat.getMediumDateTimeFormat());
    configs.add(column);

    ListStore<CusModel> store = new ListStore<CusModel>(loader);

    ColumnModel cm = new ColumnModel(configs);

    ContentPanel cp = new ContentPanel();
    cp.setHeading(msg.cuslist());
    ToolBar toolBar = new ToolBar();
    toolBar.setAlignment(HorizontalAlignment.RIGHT);
    Button ctBtn = new Button(msg.create(), Trade.ICONS.add());
    ctBtn.addSelectionListener(new SelectionListener<ButtonEvent>() {
        @Override
        public void componentSelected(ButtonEvent ce) {
          //MessageBox.alert("提示", "Come here",null);
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
    cp.setLayout(new FitLayout());
    //cp.setSize(950, 300);

    final Grid<CusModel> grid = new Grid<CusModel>(store, cm);
    //添加翻页工具条
    grid.addListener(Events.Attach, new Listener<GridEvent<CusModel>>() {
        public void handleEvent(GridEvent<CusModel> be) {
          PagingLoadConfig config = new BasePagingLoadConfig();
          config.setOffset(0);
          config.setLimit(20);
          
          Map<String, Object> state = grid.getState();
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
      grid.setLoadMask(true);
      
    grid.addPlugin(expander);
    grid.setAutoExpandColumn("nick");
    //grid.getView().setAutoFill(true);
    grid.setAutoHeight(true);
    cp.add(grid);

    panel.add(cp);
  }

}
