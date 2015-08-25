/**
 * 主页面
 */
package com.xyz.main.client.widget;

import com.extjs.gxt.ui.client.Registry;
import com.extjs.gxt.ui.client.Style.Scroll;
import com.extjs.gxt.ui.client.data.ModelData;
import com.extjs.gxt.ui.client.event.IconButtonEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.ListView;
import com.extjs.gxt.ui.client.widget.button.ToolButton;
import com.extjs.gxt.ui.client.widget.custom.Portal;
import com.extjs.gxt.ui.client.widget.custom.Portlet;
import com.google.gwt.user.client.Element;
import com.xyz.customer.client.mvc.CusPortlet;
import com.xyz.customer.client.mvc.TaskPortlet;
import com.xyz.main.client.Main;
import com.xyz.resources.client.TestData;
import com.xyz.resources.client.i18n.ZkMessages;
import com.xyz.trade.client.view.RefundPortlet;
import com.xyz.trade.client.view.TradePortlet;

public class MainPage extends LayoutContainer {
	public MainPage() {
	}

  private ListView<ModelData> dataView;
  private ZkMessages msg;

  @Override
  protected void onRender(Element parent, int index) {
    super.onRender(parent, index);
    setScrollMode(Scroll.AUTO);
    msg = (ZkMessages) Registry.get(Main.MESSAGE);
    
    Portal portal = new Portal(3);
    //portal.setBorders(true);
    portal.setStyleAttribute("backgroundColor", "white");
    portal.setColumnWidth(0, .50);
    portal.setColumnWidth(1, .50);

    Portlet portlet = new TradePortlet();
    portal.add(portlet, 0);

    portlet = new RefundPortlet();
    portal.add(portlet, 0);

    portlet = new CusPortlet();
    portal.add(portlet, 1);
    
    portlet = new TaskPortlet();
    portal.add(portlet, 1);
    
    add(portal);
  }

  private String getBogusText() {
    return "<div class=text style='padding: 5px'>" + TestData.DUMMY_TEXT_SHORT + "</div>";
  }

  private void configPanel(final ContentPanel panel) {
    panel.setCollapsible(true);
    panel.setAnimCollapse(false);
    panel.getHeader().addTool(new ToolButton("x-tool-gear"));
    panel.getHeader().addTool(
        new ToolButton("x-tool-close", new SelectionListener<IconButtonEvent>() {

          @Override
          public void componentSelected(IconButtonEvent ce) {
            panel.removeFromParent();
          }

        }));
  }

}
