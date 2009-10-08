package com.cma.intervideo.radGateway.socket;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathFactory;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.cma.intervideo.service.NotificationHandleService;

public class ConferenceTerminatedNotification extends BaseNotification{
	private static Log logger = LogFactory.getLog(ConferenceTerminatedNotification.class);
	private String terminationReason;
	
	public String getTerminationReason() {
		return terminationReason;
	}
	public void setTerminationReason(String terminationReason) {
		this.terminationReason = terminationReason;
	}
	public void decode() throws MessageDecodeException{
		try{
			XPath xpath = XPathFactory.newInstance().newXPath();
			setConfGID(xpath.evaluate("/MCU_XML_API/Notification/Conference_Terminated_Notification/ConfGID", getDocument()));
			terminationReason = xpath.evaluate("/MCU_XML_API/Notification/Conference_Terminated_Notification/TerminationReason", getDocument());
		}catch(Exception e){
			logger.error(e.toString());
			throw new MessageDecodeException("会议结束通知解析出错!");
		}
	}
	public int handle(NotificationHandleService service){
		return service.handle(this);
	}
}
