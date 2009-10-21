package com.cma.intervideo.web.action;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.cma.intervideo.service.IStatService;
import com.cma.intervideo.util.AbstractBaseAction;
import com.cma.intervideo.vo.UserReserveStatVo;

public class StatAction extends AbstractBaseAction{
	private static final Log logger = LogFactory.getLog(StatAction.class);
	private IStatService statService;
	
	public void setStatService(IStatService statService) {
		this.statService = statService;
	}

	public String userReserveStat(){
		List<UserReserveStatVo> l = statService.statUserReserve();
		request.setAttribute("statList", l);
		return "userReserveStat";
	}
	
	public String userDayReserveStat(){
		String currDate = (String)request.getAttribute("currDate");
		if (currDate == null || currDate.length() ==0) {
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			Date d = Calendar.getInstance().getTime();
			currDate = df.format(d);
		}
		List<UserReserveStatVo> l = statService.statDayUserReserve(currDate);
		request.setAttribute("statList", l);
		return "userDayReserveStat";
	}
}
