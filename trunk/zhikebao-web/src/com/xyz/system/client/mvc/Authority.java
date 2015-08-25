package com.xyz.system.client.mvc;

import java.io.Serializable;

import com.extjs.gxt.ui.client.data.BaseTreeModel;
import com.extjs.gxt.ui.client.data.BeanModelTag;


public class Authority extends BaseTreeModel implements   Serializable {
	public Authority(){
	}

	private String key;
	private String role;
	private String displayName;
	private boolean deleted;
	public Authority(String role, String displayName) {
		super();
		setRole(role);
		setDisplayName(displayName);
	}

	public String getKey() {
		return (String)get("key");
	}

	public void setKey(String key) {
		this.key = key;
		set("key",key);
	}

	public String getRole() {
		return (String)get("role");
	}

	public void setRole(String role) {
		this.role = role;
		set("role",role);
	}

	public String getDisplayName() {
		return (String)get("displayName");
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
		set("displayName",displayName);
	} 
	
	public boolean isDeleted() {
		return (Boolean) get("deleted");
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
		set("deleted", deleted);
	}
}


