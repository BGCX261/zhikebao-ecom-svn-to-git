package com.xyz.system.model;

// Generated 2009-10-31 14:39:21 by Hibernate Tools 3.2.4.GA

/**
 * 编号计划
 */
public class Noplan implements java.io.Serializable {

	private int recId;
	private String notype;
	private String sprefix;
	private Integer stagfixPos;
	private Integer startNo;
	private String noname;
	private Boolean bday;

	public Noplan() {
	}

	public Noplan(int recId) {
		this.recId = recId;
	}

	public Noplan(int recId, String notype, String sprefix,
			Integer stagfixPos, Integer startNo, String noname, Boolean bday) {
		this.recId = recId;
		this.notype = notype;
		this.sprefix = sprefix;
		this.stagfixPos = stagfixPos;
		this.startNo = startNo;
		this.noname = noname;
		this.bday = bday;
	}

	public int getRecId() {
		return this.recId;
	}

	public void setRecId(int recId) {
		this.recId = recId;
	}

	public String getNotype() {
		return this.notype;
	}

	public void setNotype(String notype) {
		this.notype = notype;
	}

	public String getSprefix() {
		return this.sprefix;
	}

	public void setSprefix(String sprefix) {
		this.sprefix = sprefix;
	}

	public Integer getStagfixPos() {
		return this.stagfixPos;
	}

	public void setStagfixPos(Integer stagfixPos) {
		this.stagfixPos = stagfixPos;
	}

	public Integer getStartNo() {
		return this.startNo;
	}

	public void setStartNo(Integer startNo) {
		this.startNo = startNo;
	}

	public String getNoname() {
		return this.noname;
	}

	public void setNoname(String noname) {
		this.noname = noname;
	}

	public Boolean getBday() {
		return this.bday;
	}

	public void setBday(Boolean bday) {
		this.bday = bday;
	}

}
