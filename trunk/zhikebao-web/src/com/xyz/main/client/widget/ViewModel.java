package com.xyz.main.client.widget;

import java.util.ArrayList;
import java.util.List;

import com.extjs.gxt.ui.client.data.BaseTreeModel;
import com.extjs.gxt.ui.client.data.ModelData;
import com.extjs.gxt.ui.client.data.TreeModel;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.google.gwt.core.client.EntryPoint;
import com.xyz.customer.client.Customer;
import com.xyz.customer.client.mvc.CusMainView;
import com.xyz.resources.client.model.Category;
import com.xyz.resources.client.model.Entry;
import com.xyz.stock.client.Stock;
import com.xyz.stock.client.view.CategoryView;
import com.xyz.stock.client.view.OnsaleItemView;
import com.xyz.system.client.General;
import com.xyz.system.client.mvc.AuthView;
import com.xyz.system.client.mvc.CodeView;
import com.xyz.system.client.mvc.ResourceView;
import com.xyz.system.client.mvc.ToolView;
import com.xyz.system.client.mvc.UserView;
import com.xyz.trade.client.Trade;
import com.xyz.trade.client.view.TradeAssign;
import com.xyz.trade.client.view.TradeMainView;

public class ViewModel extends BaseTreeModel {

	  protected List<Entry> entries = new ArrayList<Entry>();

	  public ViewModel() {
		  
	  }

	  public Entry findEntry(String name) {
	    if (get(name) != null) {
	      return (Entry) get(name);
	    }
	    for (Entry entry : getEntries()) {
	      if (name.equals(entry.getId())) {
	        return entry;
	      }
	    }
	    return null;
	  }

	  public List<Entry> getEntries() {
	    return entries;
	  }
	  
	  public void loadEntries(TreeModel model) {
		  
		    for (ModelData child : model.getChildren()) {
		      if (child instanceof Entry) {
		    	initEntry((Entry)child);
		        entries.add((Entry) child);
		      } else if (child instanceof Category) {
		    	 initModule((Category)child);
		         loadEntries((Category) child);
		      }
		  }
	 }
	  /**
	   * 初始化各个模块
	   * @param entry
	   */
	  private void initModule(Category cate)
	  {
		 String id = cate.getSerial();
		 EntryPoint ep = null;
		 if(id.equals("100"))
		 {
			 ep = new Trade();
		 }else if(id.equals("400"))
		 {
			 ep = new Stock();
		 }else if(id.equals("500"))
		 {
			 ep = new General();
		 }else if(id.equals("300"))
		 {
			 ep = new Customer();
		 }
		 if(ep!=null)
		    ep.onModuleLoad();
	  }
	  /**
	   * 初始化各个功能界面
	   * @param entry
	   */
	  private void initEntry(Entry entry)
	  {
		 String id = entry.getSerial();
		 LayoutContainer lc = null;
		 if(id.equals("501"))
			 lc = new OnsaleItemView();
		 else if(id.equals("403"))
			 lc = new CategoryView();
		 else if(id.equals("301"))
			 lc = new CusMainView();
		 else if(id.equals("206"))
			 lc = new ToolView();
		 else if(id.equals("205"))
			 lc = new CodeView();
		 else if(id.equals("204"))
			 lc = new ResourceView();
		 else if(id.equals("203"))
			 lc = new AuthView();
		 else if(id.equals("202"))
			 lc = new UserView();
		 else if(id.equals("104"))
			 lc = new TradeMainView();
		 else if(id.equals("102"))
			 lc = new TradeAssign();
		 if(lc!=null)
		    entry.set("example", lc);  
	  }
	    
}
