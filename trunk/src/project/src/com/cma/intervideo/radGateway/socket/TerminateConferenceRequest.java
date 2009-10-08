package com.cma.intervideo.radGateway.socket;

import org.w3c.dom.Element;

public class TerminateConferenceRequest extends BaseRequest{
	private String confGID = "";
	private String chairID = "";
	
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

	public void doEncode(Element request){
		Element r = add("Terminate_Conference_Request",request);
		add("ConfGID", confGID, r);
		add("ChairID", chairID, r);
		add("RequestID", getRequestId(), r);
	}

}
