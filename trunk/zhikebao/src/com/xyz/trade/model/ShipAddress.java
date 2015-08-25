package com.xyz.trade.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.xyz.resources.model.BaseModel;
import com.xyz.resources.model.Location;

@Entity @Table(name="ship_address")
public class ShipAddress extends BaseModel{

	private String sellerName;
	
	private String sellerAreaId;
	
	private String sellerPhone;
	
	private String sellerMobile;
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="location_id")
	private Location location; 
	//多对多定义
	@ManyToMany(targetEntity=com.xyz.trade.model.LogisticCompany.class,cascade=CascadeType.ALL,fetch=FetchType.EAGER)
	//中间表定义,表名采用默认命名规则
	@JoinTable(name = "SHIP_ADDR_LOGISTS", joinColumns = { @JoinColumn(name = "SHIP_ADDR_ID") }, inverseJoinColumns = { @JoinColumn(name = "LOGIST_COM_ID") })
	//Fecth策略定义
	@Fetch(FetchMode.SUBSELECT)
	//集合按id排序.
	@OrderBy("pid")
	private List<LogisticCompany> ll;//常用物流公司
	
	private Integer shopId;

	public String getSellerName() {
		return sellerName;
	}

	public void setSellerName(String sellerName) {
		this.sellerName = sellerName;
	}

	public String getSellerAreaId() {
		return sellerAreaId;
	}

	public void setSellerAreaId(String sellerAreaId) {
		this.sellerAreaId = sellerAreaId;
	}

	public String getSellerPhone() {
		return sellerPhone;
	}

	public void setSellerPhone(String sellerPhone) {
		this.sellerPhone = sellerPhone;
	}

	public String getSellerMobile() {
		return sellerMobile;
	}

	public void setSellerMobile(String sellerMobile) {
		this.sellerMobile = sellerMobile;
	}

	public Integer getShopId() {
		return shopId;
	}

	public void setShopId(Integer shopId) {
		this.shopId = shopId;
	}

	public List<LogisticCompany> getLl() {
		return ll;
	}

	public void setLl(List<LogisticCompany> ll) {
		this.ll = ll;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

}
