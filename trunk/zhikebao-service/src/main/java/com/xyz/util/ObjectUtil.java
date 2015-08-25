package com.xyz.util;

import java.util.List;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class ObjectUtil {
	 private static Log log = LogFactory.getLog(ObjectUtil.class);

	 public static boolean hasProp(Object obj,String propName) {
	        try {
	            String value=BeanUtils.getProperty(obj,propName);
	            return true;
	        }catch(Exception e) {
	            return false;
	        }
	 }
	    
	public static boolean hasValue(Object obj,String propName) {
	        try {
	            String value=BeanUtils.getProperty(obj,propName);
	            if (value==null || value.trim().length()==0)
	                return false;
	            else
	                return true;
	        }catch(Exception e) {
	            return false;
	        }
	 }
	
	    /**
	     * hibernate only
	     * @param dest
	     * @param src
	     * @param propNames
	     */
	    public static void copyObjectInclude(Object dest, Object src, List propNames) {
	        int count = propNames.size();
	        for (int i = 0; i < count; i++) {
	            try {
	                String prop = propNames.get(i).toString();
	                Object value = PropertyUtils.getSimpleProperty(src, prop);
	                if (value != null) {
	                    BeanUtils.setProperty(dest, prop, value);
	                }
	            } catch (Exception e) {
	            	log.debug(dest.getClass() + e.getMessage());
	            }
	        }
	    }
	 
}
