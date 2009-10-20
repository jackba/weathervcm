package com.cma.intervideo.service;

import java.util.List;

import com.cma.intervideo.pojo.Log;
import com.cma.intervideo.util.PageHolder;
import com.cma.intervideo.util.ParamVo;

public interface ILogService {
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
	public List<Log> findLogs(List<ParamVo> params, PageHolder ph);
}
