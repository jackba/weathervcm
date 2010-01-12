package com.cma.intervideo.web.action;

import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.cma.intervideo.pojo.ServiceTemplate;
import com.cma.intervideo.service.IResourceService;
import com.cma.intervideo.service.IServiceService;
import com.cma.intervideo.util.AbstractBaseAction;
import com.cma.intervideo.util.FusionChartUtil;
import com.cma.intervideo.util.PropertiesHelper;
import com.cma.intervideo.vo.ResourceVo;
import com.cma.intervideo.vo.line2d.Apply;
import com.cma.intervideo.vo.line2d.Style;
import com.cma.intervideo.vo.line2d.Styles;
import com.cma.intervideo.vo.stcol3d.Category;
import com.cma.intervideo.vo.stcol3d.Chart;
import com.cma.intervideo.vo.stcol3d.Dataset;
import com.cma.intervideo.vo.stcol3d.Set;
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
			/**
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
			*/
			Map<String, List<ServiceTemplate>> serviceHm = serviceService.classifyServices();
			HashMap<String,String> serviceMap = new HashMap<String,String>();
			Iterator<String> it = serviceHm.keySet().iterator();
			while (it.hasNext()) {
				String key = it.next();
				List<ServiceTemplate> lst = serviceHm.get(key);
				if (lst == null || lst.size() == 0)
					continue;
				
				serviceTemplateIds.add(lst.get(0).getServiceTemplateId());
				serviceMap.put(lst.get(0).getServiceTemplateId(), lst.get(0).getServiceTemplateClassification());
			}
			McuResourceResult mrr = ICMService.getResourceInfos(serviceTemplateIds, startTime, endTime, interval);
			if (mrr == null || mrr.getInfos().size() == 0)
			{
				outJson("{success:false, msg:'从平台获取资源失败,请检查连接和配置!'}");
				return null;
			}
			
			int totalConfs = mrr.getConfNums().get(1) - (int)Math.round(mrr.getConfNums().get(0)*mrr.getConfNums().get(1)/100.0);
			session.put("totalConfs", totalConfs);
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
//			List<Integer> portNums = mrr.getInfos().get(0).getPortNums();
//			int minutes = 0;
//			JSONObject json = new JSONObject();
//			json.put("totalProperty", voList.size());
//			json.put("totalConfs", totalConfs);
//			JSONArray arr = JSONArray.fromObject(voList);
//			json.put("root", arr);
//			System.out.println(json);
			Chart chart = new Chart();
			chart.setCaption("资源占用情况");
			chart.setXAxisName("会议类型");
			chart.setYAxisName("数量");
			chart.setShowvalues("1");
			chart.setRotateLabels("0");
			chart.setCategories(new ArrayList<Category>());
			chart.setDatasets(new ArrayList<Dataset>());
			Dataset ods = new Dataset();
			ods.setShowValues("1");
			ods.setSeriesName("占用");
			ods.setColor("FF0000");
			ods.setSets(new ArrayList<Set>());
			chart.getDatasets().add(ods);
			Dataset ads = new Dataset();
			ads.setShowValues("1");
			ads.setSeriesName("空闲");
			ads.setColor("00FF00");
			ads.setSets(new ArrayList<Set>());
			chart.getDatasets().add(ads);
			for(int i=0;i<voList.size();i++){
				ResourceVo vo = voList.get(i);
				Category ct = new Category();
				ct.setLabel(vo.getServiceTemplateName());
				chart.getCategories().add(ct);
				Set os = new Set();
				os.setValue(vo.getOccupyNum()+"");
				ods.getSets().add(os);
				Set as = new Set();
				as.setValue(vo.getAvailableNum()+"");
				ads.getSets().add(as);
			}
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
	public String getTotalConfs(){
		response.setCharacterEncoding("utf-8");
		Integer totalConfs = (Integer)session.get("totalConfs");
		if(totalConfs == null){
			totalConfs = 0;
		}
		try{
			PrintWriter out = response.getWriter();
			out.print(totalConfs);
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
				//interval = 5;
				interval = 30;
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
//			JSONObject json = new JSONObject();
//			json.put("totalProperty", voList.size());
//			JSONArray arr = JSONArray.fromObject(voList);
//			json.put("root", arr);
//			System.out.println(json);
			com.cma.intervideo.vo.line2d.Chart chart = new com.cma.intervideo.vo.line2d.Chart();
			chart.setCaption("资源占用波动图");
			chart.setXAxisName("时间");
			chart.setYAxisName("数量");
			if(range!=1){
				chart.setLabelStep("8");
			}
			chart.setSets(new ArrayList<com.cma.intervideo.vo.line2d.Set>());
			for(int i=0;i<voList.size();i++){
				ResourceVo vo = voList.get(i);
				com.cma.intervideo.vo.line2d.Set set = new com.cma.intervideo.vo.line2d.Set();
				set.setLabel(vo.getHourMinutes());
				set.setValue(""+vo.getOccupyNum());
				chart.getSets().add(set);
			}
			Styles styles = new Styles();
			chart.setStyles(styles);
			List<Style> definition = new ArrayList<Style>();
			List<Apply> application = new ArrayList<Apply>();
			styles.setDefinition(definition);
			styles.setApplication(application);
			Style style = new Style();
			style.setName("Anim1");
			style.setType("animation");
			style.setParam("_xscale");
			style.setStart("0");
			style.setDuration("1");
			definition.add(style);
			style = new Style();
			style.setName("Anim2");
			style.setType("animation");
			style.setParam("_alpha");
			style.setStart("0");
			style.setDuration("0.6");
			definition.add(style);
			style = new Style();
			style.setName("DataShadow");
			style.setType("Shadow");
			style.setAlpha("40");
			definition.add(style);
			Apply apply = new Apply();
			apply.setToObject("DIVLINES");
			apply.setStyles("Anim1");
			application.add(apply);
			apply = new Apply();
			apply.setToObject("HGRID");
			apply.setStyles("Anim2");
			application.add(apply);
			apply = new Apply();
			apply.setToObject("DATALABELS");
			apply.setStyles("DataShadow,Anim2");
			application.add(apply);
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
	public String searchAvailable1(){
		logger.info("searchAvailable1...");
		String serviceTemplateId = request.getParameter("serviceTemplateId");
		if (serviceTemplateId == null || serviceTemplateId.length() == 0)
			serviceTemplateId = PropertiesHelper.getDefaultServiceTemplateId();
		if (serviceTemplateId == null || serviceTemplateId.length() == 0) {
			logger.warn("searchAvailable1 failed, Please specify default service template first!");
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
//				outJson("{success:false, msg:'从平台获取资源失败,请检查连接和配置!'}");
				logger.warn("searchAvailable1 failed, Please check the connection to platform first!");
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
			Chart chart = new Chart();
			chart.setCaption("可用资源情况");
			chart.setXAxisName("时间");
			chart.setYAxisName("数量");
			chart.setCategories(new ArrayList<Category>());
			chart.setDatasets(new ArrayList<Dataset>());
			Dataset ods = new Dataset();
			ods.setSeriesName("占用");
			ods.setColor("FF0000");
			ods.setSets(new ArrayList<Set>());
			chart.getDatasets().add(ods);
			Dataset ads = new Dataset();
			ads.setSeriesName("空闲");
			ads.setColor("00FF00");
			ads.setSets(new ArrayList<Set>());
			chart.getDatasets().add(ads);
			for(int i=0;i<voList.size();i++){
				ResourceVo vo = voList.get(i);
				Category c = new Category();
				c.setLabel(vo.getHourMinutes());
				chart.getCategories().add(c);
				Set os = new Set();
				os.setValue(vo.getOccupyNum()+"");
				ods.getSets().add(os);
				Set as = new Set();
				as.setValue(vo.getAvailableNum()+"");
				ads.getSets().add(as);
			}
			String xml = FusionChartUtil.createDummyData(chart);
			logger.info("xml = "+xml);
//			JSONObject json = new JSONObject();
//			json.put("totalProperty", voList.size());
//			JSONArray arr = JSONArray.fromObject(voList);
//			json.put("root", arr);
//			System.out.println(json);
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
	public String searchAvailable(){
		logger.info("searchAvailable...");
		String serviceTemplateId = request.getParameter("serviceTemplateId");
		if (serviceTemplateId == null || serviceTemplateId.length() == 0)
			serviceTemplateId = PropertiesHelper.getDefaultServiceTemplateId();
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
