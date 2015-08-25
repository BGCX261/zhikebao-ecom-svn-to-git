package com.xyz.product.dao;

import com.xyz.framework.data.impl.JpaDao;
import com.xyz.product.model.Item;

public class ItemDao extends JpaDao<Item, Integer> implements IItemDao {

}
