package com.cma.intervideo.radGateway.socket;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.w3c.dom.Node;

import com.cma.intervideo.service.NotificationHandleService;

public class ParticipantDeletedNotification extends BaseNotification{
	private static Log logger = LogFactory.getLog(ParticipantDeletedNotification.class);
	private String pid;
	
	public String getPid() {
		return pid;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}
	public void decode() throws MessageDecodeException{
		try{
			XPath xpath = XPathFactory.newInstance().newXPath();
			Node n = (Node)xpath.evaluate("/MCU_XML_API/Notification/Participant_Deleted_Notification", getDocument(), XPathConstants.NODE);
			setConfGID(xpath.evaluate("ConfGID", n));
			pid = xpath.evaluate("PID", n);
		}catch(Exception e){
			logger.error(e.toString());
			throw new MessageDecodeException("解析参与人离开通知错误!");
		}
	}
	public int handle(NotificationHandleService service){
		return service.handle(this);
	}
}
