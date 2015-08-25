package com.xyz.system.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.xyz.system.dao.IDeptDao;
import com.xyz.system.model.Department;
import com.xyz.system.service.IDepartmentService;
import com.xyz.util.Page;
import com.xyz.util.PropertyFilter;

@Service("deptServ") @Transactional
public class DepartmentService implements IDepartmentService {
	
	@Autowired
	private IDeptDao deptDao;
	
	public List getTrees(String parentId) throws Exception{
	   return getTrees(parentId,false);
    }
	
	public List getTrees(String parentId,boolean isCheck) throws Exception{
		
		    List<Department> departments = deptDao.findBy("parentId", parentId);
	        List<Map> trees = new ArrayList<Map>();
	        boolean isLeaf = true;
	        for(Department department: departments){
	            Map<String,Object> tree = new HashMap();
	            tree.put("id", department.getKey());
	            tree.put("text", department.getName());
	            isLeaf = (department.getChildDepts()==null)||department.getChildDepts().size()==0;
	            tree.put("leaf", isLeaf);
	            if(isCheck&&isLeaf)
	            	tree.put("checked", false);
	            trees.add(tree);
	        }

	        return trees;
	    }
	@Transactional(propagation=Propagation.REQUIRED)
	public boolean save(Department dept) throws Exception {
		deptDao.save(dept);
		return true;
	}
	
	public Department get(Long id) {
		return (Department)deptDao.get(id);
	}
	
	public void delete(Long id) throws Exception {
		deptDao.delete(id);
	}
	
	public Page queryDepts(Page page,List<PropertyFilter> filters) {
	    return deptDao.findPage(page, filters);
	}
	
	public List queryDepts() {
		return deptDao.getAll();
	}

	 
}
