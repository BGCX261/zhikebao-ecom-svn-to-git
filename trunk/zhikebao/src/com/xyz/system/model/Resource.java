package com.xyz.system.model;

// Generated 2009-9-14 18:36:13 by Hibernate Tools 3.2.4.GA
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import net.sf.gilead.annotations.ServerOnly;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.xyz.resources.model.BaseModel;

/**
 * 定义所有的系统资源(包括菜单和功能链接)
 */
@Entity @Table(name="resources")
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
	//多对多定义
	@ManyToMany(targetEntity=com.xyz.system.model.Authority.class,cascade=CascadeType.ALL,fetch=FetchType.EAGER)
	//中间表定义,表名采用默认命名规则
	@JoinTable(name = "AUTH_RESOURCES", joinColumns = { @JoinColumn(name = "RES_ID") }, inverseJoinColumns = { @JoinColumn(name = "AUTH_ID") })
	//Fecth策略定义
	@Fetch(FetchMode.SUBSELECT)
	//集合按id排序.
	@OrderBy("pid")
	@ServerOnly
	private List<Authority> authorities ;

	public Resource() {
	}

	public Resource(String name,int serial, int parentId, String resourceType,
			String value, int position, String description) {
		this.name = name;
		this.serial = serial;
		this.parentId = parentId;
		this.resourceType = resourceType;
		this.value = value;
		this.position = position;
		this.description = description;
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
	
	public List<Authority> getAuthorities() {
		return authorities;
	}

	public void setAuthorities(List<Authority> authorities) {
		this.authorities = authorities;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	public void setPosition(Integer position) {
		this.position = position;
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

	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Resource)
		{
			Resource r = (Resource)obj;
			if(r.getPid()==this.getPid())
			   return true;
			else
			    return false;
		}
		return false;
	}	
}
