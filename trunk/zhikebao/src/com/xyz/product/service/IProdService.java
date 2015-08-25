package com.xyz.product.service;

import java.util.List;

import com.xyz.product.model.Item;
import com.xyz.util.Page;
import com.xyz.util.PropertyFilter;

public interface IProdService {
	
	public List getCategories(String parentId);

	/**
	 * 根据Item的id查询
	 */
	public Item querySingleItem(String sqlString);

	/**
	 * 保存商品信息
	 * @param entity
	 */
	public void save(Item entity);
	/**
	 * 同步在售商品列表
	 * @return
	 */
	public boolean syncItems();

	/**
	 * 同步在售商品
	 */
	public boolean syncOnSaleItems();

	/**
	 * 分页查询指定卖家的在售商品列表列表
	 * @param page
	 * @return
	 */
	public Page<Item> queryPage(Page<Item> page, List<PropertyFilter> filters);


}
