/*
 * Ext GWT - Ext for GWT
 * Copyright(c) 2007-2009, Ext JS, LLC.
 * licensing@extjs.com
 * 
 * http://extjs.com/license
 */
package com.xyz.main.client.view;

import com.extjs.gxt.ui.client.Registry;
import com.extjs.gxt.ui.client.Style.Direction;
import com.extjs.gxt.ui.client.Style.LayoutRegion;
import com.extjs.gxt.ui.client.core.XTemplate;
import com.extjs.gxt.ui.client.data.BeanModel;
import com.extjs.gxt.ui.client.data.BeanModelLookup;
import com.extjs.gxt.ui.client.event.EventType;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.IconButtonEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.fx.FxConfig;
import com.extjs.gxt.ui.client.mvc.AppEvent;
import com.extjs.gxt.ui.client.mvc.Controller;
import com.extjs.gxt.ui.client.mvc.Dispatcher;
import com.extjs.gxt.ui.client.mvc.View;
import com.extjs.gxt.ui.client.util.Margins;
import com.extjs.gxt.ui.client.util.Util;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.Dialog;
import com.extjs.gxt.ui.client.widget.HtmlContainer;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.Viewport;
import com.extjs.gxt.ui.client.widget.button.ToolButton;
import com.extjs.gxt.ui.client.widget.custom.ThemeSelector;
import com.extjs.gxt.ui.client.widget.layout.AccordionLayout;
import com.extjs.gxt.ui.client.widget.layout.BorderLayout;
import com.extjs.gxt.ui.client.widget.layout.BorderLayoutData;
import com.extjs.gxt.ui.client.widget.layout.ColumnData;
import com.extjs.gxt.ui.client.widget.layout.ColumnLayout;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.extjs.gxt.ui.client.widget.toolbar.ToolBar;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.RootPanel;
import com.xyz.main.client.Main;
import com.xyz.main.client.MainServiceAsync;
import com.xyz.main.model.Shop;
import com.xyz.resources.client.AppEvents;
import com.xyz.resources.client.i18n.ZkMessages;
import com.xyz.resources.client.theme.Default;
import com.xyz.resources.client.theme.ThemeManager;

public class AppView extends View {
  public static final String VIEWPORT = "viewport";
  public static final String CENTER_PANEL = "center";
  public static final String WEST_PANEL = "west";
  public static final String NORTH_PANEL = "north";
  public static final String TOP_BOARD = "topboard";
  
  private ZkMessages msg;

  private MainServiceAsync service;
  private Viewport viewport;
  private ContentPanel center;
  private ContentPanel west;
  private ContentPanel north; 
  
  //头部右侧信息
  private LayoutContainer right;
  //最新提醒信息
  private ContentPanel noticePanel ;
  
  public AppView(Controller controller) {
    super(controller);
    
  }

  protected void initialize() {
    service = (MainServiceAsync) Registry.get(Main.SERVICE);  
	initUI();
  }

  private void initUI() {
    viewport = new Viewport();
    viewport.setLayout(new BorderLayout());
    
    msg = (ZkMessages) Registry.get(Main.MESSAGE);
    
    createNorth();
    createWest();
    createCenter();

    // registry serves as a global context
    Registry.register(VIEWPORT, viewport);
    
    Registry.register(CENTER_PANEL, center);
    Registry.register(WEST_PANEL, west);
    Registry.register(NORTH_PANEL, north);
    
    RootPanel.get().add(viewport);
  }
  
  private void createWest() {
	    BorderLayoutData data = new BorderLayoutData(LayoutRegion.WEST, 180, 150, 320);
	    data.setMargins(new Margins(5, 0, 5, 5));                       
	    data.setCollapsible(true);
	    
	    west = new ContentPanel();
	    west.setBodyBorder(false);
	    west.setLayout(new AccordionLayout());
	    west.setLayoutOnChange(true);
	    
	    ToolBar toolBar = new ToolBar();
	    west.setTopComponent(toolBar);
	    
	    viewport.add(west, data);
 }

  private void createNorth() {
	ColumnLayout cl = new ColumnLayout();
	north = new ContentPanel();
	north.setLayout(cl);
	north.setHeaderVisible(false);
	north.setStyleName("header");
	BorderLayoutData data = new BorderLayoutData(LayoutRegion.NORTH, 80);
    data.setMargins(new Margins());
    
    StringBuffer sb = new StringBuffer();
	sb.append("<embed height='85' width='950' wmode='transparent' allowscriptaccess='always' quality='high' ");
	sb.append("flashvars='bannerWidth=950&bannerHeight=100&bannerSID=http://img.alimama.cn/bm/x/2009-11-14/2009-11-14_5273dfd9d81531b9ca09d90dfaf1e30d_0.xml&bannerXML=&bannerLink=http://&dataSource=&bid=1842448' ");
	sb.append(" name='1842448' id='1842448' src='http://img.alimama.cn/bm/bcv1.swf?v=092ebed43c01a87607c19ceceebeefd99953eb59'");
	sb.append(" type='application/x-shockwave-flash'/></embed>");
	HtmlContainer banner = new HtmlContainer(sb.toString());
	banner.setStateful(false);
	north.add(banner, new ColumnData(.75));
    viewport.add(north, data);
	//Dialog task = new Dialog();
   }

  private void createCenter() { 
    center = new ContentPanel();
    center.setBorders(false);
    center.setHeaderVisible(false);
    center.setLayout(new FitLayout());

    BorderLayoutData data = new BorderLayoutData(LayoutRegion.CENTER);
    data.setMargins(new Margins(5, 5, 5, 0));
    viewport.add(center, data);
    
    //center.add(noticePanel);
  }

  protected void handleEvent(AppEvent event) {
    EventType type = event.getType();
    if (type==AppEvents.Init)
    {  	
        right = new LayoutContainer();
        right.setLayout(new FitLayout());
        right.setBorders(false);
        right.setStyleName("board-header");
        //显示当前商家信息
        service.getShopInfo(new AsyncCallback<Shop>() {
    		public void onSuccess(Shop result) {
    			if(result!=null)
    			{
    				StringBuffer sb = new StringBuffer();
    			    sb.append("<div id='hedaer_right' style='background-image: url(http://logo.taobao.com/shop-logo/{picPath})'>");
    			    sb.append("<span class='headertitle'>{title}"+msg.oss()+"</span><br><br>");
    			    sb.append("<ul>");
    			    sb.append("<li>"+msg.boss()+":{nick}</li>");
    			    sb.append("<li>{desc:ellipsis(24)}</li>");
    			    sb.append("</ul>");
    			    sb.append("</div><br>");

    			    final XTemplate template = XTemplate.create(sb.toString());
    			    final HTML html = new HTML();
    			    BeanModel bean = BeanModelLookup.get().getFactory(
    						Shop.class).createModel(result);
    			    template.overwrite(html.getElement(), Util.getJsObject(bean));
    			    right.add(html);
    			    
    			    ThemeManager.register(Default.DEFAULT);
    			    ThemeSelector selector = new ThemeSelector();
    			    right.add(selector);
    			    north.add(right, new ColumnData(.25));
    			    north.layout();
    			}
    		}
    		public void onFailure(Throwable caught) {
    			Dispatcher.forwardEvent(Events.OnError, caught);
    		}
    	});
    }/*else if (type == AppEvents.NoticeTask) {
		onNotcieTask();
	}*/
}

/*public void onNotcieTask() {
	noticePanel = new Dialog();  
    noticePanel.setHeading("最新提醒");  
		    //cp.setIcon(Resources.ICONS.text());  
    noticePanel.setWidth(100);  
    noticePanel.addText("有一个新订单需要处理");  
			
    noticePanel.setLayout(new FitLayout());
    noticePanel.setStyleAttribute("position", "relative");
			
    noticePanel.setPosition(150, 200);
    noticePanel.setAutoHeight(true);
	noticePanel.addText("有一个新订单需要处理");  
	
	if (noticePanel.isVisible()) {
		noticePanel.el().slideOut(Direction.UP, FxConfig.NONE);
	} else {
	    noticePanel.el().slideIn(Direction.DOWN, FxConfig.NONE);
		noticePanel.el().blink(FxConfig.NONE);
		//Window.alert("显示出来");
	}

  }*/

}
