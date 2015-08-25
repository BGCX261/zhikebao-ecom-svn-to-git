package com.xyz.resources.model;

import com.extjs.gxt.ui.client.data.BaseModelData;

public class Status extends BaseModelData {

	public Status() {

	}

	public Status(String code, String name) {
		set("code", code);
		set("name", name);
	}

	public String getName() {
		return (String) get("name");
	}

	public String getCode() {
		return (String) get("code");
	}

}
