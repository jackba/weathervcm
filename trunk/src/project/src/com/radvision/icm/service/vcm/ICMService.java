package com.radvision.icm.service.vcm;

import java.util.ArrayList;
import java.util.List;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.cma.intervideo.pojo.Conference;
import com.cma.intervideo.pojo.User;
import com.cma.intervideo.pojo.VirtualRoom;
import com.cma.intervideo.util.PropertiesHelper;
import com.radvision.icm.service.ConferenceInfo;
import com.radvision.icm.service.ControlResult;
import com.radvision.icm.service.ControlService;
import com.radvision.icm.service.ControlServicePortType;
import com.radvision.icm.service.LicenseService;
import com.radvision.icm.service.LicenseServicePortType;
import com.radvision.icm.service.McuResourceResult;
import com.radvision.icm.service.MeetingType;
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
	protected static String m_ipPort = PropertiesHelper.getIcmIpPort();
	private final static QName CONTROL_SERVICE = new QName(
			"http://radvision.com/icm/service/controlservice", "ControlService");
	private final static QName RESOURCE_SERVICE = new QName(
			"http://radvision.com/icm/service/resourceservice",
			"ResourceService");
	private final static QName SCHEDULE_SERVICE = new QName(
			"http://radvision.com/icm/service/scheduleservice",
			"ScheduleService");
	private final static QName LICENSE_SERVICE = new QName(
			"http://radvision.com/icm/service/licenseservice", "LicenseService");
	private final static QName USER_SERVICE = new QName(
			"http://radvision.com/icm/service/userservice", "UserService");
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
			String url = "http://" + m_ipPort + "/icmservice/1.0/" + name
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

	public static UserResult setUser(User user) {
		UserResult ur = null;
		try {
			List<UserInfo> uis = new ArrayList<UserInfo>();
			uis.add(convertToUserInfo(user));
			ur = getUserServicePortType().setUsers(uis);
			logger.info((ur != null && ur.isSuccess() ? "success" : "fail")
					+ " to save user to iCM platform - loginid: "
					+ user.getLoginId());
		} catch (Exception e) {
			logger.info("Exception on saving user to iCM platform - "
					+ e.getMessage());
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
			logger.info((ur.isSuccess() ? "success" : "fail")
					+ " to delete user from iCM platform - userId: " + userId);
		} catch (Exception e) {
			logger.info("Exception on deleting user from iCM platform - "
					+ e.getMessage());
			e.printStackTrace();
		}
		return ur;
	}

	public static List<MeetingType> getMeetingTypes() {
		List<MeetingType> mts = null;
		try {
			mts = getResourceServicePortType().getMeetingTypes();
			logger.info("get " + (mts == null ? "0" : mts.size())
					+ " meeting types from iCM platform");
		} catch (Exception e) {
			logger.info("Exception on get meeting types from iCM platform - "
					+ e.getMessage());
			e.printStackTrace();
		}
		return mts;
	}

	public static McuResourceResult getResourceInfos(
			List<String> serviceTemplateIds, long startTime, long endTime,
			int interval) {
		McuResourceResult mrr = null;
		try {
			mrr = getResourceServicePortType().getResourceInfos(
					serviceTemplateIds, startTime, endTime, interval);
			logger.info((mrr.isSuccess() ? "success" : "fail")
					+ " to get resource infos from iCM platform");
		} catch (Exception e) {
			logger.info("Exception on get resource infos from iCM platform - "
					+ e.getMessage());
			e.printStackTrace();
		}
		return mrr;
	}

	public static VirtualRoomResult createVirtualRoom(VirtualRoom room) {
		VirtualRoomResult vrr = null;
		try {
			VirtualRoomInfoEx virtualRoo = converToVirtualRoomInfoEx(room);
			vrr = getScheduleServicePortType().createVirtualRoom(virtualRoo);
			logger
					.info((vrr != null && vrr.isSuccess() ? "success" : "fail")
							+ " to add virtual room to iCM platform - virtual room number: "
							+ room.getVitualConfId() + ", room id: "
							+ vrr.getVirtualRoomID());
		} catch (Exception e) {
			logger.info("Exception on adding virtual room to iCM platform - "
					+ e.getMessage());
			e.printStackTrace();
		}
		return vrr;
	}

	public static VirtualRoomResult modifyVirtualRoom(VirtualRoom room) {
		VirtualRoomResult vrr = null;
		try {
			VirtualRoomInfoEx virtualRoo = converToVirtualRoomInfoEx(room);
			vrr = getScheduleServicePortType().modifyVirtualRoom(virtualRoo);
			logger
					.info((vrr != null && vrr.isSuccess() ? "success" : "fail")
							+ " to modify virtual room to iCM platform - virtual room number: "
							+ room.getVitualConfId() + ", room id: "
							+ vrr.getVirtualRoomID());
		} catch (Exception e) {
			logger
					.info("Exception on modifying virtual room to iCM platform - "
							+ e.getMessage());
			e.printStackTrace();
		}
		return vrr;
	}

	public static boolean deleteVirtualRoom(String virtualRoomNumber) {
		boolean ret = false;
		try {
			ret = getScheduleServicePortType().deleteVirtualRoom(
					virtualRoomNumber);
			logger
					.info((ret ? "success" : "fail")
							+ " to delete virtual room from iCM platform - virtual room number: "
							+ virtualRoomNumber);
		} catch (Exception e) {
			logger
					.info("Exception on deleting virtual room from iCM platform - virtual room number: "
							+ virtualRoomNumber + " - " + e.getMessage());
			e.printStackTrace();
		}
		return ret;
	}

	public static List<TerminalResource> getTerminals() {
		List<TerminalResource> trs = null;
		try {
			trs = getResourceServicePortType().getTerminals(
					ALL_CLASSIFICATIONID);
			logger.info("get " + (trs == null ? "0" : trs.size())
					+ " Terminals from iCM platform");
		} catch (Exception e) {
			logger.info("Exception on getting Terminals from iCM platform - "
					+ e.getMessage());
			e.printStackTrace();
		}
		return trs;
	}

	public static ScheduleResult createConference(Conference conf,
			List<String> listTerminalId) {
		ScheduleResult sr = null;
		try {
			ConferenceInfo info = convertToConferenceInfo(conf, listTerminalId,
					true);
			sr = getScheduleServicePortType().createConference(info);
			logger
					.info((sr != null && sr.isSuccess() ? "success" : "fail")
							+ " to schedule conference to iCM platform - conference subject: "
							+ conf.getSubject());
		} catch (Exception e) {
			logger
					.info("Exception on creating confernece to iCM platform - conference subject: "
							+ conf.getSubject() + " - " + e.getMessage());
			e.printStackTrace();
		}
		return sr;
	}

	public static ScheduleResult modifyConference(Conference conf,
			List<String> listTerminalId) {
		ScheduleResult sr = null;
		try {
			ConferenceInfo info = convertToConferenceInfo(conf, listTerminalId,
					false);
			sr = getScheduleServicePortType().modifyConference(info);
			logger
					.info((sr != null && sr.isSuccess() ? "success" : "fail")
							+ " to modify conference to iCM platform - rad conference id : "
							+ conf.getRadConferenceId());
		} catch (Exception e) {
			logger
					.info("Exception on modifying confernece to iCM platform - rad conference id: "
							+ conf.getRadConferenceId()
							+ " - "
							+ e.getMessage());
			e.printStackTrace();
		}
		return sr;
	}

	public static boolean cancelConference(String confId) {
		if (confId == null || confId.length() == 0)
			return false;

		boolean b = false;
		try {
			b = getScheduleServicePortType().cancelConference(confId);
			logger
					.info((b ? "success" : "fail")
							+ " to cancel conference to iCM platform - rad conference id: "
							+ confId);
		} catch (Exception e) {
			logger
					.info("Exception on creating confernece to iCM platform - - rad conference id: "
							+ confId + " - - " + e.getMessage());
			e.printStackTrace();
		}
		return b;
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
		info.setMeetingTypeId(conf.getServiceTemplate());
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

	private static UserInfo convertToUserInfo(User user) {
		UserInfo info = new UserInfo();
		info.setUserId(user.getUserId());
		info.setEmamil(user.getEmail());
		info.setRoleId(2); // TODO: handle role
		info.setUserLoginId(user.getLoginId());
		info.setUserFirstName(user.getUserName());
		info.setUserLastName(user.getUserName()); // TODO: handle first name,
		// last name
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
