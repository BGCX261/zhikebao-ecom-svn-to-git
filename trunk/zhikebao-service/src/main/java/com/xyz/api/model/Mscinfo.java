package com.xyz.api.model;

// Generated 2009-10-31 14:39:21 by Hibernate Tools 3.2.4.GA

/**
 * MSC信息
 */
public class Mscinfo implements java.io.Serializable {

	private int recId;
	private String info;
	private String rcvTime;
	private String reserved;

	public Mscinfo() {
	}

	public Mscinfo(int recId) {
		this.recId = recId;
	}

	public Mscinfo(int recId, String info, String rcvTime, String reserved) {
		this.recId = recId;
		this.info = info;
		this.rcvTime = rcvTime;
		this.reserved = reserved;
	}

	public int getRecId() {
		return this.recId;
	}

	public void setRecId(int recId) {
		this.recId = recId;
	}

	public String getInfo() {
		return this.info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public String getRcvTime() {
		return this.rcvTime;
	}

	public void setRcvTime(String rcvTime) {
		this.rcvTime = rcvTime;
	}

	public String getReserved() {
		return this.reserved;
	}

	public void setReserved(String reserved) {
		this.reserved = reserved;
	}

}
