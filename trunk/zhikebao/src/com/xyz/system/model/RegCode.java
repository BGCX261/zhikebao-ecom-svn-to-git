package com.xyz.system.model;

// Generated 2009-10-31 14:39:21 by Hibernate Tools 3.2.4.GA

/**
 * 注册码
 */
public class RegCode implements java.io.Serializable {

	private int recId;
	private String code;
	private String softId;
	private String reserved1;
	private String reserved2;

	public RegCode() {
	}

	public RegCode(int recId) {
		this.recId = recId;
	}

	public RegCode(int recId, String code, String softId, String reserved1,
			String reserved2) {
		this.recId = recId;
		this.code = code;
		this.softId = softId;
		this.reserved1 = reserved1;
		this.reserved2 = reserved2;
	}

	public int getRecId() {
		return this.recId;
	}

	public void setRecId(int recId) {
		this.recId = recId;
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getSoftId() {
		return this.softId;
	}

	public void setSoftId(String softId) {
		this.softId = softId;
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
