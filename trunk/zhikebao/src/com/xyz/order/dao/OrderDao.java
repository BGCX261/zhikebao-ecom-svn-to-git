package com.xyz.order.dao;

import com.xyz.framework.data.impl.JpaDao;
import com.xyz.trade.model.OrderModel;

public class OrderDao extends JpaDao<OrderModel, Integer> implements IOrderDao {

}
