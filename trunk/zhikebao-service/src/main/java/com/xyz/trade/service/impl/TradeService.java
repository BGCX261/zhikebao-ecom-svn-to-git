package com.xyz.trade.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.jdo.JDOObjectNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.taobao.api.model.Trade;
import com.xyz.BizConstants;
import com.xyz.framework.log.Logger;
import com.xyz.product.dao.IItemDao;
import com.xyz.system.model.Shop;
import com.xyz.trade.dao.ITradeDao;
import com.xyz.trade.service.ITradeService;
import com.xyz.util.DateUtil;
import com.xyz.util.Page;
import com.xyz.util.PropertyFilter;
import com.xyz.util.SpringSecurityUtil;

/**
 * 交易接口的实现类
 * @see com.xyz.trade.service.ITradeService
 * @author val
 * @since 1.0
 * @version 1.0
 */
@Service("tradeService")
public class TradeService implements ITradeService {
	// property constants
	public static final String ALIPAY_NO = "alipayNo";
	public static final String STATUS = "status";
	public static final String SELLER_MEMO = "sellerMemo";
	public static final String BUYER_NICK = "buyerNick";
	public static final String BUYER_EMAIL = "buyerEmail";
	public static final String BUYER_MESSAGE = "buyerMessage";
	public static final String PAYMENT = "payment";
	public static final String ADJUST_FEE = "adjustFee";
	public static final String DISCOUNT_FEE = "discountFee";
	public static final String TOTAL_FEE = "totalFee";
	public static final String POST_FEE = "postFee";
	public static final String HAS_POST_FEE = "hasPostFee";
	public static final String SHIPPING_TYPE = "shippingType";
	public static final String RECEIVER_STATE = "receiverState";
	public static final String RECEIVER_CITY = "receiverCity";
	public static final String RECEIVER_DISTRICT = "receiverDistrict";
	public static final String RECEIVER_ADDRESS = "receiverAddress";
	public static final String RECEIVER_ZIP = "receiverZip";
	public static final String RECEIVER_MOBILE = "receiverMobile";
	public static final String RECEIVER_PHONE = "receiverPhone";
	public static final String RECEIVER_NAME = "receiverName";

	public LinkedList<String> descSort = new LinkedList<String>();// 升序条件队列
	public LinkedList<String> ascSort = new LinkedList<String>(); // 降序条件队列
	
	@Autowired
	public ITradeDao tdao;
	
	@Autowired
	public IItemDao idao;

	/**
	 * Perform an initial save of a previously unsaved BossiniTrade entity. All
	 * subsequent persist actions of this entity should use the #update()
	 * method.
	 * 
	 * @param entity
	 *            BossiniTrade entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(Trade entity) {
		Logger.info(getClass(),"saving Trade instance");
		try {
			tdao.save(entity);
			Logger.info(getClass(),"save successful");
		} catch (RuntimeException re) {
			Logger.error(getClass(),"save failed");
			throw re;
		}
	}

	/**
	 * Delete a persistent BossiniTrade entity.
	 * 
	 * @param entity
	 *            BossiniTrade entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(Trade entity) {
		Logger.info(getClass(),"deleting Trade instance");
		try {
			tdao.delete(entity);
			Logger.info(getClass(),"delete successful");
		} catch (RuntimeException re) {
			Logger.error(getClass(),"delete failed");
			throw re;
		}
	}

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
	public Trade update(Trade entity) {
		Logger.info(getClass(),"updating BossiniTrade instance");
		try {
			tdao.update(entity);
			Logger.info(getClass(),"update successful");
			return entity;
		} catch (RuntimeException re) {
			Logger.error(getClass(),"update failed" );
			throw re;
		}
	}

	public Trade findById(String id) {
		Logger.info(getClass(),"finding BossiniTrade instance with id: " + id);
		try {
			Trade instance = tdao.get(id);
			return instance;
		} catch (JDOObjectNotFoundException re) {
			Logger.warn(getClass(),"没有找到指定Id的交易");
		}
		return null;
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
	public Page<Trade> findByProperty(String propertyName, final Object value,Page p ) {
		Logger.info(getClass(),"finding BossiniOrder instance with property: " + propertyName + ", value: " + value);
		try{
			return tdao.findBy(p,propertyName, value);
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
	public Page<Trade> findAll(Page page) {
		Logger.info(getClass(),"finding all BossiniOrder instances");
		try {
			return tdao.findPage(page);
		} catch (RuntimeException re) {
			Logger.error(getClass(),"find all failed");
			throw re;
		}
	}
	/**
	 * 分页查询指定卖家的交易列表
	 * @param page
	 * @return
	 */
	public Page<Trade> queryPage(Page<Trade> page,List<PropertyFilter> filters) {
		Logger.info(getClass(),"根据卖家查询交易列表");
		Shop tbuser = SpringSecurityUtil.getShop();
		Assert.notNull(tbuser, "tbuser不能为空");
		try {
			//filters.add(new PropertyFilter("EQ_buyerNick",tbuser.getTbAccount()));

			return tdao.findPage(page,filters);
		} catch (RuntimeException re) {
			Logger.error(getClass(),"find all failed");
			throw re;
		}
	}
	@SuppressWarnings("unchecked") 
	public Page<Trade> query(Page page,String sqlString) {
		try {
			return tdao.findPage(page, sqlString);
		} catch (RuntimeException re) {
			throw re;
		}
	}
    /**
     * 根据交易Trade的id查询
     */
	public Trade querySingle(String sqlString) {
		try {
			return tdao.findUnique(sqlString);
		} catch (RuntimeException re) {
			Logger.info(getClass(), "没有查询到结果");
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public List<Object> query(String sqlString) {
		try {
			return tdao.find(sqlString);
		} catch (RuntimeException re) {
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	public Page<Trade> findManyByProperty(Object[] propertyName,Object[] value,Page page) {
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
	/**
	 * 根据状态分页查询交易
	 */
	public Page<Trade> findByStatus(Object status,Page page) {
		return findByProperty(STATUS, status, page);
	}
	
	public void clear() {
		descSort.clear();
		ascSort.clear();
	}

	@Override
	public Map<String,String> countTrades() {
        Map<String,String> stas = new HashMap<String,String>();
        List<PropertyFilter> lp = new ArrayList<PropertyFilter>();
        lp.add(new PropertyFilter("LE_created",DateUtil.beginToday()));
        List<Trade> lt =  tdao.find(lp);
        int num = 0;
        double fee = 0;
        int payedNum = 0;
        double payedFee = 0;
        int unpayNum = 0;
        int shippedNum = 0;
        for(Trade t : lt)
        {
        	num++;
        	if(t.getTotalFee()!=null)
        	  fee += Double.parseDouble(t.getTotalFee());
        	if(BizConstants.TRADE_STA_WAIT_SELLER_SEND_GOODS.equals(t.getStatus()))
        	{
        		payedNum++;
        		if(t.getPayment()!=null)
        		payedFee += Double.parseDouble(t.getPayment());
        	}
        	if(BizConstants.TRADE_STA_TRADE_NO_CREATE_PAY.equals(t.getStatus()))
        	{
        		unpayNum++;
        	}
        	if(BizConstants.TRADE_STA_WAIT_BUYER_CONFIRM_GOODS.equals(t.getStatus()))
        	{
        		shippedNum++;
        	}
        }
        stas.put("newnum", Integer.toString(num));
        stas.put("allfee", Double.toString(fee));
        stas.put("paynum",  Integer.toString(payedNum));
        stas.put("payfee", Double.toString(payedFee));
        stas.put("unpaynum", Integer.toString(unpayNum));
        stas.put("shipnum", Integer.toString(shippedNum));
		return stas;
	}


}