package com.xyz.base.model;

// Generated 2009-10-31 14:39:21 by Hibernate Tools 3.2.4.GA

import java.math.BigDecimal;

/**
 * 员工目录
 */
public class Staff implements java.io.Serializable {

	private int recId;
	private String name;
	private String sex;
	private String home;
	private BigDecimal salary;
	private BigDecimal tc;
	private String formula;
	private String remark;
	private String qq;
	private String email;
	private String adr;
	private String phone;
	private String cellPhone;
	private String cardId;

	public Staff() {
	}

	public Staff(int recId) {
		this.recId = recId;
	}

	public Staff(int recId, String name, String sex, String home,
			BigDecimal salary, BigDecimal tc, String formula, String remark,
			String qq, String email, String adr, String phone,
			String cellPhone, String cardId) {
		this.recId = recId;
		this.name = name;
		this.sex = sex;
		this.home = home;
		this.salary = salary;
		this.tc = tc;
		this.formula = formula;
		this.remark = remark;
		this.qq = qq;
		this.email = email;
		this.adr = adr;
		this.phone = phone;
		this.cellPhone = cellPhone;
		this.cardId = cardId;
	}

	public int getRecId() {
		return this.recId;
	}

	public void setRecId(int recId) {
		this.recId = recId;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSex() {
		return this.sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getHome() {
		return this.home;
	}

	public void setHome(String home) {
		this.home = home;
	}

	public BigDecimal getSalary() {
		return this.salary;
	}

	public void setSalary(BigDecimal salary) {
		this.salary = salary;
	}

	public BigDecimal getTc() {
		return this.tc;
	}

	public void setTc(BigDecimal tc) {
		this.tc = tc;
	}

	public String getFormula() {
		return this.formula;
	}

	public void setFormula(String formula) {
		this.formula = formula;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getQq() {
		return this.qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAdr() {
		return this.adr;
	}

	public void setAdr(String adr) {
		this.adr = adr;
	}

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getCellPhone() {
		return this.cellPhone;
	}

	public void setCellPhone(String cellPhone) {
		this.cellPhone = cellPhone;
	}

	public String getCardId() {
		return this.cardId;
	}

	public void setCardId(String cardId) {
		this.cardId = cardId;
	}

}
