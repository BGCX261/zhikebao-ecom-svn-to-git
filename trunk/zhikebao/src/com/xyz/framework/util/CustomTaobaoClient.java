package com.xyz.framework.util;

import java.util.HashMap;
import java.util.Map;

import com.taobao.api.ApiConstants;
import com.taobao.api.TaobaoApiException;
import com.taobao.api.TaobaoJsonRestClient;
import com.taobao.api.convert.TaobaoSkuJSONConvert;
import com.taobao.api.direct.TaobaoDirectApiMethod;
import com.taobao.api.extra.model.SkusOutGetResponse;
import com.taobao.api.json.JSONArray;
import com.taobao.api.json.JSONException;
import com.taobao.api.json.JSONObject;
import com.taobao.api.model.SkusCustomGetRequest;
import com.taobao.api.model.TaobaoResponse;

public class CustomTaobaoClient extends TaobaoJsonRestClient {

	public CustomTaobaoClient(String appkey, String secret) throws TaobaoApiException {
		this(appkey, secret, false);
	}

	public CustomTaobaoClient(String appkey, String secret, boolean isSandbox) throws TaobaoApiException {
		this(isSandbox ? ApiConstants.API_SANDBOX_SERVICE_URL : ApiConstants.API_SERVICE_URL, appkey, secret);
	}

	public CustomTaobaoClient(String serviceUrl, String appkey, String secret) throws TaobaoApiException {
		this(serviceUrl, ApiConstants.DEFAULT_SERVICE_VERSION, appkey, secret);
	}

	public CustomTaobaoClient(String serviceUrl, String version, String appkey, String secret) throws TaobaoApiException {
		super(serviceUrl, version, appkey, secret);
	}

	/*public SkusOutGetResponse skusOutGet(SkusCustomGetRequest skusOutGetRequest, String sessionId, String sessionNick) throws TaobaoApiException {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("outer_id", skusOutGetRequest.getOuterId());
		params.put("fields", skusOutGetRequest.getFields());

		TaobaoResponse response = this.getFetch().fetch(this.getServiceUrl(), getTemplateRequest(TaobaoDirectApiMethod.SKUS_OUT_GET.getMethod(), sessionId, params));
		SkusOutGetResponse skusOutGetResponse = new SkusOutGetResponse(response);
		if (skusOutGetResponse.isSuccess()) {
			JSONObject json;
			try {
				json = new JSONObject(skusOutGetResponse.getBody());
				parseError(skusOutGetResponse, json);
				if (skusOutGetResponse.isSuccess()) {
					// 处理返回结果
					JSONObject rsp = json.getJSONObject(ApiConstants.RSP);
					if (rsp.has(ApiConstants.SKUS)) {
						JSONArray skus = rsp.getJSONArray(ApiConstants.SKUS);
						JSONObject sku = skus.getJSONObject(0);
						skusOutGetResponse.setSkus(TaobaoSkuJSONConvert.convertJsonToSku(sku));
					}
				}
			} catch (JSONException e) {
				throw new TaobaoApiException(e);
			}
		}
		return skusOutGetResponse;
	}*/
}
