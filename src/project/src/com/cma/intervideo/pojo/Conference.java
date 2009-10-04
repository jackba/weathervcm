package com.cma.intervideo.pojo;

// Generated 2009-8-20 4:02:54 by Hibernate Tools 3.2.4.GA

import java.util.Date;

/**
 * Conference generated by hbm2java
 */
public class Conference implements java.io.Serializable {
	public static final short status_normal = 0;
	public static final short status_refuse = 1;
	public static final short status_delete = 2;

	private Integer conferenceId;
	private String roomId;
	private String userId;
	private String radConferenceId;
	private String dialableNumber;
	private String virtualConfId;
	private Long startTime;
	private Integer timeLong;
	private String serviceTemplate;
	private String memberId;
	private String description;
	private String password;
	private String controlPin;
	private Short status;
	private Integer portsNum;
	private String subject;
	private Date createTime;
	private Date cancelTime;
	private Date updateTime;
	private String cancelReason;
	private String initUnit;
	private Integer mainUnit;
	private String presider;
	private String principalMobile;
	private String reserveCode;
	private String contactMethod;
	private String principal;
	private String serviceTemplateName; //added additional
	private String statusStr; //added additional
	private String confUnitNames; //added additional
	public Conference() {
	}

	public Integer getConferenceId() {
		return this.conferenceId;
	}

	public void setConferenceId(Integer conferenceId) {
		this.conferenceId = conferenceId;
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

	public String getRadConferenceId() {
		return this.radConferenceId;
	}

	public void setRadConferenceId(String radConferenceId) {
		this.radConferenceId = radConferenceId;
	}

	public String getDialableNumber() {
		return this.dialableNumber;
	}

	public void setDialableNumber(String dialableNumber) {
		this.dialableNumber = dialableNumber;
	}

	public String getVirtualConfId() {
		return this.virtualConfId;
	}

	public void setVirtualConfId(String virtualConfId) {
		this.virtualConfId = virtualConfId;
	}

	public Long getStartTime() {
		return this.startTime;
	}

	public void setStartTime(Long startTime) {
		this.startTime = startTime;
	}

	

	public Integer getTimeLong() {
		return timeLong;
	}

	public void setTimeLong(Integer timeLong) {
		this.timeLong = timeLong;
	}

	public String getServiceTemplate() {
		return this.serviceTemplate;
	}

	public void setServiceTemplate(String serviceTemplate) {
		this.serviceTemplate = serviceTemplate;
	}

	public String getMemberId() {
		return this.memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
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
		return status;
	}

	public void setStatus(Short status) {
		this.status = status;
	}

	public Integer getPortsNum() {
		return this.portsNum;
	}

	public void setPortsNum(Integer portsNum) {
		this.portsNum = portsNum;
	}

	public String getSubject() {
		return this.subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getCancelTime() {
		return this.cancelTime;
	}

	public void setCancelTime(Date cancelTime) {
		this.cancelTime = cancelTime;
	}

	public Date getUpdateTime() {
		return this.updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getCancelReason() {
		return this.cancelReason;
	}

	public void setCancelReason(String cancelReason) {
		this.cancelReason = cancelReason;
	}

	public String getInitUnit() {
		return initUnit;
	}

	public void setInitUnit(String initUnit) {
		this.initUnit = initUnit;
	}

	public Integer getMainUnit() {
		return mainUnit;
	}

	public void setMainUnit(Integer mainUnit) {
		this.mainUnit = mainUnit;
	}

	public String getPresider() {
		return presider;
	}

	public void setPresider(String presider) {
		this.presider = presider;
	}

	public String getPrincipalMobile() {
		return principalMobile;
	}

	public void setPrincipalMobile(String principalMobile) {
		this.principalMobile = principalMobile;
	}

	public String getReserveCode() {
		return reserveCode;
	}

	public void setReserveCode(String reserveCode) {
		this.reserveCode = reserveCode;
	}

	public String getContactMethod() {
		return contactMethod;
	}

	public void setContactMethod(String contactMethod) {
		this.contactMethod = contactMethod;
	}

	public String getServiceTemplateName() {
		return serviceTemplateName;
	}

	public void setServiceTemplateName(String serviceTemplateName) {
		this.serviceTemplateName = serviceTemplateName;
	}

	
	public String getPrincipal() {
		return principal;
	}

	public void setPrincipal(String principal) {
		this.principal = principal;
	}

	public String getStatusStr() {
		if(status==null || status==status_normal){
			return "审批通过";
		}else if(status==status_refuse){
			return "申请失败";
		}else{
			return "删除";
		}
	}

	public String getConfUnitNames() {
		return confUnitNames;
	}

	public void setConfUnitNames(String confUnitNames) {
		this.confUnitNames = confUnitNames;
	}
}
