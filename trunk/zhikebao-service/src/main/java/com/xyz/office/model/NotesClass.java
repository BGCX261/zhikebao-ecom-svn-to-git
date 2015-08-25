package com.xyz.office.model;

// Generated 2009-10-31 14:39:21 by Hibernate Tools 3.2.4.GA

/**
 * 记事分类
 */
public class NotesClass implements java.io.Serializable {

	private int recId;
	private String className;

	public NotesClass() {
	}

	public NotesClass(int recId) {
		this.recId = recId;
	}

	public NotesClass(int recId, String className) {
		this.recId = recId;
		this.className = className;
	}

	public int getRecId() {
		return this.recId;
	}

	public void setRecId(int recId) {
		this.recId = recId;
	}

	public String getClassName() {
		return this.className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

}
