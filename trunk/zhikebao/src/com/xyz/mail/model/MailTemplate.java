package com.xyz.mail.model;

// Generated 2009-10-31 14:39:21 by Hibernate Tools 3.2.4.GA

/**
 * 邮件模板
 */
public class MailTemplate implements java.io.Serializable {

	private int recId;
	private String name;
	private String type;
	private String content;
	private String title;

	public MailTemplate() {
	}

	public MailTemplate(int recId) {
		this.recId = recId;
	}

	public MailTemplate(int recId, String name, String type, String content,
			String title) {
		this.recId = recId;
		this.name = name;
		this.type = type;
		this.content = content;
		this.title = title;
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

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

}
