package com.xyz.order.model;

// Generated 2009-10-31 14:39:21 by Hibernate Tools 3.2.4.GA

/**
 * 采购退货明细
 */
public class PurchaseReturnDetail implements java.io.Serializable {

	private PurchaseReturnDetailId id;

	public PurchaseReturnDetail() {
	}

	public PurchaseReturnDetail(PurchaseReturnDetailId id) {
		this.id = id;
	}

	public PurchaseReturnDetailId getId() {
		return this.id;
	}

	public void setId(PurchaseReturnDetailId id) {
		this.id = id;
	}

}
