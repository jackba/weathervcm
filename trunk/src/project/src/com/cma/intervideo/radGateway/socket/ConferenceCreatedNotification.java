package com.cma.intervideo.radGateway.socket;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.w3c.dom.Node;

import com.cma.intervideo.service.NotificationHandleService;

public class ConferenceCreatedNotification extends BaseNotification {
	private static Log logger = LogFactory
			.getLog(ConferenceCreatedNotification.class);

	// video capabilities
	private boolean vcaps;

	// audio capabilities
	private boolean acaps;

	// number of reserved participants
	private int resParts;

	// number of maximum participants
	private int maxParts;

	// number of current participants
	private int curParts;

	// conference number
	private String name;

	// conference numeric name
	private String numName;

	// description
	private String desc;

	// service prefix number;
	private String servicePrefix;

	// time to live
	private int ttl;

	// creation time
	private Date creatT;

	// indicates the current conference duration in seconds
	private int duraT;

	// Boolean value indicating whether conference admission is blocked to
	// additional participants
	// attempting to join the conference
	private boolean frozen;

	/**
	 * boolean value indicating whether the conference is password protected. A
	 * true value indicates that the conference is password protected
	 */
	private boolean passPrtct;

	private String password;

	/**
	 * boolean value indicating whether the chair control feature is password
	 * protected. A True value indicates the chair control feature is password
	 * protected
	 */
	private boolean chPassPrtct;

	private boolean streamingEnabled;

	private boolean lectureMode;

	/**
	 * boolean value indicating whether the conference is cascaded. A True value
	 * indicates that the conference is cascaded
	 */
	private boolean casc;

	/**
	 * boolean value indicating whether invites and dial-in calls can be made to
	 * the conference. A True value indicates that invites and dial-in calls can
	 * be made to the conference
	 */
	private boolean inviteCallAllowed;

	private List bosInfoList;

	/**
	 * indication if conference is in waiting room mode
	 */
	private boolean wROn;
	
	public boolean isAcaps() {
		return acaps;
	}

	public void setAcaps(boolean acaps) {
		this.acaps = acaps;
	}

	public List getBosInfoList() {
		return bosInfoList;
	}

	public void setBosInfoList(List bosInfoList) {
		this.bosInfoList = bosInfoList;
	}

	public boolean isCasc() {
		return casc;
	}

	public void setCasc(boolean casc) {
		this.casc = casc;
	}

	public boolean isChPassPrtct() {
		return chPassPrtct;
	}

	public void setChPassPrtct(boolean chPassPrtct) {
		this.chPassPrtct = chPassPrtct;
	}

	public Date getCreatT() {
		return creatT;
	}

	public void setCreatT(Date creatT) {
		this.creatT = creatT;
	}

	public int getCurParts() {
		return curParts;
	}

	public void setCurParts(int curParts) {
		this.curParts = curParts;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public int getDuraT() {
		return duraT;
	}

	public void setDuraT(int duraT) {
		this.duraT = duraT;
	}

	public boolean isFrozen() {
		return frozen;
	}

	public void setFrozen(boolean frozen) {
		this.frozen = frozen;
	}

	public boolean isInviteCallAllowed() {
		return inviteCallAllowed;
	}

	public void setInviteCallAllowed(boolean inviteCallAllowed) {
		this.inviteCallAllowed = inviteCallAllowed;
	}

	public boolean isLectureMode() {
		return lectureMode;
	}

	public void setLectureMode(boolean lectureMode) {
		this.lectureMode = lectureMode;
	}

	public int getMaxParts() {
		return maxParts;
	}

	public void setMaxParts(int maxParts) {
		this.maxParts = maxParts;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNumName() {
		return numName;
	}

	public void setNumName(String numName) {
		this.numName = numName;
	}

	public boolean isPassPrtct() {
		return passPrtct;
	}

	public void setPassPrtct(boolean passPrtct) {
		this.passPrtct = passPrtct;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getResParts() {
		return resParts;
	}

	public void setResParts(int resParts) {
		this.resParts = resParts;
	}

	public String getServicePrefix() {
		return servicePrefix;
	}

	public void setServicePrefix(String servicePrefix) {
		this.servicePrefix = servicePrefix;
	}

	public boolean isStreamingEnabled() {
		return streamingEnabled;
	}

	public void setStreamingEnabled(boolean streamingEnabled) {
		this.streamingEnabled = streamingEnabled;
	}

	public int getTtl() {
		return ttl;
	}

	public void setTtl(int ttl) {
		this.ttl = ttl;
	}

	public boolean isVcaps() {
		return vcaps;
	}

	public void setVcaps(boolean vcaps) {
		this.vcaps = vcaps;
	}

	public boolean isWROn() {
		return wROn;
	}

	public void setWROn(boolean on) {
		wROn = on;
	}

	public void decode() throws MessageDecodeException {
		try {
			XPath xpath = XPathFactory.newInstance().newXPath();
			Node conf = (Node) xpath
					.evaluate(
							"/MCU_XML_API/Notification/Conference_Created_Notification/Conf",
							getDocument(), XPathConstants.NODE);
			setConfGID(xpath.evaluate("ConfGID", conf));
			if (xpath.evaluate("VCaps", conf).equals("True")) {
				vcaps = true;
			} else
				vcaps = false;
			if (xpath.evaluate("ACaps", conf).equals("True")) {
				acaps = true;
			} else
				acaps = false;
			resParts = Integer.parseInt(xpath.evaluate("ResParts", conf));
			maxParts = Integer.parseInt(xpath.evaluate("MaxParts", conf));
			curParts = Integer.parseInt(xpath.evaluate("CurParts", conf));
			name = xpath.evaluate("Name", conf);
			numName = xpath.evaluate("NumName", conf);
			desc = xpath.evaluate("Desc", conf);
			servicePrefix = xpath.evaluate("ServicePrefix", conf);
			ttl = Integer.parseInt(xpath.evaluate("TTL", conf));
			DateFormat df = new SimpleDateFormat("HH:mm:ss MM-dd-yyyy");
			creatT = df.parse(xpath.evaluate("CreatT", conf));
			int duraT = Integer.parseInt(xpath.evaluate("DuraT", conf));
			if (xpath.evaluate("Frozen", conf).equals("True"))
				frozen = true;
			else
				frozen = false;
			if(xpath.evaluate("PassPrtct", conf).equals("True")){
				passPrtct = true;
			}else
				passPrtct = false;
			if(passPrtct == true){
				password = xpath.evaluate("password", conf);
			}
			if(xpath.evaluate("ChPassPrtct", conf).equals("True")){
				chPassPrtct = true;
			}else
				chPassPrtct = false;
		} catch (Exception e) {
			logger.error(e.toString());
			throw new MessageDecodeException("消息解码错误!");
		}
	}

	public int handle(NotificationHandleService service) {
		return service.handle(this);
	}
}
