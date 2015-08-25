package com.xyz.system.task;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.taobao.api.TaobaoApiException;
import com.taobao.api.model.Item;
import com.taobao.api.model.ItemGetRequest;
import com.taobao.api.model.ItemGetResponse;
import com.taobao.api.model.ItemPropValuesGetRequest;
import com.taobao.api.model.ItemPropValuesResponse;
import com.taobao.api.model.Order;
import com.taobao.api.model.Sku;
import com.taobao.api.model.SkuGetRequest;
import com.taobao.api.model.SkuGetResponse;
import com.taobao.api.model.Trade;
import com.taobao.api.model.TradeGetRequest;
import com.taobao.api.model.TradeGetResponse;
import com.taobao.api.model.TradesGetResponse;
import com.taobao.api.model.TradesSoldGetRequest;
import com.taobao.api.model.UserGetRequest;
import com.taobao.api.model.UserGetResponse;
import com.xyz.framework.data.DataUtil;
import com.xyz.system.model.Shop;
import com.xyz.system.model.User;
import com.xyz.util.DateUtil;
import com.xyz.util.SpringSecurityUtil;
import com.xyz.util.TaobaoUtil;

/**
 * 各种系统任务的公共接口
 * @author val
 *
 */
public interface ISysTask {
	/**
	 * 定时请求Taobao保持已有会话
	 */
	public void holdAuthorize();


}