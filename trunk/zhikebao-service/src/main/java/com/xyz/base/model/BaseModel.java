package com.xyz.base.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.datanucleus.jpa.annotations.Extension;
/**
 * 实体模型的基类
 * @author val
 */
@MappedSuperclass  
public class BaseModel implements Serializable {
	
	private static final long serialVersionUID = -7310465662136003180L;
	@Id  @GeneratedValue(strategy = GenerationType.IDENTITY) 
	@Extension(vendorName="datanucleus", key="gae.encoded-pk", value="true")
	protected String key;
    @Extension(vendorName="datanucleus", key="gae.pk-id", value="true")
    protected Long keyId;
    @Temporal(TemporalType.DATE)
	protected Date dateEntered;
	@Temporal(TemporalType.DATE)
	protected Date dateModified;
	@Basic
	protected String modifiedUserId;
	@Basic
	protected String ownerId;
	@Basic
	protected String createdBy;
	@Basic
	protected Boolean deleted;
	
	public BaseModel(){}
	
	public BaseModel(Date dateEntered, Date dateModified,
			String modifiedUserId, String createdBy, Boolean deleted) {
		super();
		this.dateEntered = dateEntered;
		this.dateModified = dateModified;
		this.modifiedUserId = modifiedUserId;
		this.createdBy = createdBy;
		this.deleted = deleted;
	}


	public String getKey() {
		return key;
	}

	public Long getKeyId() {
		return keyId;
	}

	public void setKeyId(Long keyId) {
		this.keyId = keyId;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public Date getDateEntered() {
		return dateEntered;
	}
	public void setDateEntered(Date dateEntered) {
		this.dateEntered = dateEntered;
	}
	public Date getDateModified() {
		return dateModified;
	}
	public void setDateModified(Date dateModified) {
		this.dateModified = dateModified;
	}
	public String getModifiedUserId() {
		return modifiedUserId;
	}
	public void setModifiedUserId(String modifiedUserId) {
		this.modifiedUserId = modifiedUserId;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public Boolean getDeleted() {
		return deleted;
	}
	public void setDeleted(Boolean deleted) {
		this.deleted = deleted;
	}

	public String getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(String ownerId) {
		this.ownerId = ownerId;
	}
}
