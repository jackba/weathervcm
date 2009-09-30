package com.cma.intervideo.pojo;

// Generated 2009-8-20 4:02:54 by Hibernate Tools 3.2.4.GA

import java.util.Date;

/**
 * User generated by hbm2java
 */
public class User implements java.io.Serializable {

	private String userId;
	private String loginId;
	private Short userType;
	private String userName;
	private Short company;
	private String email;
	private String homeTelephone;
	private String officeTelephone;
	private String mobile;
	private Short sex;
	private String address;
	private String description;
	private Short status;
	private Date createTime;
	private Date updateTime;
	private String password;

	public User() {
	}

	public User(String userId, String loginId, String userName, Short sex,
			Short status, Date createTime, Date updateTime) {
		this.userId = userId;
		this.loginId = loginId;
		this.userName = userName;
		this.sex = sex;
		this.status = status;
		this.createTime = createTime;
		this.updateTime = updateTime;
	}

	public User(String userId, String loginId, Short userType, String userName,
			Short company, String email, String homeTelephone,
			String officeTelephone, String mobile, Short sex, String address,
			String description, Short status, Date createTime, Date updateTime,
			String password) {
		this.userId = userId;
		this.loginId = loginId;
		this.userType = userType;
		this.userName = userName;
		this.company = company;
		this.email = email;
		this.homeTelephone = homeTelephone;
		this.officeTelephone = officeTelephone;
		this.mobile = mobile;
		this.sex = sex;
		this.address = address;
		this.description = description;
		this.status = status;
		this.createTime = createTime;
		this.updateTime = updateTime;
		this.password = password;
	}

	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getLoginId() {
		return this.loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	public Short getUserType() {
		return this.userType;
	}

	public void setUserType(Short userType) {
		this.userType = userType;
	}

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Short getCompany() {
		return this.company;
	}

	public void setCompany(Short company) {
		this.company = company;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getHomeTelephone() {
		return this.homeTelephone;
	}

	public void setHomeTelephone(String homeTelephone) {
		this.homeTelephone = homeTelephone;
	}

	public String getOfficeTelephone() {
		return this.officeTelephone;
	}

	public void setOfficeTelephone(String officeTelephone) {
		this.officeTelephone = officeTelephone;
	}

	public String getMobile() {
		return this.mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public Short getSex() {
		return this.sex;
	}

	public void setSex(Short sex) {
		this.sex = sex;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Short getStatus() {
		return this.status;
	}

	public void setStatus(Short status) {
		this.status = status;
	}

	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return this.updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
