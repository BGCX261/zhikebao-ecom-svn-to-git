package com.xyz.trade.model;

// Generated 2009-10-31 14:39:21 by Hibernate Tools 3.2.4.GA

/**
 * 货运方式
 */
public class SndStyle implements java.io.Serializable {

	private int recId;
	private String style;
	private String reserved;
	private Boolean bmonth;
	private String linkMan;
	private String linkStyle;
	private String remark;
	private Boolean bdefault;
	private Integer hot;

	public SndStyle() {
	}

	public SndStyle(int recId) {
		this.recId = recId;
	}

	public SndStyle(int recId, String style, String reserved, Boolean bmonth,
			String linkMan, String linkStyle, String remark, Boolean bdefault,
			Integer hot) {
		this.recId = recId;
		this.style = style;
		this.reserved = reserved;
		this.bmonth = bmonth;
		this.linkMan = linkMan;
		this.linkStyle = linkStyle;
		this.remark = remark;
		this.bdefault = bdefault;
		this.hot = hot;
	}

	public int getRecId() {
		return this.recId;
	}

	public void setRecId(int recId) {
		this.recId = recId;
	}

	public String getStyle() {
		return this.style;
	}

	public void setStyle(String style) {
		this.style = style;
	}

	public String getReserved() {
		return this.reserved;
	}

	public void setReserved(String reserved) {
		this.reserved = reserved;
	}

	public Boolean getBmonth() {
		return this.bmonth;
	}

	public void setBmonth(Boolean bmonth) {
		this.bmonth = bmonth;
	}

	public String getLinkMan() {
		return this.linkMan;
	}

	public void setLinkMan(String linkMan) {
		this.linkMan = linkMan;
	}

	public String getLinkStyle() {
		return this.linkStyle;
	}

	public void setLinkStyle(String linkStyle) {
		this.linkStyle = linkStyle;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Boolean getBdefault() {
		return this.bdefault;
	}

	public void setBdefault(Boolean bdefault) {
		this.bdefault = bdefault;
	}

	public Integer getHot() {
		return this.hot;
	}

	public void setHot(Integer hot) {
		this.hot = hot;
	}

}
