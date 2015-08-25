package com.xyz.mail.model;

// Generated 2009-10-31 14:39:21 by Hibernate Tools 3.2.4.GA

import java.util.Date;

/**
 * 短信息
 */
public class Msg implements java.io.Serializable {

	private int recId;
	private String read;
	private String snder;
	private String rcver;
	private String body;
	private Date time;
	private String title;
	private String warning;

	public Msg() {
	}

	public Msg(int recId) {
		this.recId = recId;
	}

	public Msg(int recId, String read, String snder, String rcver,
			String body, Date time, String title, String warning) {
		this.recId = recId;
		this.read = read;
		this.snder = snder;
		this.rcver = rcver;
		this.body = body;
		this.time = time;
		this.title = title;
		this.warning = warning;
	}

	public int getRecId() {
		return this.recId;
	}

	public void setRecId(int recId) {
		this.recId = recId;
	}

	public String getRead() {
		return this.read;
	}

	public void setRead(String read) {
		this.read = read;
	}

	public String getSnder() {
		return this.snder;
	}

	public void setSnder(String snder) {
		this.snder = snder;
	}

	public String getRcver() {
		return this.rcver;
	}

	public void setRcver(String rcver) {
		this.rcver = rcver;
	}

	public String getBody() {
		return this.body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public Date getTime() {
		return this.time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getWarning() {
		return this.warning;
	}

	public void setWarning(String warning) {
		this.warning = warning;
	}

}
