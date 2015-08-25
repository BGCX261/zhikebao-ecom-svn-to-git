package com.xyz.system.model;

// Generated 2009-10-31 14:39:21 by Hibernate Tools 3.2.4.GA

import java.util.Date;

/**
 * 备份日志
 */
public class BakLog implements java.io.Serializable {

	private int recId;
	private Date bakDate;
	private String fileName;
	private String bakFile;

	public BakLog() {
	}

	public BakLog(int recId) {
		this.recId = recId;
	}

	public BakLog(int recId, Date bakDate, String fileName, String bakFile) {
		this.recId = recId;
		this.bakDate = bakDate;
		this.fileName = fileName;
		this.bakFile = bakFile;
	}

	public int getRecId() {
		return this.recId;
	}

	public void setRecId(int recId) {
		this.recId = recId;
	}

	public Date getBakDate() {
		return this.bakDate;
	}

	public void setBakDate(Date bakDate) {
		this.bakDate = bakDate;
	}

	public String getFileName() {
		return this.fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getBakFile() {
		return this.bakFile;
	}

	public void setBakFile(String bakFile) {
		this.bakFile = bakFile;
	}

}
