/*
 * Ext GWT - Ext for GWT
 * Copyright(c) 2007-2009, Ext JS, LLC.
 * licensing@extjs.com
 * 
 * http://extjs.com/license
 */
package com.xyz.resources.model;

import java.io.Serializable;

import com.extjs.gxt.ui.client.data.BaseTreeModel;

public class Folder extends BaseTreeModel implements Serializable {

	private static int ID = 0;

	public Folder() {
		set("id", ID++);
	}

	public Folder(String name) {
		this();
		set("name", name);
	}

	public Folder(String name, String id) {
		set("id", id);
		set("name", name);
	}
	
	public Folder(String name, String id,boolean isParent) {
		set("id", id);
		set("name", name);
		set("hasChild",isParent);
	}

	public Folder(String name, BaseTreeModel[] children) {
		this(name);
		for (int i = 0; i < children.length; i++) {
			add(children[i]);
		}
	}

	public Folder(String name, String id, BaseTreeModel[] children) {
		this(name, id);
		for (int i = 0; i < children.length; i++) {
			add(children[i]);
		}
	}

	public String getId() {
		return (String) get("id");
	}

	public String getName() {
		return (String) get("name");
	}
	
	public boolean hasChild() {
		return (Boolean) get("hasChild");
	}

	public String toString() {
		return getName();
	}

}
