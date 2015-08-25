package com.xyz.system.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import net.sf.gilead.gwt.PersistentRemoteService;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.extjs.gxt.ui.client.data.BasePagingLoadResult;
import com.extjs.gxt.ui.client.data.BeanModel;
import com.extjs.gxt.ui.client.data.PagingLoadConfig;
import com.extjs.gxt.ui.client.data.PagingLoadResult;
import com.xyz.base.service.IBaseService;
import com.xyz.customer.service.ICustomerService;
import com.xyz.framework.log.Logger;
import com.xyz.product.service.IProdService;
import com.xyz.resources.model.Area;
import com.xyz.resources.model.BaseCode;
import com.xyz.system.client.GeneralService;
import com.xyz.system.model.Authority;
import com.xyz.system.model.Resource;
import com.xyz.system.model.Shop;
import com.xyz.system.model.User;
import com.xyz.system.service.ISecurityService;
import com.xyz.system.task.ISysTask;
import com.xyz.trade.service.ITradeService;
import com.xyz.util.Page;
import com.xyz.util.PropertyFilter;
import com.xyz.util.SpringSecurityUtil;

@Service
public class GeneralServiceImpl extends PersistentRemoteService implements GeneralService {
    
	private static final long serialVersionUID = 1L;
	@Autowired
	private IBaseService bs;
	
	@Autowired
	private ISecurityService ss;
	
	@Autowired
	private ISysTask sysTask;

	@Autowired
	private ITradeService ts;
	@Autowired
	private ICustomerService cs;
	@Autowired
	private IProdService ps ;
	@Autowired
	ISecurityService sfacade ;
    
	@Override
	public List<BaseCode> getCodes(Integer parentId) {
		Page<BaseCode> page = new Page<BaseCode>(100);
		
		page = bs.findByProperty("parentId", parentId, page);
		/*List<Code> lf = new ArrayList<Code>();
		if(page.getResult()!=null&&page.getResult().size()>0)
		{
			for(BaseCode bc : page.getResult())
			{
				boolean hasChild = (bc.getChildren()!=null&&bc.getChildren().size()>0)?true:false;
				Code f = new Code(new Long(bc.getPid()),bc.getName(),bc.getInx(),hasChild);
				lf.add(f);
			}
		}*/
		return page.getResult();
	}


	@Override
	public BaseCode saveCode(BaseCode code) {
		if(code.getPid()==null||code.getPid()<1)
		{
		    bs.save(code);
		}else{
			bs.update(code);
		}
		return code;
	}

	@Override
	public boolean delCode(BaseCode code) {
		BaseCode bc = bs.findById(code.getPid());
		bs.delete(bc);
		return true;
	}

	/**
	 * 分页查询功能列表
	 */
	public PagingLoadResult<Resource> getResources(PagingLoadConfig config) {
		Page<Resource> page = new Page<Resource>(config.getLimit());
		page.setStart(config.getOffset());
		List<PropertyFilter> filters = new ArrayList<PropertyFilter>();
		page = ss.queryResources(page, filters);
		
		return new BasePagingLoadResult<Resource>(page.getResult(), config.getOffset(),
				page.getTotalCount());
	}

	@Override
	public Resource saveResource(Resource fres) {
		com.xyz.system.model.Resource res = null;
		/*if(fres.getPid()!=null&&fres.getPid()>0)
			res = ss.getResource(fres.getPid());
		else
		   res = new com.xyz.system.model.Resource();
		BeanUtils.copyProperties(fres, res);*/
		
		ss.saveResource(fres);
		//fres.setPid(pid)
		return fres;
	}

	@Override
	public boolean delResource(Integer key) {
		ss.delResource(key);
		return true;
	}
	
	@Override
	public boolean delAuthority(Integer key) {
		ss.delAuthority(key);
		return true;
	}

	@Override
	public PagingLoadResult<Authority> getAuthorities(PagingLoadConfig config) {
		Page<Authority> page = new Page<Authority>(config.getLimit());
		page.setStart(config.getOffset());
		List<PropertyFilter> filters = new ArrayList<PropertyFilter>();
		page = ss.queryAuthorities(page, filters);
		return new BasePagingLoadResult<Authority>(page.getResult(), config.getOffset(),
				page.getTotalCount());
	}

	@Override
	public Authority saveAuthority(Authority fauth) {
		ss.saveAuth(fauth);
		/*User user = SpringSecurityUtil.getCurrentUser();
		List<com.xyz.system.model.Authority> as = user.getZkbAuthorities();
		if(as==null)
			as = new ArrayList<com.xyz.system.model.Authority>();
		as.add(fauth);
		user.setZkbAuthorities(as);
		ss.update(user);*/
		return fauth;
	}

	@Override
	public List<Resource> getResourcesOfAuth(Integer key) {
		Authority auth = ss.getAuthority(key);
		List<Resource> ress = auth.getResources();
		return ress;
	}

	/**
	 * 更新岗位的职责列表，同时更新相应职责所对应的岗位字符串
	 */
	public boolean saveResOfAuth(Integer authKey, List<Resource> ress) {
		Authority auth = null;
		try {
			auth = ss.getAuthority(authKey);
			List<Resource> lres = new ArrayList<Resource>();
			for(Resource r : ress)
			{
				Integer key = r.getPid();
				com.xyz.system.model.Resource bres = ss.getResource(key);
				lres.add(bres);
				/*List<com.xyz.system.model.Authority> auths = bres.getAuthorities();
				if(auths==null)
					auths = new ArrayList<com.xyz.system.model.Authority>();
				else if(auths.contains(auth))
					 continue;
				auths.add(e)
				bres.setAuthorities(auths);
				ss.updateResource(bres);*/
			}
			auth.setResources(lres);
			ss.updateAuth(auth);
		} catch (Exception e) {
			e.printStackTrace();
			Logger.error(getClass(), e.getStackTrace().toString());
			 return false;
		}
		return true;
     }

	/**
	 * 分页查询用户列表
	 */
	public PagingLoadResult<User> getUsers(PagingLoadConfig config) {
		User user = SpringSecurityUtil.getCurrentUser();
		Shop shop = SpringSecurityUtil.getShop();
		List<User> lf = new ArrayList<User>();
		if(shop==null)
			return null;
		
		Page<User> page = new Page<User>(config.getLimit());
		page.setStart(config.getOffset());
		List<PropertyFilter> filters = new ArrayList<PropertyFilter>();
		if("admin".equals(user.getUsername()))
		{
			filters.add(new PropertyFilter("EQ_isAdmin",true));
		}else{
		    filters.add(new PropertyFilter("EQ_shopKey",shop.getPid()));
		}
		filters.add(new PropertyFilter("EQ_deleted",false));
		page = ss.queryUsers(page, filters);
        return new BasePagingLoadResult<User>(page.getResult(), config.getOffset(),
				page.getTotalCount());
	}

	/**
	 * 检查用户名是否已被他人使用
	 */
	public boolean checkUsername(String name) {
		if(ss.findUserByLoginName(name)!=null)
		   return false;
		else
		   return true;
	}

	/**
	 * 查询当前用户可分配出去的岗位
	 */
	public List<Authority> getEnassignAuths() {
		Page<Authority> page = new Page<Authority>(10);
		page.setStart(0);
		List<PropertyFilter> filters = new ArrayList<PropertyFilter>();
		page = ss.queryAuthorities(page, filters);
		return page.getResult();
	}

	/**
	 * 保存一个用户
	 */
	public User saveUser(User user) {
		//User user = userModel.getBean();
		boolean isNew = false;
		if(user.getPid()==null||user.getPid()<1)
		{
			isNew = true;
			Shop shop = SpringSecurityUtil.getShop();
			user.setShop(shop);
			user.setShopKey(shop.getPid());
		}
		Authority auth1 = sfacade.findAuth("A_USER");
		List<Authority> keys = user.getZkbAuthorities();
		if(keys==null)
			keys = new ArrayList<Authority>();
		if(!keys.contains(auth1))
		{
		   keys.add(auth1);
		   user.setZkbAuthorities(keys);
		}
		if(isNew)
			user = ss.saveUser(user);
		else
		{
			user = ss.update(user);
		}
	    return user;
	}

	@Override
	public boolean delUser(Integer key) {
		User user = ss.getUser(key);
		user.setDeleted(true);
		ss.update(user);
		return true;
	}
	
	@Override
	public List<User> getUsers() {
		User user = SpringSecurityUtil.getCurrentUser();
		Shop shop = SpringSecurityUtil.getShop();
		List<User> blu = new ArrayList<User>();
		if (shop == null)
			return null;
		Page<User> page = new Page<User>(
				100);
		page.setStart(0);
		List<PropertyFilter> filters = new ArrayList<PropertyFilter>();
		if ("admin".equals(user.getUsername())) {
			filters.add(new PropertyFilter("EQ_isAdmin", true));
		} else {
			filters.add(new PropertyFilter("EQ_shopKey", shop.getPid()));
		}
		filters.add(new PropertyFilter("EQ_deleted", false));
		page = ss.queryUsers(page, filters);
		blu = page.getResult();
		
		return blu;
	}


	@Override
	public boolean syncCuss() {
		try {
			Set<String> nicks = new HashSet<String>();
			String str = "alipublic00,alipublic01,alipublic02,alipublic03,alipublic04,alipublic05,alipublic06,alipublic07,alipublic08,"
				+ "alipublic09,alipublic10";
			nicks = StringUtils.commaDelimitedListToSet(str);
			cs.drawCoustomer(nicks);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public boolean synOnSaleItems()
	{
		return ps.syncOnSaleItems();
	}


	@Override
	public boolean syncTrades() {
		try {
			ts.tradeFullSysn();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public boolean syncInceTrades() {
		try {
			ts.tradeSysn();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;
	}


	/**
	 * 同步退款申请列表
	 */
	public boolean syncRefunds() {
		try {
			ts.refundSysn();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;
	}


	@Override
	public List<Area> getAreaList(Integer parentId) {
		if(parentId==null||parentId<1)
		{
			parentId = 1;
		}
		return bs.queryAreas(parentId);
	}


	@Override
	public List<Resource> getAvaiAuthRes() {
		return ss.queryResources(new ArrayList<PropertyFilter>());
	}

}
