package com.xyz.resources.model;

import javax.persistence.Entity;
import javax.persistence.Table;


/**
 * 地址区域信息
 * @author gaoweibin.tw
 *
 */
@Entity @Table(name="area")
public class Area extends BaseModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2218038376753251584L;
	
	private String areaId;
	private String areaType;
	private String areaName;
	private String parentId;
	private String zip;
	public String getAreaId() {
		return areaId;
	}
	public void setAreaId(String areaId) {
		this.areaId = areaId;
	}
	public String getAreaType() {
		return areaType;
	}
	public void setAreaType(String areaType) {
		this.areaType = areaType;
	}
	public String getAreaName() {
		return areaName;
	}
	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}
	public String getParentId() {
		return parentId;
	}
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	public String getZip() {
		return zip;
	}
	public void setZip(String zip) {
		this.zip = zip;
	}
}
