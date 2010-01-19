package com.radvision.icm.service.vcm;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.cma.intervideo.pojo.Conference;
import com.cma.intervideo.pojo.RecurringMeetingInfo;
import com.cma.intervideo.pojo.User;
import com.cma.intervideo.pojo.VirtualRoom;
import com.cma.intervideo.util.PropertiesHelper;
import com.radvision.icm.service.ConferenceInfo;
import com.radvision.icm.service.ConferenceInfoCondition;
import com.radvision.icm.service.ControlResult;
import com.radvision.icm.service.ControlService;
import com.radvision.icm.service.ControlServicePortType;
import com.radvision.icm.service.LicenseService;
import com.radvision.icm.service.LicenseServicePortType;
import com.radvision.icm.service.McuResourceInfo;
import com.radvision.icm.service.McuResourceResult;
import com.radvision.icm.service.MeetingType;
import com.radvision.icm.service.RecurrenceInfo;
import com.radvision.icm.service.ResourceService;
import com.radvision.icm.service.ResourceServicePortType;
import com.radvision.icm.service.ScheduleResult;
import com.radvision.icm.service.ScheduleService;
import com.radvision.icm.service.ScheduleServicePortType;
import com.radvision.icm.service.TerminalInfo;
import com.radvision.icm.service.TerminalResource;
import com.radvision.icm.service.UserInfo;
import com.radvision.icm.service.UserResult;
import com.radvision.icm.service.UserService;
import com.radvision.icm.service.UserServicePortType;
import com.radvision.icm.service.VirtualRoomInfoEx;
import com.radvision.icm.service.VirtualRoomResult;

public class ICMService {

	private static Log logger = LogFactory.getLog(ICMService.class);
	
	private final static QName CONTROL_SERVICE = new QName(
			"http://radvision.com/icm/service/controlservice", 
			"ControlService");
	
	private final static QName RESOURCE_SERVICE = new QName(
			"http://radvision.com/icm/service/resourceservice",
			"ResourceService");
	
	private final static QName SCHEDULE_SERVICE = new QName(
			"http://radvision.com/icm/service/scheduleservice",
			"ScheduleService");
	
	private final static QName LICENSE_SERVICE = new QName(
			"http://radvision.com/icm/service/licenseservice", 
			"LicenseService");
	
	private final static QName USER_SERVICE = new QName(
			"http://radvision.com/icm/service/userservice",
			"UserService");
	
	public final static String ALL_CLASSIFICATIONID = "all";

	protected static Service getService(ServiceType type) throws Exception {
		Service svr = null;
		try {
			QName qn = null;
			String name = "";
			if (type == ServiceType.LicenseService) {
				qn = LICENSE_SERVICE;
				name = "LicenseService";
			} else if (type == ServiceType.ScheduleService) {
				qn = SCHEDULE_SERVICE;
				name = "ScheduleService";
			} else if (type == ServiceType.ControlService) {
				qn = CONTROL_SERVICE;
				name = "ControlService";
			} else if (type == ServiceType.ResourceService) {
				qn = RESOURCE_SERVICE;
				name = "ResourceService";
			} else if (type == ServiceType.UserService) {
				qn = USER_SERVICE;
				name = "UserService";
			} else {
				throw new Exception("Not supported service type.");
			}
			String url = "http://" + getIcmIPPort() + "/icmservice/1.0/" + name
					+ "?wsdl";
			if (type == ServiceType.LicenseService)
				svr = new LicenseService(new java.net.URL(url), qn);
			else if (type == ServiceType.ScheduleService)
				svr = new ScheduleService(new java.net.URL(url), qn);
			else if (type == ServiceType.ControlService)
				svr = new ControlService(new java.net.URL(url), qn);
			else if (type == ServiceType.ResourceService)
				svr = new ResourceService(new java.net.URL(url), qn);
			else if (type == ServiceType.UserService)
				svr = new UserService(new java.net.URL(url), qn);
			else
				throw new Exception("Not supported service type.");
		} catch (Exception ex) {
			throw ex;
		}
		return svr;
	}

	private static String getIcmIPPort() {
		return PropertiesHelper.getIcmIpPort();
	}
	
	public static UserServicePortType getUserServicePortType() throws Exception {
		return ((UserService) getService(ServiceType.UserService))
				.getUserServicePort();
	}

	public static ScheduleServicePortType getScheduleServicePortType()
			throws Exception {
		return ((ScheduleService) getService(ServiceType.ScheduleService))
				.getScheduleServicePort();
	}

	public static ResourceServicePortType getResourceServicePortType()
			throws Exception {
		return ((ResourceService) getService(ServiceType.ResourceService))
				.getResourceServicePort();
	}

	public static LicenseServicePortType getLicenseServicePortType()
			throws Exception {
		return ((LicenseService) getService(ServiceType.LicenseService))
				.getLicenseServicePort();
	}

	public static ControlServicePortType getControlServicePortType()
			throws Exception {
		return ((ControlService) getService(ServiceType.ControlService))
				.getControlServicePort();
	}
	
	public static boolean isConnected()
	{	
		String version;
		try {
			logger.warn("Test RADVISION iCM Service connection " + getIcmIPPort() + "...");
			ScheduleServicePortType portType = getScheduleServicePortType();
			if (portType == null) {
				logger.warn("Test RADVISION iCM Service connection failed due to cannot get ScheduleServicePortType.");
				return false;
			}
			version = portType.getVersion();
		} catch (Exception e) {
			logger.warn("Test RADVISION iCM Service connection failed due to exception: " + e.getMessage());
			return false;
		}
		return version != null && version.trim().length() > 0;
	}

	public static UserResult setUser(User user) {
		UserResult ur = null;
		try {
			List<UserInfo> uis = new ArrayList<UserInfo>();
			uis.add(convertToUserInfo(user));
			ur = getUserServicePortType().setUsers(uis);
			logger.info((ur != null && ur.isSuccess() ? "Successed" : "Failed")
					+ " to save user to iCM platform, loginId: " + user.getLoginId());
		} catch (Exception e) {
			logger.warn("Exception on saving user to iCM platform - " + e.getMessage());
			e.printStackTrace();
		}
		return ur;
	}

	public static UserResult deleteUser(String userId) {
		UserResult ur = null;
		try {
			List<String> ids = new ArrayList<String>();
			ids.add(userId);
			ur = getUserServicePortType().deleteUsers(ids);
			logger.info((ur.isSuccess() ? "Successed" : "Failed")
					+ " to delete user from iCM platform - userId: " + userId);
		} catch (Exception e) {
			logger.warn("Exception on deleting user from iCM platform - " + e.getMessage());
			e.printStackTrace();
		}
		return ur;
	}

	public static List<MeetingType> getMeetingTypes() {
		List<MeetingType> mts = null;
		try {
			mts = getResourceServicePortType().getMeetingTypes();
			logger.info("get " + (mts == null ? "0" : mts.size()) + " meeting types from iCM platform");
		} catch (Exception e) {
			logger.warn("Exception on get meeting types from iCM platform - " + e.getMessage());
			e.printStackTrace();
		}
		return mts;
	}

	public static McuResourceResult getResourceInfos(
			List<String> serviceTemplateIds, long startTime, long endTime,
			int interval)
	{
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		String tmp = "";
		if (serviceTemplateIds != null) {
			for (String s : serviceTemplateIds)
				tmp += s + " ";
		}
		logger.info("Getting Resource Infomation from platform: startTime = " + 
				df.format(startTime) + ", endTime = " + df.format(endTime) + 
				", interval = " + interval + ", serviceTemplateIds = " + tmp);
		
		McuResourceResult mrr = null;
		try {
			mrr = getResourceServicePortType().getResourceInfos(serviceTemplateIds, startTime, endTime, interval);
			if (mrr == null || !mrr.isSuccess() || mrr.getInfos().size() == 0)
			{
				logger.warn("Failed to get Resource Information from platform, result check the connection and configuration!");
				if (mrr == null)
					logger.warn("result is null!");
				else if (!mrr.isSuccess())
					logger.warn("Error code: " + mrr.getErrorCode() + "; Error info: " + mrr.getErrorInfo());
				else if (mrr.getInfos().size() == 0)
					logger.warn("McuResourceInfo size is 0!");
				return mrr;
			}
			
			List<McuResourceInfo> lst = mrr.getInfos();
			for (McuResourceInfo mri : lst) 
			{
				String portNums = "{";
				List<Integer> ports = mri.getPortNums();
				for (int i = 0; i < ports.size(); i++)
					portNums += ("[" + i + "]=" + ports.get(i)) + ";";
				portNums += "}";
				logger.info("McuResourceInfo was gotten: serviceTemplateId = " + mri.getServiceTemplateId() + ", portNums = " + portNums);
			}
		
		} catch (Exception e)
		{
			logger.info("Exception on get resource information from platform: " + e.getMessage());
			e.printStackTrace();
		}
		return mrr;
	}

	public static VirtualRoomResult createVirtualRoom(VirtualRoom room) {
		VirtualRoomResult vrr = null;
		try {
			VirtualRoomInfoEx virtualRoo = converToVirtualRoomInfoEx(room);
			vrr = getScheduleServicePortType().createVirtualRoom(virtualRoo);
			logger.info((vrr != null && vrr.isSuccess() ? "success" : "fail")
							+ " to add virtual room to iCM platform - virtual room number: "
							+ room.getVitualConfId() + ", room id: "
							+ vrr.getVirtualRoomID());
		} catch (Exception e) {
			logger.info("Exception on adding virtual room to platform: " + e.getMessage());
			e.printStackTrace();
		}
		return vrr;
	}

	public static VirtualRoomResult modifyVirtualRoom(VirtualRoom room) {
		VirtualRoomResult vrr = null;
		try {
			VirtualRoomInfoEx virtualRoo = converToVirtualRoomInfoEx(room);
			vrr = getScheduleServicePortType().modifyVirtualRoom(virtualRoo);
			logger.info((vrr != null && vrr.isSuccess() ? "success" : "fail")
							+ " to modify virtual room to iCM platform - virtual room number: "
							+ room.getVitualConfId() + ", room id: "
							+ vrr.getVirtualRoomID());
		} catch (Exception e) {
			logger.info("Exception on modifying virtual room to platform: " + e.getMessage());
			e.printStackTrace();
		}
		return vrr;
	}

	public static boolean deleteVirtualRoom(String virtualRoomNumber) {
		boolean ret = false;
		try {
			ret = getScheduleServicePortType().deleteVirtualRoom(virtualRoomNumber);
			logger.info((ret ? "success" : "fail")
							+ " to delete virtual room from iCM platform - virtual room number: "
							+ virtualRoomNumber);
		} catch (Exception e) {
			logger.info("Exception on deleting virtual room from platform: virtual room number = "
							+ virtualRoomNumber + ", Exception = " + e.getMessage());
			e.printStackTrace();
		}
		return ret;
	}

	public static List<TerminalResource> getTerminals() {
		List<TerminalResource> trs = null;
		try {
			trs = getResourceServicePortType().getTerminals(ALL_CLASSIFICATIONID);
			logger.info("get " + (trs == null ? "0" : trs.size()) + " Terminals from iCM platform");
		} catch (Exception e) {
			logger.info("Exception on getting Terminals from platform: " + e.getMessage());
			e.printStackTrace();
		}
		return trs;
	}

	public static ScheduleResult createConference(Conference conf,
			List<String> listTerminalId) {
		ScheduleResult sr = null;
		try {
			ConferenceInfo info = convertToConferenceInfo(conf, listTerminalId, true);
			sr = getScheduleServicePortType().createConference(info);
			if (sr == null)
				logger.warn("Failed to schedule conference to platform - conference subject: " + conf.getSubject());
			else if (sr.isSuccess())
				logger.info("Successed to schedule conference to platform - conference subject: " + conf.getSubject());
			else
				logger.warn("Failed to schedule conference to platform - conference subject: " + conf.getSubject() + 
						". Error Info: "+ sr.getErrorInfo()+ ". Error Status: "+ sr.getErrorStatus());
		} catch (Exception e) {
			logger.warn("Exception on creating confernece to platform: conference subject = " + conf.getSubject() + ", Exception = " + e.getMessage());
			e.printStackTrace();
		}
		return sr;
	}

	public static ScheduleResult modifyConference(Conference conf,
			List<String> listTerminalId) {
		ScheduleResult sr = null;
		try {
			ConferenceInfo info = convertToConferenceInfo(conf, listTerminalId, false);
			sr = getScheduleServicePortType().modifyConference(info);
			if (sr == null)
				logger.warn("Failed to modify conference to platform - rad conference id: " + conf.getRadConferenceId());
			else if (sr.isSuccess())
				logger.info("Successed to modify conference to platform - rad conference id: " + conf.getRadConferenceId());
			else
				logger.warn("Failed to modify conference to platform - rad conference id: " + conf.getRadConferenceId() + 
						". Error Info: "+ sr.getErrorInfo()+ ". Error Status: "+ sr.getErrorStatus());
		} catch (Exception e) {
			logger.warn("Exception on modifying confernece to platform - rad conference id: " + conf.getRadConferenceId() + ", Eexception = " + e.getMessage());
			e.printStackTrace();
		}
		return sr;
	}

	public static boolean cancelConference(String confId)
	{
		if (confId == null || confId.length() == 0)
			return false;

		boolean b = false;
		try {
			b = getScheduleServicePortType().cancelConference(confId);
			if (b)
				logger.info("Successed to cancel conference to platform - rad conference id: " + confId);
			else
				logger.warn("Fail to cancel conference to platform - rad conference id: " + confId);
		} catch (Exception e) {
			logger.warn("Exception on canceling confernece to platform - - rad conference id: " + confId + " - - " + e.getMessage());
			e.printStackTrace();
		}
		return b;
	}
	
	public static List<ScheduleResult> createRecurrence(RecurringMeetingInfo rmi,
			List<String> listTerminalId) {
		List<ScheduleResult> srs = null;
		try {
			RecurrenceInfo recurrInfo = convertToRecurringInfo(rmi, listTerminalId, true);
			srs = getScheduleServicePortType().createRecurrence(recurrInfo);
		} catch (Exception e) {
			logger.warn("Exception on creating recurrence to platform: recurrence subject = " + rmi.getSubject() + ", Exception = " + e.getMessage());
			e.printStackTrace();
		}
		return srs;
	}

	public static List<ScheduleResult> modifyRecurrence(RecurringMeetingInfo rmi,
			List<String> listTerminalId) {
		List<ScheduleResult> srs = null;
		try {
			RecurrenceInfo recurrInfo = convertToRecurringInfo(rmi, listTerminalId, true);
			srs = getScheduleServicePortType().modifyRecurrence(recurrInfo);
		} catch (Exception e) {
			logger.warn("Exception on modifying recurrence to platform: recurrence subject = " + rmi.getSubject() + ", Exception = " + e.getMessage());
			e.printStackTrace();
		}
		return srs;
	}
	
	public static List<ScheduleResult> cancelRecurrence(String recurrenceId) {
		List<ScheduleResult> srs = null;
		try {
			srs = getScheduleServicePortType().cancelRecurrence(recurrenceId);
		} catch (Exception e) {
			logger.warn("Exception on modifying recurrence to platform: recurrence id = " + recurrenceId + ", Exception = " + e.getMessage());
			e.printStackTrace();
		}
		return srs;
	}
	
	public static ControlResult terminateLiveConference(String confId) {
		if (confId == null || confId.length() == 0)
			return null;
		
		ControlResult ret = null;
		try {
			ret = getControlServicePortType().terminateLiveConference(confId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ret;
	}
	
	/**
	 * @param condition
	 * 		conferenceStatus:
	 * 			0 - upcoming
	 * 			1 - In session
	 * 			2 - history
	 * @return
	 */
	public static List<ConferenceInfo> searchConferences(ConferenceInfoCondition condition) throws Exception {
		if (condition == null)
			return null;
		List<ConferenceInfo> confList = getScheduleServicePortType().searchConferences(condition);
		logger.info("Searched " + (confList == null ? 0 : confList.size()) + " ConferenceInfo from platform, " + ServiceUtil.toString(condition));
		return confList;
	}

	private static ConferenceInfo convertToConferenceInfo(Conference conf,
			List<String> listTerminalId, boolean isCreating) {
		ConferenceInfo info = new ConferenceInfo();
		info.setConferenceId(isCreating ? null : conf.getRadConferenceId());
		info.setUserID(conf.getUserId());
		info.setOrgID(conf.getMemberId());
		info.setDialableConferenceId(conf.getDialableNumber());
		long startTime = conf.getStartTime();
		long endTime = startTime + conf.getTimeLong() * 60000;
		info.setStartTime(startTime);
		info.setEndTime(endTime);
		info.setMeetingTypeId(conf.getServiceTemplateId());
		info.setDescription(conf.getDescription());
		info.setPassword(conf.getPassword());
		info.setFullControlPassword(conf.getControlPin());
		int reservedport = conf.getPortsNum() == null ? 2 : conf.getPortsNum();
		info.setReservedIPPorts(reservedport);
		// info.setReservedISDNPorts(reservedport);
		info.setSubject(conf.getSubject());
		if (listTerminalId != null && listTerminalId.size() > 0) {
			List<TerminalInfo> terminals = info.getTerminals();
			for (int i = 0; i < listTerminalId.size(); i++) {
				TerminalInfo ti = new TerminalInfo();
				ti.setTerminalId(listTerminalId.get(i));
				terminals.add(ti);
			}
		}
		return info;
	}
	
	private static RecurrenceInfo convertToRecurringInfo(RecurringMeetingInfo conf,
			List<String> listTerminalId, boolean isCreating) {
		
		RecurrenceInfo ri = new RecurrenceInfo();

		ConferenceInfo info = new ConferenceInfo();
		info.setConferenceId(isCreating ? null : conf.getRadRecurrenceId());
		info.setUserID(conf.getUserId());
		info.setOrgID(conf.getMemberId());
		info.setDialableConferenceId(conf.getDialableNumber());
		long startTime = conf.getStartTime();
		long endTime = startTime + conf.getTimeLong() * 60000;
		info.setStartTime(startTime);
		info.setEndTime(endTime);
		info.setMeetingTypeId(conf.getServiceTemplateId());
		info.setDescription(conf.getDescription());
		info.setPassword(conf.getPassword());
		info.setFullControlPassword(conf.getControlPin());
		int reservedport = conf.getPortsNum() == null ? 2 : conf.getPortsNum();
		info.setReservedIPPorts(reservedport);
		// info.setReservedISDNPorts(reservedport);
		info.setSubject(conf.getSubject());
		if (listTerminalId != null && listTerminalId.size() > 0) {
			List<TerminalInfo> terminals = info.getTerminals();
			for (int i = 0; i < listTerminalId.size(); i++) {
				TerminalInfo ti = new TerminalInfo();
				ti.setTerminalId(listTerminalId.get(i));
				terminals.add(ti);
			}
		}
		ri.setConferenceInfoTemplate(info);
		
//		RecurInstanceInfo rii = new RecurInstanceInfo();
//		ri.getRecurInstanceInfos().add(rii);
		
		return ri;
	}

	private static UserInfo convertToUserInfo(User user) {
		UserInfo info = new UserInfo();
		info.setUserId(user.getUserId());
		info.setEmamil(user.getEmail());
		/**
		 * Role For Platform User
		 * 1: Organization Administrator
		 * 2: Meeting Organizer
		 * 3: Regular User
		 */
		info.setRoleId(3); // TODO: handle role
		info.setUserLoginId(user.getLoginId());
		// user name (VCM) < -- > last name(platform)me());
		info.setUserLastName(user.getUserName()); 
		info.setPassword(user.getPassword());
		info.setOfficePhoneNumber(user.getOfficeTelephone());
		return info;
	}

	private static VirtualRoomInfoEx converToVirtualRoomInfoEx(VirtualRoom room) {
		VirtualRoomInfoEx virtualRoo = new VirtualRoomInfoEx();
		virtualRoo.setVirtualRoomID(room.getRoomId());
		virtualRoo.setVirtualRoomName(room.getTemplateName());
		virtualRoo.setVirtualRoomNumber(room.getVitualConfId());
		virtualRoo.setPassword(room.getPassword());
		virtualRoo.setChairControlPassword(room.getControlPin());
		virtualRoo.setDescription(room.getDescription());
		virtualRoo.setMemberID(room.getMemberId());
		virtualRoo.setUserID(room.getUserId());
		virtualRoo.setServiceID(room.getServiceTemplate());
		return virtualRoo;
	}
	
}