package com.xyz.market.model;


// Generated 2009-10-31 14:39:21 by Hibernate Tools 3.2.4.GA

/**
 * 竞争对手列表
 */
public class EnemyList implements java.io.Serializable {

	private int recId;
	private String shop;
	private String webSite;
	private String strength;
	private String range;
	private String menace;
	private String price;
	private String channel;
	private String cost;
	private String brand;
	private String service;
	private String police;
	private String remark;

	public EnemyList() {
	}

	public EnemyList(int recId) {
		this.recId = recId;
	}

	public EnemyList(int recId, String shop, String webSite,
			String strength, String range, String menace, String price,
			String channel, String cost, String brand, String service,
			String police, String remark) {
		this.recId = recId;
		this.shop = shop;
		this.webSite = webSite;
		this.strength = strength;
		this.range = range;
		this.menace = menace;
		this.price = price;
		this.channel = channel;
		this.cost = cost;
		this.brand = brand;
		this.service = service;
		this.police = police;
		this.remark = remark;
	}

	public int getRecId() {
		return this.recId;
	}

	public void setRecId(int recId) {
		this.recId = recId;
	}

	public String getShop() {
		return this.shop;
	}

	public void setShop(String shop) {
		this.shop = shop;
	}

	public String getWebSite() {
		return this.webSite;
	}

	public void setWebSite(String webSite) {
		this.webSite = webSite;
	}

	public String getStrength() {
		return this.strength;
	}

	public void setStrength(String strength) {
		this.strength = strength;
	}

	public String getRange() {
		return this.range;
	}

	public void setRange(String range) {
		this.range = range;
	}

	public String getMenace() {
		return this.menace;
	}

	public void setMenace(String menace) {
		this.menace = menace;
	}

	public String getPrice() {
		return this.price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getChannel() {
		return this.channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}

	public String getCost() {
		return this.cost;
	}

	public void setCost(String cost) {
		this.cost = cost;
	}

	public String getBrand() {
		return this.brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getService() {
		return this.service;
	}

	public void setService(String service) {
		this.service = service;
	}

	public String getPolice() {
		return this.police;
	}

	public void setPolice(String police) {
		this.police = police;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof EnemyList))
			return false;
		EnemyList castOther = (EnemyList) other;

		return (this.getRecId() == castOther.getRecId())
				&& ((this.getShop() == castOther.getShop()) || (this.getShop() != null
						&& castOther.getShop() != null && this.getShop()
						.equals(castOther.getShop())))
				&& ((this.getWebSite() == castOther.getWebSite()) || (this
						.getWebSite() != null
						&& castOther.getWebSite() != null && this.getWebSite()
						.equals(castOther.getWebSite())))
				&& ((this.getStrength() == castOther.getStrength()) || (this
						.getStrength() != null
						&& castOther.getStrength() != null && this
						.getStrength().equals(castOther.getStrength())))
				&& ((this.getRange() == castOther.getRange()) || (this
						.getRange() != null
						&& castOther.getRange() != null && this.getRange()
						.equals(castOther.getRange())))
				&& ((this.getMenace() == castOther.getMenace()) || (this
						.getMenace() != null
						&& castOther.getMenace() != null && this.getMenace()
						.equals(castOther.getMenace())))
				&& ((this.getPrice() == castOther.getPrice()) || (this
						.getPrice() != null
						&& castOther.getPrice() != null && this.getPrice()
						.equals(castOther.getPrice())))
				&& ((this.getChannel() == castOther.getChannel()) || (this
						.getChannel() != null
						&& castOther.getChannel() != null && this.getChannel()
						.equals(castOther.getChannel())))
				&& ((this.getCost() == castOther.getCost()) || (this.getCost() != null
						&& castOther.getCost() != null && this.getCost()
						.equals(castOther.getCost())))
				&& ((this.getBrand() == castOther.getBrand()) || (this
						.getBrand() != null
						&& castOther.getBrand() != null && this.getBrand()
						.equals(castOther.getBrand())))
				&& ((this.getService() == castOther.getService()) || (this
						.getService() != null
						&& castOther.getService() != null && this.getService()
						.equals(castOther.getService())))
				&& ((this.getPolice() == castOther.getPolice()) || (this
						.getPolice() != null
						&& castOther.getPolice() != null && this.getPolice()
						.equals(castOther.getPolice())))
				&& ((this.getRemark() == castOther.getRemark()) || (this
						.getRemark() != null
						&& castOther.getRemark() != null && this.getRemark()
						.equals(castOther.getRemark())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + this.getRecId();
		result = 37 * result
				+ (getShop() == null ? 0 : this.getShop().hashCode());
		result = 37 * result
				+ (getWebSite() == null ? 0 : this.getWebSite().hashCode());
		result = 37 * result
				+ (getStrength() == null ? 0 : this.getStrength().hashCode());
		result = 37 * result
				+ (getRange() == null ? 0 : this.getRange().hashCode());
		result = 37 * result
				+ (getMenace() == null ? 0 : this.getMenace().hashCode());
		result = 37 * result
				+ (getPrice() == null ? 0 : this.getPrice().hashCode());
		result = 37 * result
				+ (getChannel() == null ? 0 : this.getChannel().hashCode());
		result = 37 * result
				+ (getCost() == null ? 0 : this.getCost().hashCode());
		result = 37 * result
				+ (getBrand() == null ? 0 : this.getBrand().hashCode());
		result = 37 * result
				+ (getService() == null ? 0 : this.getService().hashCode());
		result = 37 * result
				+ (getPolice() == null ? 0 : this.getPolice().hashCode());
		result = 37 * result
				+ (getRemark() == null ? 0 : this.getRemark().hashCode());
		return result;
	}


}
