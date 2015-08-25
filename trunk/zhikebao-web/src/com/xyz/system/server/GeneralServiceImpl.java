package com.xyz.system.server;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.extjs.gxt.ui.client.data.BasePagingLoadResult;
import com.extjs.gxt.ui.client.data.PagingLoadConfig;
import com.extjs.gxt.ui.client.data.PagingLoadResult;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.xyz.base.model.BaseCode;
import com.xyz.base.service.IBaseService;
import com.xyz.customer.service.ICustomerService;
import com.xyz.framework.log.Logger;
import com.xyz.product.service.IProdService;
import com.xyz.system.client.GeneralService;
import com.xyz.system.client.mvc.Authority;
import com.xyz.system.client.mvc.Code;
import com.xyz.system.client.mvc.Resource;
import com.xyz.system.client.mvc.UserModel;
import com.xyz.system.model.Shop;
import com.xyz.system.model.User;
import com.xyz.system.service.ISecurityService;
import com.xyz.system.task.ISysTask;
import com.xyz.util.Page;
import com.xyz.util.PropertyFilter;
import com.xyz.util.SpringSecurityUtil;

@Service
public class GeneralServiceImpl extends RemoteServiceServlet implements GeneralService {
    
	private static final long serialVersionUID = 1L;
	@Autowired
	private IBaseService bs;
	
	@Autowired
	private ISecurityService ss;
	
	@Autowired
	private ISysTask sysTask;
	@Autowired
	private ICustomerService cs;
	@Autowired
	private IProdService ps ;
    
	@Override
	public List<Code> getCodes(Long parentId) {
		Page<BaseCode> page = new Page<BaseCode>(100);
		
		page = bs.findByProperty("parentId", parentId, page);
		List<Code> lf = new ArrayList<Code>();
		if(page.getResult()!=null&&page.getResult().size()>0)
		{
			for(BaseCode bc : page.getResult())
			{
				boolean hasChild = (bc.getChildren()!=null&&bc.getChildren().size()>0)?true:false;
				Code f = new Code(bc.getKeyId(),bc.getName(),bc.getInx(),hasChild);
				lf.add(f);
			}
		}
		return lf;
	}


	@Override
	public Code saveCode(Code code) {
		BaseCode bc = null;
		if(code.getId()==null||code.getId()<1)
		{
			bc = new BaseCode();
			bc.setName(code.getName());
			if(code.getParent()==null)
			{
				 bc.setParentId(0L);
			}else{
		        bc.setInx(code.getInx());
		        bc.setParentId(Long.valueOf(((Code)code.getParent()).getId())); 
			}
			bc = bs.save(bc);
			code.set("id", bc.getKeyId());
		}else{
		    bc = bs.findByKeyId(code.getId());
			bc.setName(code.getName());
			bc.setInx(code.getInx());
			bs.update(bc);
		}
		return code;
	}

	@Override
	public boolean delCode(Code code) {
		BaseCode bc = bs.findByKeyId(Long.valueOf(code.getId()));
		bs.delete(bc);
		return true;
	}

	/**
	 * 分页查询功能列表
	 */
	public PagingLoadResult<Resource> getResources(PagingLoadConfig config) {
		Page<com.xyz.system.model.Resource> page = new Page<com.xyz.system.model.Resource>(config.getLimit());
		page.setStart(config.getOffset());
		List<PropertyFilter> filters = new ArrayList<PropertyFilter>();
		page = ss.queryResources(page, filters);
		List<Resource> lf = new ArrayList<Resource>();
		if(page.getResult()!=null&&page.getResult().size()>0)
		{
			for(com.xyz.system.model.Resource ic : page.getResult())
			{
				Resource f = new Resource();
				BeanUtils.copyProperties(ic, f);
				lf.add(f);
			}
	    }
		return new BasePagingLoadResult<Resource>(lf, config.getOffset(),
				page.getTotalCount());
	}

	@Override
	public Resource saveResource(Resource fres) {
		com.xyz.system.model.Resource res = null;
		if(fres.getKey()!=null&&fres.getKey().length()>0)
			res = ss.getResource(fres.getKey());
		else
		   res = new com.xyz.system.model.Resource();
		BeanUtils.copyProperties(fres, res);
		
		ss.saveResource(res);
		fres.set("key", res.getKey());
		return fres;
	}

	@Override
	public boolean delResource(String key) {
		ss.delResource(key);
		return true;
	}
	
	@Override
	public boolean delAuthority(String key) {
		ss.delAuthority(key);
		return true;
	}

	@Override
	public PagingLoadResult<Authority> getAuthorities(PagingLoadConfig config) {
		Page<com.xyz.system.model.Authority> page = new Page<com.xyz.system.model.Authority>(config.getLimit());
		page.setStart(config.getOffset());
		List<PropertyFilter> filters = new ArrayList<PropertyFilter>();
		page = ss.queryAuthorities(page, filters);
		List<Authority> lf = new ArrayList<Authority>();
		if(page.getResult()!=null&&page.getResult().size()>0)
		{
			for(com.xyz.system.model.Authority ic : page.getResult())
			{
				Authority f = new Authority();
				BeanUtils.copyProperties(ic, f);
				lf.add(f);
			}
	    }
		return new BasePagingLoadResult<Authority>(lf, config.getOffset(),
				page.getTotalCount());
	}

	@Override
	public Authority saveAuthority(Authority fauth) {
		com.xyz.system.model.Authority res = null;
		if(fauth.getKey()!=null&&fauth.getKey().length()>0)
			res = ss.getAuthority(fauth.getKey());
		else
		   res = new com.xyz.system.model.Authority();
		BeanUtils.copyProperties(fauth, res);
		
		ss.saveAuth(res);
		User user = SpringSecurityUtil.getCurrentUser();
		Set<String> as = user.getAuthorityKeys();
		if(as==null)
			as = new HashSet<String>();
		as.add(res.getKey());
		user.setAuthorityKeys(as);
		ss.update(user);
		fauth.set("key", res.getKey());
		return fauth;
	}

	@Override
	public Map<String,List<Resource>> getAllResources(String key) {
		Map<String,List<Resource>> msr = new HashMap<String,List<Resource>>();
		List<Resource> lf = new ArrayList<Resource>();
		List<Resource> hf = new ArrayList<Resource>();
		com.xyz.system.model.Authority auth = ss.getAuthority(key);
		Set<String> keys = auth.getResourceKeys();
		List<com.xyz.system.model.Resource> pr = ss.queryAllResources();
		if(pr!=null&&pr.size()>0)
		{
			for(com.xyz.system.model.Resource ic :pr)
			{
				Resource f = new Resource();
				BeanUtils.copyProperties(ic, f);
				if(keys.contains(ic.getKey()))
					hf.add(f);
				else
					lf.add(f);
			}
	    }
		msr.put("unselect", lf);
		msr.put("selected", hf);
		return msr;
	}

	/**
	 * 更新岗位的职责列表，同时更新相应职责所对应的岗位字符串
	 */
	public boolean saveResOfAuth(String authKey, List<Resource> ress) {
		com.xyz.system.model.Authority auth = null;
		try {
			auth = ss.getAuthority(authKey);
			Set<String> keys = new HashSet<String>();
			for(Resource r : ress)
			{
				String key = r.getKey();
				keys.add(key);
				com.xyz.system.model.Resource bres = ss.getResource(key);
				String authstr = bres.getAuthorities();
				if(authstr==null)
					authstr = auth.getRole();
				else if(authstr.indexOf(auth.getRole())>=0)
					 continue;
				else if(authstr.indexOf(auth.getRole())<0)
				   authstr += ","+auth.getRole();
				bres.setAuthorities(authstr);
				ss.updateResource(bres);
			}
			auth.setResourceKeys(keys);
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
	public PagingLoadResult<UserModel> getUsers(PagingLoadConfig config) {
		User user = SpringSecurityUtil.getCurrentUser();
		Shop shop = SpringSecurityUtil.getShop();
		List<UserModel> lf = new ArrayList<UserModel>();
		List<com.xyz.system.model.User> blu = new ArrayList<com.xyz.system.model.User>();
		if(shop==null)
			return null;
		
		Page<com.xyz.system.model.User> page = new Page<com.xyz.system.model.User>(config.getLimit());
		page.setStart(config.getOffset());
		List<PropertyFilter> filters = new ArrayList<PropertyFilter>();
		if("admin".equals(user.getUsername()))
		{
			filters.add(new PropertyFilter("EQ_isAdmin",true));
		}else{
		    filters.add(new PropertyFilter("EQ_shopKey",shop.getKey()));
		}
		filters.add(new PropertyFilter("EQ_deleted",false));
		page = ss.queryUsers(page, filters);
		blu = page.getResult(); 
		//blu = tbUser.getUsers();
		if(blu!=null&&blu.size()>0)
		{
			for(com.xyz.system.model.User ic : blu)
			{
				UserModel f = new UserModel();
				BeanUtils.copyProperties(ic, f);
				lf.add(f);
			}
	    }
		return new BasePagingLoadResult<UserModel>(lf, config.getOffset(),
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
		User curUser = SpringSecurityUtil.getCurrentUser();
		List<Authority> lauth = new ArrayList<Authority>();
		List<GrantedAuthority> la = curUser.getAuthorities();
		for(GrantedAuthority g : la)
		{
			com.xyz.system.model.Authority auth = (com.xyz.system.model.Authority)g;
			Authority fauth = new Authority();
			BeanUtils.copyProperties(auth, fauth);
			lauth.add(fauth);
		}
		return lauth;
	}

	/**
	 * 保存一个用户
	 */
	public UserModel saveUser(UserModel user) {
		com.xyz.system.model.User buser = null;
		boolean isNew = false;
		if(user.getKey()!=null&&user.getKey().length()>0)
			buser = ss.getUser(user.getKey());
		else
		{
			isNew = true;
			buser = new com.xyz.system.model.User();
			Shop shop = SpringSecurityUtil.getShop();
			buser.setShop(shop);
			buser.setShopKey(shop.getKey());
		}
		BeanUtils.copyProperties(user, buser);
		if(isNew)
		   ss.saveUser(buser);
		else
		   ss.update(buser);
		user.set("key", buser.getKey());
		return user;
	}

	@Override
	public boolean delUser(String key) {
		User user = ss.getUser(key);
		user.setDeleted(true);
		ss.update(user);
		return true;
	}
	
	@Override
	public List<UserModel> getUsers() {
		User user = SpringSecurityUtil.getCurrentUser();
		Shop shop = SpringSecurityUtil.getShop();
		List<UserModel> lf = new ArrayList<UserModel>();
		List<com.xyz.system.model.User> blu = new ArrayList<com.xyz.system.model.User>();
		if (shop == null)
			return null;
		Page<com.xyz.system.model.User> page = new Page<com.xyz.system.model.User>(
				100);
		page.setStart(0);
		List<PropertyFilter> filters = new ArrayList<PropertyFilter>();
		if ("admin".equals(user.getUsername())) {
			filters.add(new PropertyFilter("EQ_isAdmin", true));
		} else {
			filters.add(new PropertyFilter("EQ_shopKey", shop.getKey()));
		}
		filters.add(new PropertyFilter("EQ_deleted", false));
		page = ss.queryUsers(page, filters);
		blu = page.getResult();
		// blu = tbUser.getUsers();
		if (blu != null && blu.size() > 0) {
			for (com.xyz.system.model.User ic : blu) {
				UserModel f = new UserModel();
				BeanUtils.copyProperties(ic, f);
				lf.add(f);
			}
		}
		return lf;
	}

	@Override
	public boolean syncTrades() {
		try {
			
			sysTask.tradeSysn();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;
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
}
