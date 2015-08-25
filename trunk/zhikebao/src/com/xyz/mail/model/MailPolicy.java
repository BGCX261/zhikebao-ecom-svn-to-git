package com.xyz.mail.model;

// Generated 2009-10-31 14:39:21 by Hibernate Tools 3.2.4.GA

/**
 * 邮件策略
 */
public class MailPolicy implements java.io.Serializable {

	private int recId;
	private String snd;
	private String content;
	private String reserved1;
	private String reserved2;

	public MailPolicy() {
	}

	public MailPolicy(int recId) {
		this.recId = recId;
	}

	public MailPolicy(int recId, String snd, String content,
			String reserved1, String reserved2) {
		this.recId = recId;
		this.snd = snd;
		this.content = content;
		this.reserved1 = reserved1;
		this.reserved2 = reserved2;
	}

	public int getRecId() {
		return this.recId;
	}

	public void setRecId(int recId) {
		this.recId = recId;
	}

	public String getSnd() {
		return this.snd;
	}

	public void setSnd(String snd) {
		this.snd = snd;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getReserved1() {
		return this.reserved1;
	}

	public void setReserved1(String reserved1) {
		this.reserved1 = reserved1;
	}

	public String getReserved2() {
		return this.reserved2;
	}

	public void setReserved2(String reserved2) {
		this.reserved2 = reserved2;
	}

}
