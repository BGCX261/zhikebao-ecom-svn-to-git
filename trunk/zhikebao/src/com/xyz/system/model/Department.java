/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.xyz.system.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import com.xyz.resources.model.BaseModel;

/**
 *
 * @author val
 */
@Entity @Table(name="department")
public class Department extends BaseModel {
	 @Column(name="parent_id")
     private String parentId;
     private String code;
     private String name;
     private String phone;
     private String remark;
     
     @ManyToOne
     private Department parent;
     
     @OneToMany(mappedBy="parent")
     @Column(insertable=false,updatable=false)
     private List<Department> childDepts;

    public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }


	public List<Department> getChildDepts() {
		return childDepts;
	}

	public void setChildDepts(List<Department> childDepts) {
		this.childDepts = childDepts;
	}

	public Department getParent() {
		return parent;
	}

	public void setParent(Department parent) {
		this.parent = parent;
	}
	

}
