package com.xyz.system.dao;

import java.util.List;
import java.util.Set;

import com.xyz.framework.data.IDataObject;
import com.xyz.system.model.Authority;

public interface IAuthorityDao extends IDataObject<Authority,String>{

	public List<Authority> findAuthsByKeys(Set<String> keys);
}