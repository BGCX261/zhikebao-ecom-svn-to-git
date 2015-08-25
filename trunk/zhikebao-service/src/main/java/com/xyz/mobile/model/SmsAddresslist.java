package com.xyz.mobile.model;

// Generated 2009-10-31 14:39:21 by Hibernate Tools 3.2.4.GA

/**
 * 短信通讯录
 */
public class SmsAddresslist implements java.io.Serializable {

	private int recId;
	private String name;
	private String telNum;

	public SmsAddresslist() {
	}

	public SmsAddresslist(int recId) {
		this.recId = recId;
	}

	public SmsAddresslist(int recId, String name, String telNum) {
		this.recId = recId;
		this.name = name;
		this.telNum = telNum;
	}

	public int getRecId() {
		return this.recId;
	}

	public void setRecId(int recId) {
		this.recId = recId;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTelNum() {
		return this.telNum;
	}

	public void setTelNum(String telNum) {
		this.telNum = telNum;
	}

}
