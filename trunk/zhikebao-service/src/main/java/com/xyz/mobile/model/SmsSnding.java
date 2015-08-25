package com.xyz.mobile.model;

// Generated 2009-10-31 14:39:21 by Hibernate Tools 3.2.4.GA

import java.util.Date;

/**
 * 发送短信
 */
public class SmsSnding implements java.io.Serializable {

	private int recId;
	private String rcvNum;
	private String status;
	private Date time;
	private Date sndTime;
	private String content;
	private Integer sndTimes;

	public SmsSnding() {
	}

	public SmsSnding(int recId) {
		this.recId = recId;
	}

	public SmsSnding(int recId, String rcvNum, String status, Date time,
			Date sndTime, String content, Integer sndTimes) {
		this.recId = recId;
		this.rcvNum = rcvNum;
		this.status = status;
		this.time = time;
		this.sndTime = sndTime;
		this.content = content;
		this.sndTimes = sndTimes;
	}

	public int getRecId() {
		return this.recId;
	}

	public void setRecId(int recId) {
		this.recId = recId;
	}

	public String getRcvNum() {
		return this.rcvNum;
	}

	public void setRcvNum(String rcvNum) {
		this.rcvNum = rcvNum;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getTime() {
		return this.time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public Date getSndTime() {
		return this.sndTime;
	}

	public void setSndTime(Date sndTime) {
		this.sndTime = sndTime;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getSndTimes() {
		return this.sndTimes;
	}

	public void setSndTimes(Integer sndTimes) {
		this.sndTimes = sndTimes;
	}

}
