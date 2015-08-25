package com.xyz.order.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * AddressDatabase entity. @author sea
 */
@Entity
@Table(name = "AddressDatabase")
public class AddressDatabase implements java.io.Serializable {

	private static final long serialVersionUID = 6238755805698261272L;
	private Long id;
	private String areaid;
	private String state;
	private String city;
	private String district;
	private String address;
	private String zip;
	private String mobile;
	private String phone;
	private String name;
	private Boolean isDefault;

	// Constructors

	/** default constructor */
	public AddressDatabase() {
	}

	/** minimal constructor */
	public AddressDatabase(Long id) {
		this.id = id;
	}

	/** full constructor */
	public AddressDatabase(Long id, String areaid, String state, String city, String district, String address, String zip, String mobile, String phone, String name, Boolean isDefault) {
		this.id = id;
		this.areaid = areaid;
		this.state = state;
		this.city = city;
		this.district = district;
		this.address = address;
		this.zip = zip;
		this.mobile = mobile;
		this.phone = phone;
		this.name = name;
		this.isDefault = isDefault;
	}

	// Property accessors
	@Id
	@Column(name = "id", nullable = false)
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "areaid", length = 50)
	public String getAreaid() {
		return this.areaid;
	}

	public void setAreaid(String areaid) {
		this.areaid = areaid;
	}

	@Column(name = "state", length = 50)
	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

	@Column(name = "city", length = 50)
	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	@Column(name = "district", length = 50)
	public String getDistrict() {
		return this.district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	@Column(name = "address", length = 100)
	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Column(name = "zip", length = 50)
	public String getZip() {
		return this.zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	@Column(name = "mobile", length = 50)
	public String getMobile() {
		return this.mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	@Column(name = "phone", length = 50)
	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Column(name = "name", length = 50)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "isDefault")
	public Boolean getIsDefault() {
		return this.isDefault;
	}

	public void setIsDefault(Boolean isDefault) {
		this.isDefault = isDefault;
	}

}