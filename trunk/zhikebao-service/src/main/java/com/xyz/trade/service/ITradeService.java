package com.xyz.trade.service;

import java.util.List;
import java.util.Map;

import com.taobao.api.model.Item;
import com.taobao.api.model.Trade;
import com.xyz.util.Page;
import com.xyz.util.PropertyFilter;

/**
 * Local interface for BossiniTradeFacade.
 * 
 * @author sea
 */
public interface ITradeService {

	/**
	 * Perform an initial save of a previously unsaved BossiniTrade entity. All
	 * subsequent persist actions of this entity should use the #update()
	 * method.
	 * 
	 * @param entity
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(Trade entity);

	/**
	 * Delete a persistent BossiniTrade entity.
	 * 
	 * @param entity
	 *            BossiniTrade entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(Trade entity);

	/**
	 * Persist a previously saved BossiniTrade entity and return it or a copy of
	 * it to the sender. A copy of the BossiniTrade entity parameter is returned
	 * when the JPA persistence mechanism has not previously been tracking the
	 * updated entity.
	 * 
	 * @param entity
	 *            BossiniTrade entity to update
	 * @return BossiniTrade the persisted BossiniTrade entity instance, may not
	 *         be the same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public Trade update(Trade entity);

	public Trade findById(String id);

	/**
	 * Find all BossiniOrder entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the BossiniOrder property to query
	 * @param value
	 *            the property value to match
	 * @param rowStartIdxAndCount
	 *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
	 *            row index in the query result-set to begin collecting the
	 *            results. rowStartIdxAndCount[1] specifies the the maximum
	 *            number of results to return.
	 * @return List<BossiniOrder> found by query
	 */
	@SuppressWarnings("unchecked")
	public Page<Trade> findByProperty(String propertyName, final Object value, Page p);
	
    /**
     * 根据交易状态分页查询
     * @param status
     * @param page
     * @return
     */
	public Page<Trade> findByStatus(Object status,Page page);
	/**
	 * Find all BossiniOrder entities.
	 * 
	 * @param rowStartIdxAndCount
	 *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
	 *            row index in the query result-set to begin collecting the
	 *            results. rowStartIdxAndCount[1] specifies the the maximum
	 *            count of results to return.
	 * @return List<BossiniOrder> all BossiniOrder entities
	 */
	@SuppressWarnings("unchecked")
	public Page<Trade> findAll(Page page);

	@SuppressWarnings("unchecked")
	public Page<Trade> query(Page page, String sqlString);

	public Trade querySingle(String sqlString);

	@SuppressWarnings("unchecked")
	public List<Object> query(String sqlString);

	@SuppressWarnings("unchecked")
	public Page<Trade> findManyByProperty(Object[] propertyName, Object[] value, Page page);

	public Integer findManyByPropertySize(Object[] propertyName, Object[] value);

	/**
	 * 分页查询指定卖家的交易列表
	 * @param page
	 * @return
	 */
	public Page<Trade> queryPage(Page<Trade> page, List<PropertyFilter> filters);

	
	public Long findByPropertySize(String propertyName, Object value);

	public void addSortDesc(String propertyName);

	public void addSortAsc(String propertyName);
	/**
	 * 统计交易信息
	 * @return
	 */
	public Map<String,String> countTrades();

	
}