/*
 * Ext GWT - Ext for GWT
 * Copyright(c) 2007-2009, Ext JS, LLC.
 * licensing@extjs.com
 * 
 * http://extjs.com/license
 */
package com.xyz.resources.model;

import java.io.Serializable;

import com.extjs.gxt.ui.client.Style.HideMode;
import com.extjs.gxt.ui.client.data.BaseTreeModel;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.google.gwt.user.client.rpc.IsSerializable;

public class Category extends BaseTreeModel  implements Serializable ,IsSerializable{
	

	public Category() {
  }

	  private String serial;
    
    public String getSerial() {
		return serial;
	}

	public void setSerial(String serial) {
		this.serial = serial;
	}

  public Category(String name,String id) {
    set("name", name);
    this.serial = id;
  }

  public String getName() {
    return (String) get("name");
  }

  public String toString() {
    return getName();
  }

  public void add(String title, LayoutContainer page, String image) {
    add(new Entry(title, page, image));
  }

  public void add(String title, LayoutContainer page, String image, boolean fill) {
    add(new Entry(title, page, image, fill));
  }

  public void add(String title, LayoutContainer page, String image, boolean fill, boolean closable,
      HideMode hideMode) {
    add(new Entry(title, page, image, fill, closable, hideMode));
  }
}
