package com.xyz.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import java.util.Calendar;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <b>日期转换辅助类</b>
 */
public class DateUtil {
    private static String defaultDatePattern = "yyyy-MM-dd";
    private static String compDatePattern = "yyyy-MM-dd hh:mm:ss";

    /**
     * 获得默认的 date pattern
     */
    public static String getDatePattern() {
        return defaultDatePattern;
    }

    /**
     * 由于服务器当地时间 和 北京时间有偏差，需要校正得到当前北京时间
     */
    public static Date getNow()
    {
    	Date orinow = new Date();
    	return  orinow;//DateUtil.addMinute(orinow,8*60);
    }
    /**
     * 返回预设Format的当前日期字符串
     */
    public static String getToday() {
        Date today = new Date();
        return format(today);
    }

    /**
     * 使用预设Format格式化Date成字符串
     */
    public static String format(Date date) {
        return date == null ? "" : format(date, getDatePattern());
    }
    
    /**
     * 使用参数Format格式化Date成字符串
     */
    public static String format(Date date, String pattern) {
        return date == null ? "" : new SimpleDateFormat(pattern).format(date);
    }
    /**
     * 使用参数Format格式化Date成字符串
     */
    public static String formatAll(Date date) {
        return date == null ? "" : format(date,compDatePattern);
    }

    /**
     * 使用参数Format将字符串转为Date
     */
    public static Date parse(String strDate, String pattern) throws ParseException {
        return strDate==null || strDate.equals("")  ? null : new SimpleDateFormat(pattern).parse(strDate);
    }
    
    /**
     * 使用参数Format将字符串转为Date
     */
    public static Date parse(String strDate) throws ParseException {
        return strDate==null || strDate.equals("")  ? null : new SimpleDateFormat(defaultDatePattern).parse(strDate);
    }
    
    /**
     * 比较两个时间大小
     */
    public static boolean judgeBig(String timeS , String timeE){
    	if (java.sql.Date.valueOf(timeS).after(java.sql.Date.valueOf(timeE))){
    		return true;
    	} else {
    		return false;
    	}
    }
    
    
    /**
     * 在日期上增加或减去数个整月
     */
    public static Date addMonth(Date date, int n) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MONTH, n);
        return cal.getTime();
    }
    
    /**
     * 在日期上增加n分钟
     */
    public static Date addMinute(Date date, int n) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MINUTE, n);
        return cal.getTime();
    }
    
    /**
     * 在日期上增加n个整天
     */
    public static Date addDay(Date date, int n) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.HOUR_OF_DAY, n);
        return cal.getTime();
    }
    
    /**
     * 计算两个日期相隔的天数
     */
    public static long cutDay(Date date1, Date date2) { 
		long quot = date2.getTime()- date1.getTime(); 
		return quot / 1000 / 60 / 60 / 24;
    }
    
    @SuppressWarnings("unchecked")
	public Object convert(Class arg0, Object arg1) {
        if(arg1 instanceof XMLGregorianCalendar){
            return this.convert(arg0, (XMLGregorianCalendar)arg1);
        }else{
            return this.convert(arg0, (Calendar)arg1);
        }
    }
    
    @SuppressWarnings("unchecked")
	public Calendar convert(Class calendarType, XMLGregorianCalendar xmlCalendar){
        return xmlCalendar.toGregorianCalendar();
    }
    
    @SuppressWarnings("unchecked")
	public XMLGregorianCalendar convert(Class xmlCalendarType, Calendar calendar){
        XMLGregorianCalendar cal = null;
		try {
			cal = (XMLGregorianCalendar)xmlCalendarType.newInstance();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        cal.setYear(calendar.get(Calendar.YEAR));
        cal.setMonth(calendar.get(Calendar.MONTH) +1);
        cal.setDay(calendar.get(Calendar.DAY_OF_MONTH));
        cal.setHour(calendar.get(Calendar.HOUR_OF_DAY));
        cal.setMinute(calendar.get(Calendar.MINUTE));
        cal.setSecond(calendar.get(Calendar.SECOND));
        cal.setMillisecond(calendar.get(Calendar.MILLISECOND));
        cal.setTimezone(calendar.get(Calendar.ZONE_OFFSET) / 60000 );
        return cal;
    }  
    /**
     * 获取从0点0分0秒起始的当天日期
     */
    public static Date beginToday() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		//SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		//String now = sdf.format(cal.getTime());
		return cal.getTime();
    }
}
