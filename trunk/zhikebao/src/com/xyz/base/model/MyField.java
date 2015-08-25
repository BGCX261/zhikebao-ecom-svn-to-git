package com.xyz.base.model;

// Generated 2009-10-31 14:39:21 by Hibernate Tools 3.2.4.GA

/**
 * 自定义字段
 */
public class MyField implements java.io.Serializable {

	private int recId;
	private String defineField;
	private String defineFieldName;
	private String itemForSelect1;
	private String itemForSelect2;
	private String itemForSelect3;
	private String itemForSelect4;
	private String itemForSelect5;
	private String itemForSelect6;
	private String type;

	public MyField() {
	}

	public MyField(int recId) {
		this.recId = recId;
	}

	public MyField(int recId, String defineField, String defineFieldName,
			String itemForSelect1, String itemForSelect2,
			String itemForSelect3, String itemForSelect4,
			String itemForSelect5, String itemForSelect6, String type) {
		this.recId = recId;
		this.defineField = defineField;
		this.defineFieldName = defineFieldName;
		this.itemForSelect1 = itemForSelect1;
		this.itemForSelect2 = itemForSelect2;
		this.itemForSelect3 = itemForSelect3;
		this.itemForSelect4 = itemForSelect4;
		this.itemForSelect5 = itemForSelect5;
		this.itemForSelect6 = itemForSelect6;
		this.type = type;
	}

	public int getRecId() {
		return this.recId;
	}

	public void setRecId(int recId) {
		this.recId = recId;
	}

	public String getDefineField() {
		return this.defineField;
	}

	public void setDefineField(String defineField) {
		this.defineField = defineField;
	}

	public String getDefineFieldName() {
		return this.defineFieldName;
	}

	public void setDefineFieldName(String defineFieldName) {
		this.defineFieldName = defineFieldName;
	}

	public String getItemForSelect1() {
		return this.itemForSelect1;
	}

	public void setItemForSelect1(String itemForSelect1) {
		this.itemForSelect1 = itemForSelect1;
	}

	public String getItemForSelect2() {
		return this.itemForSelect2;
	}

	public void setItemForSelect2(String itemForSelect2) {
		this.itemForSelect2 = itemForSelect2;
	}

	public String getItemForSelect3() {
		return this.itemForSelect3;
	}

	public void setItemForSelect3(String itemForSelect3) {
		this.itemForSelect3 = itemForSelect3;
	}

	public String getItemForSelect4() {
		return this.itemForSelect4;
	}

	public void setItemForSelect4(String itemForSelect4) {
		this.itemForSelect4 = itemForSelect4;
	}

	public String getItemForSelect5() {
		return this.itemForSelect5;
	}

	public void setItemForSelect5(String itemForSelect5) {
		this.itemForSelect5 = itemForSelect5;
	}

	public String getItemForSelect6() {
		return this.itemForSelect6;
	}

	public void setItemForSelect6(String itemForSelect6) {
		this.itemForSelect6 = itemForSelect6;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
