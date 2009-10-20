package com.cma.intervideo.web.action;

import org.apache.commons.logging.LogFactory;

import com.cma.intervideo.service.ILogService;
import com.cma.intervideo.util.AbstractBaseAction;

public class LogAction extends AbstractBaseAction{
	private static org.apache.commons.logging.Log logger = LogFactory.getLog(LogAction.class);
	private ILogService logService;
	public void setLogService(ILogService logService) {
		this.logService = logService;
	}
	public String list(){
		return "list";
	}
}
