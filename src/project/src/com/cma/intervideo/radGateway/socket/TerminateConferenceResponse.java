package com.cma.intervideo.radGateway.socket;

import javax.xml.xpath.XPath;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.w3c.dom.Node;

public class TerminateConferenceResponse extends BaseResponse{
	private static Log logger = LogFactory.getLog(TerminateConferenceResponse.class);
	private String confGID;
	/**
	 * OK - The action was SUCCESSFUL
	 * ConferenceNotFound - No conference exists with the specified conference global ID
	 * NoPermission - Authorization is granted to perform this action
	 * BadOrMissingParameter - The message contains a bad parameter value or is incomplete
	 */
	private String returnValue;
	
	public String getConfGID() {
		return confGID;
	}

	public void setConfGID(String confGID) {
		this.confGID = confGID;
	}

	public String getReturnValue() {
		return returnValue;
	}

	public void setReturnValue(String returnValue) {
		this.returnValue = returnValue;
	}

	public void decode(Node cr, XPath xpath) throws MessageDecodeException{
		try{
			setRequestId(xpath.evaluate("RequestID", cr));
			confGID = xpath.evaluate("ConfGID", cr);
			returnValue = xpath.evaluate("ReturnValue", cr);
		}catch(Exception e){
			logger.error(e.toString());
			throw new MessageDecodeException("解析DropParticipantResponse消息错误！");
		}
	}
}
