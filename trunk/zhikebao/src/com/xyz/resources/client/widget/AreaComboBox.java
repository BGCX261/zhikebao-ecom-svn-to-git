package com.xyz.resources.client.widget;

import java.util.List;

import com.extjs.gxt.ui.client.Registry;
import com.extjs.gxt.ui.client.data.BaseListLoader;
import com.extjs.gxt.ui.client.data.BeanModel;
import com.extjs.gxt.ui.client.data.BeanModelReader;
import com.extjs.gxt.ui.client.data.ListLoadResult;
import com.extjs.gxt.ui.client.data.ListLoader;
import com.extjs.gxt.ui.client.data.ModelData;
import com.extjs.gxt.ui.client.data.RpcProxy;
import com.extjs.gxt.ui.client.event.ComponentEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.form.ComboBox;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.xyz.resources.model.Area;
import com.xyz.system.client.General;
import com.xyz.system.client.GeneralServiceAsync;

public class AreaComboBox extends ComboBox<BeanModel>
{
	private GeneralServiceAsync service;
	
	private AreaComboBox parent;
	
	private ListLoader<ListLoadResult<ModelData>> loader;
	
	private TextField<String> code;//地区编码
	
	private TextField<String> zip;//邮政编码

	public AreaComboBox(String name,String label)
	{

		service = (GeneralServiceAsync) Registry.get(General.BASESERVICE);
		 // proxy and reader  
		 RpcProxy<List<Area>> proxy = new RpcProxy<List<Area>>() {  
		        @Override  
		        public void load(Object loadConfig, AsyncCallback<List<Area>> callback) {
		        	  Integer parentId = 1;
		        	  if(parent!=null)
		            	{
		            		AreaComboBox acb = (AreaComboBox)parent;  
			                BeanModel bm = acb.getValue();
			        		if(bm!=null)
			        		{
			        			Area parea = (Area)bm.getBean();
			        			parentId = new Integer(parea.getAreaId());
			        		}
		            	}	
		        	 service.getAreaList(parentId, callback);
		       }  
		     };  
	    BeanModelReader reader = new BeanModelReader();  
	   
	     // loader and store  
	    loader = new BaseListLoader<ListLoadResult<ModelData>>(proxy, reader);  
	   
	    ListStore<BeanModel> store = new ListStore<BeanModel>(loader);  
	      
		setFieldLabel(label);
		setName(name);
		setForceSelection(true);  
		setDisplayField("areaName");
		setValueField("areaId");
		setTypeAhead(true);
		setStore(store);
		addListener(Events.Select,new Listener<ComponentEvent>() {  
			    @SuppressWarnings("unchecked")  
		        public void handleEvent(ComponentEvent be) {  
			    	AreaComboBox acb = (AreaComboBox) be.getComponent();  
	                BeanModel bm = acb.getValue();
	        		Area parea = (Area)bm.getBean();
	        		if(code!=null)
	        			code.setValue(parea.getAreaId());
	        		if(zip!=null)
	        			zip.setValue(parea.getZip());
	        		
			    }
			});
		//setMinChars(2);
		//setHideTrigger(true);
	}

	public ListLoader<ListLoadResult<ModelData>> getLoader() {
		return loader;
	}

	public AreaComboBox getParent() {
		return parent;
	}

	public void setParent(AreaComboBox parent) {
		this.parent = parent;
	}

	public TextField<String> getCode() {
		return code;
	}

	public void setCode(TextField<String> code) {
		this.code = code;
	}

	public TextField<String> getZip() {
		return zip;
	}

	public void setZip(TextField<String> zip) {
		this.zip = zip;
	}

	
}
