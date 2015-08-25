package com.xyz.order.service;

import java.util.List;
import com.xyz.order.model.ProductStockHistory;

/**
 * Local interface for ProductStockHistoryFacade.
 * 
 * @author sea
 */
public interface IProductStockHistoryServ {
	/**
	 * Perform an initial save of a previously unsaved ProductStockHistory
	 * entity. All subsequent persist actions of this entity should use the
	 * #update() method.
	 * 
	 * @param entity
	 *            ProductStockHistory entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(ProductStockHistory entity);

	/**
	 * Delete a persistent ProductStockHistory entity.
	 * 
	 * @param entity
	 *            ProductStockHistory entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(ProductStockHistory entity);

	/**
	 * Persist a previously saved ProductStockHistory entity and return it or a
	 * copy of it to the sender. A copy of the ProductStockHistory entity
	 * parameter is returned when the JPA persistence mechanism has not
	 * previously been tracking the updated entity.
	 * 
	 * @param entity
	 *            ProductStockHistory entity to update
	 * @return ProductStockHistory the persisted ProductStockHistory entity
	 *         instance, may not be the same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public ProductStockHistory update(ProductStockHistory entity);

	public ProductStockHistory findById(Long id);

	/**
	 * Find all ProductStockHistory entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the ProductStockHistory property to query
	 * @param value
	 *            the property value to match
	 * @param rowStartIdxAndCount
	 *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
	 *            row index in the query result-set to begin collecting the
	 *            results. rowStartIdxAndCount[1] specifies the the maximum
	 *            count of results to return.
	 * @return List<ProductStockHistory> found by query
	 */
	public List<ProductStockHistory> findByProperty(String propertyName, Object value, int... rowStartIdxAndCount);

	public List<ProductStockHistory> findByProductKey(Object productKey, int... rowStartIdxAndCount);

	public List<ProductStockHistory> findByQuantity(Object quantity, int... rowStartIdxAndCount);

	public List<ProductStockHistory> findByTbiid(Object tbiid, int... rowStartIdxAndCount);

	public List<ProductStockHistory> findByIsSuccess(Object isSuccess, int... rowStartIdxAndCount);

	public List<ProductStockHistory> findByErrorMsg(Object errorMsg, int... rowStartIdxAndCount);

	/**
	 * Find all ProductStockHistory entities.
	 * 
	 * @param rowStartIdxAndCount
	 *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
	 *            row index in the query result-set to begin collecting the
	 *            results. rowStartIdxAndCount[1] specifies the the maximum
	 *            count of results to return.
	 * @return List<ProductStockHistory> all ProductStockHistory entities
	 */
	public List<ProductStockHistory> findAll(int... rowStartIdxAndCount);
}