package com.xyz.system.model;

// Generated 2009-9-18 22:21:12 by Hibernate Tools 3.2.4.GA

/**
 * AclResource generated by hbm2java
 */
public class AclResource implements java.io.Serializable {

	private String id;
	private double position;
	private String resourceType;
	private String value;

	public AclResource() {
	}

	public AclResource(String id, double position) {
		this.id = id;
		this.position = position;
	}

	public AclResource(String id, double position, String resourceType,
			String value) {
		this.id = id;
		this.position = position;
		this.resourceType = resourceType;
		this.value = value;
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public double getPosition() {
		return this.position;
	}

	public void setPosition(double position) {
		this.position = position;
	}

	public String getResourceType() {
		return this.resourceType;
	}

	public void setResourceType(String resourceType) {
		this.resourceType = resourceType;
	}

	public String getValue() {
		return this.value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
