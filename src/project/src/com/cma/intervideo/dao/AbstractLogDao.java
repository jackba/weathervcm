package com.cma.intervideo.dao;

import java.util.Calendar;
import java.util.Date;

import org.apache.commons.logging.LogFactory;

import com.cma.intervideo.dao.util.AbstractDAO;
import com.cma.intervideo.pojo.Log;

public abstract class AbstractLogDao extends AbstractDAO<Integer, Log> implements ILogDao{
	private static org.apache.commons.logging.Log logger = LogFactory.getLog(AbstractLogDao.class);
	
	/**
	 * 增加日志
	 * @param userId
	 * @param logType
	 * @param description
	 */
	public void addLog(String userId, short logType, String description){
		Log log = new Log();
		Date d = Calendar.getInstance().getTime();
		log.setCreateTime(d);
		log.setUserId(userId);
		log.setLogType(logType);
		log.setDescription(description);
		this.getHibernateTemplate().save(log);
	}
}
