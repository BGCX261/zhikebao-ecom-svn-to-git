package com.xyz.order.service.impl;

import java.util.List;
import java.util.logging.Level;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Service;

import com.xyz.framework.log.Logger;
import com.xyz.order.model.ProductStockHistory;
import com.xyz.order.service.IProductStockHistoryServ;

/**
 * Facade for entity ProductStockHistory.
 * 
 * @see com.sasa.ejb.bean.ProductStockHistory
 * @author sea
 */
@Service("productStockHistoryServ")
public class ProductStockHistoryServ implements IProductStockHistoryServ
{
	// property constants
	public static final String PRODUCT_KEY = "productKey";
	public static final String QUANTITY = "quantity";
	public static final String TBIID = "tbiid";
	public static final String IS_SUCCESS = "isSuccess";
	public static final String ERROR_MSG = "errorMsg";

	private EntityManager entityManager;

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
	public void save(ProductStockHistory entity) {
		Logger.info(getClass(),"saving ProductStockHistory instance");
		try {
			entityManager.persist(entity);
			Logger.info(getClass(),"save successful");
		} catch (RuntimeException re) {
			Logger.error(getClass(),"save failed");
			throw re;
		}
	}

	/**
	 * Delete a persistent ProductStockHistory entity.
	 * 
	 * @param entity
	 *            ProductStockHistory entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(ProductStockHistory entity) {
		Logger.info(getClass(),"deleting ProductStockHistory instance");
		try {
			//entity = entityManager.getReference(ProductStockHistory.class,entity.getPstockId());
			entityManager.remove(entity);
			Logger.info(getClass(),"delete successful");
		} catch (RuntimeException re) {
			Logger.error(getClass(),"delete failed");
			throw re;
		}
	}

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
	public ProductStockHistory update(ProductStockHistory entity) {
		Logger.info(getClass(),"updating ProductStockHistory instance");
		try {
			ProductStockHistory result = entityManager.merge(entity);
			Logger.info(getClass(),"update successful");
			return result;
		} catch (RuntimeException re) {
			Logger.error(getClass(),"update failed");
			throw re;
		}
	}

	public ProductStockHistory findById(Long id) {
		Logger.info(getClass(),"finding ProductStockHistory instance with id: " + id );
		try {
			ProductStockHistory instance = entityManager.find(
					ProductStockHistory.class, id);
			return instance;
		} catch (RuntimeException re) {
			Logger.error(getClass(),"find failed");
			throw re;
		}
	}

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
	 *            number of results to return.
	 * @return List<ProductStockHistory> found by query
	 */
	@SuppressWarnings("unchecked")
	public List<ProductStockHistory> findByProperty(String propertyName,
			final Object value, final int... rowStartIdxAndCount) {
		Logger.info(getClass(),"finding ProductStockHistory instance with property: "+ propertyName + ", value: " + value);
		try {
			final String queryString = "select model from ProductStockHistory model where model."
					+ propertyName + "= :propertyValue";
			Query query = entityManager.createQuery(queryString);
			query.setParameter("propertyValue", value);
			if (rowStartIdxAndCount != null && rowStartIdxAndCount.length > 0) {
				int rowStartIdx = Math.max(0, rowStartIdxAndCount[0]);
				if (rowStartIdx > 0) {
					query.setFirstResult(rowStartIdx);
				}

				if (rowStartIdxAndCount.length > 1) {
					int rowCount = Math.max(0, rowStartIdxAndCount[1]);
					if (rowCount > 0) {
						query.setMaxResults(rowCount);
					}
				}
			}
			return query.getResultList();
		} catch (RuntimeException re) {
			Logger.error(getClass(),"find by property name failed");
			throw re;
		}
	}

	public List<ProductStockHistory> findByProductKey(Object productKey,
			int... rowStartIdxAndCount) {
		return findByProperty(PRODUCT_KEY, productKey, rowStartIdxAndCount);
	}

	public List<ProductStockHistory> findByQuantity(Object quantity,
			int... rowStartIdxAndCount) {
		return findByProperty(QUANTITY, quantity, rowStartIdxAndCount);
	}

	public List<ProductStockHistory> findByTbiid(Object tbiid,
			int... rowStartIdxAndCount) {
		return findByProperty(TBIID, tbiid, rowStartIdxAndCount);
	}

	public List<ProductStockHistory> findByIsSuccess(Object isSuccess,
			int... rowStartIdxAndCount) {
		return findByProperty(IS_SUCCESS, isSuccess, rowStartIdxAndCount);
	}

	public List<ProductStockHistory> findByErrorMsg(Object errorMsg,
			int... rowStartIdxAndCount) {
		return findByProperty(ERROR_MSG, errorMsg, rowStartIdxAndCount);
	}

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
	@SuppressWarnings("unchecked")
	public List<ProductStockHistory> findAll(final int... rowStartIdxAndCount) {
		Logger.info(getClass(),"finding all ProductStockHistory instances");
		try {
			final String queryString = "select model from ProductStockHistory model";
			Query query = entityManager.createQuery(queryString);
			if (rowStartIdxAndCount != null && rowStartIdxAndCount.length > 0) {
				int rowStartIdx = Math.max(0, rowStartIdxAndCount[0]);
				if (rowStartIdx > 0) {
					query.setFirstResult(rowStartIdx);
				}

				if (rowStartIdxAndCount.length > 1) {
					int rowCount = Math.max(0, rowStartIdxAndCount[1]);
					if (rowCount > 0) {
						query.setMaxResults(rowCount);
					}
				}
			}
			return query.getResultList();
		} catch (RuntimeException re) {
			Logger.error(getClass(),"find all failed");
			throw re;
		}
	}

}