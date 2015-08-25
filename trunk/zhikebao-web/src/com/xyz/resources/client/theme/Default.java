package com.xyz.resources.client.theme;

import com.extjs.gxt.ui.client.util.Theme;

public class Default extends Theme {

	  public static Theme DEFAULT = new Default();

	  public Default() {
	    super("default", "默认", "css/gxt-all.css");
	  }

	  public Default(String name) {
	    super("Default", name, "css/gxt-all.css");
	  }

	}