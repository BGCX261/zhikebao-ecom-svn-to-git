package com.xyz.util;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.util.CycleDetectionStrategy;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.processors.JsonValueProcessor;

/** *//**
 * @author </br> <a href="mailto:fx19800215@163.com"> robert.feng</a>
 *
 */
public class JsonUtil {
	
	/**
	 * 向客户端直接返回JSON字符串
	 * @param response
	 * @param isSuccess
	 * @param errorMsg
	 */
	public static void formSubmitResult(HttpServletResponse response,Boolean isSuccess,String errorMsg){
		try {
			JSONObject json = new JSONObject();
			json.put("success", isSuccess);
			json.put("errorMsg", errorMsg);
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/xml");
			response.getWriter().write(json.toString());
		}catch (IOException e) {
			e.printStackTrace();
		}
	}

    /** *//**
     * 从一个JSON 对象字符格式中得到一个java对象
     * @param jsonString
     * @param pojoCalss
     * @return
     */
    public static Object getObject4JsonString(String jsonString,Class<?> pojoCalss){
        Object pojo;
        JSONObject jsonObject = JSONObject.fromObject( jsonString );  
        pojo = JSONObject.toBean(jsonObject,pojoCalss);
        return pojo;
    }
    
    
    
    /** *//**
     * 从json HASH表达式中获取一个map，改map支持嵌套功能
     * @param jsonString
     * @return
     */
    public static Map<String, Object> getMap4Json(String jsonString){
        JSONObject jsonObject = JSONObject.fromObject( jsonString );  
        Iterator<?>  keyIter = jsonObject.keys();
        String key;
        Object value;
        Map<String, Object> valueMap = new HashMap<String, Object>();

        while( keyIter.hasNext())
        {
            key = (String)keyIter.next();
            value = jsonObject.get(key);
            valueMap.put(key, value);
        }
        
        return valueMap;
    }
    
    
    /** *//**
     * 从json数组中得到相应java数组
     * @param jsonString
     * @return
     */
    public static Object[] getObjectArray4Json(String jsonString){
        JSONArray jsonArray = JSONArray.fromObject(jsonString);
        return jsonArray.toArray();
    }
    
    
    /** *//**
     * 从json对象集合表达式中得到一个java对象列表
     * @param jsonString
     * @param pojoClass
     * @return
     */
    public static List<Object> getList4Json(String jsonString, Class<?> pojoClass){
        JSONArray jsonArray = JSONArray.fromObject(jsonString);
        JSONObject jsonObject;
        Object pojoValue;
        List<Object> list = new ArrayList<Object>();
        for ( int i = 0 ; i<jsonArray.size(); i++){
            jsonObject = jsonArray.getJSONObject(i);
            pojoValue = JSONObject.toBean(jsonObject,pojoClass);
            list.add(pojoValue);
        }
        return list;
    }
    
    /** *//**
     * 从json数组中解析出java字符串数组
     * @param jsonString
     * @return
     */
    public static String[] getStringArray4Json(String jsonString){
        
        JSONArray jsonArray = JSONArray.fromObject(jsonString);
        String[] stringArray = new String[jsonArray.size()];
        for( int i = 0 ; i<jsonArray.size() ; i++ ){
            stringArray[i] = jsonArray.getString(i);
            
        }
        
        return stringArray;
    }
    
    /** *//**
     * 从json数组中解析出javaLong型对象数组
     * @param jsonString
     * @return
     */
    public static Long[] getLongArray4Json(String jsonString){
        
        JSONArray jsonArray = JSONArray.fromObject(jsonString);
        Long[] longArray = new Long[jsonArray.size()];
        for( int i = 0 ; i<jsonArray.size() ; i++ ){
            longArray[i] = jsonArray.getLong(i);
            
        }
        return longArray;
    }
    
    /** *//**
     * 从json数组中解析出java Integer型对象数组
     * @param jsonString
     * @return
     */
    public static Integer[] getIntegerArray4Json(String jsonString){
        
        JSONArray jsonArray = JSONArray.fromObject(jsonString);
        Integer[] integerArray = new Integer[jsonArray.size()];
        for( int i = 0 ; i<jsonArray.size() ; i++ ){
            integerArray[i] = jsonArray.getInt(i);
            
        }
        return integerArray;
    }
    
    /** *//**
     * 从json数组中解析出java Date 型对象数组，使用本方法必须保证
     * @param jsonString
     * @return
     * @throws ParseException 
     */
    public static Date[] getDateArray4Json(String jsonString,String DataFormat) throws ParseException{
        
        JSONArray jsonArray = JSONArray.fromObject(jsonString);
        Date[] dateArray = new Date[jsonArray.size()];
        String dateString;
        Date date;
        
        for( int i = 0 ; i<jsonArray.size() ; i++ ){
            dateString = jsonArray.getString(i);
            date = DateUtil.parse(dateString, DataFormat);
            dateArray[i] = date;
            
        }
        return dateArray;
    }
    
    /** *//**
     * 从json数组中解析出java Integer型对象数组
     * @param jsonString
     * @return
     */
    public static Double[] getDoubleArray4Json(String jsonString){
        
        JSONArray jsonArray = JSONArray.fromObject(jsonString);
        Double[] doubleArray = new Double[jsonArray.size()];
        for( int i = 0 ; i<jsonArray.size() ; i++ ){
            doubleArray[i] = jsonArray.getDouble(i);
            
        }
        return doubleArray;
    }
    
    
    /** *//**
     * 将java对象转换成json字符串
     * @param javaObj
     * @return
     */
    public static String getJsonString4JavaPOJO(Object javaObj){
        
        JSONObject json;
        json = JSONObject.fromObject(javaObj);
        return json.toString();
        
    }
    
    
    
    
    /** *//**
     * 将java对象转换成json字符串,并设定日期格式
     * @param javaObj
     * @param dataFormat
     * @return
     */
    public static String getJsonString4JavaPOJO(Object javaObj , String dataFormat){
        
        JSONObject json;
        JsonConfig jsonConfig = configJson(dataFormat);
        json = JSONObject.fromObject(javaObj,jsonConfig);
        return json.toString();
        
        
    }
    
    
    
    /** *//**
     * @param args
     */
    public static void main(String[] args) {
    			
    }
    
    /** *//**
     * JSON 时间解析器具
     * @param datePattern
     * @return
     */
    public static JsonConfig configJson(String datePattern) {   
            JsonConfig jsonConfig = new JsonConfig();   
            jsonConfig.setExcludes(new String[]{""});   
            jsonConfig.setIgnoreDefaultExcludes(false);   
            jsonConfig.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);   
            jsonConfig.registerJsonValueProcessor(Date.class,   
                new DateJsonValueProcessor(datePattern));   
          
            return jsonConfig;   
        }  
    
    /** *//**
     * 
     * @param excludes
     * @param datePattern
     * @return
     */
    public static JsonConfig configJson(String[] excludes,   
            String datePattern) {   
            JsonConfig jsonConfig = new JsonConfig();   
            jsonConfig.setExcludes(excludes);   
            jsonConfig.setIgnoreDefaultExcludes(false);   
            jsonConfig.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);   
            jsonConfig.registerJsonValueProcessor(Date.class,   
                new DateJsonValueProcessor(datePattern));   
          
            return jsonConfig;   
        }  

}


class DateJsonValueProcessor implements JsonValueProcessor {

    
    public static final String DEFAULT_DATE_PATTERN = "yyyy-MM-dd";   
    private DateFormat dateFormat;   

    
    
    /** *//**  
     * 构造方法.  
     *  
     * @param datePattern 日期格式  
     */  
    public DateJsonValueProcessor(String datePattern) {   
          
        if( null == datePattern )
            dateFormat = new SimpleDateFormat(DEFAULT_DATE_PATTERN);  
        else
            dateFormat = new SimpleDateFormat(datePattern); 
        
    }   
    public Object processArrayValue(Object arg0, JsonConfig arg1) {
        return process(arg0);   
    }
    public Object processObjectValue(String arg0, Object arg1, JsonConfig arg2) {
        return process(arg1);   
    }
    
    private Object process(Object value) {   
        return dateFormat.format((Date) value);   
    }   


}


