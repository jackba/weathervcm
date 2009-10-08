package com.cma.intervideo.radGateway.socket;

import java.util.Date;

public class Part {
	/**
	 * Participant ID
	 */
	private String pid;
	/**
	 * string containing the name of conference participant
	 */
	private String partName;
	/**
	 * dialed string of the participant alias
	 */
	private String dialStr;
	/**
	 * Participant IP address
	 */
	private String ip;
	/**
	 * Endpoint type, 0 for ip, 1 for telephone
	 */
	private int ePType;
	public String getDialStr() {
		return dialStr;
	}
	public void setDialStr(String dialStr) {
		this.dialStr = dialStr;
	}
	public int getEPType() {
		return ePType;
	}
	public void setEPType(int type) {
		ePType = type;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getPartName() {
		return partName;
	}
	public void setPartName(String partName) {
		this.partName = partName;
	}
	public String getPid() {
		return pid;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}
	
}
