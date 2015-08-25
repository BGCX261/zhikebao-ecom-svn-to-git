package com.xyz.system.dao;

import com.google.appengine.api.datastore.Key;
import com.xyz.framework.data.impl.JpaDao;
import com.xyz.system.model.Department;

public class DeparmentDao extends JpaDao<Department, String> implements IDeptDao {

}
