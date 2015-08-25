package com.xyz.trade.model;

import com.xyz.resources.model.BaseModel;

/**
 * 发货信息
 * 
 */
public class Delivery extends BaseModel {

	//
	private static final long serialVersionUID = 245779617234550257L;
	private String tid;
	private String companyCode;
	private String outSid;
	private Integer saId;
	private String memo;
	private String orderType;

	public String getTid() {
		return tid;
	}

	public void setTid(String tid) {
		this.tid = tid;
	}

	public Integer getSaId() {
		return saId;
	}

	public void setSaId(Integer saId) {
		this.saId = saId;
	}

	// public String getAppIp() {
	// return appIp;
	// }
	// public void setAppIp(String appIp) {
	// this.appIp = appIp;
	// }
	public String getCompanyCode() {
		return companyCode;
	}

	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}

	public String getOutSid() {
		return outSid;
	}

	public void setOutSid(String outSid) {
		this.outSid = outSid;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public String getOrderType() {
		return orderType;
	}

	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}
}
