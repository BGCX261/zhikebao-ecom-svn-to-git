package com.xyz.office.model;

// Generated 2009-10-31 14:39:21 by Hibernate Tools 3.2.4.GA

import java.util.Date;

/**
 * 记事本
 */
public class Notes implements java.io.Serializable {

	private int recId;
	private Date date;
	private String title;
	private String content;
	private String author;
	private Date warningDateTime;
	private Boolean btimeoutCancel;
	private Boolean bwarningCancel;
	private Boolean bwarningOk;
	private Boolean bwarningSet;
	private Date time;
	private String shareTo;
	private String editer;
	private String class_;

	public Notes() {
	}

	public Notes(int recId) {
		this.recId = recId;
	}

	public Notes(int recId, Date date, String title, String content,
			String author, Date warningDateTime, Boolean btimeoutCancel,
			Boolean bwarningCancel, Boolean bwarningOk, Boolean bwarningSet,
			Date time, String shareTo, String editer, String class_) {
		this.recId = recId;
		this.date = date;
		this.title = title;
		this.content = content;
		this.author = author;
		this.warningDateTime = warningDateTime;
		this.btimeoutCancel = btimeoutCancel;
		this.bwarningCancel = bwarningCancel;
		this.bwarningOk = bwarningOk;
		this.bwarningSet = bwarningSet;
		this.time = time;
		this.shareTo = shareTo;
		this.editer = editer;
		this.class_ = class_;
	}

	public int getRecId() {
		return this.recId;
	}

	public void setRecId(int recId) {
		this.recId = recId;
	}

	public Date getDate() {
		return this.date;
	}

	public void setDate(Date date) {
		this.date = date;
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

	public String getAuthor() {
		return this.author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public Date getWarningDateTime() {
		return this.warningDateTime;
	}

	public void setWarningDateTime(Date warningDateTime) {
		this.warningDateTime = warningDateTime;
	}

	public Boolean getBtimeoutCancel() {
		return this.btimeoutCancel;
	}

	public void setBtimeoutCancel(Boolean btimeoutCancel) {
		this.btimeoutCancel = btimeoutCancel;
	}

	public Boolean getBwarningCancel() {
		return this.bwarningCancel;
	}

	public void setBwarningCancel(Boolean bwarningCancel) {
		this.bwarningCancel = bwarningCancel;
	}

	public Boolean getBwarningOk() {
		return this.bwarningOk;
	}

	public void setBwarningOk(Boolean bwarningOk) {
		this.bwarningOk = bwarningOk;
	}

	public Boolean getBwarningSet() {
		return this.bwarningSet;
	}

	public void setBwarningSet(Boolean bwarningSet) {
		this.bwarningSet = bwarningSet;
	}

	public Date getTime() {
		return this.time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public String getShareTo() {
		return this.shareTo;
	}

	public void setShareTo(String shareTo) {
		this.shareTo = shareTo;
	}

	public String getEditer() {
		return this.editer;
	}

	public void setEditer(String editer) {
		this.editer = editer;
	}

	public String getClass_() {
		return this.class_;
	}

	public void setClass_(String class_) {
		this.class_ = class_;
	}

}
