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
import com.xyz.order.model.ProductStockHistory;
import com.xyz.order.service.IProductStockHistoryServ;
import com.xyz.system.model.Shop;
import com.xyz.system.service.IUserService;
import com.xyz.util.DateUtil;

@Component("synchrStockTask")
public class SynchrStockTask {
	@Autowired @Qualifier("taobaoUserServ")
	private IUserService facade;
	@Autowired @Qualifier("productStockHistoryServ")
	private IProductStockHistoryServ stockFacade;
	
	private Shop tbuser;
	private TaobaoRestClient client;
	private CustomTaobaoClient customClient;
	private Date synchrStockTime;//记录库存同步时间

	// 延迟60秒执行,每隔24小时执行一次
    @Scheduled(fixedDelay=45*1000)
    public void run() {
			try {
				System.out.println("同步商品库存" + DateUtil.format(new Date(),"yyyy-MM-dd HH:mm:ss"));
				tbuser = facade.findAll().get(0);
				if (!tbuser.getIsAuthorize()) {
					return;
				}
				client = new TaobaoJsonRestClient(tbuser.getAppKey(), tbuser.getAppSecret());
				customClient = new CustomTaobaoClient(tbuser.getAppKey(), tbuser.getAppSecret());
				synchrStockTime = tbuser.getSynchrStockTime();
				Date dayTime = new Date();
				/*List<ItemextView> list = BossiniAPI.getQOHByModifyDate(synchrStockTime, dayTime);
				tbuser.setSynchrStockTime(dayTime);
				facade.update(tbuser);
				for(ItemextView itemextView : list){
					System.out.println("itemextView:" + itemextView.getXfPlu() + ":" + itemextView.getXfQoh());
					SkusOutGetRequest skusOutGetRequest = new SkusOutGetRequest();
					skusOutGetRequest.setOuterId(itemextView.getXfPlu()); 
					skusOutGetRequest.setFields("iid,sku_id,quantity,properties");
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
								req.setQuantity(new Double(itemextView.getXfQoh()).intValue()+"");
								
								SkuUpdateResponse rsp = client.skuUpdate(req, tbuser.getSessionKey());
								
								ProductStockHistory productStockHistory = new ProductStockHistory();
								productStockHistory.setTbiid(item.getIid());
								productStockHistory.setIsSuccess(rsp.isSuccess());
								productStockHistory.setErrorMsg(rsp.getMsg());
								productStockHistory.setQuantity(new Double(itemextView.getXfQoh()).intValue());
								productStockHistory.setSynchrTime(dayTime);
								productStockHistory.setTaobaoUser(tbuser);
								productStockHistory.setProductKey(itemextView.getXfPlu());
								stockFacade.save(productStockHistory);
							}
						}
					}else{
						System.out.println(skusOutGetResponse.getBody());
					}
				}*/
			} catch (TaobaoApiException e) {
				e.printStackTrace();
				tbuser.setSynchrStockTime(synchrStockTime);
				facade.update(tbuser);
			}
		}

}
