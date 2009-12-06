package com.cma.intervideo.util;

public class PropertiesHelper {
	private static String m_icmDefaultMemberId = VcmProperties.getProperty("vcm.icm.defaultMemberId", "999");
	private static String m_icmDefaultUserId = VcmProperties.getProperty("vcm.icm.defaultUserId", "999");
	private static boolean m_startMcuProxySocket = VcmProperties.getPropertyByBoolean("vcm.icm.mcuproxy.startSocket", false);
	private static int m_mcuProxyQueueNum = Integer.parseInt(VcmProperties.getProperty("vcm.icm.mcuproxy.queueNum","20"));
	private static int m_mcuConnectionTestPeriod = Integer.parseInt(VcmProperties.getProperty("vcm.icm.mcuproxy.connectionTestPeriod","5")) * 1000;
	private static String m_mcuProxyAccount = VcmProperties.getProperty("vcm.icm.mcuproxy.mcuAccount", "admin");
	private static String m_mcuProxyPassword = VcmProperties.getProperty("vcm.icm.mcuproxy.mcuPassword", "");
	private static String m_mcuProxyVersion = VcmProperties.getProperty("vcm.icm.mcuproxy.version", "iCM 5.0");
	
	public static String getIcmHost()
	{
		return VcmProperties.getProperty("vcm.icm.host", "localhost");
	}
	
	public static String getIcmPort()
	{
		return VcmProperties.getProperty("vcm.icm.port", "8080");
	}
	
	public static int getMcuProxyPort() {
		return VcmProperties.getPropertyByInt("vcm.icm.mcuproxy.port", 3336);
	}
	
	public static String getIcmDefaultMemberId() {
		return m_icmDefaultMemberId;
	}
	
	public static String getIcmDefaultUserId() {
		return m_icmDefaultUserId;
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
