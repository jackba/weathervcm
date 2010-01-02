package com.cma.intervideo.util;

public class PropertiesHelper {

//	public static synchronized boolean isMcuProxyConnected()
//	{
//		return VcmProperties.getPropertyByBoolean("vcm.icm.mcuproxy.connected", false);
//	}
//	
//	public static synchronized void setMcuProxyConnected(boolean connected)
//	{
//		VcmProperties.setProperties("vcm.icm.mcuproxy.connected", connected ? "true" : "false");
//	}
//	
//	public static synchronized boolean isIcmServiceConnected()
//	{
//		return VcmProperties.getPropertyByBoolean("vcm.icm.webservice.connected", false);
//	}
//	
//	public static synchronized void setIcmServiceConnected(boolean connected)
//	{
//		VcmProperties.setProperties("vcm.icm.webservice.connected", connected ? "true" : "false");
//	}
	
	public static String getDefaultServiceTemplateId() {
		return VcmProperties.getProperty("vcm.defaultServiceTemplateId", "");
	}
	
	public static void setDefaultServiceTemplateId(String defaultServiceTemplateId) {
		VcmProperties.setProperties("vcm.defaultServiceTemplateId", defaultServiceTemplateId);
	}
	
	public static String getIcmHost()
	{
		return VcmProperties.getProperty("vcm.icm.host", "localhost");
	}
	
	public static void setIcmHost(String icmHost) {
		VcmProperties.setProperties("vcm.icm.host", icmHost);
	}
	
	public static String getIcmPort()
	{
		return VcmProperties.getProperty("vcm.icm.port", "8080");
	}
	
	public static void setIcmPort(String icmPort) {
		VcmProperties.setProperties("vcm.icm.port", icmPort);
	}
	
	public static String getIcmIpPort() {
		return VcmProperties.getProperty("vcm.icm.host", "localhost") + ":" + getIcmPort();
	}
	
	public static int getMcuProxyPort() {
		return VcmProperties.getPropertyByInt("vcm.icm.mcuproxy.port", 3336);
	}
	
	public static String getIcmDefaultMemberId() {
		return VcmProperties.getProperty("vcm.icm.defaultMemberId", "999");
	}
	
	public static String getIcmDefaultUserId() {
		return VcmProperties.getProperty("vcm.icm.defaultUserId", "1");
	}
	
	public static boolean startMcuProxySocket() {
		return VcmProperties.getPropertyByBoolean("vcm.icm.mcuproxy.startSocket", false);
	}
	
	public static int getMcuProxyQueueNum() {
		return VcmProperties.getPropertyByInt("vcm.icm.mcuproxy.queueNum", 20);
	}
	
	public static int getMcuConnectionTestPeriod() {
		return VcmProperties.getPropertyByInt("vcm.icm.mcuproxy.connectionTestPeriod", 5) * 1000;
	}
	
	public static String getMcuProxyAccount() {
		return VcmProperties.getProperty("vcm.icm.mcuproxy.mcuAccount", "admin");
	}
	
	public static String getMcuProxyPassword() {
		return VcmProperties.getProperty("vcm.icm.mcuproxy.mcuPassword", "");
	}
	
	public static String getMcuProxyVersion() {
		return VcmProperties.getProperty("vcm.icm.mcuproxy.version", "iCM 5.0");
	}

	public static String getDialOutNumberPrefix() {
		return VcmProperties.getProperty("dialOutNumberPrefix");
	}

	public static int getMaxConfPeroidHour() {
		return VcmProperties.getPropertyByInt("vcm.icm.maxConfPeriod", 24);
	}
	
	public static String getFullMonitorURL() {
		return "http://" + getIcmIpPort() +  VcmProperties.getProperty("vcm.icm.monitorUrl");
	}

	public static void store() {
		VcmProperties.store();
	}
}
