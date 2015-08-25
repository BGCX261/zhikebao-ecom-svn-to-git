package com.xyz.resources.client.theme;

import java.util.Map;

import com.extjs.gxt.ui.client.GXT;
import com.extjs.gxt.ui.client.core.FastMap;
import com.extjs.gxt.ui.client.data.BaseModelData;
/**
 * 重写Theme类
 * @author val
 *
 */
public class Theme extends BaseModelData {
	 /**
	   * Default GXT blue theme.
	   */
	  public static Theme BLUE;

	  /**
	   * GXT gray theme (default path is 'gxt/css/gxt-gray.css').
	   */
	  public static Theme GRAY;

	  static {
	    BLUE = new Theme("Blue", "蓝色", "css/gxt-all.css");
	    GRAY = new Theme("Gray","灰色", "css/gxt-gray.css");
	  }

	  protected Theme() {

	  }

	  public Theme(String id, String name, String file) {
	    set("id", id);
	    set("name", name);
	    set("file", file);
	  }

	  public String getId() {
	    return this.<String> get("id");
	  }

	  public String getName() {
	    return this.<String> get("name");
	  }

	  public String getFile() {
	    return this.<String> get("file");
	  }

	  public Map<String, Object> asMap() {
	    Map<String, Object> map = new FastMap<Object>();
	    map.put("id", getId());
	    map.put("file", getFile());
	    return map;
	  }

	}
