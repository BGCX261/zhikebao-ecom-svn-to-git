package com.xyz.order.dao;

import com.taobao.api.model.Order;
import com.xyz.framework.data.impl.JpaDao;

public class OrderDao extends JpaDao<Order, String> implements IOrderDao {

}
