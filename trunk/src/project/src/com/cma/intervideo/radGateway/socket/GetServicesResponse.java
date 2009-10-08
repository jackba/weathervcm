package com.cma.intervideo.radGateway.socket;

import javax.xml.xpath.XPath;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.w3c.dom.Node;

public class GetServicesResponse extends BaseResponse{
	private static Log logger = LogFactory.getLog(GetServicesResponse.class);
	private String returnValue;
	
	public String getReturnValue() {
		return returnValue;
	}

	public void setReturnValue(String returnValue) {
		this.returnValue = returnValue;
	}

	public void decode(Node cr, XPath xpath) throws MessageDecodeException{
		try{
			setRequestId(xpath.evaluate("RequestID", cr));
			returnValue = xpath.evaluate("ReturnValue", cr);
		}catch(Exception e){
			logger.error(e.toString());
			throw new MessageDecodeException("解析GetServicesResponse消息错误！");
		}
	}
}
