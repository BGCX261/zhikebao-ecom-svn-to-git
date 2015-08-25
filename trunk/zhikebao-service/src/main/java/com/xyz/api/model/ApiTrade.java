package com.xyz.api.model;

// Generated 2009-10-31 14:39:21 by Hibernate Tools 3.2.4.GA

/**
 * ApiTrade ：API交易
 */
public class ApiTrade implements java.io.Serializable {

	private int recId;
	private String apitype;
	private String apiurl;
	private String ucode;
	private String shop;
	private Integer apiid;

	public ApiTrade() {
	}

	public ApiTrade(int recId) {
		this.recId = recId;
	}

	public ApiTrade(int recId, String apitype, String apiurl, String ucode,
			String shop, Integer apiid) {
		this.recId = recId;
		this.apitype = apitype;
		this.apiurl = apiurl;
		this.ucode = ucode;
		this.shop = shop;
		this.apiid = apiid;
	}

	public int getRecId() {
		return this.recId;
	}

	public void setRecId(int recId) {
		this.recId = recId;
	}

	public String getApitype() {
		return this.apitype;
	}

	public void setApitype(String apitype) {
		this.apitype = apitype;
	}

	public String getApiurl() {
		return this.apiurl;
	}

	public void setApiurl(String apiurl) {
		this.apiurl = apiurl;
	}

	public String getUcode() {
		return this.ucode;
	}

	public void setUcode(String ucode) {
		this.ucode = ucode;
	}

	public String getShop() {
		return this.shop;
	}

	public void setShop(String shop) {
		this.shop = shop;
	}

	public Integer getApiid() {
		return this.apiid;
	}

	public void setApiid(Integer apiid) {
		this.apiid = apiid;
	}

}
