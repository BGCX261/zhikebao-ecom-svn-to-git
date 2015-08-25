package com.xyz.product.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.taobao.api.TaobaoApiException;
import com.taobao.api.TaobaoJsonRestClient;
import com.taobao.api.TaobaoRestClient;
import com.taobao.api.model.ItemCatsGetRequest;
import com.taobao.api.model.ItemCatsResponse;
import com.taobao.api.model.ItemGetRequest;
import com.taobao.api.model.ItemGetResponse;
import com.taobao.api.model.ItemsGetResponse;
import com.taobao.api.model.ItemsOnSaleGetRequest;
import com.taobao.api.util.DateUtil;
import com.xyz.StaticValMethod;
import com.xyz.framework.data.DataUtil;
import com.xyz.framework.log.Logger;
import com.xyz.product.dao.IItemCatDao;
import com.xyz.product.dao.IItemDao;
import com.xyz.product.model.Item;
import com.xyz.product.model.ItemCat;
import com.xyz.product.model.ItemImg;
import com.xyz.product.model.PropImg;
import com.xyz.product.model.Sku;
import com.xyz.product.model.Video;
import com.xyz.product.service.IProdService;
import com.xyz.system.dao.IShopDao;
import com.xyz.system.model.Shop;
import com.xyz.util.Page;
import com.xyz.util.PropertyFilter;
import com.xyz.util.SpringSecurityUtil;
import com.xyz.util.TaobaoUtil;

@Service
public class ProdService implements IProdService {
	
	@Autowired
	public IItemDao idao;
	
	@Autowired
	public IItemCatDao icdao;
	
	@Autowired
	public IShopDao sao;

	@Override
	public List<ItemCat> getCategories(String parentId) {
		 List<ItemCat> li = icdao.findBy("parentCid", parentId);
		 if(li==null||li.size()<1)
			 li = initItemCats(parentId);
		return li;
	}
	
	/**
	 * 初始化商品类别列表
	 */
	private List<ItemCat> initItemCats(String parentId)
	{
		TaobaoRestClient client;
		List<ItemCat> itemCats = null;
		try {
			Shop shop = SpringSecurityUtil.getShop();
			Assert.notNull(shop, "taobaoUser不能为空");
			System.out.println("查询所有的商品分类");
			client = TaobaoUtil.getService(shop);

			ItemCatsGetRequest req = new ItemCatsGetRequest();
			req.setFields("cid,name,status,sort_order,parent_cid,is_parent");
			req.setParentCid(parentId);
			req.setDatetime(DateUtil.strToDate("1970-1-1 00:00:00"));

			ItemCatsResponse rsp = client.itemCatsGet(req);
			if(rsp.isSuccess())
			{
				itemCats = new ArrayList<ItemCat>();
				List<com.taobao.api.model.ItemCat> cats = rsp.getItemCats();
				for (com.taobao.api.model.ItemCat cat : cats) {
					ItemCat itemCat = new ItemCat();
					BeanUtils.copyProperties(cat, itemCat);
					icdao.save(itemCat);
					itemCats.add(itemCat);
				}
			}
		} catch (Exception e) {
			Logger.error(getClass(), e.getStackTrace().toString());
		}
		return itemCats;
	}
	/**
	 * 保存商品信息
	 * @param entity
	 */
	public void save(Item entity) {
		Logger.info(getClass(),"保存商品信息");
		try {
			idao.save(entity);
			Logger.info(getClass(),"保存成功");
		} catch (RuntimeException re) {
			Logger.error(getClass(),"save failed");
			throw re;
		}
	}
	
	/**
     * 根据Item的id查询
     */
	public Item querySingleItem(String sqlString) {
		try {
			return idao.findUnique(sqlString);
		} catch (RuntimeException re) {
			Logger.info(getClass(), "没有查询到结果");
		}
		return null;
	}
	
	/**
	 * 分页查询指定卖家的在售商品列表列表
	 * @param page
	 * @return
	 */
	public Page<Item> queryPage(Page<Item> page,List<PropertyFilter> filters) {
		Logger.info(getClass(),"根据卖家查询在售商品列表");
		Shop shop = SpringSecurityUtil.getShop();
		Assert.notNull(shop, "shop不能为空");
		try {
			filters.add(new PropertyFilter("EQ_nick",shop.getTbAccount()));
            return idao.findPage(page,filters);
		} catch (RuntimeException re) {
			Logger.error(getClass(),"在售商品列表失败");
			throw re;
		}
	}
	/**
	 * 同步在售商品
	 */
	public boolean syncOnSaleItems()
	{
		System.out.println("同步在售商品" + com.xyz.util.DateUtil.format(com.xyz.util.DateUtil.getNow(),"yyyy-MM-dd HH:mm:ss"));
		Shop shop = SpringSecurityUtil.getShop();
		if (!shop.getIsAuthorize()) 
		{
			Logger.warn(this.getClass(),"必须有淘宝会话");
			return false;
		}
		ItemsOnSaleGetRequest req = new ItemsOnSaleGetRequest();
		req.setFields(StaticValMethod.FIELDS_TAOBAO_ITEMS_ONSALE_GET);
		int totalRow = 100 ; //总记录数 
		int readRow = 0 ; //已读取的记录数
		int pageSize = 40;
		int pageNo = 0;
		do{
			TaobaoRestClient client = TaobaoUtil.getService(shop);
			try {
				req.setPageNo(pageNo++);
				req.setPageSize(pageSize);
				ItemsGetResponse rsp = client.itemsOnSaleGet(req, shop.getSessionKey());
				if(rsp.isSuccess())
				{
					totalRow = rsp.getTotalResults();
					List<com.taobao.api.model.Item> li = rsp.getItems();
					if(li!=null&&li.size()>0)
					{
						readRow += li.size();
						for(com.taobao.api.model.Item im : li)
						{
							Item localItem = idao.findUniqueBy("iid", im.getIid());
							if(localItem!=null&&localItem.getModified().after(im.getModified()))
							{
								continue;
							}
							persistItem(localItem,im);
						}
					}
				}
			 } catch (TaobaoApiException e) {
				Logger.error(this.getClass(), "同步中出现问题，请重试："+e.getStackTrace());
				return false;
			}
		}while(readRow<totalRow);
		return true;
	}
	
	/**
	 * 从订单中提取商品信息
	 */
	private void drawItem(String iid) {
		// 根据IId获取商品信息
		Shop shop = SpringSecurityUtil.getShop();
		try {
			Item localItem = idao.findUniqueBy("iid", iid);
			if (localItem == null) {
				TaobaoRestClient client = TaobaoUtil.getService(shop);
				ItemGetRequest itemReq = new ItemGetRequest();
				itemReq.setFields(StaticValMethod.FIELDS_TAOBAO_ITEM_GET);
				itemReq.setIid(iid);
				itemReq.setNick(shop.getTbAccount());
				ItemGetResponse itemRsp = client.itemGet(itemReq, shop
						.getSessionKey());
				if (itemRsp.isSuccess()) {
					com.taobao.api.model.Item im = itemRsp.getItem();
					if(localItem!=null&&localItem.getModified().after(im.getModified()))
					{
						return;
					}
					this.persistItem(localItem,im);
				}
			}
		} catch (TaobaoApiException e) {
			Logger.error(getClass(), "商品:" + iid + "不能正常提取/"
					+ e.getStackTrace().toString());

		}
	}
	
	private void persistItem(Item localItem,com.taobao.api.model.Item im)
	{
		if(localItem == null)
			localItem = new Item();
		BeanUtils.copyProperties(im, localItem);
		//拷贝
		List<com.taobao.api.model.ItemImg> litemImgs = im.getItemImgs();
		if(litemImgs!=null&&litemImgs.size()>0)
		{
			List<ItemImg> localItemImgs = new ArrayList<ItemImg>();
			ItemImg ii ;
			for(com.taobao.api.model.ItemImg img : litemImgs)
			{
				ii = new ItemImg();
				BeanUtils.copyProperties(img, ii,new String[]{"item"});
				localItemImgs.add(ii);
			}
			localItem.setItemImgs(localItemImgs);
		}
		List<com.taobao.api.model.PropImg> propImgs = im.getPropImgs();
		if(propImgs!=null&&propImgs.size()>0)
		{
			List<PropImg> localPropImgs = new ArrayList<PropImg>();
			PropImg pi ;
			for(com.taobao.api.model.PropImg img : propImgs)
			{
				pi = new PropImg();
				BeanUtils.copyProperties(img, pi,new String[]{"item"});
				localPropImgs.add(pi);
			}
			localItem.setPropImgs(localPropImgs);
		}
		List<com.taobao.api.model.Sku> skus = im.getSkus();
		if(skus!=null&&skus.size()>0)
		{
			List<Sku> localSkus = new ArrayList<Sku>();
			Sku sku ;
			for(com.taobao.api.model.Sku rsku : skus)
			{
				sku = new Sku();
				BeanUtils.copyProperties(rsku, sku,new String[]{"item"});
				localSkus.add(sku);
			}
			localItem.setSkus(localSkus);
		}
		List<com.taobao.api.model.Video> videos = im.getVideos();
		if(videos!=null&&videos.size()>0)
		{
			List<Video> localVideos = new ArrayList<Video>();
			Video video ;
			for(com.taobao.api.model.Video rvideo : videos)
			{
				video = new Video();
				BeanUtils.copyProperties(rvideo, video,new String[]{"item"});
				localVideos.add(video);
			}
			localItem.setVideos(localVideos);
		}
		if(localItem.getPid()!=null&&localItem.getPid()>0)
		    idao.update(localItem);
		else
			idao.save(localItem);
	}
	
	/**
	 * 同步库存列表
	 * @return
	 */
	public boolean syncItems()
	{
		System.out.println("同步商品库存" + com.xyz.util.DateUtil.format(com.xyz.util.DateUtil.getNow(),"yyyy-MM-dd HH:mm:ss"));
		Shop shop = SpringSecurityUtil.getShop();
		if (!shop.getIsAuthorize()) 
		{
			Logger.warn(this.getClass(),"必须有淘宝会话");
			return false;
		}
		Date synchrStockTime = shop.getSynchrStockTime();//记录库存同步时间
		try {
			TaobaoRestClient client = TaobaoUtil.getService(shop);
			synchrStockTime = shop.getSynchrStockTime();
			/*List<ItemextView> list = BossiniAPI.getQOHByModifyDate(synchrStockTime, dayTime);
			shop.setSynchrStockTime(dayTime);
			sao.update(shop);
			for(ItemextView itemextView : list){
				System.out.println("itemextView:" + itemextView.getXfPlu() + ":" + itemextView.getXfQoh());
				SkusOutGetRequest skusOutGetRequest = new SkusOutGetRequest();
				skusOutGetRequest.setOuterId(itemextView.getXfPlu()); 
				skusOutGetRequest.setFields("iid,sku_id,quantity,properties");
				SkusOutGetResponse skusOutGetResponse = client.skusOutGet(skusOutGetRequest, shop.getSessionKey(), shop.getTbAccount());
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
		} catch (Exception e) {
			e.printStackTrace();
			shop.setSynchrStockTime(synchrStockTime);
			sao.update(shop);
		}
		return false;
	}

}
