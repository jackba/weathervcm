package com.cma.intervideo.service.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.cma.intervideo.dao.IStatDao;
import com.cma.intervideo.service.IStatService;
import com.cma.intervideo.vo.ConfNumStatVo;
import com.cma.intervideo.vo.ConfTimeStatVo;
import com.cma.intervideo.vo.ConfTypeTimeStatVo;
import com.cma.intervideo.vo.UnitTimeStatVo;
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
	
	public List<UserReserveStatVo> statUserReserve(String startDate, String endDate){
		return statDao.statUserReserve();
	}
	
	public List<ConfNumStatVo> statConfNum(String startDate, String endDate){
		return statDao.statConfNum(startDate, endDate);
	}
	
	public List<UnitTimeStatVo> statUnitTime(String startDate, String endDate){
		return statDao.statUnitTime(startDate, endDate);
	}
	
	public List<ConfTypeTimeStatVo> statConfTypeTime(String startDate, String endDate){
		return statDao.statConfTypeTime(startDate, endDate);
	}
	public List<ConfTimeStatVo> statConfTime(String startDate, String endDate){
		return statDao.statConfTime(startDate, endDate);
	}
}
