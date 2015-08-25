package com.xyz.trade.server;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.extjs.gxt.ui.client.data.BasePagingLoadResult;
import com.extjs.gxt.ui.client.data.PagingLoadConfig;
import com.extjs.gxt.ui.client.data.PagingLoadResult;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.taobao.api.model.DeliverySendRequest;
import com.taobao.api.model.Trade;
import com.xyz.base.model.BaseCode;
import com.xyz.base.service.IBaseService;
import com.xyz.resources.client.ZkbConstants;
import com.xyz.system.client.mvc.Code;
import com.xyz.trade.client.TradeService;
import com.xyz.trade.client.model.Delivery;
import com.xyz.trade.client.model.LogisticCompany;
import com.xyz.trade.client.model.OrderModel;
import com.xyz.trade.client.model.TradeModel;
import com.xyz.trade.service.ITradeService;
import com.xyz.util.Page;
import com.xyz.util.PropertyFilter;

@Service
public class TradeServiceImpl extends RemoteServiceServlet implements TradeService {
    
	private static final long serialVersionUID = 1L;
	@Autowired
	private ITradeService ts;
	
	@Autowired
	private IBaseService bs;
    
	@Override
	 public PagingLoadResult<TradeModel> getTrades(PagingLoadConfig config,TradeModel tm) {
		Page<Trade> page = new Page<Trade>(config.getLimit());
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
			if(tm.getOwnerId()!=null&&tm.getOwnerId().trim().length()>0)
			    filters.add(new PropertyFilter("EQ_ownerId",tm.getOwnerId().trim()));
		}
		page = ts.queryPage(page, filters);
		List<TradeModel> lf = new ArrayList<TradeModel>();
		if(page.getResult()!=null&&page.getResult().size()>0)
		{
			for(Trade ic : page.getResult())
			{
				TradeModel f = new TradeModel();
				BeanUtils.copyProperties(ic, f,new String[]{"orders"});
				List<com.taobao.api.model.Order> lo = ts.getOrdersOfTrade(ic.getTid());
				List<OrderModel> orders = new ArrayList<OrderModel>();
				if(lo!=null&&lo.size()>0)
				{
					for(com.taobao.api.model.Order om : lo)
					{
						OrderModel o = new OrderModel();
						BeanUtils.copyProperties(om, o);
						//将商品的标题作为交易的标题（默认）
						f.setTitle(o.getTitle());
						orders.add(o);
					}
			    }
				f.setOrders(orders);
				lf.add(f);
			}
	    }
		return new BasePagingLoadResult<TradeModel>(lf, config.getOffset(),
				page.getTotalCount());
	}

	@Override
	public List<Code> getCusType() {
		List<BaseCode> lb = bs.queryByType(ZkbConstants.CODE_CUS_TYPE);
		List<Code> lf = new ArrayList<Code>();
		if(lb!=null)
		{
			for(BaseCode bc : lb)
			{
				Code f = new Code(bc.getKeyId(),bc.getName());
			    lf.add(f);
			}
		}
		return lf;
	}

	/**
	 * 查询今天交易情况返回一个数组
	 */
	public Map<String,String> getTodTraSta() {
		Map<String,String> mss = ts.countTrades();
		return mss;
	}

	@Override
	public boolean assignOwner(String[] tradeKeys, String userKey) {
		return ts.assignOwner(tradeKeys, userKey);
	}

	/**
	 * 查询交易订单
	*/
	public List<OrderModel> getOrdersOfTrade(String tid) {
		List<com.taobao.api.model.Order> lo = ts.getOrdersOfTrade(tid);
		List<OrderModel> lf = new ArrayList<OrderModel>();
		if(lo!=null&&lo.size()>0)
		{
			for(com.taobao.api.model.Order om : lo)
			{
				OrderModel o = new OrderModel();
				BeanUtils.copyProperties(om, o);
				lf.add(o);
			}
	    }
		return lf;
	}

	@Override
	public List<LogisticCompany> getLogisticCompanies() {
		List<com.taobao.api.model.LogisticCompany> lc = ts.getLogisticCompanies();
		List<LogisticCompany> ll = new ArrayList<LogisticCompany>();
		if(lc!=null&&lc.size()>0)
		{
			for(com.taobao.api.model.LogisticCompany l : lc)
			{
				LogisticCompany lcom = new LogisticCompany();
				BeanUtils.copyProperties(l, lcom);
				ll.add(lcom);
			}
		}
		return ll;
	}

	@Override
	public boolean deliverySend(Delivery deli) {
		DeliverySendRequest req = new DeliverySendRequest();
		BeanUtils.copyProperties(deli, req);
		return ts.deliverySend(req);
    }

}
