package com.xyz.system.service;

import java.util.List;

import com.xyz.system.model.Department;
import com.xyz.util.Page;
import com.xyz.util.PropertyFilter;

public interface IDepartmentService {
	
	public List getTrees(String parentId) throws Exception;
	
	public List getTrees(String parentId,boolean isCheck) throws Exception;
	
	public boolean save(Department dept) throws Exception;
	
	public void delete(String id) throws Exception;
	
	public Department get(Long id) ;
	
	public Page queryDepts(Page page,List<PropertyFilter> filters);

}
