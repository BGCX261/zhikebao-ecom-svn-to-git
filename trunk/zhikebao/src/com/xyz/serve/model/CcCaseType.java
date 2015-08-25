package com.xyz.serve.model;

// Generated 2009-10-31 14:39:21 by Hibernate Tools 3.2.4.GA

/**
 * 事务类型
 */
public class CcCaseType implements java.io.Serializable {

	private int recId;
	private String caseType;

	public CcCaseType() {
	}

	public CcCaseType(int recId) {
		this.recId = recId;
	}

	public CcCaseType(int recId, String caseType) {
		this.recId = recId;
		this.caseType = caseType;
	}

	public int getRecId() {
		return this.recId;
	}

	public void setRecId(int recId) {
		this.recId = recId;
	}

	public String getCaseType() {
		return this.caseType;
	}

	public void setCaseType(String caseType) {
		this.caseType = caseType;
	}

}
