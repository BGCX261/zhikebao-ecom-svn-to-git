package com.xyz.trade.model;

// Generated 2009-10-31 14:39:21 by Hibernate Tools 3.2.4.GA

/**
 * 订单标记
 */
public class TradeFlag implements java.io.Serializable {

	private int recId;
	private String sflag;
	private String bgcolor;
	private String fontColor;
	private Integer tag;

	public TradeFlag() {
	}

	public TradeFlag(int recId) {
		this.recId = recId;
	}

	public TradeFlag(int recId, String sflag, String bgcolor,
			String fontColor, Integer tag) {
		this.recId = recId;
		this.sflag = sflag;
		this.bgcolor = bgcolor;
		this.fontColor = fontColor;
		this.tag = tag;
	}

	public int getRecId() {
		return this.recId;
	}

	public void setRecId(int recId) {
		this.recId = recId;
	}

	public String getSflag() {
		return this.sflag;
	}

	public void setSflag(String sflag) {
		this.sflag = sflag;
	}

	public String getBgcolor() {
		return this.bgcolor;
	}

	public void setBgcolor(String bgcolor) {
		this.bgcolor = bgcolor;
	}

	public String getFontColor() {
		return this.fontColor;
	}

	public void setFontColor(String fontColor) {
		this.fontColor = fontColor;
	}

	public Integer getTag() {
		return this.tag;
	}

	public void setTag(Integer tag) {
		this.tag = tag;
	}

}
