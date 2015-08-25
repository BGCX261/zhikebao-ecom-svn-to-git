package com.xyz.trade.client.view;

import java.util.List;

import com.extjs.gxt.ui.client.Registry;
import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.binding.FieldBinding;
import com.extjs.gxt.ui.client.binding.FormBinding;
import com.extjs.gxt.ui.client.core.XTemplate;
import com.extjs.gxt.ui.client.data.BaseListLoader;
import com.extjs.gxt.ui.client.data.BeanModel;
import com.extjs.gxt.ui.client.data.BeanModelLookup;
import com.extjs.gxt.ui.client.data.BeanModelReader;
import com.extjs.gxt.ui.client.data.ListLoadResult;
import com.extjs.gxt.ui.client.data.ListLoader;
import com.extjs.gxt.ui.client.data.RpcProxy;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.ComponentEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.mvc.Dispatcher;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.util.Util;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.Window;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.ComboBox;
import com.extjs.gxt.ui.client.widget.form.FormPanel;
import com.extjs.gxt.ui.client.widget.form.TextArea;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.extjs.gxt.ui.client.widget.form.ComboBox.TriggerAction;
import com.extjs.gxt.ui.client.widget.form.FormPanel.LabelAlign;
import com.extjs.gxt.ui.client.widget.layout.ColumnLayout;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.extjs.gxt.ui.client.widget.layout.FormData;
import com.extjs.gxt.ui.client.widget.layout.FormLayout;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.xyz.resources.client.ZkbConstants;
import com.xyz.resources.model.Result;
import com.xyz.resources.model.Status;
import com.xyz.trade.client.Trade;
import com.xyz.trade.client.TradeServiceAsync;
import com.xyz.trade.client.i18n.TradeMessages;
import com.xyz.trade.model.Delivery;
import com.xyz.trade.model.LogisticCompany;
import com.xyz.trade.model.ShipAddress;
import com.xyz.trade.model.TradeModel;

public class TradeDetail extends Window {
	
	protected TradeModel trade; 
	protected Delivery deli;
	private TradeServiceAsync service;
	private TradeMessages msg;
	protected FormPanel formpanel ;
	//物流公司store
	private ListStore<BeanModel> vstore;
	//发货地址
	private ComboBox<BeanModel> cpies;

	public TradeDetail(final TradeModel trade) {
		
		msg = (TradeMessages) Registry.get(Trade.MESSAGE);
		service = (TradeServiceAsync) Registry.get(Trade.TRADESERVICE);
		
		deli = new Delivery();
		deli.setTid(trade.getTid());
		
		setModal(true);
		setPosition(150,100);
		setSize(800, 500);
		this.trade = trade;
		
	    setHeading("交易明细");
	    final ContentPanel cp = new ContentPanel();
	    cp.setHeaderVisible(false);
	    cp.setAnimCollapse(true);
	    cp.setCollapsible(true);
	    cp.setLayout(new FitLayout());
	    final XTemplate tpl = XTemplate.create(getBasicTemplate());
		
		this.setBodyStyle("background-color:white;");
		String status = trade.getStatus();
	    trade.setStatus(ZkbConstants.get().getStatus(status));
	    
		BeanModel bm = BeanModelLookup.get().getFactory(TradeModel.class)
					.createModel(trade);
		cp.addText(tpl.applyTemplate(Util.getJsObject(bm, 2)));
			
		add(cp);
		if("WAIT_SELLER_SEND_GOODS".equals(status))
	    {
		   add(createFp());
		   formpanel.setData("parent", this);
	    }
		layout();
	}
	
	 private native String getBasicTemplate() /*-{
	    var html = [
	    '<div id="" class="detail">',
	    '<table id="detailpanel_1" cellspacing="0"><tbody>',
	    '<tr><td class="label" width="10%">商品编号:</td>',
        '<td width="20%"> {iid}</td>',
        '<td class="label" width="10%">订单状态:</td>',
        '<td width="20%"> {status}</td>',
        '<td class="label" width="10%">商品价格:</td>',
        '<td width="20%"> {price}</td>',
        '</tr>',
        '<tr><td class="label" width="10%">数量:</td>',
        '<td width="20%"> {num}</td>',
        '<td class="label" width="10%">商品金额:</td>',
        '<td width="20%"> {totalFee}</td>',
        '<td class="label" width="10%">实付金额:</td>',
        '<td width="20%"> {payment}</td>',
        '</tr>',
        '<tr><td class="label" width="10%">邮费:</td>',
        '<td width="20%"> {postFee}</td>',
        '<td class="label" width="10%">买家使用积分:</td>',
        '<td width="20%"> {pointFee}</td>',
        '<td class="label" width="10%">物流订单号:</td>',
        '<td width="20%"> {sid}</td>',
        '</tr>',
        '<tr><td class="label" width="10%">买家昵称:</td>',
        '<td width="20%"> {buyerNick}</td>',
        '<td class="label" width="10%">购买时间:</td>',
        '<td width="20%"> {created:date(\"y/MM/dd\")}</td>',
        '<td class="label" width="10%">付款时间:</td>',
        '<td width="20%"> {payTime:date(\"y/MM/dd\")}</td>',
        '</tr>',
        '<tr><td class="label" width="10%">买家留言:</td>',
        '<td  colspan="5">{buyerMessage}</td>',
        '</tr>',
        '<tr><td class="header" colspan="6">收货人信息</td>',
        '</tr>',
        '<tr><td class="label" width="10%">收货人姓名 :</td>',
        '<td width="20%"> {receiverName}</td>',
        '<td class="label" width="10%">省份/城市/地区:</td>',
        '<td width="20%"> {receiverState}{receiverCity}{receiverDistrict}</td>',
        '<td class="label" width="10%">详细地址 :</td>',
        '<td width="20%"> {receiverAddress}</td>',
        '</tr>',
        '<tr><td class="label" width="10%">手机号:</td>',
        '<td width="20%"> {receiverMobile}</td>',
        '<td class="label" width="10%">电话:</td>',
        '<td width="20%"> {receiverPhone}</td>',
        '<td class="label" width="10%">邮编  :</td>',
        '<td width="20%"> {receiverZip}</td>',
        '</tr>',
        '<tr><td class="header" colspan="6">商品信息</td>',
        '</tr>',
        '<tpl for="orders" >',
        '<tr><td class="label" width="10%">商品名称:</td>',
        '<td width="20%">{#}.{title}</td>',
        '<td class="label" width="10%">数量:</td>',
        '<td width="20%"> {num}</td>',
        '<td class="label" width="10%">价格:</td>',
        '<td width="20%"> {price}</td>',
        '</tr></tpl>',
	    '</tbody></table></div>'
	    ];
	    return html.join("");

	    }-*/;

	 /**
	  * 创建处理表单
	  * @return
	  */
	 private FormPanel createFp()
	 {
		FormData formData = new FormData("95%");
		formpanel = new FormPanel();

		formpanel.setBorders(false);
		formpanel.setHeading("发货处理");
		formpanel.setAnimCollapse(true);
		formpanel.setCollapsible(true);
		formpanel.setIcon(Trade.ICONS.form());
		formpanel.setLabelAlign(LabelAlign.LEFT);
		formpanel.setButtonAlign(HorizontalAlignment.CENTER);

		formpanel.setLayout(new FormLayout());
		
		ComboBox<BeanModel> sa = createShipAddressField();
		formpanel.add(sa,formData);
		
        LayoutContainer fcp = new LayoutContainer();
        fcp.setStyleAttribute("paddingRight", "10px");
        fcp.setBorders(false);
        fcp.setLayout(new ColumnLayout());
        
		LayoutContainer left = new LayoutContainer();
		
		FormLayout layout = new FormLayout();
		layout.setLabelAlign(LabelAlign.LEFT);
		left.setLayout(layout);

		ListStore<Status> ls = new ListStore<Status>();
		Status deliSta = new Status("delivery_needed","物流订单");
		ls.add(deliSta);
		ls.add(new Status("virtual_goods","虚拟物品"));
		ComboBox<Status> scb = new ComboBox<Status>();
		scb.setStore(ls);
	    scb.setFieldLabel("类型");
	    scb.setDisplayField("name");
	    scb.setValueField("code");
	    scb.setName("orderType");
	    //scb.setValue(deliSta);
	    left.add(scb, formData);
	    scb.select(deliSta);

		LayoutContainer center = new LayoutContainer();
		//center.setStyleAttribute("paddingLeft", "10px");
		layout = new FormLayout();
		layout.setLabelAlign(LabelAlign.LEFT);
		center.setLayout(layout);

		ComboBox<BeanModel> cb = createComsField();
		center.add(cb, formData);
        
		LayoutContainer right = new LayoutContainer();
		//right.setStyleAttribute("paddingLeft", "10px");
		layout = new FormLayout();
		layout.setLabelAlign(LabelAlign.LEFT);
		right.setLayout(layout);

		TextField<String> outSid = new TextField<String>();
		outSid.setFieldLabel("运单号 ");
		outSid.setAllowBlank(false);
		outSid.setName("outSid");
		right.add(outSid, formData);

		fcp.add(left,
				new com.extjs.gxt.ui.client.widget.layout.ColumnData(.32));
		fcp.add(center,
				new com.extjs.gxt.ui.client.widget.layout.ColumnData(.32));
		fcp.add(right,
				new com.extjs.gxt.ui.client.widget.layout.ColumnData(.31));
		formpanel.add(fcp);
		
		TextArea memo = new TextArea();
		memo.setName("memo");
		memo.setPreventScrollbars(false);
		memo.setFieldLabel("卖家备注");
		FormData mformData = new FormData("95%");
		formpanel.add(memo, mformData);
		
		final FormBinding queryFb = new FormBinding(formpanel, true);
		BeanModel bm = BeanModelLookup.get().getFactory(Delivery.class)
				.createModel(deli);
		queryFb.bind(bm);
		FieldBinding saBinding = new FieldBinding(sa, "saId") {
		      @Override
		      protected Object onConvertFieldValue(Object value) {
		    	  if(value!=null)
		    	  {
		    		ShipAddress lc = ((BeanModel)value).getBean();
		    		if(lc != null )
		              return lc.getPid();
		    	  }
                    return null;
		      }
		};
		queryFb.addFieldBinding(saBinding);
		
		FieldBinding comBinding = new FieldBinding(cb, "companyCode") {
		      @Override
		      protected Object onConvertFieldValue(Object value) {
		    	  if(value!=null)
		    	  {
		    		LogisticCompany lc = ((BeanModel)value).getBean();
		    		if(lc != null )
		              return lc.getCompanyCode();
		    	  }
                      return null;
		      }
		};
		queryFb.addFieldBinding(comBinding);
		FieldBinding typeBinding = new FieldBinding(scb, "orderType") {
		      @Override
		      protected Object onConvertFieldValue(Object value) {
		    	  if(value!=null)
		    	  {
		    		Status lc = (Status)value;
		    		if(lc != null )
		              return lc.getCode();
		    	  }
                    return null;
		      }
		};
		queryFb.addFieldBinding(typeBinding);
		//如果等待发货，则显示发货处理表单
		formpanel.addButton(new Button("确认已发货",new SelectionListener<ButtonEvent>() {
				@Override
				public void componentSelected(ButtonEvent ce) {
					if(formpanel.isValid())
					{
						service.deliverySend(deli, new AsyncCallback<Result>() {
	                        public void onFailure(Throwable caught) {
	                        	Dispatcher.forwardEvent(Events.OnError, caught);
							}
	                        public void onSuccess(Result result) {
								if(result.isSuccess())
								{
									TradeDetail td = formpanel.getData("parent");
									com.google.gwt.user.client.Window.alert("发货信息已同步到淘宝。");
									td.hide();
								}else{
									com.google.gwt.user.client.Window.alert(result.getMsg());
								}
							}
						});
					}
				}
			}));
	      formpanel.addButton(new Button(msg.cancel(),new SelectionListener<ButtonEvent>() {
			@Override
			public void componentSelected(ButtonEvent ce) {
				TradeDetail td = formpanel.getData("parent");
				td.hide();
			}
	    }));
		return formpanel;
	 }
	 
	 private ComboBox<BeanModel> createComsField() {
		    vstore = new ListStore<BeanModel>();
			
			final ComboBox<BeanModel> cpies = new ComboBox<BeanModel>();
			cpies.setAllowBlank(false);
			cpies.setName("companyCode");
			cpies.setFieldLabel("物流公司");
			cpies.setStore(vstore);
			cpies.setDisplayField("companyName");
			cpies.setValueField("companyCode");
			cpies.setTypeAhead(true);
			
			return cpies;
		}
     
	 private ComboBox<BeanModel> createShipAddressField() {
		 
		 RpcProxy<List<ShipAddress>> proxy = new RpcProxy<List<ShipAddress>>() {
				@Override
				protected void load(Object loadConfig,
						AsyncCallback<List<ShipAddress>> callback) {
					service.getShipAddress(callback);
				}
			};

			ListLoader<ListLoadResult<ShipAddress>> loader = new BaseListLoader<ListLoadResult<ShipAddress>>(
					proxy,new BeanModelReader());
			ListStore<BeanModel> sastore = new ListStore<BeanModel>(loader);
			loader.load();

			cpies = new ComboBox<BeanModel>();
			cpies.setAllowBlank(false);
			cpies.setName("saId");
			cpies.setFieldLabel("发货人");
			cpies.setStore(sastore);
			//cpies.setTemplate(getSaTemplate());
			cpies.setForceSelection(true);
			cpies.setEditable(false);
			cpies.setDisplayField("sellerName");
			cpies.setValueField("pid");
			cpies.setTriggerAction(TriggerAction.ALL);  
			/*cpies.setPropertyEditor(new ListModelPropertyEditor<BeanModel>() {
				    public String getStringValue(BeanModel value) {
				       ShipAddress sa = (ShipAddress)value.getBean();
				       String str = sa.getSellerName();
				       if(sa.getSellerMobile()!=null)
				    	   str += " "+sa.getSellerMobile();
				       if(sa.getSellerPhone()!=null)
				    	   str += " "+sa.getSellerPhone();
				       if(sa.getLocation()!=null&&sa.getLocation().getAddress()!=null)
				    	   str += " "+sa.getLocation().getAddress();
				       return str;
				    }
			  });*/
			cpies.addListener(Events.Select,new Listener<ComponentEvent>() {  
			    @SuppressWarnings("unchecked")  
		        public void handleEvent(ComponentEvent be) {
			    	ComboBox cbm = (ComboBox)be.getComponent();
			    	BeanModel bm = (BeanModel)cbm.getValue();
	                ShipAddress sa = (ShipAddress)bm.getBean();
	        		if(sa!=null)
	        		{
	        			List<LogisticCompany> lc = sa.getLl();
	        			List<BeanModel> lbm = BeanModelLookup.get().getFactory(LogisticCompany.class).createModel(lc);
	        			vstore.add(lbm);
	        		}
			    }
			});
			
			return cpies;
		}
	 
	/* private native String getSaTemplate() -{ 
	      return  [ 
	      '<tpl for=".">', 
	      '<div class="x-combo-list-item">{[values.sellerName]}/{[values.sellerPhone]}/{[values.sellerMobile]}/{[values.location.state]}/{[values.location.city]} </div>', 
	      '</tpl>' 
	      ].join(""); 
	  }-;  */
}
