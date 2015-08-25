package com.xyz.order.service.impl;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xyz.framework.log.Logger;
import com.xyz.order.dao.IOrderDao;
import com.xyz.order.service.IOrderService;
import com.xyz.trade.model.OrderModel;
import com.xyz.util.Page;

/**
 * Facade for entity Order.
 * 
 * @see 
 * @author val
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
	 * 
	 * @param entity Order entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(OrderModel entity) {
		Logger.info(getClass(),"saving Order instance");
		try {
			odao.save(entity);
			Logger.info(getClass(),"save successful");
		} catch (RuntimeException re) {
			Logger.error(getClass(),"save failed");
			throw re;
		}
	}

	/**
	 * Delete a persistent Order entity.
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(String id) {
		Logger.info(getClass(),"deleting Order instance");
		try {
			odao.delete(Integer.parseInt(id));
			Logger.info(getClass(),"delete successful");
		} catch (RuntimeException re) {
			Logger.error(getClass(),"delete failed");
			throw re;
		}
	}

	/**
	 * Persist a previously saved Order entity 
	 * 
	 * @param entity  
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public OrderModel update(OrderModel entity) {
		Logger.info(getClass(),"updating Order instance");
		try {
			odao.update(entity);
			Logger.info(getClass(),"update successful");
			return entity;
		} catch (RuntimeException re) {
			Logger.error(getClass(),"update failed");
			throw re;
		}
	}

	public OrderModel findById(Long id) {
		Logger.info(getClass(),"finding Order instance with id: " + id);
		try {
			OrderModel instance = odao.get(id);
			return instance;
		} catch (RuntimeException re) {
			Logger.error(getClass(),"find failed");
			throw re;
		}
	}

	/**
	 * Find all Order entities with a specific property value.
	 * 
	 * @param propertyName
	 *           
	 * @param value  the property value to match
	 * @param rowStartIdxAndCount
	 *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
	 *            row index in the query result-set to begin collecting the
	 *            results. rowStartIdxAndCount[1] specifies the the maximum
	 *            number of results to return.
	 * @return List<OrderModel> found by query
	 */
	@SuppressWarnings("unchecked")
	public Page<OrderModel> findByProperty(String propertyName, final Object value,Page p ) {
		Logger.info(getClass(),"finding Order instance with property: " + propertyName + ", value: " + value);
		try{
			return odao.findBy(p,propertyName, value);
		} catch (RuntimeException re) {
			Logger.error(getClass(),"find by property name failed");
			throw re;
		}
	}

	/**
	 * Find all Order entities.
	 * 
	 * @param 
	 * @return List<OrderModel> all Order entities
	 */
	@SuppressWarnings("unchecked")
	public Page<OrderModel> findAll(Page page) {
		Logger.info(getClass(),"finding all Order instances");
		try {
			return odao.findPage(page);
		} catch (RuntimeException re) {
			Logger.error(getClass(),"find all failed");
			throw re;
		}
	}

	@SuppressWarnings("unchecked") 
	public Page<OrderModel> query(Page page,String sqlString) {
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
	public Page<OrderModel> findManyByProperty(Object[] propertyName,Object[] value,Page page) {
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
	public void delete(OrderModel entity) {
		odao.delete(entity);
	}


}