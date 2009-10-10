package com.cma.intervideo.web.action;

import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.cma.intervideo.service.IResourceService;
import com.cma.intervideo.util.AbstractBaseAction;
import com.cma.intervideo.vo.ResourceVo;
import com.radvision.icm.service.McuResourceResult;
import com.radvision.icm.service.vcm.ICMService;

public class ResourceAction extends AbstractBaseAction{
	private static final Log logger = LogFactory.getLog(ResourceAction.class);
	private IResourceService resourceService;
	public void setResourceService(IResourceService resourceService) {
		this.resourceService = resourceService;
	}
	public String occupy(){
		return "occupy";
	}
	public String available(){
		return "available";
	}
	public String searchOccupy(){
		return null;
	}
	public String searchAvailable(){
		String serviceTemplateId = request.getParameter("serviceTemplate");
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
	private String getTwoChar(int i){
		if(i<10){
			return "0"+i;
		}else{
			return ""+i;
		}
	}
}
