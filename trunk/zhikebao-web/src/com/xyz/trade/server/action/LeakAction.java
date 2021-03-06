/*
 * Generated by MyEclipse Struts
 * Template path: templates/java/JavaClass.vtl
 */
package com.xyz.trade.server.action;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.taobao.api.TaobaoApiException;
import com.taobao.api.TaobaoJsonRestClient;
import com.taobao.api.TaobaoRestClient;
import com.taobao.api.model.Item;
import com.taobao.api.model.ItemGetRequest;
import com.taobao.api.model.ItemGetResponse;
import com.taobao.api.model.ItemPropValuesGetRequest;
import com.taobao.api.model.ItemPropValuesResponse;
import com.taobao.api.model.Order;
import com.taobao.api.model.Shipping;
import com.taobao.api.model.Sku;
import com.taobao.api.model.SkuGetRequest;
import com.taobao.api.model.SkuGetResponse;
import com.taobao.api.model.Trade;
import com.taobao.api.model.TradeGetRequest;
import com.taobao.api.model.TradeGetResponse;
import com.xyz.order.service.IOrderService;
import com.xyz.system.model.Shop;
import com.xyz.system.service.IUserService;
import com.xyz.trade.service.ITradeService;
import com.xyz.util.TaobaoUtil;

/** 
 * 补单Action
 */
@Controller   @RequestMapping("/laek.do")
public class LeakAction {
	@Autowired @Qualifier("taobaoUserServ")
	private IUserService facade;
	@Autowired
	private ITradeService tradeFacade;
	@Autowired @Qualifier("orderServ")
	private IOrderService orderFacade;

	public String execute( HttpServletRequest request, HttpServletResponse response) throws TaobaoApiException{
		String tid = request.getParameter("tid");
		if(tid == null || tid.equals("")){
			return null;
		}
		Shop tbuser = facade.findAll().get(0);
		TaobaoRestClient client = new TaobaoJsonRestClient(tbuser.getAppKey(), tbuser.getAppSecret());
		
		Trade Trade = tradeFacade.findById(tid);
		if (Trade != null) {
			return null;
		}
		TradeGetRequest tgReq = new TradeGetRequest();
		tgReq.setFields("seller_memo,buyer_nick,created,tid,status,payment,discount_fee,adjust_fee,post_fee,total_fee,pay_time,end_time,modified,consign_time,alipay_no,buyer_message,buyer_email,has_postFee,shipping_type,orders");
		tgReq.setTid(tid);
		TradeGetResponse tgRsp = client.tradeFullInfoGet(tgReq, tbuser.getSessionKey());

		if (tgRsp.isSuccess()) {
			Trade trade = tgRsp.getTrade();
			
			tradeFacade.save(trade);
			
		}
		return null;
	}
}