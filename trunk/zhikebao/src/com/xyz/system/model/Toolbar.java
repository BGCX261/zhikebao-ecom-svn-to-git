package com.xyz.system.model;

// Generated 2009-10-31 14:39:21 by Hibernate Tools 3.2.4.GA

/**
 * 工具栏
 */
public class Toolbar implements java.io.Serializable {

	private int recId;
	private String btnName;
	private Integer btnValue;
	private Integer btnIndex;
	private Integer flag;
	private String operator;

	public Toolbar() {
	}

	public Toolbar(int recId) {
		this.recId = recId;
	}

	public Toolbar(int recId, String btnName, Integer btnValue,
			Integer btnIndex, Integer flag, String operator) {
		this.recId = recId;
		this.btnName = btnName;
		this.btnValue = btnValue;
		this.btnIndex = btnIndex;
		this.flag = flag;
		this.operator = operator;
	}

	public int getRecId() {
		return this.recId;
	}

	public void setRecId(int recId) {
		this.recId = recId;
	}

	public String getBtnName() {
		return this.btnName;
	}

	public void setBtnName(String btnName) {
		this.btnName = btnName;
	}

	public Integer getBtnValue() {
		return this.btnValue;
	}

	public void setBtnValue(Integer btnValue) {
		this.btnValue = btnValue;
	}

	public Integer getBtnIndex() {
		return this.btnIndex;
	}

	public void setBtnIndex(Integer btnIndex) {
		this.btnIndex = btnIndex;
	}

	public Integer getFlag() {
		return this.flag;
	}

	public void setFlag(Integer flag) {
		this.flag = flag;
	}

	public String getOperator() {
		return this.operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

}
