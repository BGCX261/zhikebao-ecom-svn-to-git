package com.xyz.trade.client.view;

import java.util.List;

import com.extjs.gxt.ui.client.Registry;
import com.extjs.gxt.ui.client.binding.FormBinding;
import com.extjs.gxt.ui.client.data.BeanModel;
import com.extjs.gxt.ui.client.data.BeanModelLookup;
import com.extjs.gxt.ui.client.mvc.Dispatcher;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.TabItem;
import com.extjs.gxt.ui.client.widget.TabPanel;
import com.extjs.gxt.ui.client.widget.Window;
import com.extjs.gxt.ui.client.widget.form.ComboBox;
import com.extjs.gxt.ui.client.widget.form.FormPanel;
import com.extjs.gxt.ui.client.widget.form.SimpleComboBox;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.extjs.gxt.ui.client.widget.form.ComboBox.TriggerAction;
import com.extjs.gxt.ui.client.widget.layout.ColumnData;
import com.extjs.gxt.ui.client.widget.layout.ColumnLayout;
import com.extjs.gxt.ui.client.widget.layout.FillLayout;
import com.extjs.gxt.ui.client.widget.layout.FormData;
import com.extjs.gxt.ui.client.widget.layout.FormLayout;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.xyz.resources.client.AppEvents;
import com.xyz.resources.client.pages.Template;
import com.xyz.resources.model.BaseCode;
import com.xyz.trade.client.Trade;
import com.xyz.trade.client.TradeServiceAsync;
import com.xyz.trade.client.i18n.TradeMessages;

public class TradeForm extends Window {
	private TradeMessages msg;
	
	private final  TradeServiceAsync service;
	
	private FormPanel tradeFp;
	private FormData formData;  
	protected ListStore<BeanModel> store; 
	protected BeanModel tradeBean; 
	private FormBinding fb;

	public TradeForm(final TradeServiceAsync service,final ListStore<BeanModel> store) {
		this.service = service;
		this.store = store;
        msg = (TradeMessages) Registry.get(Trade.MESSAGE); 
        
		setModal(true);
		setPosition(100,100);
		setHeading(msg.orderForm());
		
		formData = new FormData("30%");  
		
		tradeFp = new FormPanel();
		tradeFp.setLayout(new FillLayout());
		tradeFp.setHeaderVisible(false);
		tradeFp.setBorders(false);
		tradeFp.setCollapsible(true);
		
		createTab();
		createTradeContainer();
		
		add(tradeFp);
		this.setAutoWidth(true);
		fb = new FormBinding(tradeFp, true);
	}
	
	/**
	 * 创建买卖方信息Tab页
	 */
	private void createTab()
	{
		TabPanel tabPanel = new TabPanel();
		tabPanel.setHeight(150);
		TabItem cusTi = new TabItem(msg.customer());
		cusTi.setLayout(new ColumnLayout());
		cusTi.setStyleName("pad-text");
		LayoutContainer firCol = new LayoutContainer();
		FormLayout fl = new FormLayout();
		firCol.setLayout(fl);
		
		TextField<String> vBnTf = new TextField<String>();
		firCol.add(vBnTf, formData);
		vBnTf.setName(msg.dBuyerNick());
		vBnTf.setFieldLabel(msg.lBuyerNick());
		
		ComboBox<BeanModel> typeCb = new ComboBox<BeanModel>();
		typeCb.setName(msg.typeCb_name());
		
		/**
		 * 为下拉列表添加选项，通过RPC查询
		 */
		final ListStore<BeanModel> codes = new ListStore<BeanModel>();
		service.getCusType(new AsyncCallback<List<BaseCode>>() {
		      public void onFailure(Throwable caught) {
		        Dispatcher.forwardEvent(AppEvents.Error, caught);
		      }
              public void onSuccess(List<BaseCode> result) {
            	  List<BeanModel> beans = BeanModelLookup.get().getFactory(
              			BaseCode.class).createModel(result);
		    	  codes.add(beans);
		      }
		    });
		typeCb.setDisplayField("name");
		typeCb.setEmptyText(msg.defaultOption());
		typeCb.setStore(codes);
		typeCb.setTypeAhead(true); 
		typeCb.setTemplate(Template.getOptionTpl());
		typeCb.setTriggerAction(TriggerAction.ALL);
		
		firCol.add(typeCb, formData);
		typeCb.setFieldLabel(msg.type());
		
		TextField qmTf = new TextField();
		qmTf.setName(msg.type_name());
		firCol.add(qmTf, formData);
		qmTf.setFieldLabel(msg.lBuyerIm());
		
		cusTi.add(firCol, new ColumnData(0.3));
		
		LayoutContainer secCol = new LayoutContainer();
		secCol.setLayout(new FormLayout());
		
		TextField<String> nameTf = new TextField<String>();
		secCol.add(nameTf, formData);
		nameTf.setFieldLabel(msg.cusName());
		
		cusTi.add(secCol, new ColumnData(0.3));
	
		tabPanel.add(cusTi);
		
		TabItem recTi = new TabItem(msg.receiverName());
		recTi.setStyleName("pad-text");
		tabPanel.add(recTi);
		
		TabItem descTi = new TabItem(msg.tradeDesc());
		descTi.setStyleName("pad-text");
		tabPanel.add(descTi);
		
		tradeFp.add(tabPanel);
	}
	
	/**
	 * 创建交易详细信息输入项
	 */
	private void createTradeContainer()
	{
		LayoutContainer detailLc = new LayoutContainer();
		detailLc.setStyleAttribute("background-color", "white");
		detailLc.setStyleName("pad-text");
		detailLc.setBorders(true);
		detailLc.setStyleAttribute("margin-top", "3px");
		detailLc.setLayout(new ColumnLayout());
		
		LayoutContainer dFirCol = new LayoutContainer();
		dFirCol.setLayout(new FormLayout());
		
		TextField<String> vShop = new TextField<String>();
		vShop.setName(msg.vShop_name());
		dFirCol.add(vShop, formData);
		vShop.setFieldLabel(msg.lShop());
		
		TextField<String> vAdjustFee = new TextField<String>();
		vAdjustFee.setName(msg.vAdjustFee_name());
	    dFirCol.add(vAdjustFee, formData);
		vAdjustFee.setFieldLabel(msg.lAdjustFee());
		detailLc.add(dFirCol, new ColumnData(0.3));
		
		LayoutContainer dSecCol = new LayoutContainer();
		dSecCol.setLayout(new FormLayout());
		
		SimpleComboBox vLevelCb = new SimpleComboBox();
		vLevelCb.setName(msg.vLevelCb_name());
		dSecCol.add(vLevelCb, formData);
		vLevelCb.setFieldLabel(msg.lLevel());
		
		TextField vTotalFee = new TextField();
		vTotalFee.setName(msg.vTotalFee_name());
		dSecCol.add(vTotalFee, formData);
		vTotalFee.setFieldLabel(msg.lTotalFee());
		detailLc.add(dSecCol, new ColumnData(0.3));
		
		LayoutContainer dThiCol = new LayoutContainer();
		dThiCol.setLayout(new FormLayout());
		
		SimpleComboBox vsellerRateCb = new SimpleComboBox();
		vsellerRateCb.setName(msg.vsellerRateCb_name());
		dThiCol.add(vsellerRateCb, formData);
		vsellerRateCb.setFieldLabel(msg.lSellerRate());
		detailLc.add(dThiCol, new ColumnData(0.3));
		
		tradeFp.add(detailLc);
	}

	public void setModel(BeanModel bean) {
		fb.bind(bean);
	}
	
	
}
