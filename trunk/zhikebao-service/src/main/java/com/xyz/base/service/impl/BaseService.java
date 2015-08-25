package com.xyz.base.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.xyz.base.dao.IBaseDao;
import com.xyz.base.model.BaseCode;
import com.xyz.base.service.IBaseService;
import com.xyz.util.Page;

@Service
public class BaseService implements IBaseService {
	
	@Autowired
	private IBaseDao bdao;

	@Override
	public void delete(BaseCode entity) {
		if(0!=entity.getParentId())
		{
		   BaseCode parent = findByKeyId(entity.getParentId());
		   if(parent!=null)
		   {
		     Set<Long> sk = parent.getChildren();
		     sk.remove(entity.getKeyId());
		     parent.setChildren(sk);
		   }
		}
		bdao.delete(entity);
		
	}

	@Transactional
	public Page<BaseCode> findAll(Page page) {
		
		return bdao.getAll(page);
	}

	@Override
	public BaseCode findById(String id) {
		//Key k = KeyFactory.stringToKey(id);
		// TODO Auto-generated method stub
		return bdao.get(id);
	}
	
	@Override
	public BaseCode findByKeyId(Long id) {
		Key k = KeyFactory.createKey("baseCode", id);
		return bdao.get(KeyFactory.keyToString(k));
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
		   BaseCode parent = this.findByKeyId(entity.getParentId());
		   if(parent!=null)
		   {
		     Set<Long> sk = parent.getChildren();
		     if(sk==null)
		    	 sk = new HashSet<Long>();
		     sk.add(entity.getKeyId());
		     parent.setChildren(sk);
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
		Map<Long, List<BaseCode>> ml = bdao.getAllCodes();
		return ml.get(parentId);
	}

}
