package com.xyz.main.client.view;

import java.util.List;
import java.util.Map;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.JsArray;
import com.google.gwt.maps.client.overlay.Marker;
import com.extjs.gxt.ui.client.Registry;
import com.extjs.gxt.ui.client.Style.Direction;
import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.event.IconButtonEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.fx.FxConfig;
import com.extjs.gxt.ui.client.mvc.AppEvent;
import com.extjs.gxt.ui.client.mvc.Dispatcher;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.Dialog;
import com.extjs.gxt.ui.client.widget.Header;
import com.extjs.gxt.ui.client.widget.MessageBox;
import com.extjs.gxt.ui.client.widget.button.ToolButton;
import com.extjs.gxt.ui.client.widget.custom.Portlet;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.extjs.gxt.ui.client.widget.toolbar.ToolBar;
import com.google.gwt.maps.client.InfoWindow;
import com.google.gwt.maps.client.InfoWindowContent;
import com.google.gwt.maps.client.MapWidget;
import com.google.gwt.maps.client.control.LargeMapControl;
import com.google.gwt.maps.client.geocode.Geocoder;
import com.google.gwt.maps.client.geocode.LocationCallback;
import com.google.gwt.maps.client.geocode.Placemark;
import com.google.gwt.maps.client.geom.LatLng;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.xyz.customer.model.Guest;
import com.xyz.main.client.Main;
import com.xyz.main.client.MainServiceAsync;
import com.xyz.resources.client.AppEvents;

public class MapPortlet extends Portlet {

	 private MainServiceAsync service;
	 
	 private MapWidget map;
	 private Geocoder geocoder;
	 
	 
	public MapPortlet() {
		service = (MainServiceAsync) Registry.get(Main.SERVICE);
		
		geocoder = new Geocoder();
		 
		setHeading("商城实时访问情况(在大赛环境下还不能取得真实同步数据)");
	    LatLng china = LatLng.newInstance(30.54,114.34);
	    // Open a map centered on Cawker City, KS USA

	    map = new MapWidget(china, 6);
	    map.setSize("100%", "100%");
	    // Add some controls for the zoom level
	    map.addControl(new LargeMapControl());
	    
	    // Add a marker
	    //map.addOverlay(new Marker(china));

	    // Add an info window to highlight a point of interest
	    //map.getInfoWindow().open(map.getCenter(), new InfoWindowContent("顾客访问位置图"));
	    
	    add(map);
	    runAction();
	    guestListener();
	    configPanel(this);
	    setHeight(360);
	}
	
	private void guestListener() {
		//开始重复轮询，获取最新的访客信息
		Timer timer = new Timer() {
            public void run() {
            	runAction();
			}
		};
		//timer.scheduleRepeating(1000 * 10);
	}
	
	private void runAction()
	{
		service.getNewestGuests(new AsyncCallback<List<Guest>>() {
			public void onSuccess(List<Guest> guests) {
				if(guests!=null&&guests.size()>0)
				{
					for(Guest guest : guests)
					{
						showInfo(guest);
					}
			    }  
			}
            public void onFailure(Throwable caught) {
				Dispatcher.forwardEvent(AppEvents.Error, caught);
			}
		});
	}
	
	private void showInfo(final Guest guest) {
	    final InfoWindow info = map.getInfoWindow();
	    String address = guest.getCity();
	    if(address==null)
	    	address = guest.getState();
	    if(address!=null)
	    {
	      geocoder.getLocations(address, new LocationCallback() {
	        public void onFailure(int statusCode) {
	           Window.alert("");
	      }

	      public void onSuccess(JsArray<Placemark> locations) {
	        Placemark place = locations.get(0);
	        Marker marker = new Marker(place.getPoint());
	        map.addOverlay(marker);
	        info.open(marker, displayInfoWindowMaxWidget(guest));
	      }
	    });
	    }else{
	    	displayInfoWindowMaxWidget(guest);
	    }
	  }
	
	  private InfoWindowContent displayInfoWindowMaxWidget(Guest guest) {
		    VerticalPanel panel = new VerticalPanel();
		    String gstr = guest.getGoods();
		    if(gstr!=null&&gstr.length()>24)
		    	gstr =gstr.substring(0,24)+"...";
		    StringBuilder str = new StringBuilder("<div id=\"googlemap\"><h1>"+guest.getNick());
		    str.append("<a href=\"http://amos1.taobao.com/msg.ww?v=2&uid="+guest.getNick()+"&s=1\" target=\"_blank\">");
		    str.append("<img alt=\"开始聊天\" src=\"http://amos1.taobao.com/online.ww?v=2&uid="+guest.getNick()+"&s=1\"/></a></h1>");
		    str.append("<p>"+guest.getState()+guest.getCity()+"</p>");
		    str.append("<span>正在浏览</span>:<p>"+gstr+"</p></div>");
		    HTML html = new HTML(str.toString());
		    panel.add(html);
		    return new InfoWindowContent(panel);
		  }

		private void configPanel(final ContentPanel panel) {
			panel.setCollapsible(true);
			panel.setAnimCollapse(true);
		
			panel.getHeader().addTool(
					new ToolButton("x-tool-close",
							new SelectionListener<IconButtonEvent>() {

								@Override
								public void componentSelected(IconButtonEvent ce) {
									panel.removeFromParent();
								}

				}));
		}

}
