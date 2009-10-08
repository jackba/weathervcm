package com.cma.intervideo.radGateway.socket;

import org.w3c.dom.Element;

public class DropParticipantRequest extends BaseRequest{
	private String confGID = "";
	private String discReason = "";
	private String chairID = "";
	private String pid = "";
	
	public String getChairID() {
		return chairID;
	}

	public void setChairID(String chairID) {
		this.chairID = chairID;
	}

	public String getConfGID() {
		return confGID;
	}

	public void setConfGID(String confGID) {
		this.confGID = confGID;
	}

	public String getDiscReason() {
		return discReason;
	}

	public void setDiscReason(String discReason) {
		this.discReason = discReason;
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public void doEncode(Element request){
		Element r = add("Drop_Participant_Request",request);
		add("RequestID",getRequestId(),r);
		add("ConfGID",confGID,r);
		Element l = add("Drop_Part_List",r);
		add("PID",pid,l);
		add("DiscRsn",discReason,r);
		add("ChairID",chairID,r);
	}

}
