package com.xyz.system.task.impl;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import com.taobao.api.TaobaoApiException;
import com.taobao.api.TaobaoRestClient;
import com.taobao.api.model.Item;
import com.taobao.api.model.ItemGetRequest;
import com.taobao.api.model.ItemGetResponse;
import com.taobao.api.model.Order;
import com.taobao.api.model.Trade;
import com.taobao.api.model.TradeGetRequest;
import com.taobao.api.model.TradeGetResponse;
import com.taobao.api.model.TradesGetResponse;
import com.taobao.api.model.TradesSoldGetRequest;
import com.taobao.api.model.UserGetRequest;
import com.taobao.api.model.UserGetResponse;
import com.xyz.customer.service.ICustomerService;
import com.xyz.framework.data.DataUtil;
import com.xyz.framework.log.Logger;
import com.xyz.order.service.IOrderService;
import com.xyz.product.service.IProdService;
import com.xyz.system.model.Shop;
import com.xyz.system.model.User;
import com.xyz.system.service.IUserService;
import com.xyz.system.task.ISysTask;
import com.xyz.trade.service.ITradeService;
import com.xyz.util.DateUtil;
import com.xyz.util.SpringSecurityUtil;
import com.xyz.util.TaobaoUtil;

/***
 * 各种系统任务接口的实现类
 * @author val
 *
 */
public class SystemTask implements ISysTask {
	@Autowired
	public IUserService facade;
	@Autowired
	public ITradeService tradeFacade;
	@Autowired
	private IOrderService orderFacade;
	@Autowired
	private IProdService prodFacade;
    @Autowired
    private ICustomerService cusFacede;
	
    private Shop shop;
    private TaobaoRestClient client;
    private String tid;
    private String title;

	/**
	 * 定时获取最新的交易信息
	 */
    public void tradeSysn() {
			try {
				System.out.println("自动获取淘宝交易订单任务" + DateUtil.format(new Date(), "yyyy-MM-dd HH:mm:ss"));
				User user = SpringSecurityUtil.getCurrentUser();
				shop = user.getShop();
				client = TaobaoUtil.getService(shop);
				TradesSoldGetRequest req = new TradesSoldGetRequest();
				req.setFields("tid");
				req.setPageSize(100);
				req.setStatus("WAIT_SELLER_SEND_GOODS");
				TradesGetResponse rsp = null;
				if (shop.getIsAuthorize()) {
					rsp = client.tradesSoldGet(req, shop.getSessionKey());
				} else {
					return;
				}
				if (rsp != null && rsp.isSuccess()) {
					if (rsp.getTrades() == null) {
						return;
					}
					Set<String> nicks = new HashSet<String>();
					for (Trade trade : rsp.getTrades()) {
						try {
							tid = trade.getTid();
							Trade tradeInfo = tradeFacade.querySingle("tid = " + DataUtil.toSqlStr(tid));
							if (tradeInfo != null) {
								nicks.add(tradeInfo.getBuyerNick());
								continue;
							}
							TradeGetRequest tgReq = new TradeGetRequest();
							tgReq.setFields("seller_memo,buyer_nick,created,tid,title,status,payment,discount_fee,adjust_fee,post_fee,total_fee,pay_time,end_time,modified,consign_time,alipay_no,buyer_message,buyer_email,has_postFee,shipping_type,orders");
							tgReq.setTid(tid);
							TradeGetResponse tgRsp = client.tradeFullInfoGet(tgReq, shop.getSessionKey());
                            List<Order> lo = null;
							if (tgRsp.isSuccess()) {
								trade = tgRsp.getTrade();
								
								nicks.add(tradeInfo.getBuyerNick());
								lo = trade.getOrders();
								for (Order order : lo) {
									order.setTrade(trade);
									//提取商品信息
									drawItem(order.getIid());
								}
								trade.setOrders(lo);
								tradeFacade.save(trade);
							}
							
						} catch (Exception ex) {
							Logger.error(getClass(),"订单号:" + tid + ":" + title + ":" + "导致系统级错误!");
							ex.printStackTrace();
							//continue;
						}
						
					}
					//提取客户信息
					if(nicks.size()>0)
						cusFacede.drawCoustomer(nicks);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
    /**
     * 定时请求Taobao保持已有会话
     */
	public void holdAuthorize() { 
		try {
			Shop tbuser = SpringSecurityUtil.getShop();
			Assert.notNull(tbuser, "当前店铺不能为空");
			System.out.println("自动延续授权任务" + DateUtil.format(new Date(),"yyyy-MM-dd HH:mm:ss"));
			client = TaobaoUtil.getService(tbuser);
			UserGetRequest req = new UserGetRequest();
			String publicFields = "nick";
			String privateFields = "type,auto_repost,promoted_type,status,consumer_protection";
			req.setFields(publicFields + "," + privateFields);
			req.setNick(tbuser.getTbAccount());
			
			UserGetResponse rsp = client.userGet(req, tbuser.getSessionKey());
		    //TradesBoughtGetRequest tbReq = new TradesBoughtGetRequest().withFields("seller_nick,buyer_nick,iid,title,price,num,type,tid,payment,status,orders,refund_status").withStatus("TRADE_FINISHED");
		    //TradesGetResponse tbRsp = client.tradesBoughtGet(tbReq,  tbuser.getSessionKey());// Request
		    if (rsp.isSuccess()) {
				if (!tbuser.getIsAuthorize()) {
					tbuser.setAuthorizeTime(new Date());
					tbuser.setIsAuthorize(true);
					facade.update(tbuser);
				}
			} else {
				if (tbuser.getIsAuthorize()) {
					tbuser.setIsAuthorize(false);
					facade.update(tbuser);
				}
			}
		} catch (TaobaoApiException e) {
			e.printStackTrace();
		}
	}
	
	
   /**
    * 从订单中提取商品信息
    */
   private void drawItem(String iid)
   {
	// 根据IId获取商品信息
		try {
			Item item = prodFacade.querySingleItem("iid = " + DataUtil.toSqlStr(iid));
			if(item==null)
			{
				ItemGetRequest itemReq = new ItemGetRequest();
				itemReq.setFields("iid,title,pic_path,cid,property_alias,detail_url,num_iid,nick," +
						"type,cid,seller_cids,props,input_pids,input_str,num,pic_path,outer_id," +
						"item_img,prop_img,sku");
				itemReq.setIid(iid);
				itemReq.setNick(shop.getTbAccount());
				ItemGetResponse itemRsp = client.itemGet(itemReq, shop.getSessionKey());
				if (itemRsp.isSuccess()) {
					item = itemRsp.getItem();
					prodFacade.save(item);
				}
			}
		} catch (TaobaoApiException e) {
			Logger.error(getClass(),"商品:" + iid + "不能正常提取/"+e.getStackTrace().toString());
			
		}
   }
}
