package com.xyz.base.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.taobao.api.TaobaoApiException;
import com.taobao.api.TaobaoRestClient;
import com.taobao.api.model.AreasGetRequest;
import com.taobao.api.model.AreasGetResponse;
import com.taobao.api.model.Shop;
import com.taobao.api.model.ShopGetRequest;
import com.taobao.api.model.ShopGetResponse;
import com.xyz.StaticValMethod;
import com.xyz.base.dao.IAreaDao;
import com.xyz.base.dao.IBaseDao;
import com.xyz.base.dao.IShopInfoDao;
import com.xyz.base.service.IBaseService;
import com.xyz.resources.model.Area;
import com.xyz.resources.model.BaseCode;
import com.xyz.util.Page;
import com.xyz.util.SpringSecurityUtil;
import com.xyz.util.TaobaoUtil;

@Service
public class BaseService implements IBaseService {
	
	@Autowired
	private IBaseDao bdao;
	@Autowired
	private IShopInfoDao sdao;
	@Autowired
	private IAreaDao adao;

	@Transactional(propagation=Propagation.REQUIRED)
	public void delete(BaseCode entity) {
		if(0!=entity.getParentId())
		{
		   BaseCode parent = bdao.get(entity.getParentId());
		   if(parent!=null)
		   {
		     Set<BaseCode> sk = parent.getChildren();
		     sk.remove(entity);
		     parent.setChildren(sk);
		     bdao.update(parent);
		   }
		}
		bdao.delete(entity.getPid());
		
	}

	@Transactional
	public Page<BaseCode> findAll(Page page) {
		
		return bdao.getAll(page);
	}

	@Override
	public BaseCode findById(Integer id) {
		//Key k = KeyFactory.stringToKey(id);
		// TODO Auto-generated method stub
		return bdao.get(id);
	}

	@Override
	public Page<BaseCode> findByProperty(String propertyName, Object value,
			Page page) {
		// TODO Auto-generated method stub
		return bdao.findBy(page, propertyName, value);
	}

	public BaseCode save(BaseCode entity) {
		bdao.save(entity);
		if(0!=entity.getParentId())
		{
		   BaseCode parent = bdao.get(entity.getParentId());
		   if(parent!=null)
		   {
		     Set<BaseCode> sk = parent.getChildren();
		     if(sk==null)
		    	 sk = new HashSet<BaseCode>();
		     sk.add(entity);
		     parent.setChildren(sk);
		     bdao.update(parent);
		   }
		}
		return entity;
	}

	@Override
	public void update(BaseCode entity) {
		// TODO Auto-generated method stub
		 bdao.update(entity);
	}

	@Override
	public List<BaseCode> queryByType(Long parentId) {
		Assert.notNull(parentId,"类别不能为空");
		Map<Integer, List<BaseCode>> ml = bdao.getAllCodes();
		return ml.get(parentId);
	}
	
	/**
	 * 获取商家的基本信息
	 */
    public Shop getShopInfo()
    {
    	Shop shop = null;
    	
    	com.xyz.system.model.Shop session = SpringSecurityUtil.getShop();
    	shop = sdao.findUniqueBy("nick", session.getTbAccount());
    	if(shop==null)
    	{
    	   TaobaoRestClient client = TaobaoUtil.getService(session);
    	   ShopGetRequest req = new ShopGetRequest();
    	   req.setFields(StaticValMethod.FIELDS_TAOBAO_SHOP_GET);
    	   req.setNick(session.getTbAccount());		 
    	   try {
    		   ShopGetResponse rsp= client.shopGet(req);
				if(rsp.isSuccess())
				{
					shop = rsp.getShop();
					if(shop.getBulletin()!=null&&shop.getBulletin().length()>500)
					   shop.setBulletin(shop.getBulletin().substring(0, 500));
					sdao.save(shop);
				}
			} catch (TaobaoApiException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 	
        }
    	return shop;
    }

    /**
     * 获取标准地区码.
     * @param name
     * @return
     */
    private void initAreas()
    {
 		com.xyz.system.model.Shop shop = SpringSecurityUtil.getShop();
		Assert.notNull(shop, "商家不能为空");
		TaobaoRestClient client = TaobaoUtil.getService(shop);
		AreasGetRequest request = new AreasGetRequest();
		request.setField(StaticValMethod.FIELDS_TAOBAO_AREAS_GET);
		try {
			AreasGetResponse response = client.areasGet(request);
			if(response.isSuccess())
			{
				List<com.taobao.api.model.Area> orila = response.getAreas();
				Area zarea ;
				for(com.taobao.api.model.Area a : orila)
				{
					zarea = new Area();
					BeanUtils.copyProperties(a, zarea);
					adao.save(zarea);
				}
			}
		} catch (TaobaoApiException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

    }

	@Override
	public List<Area> queryAreas(Integer parentId) {
		List<Area> la = adao.find(" parentId = "+parentId, " areaId ");
		if(la==null||la.size()<1)
		{
			initAreas();
			la = adao.find(" parentId = "+parentId, " areaId ");
		}
		return la;
	}

	@Override
	public Area getAreaByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

}
