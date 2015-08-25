package com.xyz.product.model;

// Generated 2009-10-31 14:39:21 by Hibernate Tools 3.2.4.GA

/**
 * 商品描述模板
 */
public class GoodsDesc implements java.io.Serializable {

	private int recId;
	private String defineModal;
	private Boolean bok;

	public GoodsDesc() {
	}

	public GoodsDesc(int recId) {
		this.recId = recId;
	}

	public GoodsDesc(int recId, String defineModal, Boolean bok) {
		this.recId = recId;
		this.defineModal = defineModal;
		this.bok = bok;
	}

	public int getRecId() {
		return this.recId;
	}

	public void setRecId(int recId) {
		this.recId = recId;
	}

	public String getDefineModal() {
		return this.defineModal;
	}

	public void setDefineModal(String defineModal) {
		this.defineModal = defineModal;
	}

	public Boolean getBok() {
		return this.bok;
	}

	public void setBok(Boolean bok) {
		this.bok = bok;
	}

}
