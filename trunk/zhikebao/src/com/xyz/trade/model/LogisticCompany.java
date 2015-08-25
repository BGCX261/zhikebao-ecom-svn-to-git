package com.xyz.trade.model;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.xyz.resources.model.BaseModel;

/**
 * 物流公司信息
 * @author gaoweibin.tw
 *
 */
@Entity @Table(name="logisticCompany")
public class LogisticCompany extends BaseModel{

	private static final long serialVersionUID = 7832590988057945299L;
	
	private String companyId;
	private String companyCode;
	private String companyName;
	public String getCompanyId() {
		return companyId;
	}
	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}
	public String getCompanyCode() {
		return companyCode;
	}
	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
}
