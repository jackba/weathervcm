package com.cma.intervideo.pojo;

import java.util.Date;

public class SendMessage implements java.io.Serializable{
	public static short status_success = (short)0;
	public static short status_failure = (short)1;
	private Integer msgId;
	private String message;
	private String msisdn;
	private Date sendTime;
	private Integer comPort;
	private Short status;
	private String errorDesc;
	public Integer getMsgId() {
		return msgId;
	}
	public void setMsgId(Integer msgId) {
		this.msgId = msgId;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getMsisdn() {
		return msisdn;
	}
	public void setMsisdn(String msisdn) {
		this.msisdn = msisdn;
	}
	public Date getSendTime() {
		return sendTime;
	}
	public void setSendTime(Date sendTime) {
		this.sendTime = sendTime;
	}
	public Integer getComPort() {
		return comPort;
	}
	public void setComPort(Integer comPort) {
		this.comPort = comPort;
	}
	
	public Short getStatus() {
		return status;
	}
	public void setStatus(Short status) {
		this.status = status;
	}
	public String getErrorDesc() {
		return errorDesc;
	}
	public void setErrorDesc(String errorDesc) {
		this.errorDesc = errorDesc;
	}
	public SendMessage() {
		
	}
}
