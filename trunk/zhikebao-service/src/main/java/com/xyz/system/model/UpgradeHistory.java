package com.xyz.system.model;

// Generated 2009-9-11 18:08:43 by Hibernate Tools 3.2.4.GA

import java.util.Date;

/**
 * UpgradeHistory generated by hbm2java
 */
public class UpgradeHistory implements java.io.Serializable {

	private String id;
	private String version;
	private String filename;
	private String md5sum;
	private String type;
	private String status;
	private String name;
	private String description;
	private String idName;
	private String manifest;
	private Date dateEntered;
	private Boolean enabled;

	public UpgradeHistory() {
	}

	public UpgradeHistory(String id, Date dateEntered) {
		this.id = id;
		this.dateEntered = dateEntered;
	}

	public UpgradeHistory(String id, String filename, String md5sum,
			String type, String status, String name, String description,
			String idName, String manifest, Date dateEntered, Boolean enabled) {
		this.id = id;
		this.filename = filename;
		this.md5sum = md5sum;
		this.type = type;
		this.status = status;
		this.name = name;
		this.description = description;
		this.idName = idName;
		this.manifest = manifest;
		this.dateEntered = dateEntered;
		this.enabled = enabled;
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getVersion() {
		return this.version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getFilename() {
		return this.filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public String getMd5sum() {
		return this.md5sum;
	}

	public void setMd5sum(String md5sum) {
		this.md5sum = md5sum;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getIdName() {
		return this.idName;
	}

	public void setIdName(String idName) {
		this.idName = idName;
	}

	public String getManifest() {
		return this.manifest;
	}

	public void setManifest(String manifest) {
		this.manifest = manifest;
	}

	public Date getDateEntered() {
		return this.dateEntered;
	}

	public void setDateEntered(Date dateEntered) {
		this.dateEntered = dateEntered;
	}

	public Boolean getEnabled() {
		return this.enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

}
