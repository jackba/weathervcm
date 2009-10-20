package com.cma.intervideo.service.impl;

import org.apache.commons.logging.LogFactory;

import com.cma.intervideo.dao.ILogDao;
import com.cma.intervideo.service.ILogService;

public class LogServiceImpl implements ILogService{
	private static org.apache.commons.logging.Log logger = LogFactory.getLog(LogServiceImpl.class);
	private ILogDao logDao;
	public void setLogDao(ILogDao logDao) {
		this.logDao = logDao;
	}
	
}
