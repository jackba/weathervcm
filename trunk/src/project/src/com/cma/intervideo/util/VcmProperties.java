package com.cma.intervideo.util;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class VcmProperties {
	/**
	 * Logger for this class
	 */
	private static final Log logger = LogFactory.getLog(VcmProperties.class);

	private static Properties properties;
	
	private static String filePath;
	
	private VcmProperties() {
		InputStream inputStream = null;
		try {
			URL url = VcmProperties.class.getClassLoader().getResource("vcm.properties");
			if (url == null) {
				throw new java.lang.RuntimeException("cannot find vcm.properties by classLoader.getResource()!");
			}
			filePath = url.getFile();
			inputStream = url.openStream();
			properties = new Properties();
			properties.load(inputStream);
		} catch (Exception e) {
			throw new java.lang.RuntimeException("fail to read vcm properties!", e);
		} finally {
			if (inputStream != null) {
				try {
					inputStream.close();
				} catch (IOException e) { e.printStackTrace(); }
			}
		}
	}

	public synchronized static String getProperty(String key) {
		if (properties == null) {
			new VcmProperties();
		}
		return properties.getProperty(key);
	}

	public synchronized static String getProperty(String key, String defaultValue) {
		if (properties == null) {
			new VcmProperties();
		}
		return properties.getProperty(key, defaultValue);
	}

	public synchronized static void reloadSystemProperties() {
		logger.debug("重新读取系统配置");
		new VcmProperties();
	}
	
	public synchronized static void setProperties(String key,String value){
		if (properties == null) {
			new VcmProperties();
		}
		properties.setProperty(key, value);
		FileOutputStream opt=null;
		try {
			 opt=new FileOutputStream(filePath);
			properties.store(opt, null);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			try {
				if (opt != null)
					opt.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public synchronized static String getICMIPPort() {
		if (properties == null) {
			new VcmProperties();
		}
		return properties.getProperty("vcm.icm.ipport", "localhost:8080");
	}
	
	public synchronized static String getICMDefaultMemberId() {
		if (properties == null) {
			new VcmProperties();
		}
		return properties.getProperty("vcm.icm.defaultMemberId", "999");
	}
}
