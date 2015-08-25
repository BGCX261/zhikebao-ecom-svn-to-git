package com.xyz.order.service;

import java.util.List;

import com.xyz.trade.model.OrderModel;
import com.xyz.util.Page;

/**
 * Local interface for OrderFacade.
 * 
 * @author val
 */
public interface IOrderService {
	/**
	 * Perform an initial save of a previously 
	 * 
	 * @param entity Order entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(OrderModel entity);

	/**
	 * Delete a persistent Order entity.
	 * 
	 * @param entity Order entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(OrderModel entity);

	/**
	 * Persist a previously saved Order entity 
	 * 
	 * @param entity Order entity to update
	 * @return  Order 
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public OrderModel update(OrderModel entity);

	public OrderModel findById(Long id);

	/**
	 * Find all Order entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the BossiniOrder property to query
	 * @param value
	 *            the property value to match
	 * @param rowStartIdxAndCount
	 *          
	 * @return List<Order> found by query
	 */
	public Page<OrderModel> findByProperty(String propertyName, Object value, Page page);

	
	/**
	 * Find all Order entities.
	 * 
	 * @param rowStartIdxAndCount
	 *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
	 *            row index in the query result-set to begin collecting the
	 *            results. rowStartIdxAndCount[1] specifies the the maximum
	 *            count of results to return.
	 * @return List<OrderModel> all Order entities
	 */
	public Page<OrderModel> findAll(Page page);

	/**
	 * 根据SQL语句获取记录
	 * 
	 * @param sql
	 *            SQL语句
	 * @param startRecord
	 *            开始记录数
	 * @param maxRecord
	 *            返回最大记录数
	 */
	public Page<OrderModel> query(Page page,String sqlString);

	/**
	 * 执行聚合查询
	 * 
	 * @param sqlString
	 * @return 单值
	 */
	public Object querySingle(String sqlString);

	/**
	 * 查找指定返回字段值
	 * 
	 * @param sqlString
	 * @return 字段值
	 */
	public List<Object> query(String sqlString);

	/**
	 * 多字段等值查询
	 * 
	 * @param propertyName
	 *            字段数组
	 * @param value
	 *            字段值数组
	 * @param page
	 *            任择整数varargs 。 rowStartIdxAndCount [ 0 ]指定的行指数在查询结果集，开始收集的结果。
	 *            rowStartIdxAndCount [ 1 ]指定的最高计数结果返回。
	 * @return 符合条件CallRequest实体
	 */
	public Page<OrderModel> findManyByProperty(Object[] propertyName, Object[] value,Page page);

	/**
	 * 找到所有实体的具体属性值返回记录总数
	 * 
	 * @param propertyName
	 * @param value
	 * @return
	 */
	public Long findByPropertySize(String propertyName, Object value);

	/**
	 * 找到所有实体的具体属性值数组返回记录总数
	 * 
	 * @param propertyName
	 * @param value
	 * @return
	 */
	public Integer findManyByPropertySize(Object[] propertyName, Object[] value);

	/**
	 * 添加降序属性
	 * 
	 * @param propertyName
	 */
	public void addSortDesc(String propertyName);

	/**
	 * 添加升序属性
	 * 
	 * @param propertyName
	 */
	public void addSortAsc(String propertyName);

	/**
	 * 清空所有排序属性
	 * 
	 * @param propertyName
	 */
	public void clear();
}