/*
 * Ext GWT - Ext for GWT
 * Copyright(c) 2007-2009, Ext JS, LLC.
 * licensing@extjs.com
 * 
 * http://extjs.com/license
 */
package com.xyz.main.server;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.extjs.gxt.ui.client.data.BasePagingLoadResult;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.xyz.main.client.MainService;
import com.xyz.main.client.widget.ViewModel;
import com.xyz.resources.client.model.Category;
import com.xyz.resources.client.model.Entry;
import com.xyz.resources.client.model.Folder;
import com.xyz.resources.client.model.Item;
import com.xyz.system.client.mvc.UserModel;
import com.xyz.system.model.Resource;
import com.xyz.system.model.Shop;
import com.xyz.system.model.User;
import com.xyz.system.service.ISecurityService;
import com.xyz.system.task.ISysTask;
import com.xyz.util.Page;
import com.xyz.util.PropertyFilter;
import com.xyz.util.SpringSecurityUtil;

@Service
public class MainServiceImpl extends RemoteServiceServlet implements
		MainService {

	@Autowired
	private ISecurityService secuServ;

	@Autowired
	@Qualifier("systemTask")
	private ISysTask authTask;

	@Autowired
	private ISecurityService ss;

	private ViewModel model;

	public MainServiceImpl() {
	}

	public ViewModel getModels() {
		model = new ViewModel();
		Map<Resource, List<Resource>> lr = secuServ.findMenus();
		List<Resource> parentList = new ArrayList<Resource>(lr.keySet());

		Collections.sort(parentList);
		for (Resource r : parentList) {
			Category cate = new Category(r.getName(), Integer.toString(r
					.getSerial()));
			List<Resource> cr = lr.get(r);
			List<Folder> cli = new ArrayList<Folder>();
			for (Resource c : cr) {
				Entry child = new Entry(c.getName(), Integer.toString(c
						.getSerial()));
				cate.add(child);
			}
			model.add(cate);
		}
		return model;
	}

	public List<Item> getItems(Folder folder) {
		String name = folder.getName();

		return new ArrayList<Item>();
	}

	@Override
	public Boolean validateSession() {
		// 每隔八分钟刷新一次会话
		authTask.holdAuthorize();
		User user = SpringSecurityUtil.getCurrentUser();
		boolean success = true;
		if (user == null) {
			success = false;
		}
		Shop shop = user.getShop();
		if (shop == null || (!shop.getIsAuthorize())) {
			success = false;
		}

		return success;
	}

	@Override
	public String getUserNick() {
		User user = SpringSecurityUtil.getCurrentUser();
		return user.getUsername();
	}

}
