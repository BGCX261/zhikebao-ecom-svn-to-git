package com.xyz.trade.model;

// Generated 2009-10-31 14:39:21 by Hibernate Tools 3.2.4.GA

/**
 * 订单分析模板索引
 */
public class TradeInfoTemplateIndex implements java.io.Serializable {

	private int recId;
	private String templateType;
	private String templateName;
	private Integer nindex;
	private String kewWord;

	public TradeInfoTemplateIndex() {
	}

	public TradeInfoTemplateIndex(int recId) {
		this.recId = recId;
	}

	public TradeInfoTemplateIndex(int recId, String templateType,
			String templateName, Integer nindex, String kewWord) {
		this.recId = recId;
		this.templateType = templateType;
		this.templateName = templateName;
		this.nindex = nindex;
		this.kewWord = kewWord;
	}

	public int getRecId() {
		return this.recId;
	}

	public void setRecId(int recId) {
		this.recId = recId;
	}

	public String getTemplateType() {
		return this.templateType;
	}

	public void setTemplateType(String templateType) {
		this.templateType = templateType;
	}

	public String getTemplateName() {
		return this.templateName;
	}

	public void setTemplateName(String templateName) {
		this.templateName = templateName;
	}

	public Integer getNindex() {
		return this.nindex;
	}

	public void setNindex(Integer nindex) {
		this.nindex = nindex;
	}

	public String getKewWord() {
		return this.kewWord;
	}

	public void setKewWord(String kewWord) {
		this.kewWord = kewWord;
	}

}
