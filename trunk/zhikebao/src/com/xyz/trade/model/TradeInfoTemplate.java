package com.xyz.trade.model;

// Generated 2009-10-31 14:39:21 by Hibernate Tools 3.2.4.GA

/**
 * 订单分析模板
 */
public class TradeInfoTemplate implements java.io.Serializable {

	private int recId;
	private String itemName;
	private String tagHead;
	private String tagTail;
	private Integer npos;
	private String templateName;

	public TradeInfoTemplate() {
	}

	public TradeInfoTemplate(int recId) {
		this.recId = recId;
	}

	public TradeInfoTemplate(int recId, String itemName, String tagHead,
			String tagTail, Integer npos, String templateName) {
		this.recId = recId;
		this.itemName = itemName;
		this.tagHead = tagHead;
		this.tagTail = tagTail;
		this.npos = npos;
		this.templateName = templateName;
	}

	public int getRecId() {
		return this.recId;
	}

	public void setRecId(int recId) {
		this.recId = recId;
	}

	public String getItemName() {
		return this.itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getTagHead() {
		return this.tagHead;
	}

	public void setTagHead(String tagHead) {
		this.tagHead = tagHead;
	}

	public String getTagTail() {
		return this.tagTail;
	}

	public void setTagTail(String tagTail) {
		this.tagTail = tagTail;
	}

	public Integer getNpos() {
		return this.npos;
	}

	public void setNpos(Integer npos) {
		this.npos = npos;
	}

	public String getTemplateName() {
		return this.templateName;
	}

	public void setTemplateName(String templateName) {
		this.templateName = templateName;
	}

}
