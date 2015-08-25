package com.xyz.trade.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.taobao.api.TaobaoApiException;
import com.taobao.api.TaobaoRestClient;
import com.taobao.api.model.DeliverySendRequest;
import com.taobao.api.model.DeliverySendResponse;
import com.taobao.api.model.LogisticsCompaniesGetRequest;
import com.taobao.api.model.LogisticsCompaniesGetResponse;
import com.taobao.api.model.Order;
import com.taobao.api.model.Refund;
import com.taobao.api.model.RefundGetRequest;
import com.taobao.api.model.RefundGetResponse;
import com.taobao.api.model.RefundsRecieveGetRequest;
import com.taobao.api.model.RefundsRecieveGetResponse;
import com.taobao.api.model.Trade;
import com.taobao.api.model.TradeGetRequest;
import com.taobao.api.model.TradeGetResponse;
import com.taobao.api.model.TradesGetResponse;
import com.taobao.api.model.TradesSoldGetRequest;
import com.taobao.api.model.TradesSoldIncrementGetRequest;
import com.xyz.BizConstants;
import com.xyz.StaticValMethod;
import com.xyz.base.service.IBaseService;
import com.xyz.customer.dao.ICustomerDao;
import com.xyz.customer.model.Customer;
import com.xyz.customer.service.ICustomerService;
import com.xyz.framework.log.Logger;
import com.xyz.order.dao.IOrderDao;
import com.xyz.product.dao.IItemDao;
import com.xyz.product.model.Item;
import com.xyz.resources.model.Area;
import com.xyz.resources.model.Result;
import com.xyz.system.dao.IShopDao;
import com.xyz.system.model.Shop;
import com.xyz.system.model.User;
import com.xyz.trade.dao.ILogisticsDao;
import com.xyz.trade.dao.IRefundDao;
import com.xyz.trade.dao.IShipAddressDao;
import com.xyz.trade.dao.ITradeDao;
import com.xyz.trade.model.Delivery;
import com.xyz.trade.model.LogisticCompany;
import com.xyz.trade.model.OrderModel;
import com.xyz.trade.model.ShipAddress;
import com.xyz.trade.model.TradeModel;
import com.xyz.trade.service.ITradeService;
import com.xyz.util.DateUtil;
import com.xyz.util.Page;
import com.xyz.util.PropertyFilter;
import com.xyz.util.SpringSecurityUtil;
import com.xyz.util.TaobaoUtil;

/**
 * 交易接口的实现类
 * 
 * @see com.xyz.trade.service.ITradeService
 * @author val
 * @since 1.0
 * @version 1.0
 */
@Service("tradeService")
public class TradeService implements ITradeService {

	public LinkedList<String> descSort = new LinkedList<String>();// 升序条件队列
	public LinkedList<String> ascSort = new LinkedList<String>(); // 降序条件队列

	@Autowired
	private ITradeDao tdao;
	@Autowired
	private ICustomerDao cdao;
	@Autowired
	private IShopDao sao;
	@Autowired
	private IItemDao idao;
	@Autowired
	private IOrderDao odao;
	@Autowired
	private ILogisticsDao ldao;
	@Autowired
	private ICustomerService cusFacede;
	
	@Autowired
	private IBaseService bs;
	
	@Autowired
    private IRefundDao rdao;
	
	@Autowired
	private IShipAddressDao sadao;
	/**
	 * Perform an initial save of a previously 
	 * @param entity
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(TradeModel entity) {
		Logger.info(getClass(), "saving TradeModel instance");
		try {
			tdao.save(entity);
			Logger.info(getClass(), "save successful");
		} catch (RuntimeException re) {
			Logger.error(getClass(), "save failed");
			throw re;
		}
	}

	/**
	 * Delete a persistent
	 * 
	 * @param entity entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(String key) {
		Logger.info(getClass(), "deleting Trade instance");
		try {
			tdao.delete(Integer.parseInt(key));
			Logger.info(getClass(), "delete successful");
		} catch (RuntimeException re) {
			Logger.error(getClass(), "delete failed");
			throw re;
		}
	}

	/**
	 * Persist a previously saved 
	 * 
	 * @param entity
	 * @return 
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public TradeModel update(TradeModel entity) {
		Logger.info(getClass(), "updating Trade instance");
		try {
			tdao.update(entity);
			Logger.info(getClass(), "update successful");
			return entity;
		} catch (RuntimeException re) {
			Logger.error(getClass(), "update failed");
			throw re;
		}
	}

	public TradeModel findById(String id) {
		Logger.info(getClass(), "finding Trade instance with id: " + id);
		try {
			TradeModel instance = tdao.get(id);
			return instance;
		} catch (Exception re) {
			Logger.warn(getClass(), "没有找到指定Id的交易");
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
	public Page<TradeModel> findByProperty(String propertyName, final Object value,
			Page p) {
		Logger.info(getClass(), "finding instance with property: "
				+ propertyName + ", value: " + value);
		try {
			return tdao.findBy(p, propertyName, value);
		} catch (RuntimeException re) {
			Logger.error(getClass(), "find by property name failed");
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
	public Page<TradeModel> findAll(Page page) {
		Logger.info(getClass(), "finding all BossiniOrder instances");
		try {
			return tdao.findPage(page);
		} catch (RuntimeException re) {
			Logger.error(getClass(), "find all failed");
			throw re;
		}
	}

	/**
	 * 分页查询指定卖家的交易列表
	 * 
	 * @param page
	 * @return
	 */
	public Page<TradeModel> queryPage(Page<TradeModel> page, List<PropertyFilter> filters) {
		Logger.info(getClass(), "根据卖家查询交易列表");
		Shop shop = SpringSecurityUtil.getShop();
		Assert.notNull(shop, "shop不能为空");
		try {
			filters
					.add(new PropertyFilter("EQ_sellerNick", shop
							.getTbAccount()));

			return tdao.findPage(page, filters);
		} catch (RuntimeException re) {
			Logger.error(getClass(), "find all failed");
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	public Page<TradeModel> query(Page page, String sqlString) {
		try {
			return tdao.findPage(page, sqlString);
		} catch (RuntimeException re) {
			throw re;
		}
	}

	/**
	 * 根据交易Trade的id查询
	 */
	public TradeModel querySingle(String sqlString) {
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
	public Page<TradeModel> findManyByProperty(Object[] propertyName,
			Object[] value, Page page) {
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

	public void addSortDesc(String propertyName) {
		if (descSort.contains(propertyName)) {
			return;
		}
		descSort.add(propertyName);
	}

	public void addSortAsc(String propertyName) {
		if (ascSort.contains(propertyName)) {
			return;
		}
		ascSort.add(propertyName);
	}

	/**
	 * 根据状态分页查询交易
	 */
	public Page<TradeModel> findByStatus(Object status, Page page) {
		return findByProperty("status", status, page);
	}

	public void clear() {
		descSort.clear();
		ascSort.clear();
	}

	@Override
	public Map<String, String> countTrades() {
		Map<String, String> stas = new HashMap<String, String>();
		List<PropertyFilter> lp = new ArrayList<PropertyFilter>();
		lp.add(new PropertyFilter("LE_created", DateUtil.beginToday()));
		List<TradeModel> lt = tdao.find(lp);
		int num = 0;
		double fee = 0;
		int payedNum = 0;
		double payedFee = 0;
		int unpayNum = 0;
		int shippedNum = 0;
		for (TradeModel t : lt) {
			num++;
			if (t.getTotalFee() != null)
				fee += Double.parseDouble(t.getTotalFee());
			if (BizConstants.TRADE_STA_WAIT_SELLER_SEND_GOODS.equals(t
					.getStatus())) {
				payedNum++;
				if (t.getPayment() != null)
					payedFee += Double.parseDouble(t.getPayment());
			}
			if (BizConstants.TRADE_STA_TRADE_NO_CREATE_PAY
					.equals(t.getStatus())) {
				unpayNum++;
			}
			if (BizConstants.TRADE_STA_WAIT_BUYER_CONFIRM_GOODS.equals(t
					.getStatus())) {
				shippedNum++;
			}
		}
		stas.put("newnum", Integer.toString(num));
		stas.put("allfee", Double.toString(fee));
		stas.put("paynum", Integer.toString(payedNum));
		stas.put("payfee", Double.toString(payedFee));
		stas.put("unpaynum", Integer.toString(unpayNum));
		stas.put("shipnum", Integer.toString(shippedNum));
		return stas;
	}

	/**
	 * 删除所有交易
	 */
	public void deleteAll() {
		List<Item> li = idao.getAll();
		for (Item i : li)
			idao.delete(i.getPid());
		List<TradeModel> lt = tdao.getAll();
		for (TradeModel t : lt)
			tdao.delete(t.getPid());
	}

	/**
	 * 将交易分配给指定的人员
	 * 
	 * @param tradeKeys
	 * @param userKey
	 * @return
	 */
	public boolean assignOwner(Integer[] tradeKeys, Integer userKey) {
		Shop shop = SpringSecurityUtil.getShop();
		try {
			for (Integer tkey : tradeKeys) {
				TradeModel trade = tdao.get(tkey);
				trade.setOwnerId(userKey);
				tdao.update(trade);
				// 将该交易所对应的客户分配给同一个用户，便于以后自动分配交易
				Customer cus = cdao.findUnique("shopKey=?0 and nick = ?1 ",
						shop.getPid(), trade.getBuyerNick());
				cus.setOwnerId(userKey);
				cdao.update(cus);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;
	}

	/**
	 * 查询交易包含订单
	 */
	public List<OrderModel> getOrdersOfTrade(String tid) {
		return odao.findBy("tid ", tid);
	}

	/**
	 * 查询物流公司列表
	 */
	public List<LogisticCompany> getLogisticCompanies() {
		List<LogisticCompany> lclist = ldao.getAll();
		if (lclist == null || lclist.size() < 1) {
			Shop shop = SpringSecurityUtil.getShop();
			TaobaoRestClient client = TaobaoUtil.getService(shop);
			LogisticsCompaniesGetRequest lcgreq = new LogisticsCompaniesGetRequest();
			lcgreq.setFields("company_id,company_code,company_name");
			try {
				LogisticsCompaniesGetResponse lcgres = client
						.logisticsCompaniesGet(lcgreq);
				if (lcgres.isSuccess()) {
					List<com.taobao.api.model.LogisticCompany> tblclist = lcgres.getLogisticCompanies();
					lclist = new ArrayList<LogisticCompany>();
					LogisticCompany lc ;
					for (com.taobao.api.model.LogisticCompany tblc : tblclist)
					{
						lc = new LogisticCompany();
						BeanUtils.copyProperties(tblc, lc);
						ldao.save(lc);
					}
				}
			} catch (TaobaoApiException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return lclist;
	}

	/**
	 * 发货处理
	 * 
	 * @param deli
	 * @return
	 */
	public Result deliverySend(Delivery deli) {
		Result res = new Result();
		Shop shop = SpringSecurityUtil.getShop();
		Integer saId = deli.getSaId();
		ShipAddress sa = sadao.get(saId);
		Area area = null;
		if(sa.getSellerAreaId()==null)
		{
		    area = bs.getAreaByName("北京");
		    if(area==null)
		    {
			  Logger.error(getClass(), "不能得到地区码");
			  res.setMsg("不能得到地区码");
			  return res;
		    }
			sa.setSellerAreaId(area.getAreaId());
		}
		DeliverySendRequest delireq = new DeliverySendRequest();
		delireq.setSellerAreaId(sa.getSellerAreaId());
		delireq.setSellerAddress(sa.getLocation().getAddress());
		delireq.setSellerZip(sa.getLocation().getZip());
		BeanUtils.copyProperties(sa, delireq);
		BeanUtils.copyProperties(deli, delireq);
		TaobaoRestClient client = TaobaoUtil.getService(shop);
		DeliverySendResponse result;
		try {
			
			result = client.deliverySend(delireq, shop.getSessionKey());
			if (result.isDeliverSuccess()) {
				String tid = delireq.getTid();
				TradeModel trade = tdao.findUniqueBy("tid", tid);
				if (trade != null) {
					trade
							.setStatus(BizConstants.TRADE_STA_WAIT_BUYER_CONFIRM_GOODS);
					trade.setTradeMemo(delireq.getMemo());
					tdao.update(trade);
				}else{
					res.setMsg(result.getMsg());
					res.setErrCode(result.getErrorCode());
					return res;
				}
			}
		} catch (TaobaoApiException e) {
			Logger.error(this.getClass(), e.getStackTrace().toString());
			res.setMsg("执行失败。");
			return res;
		}
		res.setSuccess(true);
		return res;
	}

	/**
	 * 定时获取增量的交易信息
	 */
	public void tradeSysn() {
		    //deleteAll();
			Date now = DateUtil.getNow();//最新同步时间
			
			System.out.println("自动获取淘宝交易订单任务"
					+ DateUtil.format(now, "yyyy-MM-dd HH:mm:ss"));
			User user = SpringSecurityUtil.getCurrentUser();
			Shop shop = user.getShop();
			Assert.notNull(shop,"必须有淘宝会话");
			TaobaoRestClient client = TaobaoUtil.getService(shop);
			TradesSoldIncrementGetRequest req = new TradesSoldIncrementGetRequest();
			req.setFields(StaticValMethod.FIELDS_TAOBAO_TRADES_SOLD_GET);
			TradesGetResponse rsp = null;
			 //上一次成功同步的时间
			Date lastSyncTime = shop.getTaobaoTradesSoldGetTime();
			if(lastSyncTime==null)
				lastSyncTime = DateUtil.addMonth(now, -2);
			Set<String> nicks = new HashSet<String>();
			
			int totalRow = 0 ; //总记录数 
			int readRow = 0 ; //已处理的记录数
			int pageSize = 60;
			int pageNo = 1;
			String tid = "";
			String title;
			Date newSyncTime ;//最新同步时间
			//不断累加最后同步时间，直到当前
			while(lastSyncTime.before(now))
			{
			  do{
				req.setPageSize(pageSize);
				req.setPageNo(pageNo++);
				//累加一个小时
				newSyncTime = DateUtil.addMinute(lastSyncTime, 60*24);
				req.setStartModified(lastSyncTime);
				req.setEndModified(newSyncTime);
				//req.setStatus("WAIT_SELLER_SEND_GOODS");
				try {
					if(shop.getIsAuthorize()) 
						rsp = client.tradesSoldIncrementGet(req, shop.getSessionKey());
					else 
						return;
				} catch (TaobaoApiException e) {
					Logger.error(getClass(), "请求同步交易数据失败:"+e.getStackTrace());
					return;
				}
				if (rsp != null && rsp.isSuccess()) {
					if (rsp.getTrades() != null) 
					{
					totalRow = rsp.getTotalResults();
					for (Trade trade : rsp.getTrades()) {
						try {
							tid = trade.getTid();
							TradeModel localTrade = tdao.findUniqueBy("tid ",tid);
							if (localTrade != null) {
								if(localTrade.getModified().after(trade.getModified()))
								{
									readRow++;
									continue;
								}
							}else{
								localTrade = new TradeModel();
							}
							TradeGetRequest tgReq = new TradeGetRequest();
							tgReq.setFields(StaticValMethod.FIELDS_TAOBAO_TRADES_SOLD_GET_FULL_TRADE+""+StaticValMethod.FIELDS_TAOBAO_TRADES_SOLD_GET_ORDERS);
							tgReq.setTid(tid);
							TradeGetResponse tgRsp = client.tradeFullInfoGet(tgReq,shop.getSessionKey());
							
							if (tgRsp.isSuccess()) {
								trade = tgRsp.getTrade();
								nicks.add(trade.getBuyerNick());
								persistTrade(trade,localTrade);
								readRow++;
							}
	                     } catch (Exception ex) {
							Logger.error(getClass(), "订单号:" + tid + "不能正常同步!"+ex.getStackTrace());
							break;
						}
	
					}
				     }
				   }
			  }while(readRow<totalRow);
			  shop.setTaobaoTradesSoldGetTime(newSyncTime);
			  sao.update(shop);
			  lastSyncTime = newSyncTime;	
			  pageNo = 1;
			  readRow = 0;
			  totalRow = 0;
			}
				// 提取客户信息
				if (nicks.size() > 0)
					cusFacede.drawCoustomer(nicks);
	
	}
	
	/**
	 * 一次性完全获取全部的交易信息
	 */
	public void tradeFullSysn() {
		    //deleteAll();
			Date now = DateUtil.getNow();//最新同步时间
			
			System.out.println("自动获取淘宝交易订单任务"
					+ DateUtil.format(now, "yyyy-MM-dd HH:mm:ss"));
			User user = SpringSecurityUtil.getCurrentUser();
			Shop shop = user.getShop();
			Assert.notNull(shop,"必须有淘宝会话");
			TaobaoRestClient client = TaobaoUtil.getService(shop);
			TradesSoldGetRequest req = new TradesSoldGetRequest();
			req.setFields(StaticValMethod.FIELDS_TAOBAO_TRADES_SOLD_GET);
			TradesGetResponse rsp = null;
			 //上一次成功同步的时间
			Date lastSyncTime = shop.getTaobaoTradesSoldGetTime();
			if(lastSyncTime==null)
				lastSyncTime = DateUtil.addMonth(now, -2);
			Set<String> nicks = new HashSet<String>();
			
			int totalRow = 0 ; //总记录数 
			int readRow = 0 ; //已处理的记录数
			int pageSize = 60;
			int pageNo = 1;
			String tid = "";
			do{
				req.setPageSize(pageSize);
				req.setPageNo(pageNo++);
				//req.setStatus("WAIT_SELLER_SEND_GOODS");
				try {
					if(shop.getIsAuthorize()) 
						rsp = client.tradesSoldGet(req, shop.getSessionKey());
					else 
						return;
				} catch (TaobaoApiException e) {
					Logger.error(getClass(), "请求同步交易数据失败:"+e.getStackTrace());
					return;
				}
				if (rsp != null && rsp.isSuccess()) {
					if (rsp.getTrades() != null) 
					{
					totalRow = rsp.getTotalResults();
					for (Trade trade : rsp.getTrades()) {
						try {
							tid = trade.getTid();
							TradeModel localTrade = tdao.findUniqueBy("tid ",tid);
							if (localTrade != null) {
								if(localTrade.getModified().after(trade.getModified()))
								{
									readRow++;
									continue;
								}
							}else{
								localTrade = new TradeModel();
							}
							TradeGetRequest tgReq = new TradeGetRequest();
							tgReq.setFields(StaticValMethod.FIELDS_TAOBAO_TRADES_SOLD_GET_FULL_TRADE+","+StaticValMethod.FIELDS_TAOBAO_TRADES_SOLD_GET_ORDERS);
							tgReq.setTid(tid);
							TradeGetResponse tgRsp = client.tradeFullInfoGet(tgReq,shop.getSessionKey());
							if (tgRsp.isSuccess()) {
								trade = tgRsp.getTrade();
								persistTrade(trade,localTrade);
								readRow++;
								nicks.add(trade.getBuyerNick());
							}
	                     } catch (Exception ex) {
							Logger.error(getClass(), "订单号:" + tid + "不能正常同步!"+ex.getStackTrace());
							ex.printStackTrace();
							break;
						}
	
					}
				     }
				   }
			  }while(readRow<totalRow);
			  //更新最新同步时间
			  shop.setTaobaoTradesSoldGetTime(now);
			  sao.update(shop);
			  // 提取客户信息
		      if (nicks.size() > 0)
				cusFacede.drawCoustomer(nicks);
	
	}
	
	/**
	 * 定时或手动获取最新的退款申请列表信息
	 */
	public void refundSysn()
	{
		Date now = DateUtil.getNow();//最新同步时间
		
		System.out.println("获取淘宝退款申请列表"
				+ DateUtil.format(now, "yyyy-MM-dd HH:mm:ss"));
		User user = SpringSecurityUtil.getCurrentUser();
		Shop shop = user.getShop();
		Assert.notNull(shop,"必须有淘宝会话");
		TaobaoRestClient client = TaobaoUtil.getService(shop);
		RefundsRecieveGetRequest  req = new RefundsRecieveGetRequest ();
		req.setFields(StaticValMethod.FIELDS_TAOBAO_REFUNDS_RECEIVE_BASE_GET);
		RefundsRecieveGetResponse  rsp = null;
		 //上一次成功同步的时间
		Date lastSyncTime = shop.getSynchrRefunTime();
		if(lastSyncTime==null)
			lastSyncTime = DateUtil.addMonth(now, -1);
		int totalRow = 0 ; //总记录数 
		int readRow = 0 ; //已处理的记录数
		int pageSize = 60;
		int pageNo = 1;
		String rid = "";
		Date newSyncTime ;//最新同步时间
		//不断累加最后同步时间，直到当前
		while(lastSyncTime.before(now))
		{
		  do{
			req.setPageSize(pageSize);
			req.setPageNo(pageNo++);
			//累加一天
			newSyncTime = DateUtil.addMinute(lastSyncTime, 60*24);
			req.setStartModified(lastSyncTime);
			req.setEndModified(newSyncTime);
			try {
				if(shop.getIsAuthorize()) 
					rsp = client.refundsRecieveGet(req, shop.getSessionKey());
				else 
					return;
			} catch (TaobaoApiException e) {
				Logger.error(getClass(), "请求同步退款申请失败:"+e.getStackTrace());
				return;
			}
			if (rsp != null && rsp.isSuccess()) {
				if (rsp.getRefundList() != null) 
				{
				totalRow = rsp.getTotalResults();
				for (Refund refund : rsp.getRefundList()) {
					try {
						rid = refund.getRefundId();
						boolean isUpdate = false;
						//查询本地数据库
						Refund localRefund = rdao.findUniqueBy("refundId ",rid);
						if (localRefund != null) {
							if(localRefund.getModified().before(localRefund.getModified()))
								isUpdate = true;
							else
							{
								readRow++;
								continue;
							}
						}
						RefundGetRequest rgReq = new RefundGetRequest ();
						rgReq.setFields(StaticValMethod.FIELDS_TAOBAO_REFUNDS_RECEIVE_GET);
						rgReq.setRefundId(rid);
						RefundGetResponse rgRsp = client.refundGet(rgReq,shop.getSessionKey());
						List<Order> lo = null;
						if (rgRsp.isSuccess()) {
							refund = rgRsp.getRefund();
							if(isUpdate)
							{
								BeanUtils.copyProperties(localRefund, refund);
								rdao.update(localRefund);
							}else{
								//未分配的交易
								refund.setOwnerId(0);
								rdao.save(refund);
							}
							readRow++;
						}
                     } catch (Exception ex) {
						Logger.error(getClass(), "退款申请号:" + rid + "不能正常同步!"+ex.getStackTrace());
						break;
					}

				}
			     }
			   }
		  }while(readRow<totalRow);
		  shop.setSynchrRefunTime(newSyncTime);
		  sao.update(shop);
		  lastSyncTime = newSyncTime;	
		  pageNo = 1;
		  readRow = 0;
		  totalRow = 0;
		}
	}

	/**
	 * 分页查询指定卖家的发货地址列表
	 * @param page
	 * @return
	 */
	public Page<ShipAddress> queryShipAddrPage(Page<ShipAddress> page,
			List<PropertyFilter> filters) {
		Logger.info(getClass(), "根据卖家查询发货地址列表");
		Shop shop = SpringSecurityUtil.getShop();
		Assert.notNull(shop, "shop不能为空");
		try {
			filters
					.add(new PropertyFilter("EQ_shopId", shop.getPid()));

			return sadao.findPage(page, filters);
		} catch (RuntimeException re) {
			Logger.error(getClass(), "find all failed");
			throw re;
		}
	}
	/**
	 * 保存发货地址信息
	 */
	public void saveShipAddress(ShipAddress sa)
	{
		Shop shop = SpringSecurityUtil.getShop();
		if(sa.getPid()!=null&&sa.getPid()>0)
		{
		   sadao.update(sa);
		}else{
			sa.setShopId(shop.getPid());
			sadao.save(sa);	
		}
	}
	
	/**
	 * 保存发货地址中常用的物流公司信息
	 */
	public boolean saveLogisticsOfShipAddress(Integer saId, List<Integer> lcIds)
	{
		try {
			ShipAddress sa = sadao.get(saId);
			List<LogisticCompany> lr = new ArrayList<LogisticCompany>();
			for(Integer lcId : lcIds)
			{
				lr.add(ldao.get(lcId));
			}
			sa.setLl(lr);
			sadao.update(sa);
		} catch (Exception e) {
			Logger.error(getClass(), e.getStackTrace().toString());
			return false;
		}
		return true;
	}
	
	/**
	 * 获取发货地址信息
	 */
	public ShipAddress getShipAddress(Integer pid)
	{
		return sadao.get(pid);
	}
	
	private void persistTrade(Trade trade,TradeModel localTrade)
	{
		List<Order> lo = null;
		lo = trade.getOrders();
		List<OrderModel> lom = new ArrayList<OrderModel>();
		OrderModel localOrder ;
		
		for (Order order : lo) {
			localOrder = new OrderModel();
			BeanUtils.copyProperties(order, localOrder);
			lom.add(localOrder);
		}
		localTrade.setOrders(lom);
		BeanUtils.copyProperties(trade,localTrade,new String[]{"orders"});
		if(localTrade.getPid()!=null&&localTrade.getPid()>0)
		{
			tdao.update(localTrade);
		}else{
			//未分配的交易
			localTrade.setOwnerId(0);
			tdao.save(localTrade);
		}
	}

	@Override
	public List<ShipAddress> queryShipAddrs() {
		Logger.info(getClass(), "根据卖家查询发货地址列表");
		Shop shop = SpringSecurityUtil.getShop();
		Assert.notNull(shop, "shop不能为空");
		List<PropertyFilter> filters = new ArrayList<PropertyFilter>();
		try {
			filters.add(new PropertyFilter("EQ_shopId", shop.getPid()));
            return sadao.find(filters);
		} catch (RuntimeException re) {
			Logger.error(getClass(), "find all failed");
			throw re;
		}
		
	}
}