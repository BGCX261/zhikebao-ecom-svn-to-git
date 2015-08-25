package com.xyz.product.service;

import java.util.List;

import com.taobao.api.model.Item;
import com.xyz.framework.log.Logger;

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


}
