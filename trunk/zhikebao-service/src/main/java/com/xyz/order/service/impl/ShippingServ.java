package com.xyz.order.service.impl;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Service;

import com.xyz.framework.log.Logger;
import com.xyz.order.model.Shipping;
import com.xyz.order.service.IShippingServ;
import com.xyz.util.DateUtil;
import com.xyz.util.QueryDate;

/**
 * Facade for entity Shipping.
 * 
 * @see com.bossini.ejb.bean.Shipping
 * @author sea
 */
@Service("shippingServ")
public class ShippingServ implements IShippingServ {
	// property constants
	public static final String OUT_SID = "outSid";
	public static final String COMPANY_NAME = "companyName";
	public static final String COMPANY_CODE = "companyCode";
	public static final String IS_SUCCESS = "isSuccess";
	public static final String ERROR_MSG = "errorMsg";
	private LinkedList<String> descSort = new LinkedList<String>();// 升序条件队列
	private LinkedList<String> ascSort = new LinkedList<String>(); // 降序条件队列

	private EntityManager entityManager;

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
	public void save(Shipping entity) {
		Logger.info(getClass(),"saving Shipping instance");
		try {
			entityManager.persist(entity);
			Logger.info(getClass(),"save successful");
		} catch (RuntimeException re) {
			Logger.error(getClass(),"save failed");
			throw re;
		}
	}

	/**
	 * Delete a persistent Shipping entity.
	 * 
	 * @param entity
	 *            Shipping entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(Shipping entity) {
		Logger.info(getClass(),"deleting Shipping instance");
		try {
			entity = entityManager.getReference(Shipping.class, entity.getSid());
			entityManager.remove(entity);
			Logger.info(getClass(),"delete successful");
		} catch (RuntimeException re) {
			Logger.error(getClass(),"delete failed");
			throw re;
		}
	}

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
	public Shipping update(Shipping entity) {
		Logger.info(getClass(),"updating Shipping instance");
		try {
			Shipping result = entityManager.merge(entity);
			Logger.info(getClass(),"update successful");
			return result;
		} catch (RuntimeException re) {
			Logger.error(getClass(),"update failed");
			throw re;
		}
	}

	public Shipping findById(Long id) {
		Logger.info(getClass(),"finding Shipping instance with id: " + id);
		try {
			Shipping instance = entityManager.find(Shipping.class, id);
			return instance;
		} catch (RuntimeException re) {
			Logger.error(getClass(),"find failed");
			throw re;
		}
	}

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
	 *            number of results to return.
	 * @return List<Shipping> found by query
	 */
	@SuppressWarnings("unchecked")
	public List<Shipping> findByProperty(String propertyName, final Object value, final int... rowStartIdxAndCount) {
		Logger.info(getClass(),"finding Shipping instance with property: " + propertyName + ", value: " + value);
		try {
			String queryString = "select model from Shipping model where model." + propertyName + "= :propertyValue";
			//排序处理
			if(descSort!= null && descSort.size()>0 || ascSort!=null && ascSort.size()>0){
				queryString += " order by ";
			}
			if (descSort != null) {
				for (String name : descSort) {
					queryString += "model." + name + " desc,";
				}
		    }
			if (ascSort != null) {
				for (String name : ascSort) {
					queryString += "model." + name + " asc,";
				}
			}
			if(descSort!= null && descSort.size()>0 || ascSort!=null && ascSort.size()>0){
				queryString = queryString.substring(0,queryString.length()-1);
			}
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
	public List<Shipping> findByOutSid(Object outSid, int... rowStartIdxAndCount) {
		return findByProperty(OUT_SID, outSid, rowStartIdxAndCount);
	}

	public List<Shipping> findByCompanyName(Object companyName, int... rowStartIdxAndCount) {
		return findByProperty(COMPANY_NAME, companyName, rowStartIdxAndCount);
	}

	public List<Shipping> findByCompanyCode(Object companyCode, int... rowStartIdxAndCount) {
		return findByProperty(COMPANY_CODE, companyCode, rowStartIdxAndCount);
	}

	public List<Shipping> findByIsSuccess(Object isSuccess, int... rowStartIdxAndCount) {
		return findByProperty(IS_SUCCESS, isSuccess, rowStartIdxAndCount);
	}

	public List<Shipping> findByErrorMsg(Object errorMsg, int... rowStartIdxAndCount) {
		return findByProperty(ERROR_MSG, errorMsg, rowStartIdxAndCount);
	}

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
	@SuppressWarnings("unchecked")
	public List<Shipping> findAll(final int... rowStartIdxAndCount) {
		Logger.info(getClass(),"finding all Shipping instances");
		try {
			String queryString = "select model from Shipping model";
			//排序处理
			if(descSort!= null && descSort.size()>0 || ascSort!=null && ascSort.size()>0){
				queryString += " order by ";
			}
			if (descSort != null) {
				for (String name : descSort) {
					queryString += "model." + name + " desc,";
				}
		    }
			if (ascSort != null) {
				for (String name : ascSort) {
					queryString += "model." + name + " asc,";
				}
			}
			if(descSort!= null && descSort.size()>0 || ascSort!=null && ascSort.size()>0){
				queryString = queryString.substring(0,queryString.length()-1);
			}
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
	
	@SuppressWarnings("unchecked")
	public List<Shipping> query(String sqlString, Integer startRecord, Integer maxRecord) {
		try {
			Query query = entityManager.createNativeQuery(sqlString, Shipping.class);
			if (startRecord != null) {
				query.setFirstResult(startRecord);
			}
			if (maxRecord != null) {
				query.setMaxResults(maxRecord);
			}
			return query.getResultList();
		} catch (RuntimeException re) {
			throw re;
		}
	}

	public Object querySingle(String sqlString) {
		try {
			Query query = entityManager.createNativeQuery(sqlString);
			return query.getSingleResult();
		} catch (RuntimeException re) {
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	public List<Object[]> query(String sqlString) {
		try {
			Query query = entityManager.createNativeQuery(sqlString);
			return (List<Object[]>) query.getResultList();
		} catch (RuntimeException re) {
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	public List<Shipping> findManyByProperty(Object[] propertyName,Object[] value, final int... rowStartIdxAndCount) {
		try {
			String queryString = "select * from Shipping model where 1=1";
			for(int i=0;i<propertyName.length;i++){
				if(value[i] instanceof String){
					queryString += " and model." + propertyName[i] + " like '%" + value[i] +"%'";
				}else if(value[i] instanceof Date){
					queryString += " and DateDiff(day,'" + DateUtil.format((Date)value[i]) + "',model."+propertyName[i]+") = 0";
				}else if(value[i] instanceof QueryDate){
					QueryDate queryDate = (QueryDate)value[i];
					if(queryDate.getStartDate() != null){
						queryString += " and DateDiff(day,'" + queryDate.getStartDate() + "',model."+propertyName[i]+") >= 0";
					}
					if(queryDate.getEndDate() != null){
						queryString += " and DateDiff(day,'" + queryDate.getEndDate() + "',model."+propertyName[i]+") <= 0";
					}
				}else if(value[i] instanceof Boolean){
					queryString += " and model." + propertyName[i] + "=" + (((Boolean)value[i]).booleanValue()==true?1:0);
				}else{
					queryString += " and model." + propertyName[i] + "=" + value[i];
				}
			}
			
			//排序处理
			if(descSort!= null && descSort.size()>0 || ascSort!=null && ascSort.size()>0){
				queryString += " order by ";
			}
			if (descSort != null) {
				for (String name : descSort) {
					queryString += "model." + name + " desc,";
				}
		    }
			if (ascSort != null) {
				for (String name : ascSort) {
					queryString += "model." + name + " asc,";
				}
			}
			if(descSort!= null && descSort.size()>0 || ascSort!=null && ascSort.size()>0){
				queryString = queryString.substring(0,queryString.length()-1);
			}
			
			Query query = entityManager.createNativeQuery(queryString, Shipping.class);
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
			throw re;
		}
	}
	
	public Integer findManyByPropertySize(Object[] propertyName, Object[] value) {
		try {
			String queryString = "select count(*) from Shipping model where 1=1";
			for(int i=0;i<propertyName.length;i++){
				if(value[i] instanceof String){
					queryString += " and model." + propertyName[i] + " like '%" + value[i] +"%'";
				}else if(value[i] instanceof Date){
					queryString += " and DateDiff(day,'" + DateUtil.format((Date)value[i]) + "',model."+propertyName[i]+") >= 0";
				}else if(value[i] instanceof QueryDate){
					QueryDate queryDate = (QueryDate)value[i];
					if(queryDate.getStartDate() != null){
						queryString += " and DateDiff(day,'" + queryDate.getStartDate() + "',model."+propertyName[i]+") >= 0";
					}
					if(queryDate.getEndDate() != null){
						queryString += " and DateDiff(day,'" + queryDate.getEndDate() + "',model."+propertyName[i]+") <= 0";
					}
				}else if(value[i] instanceof Boolean){
					queryString += " and model." + propertyName[i] + "=" + (((Boolean)value[i]).booleanValue()==true?1:0);
				}else{
					queryString += " and model." + propertyName[i] + "=" + value[i];
				}
			}
			Query query = entityManager.createNativeQuery(queryString);
			return (Integer)query.getSingleResult();
		} catch (RuntimeException re) {
			throw re;
		}
	}

	public Long findByPropertySize(String propertyName, Object value) {
		try {
			String queryString = "select count(model) from Shipping model where model." + propertyName + "= :propertyValue";
			Query query = entityManager.createQuery(queryString);
			query.setParameter("propertyValue", value);
			return (Long) query.getSingleResult();
		} catch (RuntimeException re) {
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	public List<Object> queryObjectList(String sqlString){
		try 
		{
			Query query = entityManager.createNativeQuery(sqlString);
			return (List<Object>)query.getResultList();
		} catch (RuntimeException re) {
			throw re;
		}
	}
	
	public void addSortDesc( String propertyName ) {
		if( descSort.contains( propertyName )) {
			return;
		}
		descSort.add(propertyName);
	}
	
	public void addSortAsc( String propertyName ) {
		if( ascSort.contains( propertyName )) {
			return;
		}
		ascSort.add(propertyName);
	}
	
	public void clear() {
		descSort.clear();
		ascSort.clear();
	}

}