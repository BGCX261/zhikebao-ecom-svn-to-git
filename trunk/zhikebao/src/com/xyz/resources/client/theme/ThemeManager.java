package com.xyz.resources.client.theme;

import java.util.ArrayList;
import java.util.List;

/**
 * 皮肤管理器
 * @author val
 */

public class ThemeManager {

	  private static List<Theme> themes = new ArrayList<Theme>();

	  static {
	    register(Theme.BLUE);
	    register(Theme.GRAY);
	  }

	  /**
	   * Returns all registered themes.
	   * 
	   * @return the themes
	   */
	  public static List<Theme> getThemes() {
	    return new ArrayList<Theme>(themes);
	  }

	  /**
	   * Returns the theme at the given index.
	   * 
	   * @param index the index
	   * @return the theme
	   */
	  public static Theme getTheme(int index) {
	    return themes.get(index);
	  }

	  /**
	   * Registers a theme.
	   * 
	   * @param theme the theme to register.
	   */
	  public static void register(Theme theme) {
	    themes.add(theme);
	  }

	  /**
	   * Unregisters a theme.
	   * 
	   * @param theme the theme to unregister
	   */
	  public static void unregister(Theme theme) {
	    themes.remove(theme);
	  }
	}
