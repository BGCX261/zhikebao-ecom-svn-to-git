package com.xyz.base.dao;

import java.util.List;
import java.util.Map;

import com.xyz.base.model.BaseCode;
import com.xyz.framework.data.IDataObject;

public interface IBaseDao extends IDataObject<BaseCode,String>{

	public Map<Long,List<BaseCode>> getAllCodes();
}