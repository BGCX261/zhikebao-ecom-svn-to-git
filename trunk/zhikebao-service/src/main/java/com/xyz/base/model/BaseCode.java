package com.xyz.base.model;

import java.util.HashSet;
import java.util.Set;

import javax.jdo.annotations.Persistent;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 基础代码表
 * @author val
 *
 */
@Entity @Table(name="baseCode") 
public class BaseCode extends BaseModel  implements java.io.Serializable {
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
	@Persistent
	private int inx;
	
	/**
	 * 父编号
	 */
	private Long parentId;
	
	private Set<Long> children = new HashSet<Long>(0);
	

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

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public Set<Long> getChildren() {
		return children;
	}

	public void setChildren(Set<Long> children) {
		this.children = children;
	}

	
}
