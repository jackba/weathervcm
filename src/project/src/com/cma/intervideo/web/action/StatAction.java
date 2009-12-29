package com.cma.intervideo.web.action;

import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.cma.intervideo.service.IStatService;
import com.cma.intervideo.util.AbstractBaseAction;
import com.cma.intervideo.util.FusionChartUtil;
import com.cma.intervideo.vo.ConfNumStatVo;
import com.cma.intervideo.vo.ConfTimeStatVo;
import com.cma.intervideo.vo.ConfTypeTimeStatVo;
import com.cma.intervideo.vo.UnitTimeStatVo;
import com.cma.intervideo.vo.UserReserveStatVo;
import com.cma.intervideo.vo.column3d.Chart;
import com.cma.intervideo.vo.column3d.Set;

public class StatAction extends AbstractBaseAction{
	private static final Log logger = LogFactory.getLog(StatAction.class);
	private IStatService statService;
	
	public void setStatService(IStatService statService) {
		this.statService = statService;
	}

//	public String userReserveStat(){
//		List<UserReserveStatVo> l = statService.statUserReserve();
//		request.setAttribute("statList", l);
//		return "userReserveStat";
//	}
	
	public String userReserveStat(){
		return "userReserveStat";
	}
	
	public String searchUserReserveStat(){
		try{
			String startDate = request.getParameter("startDate");
			String endDate = request.getParameter("endDate");
			List<UserReserveStatVo> voList = statService.statUserReserve(startDate, endDate);
//			JSONObject json = new JSONObject();
//			json.put("totalProperty", voList.size());
//			JSONArray arr = JSONArray.fromObject(voList);
//			json.put("root", arr);
//			System.out.println(json);
			
			Chart chart = new Chart();
			chart.setCaption("用户使用次数排行榜");
			chart.setXAxisName("用户");
			chart.setYAxisName("次数");
			List<Set> set = new ArrayList<Set>();
			for(int i=0;i<voList.size();i++){
				Set s = new Set();
				UserReserveStatVo vo = voList.get(i);
				s.setLabel(vo.getUserName());
				s.setValue(String.valueOf((vo.getNumber())));
				set.add(s);
			}
			chart.setSet(set);
			String xml = FusionChartUtil.createDummyData(chart);
			logger.info("xml = "+xml);
			response.setCharacterEncoding("utf-8");

			PrintWriter out = response.getWriter();
			out.print(xml);
			out.flush();
			out.close();
		}catch(Exception e){
			logger.error(e.toString());
		}
		return null;
	}
	
	public String userDayReserveStat(){
		String currDate = (String)request.getAttribute("currDate");
		if (currDate == null || currDate.length() ==0) {
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			Date d = Calendar.getInstance().getTime();
			currDate = df.format(d);
		}
		List<UserReserveStatVo> l = statService.statDayUserReserve(currDate);
		for (int i = 0; i < l.size(); i++)
			logger.info("***************" + i + l.get(i));
		logger.info("StatAction::userDayReserveStat " + ((l == null ) ? 0 : l.size()) + " were retrieved!");
		request.setAttribute("statList", l);
		return "userDayReserveStat";
	}
	public String confNumStat(){
		String startDate = request.getParameter("startDate");
		String endDate = request.getParameter("endDate");
		List<ConfNumStatVo> l = statService.statConfNum(startDate, endDate);
		request.setAttribute("statList", l);
		return "confNumStat";
	}
	public String unitTimeStat(){
		return "unitTimeStat";
	}
	public String searchUnitTimeStat(){
		try{
			String startDate = request.getParameter("startDate");
			String endDate = request.getParameter("endDate");
			List<UnitTimeStatVo> voList = statService.statUnitTime(startDate, endDate);
//			JSONObject json = new JSONObject();
//			json.put("totalProperty", voList.size());
//			JSONArray arr = JSONArray.fromObject(voList);
//			json.put("root", arr);
//			System.out.println(json);
			
			Chart chart = new Chart();
			chart.setCaption("主会场时长统计");
			chart.setXAxisName("主会场");
			chart.setYAxisName("时长(分钟)");
			List<Set> set = new ArrayList<Set>();
			for(int i=0;i<voList.size();i++){
				Set s = new Set();
				UnitTimeStatVo vo = voList.get(i);
				s.setLabel(vo.getUnitName());
				s.setValue(String.valueOf((vo.getTimeLong())));
				set.add(s);
			}
			chart.setSet(set);
			String xml = FusionChartUtil.createDummyData(chart);
			logger.info("xml = "+xml);
			response.setCharacterEncoding("utf-8");

			PrintWriter out = response.getWriter();
			out.print(xml);
			out.flush();
			out.close();
		}catch(Exception e){
			logger.error(e.toString());
		}
		return null;
	}
	public String confTypeTimeStat(){
		return "confTypeTimeStat";
	}
	public String searchConfTypeTimeStat(){
		try{
			String startDate = request.getParameter("startDate");
			String endDate = request.getParameter("endDate");
			List<ConfTypeTimeStatVo> voList = statService.statConfTypeTime(startDate, endDate);
			response.setContentType("application/xml;charset=utf-8");
			Chart chart = new Chart();
			chart.setCaption("会议类型时长统计");
			chart.setXAxisName("会议类型");
			chart.setYAxisName("时长(分钟)");
			List<Set> set = new ArrayList<Set>();
			for(int i=0;i<voList.size();i++){
				Set s = new Set();
				ConfTypeTimeStatVo vo = voList.get(i);
				s.setLabel(vo.getConfTypeDesc());
				s.setValue(String.valueOf((vo.getTimeLong())));
				set.add(s);
			}
			chart.setSet(set);
			String xml = FusionChartUtil.createDummyData(chart);
			logger.info("xml = "+xml);
			PrintWriter out = response.getWriter();
			out.print(xml);
			out.flush();
			out.close();
		}catch(Exception e){
			logger.error(e.toString());
		}
		return null;
	}
//	public String searchConfTypeTimeStat(){
//		try{
//			String startDate = request.getParameter("startDate");
//			String endDate = request.getParameter("endDate");
//			List<ConfTypeTimeStatVo> voList = statService.statConfTypeTime(startDate, endDate);
//			JSONObject json = new JSONObject();
//			json.put("totalProperty", voList.size());
//			JSONArray arr = JSONArray.fromObject(voList);
//			json.put("root", arr);
//			System.out.println(json);
//			response.setCharacterEncoding("utf-8");
//
//			PrintWriter out = response.getWriter();
//			out.print(json);
//			out.flush();
//			out.close();
//		}catch(Exception e){
//			logger.error(e.toString());
//		}
//		return null;
//	}
	public String confTimeStat(){
		return "confTimeStat";
	}
	public String searchConfTimeStat(){
		try{
			String startDate = request.getParameter("startDate");
			String endDate = request.getParameter("endDate");
			List<ConfTimeStatVo> voList = statService.statConfTime(startDate, endDate);
//			JSONObject json = new JSONObject();
//			json.put("totalProperty", voList.size());
//			JSONArray arr = JSONArray.fromObject(voList);
//			json.put("root", arr);
//			System.out.println(json);
			
			Chart chart = new Chart();
			chart.setCaption("会议时长排行榜");
			chart.setXAxisName("会议名称");
			chart.setYAxisName("时长(分钟)");
			List<Set> set = new ArrayList<Set>();
			for(int i=0;i<voList.size();i++){
				Set s = new Set();
				ConfTimeStatVo vo = voList.get(i);
				s.setLabel(vo.getSubject());
				s.setValue(String.valueOf((vo.getTimeLong())));
				set.add(s);
			}
			chart.setSet(set);
			String xml = FusionChartUtil.createDummyData(chart);
			logger.info("xml = "+xml);
			response.setCharacterEncoding("utf-8");

			PrintWriter out = response.getWriter();
			out.print(xml);
			out.flush();
			out.close();
		}catch(Exception e){
			logger.error(e.toString());
		}
		return null;
	}
}
