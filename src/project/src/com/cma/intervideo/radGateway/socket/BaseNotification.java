package com.cma.intervideo.radGateway.socket;

import java.io.ByteArrayInputStream;
import java.util.Calendar;
import java.util.Date;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

import com.cma.intervideo.service.NotificationHandleService;

public abstract class BaseNotification {
	private static Log logger = LogFactory.getLog(BaseNotification.class);
	//创建会议通知
	public static final int Conference_Created_Notification = 1;
	public static final int Conference_Extended_Notification = 2;
	public static final int New_Participant_Notification = 3;
	public static final int Participant_Disconnected_Notification = 4;
	public static final int Conference_Terminated_Notification = 5;
	public static final int Other_Unhandle_Notification = 6;
	public static final int Participant_Deleted_Notification = 7;
	private String xml;
	private Document document;
	private String version;
	private String cookie;
	private int notificationType;
	private String confGID;
	public String getConfGID() {
		return confGID;
	}
	public void setConfGID(String confGID) {
		this.confGID = confGID;
	}
	public String getCookie() {
		return cookie;
	}
	public void setCookie(String cookie) {
		this.cookie = cookie;
	}
	public int getNotificationType() {
		return notificationType;
	}
	public void setNotificationType(int notificationType) {
		this.notificationType = notificationType;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	
	public Document getDocument() {
		return document;
	}
	public void setDocument(Document document) {
		this.document = document;
	}
	public String getXml() {
		return xml;
	}
	public void setXml(String xml) {
		this.xml = xml;
	}
	/**
	 * 对通知消息进行处理,由子类实现. 消息处理成功返回0，被忽略返回1，错误返回-1
	 */
	public abstract int handle(NotificationHandleService service);
	//解析消息通知的xml字符串
	public static BaseNotification parse(String xml){
		try{
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document doc = db.parse(new ByteArrayInputStream(xml.getBytes("utf-8")));
			XPath xpath = XPathFactory.newInstance().newXPath();
			Node n = (Node)xpath.evaluate("/MCU_XML_API/Notification", doc, XPathConstants.NODE);
			String notificationType = n.getFirstChild().getNodeName();
			BaseNotification notification = null;
			//会议创建通知
			if(notificationType.equals("Conference_Created_Notification")){
				notification = new ConferenceCreatedNotification();
				notification.setNotificationType(Conference_Created_Notification);
			}
			//会议终止通知
			else if(notificationType.equals("Conference_Terminated_Notification")){
				notification = new ConferenceTerminatedNotification();
				notification.setNotificationType(Conference_Terminated_Notification);
			}
			//新人加入通知
			else if(notificationType.equals("New_Participant_Notification")){
				notification = new NewParticipantNotification();
				notification.setNotificationType(New_Participant_Notification);
			}
			//参会人离开通知
			else if(notificationType.equals("Participant_Disconnected_Notification")){
				notification = new ParticipantDisconnectedNotification();
				notification.setNotificationType(Participant_Disconnected_Notification);
			}
			//参会人退出会议通知
			else if(notificationType.equals("Participant_Deleted_Notification")) {
				notification = new ParticipantDeletedNotification();
				notification.setNotificationType(Participant_Deleted_Notification);
			}
			//不进行处理的会议通知
			else{
				logger.info("received "+notificationType);
			}
			if(notification!=null){
				Node concreteNotification = n.getFirstChild();
				notification.setXml(xml);
				notification.setDocument(doc);
				notification.setVersion(xpath.evaluate("/MCU_XML_API/Version", doc));
				notification.setCookie(xpath.evaluate("cookie", concreteNotification));
				//notification.setConfGID(xpath.evaluate("/Conf/ConfGID", concreteNotification));
				notification.decode();
				return notification;
			}else
				return null;
		}catch(Exception e){
			logger.error(e.toString());
			return null;
		}
	}
	//对通知消息的xml字符串进行解码,由子类实现
	protected void decode() throws MessageDecodeException{
	}
	//把通知消息类编码成xml字符串,由子类实现
	protected String endcode(){
		return null;
	}
}
