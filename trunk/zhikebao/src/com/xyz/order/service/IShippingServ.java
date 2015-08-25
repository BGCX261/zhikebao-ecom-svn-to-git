package com.xyz.order.service;

import java.util.List;

import com.xyz.order.model.Shipping;

/**
 * Local interface for ShippingFacade.
 * 
 * @author sea
 */
public interface IShippingServ {
	/**
	 * Perform an initial save of a previously unsaved Shipping entity. All
	 * subsequent persist actions of this entity should use the #update()
	 * method.
	 * 
	 * @param entity
	 *            Shipping entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(Shipping entity);
	/**
	 * Delete a persistent Shipping entity.
	 * 
	 * @param entity
	 *            Shipping entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(Shipping entity);
	/**
	 * Persist a previously saved Shipping entity and return it or a copy of it
	 * to the sender. A copy of the Shipping entity parameter is returned when
	 * the JPA persistence mechanism has not previously been tracking the
	 * updated entity.
	 * 
	 * @param entity
	 *            Shipping entity to update
	 * @return Shipping the persisted Shipping entity instance, may not be the
	 *         same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public Shipping update(Shipping entity);
	public Shipping findById(Long id);
	/**
	 * Find all Shipping entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the Shipping property to query
	 * @param value
	 *            the property value to match
	 * @param rowStartIdxAndCount
	 *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
	 *            row index in the query result-set to begin collecting the
	 *            results. rowStartIdxAndCount[1] specifies the the maximum
	 *            count of results to return.
	 * @return List<Shipping> found by query
	 */
	public List<Shipping> findByProperty(String propertyName, Object value, int... rowStartIdxAndCount);
	public List<Shipping> findByOutSid(Object outSid, int... rowStartIdxAndCount);
	public List<Shipping> findByCompanyName(Object companyName, int... rowStartIdxAndCount);
	public List<Shipping> findByCompanyCode(Object companyCode, int... rowStartIdxAndCount);
	public List<Shipping> findByIsSuccess(Object isSuccess, int... rowStartIdxAndCount);
	public List<Shipping> findByErrorMsg(Object errorMsg, int... rowStartIdxAndCount);
	/**
	 * Find all Shipping entities.
	 * 
	 * @param rowStartIdxAndCount
	 *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
	 *            row index in the query result-set to begin collecting the
	 *            results. rowStartIdxAndCount[1] specifies the the maximum
	 *            count of results to return.
	 * @return List<Shipping> all Shipping entities
	 */
	public List<Shipping> findAll(int... rowStartIdxAndCount);
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
	public List<Shipping> query(String sqlString, Integer startRecord, Integer maxRecord);

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
	public List<Object[]> query(String sqlString);

	/**
	 * 多字段等值查询
	 * 
	 * @param propertyName
	 *            字段数组
	 * @param value
	 *            字段值数组
	 * @param rowStartIdxAndCount
	 *            任择整数varargs 。 rowStartIdxAndCount [ 0 ]指定的行指数在查询结果集，开始收集的结果。
	 *            rowStartIdxAndCount [ 1 ]指定的最高计数结果返回。
	 * @return 符合条件CallRequest实体
	 */
	public List<Shipping> findManyByProperty(Object[] propertyName, Object[] value, int... rowStartIdxAndCount);

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
	 * 找到某个属于值
	 * 
	 * @param propertyName
	 * @param value
	 * @return
	 */
	public List<Object> queryObjectList(String sqlString);

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