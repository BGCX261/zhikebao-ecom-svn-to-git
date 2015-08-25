package com.xyz.mail.model;

// Generated 2009-10-31 14:39:21 by Hibernate Tools 3.2.4.GA

import java.math.BigDecimal;
import java.util.Date;

/**
 * 邮件任务
 */
public class Mailing implements java.io.Serializable {

	private int recId;
	private String sndTo;
	private String content;
	private String name;
	private String remark;
	private Date time;
	private String subject;
	private String type;
	private String status;
	private String reserved1;
	private BigDecimal reserved2;

	public Mailing() {
	}

	public Mailing(int recId) {
		this.recId = recId;
	}

	public Mailing(int recId, String sndTo, String content, String name,
			String remark, Date time, String subject, String type,
			String status, String reserved1, BigDecimal reserved2) {
		this.recId = recId;
		this.sndTo = sndTo;
		this.content = content;
		this.name = name;
		this.remark = remark;
		this.time = time;
		this.subject = subject;
		this.type = type;
		this.status = status;
		this.reserved1 = reserved1;
		this.reserved2 = reserved2;
	}

	public int getRecId() {
		return this.recId;
	}

	public void setRecId(int recId) {
		this.recId = recId;
	}

	public String getSndTo() {
		return this.sndTo;
	}

	public void setSndTo(String sndTo) {
		this.sndTo = sndTo;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Date getTime() {
		return this.time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public String getSubject() {
		return this.subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getReserved1() {
		return this.reserved1;
	}

	public void setReserved1(String reserved1) {
		this.reserved1 = reserved1;
	}

	public BigDecimal getReserved2() {
		return this.reserved2;
	}

	public void setReserved2(BigDecimal reserved2) {
		this.reserved2 = reserved2;
	}

}
