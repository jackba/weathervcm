package com.radvision.icm.service.vcm;

import java.util.ArrayList;
import java.util.List;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;

import com.cma.intervideo.pojo.User;
import com.cma.intervideo.util.VcmProperties;
import com.radvision.icm.service.ControlService;
import com.radvision.icm.service.ControlServicePortType;
import com.radvision.icm.service.LicenseService;
import com.radvision.icm.service.LicenseServicePortType;
import com.radvision.icm.service.MeetingType;
import com.radvision.icm.service.ResourceService;
import com.radvision.icm.service.ResourceServicePortType;
import com.radvision.icm.service.ScheduleService;
import com.radvision.icm.service.ScheduleServicePortType;
import com.radvision.icm.service.UserInfo;
import com.radvision.icm.service.UserResult;
import com.radvision.icm.service.UserService;
import com.radvision.icm.service.UserServicePortType;

public class ICMService {
	protected static String m_ipPort = VcmProperties.getICMIPPort();
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

	public static ScheduleServicePortType getScheduleServicePort()
			throws Exception {
		return ((ScheduleService) getService(ServiceType.ScheduleService))
				.getScheduleServicePort();
	}

	public static ResourceServicePortType getResourceServicePort()
			throws Exception {
		return ((ResourceService) getService(ServiceType.ResourceService))
				.getResourceServicePort();
	}

	public static LicenseServicePortType getLicenseServicePort()
			throws Exception {
		return ((LicenseService) getService(ServiceType.LicenseService))
				.getLicenseServicePort();
	}

	public static ControlServicePortType getControlServicePort()
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
		} catch (Exception e) {
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
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ur;
	}
	
	private static UserInfo convertToUserInfo(User user) {
		UserInfo info = new UserInfo();
		info.setUserId(user.getUserId());
		info.setEmamil(user.getEmail());
		info.setRoleId(2); //TODO: handle role
		info.setUserLoginId(user.getLoginId());
		info.setUserFirstName(user.getUserName());
		info.setUserLastName(user.getUserName()); //TODO: handle first name, last name
		info.setPassword(user.getPassword());
		info.setOfficePhoneNumber(user.getOfficeTelephone());
		return info;
	}
	
	public static List<MeetingType> getMeetingTypes() {
		List<MeetingType> mts = null;
		try {
			mts = getResourceServicePort().getMeetingTypes();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mts;
	}
}
