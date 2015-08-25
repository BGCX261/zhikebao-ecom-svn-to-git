package com.xyz.trade.model;

// Generated 2009-10-31 14:39:21 by Hibernate Tools 3.2.4.GA

/**
 * 店铺目录
 */
public class Shop implements java.io.Serializable {

	private int recId;
	private String name;
	private String type;
	private Integer hot;
	private String website;
	private String linkman;
	private String adr;
	private String zip;
	private String tel;
	private String email;
	private String qq;
	private String remark;
	private Boolean bsndAdr;
	private String country;
	private String province;
	private String city;
	private String area;
	private String packageInclude;

	public Shop() {
	}

	public Shop(int recId) {
		this.recId = recId;
	}

	public Shop(int recId, String name, String type, Integer hot,
			String website, String linkman, String adr, String zip, String tel,
			String email, String qq, String remark, Boolean bsndAdr,
			String country, String province, String city, String area,
			String packageInclude) {
		this.recId = recId;
		this.name = name;
		this.type = type;
		this.hot = hot;
		this.website = website;
		this.linkman = linkman;
		this.adr = adr;
		this.zip = zip;
		this.tel = tel;
		this.email = email;
		this.qq = qq;
		this.remark = remark;
		this.bsndAdr = bsndAdr;
		this.country = country;
		this.province = province;
		this.city = city;
		this.area = area;
		this.packageInclude = packageInclude;
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

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Integer getHot() {
		return this.hot;
	}

	public void setHot(Integer hot) {
		this.hot = hot;
	}

	public String getWebsite() {
		return this.website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public String getLinkman() {
		return this.linkman;
	}

	public void setLinkman(String linkman) {
		this.linkman = linkman;
	}

	public String getAdr() {
		return this.adr;
	}

	public void setAdr(String adr) {
		this.adr = adr;
	}

	public String getZip() {
		return this.zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public String getTel() {
		return this.tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getQq() {
		return this.qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Boolean getBsndAdr() {
		return this.bsndAdr;
	}

	public void setBsndAdr(Boolean bsndAdr) {
		this.bsndAdr = bsndAdr;
	}

	public String getCountry() {
		return this.country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getProvince() {
		return this.province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getArea() {
		return this.area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getPackageInclude() {
		return this.packageInclude;
	}

	public void setPackageInclude(String packageInclude) {
		this.packageInclude = packageInclude;
	}

}
