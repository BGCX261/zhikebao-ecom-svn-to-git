package com.xyz.system.client.mvc;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.extjs.gxt.ui.client.data.BaseTreeModel;
import com.googlecode.jsonplugin.annotations.JSON;
import com.xyz.base.model.BaseModel;

// Generated 2009-10-31 14:39:21 by Hibernate Tools 3.2.4.GA

/**
 * 用户登录账户
 */
public class UserModel extends BaseTreeModel implements Serializable {
	
	public String getKey() {
		return (String)get("key");
	}

	public void setKey(String key) {
		set("key",key);
	}
	public Boolean isDeleted() {
		return (Boolean) get("deleted");
	}

	public void setDeleted(Boolean deleted) {
		set("deleted", deleted);
	}
       public UserModel() {
		}

		public UserModel(String username, String password) {
			setUsername(username);
			setPassword(password);
		}
		
		
		public String getUsername() {
			return (String)get("username");
		}

		public void setUsername(String username) {
			set("username",username);
		}

		public String getPassword() {
			return (String)get("password");
		}

		public void setPassword(String password) {
			set("password",password);
		}

		public Set<String> getAuthorityKeys() {
			Set<String> set = (Set<String>)get("authorityKeys");
			return set;
		}

		public void setAuthorityKeys(Set<String> authorityKeys) {
			
			set("authorityKeys",authorityKeys);
		}
		
		public Boolean getIsAdmin() {
			return (Boolean)get("isAdmin");
		}

		public void setIsAdmin(Boolean isAdmin) {
			set("isAdmin",isAdmin);
		}

	   public String getTitle() {
		   return (String)get("title");
		}

		public void setTitle(String title) {
			set("title",title);
		}

		public String getPhoneHome() {
			 return (String)get("phoneHome");
		}

		public void setPhoneHome(String phoneHome) {
			set("phoneHome",phoneHome);
		}

		public String getPhoneMobile() {
			return (String)get("phoneMobile");
		}

		public void setPhoneMobile(String phoneMobile) {
			set("phoneMobile",phoneMobile);
		}

		public String getPhoneWork() {
			return (String)get("phoneWork");
		}

		public void setPhoneWork(String phoneWork) {
			set("phoneWork",phoneWork);
		}

		public String getPhoneOther() {
			return (String)get("phoneOther");
		}

		public void setPhoneOther(String phoneOther) {
			set("phoneOther",phoneOther);
		}

		public String getPhoneFax() {
			return (String)get("phoneFax");
		}

		public void setPhoneFax(String phoneFax) {
			set("phoneFax",phoneFax);
		}

		public String getAddressStreet() {
			return (String)get("addressStreet");
		}

		public void setAddressStreet(String addressStreet) {
			set("addressStreet",addressStreet);
		}

		public String getAddressCity() {
			return (String)get("addressCity");
		}

		public void setAddressCity(String addressCity) {
			set("addressCity",addressCity);
		}

		public String getAddressState() {
			return (String)get("addressState");
		}

		public void setAddressState(String addressState) {
			set("addressState",addressState);
		}

		public String getAddressCountry() {
			return (String)get("addressCountry");
		}

		public void setAddressCountry(String addressCountry) {
			set("addressCountry",addressCountry);
		}

		public String getAddressPostalcode() {
			return (String)get("addressPostalcode");
		}

		public void setAddressPostalcode(String addressPostalcode) {
			set("addressPostalcode",addressPostalcode);
		}

		public String getRealName() {
			return (String)get("realName");
		}

		public void setRealName(String realName) {
			set("realName",realName);
		}

		public String getNickName() {
			return (String)get("nickName");
		}

		public void setNickName(String nickName) {
			set("nickName",nickName);
		}

		public String getMessengerId() {
			return (String)get("messengerId");
		}

		public void setMessengerId(String messengerId) {
			set("messengerId",messengerId);
		}

		public String getMessengerType() {
			return (String)get("messengerType");
		}

		public void setMessengerType(String messengerType) {
			set("messengerType",messengerType);
		}
		
}
