package com.cma.intervideo.pojo;

// Generated Oct 2, 2009 3:49:30 PM by Hibernate Tools 3.2.4.GA

import java.util.Date;

/**
 * VirtualRoom generated by hbm2java
 */
public class VirtualRoom implements java.io.Serializable {

	private String roomId;
	private String userId;
	private Long startTime;
	private Integer duration;
	private String subject;
	private String serviceTemplate;
	private String memberId;
	private String templateName;
	private String vitualConfId;
	private String description;
	private String password;
	private String controlPin;
	private Short status;
	private Date createTime;
	private Date updateTime;
	private String serviceTemplateName; // additional property
	private String serviceTemplateDesc; //additional property

	public String getServiceTemplateName() {
		return serviceTemplateName;
	}

	public void setServiceTemplateName(String serviceTemplateName) {
		this.serviceTemplateName = serviceTemplateName;
	}

	public VirtualRoom() {
	}

	public VirtualRoom(String roomId, String userId, String serviceTemplate,
			String memberId, String templateName, String vitualConfId,
			Short status, Date createTime, Date updateTime) {
		this.roomId = roomId;
		this.userId = userId;
		this.serviceTemplate = serviceTemplate;
		this.memberId = memberId;
		this.templateName = templateName;
		this.vitualConfId = vitualConfId;
		this.status = status;
		this.createTime = createTime;
		this.updateTime = updateTime;
	}

	public VirtualRoom(String roomId, String userId, Long startTime,
			Integer duration, String subject, String serviceTemplate,
			String memberId, String templateName, String vitualConfId,
			String description, String password, String controlPin,
			Short status, Date createTime, Date updateTime) {
		this.roomId = roomId;
		this.userId = userId;
		this.startTime = startTime;
		this.duration = duration;
		this.subject = subject;
		this.serviceTemplate = serviceTemplate;
		this.memberId = memberId;
		this.templateName = templateName;
		this.vitualConfId = vitualConfId;
		this.description = description;
		this.password = password;
		this.controlPin = controlPin;
		this.status = status;
		this.createTime = createTime;
		this.updateTime = updateTime;
	}

	public String getRoomId() {
		return this.roomId;
	}

	public void setRoomId(String roomId) {
		this.roomId = roomId;
	}

	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Long getStartTime() {
		return this.startTime;
	}

	public void setStartTime(Long startTime) {
		this.startTime = startTime;
	}

	public Integer getDuration() {
		return this.duration;
	}

	public void setDuration(Integer duration) {
		this.duration = duration;
	}

	public String getSubject() {
		return this.subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getServiceTemplate() {
		return this.serviceTemplate;
	}

	public void setServiceTemplate(String serviceTemplate) {
		this.serviceTemplate = serviceTemplate;
	}

	public String getServiceTemplateDesc() {
		return serviceTemplateDesc;
	}

	public void setServiceTemplateDesc(String serviceTemplateDesc) {
		this.serviceTemplateDesc = serviceTemplateDesc;
	}

	public String getMemberId() {
		return this.memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public String getTemplateName() {
		return this.templateName;
	}

	public void setTemplateName(String templateName) {
		this.templateName = templateName;
	}

	public String getVitualConfId() {
		return this.vitualConfId;
	}

	public void setVitualConfId(String vitualConfId) {
		this.vitualConfId = vitualConfId;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getControlPin() {
		return this.controlPin;
	}

	public void setControlPin(String controlPin) {
		this.controlPin = controlPin;
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

}