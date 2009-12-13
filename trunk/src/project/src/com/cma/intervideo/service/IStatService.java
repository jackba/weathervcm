package com.cma.intervideo.service;

import java.util.List;

import com.cma.intervideo.vo.UserReserveStatVo;

public interface IStatService {
	/**
	 * 统计用户使用次数排行，给出前20名
	 * @return
	 */
	public List<UserReserveStatVo> statUserReserve();
	
	public List<UserReserveStatVo> statDayUserReserve(String currDate);
	
	public List<UserReserveStatVo> statUserReserve(String startDate, String endDate);
}
