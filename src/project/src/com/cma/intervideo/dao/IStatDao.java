package com.cma.intervideo.dao;

import java.util.List;

import com.cma.intervideo.vo.ConfNumStatVo;
import com.cma.intervideo.vo.ConfTimeStatVo;
import com.cma.intervideo.vo.ConfTypeTimeStatVo;
import com.cma.intervideo.vo.UnitTimeStatVo;
import com.cma.intervideo.vo.UserReserveStatVo;

public interface IStatDao {
	/**
	 * 统计用户使用次数排行，给出前20名
	 * @return
	 */
	public List<UserReserveStatVo> statUserReserve();
	
	public List<UserReserveStatVo> statDayUserReserve(String currDate);
	
	public List<UserReserveStatVo> statUserReserve(String startDate, String endDate);
	
	public List<ConfNumStatVo> statConfNum(String startDate, String endDate);
	
	public List<UnitTimeStatVo> statUnitTime(String startDate, String endDate);
	
	public List<ConfTypeTimeStatVo> statConfTypeTime(String startDate, String endDate);
	
	public List<ConfTimeStatVo> statConfTime(String startDate, String endDate);
}
