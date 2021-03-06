package com.xyz.system.model;

// Generated 2009-9-11 18:08:43 by Hibernate Tools 3.2.4.GA

import java.util.Date;

/**
 * Tasks generated by hbm2java
 */
public class Tasks implements java.io.Serializable {

	private String id;
	private String name;
	private Date dateEntered;
	private Date dateModified;
	private String modifiedUserId;
	private String createdBy;
	private String description;
	private Boolean deleted;
	private String assignedUserId;
	private String status;
	private Boolean dateDueFlag;
	private Date dateDue;
	private Boolean dateStartFlag;
	private Date dateStart;
	private String parentType;
	private String parentId;
	private String contactId;
	private String priority;

	public Tasks() {
	}

	public Tasks(String id) {
		this.id = id;
	}

	public Tasks(String id, String name, Date dateEntered, Date dateModified,
			String modifiedUserId, String createdBy, String description,
			Boolean deleted, String assignedUserId, String status,
			Boolean dateDueFlag, Date dateDue, Boolean dateStartFlag,
			Date dateStart, String parentType, String parentId,
			String contactId, String priority) {
		this.id = id;
		this.name = name;
		this.dateEntered = dateEntered;
		this.dateModified = dateModified;
		this.modifiedUserId = modifiedUserId;
		this.createdBy = createdBy;
		this.description = description;
		this.deleted = deleted;
		this.assignedUserId = assignedUserId;
		this.status = status;
		this.dateDueFlag = dateDueFlag;
		this.dateDue = dateDue;
		this.dateStartFlag = dateStartFlag;
		this.dateStart = dateStart;
		this.parentType = parentType;
		this.parentId = parentId;
		this.contactId = contactId;
		this.priority = priority;
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getDateEntered() {
		return this.dateEntered;
	}

	public void setDateEntered(Date dateEntered) {
		this.dateEntered = dateEntered;
	}

	public Date getDateModified() {
		return this.dateModified;
	}

	public void setDateModified(Date dateModified) {
		this.dateModified = dateModified;
	}

	public String getModifiedUserId() {
		return this.modifiedUserId;
	}

	public void setModifiedUserId(String modifiedUserId) {
		this.modifiedUserId = modifiedUserId;
	}

	public String getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Boolean getDeleted() {
		return this.deleted;
	}

	public void setDeleted(Boolean deleted) {
		this.deleted = deleted;
	}

	public String getAssignedUserId() {
		return this.assignedUserId;
	}

	public void setAssignedUserId(String assignedUserId) {
		this.assignedUserId = assignedUserId;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Boolean getDateDueFlag() {
		return this.dateDueFlag;
	}

	public void setDateDueFlag(Boolean dateDueFlag) {
		this.dateDueFlag = dateDueFlag;
	}

	public Date getDateDue() {
		return this.dateDue;
	}

	public void setDateDue(Date dateDue) {
		this.dateDue = dateDue;
	}

	public Boolean getDateStartFlag() {
		return this.dateStartFlag;
	}

	public void setDateStartFlag(Boolean dateStartFlag) {
		this.dateStartFlag = dateStartFlag;
	}

	public Date getDateStart() {
		return this.dateStart;
	}

	public void setDateStart(Date dateStart) {
		this.dateStart = dateStart;
	}

	public String getParentType() {
		return this.parentType;
	}

	public void setParentType(String parentType) {
		this.parentType = parentType;
	}

	public String getParentId() {
		return this.parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getContactId() {
		return this.contactId;
	}

	public void setContactId(String contactId) {
		this.contactId = contactId;
	}

	public String getPriority() {
		return this.priority;
	}

	public void setPriority(String priority) {
		this.priority = priority;
	}

}
