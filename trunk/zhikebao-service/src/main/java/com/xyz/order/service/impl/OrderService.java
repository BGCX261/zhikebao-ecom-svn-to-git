package com.xyz.order.service.impl;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taobao.api.model.Order;
import com.xyz.framework.log.Logger;
import com.xyz.order.dao.IOrderDao;
import com.xyz.order.service.IOrderService;
import com.xyz.util.Page;

/**
 * Facade for entity BossiniOrder.
 * 
 * @see com.OrderInfo.ejb.bean.BossiniOrder
 * @author sea
 */
@Service("orderServ")
public class OrderService implements IOrderService {
	// property constants
	public static final String IID = "iid";
	public static final String NUM = "num";
	public static final String TITLE = "title";
	public static final String PRICE = "price";
	public static final String PIC_PATH = "picPath";
	public static final String REFUND_STATUS = "refundStatus";
	public static final String REFUND_DESC = "refundDesc";
	public static final String OUTER_IID = "outerIid";
	public static final String TOTAL_FEE = "totalFee";
	public static final String PAYMENT = "payment";
	public static final String DISCOUNT_FEE = "discountFee";
	public static final String ADJUST_FEE = "adjustFee";
	private LinkedList<String> descSort = new LinkedList<String>();// 升序条件队列
	private LinkedList<String> ascSort = new LinkedList<String>(); // 降序条件队列

	@Autowired
	private IOrderDao odao ;
	/**
	 * Perform an initial save of a previously unsaved BossiniOrder entity. All
	 * subsequent persist actions of this entity should use the #update()
	 * method.
	 * 
	 * @param entity
	 *            BossiniOrder entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(Order entity) {
		Logger.info(getClass(),"saving BossiniOrder instance");
		try {
			odao.save(entity);
			Logger.info(getClass(),"save successful");
		} catch (RuntimeException re) {
			Logger.error(getClass(),"save failed");
			throw re;
		}
	}

	/**
	 * Delete a persistent BossiniOrder entity.
	 * 
	 * @param entity
	 *            BossiniOrder entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(Long id) {
		Logger.info(getClass(),"deleting BossiniOrder instance");
		try {
			odao.delete(id);
			Logger.info(getClass(),"delete successful");
		} catch (RuntimeException re) {
			Logger.error(getClass(),"delete failed");
			throw re;
		}
	}

	/**
	 * Persist a previously saved BossiniOrder entity and return it or a copy of
	 * it to the sender. A copy of the BossiniOrder entity parameter is returned
	 * when the JPA persistence mechanism has not previously been tracking the
	 * updated entity.
	 * 
	 * @param entity
	 *            BossiniOrder entity to update
	 * @return BossiniOrder the persisted BossiniOrder entity instance, may not
	 *         be the same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public Order update(Order entity) {
		Logger.info(getClass(),"updating BossiniOrder instance");
		try {
			odao.update(entity);
			Logger.info(getClass(),"update successful");
			return entity;
		} catch (RuntimeException re) {
			Logger.error(getClass(),"update failed");
			throw re;
		}
	}

	public Order findById(Long id) {
		Logger.info(getClass(),"finding BossiniOrder instance with id: " + id);
		try {
			Order instance = odao.get(id);
			return instance;
		} catch (RuntimeException re) {
			Logger.error(getClass(),"find failed");
			throw re;
		}
	}

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
	public Page<Order> findByProperty(String propertyName, final Object value,Page p ) {
		Logger.info(getClass(),"finding BossiniOrder instance with property: " + propertyName + ", value: " + value);
		try{
			return odao.findBy(p,propertyName, value);
		} catch (RuntimeException re) {
			Logger.error(getClass(),"find by property name failed");
			throw re;
		}
	}

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
	public Page<Order> findAll(Page page) {
		Logger.info(getClass(),"finding all BossiniOrder instances");
		try {
			return odao.findPage(page);
		} catch (RuntimeException re) {
			Logger.error(getClass(),"find all failed");
			throw re;
		}
	}

	@SuppressWarnings("unchecked") 
	public Page<Order> query(Page page,String sqlString) {
		try {
			return odao.findPage(page, sqlString);
		} catch (RuntimeException re) {
			throw re;
		}
	}

	public Object querySingle(String sqlString) {
		try {
			return odao.findUnique(sqlString);
		} catch (RuntimeException re) {
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	public List<Object> query(String sqlString) {
		try {
			return odao.find(sqlString);
		} catch (RuntimeException re) {
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	public Page<Order> findManyByProperty(Object[] propertyName,Object[] value,Page page) {
		try {
			return null;
		} catch (RuntimeException re) {
			throw re;
		}
	}
	
	public Integer findManyByPropertySize(Object[] propertyName, Object[] value) {
		try {
		
			return null;
		} catch (RuntimeException re) {
			throw re;
		}
	}

	public Long findByPropertySize(String propertyName, Object value) {
		try {
			
			return null;
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

	@Override
	public void delete(Order entity) {
		odao.delete(entity);
	}


}