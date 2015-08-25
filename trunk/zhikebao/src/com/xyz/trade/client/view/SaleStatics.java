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

import com.extjs.gxt.charts.client.Chart;
import com.extjs.gxt.charts.client.event.ChartEvent;
import com.extjs.gxt.charts.client.event.ChartListener;
import com.extjs.gxt.charts.client.model.BarDataProvider;
import com.extjs.gxt.charts.client.model.ChartModel;
import com.extjs.gxt.charts.client.model.Legend;
import com.extjs.gxt.charts.client.model.LineDataProvider;
import com.extjs.gxt.charts.client.model.ScaleProvider;
import com.extjs.gxt.charts.client.model.Legend.Position;
import com.extjs.gxt.charts.client.model.charts.BarChart;
import com.extjs.gxt.charts.client.model.charts.ChartConfig;
import com.extjs.gxt.charts.client.model.charts.LineChart;
import com.extjs.gxt.charts.client.model.charts.BarChart.BarStyle;
import com.extjs.gxt.ui.client.Style.Orientation;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.GridEvent;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.util.Margins;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.NumberField;
import com.extjs.gxt.ui.client.widget.form.NumberPropertyEditor;
import com.extjs.gxt.ui.client.widget.form.Radio;
import com.extjs.gxt.ui.client.widget.form.RadioGroup;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.extjs.gxt.ui.client.widget.form.FormPanel.LabelAlign;
import com.extjs.gxt.ui.client.widget.grid.CellEditor;
import com.extjs.gxt.ui.client.widget.grid.CellSelectionModel;
import com.extjs.gxt.ui.client.widget.grid.ColumnConfig;
import com.extjs.gxt.ui.client.widget.grid.ColumnModel;
import com.extjs.gxt.ui.client.widget.grid.EditorGrid;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.extjs.gxt.ui.client.widget.layout.FormLayout;
import com.extjs.gxt.ui.client.widget.layout.RowData;
import com.extjs.gxt.ui.client.widget.layout.RowLayout;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.Random;
import com.xyz.resources.client.TestData;
import com.xyz.trade.model.SaleStaData;

public class SaleStatics extends LayoutContainer {

  private EditorGrid<SaleStaData> teamSalesGrid;
  private Radio selRadio;

  @Override
  protected void onRender(Element parent, int index) {
    super.onRender(parent, index);

    final ListStore<SaleStaData> store = new ListStore<SaleStaData>();
    store.add(getTeamSales());

    // chart
    String url = "../chart/open-flash-chart.swf";
    final Chart chart = new Chart(url);

    ChartListener listener = new ChartListener() {
      public void chartClick(ChartEvent ce) {
        int row = ce.getChartConfig().getValues().indexOf(ce.getDataType());
        int col = ce.getChartModel().getChartConfigs().indexOf(ce.getChartConfig()) + 1;
        CellSelectionModel<SaleStaData> csm = (CellSelectionModel<SaleStaData>) teamSalesGrid.getSelectionModel();
        if (selRadio.getValue()) {
          csm.selectCell(row, col);
        } else {
          teamSalesGrid.startEditing(row, col);
        }
      }
    };

    ChartModel model = new ChartModel("本月销售统计",
        "font-size: 14px; font-family: Verdana; text-align: center;");
    model.setBackgroundColour("#fefefe");
    model.setLegend(new Legend(Position.TOP, true));
    model.setScaleProvider(ScaleProvider.ROUNDED_NEAREST_SCALE_PROVIDER);

    BarChart bar = new BarChart(BarStyle.GLASS);
    bar.setColour("#00aa00");
    BarDataProvider barProvider = new BarDataProvider("alphasales", "month");
    barProvider.bind(store);
    bar.setDataProvider(barProvider);
    bar.addChartListener(listener);
    bar.setTooltip("交易总数");
    model.addChartConfig(bar);

    bar = new BarChart(BarStyle.GLASS);
    bar.setColour("#0000cc");
    bar.setTooltip("成功交易数");
    barProvider = new BarDataProvider("betasales");
    barProvider.bind(store);
    /*barProvider.populateData(new ChartConfig("") {
	});*/
    bar.setDataProvider(barProvider);
    bar.addChartListener(listener);
    model.addChartConfig(bar);

    bar = new BarChart(BarStyle.GLASS);
    bar.setColour("#ff6600");
    barProvider = new BarDataProvider("gammasales");
    barProvider.bind(store);
    bar.setDataProvider(barProvider);
    bar.setTooltip("销售总额");
    bar.addChartListener(listener);
    model.addChartConfig(bar);

    LineChart line = new LineChart();
    line.setAnimateOnShow(true);
    line.setText("销售额");
    line.setTooltip("销售总额");
    line.setColour("#FF0000");
    LineDataProvider lineProvider = new LineDataProvider("gammasales");
    lineProvider.bind(store);
    line.setDataProvider(lineProvider);
    model.addChartConfig(line);

    chart.setChartModel(model);

    // grid
    NumberPropertyEditor npe = new NumberPropertyEditor(Integer.class);
    ArrayList<ColumnConfig> cols = new ArrayList<ColumnConfig>();

    ColumnConfig qtr = new ColumnConfig("month", "销售人员", 100);
    cols.add(qtr);
    qtr.setEditor(new CellEditor(new TextField<String>()));

    ColumnConfig alpha = new ColumnConfig("alphasales", "交易总数", 100);
    cols.add(alpha);
    NumberField nf = new NumberField();
    nf.setPropertyEditor(npe);
    alpha.setEditor(new CellEditor(nf));

    ColumnConfig beta = new ColumnConfig("betasales", "成功交易总数", 100);
    cols.add(beta);
    nf = new NumberField();
    nf.setPropertyEditor(npe);
    beta.setEditor(new CellEditor(nf));

    ColumnConfig gamma = new ColumnConfig("gammasales", "销售总额", 100);
    cols.add(gamma);
    nf = new NumberField();
    nf.setPropertyEditor(npe);
    gamma.setEditor(new CellEditor(nf));
    
    ColumnConfig refund = new ColumnConfig("refunds", "退换交易数", 100);
    cols.add(refund);
    nf = new NumberField();
    nf.setPropertyEditor(npe);
    refund.setEditor(new CellEditor(nf));

    ColumnModel cm = new ColumnModel(cols);

    teamSalesGrid = new EditorGrid<SaleStaData>(store, cm);
    teamSalesGrid.getView().setForceFit(true);
    teamSalesGrid.getView().setShowDirtyCells(false);
    teamSalesGrid.addListener(Events.AfterEdit, new Listener<GridEvent<SaleStaData>>() {
      public void handleEvent(GridEvent<SaleStaData> be) {
        store.commitChanges();
      }
    });

    Button reload = new Button("重新读取数据");
    reload.addSelectionListener(new SelectionListener<ButtonEvent>() {
      @Override
      public void componentSelected(ButtonEvent ce) {
        store.removeAll();
        store.add(getTeamSales());
      }
    });

    RadioGroup rg = new RadioGroup();
    rg.setFieldLabel("点击方式");
    Radio edRadio = new Radio();
    edRadio.setBoxLabel("编辑单元格");
    rg.add(edRadio);
    selRadio = new Radio();
    selRadio.setValue(true);
    selRadio.setBoxLabel("选中单元格");
    rg.add(selRadio);
    LayoutContainer radForm = new LayoutContainer(new FormLayout(LabelAlign.RIGHT));
    radForm.add(rg);

    LayoutContainer lc = new LayoutContainer();
    RowLayout rl = new RowLayout();
    lc.setLayout(rl);
    lc.setAutoHeight(true);
    RowData data;
    data = new RowData(1, 300, new Margins(10));
    ContentPanel cp = new ContentPanel(new FitLayout());
    cp.setHeading("图表");
    cp.add(chart);
    lc.add(cp, data);

    data = new RowData(1, 200, new Margins(10));
    cp = new ContentPanel(new FitLayout());
    cp.setHeading("销售业绩");
    cp.add(teamSalesGrid);
    lc.add(cp, data);

    data = new RowData(1, 60, new Margins(10));
    LayoutContainer bbar = new LayoutContainer(new RowLayout(Orientation.HORIZONTAL));
    bbar.add(reload);
    bbar.add(radForm, new RowData(1, -1));
    lc.add(bbar, data);

    add(lc);

  }
  
  public static List<SaleStaData> getTeamSales() {
	    List<SaleStaData> teamsales = new ArrayList<SaleStaData>();
	    for (String m : months) {
	    	
	      int a = Random.nextInt(200);
	      int b = Random.nextInt(a);
	      int c = Random.nextInt(100000);
	      int d = Random.nextInt(14);
	      teamsales.add(new SaleStaData(m, a, b, c,d));
	    }
	    return teamsales;
	  }
  
  private static final String[] months = new String[] {
      "客服一", "客服二", "客服三", "客服四", "客服五", "客服六", "客服七", "客服八"};


}
