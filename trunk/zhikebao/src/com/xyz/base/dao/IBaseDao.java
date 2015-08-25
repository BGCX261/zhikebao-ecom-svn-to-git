package com.xyz.base.dao;

import java.util.List;
import java.util.Map;

import com.xyz.framework.data.IDataObject;
import com.xyz.resources.model.BaseCode;

public interface IBaseDao extends IDataObject<BaseCode,Integer>{

	public Map<Integer,List<BaseCode>> getAllCodes();
}