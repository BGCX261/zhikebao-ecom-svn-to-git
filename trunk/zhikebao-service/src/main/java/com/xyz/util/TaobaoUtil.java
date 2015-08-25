package com.xyz.util;

import java.util.HashMap;
import java.util.Map;

import com.taobao.api.TaobaoApiException;
import com.taobao.api.TaobaoJsonRestClient;
import com.xyz.framework.FrameConstants;
import com.xyz.framework.log.Logger;
import com.xyz.system.model.Shop;

public class TaobaoUtil {

	public static TaobaoJsonRestClient getService(Shop tbuser)
	{
		try {
			return new TaobaoJsonRestClient(FrameConstants.SYS_TAOBAO_SERVICE_URL,tbuser.getAppKey(), tbuser.getAppSecret());
		} catch (TaobaoApiException e) {
			Logger.error(TaobaoUtil.class, "不能正常访问淘宝平台");
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 处理Item propertyAlias 属性分组
	 * 20518:28422:27; 20518:28421:26; 20518:28420:25; 20518:28419:24; 1627207:28332:奶油色; 1627207:3232482:卡其色
	 * key:1627207:28332
	 * vaule: val
	 * @param propertyAlias Item
	 * @return
	 */
	public static Map<String,String> getPropertyAlias(String itemPropertyAlias ) {
		Map<String,String> map = new HashMap<String,String>();
		String[] propertyAlias = itemPropertyAlias.split(";");
		for(String propertyAlia : propertyAlias){
			String[] nameAlias = propertyAlia.split(":");
			map.put(nameAlias[0] + ":" + nameAlias[1], nameAlias[2]);
		}
		return map;
	}
	
	public static String getSizeName(String tbSizeName){
		String sizeName = tbSizeName;
		if(sizeName.equals("160/80A")){
			sizeName = "XS";
		}
		if(sizeName.equals("165/85A")){
			sizeName = "S";
		}
		if(sizeName.equals("170/90A")){
			sizeName = "M";
		}
		if(sizeName.equals("175/95A")){
			sizeName = "L";
		}
		if(sizeName.equals("180/100A")){
			sizeName = "XL";
		}
		if(sizeName.equals("185/105A")){
			sizeName = "XXL";
		}
		return sizeName;
	}
}
