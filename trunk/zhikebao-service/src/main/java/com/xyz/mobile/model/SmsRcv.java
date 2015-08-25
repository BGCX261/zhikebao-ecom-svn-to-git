package com.xyz.mobile.model;

// Generated 2009-10-31 14:39:21 by Hibernate Tools 3.2.4.GA

import java.util.Date;

/**
 * 收到短信
 */
public class SmsRcv implements java.io.Serializable {

	private int recId;
	private String msg;
	private String sender;
	private Boolean status;
	private Date time;
	private Boolean writeBack;

	public SmsRcv() {
	}

	public SmsRcv(int recId) {
		this.recId = recId;
	}

	public SmsRcv(int recId, String msg, String sender, Boolean status,
			Date time, Boolean writeBack) {
		this.recId = recId;
		this.msg = msg;
		this.sender = sender;
		this.status = status;
		this.time = time;
		this.writeBack = writeBack;
	}

	public int getRecId() {
		return this.recId;
	}

	public void setRecId(int recId) {
		this.recId = recId;
	}

	public String getMsg() {
		return this.msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getSender() {
		return this.sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public Boolean getStatus() {
		return this.status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public Date getTime() {
		return this.time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public Boolean getWriteBack() {
		return this.writeBack;
	}

	public void setWriteBack(Boolean writeBack) {
		this.writeBack = writeBack;
	}

}
