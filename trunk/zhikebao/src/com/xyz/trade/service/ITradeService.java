package com.xyz.trade.service;

import java.util.List;
import java.util.Map;

import com.xyz.resources.model.Result;
import com.xyz.trade.model.Delivery;
import com.xyz.trade.model.LogisticCompany;
import com.xyz.trade.model.OrderModel;
import com.xyz.trade.model.ShipAddress;
import com.xyz.trade.model.TradeModel;
import com.xyz.util.Page;
import com.xyz.util.PropertyFilter;

/**
 * Local interface for BossiniTradeFacade.
 * 
 * @author sea
 */
public interface ITradeService {

	/**
	 * 
	 */
	public void save(TradeModel entity);

	/**
	 * Delete a entity
	 */
	public void delete(String entity);

	/**
	 * @param entity
	 */
	public TradeModel update(TradeModel entity);

	public TradeModel findById(String id);

	/**
	 * Find all
	 */
	@SuppressWarnings("unchecked")
	public Page<TradeModel> findByProperty(String propertyName, final Object value, Page p);
	
    /**
     * 根据交易状态分页查询
     * @param status
     * @param page
     * @return
     */
	public Page<TradeModel> findByStatus(Object status,Page page);
	/**
	 * Find all Order entities.
	 * 
	 */
	public Page<TradeModel> findAll(Page page);

	@SuppressWarnings("unchecked")
	public Page<TradeModel> query(Page page, String sqlString);

	public TradeModel querySingle(String sqlString);

	@SuppressWarnings("unchecked")
	public List<Object> query(String sqlString);

	@SuppressWarnings("unchecked")
	public Page<TradeModel> findManyByProperty(Object[] propertyName, Object[] value, Page page);

	public Integer findManyByPropertySize(Object[] propertyName, Object[] value);

	/**
	 * 分页查询指定卖家的交易列表
	 * @param page
	 * @return
	 */
	public Page<TradeModel> queryPage(Page<TradeModel> page, List<PropertyFilter> filters);
	
	/**
	 * 分页查询指定卖家的发货地址列表
	 * @param page
	 * @return
	 */
	public Page<ShipAddress> queryShipAddrPage(Page<ShipAddress> page, List<PropertyFilter> filters);
	
	/**
	 * 查询指定卖家的发货地址列表
	 * @param page
	 * @return
	 */
	public List<ShipAddress> queryShipAddrs();
	
	public Long findByPropertySize(String propertyName, Object value);

	public void addSortDesc(String propertyName);

	public void addSortAsc(String propertyName);
	/**
	 * 统计交易信息
	 * @return
	 */
	public Map<String,String> countTrades();

	/**
	 * 删除所有交易
	 */
	public void deleteAll();
	/**
	 * 将交易分配给指定的人员
	 * @param tradeKeys
	 * @param userKey
	 * @return
	 */
	public boolean assignOwner(Integer[] tradeKeys, Integer userKey);
	
	/**
	 * 查询交易包含订单
	 */
    public List<OrderModel> getOrdersOfTrade(String tid);
    
    /**
     * 查询物流公司列表
     */
    public List<LogisticCompany> getLogisticCompanies();
    /**
     * 发货处理
     * @param deli
     * @return
     */
	public Result deliverySend(Delivery deli) ;

	/**
	 * 定时获取最新的交易信息
	 */
	public void tradeSysn();
	
	/**
	 * 定时或手动获取最新的退款申请列表信息
	 */
	public void refundSysn();

	/**
	 * 一次性完全获取全部的交易信息
	 */
	public void tradeFullSysn();
	
	/**
	 * 保存发货地址信息
	 */
	public void saveShipAddress(ShipAddress sa);
	
	/**
	 * 获取发货地址信息
	 */
	public ShipAddress getShipAddress(Integer pid);
	
	
	/**
	 * 保存发货地址中常用的物流公司信息
	 */
	public boolean saveLogisticsOfShipAddress(Integer saId, List<Integer> lr);
}