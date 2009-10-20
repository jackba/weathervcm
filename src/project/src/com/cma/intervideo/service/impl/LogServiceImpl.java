package com.cma.intervideo.service.impl;

import java.util.List;

import org.apache.commons.logging.LogFactory;

import com.cma.intervideo.dao.ILogDao;
import com.cma.intervideo.pojo.Log;
import com.cma.intervideo.service.ILogService;
import com.cma.intervideo.util.PageHolder;
import com.cma.intervideo.util.ParamVo;

public class LogServiceImpl implements ILogService{
	private static org.apache.commons.logging.Log logger = LogFactory.getLog(LogServiceImpl.class);
	private ILogDao logDao;
	public void setLogDao(ILogDao logDao) {
		this.logDao = logDao;
	}
	/**
	 * 增加日志
	 * @param userId
	 * @param logType
	 * @param description
	 */
	public void addLog(String userId, short logType, String description){
		logDao.addLog(userId, logType, description);
	}
	public List<Log> findLogs(List<ParamVo> params, PageHolder ph){
		return logDao.findLogs(params, ph);
	}
}
