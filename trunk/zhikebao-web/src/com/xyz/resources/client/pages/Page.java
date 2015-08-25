/*
 * Ext GWT - Ext for GWT
 * Copyright(c) 2007-2009, Ext JS, LLC.
 * licensing@extjs.com
 * 
 * http://extjs.com/license
 */
package com.xyz.resources.client.pages;

import com.extjs.gxt.ui.client.Style.Scroll;
import com.extjs.gxt.ui.client.event.ComponentEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.TabItem;
import com.extjs.gxt.ui.client.widget.TabPanel.TabPosition;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.xyz.resources.client.model.Entry;

public class Page extends ContentPanel {

  protected Entry entry;

  public LayoutContainer getContent() {
    return entry.getExample();
  }

  public Page(final Entry entry) {
    this.entry = entry;
    setHeaderVisible(false);
    setBodyBorder(false);
    setLayout(new FitLayout());
    add(entry.getExample());
    addListener(Events.Adopt, new Listener<ComponentEvent>() {
      public void handleEvent(ComponentEvent be) {
        if(getParent() != null && getParent() instanceof TabItem) {
          TabItem t = (TabItem) getParent();
          t.setHideMode(entry.getHideMode());
        }
      }
    });
    /*
    setTabPosition(TabPosition.BOTTOM);
    setBorderStyle(false);
    TabItem demo = new TabItem();
    demo.setScrollMode(Scroll.AUTO);
    if (entry.isFill()) {
      demo.setLayout(new FitLayout());
      demo.setScrollMode(Scroll.NONE);
    }
    
    demo.setHideMode(entry.getHideMode());

    demo.setText("示例");
    demo.add(entry.getExample());
    add(demo);
    
    if (entry.isClosable()) {
      TabItem source = new TabItem();
      source.setText("源码");
      source.setUrl(entry.getSourceUrl());
      add(source);
    }*/
  }

}


