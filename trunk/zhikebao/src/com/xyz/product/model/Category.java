package com.xyz.product.model;

import com.xyz.resources.model.BaseModel;

/**
 * 商品类别
 * 
 */
public abstract class Category extends BaseModel {
	private static final long serialVersionUID = -765481977602589175L;
	private String cid;
	private String parentCid;
	private String name;
	private Boolean isParent;

	public String getCid() {
		return cid;
	}

	public void setCid(String cid) {
		this.cid = cid;
	}

	public String getParentCid() {
		return parentCid;
	}

	public void setParentCid(String parentCid) {
		this.parentCid = parentCid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Boolean getIsParent() {
		return isParent;
	}

	public void setIsParent(Boolean isParent) {
		this.isParent = isParent;
	}

}
