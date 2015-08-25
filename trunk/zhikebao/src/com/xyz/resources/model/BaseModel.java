package com.xyz.resources.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import net.sf.gilead.pojo.java5.LightEntity;

import com.extjs.gxt.ui.client.data.BeanModelTag;
import com.google.gwt.user.client.rpc.IsSerializable;
import com.xyz.system.model.Resource;
/**
 * 实体模型的基类
 * @author val
 */
@MappedSuperclass
public class BaseModel extends LightEntity implements IsSerializable , BeanModelTag, Serializable  {
	
	private static final long serialVersionUID = -7310465662136003180L;

	@Id  @GeneratedValue(strategy = GenerationType.IDENTITY) 
	protected Integer pid;

	@Temporal(TemporalType.TIMESTAMP)
	protected Date dateEntered;
	@Temporal(TemporalType.TIMESTAMP)
	protected Date dateModified;
	@Basic
	protected Integer modifiedUserId;
	@Basic
	protected Integer createdBy;
	@Basic
	protected Integer ownerId;
	@Basic
	protected Boolean deleted;
	@Transient
	protected Boolean isEditable=false;
	
	public BaseModel(){}
	
	public BaseModel(Date dateEntered, Date dateModified,
			Integer modifiedUserId, Integer createdBy, Boolean deleted) {
		super();
		this.dateEntered = dateEntered;
		this.dateModified = dateModified;
		this.modifiedUserId = modifiedUserId;
		this.createdBy = createdBy;
		this.deleted = deleted;
	}


	public Integer getPid() {
		return pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
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

	public Boolean getDeleted() {
		return deleted;
	}
	public void setDeleted(Boolean deleted) {
		this.deleted = deleted;
	}

	public Integer getModifiedUserId() {
		return modifiedUserId;
	}

	public void setModifiedUserId(Integer modifiedUserId) {
		this.modifiedUserId = modifiedUserId;
	}

	public Integer getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Integer createdBy) {
		this.createdBy = createdBy;
	}

	public Integer getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(Integer ownerId) {
		this.ownerId = ownerId;
	}

	public Boolean getIsEditable() {
		return isEditable;
	}

	public void setIsEditable(Boolean isEditable) {
		this.isEditable = isEditable;
	}

	/*public boolean equals(Object obj) {
		BaseModel r = (BaseModel)obj;
		if(r.getPid()==this.getPid())
		   return true;
		else
		    return false;
	}	*/


}
