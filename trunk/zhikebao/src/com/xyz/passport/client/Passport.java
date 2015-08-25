package com.xyz.passport.client;

import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.Viewport;
import com.extjs.gxt.ui.client.widget.Window;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.CheckBox;
import com.extjs.gxt.ui.client.widget.form.FormPanel;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Passport implements EntryPoint {

   public void onModuleLoad() {
		 RootPanel rootPanel = RootPanel.get("viewport");
		 final Window winModal = new Window();
		 //winModal.setPosition(300, 300);
         winModal.setWidth(400);
         winModal.setHeight(180);
         winModal.setTitle("用户登陆");
         winModal.setClosable(false);
         winModal.setLayout(new FitLayout());  
         //winModal.render(rootPanel.getElement());
     
         final FormPanel form = new FormPanel();
         form.setButtonAlign(HorizontalAlignment.CENTER);
         //form.setWidth(200);
         form.setPadding(5);
         //LayoutContainer lc = new LayoutContainer();
         TextField<String> userItem = new TextField<String>();
         userItem.setName("j_username");
         userItem.setFieldLabel("帐号");
         userItem.setAllowBlank(false);
         form.add(userItem);
         
         TextField<String> pwdItem = new TextField<String>();
         pwdItem.setName("j_password");
         pwdItem.setFieldLabel("密码");
         pwdItem.setAllowBlank(false);
         form.add(pwdItem);

         CheckBox remItem  = new CheckBox();
         remItem.setName("_spring_security_remember_me");
         remItem.setBoxLabel("两周内记住我");
         remItem.setHideLabel(true);
         form.add(remItem);
        
         Button subItem = new Button("登陆",new SelectionListener<ButtonEvent>() {
			@Override
			public void componentSelected(ButtonEvent ce) {
				form.setAction("/zhikebao/j_spring_security_check");
				
				form.submit();
			}
	    });
         //subItem.setWidth(50);
         form.add(subItem);
         //form.add(lc);
         
         winModal.add(form);
         winModal.show();
         rootPanel.add(winModal);
	}
}
