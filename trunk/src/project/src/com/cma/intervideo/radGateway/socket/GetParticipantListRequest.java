package com.cma.intervideo.radGateway.socket;

import org.w3c.dom.Element;

public class GetParticipantListRequest extends BaseRequest{
	private String confGID;
	
	public String getConfGID() {
		return confGID;
	}

	public void setConfGID(String confGID) {
		this.confGID = confGID;
	}

	public void doEncode(Element request){
		Element r = add("Get_Participant_List_Request",request);
		add("RequestID",getRequestId(),r);
		add("ConfGID",confGID,r);
		add("SnapShotValue","0",r);
	}
}
