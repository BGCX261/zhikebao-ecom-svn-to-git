/*
 * Ext GWT - Ext for GWT
 * Copyright(c) 2007-2009, Ext JS, LLC.
 * licensing@extjs.com
 * 
 * http://extjs.com/license
 */
package com.xyz.main.client.mvc;

import com.extjs.gxt.ui.client.Registry;
import com.extjs.gxt.ui.client.Style.LayoutRegion;
import com.extjs.gxt.ui.client.mvc.AppEvent;
import com.extjs.gxt.ui.client.mvc.Controller;
import com.extjs.gxt.ui.client.mvc.View;
import com.extjs.gxt.ui.client.util.Margins;
import com.extjs.gxt.ui.client.util.ThemeManager;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.HtmlContainer;
import com.extjs.gxt.ui.client.widget.Viewport;
import com.extjs.gxt.ui.client.widget.custom.ThemeSelector;
import com.extjs.gxt.ui.client.widget.layout.AccordionLayout;
import com.extjs.gxt.ui.client.widget.layout.BorderLayout;
import com.extjs.gxt.ui.client.widget.layout.BorderLayoutData;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.extjs.gxt.ui.client.widget.layout.RowData;
import com.extjs.gxt.ui.client.widget.layout.RowLayout;
import com.extjs.gxt.ui.client.widget.toolbar.ToolBar;
import com.google.gwt.user.client.ui.RootPanel;
import com.xyz.main.client.Main;
import com.xyz.resources.client.i18n.ZkMessages;
import com.xyz.resources.client.theme.Default;

public class AppView extends View {

  public static final String VIEWPORT = "viewport";
  public static final String CENTER_PANEL = "center";
  public static final String WEST_PANEL = "west";
  public static final String NORTH_PANEL = "north";
  
  private ZkMessages msg;

  private Viewport viewport;
  private ContentPanel center;
  private ContentPanel west;
  private ContentPanel north; 

  public AppView(Controller controller) {
    super(controller);
  }

  protected void initialize() {
   /* LoginDialog dialog = new LoginDialog();
    dialog.setClosable(false);
    dialog.addListener(Events.Hide, new Listener<WindowEvent>() {
      public void handleEvent(WindowEvent be) {
        Dispatcher.forwardEvent(AppEvents.Init);
      }
    });
    dialog.hide();*/
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
	RowLayout rl = new RowLayout();
	north = new ContentPanel();
	north.setLayout(rl);
	north.setHeaderVisible(false);
	StringBuffer sb = new StringBuffer();
	sb.append("<embed height='85' width='950' wmode='transparent' allowscriptaccess='always' quality='high' ");
	sb.append("flashvars='bannerWidth=950&bannerHeight=100&bannerSID=http://img.alimama.cn/bm/x/2009-11-14/2009-11-14_5273dfd9d81531b9ca09d90dfaf1e30d_0.xml&bannerXML=&bannerLink=http://&dataSource=&bid=1842448' ");
	sb.append(" name='1842448' id='1842448' src='http://img.alimama.cn/bm/bcv1.swf?v=092ebed43c01a87607c19ceceebeefd99953eb59'");
	sb.append(" type='application/x-shockwave-flash'/></embed>");
	HtmlContainer banner = new HtmlContainer(sb.toString());
	banner.setStateful(false);
	north.add(banner, new RowData());
	north.setStateful(false);
	//north.setUrl("/commons/header.html");
	ThemeManager.register(Default.DEFAULT);
    ThemeSelector selector = new ThemeSelector();
    selector.setWidth(125);
    north.add(selector, new RowData());
    BorderLayoutData data = new BorderLayoutData(LayoutRegion.NORTH, 80);
    data.setMargins(new Margins());
    viewport.add(north, data);
  }

  private void createCenter() { 
    center = new ContentPanel();
    center.setBorders(false);
    center.setHeaderVisible(false);
    center.setLayout(new FitLayout());

    BorderLayoutData data = new BorderLayoutData(LayoutRegion.CENTER);
    data.setMargins(new Margins(5, 5, 5, 0));
    viewport.add(center, data);
  }

  protected void handleEvent(AppEvent event) {
    
  }

}
