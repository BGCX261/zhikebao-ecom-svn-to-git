package com.xyz.system.client.mvc;

import com.extjs.gxt.ui.client.Registry;
import com.extjs.gxt.ui.client.Style.IconAlign;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.mvc.Dispatcher;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.VerticalPanel;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.xyz.system.client.General;
import com.xyz.system.client.GeneralServiceAsync;
import com.xyz.system.client.i18n.BaseMessages;

public class ToolView extends LayoutContainer {
	
	private VerticalPanel vp;
	  
	private GeneralServiceAsync service;
	private BaseMessages msg;
	
	protected void onRender(Element parent, int index) {
		super.onRender(parent, index);
		msg = (BaseMessages) Registry.get(General.MESSAGE);
		service = (GeneralServiceAsync) Registry.get(General.BASESERVICE);
		
		 vp = new VerticalPanel();
		 vp.setSpacing(10);
		 
		 Button btn = new Button();
		 btn.setIconAlign(IconAlign.BOTTOM);
		 btn.setText(msg.synctrades());
		 btn.setIcon(General.ICONS.reload());
		 
		 btn.addSelectionListener(new SelectionListener<ButtonEvent>() {
		      @Override
		      public void componentSelected(ButtonEvent ce) {
		    	   service.syncTrades(new AsyncCallback<Boolean>() {
					public void onSuccess(Boolean result) {
						if(result)
							Window.alert(msg.success());
					}
					public void onFailure(Throwable caught) {
						Dispatcher.forwardEvent(Events.OnError, caught);
					}
				});
		      }
		    });
		 vp.add(btn);
		 
		 Button cusbtn = new Button();
		 cusbtn.setIconAlign(IconAlign.BOTTOM);
		 cusbtn.setText(msg.synccuss());
		 cusbtn.setIcon(General.ICONS.reload());
		 
		 cusbtn.addSelectionListener(new SelectionListener<ButtonEvent>() {
		      @Override
		      public void componentSelected(ButtonEvent ce) {
		    	  service.syncCuss(new AsyncCallback<Boolean>() {
					public void onSuccess(Boolean result) {
						if(result)
							Window.alert(msg.success());
					}
					public void onFailure(Throwable caught) {
						Dispatcher.forwardEvent(Events.OnError, caught);
					}
				});
		      }
		    });
		 vp.add(cusbtn);
		 
		 Button ositembtn = new Button();
		 ositembtn.setIconAlign(IconAlign.BOTTOM);
		 ositembtn.setText(msg.syncositem());
		 ositembtn.setIcon(General.ICONS.reload());
		 
		 ositembtn.addSelectionListener(new SelectionListener<ButtonEvent>() {
		      @Override
		      public void componentSelected(ButtonEvent ce) {
		    	  service.synOnSaleItems(new AsyncCallback<Boolean>() {
					public void onSuccess(Boolean result) {
						if(result)
							Window.alert(msg.success());
					}
					public void onFailure(Throwable caught) {
						Dispatcher.forwardEvent(Events.OnError, caught);
					}
				});
		      }
		    });
		 vp.add(ositembtn);
		 
		 add(vp);
	}

}
