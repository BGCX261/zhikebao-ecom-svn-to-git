package com.xyz.system.client.mvc;

import java.io.Serializable;

import com.extjs.gxt.ui.client.data.BaseTreeModel;

public class Resource extends BaseTreeModel implements Serializable {

	public Resource() {
	}

	public String getId() {
		return (String) get("id");
	}
	
	public void setId(String id) {
		set("id", id);
	}
	
	public String getKey() {
		return (String) get("key");
	}

	public void setKey(String key) {
		set("key", key);
	}

	public String getName() {
		return (String) get("name");
	}

	public void setName(String name) {
		set("name", name);
	}

	public Integer getSerial() {
		return (Integer) get("serial");
	}

	public void setSerial(Integer serial) {
		set("serial", serial);
	}

	public Integer getParentId() {
		return (Integer) get("parentId");
	}

	public void setParentId(Integer parentId) {
		set("parentId", parentId);
	}

	public String getResourceType() {
		return (String) get("resourceType");
	}

	public void setResourceType(String resourceType) {
		set("resourceType", resourceType);
	}

	public String getValue() {
		return (String) get("value");
	}

	public void setValue(String value) {
		set("value", value);
	}

	public Integer getPosition() {
		return (Integer) get("position");
	}

	public void setPosition(Integer position) {
		set("position", position);
	}

	public String getDescription() {
		return (String) get("description");
	}

	public void setDescription(String description) {
		set("description", description);
	}

	public boolean isDeleted() {
		return (Boolean) get("deleted");
	}

	public void setDeleted(boolean deleted) {
		set("deleted", deleted);
	}

	public Resource(String id, String name, Integer serial, Integer parentId,
			String resourceType, String value, Integer position,
			String description) {
		super();
		set("id", id);
		set("name", name);
		set("serial", serial);
		set("parentId", parentId);
		set("resourceType", resourceType);
		set("value", value);
		set("position", position);
		set("description", description);
	}

}
