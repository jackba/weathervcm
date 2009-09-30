package com.cma.intervideo.constant;

import java.util.HashMap;
import java.util.Map;

public abstract class DataDictStatus {

	// 默认正常状态
	public static final Short normalStatus = new Short("0");

	// 无效状态
	public static final Short invalidateStatus = new Short("1");

	// 暂停状态
	public static final Short pauseStatus = new Short("2");

	public static Map statusMap = new HashMap();

	static {
		statusMap.put(normalStatus, "正常");
		statusMap.put(invalidateStatus, "取消");
		statusMap.put(pauseStatus, "禁用");
	}
	
	public static final Short bulletinNormalStatus = new Short("0");
	public static final Short bulletinInvalidStatus = new Short("1");
	public static final Short messageNormalStatus = new Short("0");
	public static final Short messageInvalidStatus = new Short("1");
	public static Map bulletinStatusMap = new HashMap();
	public static Map messageStatusMap = new HashMap();
	static {
		bulletinStatusMap.put(bulletinNormalStatus, "正常");
		bulletinStatusMap.put(bulletinInvalidStatus, "无效");
		messageStatusMap.put(messageNormalStatus, "正常");
		messageStatusMap.put(messageInvalidStatus, "无效");
	}
}