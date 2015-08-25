package com.xyz.api.model;

// Generated 2009-10-31 14:39:21 by Hibernate Tools 3.2.4.GA

import java.util.Date;

/**
 * api列表
 */
public class ApiList implements java.io.Serializable {

	private int recId;
	private String status;
	private String data;
	private String apiurl;
	private String type;
	private Integer postTimes;
	private Integer apirecId;
	private Date createTime;

	public ApiList() {
	}

	public ApiList(int recId) {
		this.recId = recId;
	}

	public ApiList(int recId, String status, String data, String apiurl,
			String type, Integer postTimes, Integer apirecId, Date createTime) {
		this.recId = recId;
		this.status = status;
		this.data = data;
		this.apiurl = apiurl;
		this.type = type;
		this.postTimes = postTimes;
		this.apirecId = apirecId;
		this.createTime = createTime;
	}

	public int getRecId() {
		return this.recId;
	}

	public void setRecId(int recId) {
		this.recId = recId;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getData() {
		return this.data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getApiurl() {
		return this.apiurl;
	}

	public void setApiurl(String apiurl) {
		this.apiurl = apiurl;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Integer getPostTimes() {
		return this.postTimes;
	}

	public void setPostTimes(Integer postTimes) {
		this.postTimes = postTimes;
	}

	public Integer getApirecId() {
		return this.apirecId;
	}

	public void setApirecId(Integer apirecId) {
		this.apirecId = apirecId;
	}

	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

}
