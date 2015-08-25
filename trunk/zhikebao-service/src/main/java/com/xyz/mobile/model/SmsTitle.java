package com.xyz.mobile.model;

// Generated 2009-10-31 14:39:21 by Hibernate Tools 3.2.4.GA

/**
 * 短信模板
 */
public class SmsTitle implements java.io.Serializable {

	private int recId;
	private String title;
	private String content;

	public SmsTitle() {
	}

	public SmsTitle(int recId) {
		this.recId = recId;
	}

	public SmsTitle(int recId, String title, String content) {
		this.recId = recId;
		this.title = title;
		this.content = content;
	}

	public int getRecId() {
		return this.recId;
	}

	public void setRecId(int recId) {
		this.recId = recId;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}
