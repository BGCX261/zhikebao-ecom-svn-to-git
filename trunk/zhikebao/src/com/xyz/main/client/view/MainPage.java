/**
 * 主页面
 */
package com.xyz.main.client.view;

import com.extjs.gxt.ui.client.Style.Scroll;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.custom.Portal;
import com.extjs.gxt.ui.client.widget.custom.Portlet;
import com.google.gwt.user.client.Element;
import com.xyz.customer.client.mvc.CusPortlet;
import com.xyz.trade.client.view.RefundPortlet;
import com.xyz.trade.client.view.TradePortlet;

public class MainPage extends LayoutContainer {
	public MainPage() {
	}
  @Override
  protected void onRender(Element parent, int index) {
    super.onRender(parent, index);
    setScrollMode(Scroll.AUTO);
    
    Portal portal = new Portal(3);
    //portal.setBorders(true);
    portal.setStyleAttribute("backgroundColor", "white");
    portal.setColumnWidth(0, .50);
    portal.setColumnWidth(1, .50);

    Portlet portlet = new MapPortlet();
    portal.add(portlet, 0);
    
    portlet = new RefundPortlet();
    portal.add(portlet, 0);

    portlet = new TradePortlet();
    portal.add(portlet, 1);
    
    portlet = new CusPortlet();
    portal.add(portlet, 1);

    add(portal);
  }

}
