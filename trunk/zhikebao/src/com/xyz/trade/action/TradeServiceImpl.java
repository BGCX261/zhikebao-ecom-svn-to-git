package com.xyz.trade.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.extjs.gxt.ui.client.data.BasePagingLoadResult;
import com.extjs.gxt.ui.client.data.PagingLoadConfig;
import com.extjs.gxt.ui.client.data.PagingLoadResult;
import com.xyz.FrameConstants;
import com.xyz.base.service.IBaseService;
import com.xyz.framework.log.Logger;
import com.xyz.resources.client.ZkbConstants;
import com.xyz.resources.model.BaseCode;
import com.xyz.resources.model.Result;
import com.xyz.system.model.User;
import com.xyz.trade.client.TradeService;
import com.xyz.trade.model.Delivery;
import com.xyz.trade.model.LogisticCompany;
import com.xyz.trade.model.OrderModel;
import com.xyz.trade.model.ShipAddress;
import com.xyz.trade.model.TradeModel;
import com.xyz.trade.service.ITradeService;
import com.xyz.util.Page;
import com.xyz.util.PropertyFilter;
import com.xyz.util.SpringSecurityUtil;

@Service("tradeServiceImpl")
public class TradeServiceImpl implements TradeService {
    
	private static final long serialVersionUID = 1L;
	@Autowired
	private ITradeService ts;
	
	@Autowired
	private IBaseService bs;
    
	@Override
	 public PagingLoadResult<TradeModel> getTrades(PagingLoadConfig config,TradeModel tm) {
		Page<TradeModel> page = new Page<TradeModel>(config.getLimit());
		page.setStart(config.getOffset());
		List<PropertyFilter> filters = new ArrayList<PropertyFilter>();
		/*
		 *设置查询条件
		 **/
		if(tm!=null)
		{
			if(tm.getBuyerNick()!=null&&tm.getBuyerNick().trim().length()>0)
			    filters.add(new PropertyFilter("EQ_buyerNick",tm.getBuyerNick().trim()));
			if(tm.getCreated()!=null)
				    filters.add(new PropertyFilter("GE_created",tm.getCreated()));
			if(tm.getModified()!=null)
				    filters.add(new PropertyFilter("LE_created",tm.getModified()));
			if(tm.getIid()!=null&&tm.getIid().trim().length()>0)
			    filters.add(new PropertyFilter("EQ_tid",tm.getIid().trim()));
			if(tm.getStatus()!=null&&tm.getStatus().trim().length()>0)
			    filters.add(new PropertyFilter("EQ_status",tm.getStatus().trim()));
			if(tm.getOwnerId()!=null&&tm.getOwnerId()>=0)
			    filters.add(new PropertyFilter("EQ_ownerId",tm.getOwnerId()));
		}
		page = ts.queryPage(page, filters);
		List<TradeModel> lf = page.getResult();
		User user = SpringSecurityUtil.getCurrentUser();
		if(lf!=null&&lf.size()>0)
		{
			for(TradeModel ic : lf)
			{
				if(SpringSecurityUtil.isHasAuth(FrameConstants.SYS_AUTH_ADMIN))
					ic.setIsEditable(true);
				else if(user.getPid().equals(ic.getOwnerId()))
					ic.setIsEditable(true);
				List<OrderModel> lo = ic.getOrders();
				if(lo!=null&&lo.size()>0)
				{
					for(OrderModel om : lo)
					{
						//将商品的标题作为交易的标题（默认）
						ic.setTitle(om.getTitle());
						break;
					}
			    }
			}
	    }
		return new BasePagingLoadResult<TradeModel>(lf, config.getOffset(),
				page.getTotalCount());
	}

	@Override
	public List<BaseCode> getCusType() {
		List<BaseCode> lb = bs.queryByType(ZkbConstants.CODE_CUS_TYPE);
		
		return lb;
	}

	/**
	 * 查询今天交易情况返回一个数组
	 */
	public Map<String,String> getTodTraSta() {
		Map<String,String> mss = ts.countTrades();
		return mss;
	}

	@Override
	public boolean assignOwner(Integer[] tradeKeys,Integer userKey) {
		return ts.assignOwner(tradeKeys, userKey);
	}

	/**
	 * 查询交易订单
	*/
	public List<OrderModel> getOrdersOfTrade(String tid) {
		List<OrderModel> lo = ts.getOrdersOfTrade(tid);
		return lo;
	}

	@Override
	public List<LogisticCompany> getLogisticCompanies() {
		return ts.getLogisticCompanies();
	}


    /**
     * 处理发货
     */
	public Result deliverySend(Delivery deli) {
		return ts.deliverySend(deli);
    }

	/**
	 * 按条件分页查询发货地址列表
	 */
	public PagingLoadResult<ShipAddress> getShipAddress(
			PagingLoadConfig config, ShipAddress sa) {
		Page<ShipAddress> page = new Page<ShipAddress>(config.getLimit());
		page.setStart(config.getOffset());
		List<PropertyFilter> filters = new ArrayList<PropertyFilter>();
		/*
		 *设置查询条件
		 **/
		if(sa!=null)
		{
		}
		page = ts.queryShipAddrPage(page, filters);
		List<ShipAddress> lf = page.getResult();
		
		return new BasePagingLoadResult<ShipAddress>(lf, config.getOffset(),
				page.getTotalCount());
	}
	
	 /**
	   * 取得发货地址列表
	   * @param loadConfig
	   * @param tm
	   * @return
	   */
	  public List<ShipAddress> getShipAddress()
	  {
		  List<ShipAddress> ls = ts.queryShipAddrs();
	      return ls;
	  }

	@Override
	public ShipAddress saveShipAddress(ShipAddress sa) {
        try {
			ts.saveShipAddress(sa);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			Logger.error(getClass(), "保存发货地址失败");
			return null;
		}
           return sa;
	}

	@Override
	public boolean saveLogisticsOfShipAddress(Integer saId,
			List<Integer> logisIds) {
		return ts.saveLogisticsOfShipAddress(saId, logisIds);
	}

	/**
	 * 获取物流公司列表，已选和备选两个列表
	 */
	public Map<String, List<LogisticCompany>> getAllLogistics(Integer key) {
		Map<String,List<LogisticCompany>> msr = new HashMap<String,List<LogisticCompany>>();
		List<LogisticCompany> unself = new ArrayList<LogisticCompany>();
		ShipAddress sa = ts.getShipAddress(key);
		List<LogisticCompany> seledfs = sa.getLl();
		List<LogisticCompany> pr = ts.getLogisticCompanies();
		if(pr!=null&&pr.size()>0)
		{
			for(LogisticCompany lc :pr)
			{
				if(!seledfs.contains(lc))
					unself.add(lc);
			}
	    }
		msr.put("unselect", unself);
		msr.put("selected", seledfs);
		return msr;
	}

}
