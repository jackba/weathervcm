package com.cma.intervideo.radGateway.socket;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.w3c.dom.Node;

import com.cma.intervideo.service.NotificationHandleService;
import com.cma.intervideo.util.PropertiesHelper;

public class NewParticipantNotification extends BaseNotification{
	private static Log logger = LogFactory.getLog(NewParticipantNotification.class);
	private static String dialOutNumberPrefix = PropertiesHelper.getDialOutNumberPrefix();
	/**
	 * Participant ID
	 */
	private String pid;
	/**
	 * string containing the name of conference participant
	 */
	private String partName;
	private String scopiaPID;
	/**
	 * dialed string of the participant alias
	 */
	private String dialStr;
	/**
	 * Participant IP address
	 */
	private String ip;
	/**
	 * the time the participant joined the conference displayed in the following
	 */
	private Date startT;
	/**
	 * Endpoint type, 0 for ip, 1 for telephone
	 */
	private int ePType;
	/**
	 * only used when EPType is "Gateway", it's value is telephone number
	 */
	private String sourceAddress;
	
	/**
	 * true when eptype is gateway and notification is caused by dialing out
	 */
	private boolean isDialOut;
	
	
	
	public boolean isDialOut() {
		return isDialOut;
	}
	public void setDialOut(boolean isDialOut) {
		this.isDialOut = isDialOut;
	}
	public String getSourceAddress() {
		return sourceAddress;
	}
	public void setSourceAddress(String sourceAddress) {
		this.sourceAddress = sourceAddress;
	}
	
	public int getEPType() {
		return ePType;
	}
	public void setEPType(int type) {
		ePType = type;
	}
	public void decode() throws MessageDecodeException{
		try{
			XPath xpath = XPathFactory.newInstance().newXPath();
			setConfGID(xpath.evaluate("/MCU_XML_API/Notification/New_Participant_Notification/ConfGID",getDocument()));
			Node part = (Node)xpath.evaluate("/MCU_XML_API/Notification/New_Participant_Notification/Part",getDocument(),XPathConstants.NODE);
			pid = xpath.evaluate("PID", part);
			partName = xpath.evaluate("PartName", part);
			int index = partName.lastIndexOf("(External)");
			if(index != -1){
				partName = partName.substring(0,index);
			}
			scopiaPID = xpath.evaluate("SCOPIAPID", part);
			dialStr = xpath.evaluate("DialStr", part);
			ip = xpath.evaluate("IP", part);
			DateFormat df = new SimpleDateFormat("HH:mm:ss MM-dd-yyyy");
			String st = xpath.evaluate("StartT", part);
			if(st!=null && !st.equals("")){
				startT = df.parse(st);
			}
			//startT = df.parse(xpath.evaluate("StartT", part));
			String str1 = xpath.evaluate("EPType", part);
			String invited = xpath.evaluate("Invited", part);
			
			if(str1.equals("Gateway")){
				//如果终端是电话
				ePType = 1;
//				if(dialStr.startsWith(dialOutNumberPrefix)){
//					isDialOut = true;
//				}else{
//					isDialOut = false;
//				}
				if(invited!=null && invited.equals("True")){
					isDialOut = true;
				}else{
					isDialOut = false;
				}
				if(dialStr.startsWith(dialOutNumberPrefix)){
					dialStr = dialStr.substring(dialOutNumberPrefix.length());
				}
				Node sourceList = (Node)xpath.evaluate("Source_List", part, XPathConstants.NODE);
				if(sourceList!=null){
					sourceAddress = sourceList.getFirstChild().getFirstChild().getFirstChild().getNodeValue();
				}else{
					sourceAddress = dialStr;
				}
			}else{
				ePType = 0;
			}
		}catch(Exception e){
			logger.error(e.toString());
			throw new MessageDecodeException("解析新人加入通知错误!");
		}
	}
	public int handle(NotificationHandleService service){
		return service.handle(this); 
	}
	public String getDialStr() {
		return dialStr;
	}
	public void setDialStr(String dialStr) {
		this.dialStr = dialStr;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getPartName() {
		return partName;
	}
	public void setPartName(String partName) {
		this.partName = partName;
	}
	public String getPid() {
		return pid;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}
	public String getScopiaPID() {
		return scopiaPID;
	}
	public void setScopiaPID(String scopiaPID) {
		this.scopiaPID = scopiaPID;
	}
	public Date getStartT() {
		return startT;
	}
	public void setStartT(Date startT) {
		this.startT = startT;
	}
	
}
