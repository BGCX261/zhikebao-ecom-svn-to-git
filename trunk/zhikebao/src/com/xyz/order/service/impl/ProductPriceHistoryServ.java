package com.xyz.order.service.impl;

import java.util.List;
import java.util.logging.Level;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Service;

import com.xyz.framework.log.Logger;
import com.xyz.order.model.ProductPriceHistory;
import com.xyz.order.service.IProductPriceHistoryServ;

/**
 * Facade for entity ProductPriceHistory.
 * 
 * @see com.sasa.ejb.bean.ProductPriceHistory
 * @author sea
 */
@Service("productPriceHistoryServ")
public class ProductPriceHistoryServ implements IProductPriceHistoryServ {
	// property constants
	public static final String PRODUCT_KEY = "productKey";
	public static final String PRICE = "price";
	public static final String TBIID = "tbiid";
	public static final String IS_SUCCESS = "isSuccess";
	public static final String ERROR_MSG = "errorMsg";

	private EntityManager entityManager;

	/**
	 * Perform an initial save of a previously unsaved ProductPriceHistory
	 * entity. All subsequent persist actions of this entity should use the
	 * #update() method.
	 * 
	 * @param entity
	 *            ProductPriceHistory entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(ProductPriceHistory entity) {
		Logger.info(getClass(),"saving ProductPriceHistory instance");
		try {
			entityManager.persist(entity);
			Logger.info(getClass(),"save successful");
		} catch (RuntimeException re) {
			Logger.error(getClass(),"save failed");
			throw re;
		}
	}

	/**
	 * Delete a persistent ProductPriceHistory entity.
	 * 
	 * @param entity
	 *            ProductPriceHistory entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(ProductPriceHistory entity) {
		Logger.info(getClass(),"deleting ProductPriceHistory instance");
		try {
			//entity = entityManager.getReference(ProductPriceHistory.class, entity.getPpriceId());
			entityManager.remove(entity);
			Logger.info(getClass(),"delete successful");
		} catch (RuntimeException re) {
			Logger.error(getClass(),"delete failed");
			throw re;
		}
	}

	/**
	 * Persist a previously saved ProductPriceHistory entity and return it or a
	 * copy of it to the sender. A copy of the ProductPriceHistory entity
	 * parameter is returned when the JPA persistence mechanism has not
	 * previously been tracking the updated entity.
	 * 
	 * @param entity
	 *            ProductPriceHistory entity to update
	 * @return ProductPriceHistory the persisted ProductPriceHistory entity
	 *         instance, may not be the same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public ProductPriceHistory update(ProductPriceHistory entity) {
		Logger.info(getClass(),"updating ProductPriceHistory instance");
		try {
			ProductPriceHistory result = entityManager.merge(entity);
			Logger.info(getClass(),"update successful");
			return result;
		} catch (RuntimeException re) {
			Logger.error(getClass(),"update failed");
			throw re;
		}
	}

	public ProductPriceHistory findById(Long id) {
		Logger.info(getClass(),"finding ProductPriceHistory instance with id: " + id);
		try {
			ProductPriceHistory instance = entityManager.find(ProductPriceHistory.class, id);
			return instance;
		} catch (RuntimeException re) {
			Logger.error(getClass(),"find failed");
			throw re;
		}
	}

	/**
	 * Find all ProductPriceHistory entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the ProductPriceHistory property to query
	 * @param value
	 *            the property value to match
	 * @param rowStartIdxAndCount
	 *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
	 *            row index in the query result-set to begin collecting the
	 *            results. rowStartIdxAndCount[1] specifies the the maximum
	 *            number of results to return.
	 * @return List<ProductPriceHistory> found by query
	 */
	@SuppressWarnings("unchecked")
	public List<ProductPriceHistory> findByProperty(String propertyName, final Object value, final int... rowStartIdxAndCount) {
		Logger.info(getClass(),"finding ProductPriceHistory instance with property: " + propertyName + ", value: " + value);
		try {
			final String queryString = "select model from ProductPriceHistory model where model." + propertyName + "= :propertyValue";
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

	public List<ProductPriceHistory> findByProductKey(Object productKey, int... rowStartIdxAndCount) {
		return findByProperty(PRODUCT_KEY, productKey, rowStartIdxAndCount);
	}

	public List<ProductPriceHistory> findByPrice(Object price, int... rowStartIdxAndCount) {
		return findByProperty(PRICE, price, rowStartIdxAndCount);
	}

	public List<ProductPriceHistory> findByTbiid(Object tbiid, int... rowStartIdxAndCount) {
		return findByProperty(TBIID, tbiid, rowStartIdxAndCount);
	}

	public List<ProductPriceHistory> findByIsSuccess(Object isSuccess, int... rowStartIdxAndCount) {
		return findByProperty(IS_SUCCESS, isSuccess, rowStartIdxAndCount);
	}

	public List<ProductPriceHistory> findByErrorMsg(Object errorMsg, int... rowStartIdxAndCount) {
		return findByProperty(ERROR_MSG, errorMsg, rowStartIdxAndCount);
	}

	/**
	 * Find all ProductPriceHistory entities.
	 * 
	 * @param rowStartIdxAndCount
	 *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
	 *            row index in the query result-set to begin collecting the
	 *            results. rowStartIdxAndCount[1] specifies the the maximum
	 *            count of results to return.
	 * @return List<ProductPriceHistory> all ProductPriceHistory entities
	 */
	@SuppressWarnings("unchecked")
	public List<ProductPriceHistory> findAll(final int... rowStartIdxAndCount) {
		Logger.info(getClass(),"finding all ProductPriceHistory instances");
		try {
			final String queryString = "select model from ProductPriceHistory model";
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