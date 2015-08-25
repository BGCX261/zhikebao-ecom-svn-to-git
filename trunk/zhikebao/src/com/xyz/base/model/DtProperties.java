package com.xyz.base.model;

// Generated 2009-10-31 14:39:21 by Hibernate Tools 3.2.4.GA

/**
 * ？？？
 */
public class DtProperties implements java.io.Serializable {

	private DtPropertiesId id;
	private int version;
	private Integer objectid;
	private String value;
	private String uvalue;
	private byte[] lvalue;

	public DtProperties() {
	}

	public DtProperties(DtPropertiesId id) {
		this.id = id;
	}

	public DtProperties(DtPropertiesId id, Integer objectid, String value,
			String uvalue, byte[] lvalue) {
		this.id = id;
		this.objectid = objectid;
		this.value = value;
		this.uvalue = uvalue;
		this.lvalue = lvalue;
	}

	public DtPropertiesId getId() {
		return this.id;
	}

	public void setId(DtPropertiesId id) {
		this.id = id;
	}

	public int getVersion() {
		return this.version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public Integer getObjectid() {
		return this.objectid;
	}

	public void setObjectid(Integer objectid) {
		this.objectid = objectid;
	}

	public String getValue() {
		return this.value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getUvalue() {
		return this.uvalue;
	}

	public void setUvalue(String uvalue) {
		this.uvalue = uvalue;
	}

	public byte[] getLvalue() {
		return this.lvalue;
	}

	public void setLvalue(byte[] lvalue) {
		this.lvalue = lvalue;
	}

}
