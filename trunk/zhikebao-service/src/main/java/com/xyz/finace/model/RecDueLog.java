package com.xyz.finace.model;

// Generated 2009-10-31 14:39:21 by Hibernate Tools 3.2.4.GA

/**
 * 应收应付日志
 */
public class RecDueLog implements java.io.Serializable {

	private int recId;
	private String ysyfid;
	private String eventTime;
	private String operator;
	private String event;

	public RecDueLog() {
	}

	public RecDueLog(int recId) {
		this.recId = recId;
	}

	public RecDueLog(int recId, String ysyfid, String eventTime,
			String operator, String event) {
		this.recId = recId;
		this.ysyfid = ysyfid;
		this.eventTime = eventTime;
		this.operator = operator;
		this.event = event;
	}

	public int getRecId() {
		return this.recId;
	}

	public void setRecId(int recId) {
		this.recId = recId;
	}

	public String getYsyfid() {
		return this.ysyfid;
	}

	public void setYsyfid(String ysyfid) {
		this.ysyfid = ysyfid;
	}

	public String getEventTime() {
		return this.eventTime;
	}

	public void setEventTime(String eventTime) {
		this.eventTime = eventTime;
	}

	public String getOperator() {
		return this.operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public String getEvent() {
		return this.event;
	}

	public void setEvent(String event) {
		this.event = event;
	}

}
