package com.cma.intervideo.radGateway.socket;

import org.w3c.dom.Element;

public class GetConferenceListRequest extends BaseRequest{
	public void doEncode(Element request){
		Element r = add("Get_Conference_List_Request",request);
		add("RequestID",getRequestId(),r);
		add("SnapShotValue","0",r);
	}
}
