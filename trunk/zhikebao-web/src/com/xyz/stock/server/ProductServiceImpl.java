package com.xyz.stock.server;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.extjs.gxt.ui.client.data.BasePagingLoadResult;
import com.extjs.gxt.ui.client.data.PagingLoadConfig;
import com.extjs.gxt.ui.client.data.PagingLoadResult;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.taobao.api.model.Item;
import com.taobao.api.model.ItemCat;
import com.taobao.api.model.Trade;
import com.xyz.product.service.IProdService;
import com.xyz.resources.client.model.Folder;
import com.xyz.stock.client.ProductService;
import com.xyz.stock.client.model.ItemModel;
import com.xyz.trade.client.model.OrderModel;
import com.xyz.trade.client.model.TradeModel;
import com.xyz.util.Page;
import com.xyz.util.PropertyFilter;

@Service
public class ProductServiceImpl extends RemoteServiceServlet implements ProductService {
    
	private static final long serialVersionUID = 1L;
	@Autowired
	private IProdService ps;
    
	@Override
	public List<Folder> getCategories(String parentId) {
		List<ItemCat> cats = ps.getCategories(parentId);
		List<Folder> lf = new ArrayList<Folder>();
		for(ItemCat ic : cats)
		{
			Folder f = new Folder(ic.getName(),ic.getCid(),ic.getIsParent());
			lf.add(f);
		}
		return lf;
	}

	@Override
	public PagingLoadResult<ItemModel> getOnsaleItems(
			PagingLoadConfig config, ItemModel im) {
		Page<Item> page = new Page<Item>(config.getLimit());
		page.setStart(config.getOffset());
		List<PropertyFilter> filters = new ArrayList<PropertyFilter>();
		/*
		 *设置查询条件
		 **/
		if(im!=null)
		{
			if(im.getTitle()!=null&&im.getTitle().trim().length()>0)
			    filters.add(new PropertyFilter("EQ_title",im.getTitle().trim()));
		}
		page = ps.queryPage(page, filters);
		List<ItemModel> lf = new ArrayList<ItemModel>();
		if(page.getResult()!=null&&page.getResult().size()>0)
		{
			for(Item ic : page.getResult())
			{
				ItemModel f = new ItemModel();
				BeanUtils.copyProperties(ic, f,new String[]{"propImgs","videos","itemImgs","skus"});
				lf.add(f);
			}
	    }
		return new BasePagingLoadResult<ItemModel>(lf, config.getOffset(),
				page.getTotalCount());
	}

}
