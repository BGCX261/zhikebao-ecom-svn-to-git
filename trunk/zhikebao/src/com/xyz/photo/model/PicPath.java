package com.xyz.photo.model;

// Generated 2009-10-31 14:39:21 by Hibernate Tools 3.2.4.GA

/**
 * 商品图片
 */
public class PicPath implements java.io.Serializable {

	private int recId;
	private String goodsNo;
	private String title;
	private String picIndex;
	private String filesize;
	private String status;
	private String remark;
	private String lastEditTime;
	private Boolean bdownload;
	private String editId;
	private String lastEditName;

	public PicPath() {
	}

	public PicPath(int recId) {
		this.recId = recId;
	}

	public PicPath(int recId, String goodsNo, String title, String picIndex,
			String filesize, String status, String remark, String lastEditTime,
			Boolean bdownload, String editId, String lastEditName) {
		this.recId = recId;
		this.goodsNo = goodsNo;
		this.title = title;
		this.picIndex = picIndex;
		this.filesize = filesize;
		this.status = status;
		this.remark = remark;
		this.lastEditTime = lastEditTime;
		this.bdownload = bdownload;
		this.editId = editId;
		this.lastEditName = lastEditName;
	}

	public int getRecId() {
		return this.recId;
	}

	public void setRecId(int recId) {
		this.recId = recId;
	}

	public String getGoodsNo() {
		return this.goodsNo;
	}

	public void setGoodsNo(String goodsNo) {
		this.goodsNo = goodsNo;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getPicIndex() {
		return this.picIndex;
	}

	public void setPicIndex(String picIndex) {
		this.picIndex = picIndex;
	}

	public String getFilesize() {
		return this.filesize;
	}

	public void setFilesize(String filesize) {
		this.filesize = filesize;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getLastEditTime() {
		return this.lastEditTime;
	}

	public void setLastEditTime(String lastEditTime) {
		this.lastEditTime = lastEditTime;
	}

	public Boolean getBdownload() {
		return this.bdownload;
	}

	public void setBdownload(Boolean bdownload) {
		this.bdownload = bdownload;
	}

	public String getEditId() {
		return this.editId;
	}

	public void setEditId(String editId) {
		this.editId = editId;
	}

	public String getLastEditName() {
		return this.lastEditName;
	}

	public void setLastEditName(String lastEditName) {
		this.lastEditName = lastEditName;
	}

}
