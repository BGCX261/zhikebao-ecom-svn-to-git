package com.xyz.system.task.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import com.taobao.api.TaobaoApiException;
import com.taobao.api.TaobaoRestClient;
import com.taobao.api.model.ItemGetRequest;
import com.taobao.api.model.ItemGetResponse;
import com.taobao.api.model.UserGetRequest;
import com.taobao.api.model.UserGetResponse;
import com.xyz.StaticValMethod;
import com.xyz.framework.data.DataUtil;
import com.xyz.framework.log.Logger;
import com.xyz.product.model.Item;
import com.xyz.product.service.IProdService;
import com.xyz.system.model.Shop;
import com.xyz.system.service.IUserService;
import com.xyz.system.task.ISysTask;
import com.xyz.trade.service.ITradeService;
import com.xyz.util.DateUtil;
import com.xyz.util.Page;
import com.xyz.util.TaobaoUtil;

/***
 * 各种系统任务接口的实现类
 * 
 * @author val
 * 
 */
public class SystemTask implements ISysTask {
	@Autowired
	public IUserService facade;
	@Autowired
	public ITradeService tradeFacade;
	@Autowired
	private IProdService prodFacade;

	private Shop shop;
	private TaobaoRestClient client;


	/**
	 * 定时请求Taobao保持已有会话
	 */
	public void holdAuthorize() { 
		try {
			Page<Shop> shops = facade.getActiveShops();
			if(shops!=null&&shops.getResult()!=null&&shops.getResult().size()>0)
			{
				for(Shop shop : shops.getResult())
				{
					Assert.notNull(shop, "当前店铺不能为空");
					System.out.println("自动延续授权任务" + DateUtil.format(DateUtil.getNow(),"yyyy-MM-dd HH:mm:ss"));
					client = TaobaoUtil.getService(shop);
					UserGetRequest req = new UserGetRequest();
					String publicFields = "nick";
					String privateFields = "type,auto_repost,promoted_type,status,consumer_protection";
					req.setFields(publicFields + "," + privateFields);
					req.setNick(shop.getTbAccount());
					
					UserGetResponse rsp = client.userGet(req, shop.getSessionKey());
				    //TradesBoughtGetRequest tbReq = new TradesBoughtGetRequest().withFields("seller_nick,buyer_nick,iid,title,price,num,type,tid,payment,status,orders,refund_status").withStatus("TRADE_FINISHED");
				    //TradesGetResponse tbRsp = client.tradesBoughtGet(tbReq,  tbuser.getSessionKey());// Request
				    if (rsp.isSuccess()) {
						if (!shop.getIsAuthorize()) {
							shop.setAuthorizeTime(DateUtil.getNow());
							shop.setIsAuthorize(true);
							facade.update(shop);
						}
					} else {
						if (shop.getIsAuthorize()) {
							shop.setIsAuthorize(false);
							facade.update(shop);
						}
					}
				}
			}
		} catch (TaobaoApiException e) {
			e.printStackTrace();
		}
	}

	
}
