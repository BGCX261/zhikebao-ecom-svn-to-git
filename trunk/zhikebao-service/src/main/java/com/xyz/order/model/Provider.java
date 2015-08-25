package com.xyz.order.model;

// Generated 2009-10-31 14:39:21 by Hibernate Tools 3.2.4.GA

/**
 * 供货商
 */
public class Provider implements java.io.Serializable {

	private int recId;
	private String name;
	private String linkMan;
	private String adr;
	private String zip;
	private String phone;
	private String fax;
	private String email;
	private String remark;
	private String qq;
	private Integer hot;
	private String range;

	public Provider() {
	}

	public Provider(int recId) {
		this.recId = recId;
	}

	public Provider(int recId, String name, String linkMan, String adr,
			String zip, String phone, String fax, String email, String remark,
			String qq, Integer hot, String range) {
		this.recId = recId;
		this.name = name;
		this.linkMan = linkMan;
		this.adr = adr;
		this.zip = zip;
		this.phone = phone;
		this.fax = fax;
		this.email = email;
		this.remark = remark;
		this.qq = qq;
		this.hot = hot;
		this.range = range;
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

	public String getLinkMan() {
		return this.linkMan;
	}

	public void setLinkMan(String linkMan) {
		this.linkMan = linkMan;
	}

	public String getAdr() {
		return this.adr;
	}

	public void setAdr(String adr) {
		this.adr = adr;
	}

	public String getZip() {
		return this.zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getFax() {
		return this.fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getQq() {
		return this.qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	public Integer getHot() {
		return this.hot;
	}

	public void setHot(Integer hot) {
		this.hot = hot;
	}

	public String getRange() {
		return this.range;
	}

	public void setRange(String range) {
		this.range = range;
	}

}
