package com.cma.intervideo.util;

public class PropertiesHelper {
	private static String m_icmIpPort = VcmProperties.getProperty("vcm.icm.ipport", "localhost:8080");
	private static String m_icmDefaultMemberId = VcmProperties.getProperty("vcm.icm.defaultMemberId", "999");
	private static String m_icmDefaultUserId = VcmProperties.getProperty("vcm.icm.defaultUserId", "999");
	private static String m_mcuProxyIp = VcmProperties.getProperty("vcm.icm.mcuproxy.ip","localhost");
	private static int m_mcuProxyPort = VcmProperties.getPropertyByInt("vcm.icm.mcuproxy.port", 3336);
	private static boolean m_startMcuProxySocket = VcmProperties.getPropertyByBoolean("vcm.icm.mcuproxy.startSocket", false);
	private static int m_mcuProxyQueueNum = Integer.parseInt(VcmProperties.getProperty("vcm.icm.mcuproxy.queueNum","20"));
	private static int m_mcuConnectionTestPeriod = Integer.parseInt(VcmProperties.getProperty("vcm.icm.mcuproxy.connectionTestPeriod","5")) * 1000;
	private static String m_mcuProxyAccount = VcmProperties.getProperty("vcm.icm.mcuproxy.mcuAccount", "admin");
	private static String m_mcuProxyPassword = VcmProperties.getProperty("vcm.icm.mcuproxy.mcuPassword", "");
	private static String m_mcuProxyVersion = VcmProperties.getProperty("vcm.icm.mcuproxy.version", "iCM 5.0");
	
	public static String getIcmIpPort() {
		return m_icmIpPort;
	}
	
	public static String getIcmDefaultMemberId() {
		return m_icmDefaultMemberId;
	}
	
	public static String getIcmDefaultUserId() {
		return m_icmDefaultUserId;
	}
	
	public static String getMcuProxyIp() {
		return m_mcuProxyIp;
	}
	
	public static int getMcuProxyPort() {
		return m_mcuProxyPort;
	}
	
	public static boolean startMcuProxySocket() {
		return m_startMcuProxySocket;
	}
	
	public static int getMcuProxyQueueNum() {
		return m_mcuProxyQueueNum;
	}
	
	public static int getMcuConnectionTestPeriod() {
		return m_mcuConnectionTestPeriod;
	}
	
	public static String getMcuProxyAccount() {
		return m_mcuProxyAccount;
	}
	
	public static String getMcuProxyPassword() {
		return m_mcuProxyPassword;
	}
	
	public static String getMcuProxyVersion() {
		return m_mcuProxyVersion;
	}
}
