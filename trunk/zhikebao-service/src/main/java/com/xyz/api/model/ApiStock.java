package com.xyz.api.model;

// Generated 2009-10-31 14:39:21 by Hibernate Tools 3.2.4.GA

/**
 * API信息
 */
public class ApiStock implements java.io.Serializable {

	private int recId;
	private String apitype;
	private String apiurl;
	private String ucode;
	private Integer apiid;

	public ApiStock() {
	}

	public ApiStock(int recId) {
		this.recId = recId;
	}

	public ApiStock(int recId, String apitype, String apiurl, String ucode,
			Integer apiid) {
		this.recId = recId;
		this.apitype = apitype;
		this.apiurl = apiurl;
		this.ucode = ucode;
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

	public Integer getApiid() {
		return this.apiid;
	}

	public void setApiid(Integer apiid) {
		this.apiid = apiid;
	}

}
