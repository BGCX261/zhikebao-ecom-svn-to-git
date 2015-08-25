package com.xyz.system.dao;

import com.google.appengine.api.datastore.Key;
import com.xyz.framework.data.impl.JpaDao;
import com.xyz.system.model.Shop;

public class ShopDao extends JpaDao<Shop, String> implements IShopDao {

}
