/*
 * Ext GWT - Ext for GWT
 * Copyright(c) 2007-2009, Ext JS, LLC.
 * licensing@extjs.com
 * 
 * http://extjs.com/license
 */
package com.xyz.system.client.mvc;

import java.io.Serializable;

import com.extjs.gxt.ui.client.data.BaseTreeModel;

public class Code extends BaseTreeModel implements Serializable {

	private static int ID = 0;

	public Code() {
		set("id", ID++);
	}

	public Code(String name) {
		this();
		set("name", name);
	}

	public Code(Long id,String name) {
		set("id", id);
		set("name", name);
	}
	
	public Code(Long id,String name, boolean isParent) {
		set("id", id);
		set("name", name);
		set("hasChild",isParent);
	}
	
	public Code(Long id,String name,Integer inx, boolean isParent) {
		set("id", id);
		set("name", name);
		set("inx",inx);
		set("hasChild",isParent);
	}

	public Code(String name, BaseTreeModel[] children) {
		this(name);
		for (int i = 0; i < children.length; i++) {
			add(children[i]);
		}
	}

	public Code(Long id,String name,  BaseTreeModel[] children) {
		this( id,name);
		for (int i = 0; i < children.length; i++) {
			add(children[i]);
		}
	}

 	public Long getId() {
		return (Long) get("id");
	}

	public String getName() {
		return (String) get("name");
	}
	
	public Integer getInx() {
		return (Integer)get("inx");
	}
	

	public String toString() {
		return getName();
	}
	
	public boolean setIsParent(boolean hasChild) {
		return (Boolean) set("hasChild",hasChild);
	}

	public boolean hasChild() {
		return (Boolean) get("hasChild");
	}
	
}
