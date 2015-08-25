package com.xyz.system.model;

// Generated 2009-9-14 18:36:13 by Hibernate Tools 3.2.4.GA
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.xyz.base.model.BaseModel;
import com.xyz.util.ReflectionUtil;

/**
 * 定义所有的系统资源(包括菜单和功能链接)
 */
@Entity @Table(name="resource")
public class Resource extends BaseModel implements Comparable<Resource> {
	//resourceType常量
	public static final String URL_TYPE = "'url'";
	public static final String MENU_TYPE = "'menu'";
	@Basic
	private String name;
	@Basic
	private Integer serial; //资源的序列号，
	@Basic
	private Integer parentId;
	@Basic
	private String resourceType; //资源类型
	@Basic
	private String value; //资源标识
	@Basic
	private Integer position; //资源在SpringSecurity中的校验顺序字段
	@Basic
	private String description;
	@Basic
	private String authorities ;

	public Resource() {
	}

	public Resource(String name,int serial, int parentId, String resourceType,
			String value, int position, String description,
			String authorities) {
		this.name = name;
		this.serial = serial;
		this.parentId = parentId;
		this.resourceType = resourceType;
		this.value = value;
		this.position = position;
		this.description = description;
		this.authorities = authorities;
	}



	public Resource(int position) {
		this.position = position;
	}
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getResourceType() {
		return resourceType;
	}

	public void setResourceType(String resourceType) {
		this.resourceType = resourceType;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}
	

	public String getAuthorities() {
		return authorities;
	}

	public void setAuthorities(String authorities) {
		this.authorities = authorities;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getSerial() {
		return serial;
	}

	public void setSerial(int serial) {
		this.serial = serial;
	}

	public int getParentId() {
		return parentId;
	}

	public void setParentId(int parentId) {
		this.parentId = parentId;
	}

	@Override
	public int compareTo(Resource o) {
	    Integer key1 = this.getPosition();
	    Integer key2 = new Integer(o.getPosition());
	    return key1.compareTo(key2);
	}	
	

}
