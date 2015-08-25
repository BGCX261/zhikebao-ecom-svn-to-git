package com.xyz.product.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.taobao.api.TaobaoJsonRestClient;
import com.taobao.api.TaobaoRestClient;
import com.taobao.api.model.Item;
import com.taobao.api.model.ItemCat;
import com.taobao.api.model.ItemCatsGetRequest;
import com.taobao.api.model.ItemCatsResponse;
import com.taobao.api.util.DateUtil;
import com.xyz.framework.log.Logger;
import com.xyz.product.dao.IItemDao;
import com.xyz.product.service.IProdService;
import com.xyz.system.model.Shop;
import com.xyz.system.model.User;
import com.xyz.util.SpringSecurityUtil;

@Service
public class ProdService implements IProdService {
	
	@Autowired
	public IItemDao idao;

	@Override
	public List getCategories(String parentId) {
		TaobaoRestClient client;
		try {
			
			Shop shop = SpringSecurityUtil.getShop();
			Assert.notNull(shop, "taobaoUser不能为空");
			System.out.println("查询所有的商品分类");
			client = new TaobaoJsonRestClient("http://gw.api.tbsandbox.com/router/rest", shop
							.getAppKey(), shop.getAppSecret());

			ItemCatsGetRequest req = new ItemCatsGetRequest();
			req.setFields("cid,name,status,sort_order,parent_cid,is_parent");
			req.setParentCid(parentId);
			req.setDatetime(DateUtil.strToDate("1970-1-1 00:00:00"));

			ItemCatsResponse rsp = client.itemCatsGet(req);
			List<ItemCat> cats = rsp.getItemCats();
			for (ItemCat cat : cats) {
				System.out.println("cid: " + cat.getCid());
				System.out.println("类目名称: " + cat.getName());
				System.out.println("父类目cid: " + cat.getParentCid());
				System.out.println("是否包含子类目: " + cat.getIsParent());
			}
			return cats;
		} catch (Exception e) {
			Logger.error(getClass(), e.getStackTrace().toString());
		}
		return null;
	}
	/**
	 * 保存商品信息
	 * @param entity
	 */
	public void save(Item entity) {
		Logger.info(getClass(),"保存商品信息");
		try {
			idao.save(entity);
			Logger.info(getClass(),"保存成功");
		} catch (RuntimeException re) {
			Logger.error(getClass(),"save failed");
			throw re;
		}
	}
	
	/**
     * 根据Item的id查询
     */
	public Item querySingleItem(String sqlString) {
		try {
			return idao.findUnique(sqlString);
		} catch (RuntimeException re) {
			Logger.info(getClass(), "没有查询到结果");
		}
		return null;
	}

}
