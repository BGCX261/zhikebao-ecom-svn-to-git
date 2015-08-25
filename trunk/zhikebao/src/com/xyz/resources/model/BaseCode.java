package com.xyz.resources.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * 基础代码表
 * @author val
 *
 */
@Entity @Table(name="base_codes") 
public class BaseCode extends BaseModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1203290358642721388L;

	/**
	 * 代码名称
	 */
	private String name;
	
	/**
	 * 代码编号
	 */
	private String code;
	
	/**
	 * 代码序号
	 */
	private int inx;
	
	/**
	 * 父编号
	 */
	@Column(name="parent_id")
	private Integer parentId;
	
	@ManyToOne(cascade=CascadeType.ALL)
	private BaseCode parent;
	
	@OneToMany(fetch=FetchType.EAGER,mappedBy="parent")
	@Column(insertable=false,updatable=false)
	private Set<BaseCode> children = new HashSet<BaseCode>(0);
	
	private boolean isParent;
	
	

	public boolean isParent() {
		return isParent;
	}

	public void setParent(boolean isParent) {
		this.isParent = isParent;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public int getInx() {
		return inx;
	}

	public void setInx(int inx) {
		this.inx = inx;
	}

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	public BaseCode getParent() {
		return parent;
	}

	public void setParent(BaseCode parent) {
		this.parent = parent;
	}

	public Set<BaseCode> getChildren() {
		return children;
	}

	public void setChildren(Set<BaseCode> children) {
		this.children = children;
	}


}
