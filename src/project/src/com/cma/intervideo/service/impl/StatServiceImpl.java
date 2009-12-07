package com.cma.intervideo.service.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.cma.intervideo.dao.IStatDao;
import com.cma.intervideo.service.IStatService;
import com.cma.intervideo.vo.UserReserveStatVo;

public class StatServiceImpl implements IStatService{
	private static final Log logger = LogFactory.getLog(StatServiceImpl.class);
	private IStatDao statDao;
	public IStatDao getStatDao() {
		return statDao;
	}
	public void setStatDao(IStatDao statDao) {
		this.statDao = statDao;
	}
	/**
	 * 统计用户使用次数排行，给出前20名
	 * @return
	 */
	public List<UserReserveStatVo> statUserReserve(){
		return statDao.statUserReserve();
	}
	
	public List<UserReserveStatVo> statDayUserReserve(String currDate){
		return statDao.statDayUserReserve(currDate);
	}
}