package com.cma.intervideo.dao;

import com.cma.intervideo.dao.util.DAO;
import com.cma.intervideo.pojo.Log;

public interface ILogDao extends DAO<Integer, Log>{
	public static final short type_login = 1;
	public static final short type_logout = 2;
	public static final short type_reserve_conf = 3;
	public static final short type_modify_conf = 4;
	public static final short type_delete_conf = 5;
	public static final short type_create_user = 6;
	public static final short type_modify_user = 7;
	public static final short type_delete_user = 8;
	/**
	 * 增加日志
	 * @param userId
	 * @param logType
	 * @param description
	 */
	public void addLog(String userId, short logType, String description);
}