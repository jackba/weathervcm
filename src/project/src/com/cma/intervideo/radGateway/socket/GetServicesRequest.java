package com.cma.intervideo.radGateway.socket;

import org.w3c.dom.Element;

public class GetServicesRequest extends BaseRequest{
	public void doEncode(Element request){
		Element r = add("Get_Services_Request", request);
		add("RequestID", getRequestId(), r);
	}
}
