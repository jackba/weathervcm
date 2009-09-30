package com.radvision.icm.service.test;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;

import com.radvision.icm.service.ControlService;
import com.radvision.icm.service.LicenseService;
import com.radvision.icm.service.ResourceService;
import com.radvision.icm.service.ScheduleService;
import com.radvision.icm.service.UserService;
import com.radvision.icm.service.vcm.ServiceType;

public class ServiceTester {
	//protected static String m_ipPort = "192.168.227.40:8080";
	//protected static String m_ipPort = "192.168.1.102:8080";
	protected static String m_ipPort = "192.168.225.207:8080";
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
}
