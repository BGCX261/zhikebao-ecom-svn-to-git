package com.xyz.serve.model;

// Generated 2009-10-31 14:39:21 by Hibernate Tools 3.2.4.GA

import java.util.Date;

/**
 * 事务记录
 */
public class CcCase implements java.io.Serializable {

	private int recId;
	private String caseNo;
	private String curStatus;
	private String caseType;
	private String customerId;
	private String customerName;
	private String customerTel;
	private String customerOtherInfo;
	private Date regDate;
	private String regOperator;
	private String caseInfo;
	private Date exeDate;
	private String exeDetail;
	private String exeOperator;
	private Date chkDate;
	private String chkOperator;
	private String remark;
	private String customerAdr;
	private String tradeNo;
	private String postId;
	private String sndStyle;

	public CcCase() {
	}

	public CcCase(int recId) {
		this.recId = recId;
	}

	public CcCase(int recId, String caseNo, String curStatus,
			String caseType, String customerId, String customerName,
			String customerTel, String customerOtherInfo, Date regDate,
			String regOperator, String caseInfo, Date exeDate,
			String exeDetail, String exeOperator, Date chkDate,
			String chkOperator, String remark, String customerAdr,
			String tradeNo, String postId, String sndStyle) {
		this.recId = recId;
		this.caseNo = caseNo;
		this.curStatus = curStatus;
		this.caseType = caseType;
		this.customerId = customerId;
		this.customerName = customerName;
		this.customerTel = customerTel;
		this.customerOtherInfo = customerOtherInfo;
		this.regDate = regDate;
		this.regOperator = regOperator;
		this.caseInfo = caseInfo;
		this.exeDate = exeDate;
		this.exeDetail = exeDetail;
		this.exeOperator = exeOperator;
		this.chkDate = chkDate;
		this.chkOperator = chkOperator;
		this.remark = remark;
		this.customerAdr = customerAdr;
		this.tradeNo = tradeNo;
		this.postId = postId;
		this.sndStyle = sndStyle;
	}

	public int getRecId() {
		return this.recId;
	}

	public void setRecId(int recId) {
		this.recId = recId;
	}

	public String getCaseNo() {
		return this.caseNo;
	}

	public void setCaseNo(String caseNo) {
		this.caseNo = caseNo;
	}

	public String getCurStatus() {
		return this.curStatus;
	}

	public void setCurStatus(String curStatus) {
		this.curStatus = curStatus;
	}

	public String getCaseType() {
		return this.caseType;
	}

	public void setCaseType(String caseType) {
		this.caseType = caseType;
	}

	public String getCustomerId() {
		return this.customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getCustomerName() {
		return this.customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getCustomerTel() {
		return this.customerTel;
	}

	public void setCustomerTel(String customerTel) {
		this.customerTel = customerTel;
	}

	public String getCustomerOtherInfo() {
		return this.customerOtherInfo;
	}

	public void setCustomerOtherInfo(String customerOtherInfo) {
		this.customerOtherInfo = customerOtherInfo;
	}

	public Date getRegDate() {
		return this.regDate;
	}

	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}

	public String getRegOperator() {
		return this.regOperator;
	}

	public void setRegOperator(String regOperator) {
		this.regOperator = regOperator;
	}

	public String getCaseInfo() {
		return this.caseInfo;
	}

	public void setCaseInfo(String caseInfo) {
		this.caseInfo = caseInfo;
	}

	public Date getExeDate() {
		return this.exeDate;
	}

	public void setExeDate(Date exeDate) {
		this.exeDate = exeDate;
	}

	public String getExeDetail() {
		return this.exeDetail;
	}

	public void setExeDetail(String exeDetail) {
		this.exeDetail = exeDetail;
	}

	public String getExeOperator() {
		return this.exeOperator;
	}

	public void setExeOperator(String exeOperator) {
		this.exeOperator = exeOperator;
	}

	public Date getChkDate() {
		return this.chkDate;
	}

	public void setChkDate(Date chkDate) {
		this.chkDate = chkDate;
	}

	public String getChkOperator() {
		return this.chkOperator;
	}

	public void setChkOperator(String chkOperator) {
		this.chkOperator = chkOperator;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getCustomerAdr() {
		return this.customerAdr;
	}

	public void setCustomerAdr(String customerAdr) {
		this.customerAdr = customerAdr;
	}

	public String getTradeNo() {
		return this.tradeNo;
	}

	public void setTradeNo(String tradeNo) {
		this.tradeNo = tradeNo;
	}

	public String getPostId() {
		return this.postId;
	}

	public void setPostId(String postId) {
		this.postId = postId;
	}

	public String getSndStyle() {
		return this.sndStyle;
	}

	public void setSndStyle(String sndStyle) {
		this.sndStyle = sndStyle;
	}

}
