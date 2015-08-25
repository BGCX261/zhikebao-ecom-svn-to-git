package com.xyz.system.model;

// Generated 2009-10-31 14:39:21 by Hibernate Tools 3.2.4.GA

import java.util.Date;

/**
 * 系统日志
 */
public class Log implements java.io.Serializable {

	private int recId;
	private Date time;
	private String event;
	private String user;

	public Log() {
	}

	public Log(int recId) {
		this.recId = recId;
	}

	public Log(int recId, Date time, String event, String user) {
		this.recId = recId;
		this.time = time;
		this.event = event;
		this.user = user;
	}

	public int getRecId() {
		return this.recId;
	}

	public void setRecId(int recId) {
		this.recId = recId;
	}

	public Date getTime() {
		return this.time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public String getEvent() {
		return this.event;
	}

	public void setEvent(String event) {
		this.event = event;
	}

	public String getUser() {
		return this.user;
	}

	public void setUser(String user) {
		this.user = user;
	}

}
