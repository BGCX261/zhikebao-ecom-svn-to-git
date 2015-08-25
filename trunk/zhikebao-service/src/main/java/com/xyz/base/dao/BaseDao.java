package com.xyz.base.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.xyz.base.model.BaseCode;
import com.xyz.framework.data.impl.JpaDao;

public class BaseDao extends JpaDao<BaseCode, String> implements IBaseDao {

	@Override
	public Map<Long, List<BaseCode>> getAllCodes() {
		List<BaseCode> lt = find(" deleted = false ", " parentId ,inx ");
		Map<Long, List<BaseCode>> mball = new HashMap<Long, List<BaseCode>>();
		List<BaseCode>  lb = null;
		for(int i=0;i<lt.size();i++)
		{
			BaseCode bc = lt.get(i);
			Long parentId = bc.getParentId();
			if(mball.containsKey(parentId))
			{
				lb = mball.get(parentId);
			}else{
				lb = new ArrayList<BaseCode>();
				mball.put(parentId, lb);
			}
			lb.add(bc);
		}
		return mball;
	}
}
