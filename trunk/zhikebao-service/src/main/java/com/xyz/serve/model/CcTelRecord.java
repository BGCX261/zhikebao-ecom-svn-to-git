package com.xyz.serve.model;

// Generated 2009-10-31 14:39:21 by Hibernate Tools 3.2.4.GA

import java.util.Date;

/**
 * 客户回访电话记录
 */
public class CcTelRecord implements java.io.Serializable {

	private int recId;
	private Date date;
	private String time;
	private String telNo;
	private String telLine;
	private String wavFilePath;
	private String telType;
	private String operator;
	private String inOut;
	private String customerInfo;

	public CcTelRecord() {
	}

	public CcTelRecord(int recId) {
		this.recId = recId;
	}

	public CcTelRecord(int recId, Date date, String time, String telNo,
			String telLine, String wavFilePath, String telType,
			String operator, String inOut, String customerInfo) {
		this.recId = recId;
		this.date = date;
		this.time = time;
		this.telNo = telNo;
		this.telLine = telLine;
		this.wavFilePath = wavFilePath;
		this.telType = telType;
		this.operator = operator;
		this.inOut = inOut;
		this.customerInfo = customerInfo;
	}

	public int getRecId() {
		return this.recId;
	}

	public void setRecId(int recId) {
		this.recId = recId;
	}

	public Date getDate() {
		return this.date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getTime() {
		return this.time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getTelNo() {
		return this.telNo;
	}

	public void setTelNo(String telNo) {
		this.telNo = telNo;
	}

	public String getTelLine() {
		return this.telLine;
	}

	public void setTelLine(String telLine) {
		this.telLine = telLine;
	}

	public String getWavFilePath() {
		return this.wavFilePath;
	}

	public void setWavFilePath(String wavFilePath) {
		this.wavFilePath = wavFilePath;
	}

	public String getTelType() {
		return this.telType;
	}

	public void setTelType(String telType) {
		this.telType = telType;
	}

	public String getOperator() {
		return this.operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public String getInOut() {
		return this.inOut;
	}

	public void setInOut(String inOut) {
		this.inOut = inOut;
	}

	public String getCustomerInfo() {
		return this.customerInfo;
	}

	public void setCustomerInfo(String customerInfo) {
		this.customerInfo = customerInfo;
	}

}
