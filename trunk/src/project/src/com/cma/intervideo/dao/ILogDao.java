package com.cma.intervideo.dao;

import com.cma.intervideo.dao.util.DAO;
import com.cma.intervideo.pojo.Log;

public interface ILogDao extends DAO<Integer, Log>{
	/**
	 * 增加日志
	 * @param userId
	 * @param logType
	 * @param description
	 */
	public void addLog(String userId, short logType, String description);
}
