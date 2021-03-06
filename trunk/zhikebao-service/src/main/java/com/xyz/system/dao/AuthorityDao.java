package com.xyz.system.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.Query;

import org.springframework.util.Assert;

import com.xyz.framework.data.impl.JpaDao;
import com.xyz.system.model.Authority;
import com.xyz.system.model.Resource;

public class AuthorityDao  extends JpaDao<Authority, String> implements IAuthorityDao {
	
	public List<Authority> findAuthsByKeys(Set<String> keys) {
		Assert.notEmpty(keys, "没有关联主键");
		List<Authority> userAuths = new ArrayList<Authority>();
		List<Authority> la= getAllAuths();
		Map<String,Authority> ms = new HashMap<String,Authority>();
		for(int i=0;i<la.size();i++)
		{
			Authority a = la.get(i);
			ms.put(a.getKey(), a);
		}
		for(String k : keys)
		{
			Authority a = ms.get(k);
			userAuths.add(a);
		}
		return userAuths;
	}
	
	/**
	 * 获取全部对象.
	 */
	public List<Authority> getAllAuths() {
		Query query = entityManager.createQuery(" select a from "+Authority.class.getName()+ " a ");
		List<Authority> la = query.getResultList();
		return la;
	}


}
