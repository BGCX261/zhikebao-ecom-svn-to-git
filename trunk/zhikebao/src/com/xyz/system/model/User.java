package com.xyz.system.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Transient;

import net.sf.gilead.annotations.ReadOnly;
import net.sf.gilead.annotations.ServerOnly;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.xyz.resources.model.BaseModel;

// Generated 2009-10-31 14:39:21 by Hibernate Tools 3.2.4.GA

/**
 * 用户登录账户
 */
@Entity(name="user") @Table(name="users")
public class User extends BaseModel  implements UserDetails {
	    
	    private static final long serialVersionUID = 3922143702594309881L;

		private String username;
	   
		private String password;
	   
		private String authenticateId;
		
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
		 @ServerOnly @ReadOnly
		private Boolean isEnabled;
	   
		private String addressStreet;
	   
		private String addressCity;
	   
		private String addressState;
	   
		private String addressCountry;
	   
		private String addressPostalcode;
	   
		private String userPreferences;
			
		private Boolean portalOnly;
	   
		private String employeeStatus;
	   
		private String messengerId;
	   
		private String messengerType;
		
		private Boolean isGroup;
	
		private Boolean isCredentialsNonExpired;
		
		private Boolean isAccountNonExpired;
		
		private Boolean isAccountNonLocked;
		   
		public Boolean getIsAccountNonLocked() {
			return isAccountNonLocked;
		}

		public void setIsAccountNonLocked(Boolean isAccountNonLocked) {
			this.isAccountNonLocked = isAccountNonLocked;
		}
		
        @OneToOne
        @ServerOnly
		private Department dept;   //所在部门
		@Transient
	    private Shop shop; //解除与TaobaoUser之间强关联关系
		@Basic
		private Integer shopKey; //通过外键关联
		
        //private Set<String> authorityKeys; //当前用户权限的KEY列表
		//多对多定义
		@ManyToMany(targetEntity=com.xyz.system.model.Authority.class,cascade=CascadeType.ALL,fetch=FetchType.EAGER)
		//中间表定义,表名采用默认命名规则
		@JoinTable(name = "USER_AUTHS", joinColumns = { @JoinColumn(name = "USER_ID") }, inverseJoinColumns = { @JoinColumn(name = "AUTH_ID") })
		//Fecth策略定义
		@Fetch(FetchMode.SUBSELECT)
		//集合按id排序.
		@OrderBy("pid")
		private List<Authority> zkbAuthorities; //当前用户权限列表
		
		@Transient
		private String[] authes; //当前用户权限列表转换为字符串数组
		
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
			this.isAccountNonLocked = accountNonLocked;
			this.realName = realName;
			this.nickName = nickName;
			this.isAdmin = isAdmin;
			this.isAccountNonExpired = accountNonExpired;
			this.isCredentialsNonExpired = credentialsNonExpired;
		}
		
		public Boolean getIsCredentialsNonExpired() {
			return isCredentialsNonExpired;
		}

		public void setIsCredentialsNonExpired(Boolean isCredentialsNonExpired) {
			this.isCredentialsNonExpired = isCredentialsNonExpired;
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

		public Boolean getIsEnabled() {
			return isEnabled;
		}

		public void setIsEnabled(Boolean isEnabled) {
			this.isEnabled = isEnabled;
		}

		public String getAuthenticateId() {
			return this.authenticateId;
		}

		public void setAuthenticateId(String authenticateId) {
			this.authenticateId = authenticateId;
		}

		
		public Boolean getIsAdmin() {
			return this.isAdmin;
		}

		public void setIsAdmin(Boolean isAdmin) {
			this.isAdmin = isAdmin;
		}

		public String getReportsToId() {
			return this.reportsToId;
		}

		public void setReportsToId(String reportsToId) {
			this.reportsToId = reportsToId;
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
		
		
		public Boolean getIsAccountNonExpired() {
			return isAccountNonExpired;
		}

		public void setIsAccountNonExpired(Boolean isAccountNonExpired) {
			this.isAccountNonExpired = isAccountNonExpired;
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



		public Department getDept() {
			return dept;
		}

		public void setDept(Department dept) {
			this.dept = dept;
		}
	   @ServerOnly
       public List<GrantedAuthority> getAuthorities() {
			List<GrantedAuthority> lg = new ArrayList<GrantedAuthority>();
			if(zkbAuthorities!=null)
			{
				for(final Authority auth : zkbAuthorities)
				{
					GrantedAuthority ga = new GrantedAuthority(){
						private static final long serialVersionUID = -8091592236940698342L;
						public String getAuthority() {
							// TODO Auto-generated method stub
							return auth.getAuthority();
						}
	                     public int compareTo(GrantedAuthority o) {
	                    	return auth.getAuthority().compareTo(o.getAuthority());
						}
						
					};
					lg.add(ga);
			   }
			}
			return lg;
		}

     public List<Authority> getZkbAuthorities() {
		return zkbAuthorities;
	 }

	public void setZkbAuthorities(List<Authority> zkbAuthorities) {
		this.zkbAuthorities = zkbAuthorities;
	}

		public Shop getShop() {
			return shop;
		}

		public void setShop(Shop shop) {
			this.shop = shop;
		}

		public Integer getShopKey() {
			return shopKey;
		}

		public void setShopKey(Integer shopKey) {
			this.shopKey = shopKey;
		}

		@ServerOnly
		public boolean isAccountNonLocked() {
			// TODO Auto-generated method stub
			return this.isAccountNonLocked;
		}

		@ServerOnly
		public boolean isAccountNonExpired() {
			// TODO Auto-generated method stub
			return this.isAccountNonExpired;
		}

		@ServerOnly
		public boolean isCredentialsNonExpired() {
			// TODO Auto-generated method stub
			return this.isCredentialsNonExpired;
		}

		@Override
		public boolean isEnabled() {
			// TODO Auto-generated method stub
			return this.isEnabled;
		}

		public String[] getAuthes() {
			return authes;
		}

		public void setAuthes(String[] authes) {
			this.authes = authes;
		}


		
}
