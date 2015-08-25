package com.xyz.framework.data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.xml.crypto.Data;

import org.apache.commons.beanutils.BeanUtils;

import com.google.appengine.api.datastore.Key;
import com.xyz.framework.FrameConstants;
import com.xyz.framework.log.Logger;
import com.xyz.util.ObjectUtil;
import com.xyz.util.SpringSecurityUtil;

public class DataUtil {
	
	/* 
     * 设置默认的系统字段
     */
    public static void onSave(Object entity) {
    	Logger.debug(DataUtil.class, "开始拦截处理");
        try {
        	//设置默认的创建人、创建时间、修改人和修改时间
        	onUpdate(entity);
            //是否已删除
            if (ObjectUtil.hasProp(entity,FrameConstants.SYS_POJO_DD)&& !ObjectUtil.hasValue(entity,FrameConstants.SYS_POJO_DD))
            	BeanUtils.setProperty(entity,FrameConstants.SYS_POJO_DD,FrameConstants.SYS_FALSE); 
        }catch(Exception e) {
            //just log and ignore the errors here
            Logger.error(DataUtil.class,e.getMessage(),e);
        }
    }
	
	 /*
     * 设置默认的创建人、创建时间、修改人和修改时间
     */
    public static void onUpdate(Object entity) {
    	Logger.debug(DataUtil.class, "更新前处理");
        try {
            //如果未登录，则忽略
            if (SpringSecurityUtil.getCurrentUser()==null)
                return;
            String userId = SpringSecurityUtil.getUserId();
            Date ts=new Date();
            //创建人和创建时间
            if (ObjectUtil.hasProp(entity,FrameConstants.SYS_POJO_CU) && !ObjectUtil.hasValue(entity,FrameConstants.SYS_POJO_CU)) {
            	BeanUtils.setProperty(entity,FrameConstants.SYS_POJO_CU,userId);
            	BeanUtils.setProperty(entity,FrameConstants.SYS_POJO_DE,ts);               
            }
            //修改时间和修改人
            if (ObjectUtil.hasProp(entity,FrameConstants.SYS_POJO_MU)) {
            	BeanUtils.setProperty(entity,FrameConstants.SYS_POJO_MU,userId);
            	BeanUtils.setProperty(entity,FrameConstants.SYS_POJO_DM,ts); 
            }
           
        }catch(Exception e) {
           Logger.debug(DataUtil.class,e.getMessage(),e);
        }
    }
    /**
     * 将Key的集合转换成id的List
     * @param keys
     * @return
     */
    public static List<Long> keysToIds(Set<Key> keys)
    {
    	List<Long> ll = new ArrayList();
    	for(Key k : keys)
    	{
    		ll.add(k.getId());
    	}
    	return ll;
    }
    
    /**
     * 在字符串前后加上单引号，满足查询语句需要
     * @param keys
     * @return
     */
    public static String toSqlStr(String str)
    {
    	str = str.trim();
    	str = "'"+str+"'";
    	return str;
    }

}
