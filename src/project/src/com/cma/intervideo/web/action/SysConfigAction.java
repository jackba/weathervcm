package com.cma.intervideo.web.action;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.cma.intervideo.util.AbstractBaseAction;
import com.cma.intervideo.util.PropertiesHelper;
import com.cma.intervideo.util.SystemConfiguration;

public class SysConfigAction extends AbstractBaseAction {

	private static Log logger = LogFactory.getLog(SysConfigAction.class);

	private SystemConfiguration config;

	public SystemConfiguration getConfig() {
		return config;
	}

	public void setConfig(SystemConfiguration config) {
		this.config = config;
	}
	
	public String configModify() {
		if (config == null)
			config = new SystemConfiguration();
		config.setDefaultServiceTemplateId(PropertiesHelper.getDefaultServiceTemplateId());
		config.setIcmHost(PropertiesHelper.getIcmHost());
		config.setIcmPort(PropertiesHelper.getIcmPort());
		return "configModify";
	}
	
	public String configDetail() {
		return "configDetail";
	}
	
	public String modifyConfiguration() throws Exception{
		logger.info("modifyConfiguration...");
		// TODO: confirm which properties need to be configured
		PropertiesHelper.setDefaultServiceTemplateId(config.getDefaultServiceTemplateId());
		PropertiesHelper.setIcmHost(config.getIcmHost());
		PropertiesHelper.setIcmPort(config.getIcmPort());
		PropertiesHelper.store();
		try {
			outJson("{success:true, msg:'修改配置成功!'}");
		} catch (Exception e) {
			outJson("{success:true, msg:'修改配置失败!'}");
		}
		return null;
	}
}
