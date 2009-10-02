package com.cma.intervideo.web.action;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.cma.intervideo.service.IConfService;
import com.cma.intervideo.util.AbstractBaseAction;

public class ConfAction extends AbstractBaseAction{
	private static Log logger = LogFactory.getLog(AbstractBaseAction.class);
	private IConfService confService;
	
	public void setConfService(IConfService confService) {
		this.confService = confService;
	}
	public String listReserve(){
		return "listReserve";
	}
	public String reserveConf(){
		return "reserveConf";
	}
	public String modifyReserve(){
		return "modifyReserve";
	}
	public String reserveDetail(){
		return "reserveDetail";
	}
}
