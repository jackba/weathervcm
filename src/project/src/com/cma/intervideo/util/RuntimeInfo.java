package com.cma.intervideo.util;

import java.util.concurrent.atomic.AtomicBoolean;

public class RuntimeInfo {
	private static AtomicBoolean isMcuProxyConnected = new AtomicBoolean(false);
	private static AtomicBoolean isIcmServiceConnected = new AtomicBoolean(false);

	public static boolean isMcuProxyConnected() {
		return isMcuProxyConnected.get();
	}

	public static void setMcuProxyConnected(boolean connected) {
		isMcuProxyConnected.set(connected);
	}

	public static boolean isIcmServiceConnected() {
		return isIcmServiceConnected.get();
	}

	public static void setIcmServiceConnected(boolean connected) {
		isIcmServiceConnected.set(connected);
	}

}
