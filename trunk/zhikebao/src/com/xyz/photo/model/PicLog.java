package com.xyz.photo.model;

// Generated 2009-10-31 14:39:21 by Hibernate Tools 3.2.4.GA

/**
 * 图片历史
 */
public class PicLog implements java.io.Serializable {

	private int recId;
	private String picIndex;

	public PicLog() {
	}

	public PicLog(int recId) {
		this.recId = recId;
	}

	public PicLog(int recId, String picIndex) {
		this.recId = recId;
		this.picIndex = picIndex;
	}

	public int getRecId() {
		return this.recId;
	}

	public void setRecId(int recId) {
		this.recId = recId;
	}

	public String getPicIndex() {
		return this.picIndex;
	}

	public void setPicIndex(String picIndex) {
		this.picIndex = picIndex;
	}

}
