package com.xyz.resources.client.theme;

public class Default extends Theme {

	
	private static final long serialVersionUID = 1L;
	
	public static Theme DEFAULT = new Default();

	  public Default() {
	    super("default", "默认", "css/gxt-all.css");
	  }

	  public Default(String name) {
	    super("Default", name, "css/gxt-all.css");
	  }
	  
}