/*
 * Ext GWT - Ext for GWT
 * Copyright(c) 2007-2009, Ext JS, LLC.
 * licensing@extjs.com
 * 
 * http://extjs.com/license
 */
package com.xyz.main.client.widget;

import com.xyz.resources.client.model.Entry;


public class PortalModel extends ViewModel {

  public PortalModel() {
	  set("main", new Entry("首页", new MainPage(), null, true, false));
  }

}
