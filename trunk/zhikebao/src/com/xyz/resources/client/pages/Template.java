package com.xyz.resources.client.pages;

public class Template {
	
	  public static native String getOptionTpl() /*-{
	    return  [
	    '<tpl for=".">',
	    '<div class="x-combo-list-item" qtip="{name}" qtitle="名称">{name}</div>',
	    '</tpl>'
	    ].join("");
	  }-*/;
	  
	  public static native String getCustomerTpl() /*-{
	     return [
	      '<tpl for=".">',
	      '<div class="search-item" qtip="{nick}" qtitle="昵称">{nick}</div>',
	      '</tpl>'
	    ].join("");
	   }-*/;

}
