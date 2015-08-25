package com.xyz.system.model;

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

import com.googlecode.jsonplugin.annotations.JSON;
import com.xyz.base.model.BaseModel;

// Generated 2009-10-31 14:39:21 by Hibernate Tools 3.2.4.GA

/**
 * 用户登录账户
 */
@Entity @Table(name="User")
public class User extends BaseModel  implements UserDetails {
	    
		private String username;
	   
		private String password;
	   
		private String authenticateId;
	   
		private Boolean accountNonLocked;
	   
		private String realName;
	   
		private String nickName;
	   
		private String reportsToId; //上司
	   
		private Boolean isAdmin; //是否是淘宝商店掌柜
	   
		private Boolean receiveNotifications;
	   
		private String description;
	   
		private String title;
	   
		private String department;
	   
		private String phoneHome;
	   
		private String phoneMobile;
	   
		private String phoneWork;
	   
		private String phoneOther;
	   
		private String phoneFax;
		@Basic
		private Boolean enabled;
	   
		private String addressStreet;
	   
		private String addressCity;
	   
		private String addressState;
	   
		private String addressCountry;
	   
		private String addressPostalcode;
	   
		private String userPreferences;
		@Basic
		private Boolean accountNonExpired;
	   
		private Boolean portalOnly;
	   
		private String employeeStatus;
	   
		private String messengerId;
	   
		private String messengerType;
	   
		private Boolean isGroup;
		@Basic
		private Boolean credentialsNonExpired;

		private Department dept;   //所在部门
		
		private User mgr;
		@Transient
	    private Shop shop; //解除与TaobaoUser之间强关联关系
		@Basic
		private String shopKey; //通过外键关联
		
        @Basic(fetch=FetchType.EAGER)
		private Set<String> authorityKeys; //当前用户权限的KEY列表
	    
	    @Transient
		private List<GrantedAuthority> authorities; //当前用户权限的KEY列表
		
        public User() {
		}

		public User(String username, String password) {
			this.username = username;
			this.password = password;
		}
		
		public User(String username, String password,String authenticateId, 
				boolean accountNonLocked, String realName,String nickName, 
				Boolean isAdmin,boolean accountNonExpired,  boolean credentialsNonExpired) {
			this.username = username;
			this.password = password;
			this.authenticateId = authenticateId;
			this.accountNonLocked = accountNonLocked;
			this.realName = realName;
			this.nickName = nickName;
			this.isAdmin = isAdmin;
			this.accountNonExpired = accountNonExpired;
			this.credentialsNonExpired = credentialsNonExpired;
		}
		
		public User(String username, String password, boolean enabled,boolean accountNonLocked, String realName,
				String nickName,  Boolean isAdmin,Date dateEntered,
				Date dateModified, String modifiedUserId, String createdBy,
				boolean accountNonExpired,  boolean credentialsNonExpired) {
			this.username = username;
			this.password = password;
			this.accountNonLocked = accountNonLocked;
			this.enabled= enabled;
			this.realName = realName;
			this.nickName = nickName;
			this.isAdmin = isAdmin;
			this.dateEntered = dateEntered;
			this.dateModified = dateModified;
			this.modifiedUserId = modifiedUserId;
			this.createdBy = createdBy;
			this.accountNonExpired = accountNonExpired;
			this.credentialsNonExpired = credentialsNonExpired;
		}

		public User(String username, String password,
				String authenticateId, boolean accountNonLocked, String realName,
				String nickName, String reportsToId, Boolean isAdmin,
				Boolean receiveNotifications, String description, Date dateEntered,
				Date dateModified, String modifiedUserId, String createdBy,
				String title, String department, String phoneHome,
				String phoneMobile, String phoneWork, String phoneOther,
				String phoneFax, boolean enabled, String addressStreet,
				String addressCity, String addressState, String addressCountry,
				String addressPostalcode, String userPreferences,
				boolean accountNonExpired, Boolean portalOnly,
				String employeeStatus, String messengerId, String messengerType,
				Boolean isGroup, boolean credentialsNonExpired) {
			this.username = username;
			this.password = password;
			this.authenticateId = authenticateId;
			this.accountNonLocked = accountNonLocked;
			this.realName = realName;
			this.nickName = nickName;
			this.reportsToId = reportsToId;
			this.isAdmin = isAdmin;
			this.receiveNotifications = receiveNotifications;
			this.description = description;
			this.dateEntered = dateEntered;
			this.dateModified = dateModified;
			this.modifiedUserId = modifiedUserId;
			this.createdBy = createdBy;
			this.title = title;
			this.department = department;
			this.phoneHome = phoneHome;
			this.phoneMobile = phoneMobile;
			this.phoneWork = phoneWork;
			this.phoneOther = phoneOther;
			this.phoneFax = phoneFax;
			this.enabled = enabled;
			this.addressStreet = addressStreet;
			this.addressCity = addressCity;
			this.addressState = addressState;
			this.addressCountry = addressCountry;
			this.addressPostalcode = addressPostalcode;
			this.userPreferences = userPreferences;
			this.accountNonExpired = accountNonExpired;
			this.portalOnly = portalOnly;
			this.employeeStatus = employeeStatus;
			this.messengerId = messengerId;
			this.messengerType = messengerType;
			this.isGroup = isGroup;
			this.credentialsNonExpired = credentialsNonExpired;
		}

		public String getUsername() {
			return this.username;
		}

		public void setUsername(String username) {
			this.username = username;
		}

		public String getPassword() {
			return this.password;
		}

		public void setPassword(String password) {
			this.password = password;
		}

		public String getAuthenticateId() {
			return this.authenticateId;
		}

		public void setAuthenticateId(String authenticateId) {
			this.authenticateId = authenticateId;
		}

		public boolean isAccountNonLocked() {
			return this.accountNonLocked;
		}

		public void setAccountNonLocked(boolean accountNonLocked) {
			this.accountNonLocked = accountNonLocked;
		}


		public String getReportsToId() {
			return this.reportsToId;
		}

		public void setReportsToId(String reportsToId) {
			this.reportsToId = reportsToId;
		}

		public Boolean getIsAdmin() {
			return this.isAdmin;
		}

		public void setIsAdmin(Boolean isAdmin) {
			this.isAdmin = isAdmin;
		}

		public Boolean getReceiveNotifications() {
			return this.receiveNotifications;
		}

		public void setReceiveNotifications(Boolean receiveNotifications) {
			this.receiveNotifications = receiveNotifications;
		}

		public String getDescription() {
			return this.description;
		}

		public void setDescription(String description) {
			this.description = description;
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

		public String getTitle() {
			return this.title;
		}

		public void setTitle(String title) {
			this.title = title;
		}

		public String getDepartment() {
			return this.department;
		}

		public void setDepartment(String department) {
			this.department = department;
		}

		public String getPhoneHome() {
			return this.phoneHome;
		}

		public void setPhoneHome(String phoneHome) {
			this.phoneHome = phoneHome;
		}

		public String getPhoneMobile() {
			return this.phoneMobile;
		}

		public void setPhoneMobile(String phoneMobile) {
			this.phoneMobile = phoneMobile;
		}

		public String getPhoneWork() {
			return this.phoneWork;
		}

		public void setPhoneWork(String phoneWork) {
			this.phoneWork = phoneWork;
		}

		public String getPhoneOther() {
			return this.phoneOther;
		}

		public void setPhoneOther(String phoneOther) {
			this.phoneOther = phoneOther;
		}

		public String getPhoneFax() {
			return this.phoneFax;
		}

		public void setPhoneFax(String phoneFax) {
			this.phoneFax = phoneFax;
		}

		public boolean isEnabled() {
			return this.enabled;
		}

		public void setEnabled(boolean enabled) {
			this.enabled = enabled;
		}

		public String getAddressStreet() {
			return this.addressStreet;
		}

		public void setAddressStreet(String addressStreet) {
			this.addressStreet = addressStreet;
		}

		public String getAddressCity() {
			return this.addressCity;
		}

		public void setAddressCity(String addressCity) {
			this.addressCity = addressCity;
		}

		public String getAddressState() {
			return this.addressState;
		}

		public void setAddressState(String addressState) {
			this.addressState = addressState;
		}

		public String getAddressCountry() {
			return this.addressCountry;
		}

		public void setAddressCountry(String addressCountry) {
			this.addressCountry = addressCountry;
		}

		public String getAddressPostalcode() {
			return this.addressPostalcode;
		}

		public void setAddressPostalcode(String addressPostalcode) {
			this.addressPostalcode = addressPostalcode;
		}

		public String getUserPreferences() {
			return this.userPreferences;
		}

		public void setUserPreferences(String userPreferences) {
			this.userPreferences = userPreferences;
		}

		public boolean isAccountNonExpired() {
			return this.accountNonExpired;
		}

		public void setAccountNonExpired(boolean accountNonExpired) {
			this.accountNonExpired = accountNonExpired;
		}

		public Boolean getPortalOnly() {
			return this.portalOnly;
		}

		public void setPortalOnly(Boolean portalOnly) {
			this.portalOnly = portalOnly;
		}

		public String getEmployeeStatus() {
			return this.employeeStatus;
		}

		public void setEmployeeStatus(String employeeStatus) {
			this.employeeStatus = employeeStatus;
		}

		public String getRealName() {
			return realName;
		}

		public void setRealName(String realName) {
			this.realName = realName;
		}

		public String getNickName() {
			return nickName;
		}

		public void setNickName(String nickName) {
			this.nickName = nickName;
		}

		public String getMessengerId() {
			return this.messengerId;
		}

		public void setMessengerId(String messengerId) {
			this.messengerId = messengerId;
		}

		public String getMessengerType() {
			return this.messengerType;
		}

		public void setMessengerType(String messengerType) {
			this.messengerType = messengerType;
		}

		public Boolean getIsGroup() {
			return this.isGroup;
		}

		public void setIsGroup(Boolean isGroup) {
			this.isGroup = isGroup;
		}

		public boolean isCredentialsNonExpired() {
			return this.credentialsNonExpired;
		}

		public void setCredentialsNonExpired(boolean credentialsNonExpired) {
			this.credentialsNonExpired = credentialsNonExpired;
		}


		public Set<String> getAuthorityKeys() {
			return authorityKeys;
		}

		public void setAuthorityKeys(Set<String> authorityKeys) {
			this.authorityKeys = authorityKeys;
		}

		@JSON(serialize=false)
		public Department getDept() {
			return dept;
		}

		public void setDept(Department dept) {
			this.dept = dept;
		}
	    @JSON(serialize=false)
		public User getMgr() {
			return mgr;
		}

		public void setMgr(User mgr) {
			this.mgr = mgr;
		}

        @Transient
		public List<GrantedAuthority> getAuthorities() {
			return authorities;
		}

		public void setAuthorities(List<GrantedAuthority> authorities) {
			this.authorities = authorities;
		}

		public Shop getShop() {
			return shop;
		}

		public void setShop(Shop shop) {
			this.shop = shop;
		}

		public String getShopKey() {
			return shopKey;
		}

		public void setShopKey(String shopKey) {
			this.shopKey = shopKey;
		}

		
}
