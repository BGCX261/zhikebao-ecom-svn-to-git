package com.xyz.system.model;
/**
 * @author IBM
 * @version 创建时间：2009-9-20 下午02:10:06
 * 类说明 ：
 */
public class Menu {
	
	private String parentName; 
	 
	private String name;
	 
	private String title; 
	 
	private String description; 
	 
	private String location;
	 
	private String authorities;

	public String getParentName() {
		return parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getAuthorities() {
		return authorities;
	}

	public void setAuthorities(String authorities) {
		this.authorities = authorities;
	}
}
