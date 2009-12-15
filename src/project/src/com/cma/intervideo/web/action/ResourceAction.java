package com.cma.intervideo.web.action;

import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.cma.intervideo.pojo.ServiceTemplate;
import com.cma.intervideo.service.IResourceService;
import com.cma.intervideo.service.IServiceService;
import com.cma.intervideo.util.AbstractBaseAction;
import com.cma.intervideo.util.PropertiesHelper;
import com.cma.intervideo.vo.ResourceVo;
import com.radvision.icm.service.McuResourceInfo;
import com.radvision.icm.service.McuResourceResult;
import com.radvision.icm.service.vcm.ICMService;

public class ResourceAction extends AbstractBaseAction{
	
	private static final Log logger = LogFactory.getLog(ResourceAction.class);
	
	private IResourceService resourceService;
	
	private IServiceService serviceService;
	
	public void setResourceService(IResourceService resourceService) {
		this.resourceService = resourceService;
	}
	
	public void setServiceService(IServiceService serviceService) {
		this.serviceService = serviceService;
	}

	public String occupy(){
		return "occupy";
	}
	
	public String occupywave(){
		return "occupywave";
	}
	public String available(){
		return "available";
	}
	public String searchOccupy(){
		try{
			logger.info("searchOccupy...");
			Calendar c = Calendar.getInstance();
			long startTime = c.getTimeInMillis();
			long endTime = startTime + 60*1000;
			int interval = 1;
			List<String> serviceTemplateIds = new ArrayList<String>();
			List<ServiceTemplate> serviceList = serviceService.findServices();
			HashMap<String,String> serviceMap = new HashMap<String,String>(serviceList.size());
			for(int i=0;i<serviceList.size();i++){
				serviceTemplateIds.add(serviceList.get(i).getServiceTemplateId());
				serviceMap.put(serviceList.get(i).getServiceTemplateId(), serviceList.get(i).getServiceTemplateName());
			}
			McuResourceResult mrr = ICMService.getResourceInfos(serviceTemplateIds, startTime, endTime, interval);
			if (mrr == null || mrr.getInfos().size() == 0)
			{
				outJson("{success:false, msg:'从平台获取资源失败,请检查连接和配置!'}");
				return null;
			}
			
			int totalConfs = mrr.getConfNums().get(1) - (int)Math.round(mrr.getConfNums().get(0)*mrr.getConfNums().get(1)/100.0);
			List<McuResourceInfo> infoList = mrr.getInfos();
			List<ResourceVo> voList = new ArrayList<ResourceVo>();
			for(int i=0;i<infoList.size();i++){
				List<Integer> portNums = infoList.get(i).getPortNums();
				int total = portNums.get(1);
				ResourceVo vo = new ResourceVo();
				vo.setAvailableNum((int)Math.round(portNums.get(0)*total/100.0));
				vo.setOccupyNum(total-vo.getAvailableNum());
				vo.setServiceTemplateName(serviceMap.get(infoList.get(i).getServiceTemplateId()));
				voList.add(vo);
			}
			List<Integer> portNums = mrr.getInfos().get(0).getPortNums();
			
			int minutes = 0;
			JSONObject json = new JSONObject();
			json.put("totalProperty", voList.size());
			json.put("totalConfs", totalConfs);
			JSONArray arr = JSONArray.fromObject(voList);
			json.put("root", arr);
			System.out.println(json);
			response.setCharacterEncoding("utf-8");

			PrintWriter out = response.getWriter();
			out.print(json);
			out.flush();
			out.close();
		}catch(Exception e){
			logger.error(e.toString());
		}
		return null;
	}
	public String searchOccupyWave(){
		logger.info("searchOccupyWave...");
//		String serviceTemplateId = request.getParameter("serviceTemplate");
		String serviceTemplateId = PropertiesHelper.getDefaultServiceTemplateId();
		if (serviceTemplateId == null || serviceTemplateId.length() == 0) {
			logger.info("searchOccupyWave failed, Please specify default service template first!");
			return null;
		}
		String day = request.getParameter("day");
		int range = Integer.parseInt(request.getParameter("range"));
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		try{
			Date d = df.parse(day);
			Date startDay = d;
			long startTime;
			long endTime;
			int interval;
			if(range == 1){
				//时间范围为一天
				startTime = d.getTime();
				endTime = startTime + 24*60*60*1000;
				interval = 5;
			}else{
				//时间范围为一周
				Calendar c = Calendar.getInstance();
				c.setTime(d);
				int weekday = c.get(Calendar.DAY_OF_WEEK);
				weekday = (weekday+5) % 7;
				c.add(Calendar.DATE, 0-weekday);
				startTime = c.getTimeInMillis();
				startDay = c.getTime();
				c.add(Calendar.DATE, 7);
				endTime = c.getTimeInMillis();
				interval = 30;
			}
			List<String> serviceTemplateIds = new ArrayList<String>();
			serviceTemplateIds.add(serviceTemplateId);
			McuResourceResult mrr = ICMService.getResourceInfos(serviceTemplateIds, startTime, endTime, interval);
			if (mrr == null || !mrr.isSuccess() || mrr.getInfos().size() == 0)
			{
				outJson("{success:false, msg:'从平台获取资源失败,请检查连接和配置!'}");
				return null;
			}
			
			List<Integer> portNums = mrr.getInfos().get(0).getPortNums();
			int total = portNums.get(portNums.size()-1);
			int minutes = 0;
			List<ResourceVo> voList = new ArrayList<ResourceVo>();
			for(int i=0;i<portNums.size()-1;i++){
				ResourceVo vo = new ResourceVo();
				vo.setHourMinutes(getDayHourMinutes(startDay, minutes));
				vo.setAvailableNum((int)Math.round(portNums.get(i)*total/100.0));
				vo.setOccupyNum(total-vo.getAvailableNum());
				voList.add(vo);
				minutes += interval;
			}
			JSONObject json = new JSONObject();
			json.put("totalProperty", voList.size());
			JSONArray arr = JSONArray.fromObject(voList);
			json.put("root", arr);
			System.out.println(json);
			response.setCharacterEncoding("utf-8");

			PrintWriter out = response.getWriter();
			out.print(json);
			out.flush();
			out.close();
		}catch(Exception e){
			logger.error(e.toString());
		}
		return null;
	}
	public String searchAvailable(){
		logger.info("searchAvailable...");
//		String serviceTemplateId = request.getParameter("serviceTemplate");
		String serviceTemplateId = PropertiesHelper.getDefaultServiceTemplateId();
		if (serviceTemplateId == null || serviceTemplateId.length() == 0) {
			logger.info("searchAvailable failed, Please specify default service template first!");
			return null;
		}
		String day = request.getParameter("day");
		int interval = Integer.parseInt(request.getParameter("interval"));
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		try{
			Date d = df.parse(day);
			long startTime = d.getTime();
			long endTime = startTime + 24*60*60*1000;
			List<String> serviceTemplateIds = new ArrayList<String>();
			serviceTemplateIds.add(serviceTemplateId);
			McuResourceResult mrr = ICMService.getResourceInfos(serviceTemplateIds, startTime, endTime, interval);
			if (mrr == null || !mrr.isSuccess() || mrr.getInfos().size() == 0)
			{
				outJson("{success:false, msg:'从平台获取资源失败,请检查连接和配置!'}");
				return null;
			}
			
			List<Integer> portNums = mrr.getInfos().get(0).getPortNums();
			int total = portNums.get(portNums.size()-1);
			int minutes = 0;
			List<ResourceVo> voList = new ArrayList<ResourceVo>();
			for(int i=0;i<portNums.size()-1;i++){
				ResourceVo vo = new ResourceVo();
				vo.setHourMinutes(getHourMinutes(minutes));
				vo.setAvailableNum((int)Math.round(portNums.get(i)*total/100.0));
				vo.setOccupyNum(total-vo.getAvailableNum());
				voList.add(vo);
				minutes += interval;
			}
			JSONObject json = new JSONObject();
			json.put("totalProperty", voList.size());
			JSONArray arr = JSONArray.fromObject(voList);
			json.put("root", arr);
			System.out.println(json);
			response.setCharacterEncoding("utf-8");

			PrintWriter out = response.getWriter();
			out.print(json);
			out.flush();
			out.close();
		}catch(Exception e){
			logger.error(e.toString());
		}
		return null;
	}
	
	private String getHourMinutes(int minutes){
		int hour = minutes/60;
		int m = minutes%60;
		return getTwoChar(hour)+":"+getTwoChar(m);
	}
	
	private String getDayHourMinutes(Date d, int minutes){
		Calendar c = Calendar.getInstance();
		c.setTime(d);
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		int day = (minutes/60)/24;
		int hour = (minutes/60)%24;
		int m = minutes%60;
		c.add(Calendar.DATE, day);
		return df.format(c.getTime())+" "+getTwoChar(hour)+":"+getTwoChar(m);
	}
	private String getTwoChar(int i){
		if(i<10){
			return "0"+i;
		}else{
			return ""+i;
		}
	}
	
}
