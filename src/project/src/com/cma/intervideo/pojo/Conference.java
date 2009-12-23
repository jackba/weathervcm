package com.cma.intervideo.pojo;

// Generated 2009-8-20 4:02:54 by Hibernate Tools 3.2.4.GA

import java.util.Date;

/**
 * Conference generated by hbm2java
 */
public class Conference implements java.io.Serializable {
	public static final short status_tobescheduled = -1;
	public static final short status_upcoming = 0;
	public static final short status_ongoing = 1;
	public static final short status_history = 2;
	public static final short status_cancel = 3;
	
	private Integer conferenceId;
	private String userId;
	private String serviceTemplateId;
	private Short confType;
	private String confTypeDesc;
	private Integer confTemplateId;
	private String radConferenceId;
	private String dialableNumber;
	private String virtualConfId;
	private Long startTime;
	private Integer timeLong;
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
	private String serviceTemplateDesc; //added additional
	//	private String statusStr; //added additional
	private String confUnitNames; //added additional
	private String mainUnitName; //added additional
	private Short isBroadcast;
	private Short isSupport;
	private Short isRecord;
	public String toString() {
		StringBuilder buf = new StringBuilder();
		
		String line = System.getProperty("line.separator");
		buf.append(line + "[Conference:( @" + hashCode() + ")" + line);
		buf.append(" conferenceId = " + conferenceId + ", radConferenceId = " + radConferenceId + line);
		buf.append(" virtualConfId = " + virtualConfId + ", dialableNumber = " + dialableNumber + line);
		buf.append(" description = " + description + ", confTemplateId = " + confTemplateId + line);
		buf.append(" startTime = " + startTime + ", timeLong = " + timeLong + line);
		buf.append(" serviceTemplateId = " + serviceTemplateId + ", serviceTemplateName = " + serviceTemplateName + ", serviceTemplateDesc = " + serviceTemplateDesc + line);
		buf.append(" memberId = " + memberId + ", userId = " + userId + line);
		buf.append(" status = " + status + ", subject = " + subject + line);
		buf.append(" createTime = " + createTime + ", updateTime = " + updateTime + ", cancelTime = " + cancelTime + line);
		buf.append(" initUnit = " + initUnit + ", mainUnit = " + mainUnit + line);
		buf.append(" presider = " + presider + ", principalMobile = " + principalMobile + line);
		buf.append(" contactMethod = " + contactMethod + ", principal = " + principal + line);
		buf.append("]." + line);
		return buf.toString();
	}
	
	public Conference() {
	}

	public Integer getConferenceId() {
		return this.conferenceId;
	}

	public void setConferenceId(Integer conferenceId) {
		this.conferenceId = conferenceId;
	}

	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getServiceTemplateId() {
		return this.serviceTemplateId;
	}

	public void setServiceTemplateId(String serviceTemplateId) {
		this.serviceTemplateId = serviceTemplateId;
	}

	public Integer getConfTemplateId() {
		return this.confTemplateId;
	}

	public void setConfTemplateId(Integer confTemplateId) {
		this.confTemplateId = confTemplateId;
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
		return this.timeLong;
	}

	public void setTimeLong(Integer timeLong) {
		this.timeLong = timeLong;
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
		if (status == null || status == status_upcoming){
			return "待召开";
		}else if(status==status_ongoing){
			return "召开中";
		}else if (status == status_history){
			return "已结束";
		}else if (status == status_cancel){
			return "已取消";
		} else
			return "待预定";
	}

	public String getConfUnitNames() {
		return confUnitNames;
	}

	public void setConfUnitNames(String confUnitNames) {
		this.confUnitNames = confUnitNames;
	}
	
	public String getServiceTemplateDesc() {
		return serviceTemplateDesc;
	}

	public void setServiceTemplateDesc(String serviceTemplateDesc) {
		this.serviceTemplateDesc = serviceTemplateDesc;
	}

	public String getMainUnitName() {
		return mainUnitName;
	}

	public void setMainUnitName(String mainUnitName) {
		this.mainUnitName = mainUnitName;
	}

	public Short getConfType() {
		return confType;
	}

	public void setConfType(Short confType) {
		this.confType = confType;
	}

	public String getConfTypeDesc() {
		return confTypeDesc;
	}

	public void setConfTypeDesc(String confTypeDesc) {
		this.confTypeDesc = confTypeDesc;
	}

	public Short getIsBroadcast() {
		return isBroadcast;
	}

	public void setIsBroadcast(Short isBroadcast) {
		this.isBroadcast = isBroadcast;
	}

	public Short getIsSupport() {
		return isSupport;
	}

	public void setIsSupport(Short isSupport) {
		this.isSupport = isSupport;
	}

	public Short getIsRecord() {
		return isRecord;
	}

	public void setIsRecord(Short isRecord) {
		this.isRecord = isRecord;
	}
	
}
