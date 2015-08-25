package com.xyz.util;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import org.springframework.util.StringUtils;

/**
 * 字符串常用处理
 */
public class StringUtil {
	
	/**
	 * 判断是否为null和为空
	 * @param str 字符串
	 * @return true:不为空 false:为空
	 */
	public static boolean isNullOrEmpty( String str ){
		if ( str == null || str.equals("")  )
			return true;
		else
			return false;
	}
	
	/**
	 * 判断是否为null,若为空返回空字串
	 * @param str 字符串
	 * @return true:不为空 false:为空
	 */
	public static String isNull( String str ){
		if ( str != null)
			return str.toString();
		else
			return "";
	}
	/**
     * 编码转换
     * @param str 字符串
     * @return gb2312编码的字符串
     */
    public static String conver(String str){
		byte [] b;
		String utf8_value; 
		if ( str == null )
			str = "";
		utf8_value = str;
		String name = "";
		try {
			b = utf8_value.getBytes("8859_1"); //中间用ISO-8859-1过渡
			name = new String(b, "GB2312"); //转换成GB2312字符
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		return name;
	}
	/**
	 * url编码转换
	 * @param str url字符串
	 * @return 解编号后的字符串
	 */
    @SuppressWarnings("deprecation")
	public static String converUrl(String str){
    	String strurl = URLEncoder.encode(str);
    	return strurl;
    }
    /**
     * 获取随机数
     * @param size 位数
     * @param isLetter 是否包含字母
     * @param isNum 是否包含数字
     * @return 随机数
     */
    public static String getRandom(int size,boolean isLetter,boolean isNum){
    	Random random = new Random();
		String sRand = "";
		int count = 0;
		String[] ychar = new String[35];
		String[] nchar = {"1", "2", "3", "4", "5", "6", "7", "8", "9"};
		String[] lchar = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};
		
		if ( isLetter == false && isNum == false ){
			isLetter = true;
			isNum = true;
		}
		
		if ( isLetter ){
			for (int i = 0; i < lchar.length; i++) {
				ychar[i] = lchar[i];
			}
			count = 26;
		}
		
		if ( isNum ){
			for (int i = 0; i < nchar.length; i++) {
				ychar[i + count] = nchar[i];
			}
			if ( isLetter )
				count = 35;
			else 
				count = 9;
		}
		
		for (int i = 0; i < size; i++) {
		     String rand = ychar[random.nextInt(count)];
		     sRand += rand;
		}
    	
    	return sRand;
    }
    /**
     * 字符串补零
     * @param str
     * @param letter
     * @param leng
     * @param way left,right
     * @return
     */
    public static String fillStr(String str,char letter,int leng,String way){
		if ( str.length() < leng ){
			String j = "";
			for(int i=0; i < leng-str.length();i++){
				j += letter;
			}
			if ( way.equals("right") ){
				str = str + j;
			} else {
				str = j + str;
			}
		}
		return str;
	}
    /**
	 * 编码是否有效
	 */
	private static boolean Utf8codeCheck(String text) {
		String sign = "";
		if (text.startsWith("%e"))
			for (int i = 0, p = 0; p != -1; i++) {
				p = text.indexOf("%", p);
				if (p != -1)
					p++;
				sign += p;
			}
		return sign.equals("147-1");
	}
    /**
	 * 是否Utf8Url编码
	 */
	public static boolean isUtf8Url(String text) {
		text = text.toLowerCase();
		int p = text.indexOf("%");
		if (p != -1 && text.length() - p > 9) {
			text = text.substring(p, p + 9);
		}
		return Utf8codeCheck(text);
	}
	/**
	 * Utf8URL解码
	 */
	public static String Utf8URLdecode(String text) {
		String result = "";
		int p = 0;

		if (text != null && text.length() > 0) {
			text = text.toLowerCase();
			p = text.indexOf("%e");
			if (p == -1)
				return text;

			while (p != -1) {
				result += text.substring(0, p);
				text = text.substring(p, text.length());
				if (text == "" || text.length() < 9)
					return result;

				result += CodeToWord(text.substring(0, 9));
				text = text.substring(9, text.length());
				p = text.indexOf("%e");
			}

		}

		return result + text;
	}
	/**
	 * utf8URL编码转字符
	 */
	private static String CodeToWord(String text) {
		String result;

		if (Utf8codeCheck(text)) {
			byte[] code = new byte[3];
			code[0] = (byte) (Integer.parseInt(text.substring(1, 3), 16) - 256);
			code[1] = (byte) (Integer.parseInt(text.substring(4, 6), 16) - 256);
			code[2] = (byte) (Integer.parseInt(text.substring(7, 9), 16) - 256);
			try {
				result = new String(code, "UTF-8");
			} catch (UnsupportedEncodingException ex) {
				result = null;
			}
		} else {
			result = text;
		}

		return result;
	}
	
	 
	 public static List<String> toStringArray(String field){
	    	List<String> list = new ArrayList<String>();
			if(!isNullOrEmpty(field)){
				return list;
			}
			field = field.substring(1, field.length() - 1);
			field = field.replaceAll("\"", " ");
			
			String[] fields = field.split(",");
			for(String str : fields){
				list.add(str);
			}
			return list;
	}
	 /**
	  * 将用,分隔的字符串转换成list
	  * @param field
	  * @return
	  */
	 public static List<String> stringToList(String field){
	    	List<String> list = new ArrayList<String>();
			if(!isNullOrEmpty(field)){
				return list;
			}
			String[] fields = StringUtils.delimitedListToStringArray(field, ",");
			for(String str : fields){
				list.add(str);
			}
			return list;
		}
	 
	 /**
	  * 判断两个字符串数组中是否有相同元素
	  * @param field
	  * @return
	  */
	 public static boolean isHasSame(String[] sa1,String[] sa2){
		    for(String s1 : sa1)
		    {
		    	for(String s2 : sa2)
		    	{
		    		if(s1.equals(s2))
		    			return true;
		    	}
		    }
			return false;
		}
	 
    public static void main(String[] args){
    	
    	for(int i=0 ;i<1000;i++){
    		System.out.println( getRandom(4,true,true ));
    	}
    }
    
    
}
