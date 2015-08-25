package com.xyz.api.model;

// Generated 2009-10-31 14:39:21 by Hibernate Tools 3.2.4.GA

import java.util.Date;

/**
 * MSC接口
 */
public class Mscinterface implements java.io.Serializable {

	private int recId;
	private String status;
	private Date time;
	private String data;
	private String type;

	public Mscinterface() {
	}

	public Mscinterface(int recId) {
		this.recId = recId;
	}

	public Mscinterface(int recId, String status, Date time, String data,
			String type) {
		this.recId = recId;
		this.status = status;
		this.time = time;
		this.data = data;
		this.type = type;
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

	public Date getTime() {
		return this.time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public String getData() {
		return this.data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
