package com.xyz.order.service.impl;

import java.util.List;
import java.util.logging.Level;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Service;

import com.xyz.framework.log.Logger;
import com.xyz.order.model.AddressDatabase;
import com.xyz.order.service.IAddressServ;

/**
 * Facade for entity AddressDatabase.
 * 
 * @see com.bossini.ejb.bean.AddressDatabase
 * @author sea
 */
@Service("addressServ")
public class AddressServ implements IAddressServ  {
	// property constants
	public static final String AREAID = "areaid";
	public static final String STATE = "state";
	public static final String CITY = "city";
	public static final String DISTRICT = "district";
	public static final String ADDRESS = "address";
	public static final String ZIP = "zip";
	public static final String MOBILE = "mobile";
	public static final String PHONE = "phone";
	public static final String NAME = "name";
	public static final String IS_DEFAULT = "isDefault";

	private EntityManager entityManager;

	/* (non-Javadoc)
	 * @see com.xyz.order.service.impl.IAddressServ#save(com.xyz.order.model.AddressDatabase)
	 */
	public void save(AddressDatabase entity) {
		Logger.info(getClass(),"saving AddressDatabase instance");
		try {
			entityManager.persist(entity);
			Logger.info(getClass(),"save successful");
		} catch (RuntimeException re) {
			Logger.error(getClass(),"save failed");
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.xyz.order.service.impl.IAddressServ#delete(com.xyz.order.model.AddressDatabase)
	 */
	public void delete(AddressDatabase entity) {
		Logger.info(getClass(),"deleting AddressDatabase instance");
		try {
			entity = entityManager.getReference(AddressDatabase.class, entity.getId());
			entityManager.remove(entity);
			Logger.info(getClass(),"delete successful");
		} catch (RuntimeException re) {
			Logger.error(getClass(),"delete failed");
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.xyz.order.service.impl.IAddressServ#update(com.xyz.order.model.AddressDatabase)
	 */
	public AddressDatabase update(AddressDatabase entity) {
		Logger.info(getClass(),"updating AddressDatabase instance");
		try {
			AddressDatabase result = entityManager.merge(entity);
			Logger.info(getClass(),"update successful");
			return result;
		} catch (RuntimeException re) {
			Logger.info(getClass(),"update failed");
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.xyz.order.service.impl.IAddressServ#findById(java.lang.Integer)
	 */
	public AddressDatabase findById(Integer id) {
		Logger.info(getClass(),"finding AddressDatabase instance with id: " + id);
		try {
			AddressDatabase instance = entityManager.find(AddressDatabase.class, id);
			return instance;
		} catch (RuntimeException re) {
			Logger.info(getClass(),"find failed");
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.xyz.order.service.impl.IAddressServ#findByProperty(java.lang.String, java.lang.Object, int)
	 */
	@SuppressWarnings("unchecked")
	public List<AddressDatabase> findByProperty(String propertyName, final Object value, final int... rowStartIdxAndCount) {
		Logger.info(getClass(),"finding AddressDatabase instance with property: " + propertyName + ", value: " + value);
		try {
			final String queryString = "select model from AddressDatabase model where model." + propertyName + "= :propertyValue";
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
			Logger.info(getClass(),"find by property name failed");
			throw re;
		}
	}
	/* (non-Javadoc)
	 * @see com.xyz.order.service.impl.IAddressServ#findByAreaid(java.lang.Object, int)
	 */
	public List<AddressDatabase> findByAreaid(Object areaid, int... rowStartIdxAndCount) {
		return findByProperty(AREAID, areaid, rowStartIdxAndCount);
	}

	/* (non-Javadoc)
	 * @see com.xyz.order.service.impl.IAddressServ#findByState(java.lang.Object, int)
	 */
	public List<AddressDatabase> findByState(Object state, int... rowStartIdxAndCount) {
		return findByProperty(STATE, state, rowStartIdxAndCount);
	}

	/* (non-Javadoc)
	 * @see com.xyz.order.service.impl.IAddressServ#findByCity(java.lang.Object, int)
	 */
	public List<AddressDatabase> findByCity(Object city, int... rowStartIdxAndCount) {
		return findByProperty(CITY, city, rowStartIdxAndCount);
	}

	/* (non-Javadoc)
	 * @see com.xyz.order.service.impl.IAddressServ#findByDistrict(java.lang.Object, int)
	 */
	public List<AddressDatabase> findByDistrict(Object district, int... rowStartIdxAndCount) {
		return findByProperty(DISTRICT, district, rowStartIdxAndCount);
	}

	/* (non-Javadoc)
	 * @see com.xyz.order.service.impl.IAddressServ#findByAddress(java.lang.Object, int)
	 */
	public List<AddressDatabase> findByAddress(Object address, int... rowStartIdxAndCount) {
		return findByProperty(ADDRESS, address, rowStartIdxAndCount);
	}

	/* (non-Javadoc)
	 * @see com.xyz.order.service.impl.IAddressServ#findByZip(java.lang.Object, int)
	 */
	public List<AddressDatabase> findByZip(Object zip, int... rowStartIdxAndCount) {
		return findByProperty(ZIP, zip, rowStartIdxAndCount);
	}

	/* (non-Javadoc)
	 * @see com.xyz.order.service.impl.IAddressServ#findByMobile(java.lang.Object, int)
	 */
	public List<AddressDatabase> findByMobile(Object mobile, int... rowStartIdxAndCount) {
		return findByProperty(MOBILE, mobile, rowStartIdxAndCount);
	}

	/* (non-Javadoc)
	 * @see com.xyz.order.service.impl.IAddressServ#findByPhone(java.lang.Object, int)
	 */
	public List<AddressDatabase> findByPhone(Object phone, int... rowStartIdxAndCount) {
		return findByProperty(PHONE, phone, rowStartIdxAndCount);
	}

	/* (non-Javadoc)
	 * @see com.xyz.order.service.impl.IAddressServ#findByName(java.lang.Object, int)
	 */
	public List<AddressDatabase> findByName(Object name, int... rowStartIdxAndCount) {
		return findByProperty(NAME, name, rowStartIdxAndCount);
	}

	/* (non-Javadoc)
	 * @see com.xyz.order.service.impl.IAddressServ#findByIsDefault(java.lang.Object, int)
	 */
	public List<AddressDatabase> findByIsDefault(Object isDefault, int... rowStartIdxAndCount) {
		return findByProperty(IS_DEFAULT, isDefault, rowStartIdxAndCount);
	}

	/* (non-Javadoc)
	 * @see com.xyz.order.service.impl.IAddressServ#findAll(int)
	 */
	@SuppressWarnings("unchecked")
	public List<AddressDatabase> findAll(final int... rowStartIdxAndCount) {
		Logger.info(getClass(),"finding all AddressDatabase instances");
		try {
			final String queryString = "select model from AddressDatabase model";
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