package com.xyz.system.task.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.taobao.api.TaobaoApiException;
import com.taobao.api.TaobaoJsonRestClient;
import com.taobao.api.TaobaoRestClient;
import com.taobao.api.model.Item;
import com.taobao.api.model.ItemGetRequest;
import com.taobao.api.model.ItemGetResponse;
import com.taobao.api.model.Sku;
import com.taobao.api.model.SkuUpdateRequest;
import com.taobao.api.model.SkuUpdateResponse;
import com.xyz.framework.util.CustomTaobaoClient;
import com.xyz.order.model.ProductPriceHistory;
import com.xyz.order.service.IProductPriceHistoryServ;
import com.xyz.system.model.Shop;
import com.xyz.system.service.IUserService;
import com.xyz.util.DateUtil;
@Component("synchrPriceTask")
public class SynchrPriceTask {
	@Autowired @Qualifier("taobaoUserServ")
	private IUserService facade;
	@Autowired @Qualifier("productPriceHistoryServ")
	private IProductPriceHistoryServ priceFacade;
	
	private Shop tbuser;
	private TaobaoRestClient client;
	private CustomTaobaoClient customClient;
	private Date synchrPriceTime;//记录价格同步时间
	
	// 延迟60秒执行,每隔24小时执行一次
    @Scheduled(fixedDelay=45*1000)
	public void run() {
			try {
				System.out.println("同步商品价格" + DateUtil.format(new Date(),"yyyy-MM-dd HH:mm:ss"));
				tbuser = facade.findAll().get(0);
				if (!tbuser.getIsAuthorize()) {
					return;
				}
				client = new TaobaoJsonRestClient(tbuser.getAppKey(), tbuser.getAppSecret());
				customClient = new CustomTaobaoClient(tbuser.getAppKey(), tbuser.getAppSecret());
				synchrPriceTime = tbuser.getSynchrPriceTime();
				Date dayTime = new Date();
				/*List<ItemPriceView> list = BossiniAPI.getPreceModifyDate(synchrPriceTime, dayTime);
				tbuser.setSynchrPriceTime(dayTime);
				facade.update(tbuser);
				for(ItemPriceView itemPriceView : list){
					System.out.println("itemPriceView:" + itemPriceView.getXfPlu() + ":" + itemPriceView.getPrice());
					SkusOutGetRequest skusOutGetRequest = new SkusOutGetRequest();
					skusOutGetRequest.setOuterId(itemPriceView.getXfPlu()); 
					skusOutGetRequest.setFields("iid,sku_id,price,properties");
					SkusOutGetResponse skusOutGetResponse = customClient.skusOutGet(skusOutGetRequest, tbuser.getSessionKey(), tbuser.getTbAccount());
					if(skusOutGetResponse.isSuccess()){
						Sku sku = skusOutGetResponse.getSku();
						if(sku != null){
							ItemGetRequest itemReq = new ItemGetRequest();
							itemReq.setFields("iid");
							itemReq.setNumIid(sku.getIid());
							itemReq.setNick(tbuser.getTbAccount());
							ItemGetResponse itemRsp  = client.itemGet(itemReq, tbuser.getSessionKey());
							if(itemRsp.isSuccess()){
								Item item = itemRsp.getItem();
								SkuUpdateRequest req = new SkuUpdateRequest();
								req.setIid(item.getIid());
								req.setProperties(sku.getProperties());
								req.setPrice(itemPriceView.getPrice()+"");
								SkuUpdateResponse rsp = client.skuUpdate(req, tbuser.getSessionKey());
								
								ProductPriceHistory productPriceHistory = new ProductPriceHistory();
								productPriceHistory.setTbiid(item.getIid());
								productPriceHistory.setIsSuccess(rsp.isSuccess());
								productPriceHistory.setErrorMsg(rsp.getMsg());
								productPriceHistory.setPrice(itemPriceView.getPrice());
								productPriceHistory.setSynchrTime(dayTime);
								productPriceHistory.setTaobaoUser(tbuser);
								productPriceHistory.setProductKey(itemPriceView.getXfPlu());
								priceFacade.save(productPriceHistory);
							}
						}
					}else{
						System.out.println(skusOutGetResponse.getBody());
					}
				}*/
			} catch (TaobaoApiException e) {
				e.printStackTrace();
				tbuser.setSynchrPriceTime(synchrPriceTime);
				facade.update(tbuser);
			}
		}
}
