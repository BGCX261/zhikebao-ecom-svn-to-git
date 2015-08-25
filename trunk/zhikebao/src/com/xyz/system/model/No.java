package com.xyz.system.model;

// Generated 2009-10-31 14:39:21 by Hibernate Tools 3.2.4.GA

import java.util.Date;

/**
 * 编号队列
 */
public class No implements java.io.Serializable {

	private int recId;
	private Integer pointer;
	private Date date;
	private String notype;
	private String operationNo;

	public No() {
	}

	public No(int recId) {
		this.recId = recId;
	}

	public No(int recId, Integer pointer, Date date, String notype,
			String operationNo) {
		this.recId = recId;
		this.pointer = pointer;
		this.date = date;
		this.notype = notype;
		this.operationNo = operationNo;
	}

	public int getRecId() {
		return this.recId;
	}

	public void setRecId(int recId) {
		this.recId = recId;
	}

	public Integer getPointer() {
		return this.pointer;
	}

	public void setPointer(Integer pointer) {
		this.pointer = pointer;
	}

	public Date getDate() {
		return this.date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getNotype() {
		return this.notype;
	}

	public void setNotype(String notype) {
		this.notype = notype;
	}

	public String getOperationNo() {
		return this.operationNo;
	}

	public void setOperationNo(String operationNo) {
		this.operationNo = operationNo;
	}

}
