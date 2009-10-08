package com.cma.intervideo.radGateway.socket;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.w3c.dom.Node;

import com.cma.intervideo.service.NotificationHandleService;

public class ParticipantDisconnectedNotification extends BaseNotification{
	private static Log logger = LogFactory.getLog(ParticipantDisconnectedNotification.class);
	/**
	 * The Participant ID is a string the MCU generates to identify a participant
	 */
	private String pid;
	/**
	 * new participant ID
	 */
	private String newPID;
	/**
	 * disconnection reason
	 */
	private String discRsn;
	
	public String getDiscRsn() {
		return discRsn;
	}
	public void setDiscRsn(String discRsn) {
		this.discRsn = discRsn;
	}
	public String getNewPID() {
		return newPID;
	}
	public void setNewPID(String newPID) {
		this.newPID = newPID;
	}
	public String getPid() {
		return pid;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}
	public void decode() throws MessageDecodeException{
		try{
			XPath xpath = XPathFactory.newInstance().newXPath();
			Node n = (Node)xpath.evaluate("/MCU_XML_API/Notification/Participant_Disconnected_Notification", getDocument(), XPathConstants.NODE);
			setConfGID(xpath.evaluate("ConfGID", n));
			pid = xpath.evaluate("PID", n);
			newPID = xpath.evaluate("NewPID", n);
			discRsn = xpath.evaluate("DiscRsn", n);
		}catch(Exception e){
			logger.error(e.toString());
			throw new MessageDecodeException("解析参与人离开通知错误!");
		}
	}
	public int handle(NotificationHandleService service){
		return service.handle(this);
	}
}
