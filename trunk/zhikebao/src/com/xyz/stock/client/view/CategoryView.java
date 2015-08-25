package com.xyz.stock.client.view;

import java.util.Arrays;
import java.util.List;

import com.extjs.gxt.ui.client.Registry;
import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.data.BaseTreeLoader;
import com.extjs.gxt.ui.client.data.RpcProxy;
import com.extjs.gxt.ui.client.data.TreeLoader;
import com.extjs.gxt.ui.client.store.TreeStore;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.grid.ColumnConfig;
import com.extjs.gxt.ui.client.widget.grid.ColumnModel;
import com.extjs.gxt.ui.client.widget.grid.RowNumberer;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.extjs.gxt.ui.client.widget.treegrid.TreeGrid;
import com.extjs.gxt.ui.client.widget.treegrid.TreeGridCellRenderer;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.xyz.resources.model.Folder;
import com.xyz.stock.client.ProductServiceAsync;
import com.xyz.stock.client.Stock;

public class CategoryView extends LayoutContainer {
	
	  private TreeLoader<Folder> loader;

	  @Override
	  protected void onRender(Element parent, int index) {
	    super.onRender(parent, index);
	    
	    setLayout(new FitLayout());
    
	    final  ProductServiceAsync service = (ProductServiceAsync) Registry.get(Stock.PRODSERVICE);

	      // data proxy
	      RpcProxy<List<Folder>> proxy = new RpcProxy<List<Folder>>() {
	          @Override
	          protected void load(Object loadConfig, AsyncCallback<List<Folder>> callback) {
	              Folder parent = (Folder)loadConfig;
	              String pid = "0";
	              if(parent!=null)
	            	  pid = parent.getId();
	        	  service.getCategories(pid, callback);
	          }
	      };

	      // tree loader
	      loader = new BaseTreeLoader<Folder>(proxy) {
	          @Override
	          public boolean hasChildren(Folder parent) {
	              return parent.hasChild();
	          }
	      };
	     
	    TreeStore<Folder> store = new TreeStore<Folder>(loader);
	    RowNumberer numberer = new RowNumberer();
	    
	    ColumnConfig name = new ColumnConfig("name", "名称", 100);
	    name.setRenderer(new TreeGridCellRenderer<Folder>());
	    
	    ColumnConfig date = new ColumnConfig("author", "Author", 100);
	    ColumnConfig size = new ColumnConfig("genre", "Genre", 100);

	    ColumnModel cm = new ColumnModel(Arrays.asList(numberer, name, date, size));

	    ContentPanel cp = new ContentPanel();
	    cp.setBodyBorder(false);
	    cp.setHeading("商品类别");
	    cp.setButtonAlign(HorizontalAlignment.CENTER);
	    cp.setLayout(new FitLayout());
	    cp.setFrame(true);
	    cp.setSize(600, 300);

	    TreeGrid<Folder> tree = new TreeGrid<Folder>(store, cm);
	    tree.addPlugin(numberer);
	    tree.setBorders(true);
	    tree.getStyle().setLeafIcon(Stock.ICONS.music());
	    tree.setSize(400, 400);
	    tree.setAutoExpandColumn("name");
	    tree.setTrackMouseOver(false);
	    cp.add(tree);

	    add(cp);
	  }
	  
	}