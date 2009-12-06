package com.cma.intervideo.web.action;

import java.io.PrintWriter;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.cma.intervideo.pojo.ServiceTemplate;
import com.cma.intervideo.service.IServiceService;
import com.cma.intervideo.util.AbstractBaseAction;
import com.radvision.icm.service.MeetingType;
import com.radvision.icm.service.vcm.ICMService;

public class ServiceAction extends AbstractBaseAction{
	
	private static Log logger = LogFactory.getLog(ServiceAction.class);
	
	private IServiceService serviceService ;
	
	private ServiceTemplate serviceTemplate;
	
	public ServiceTemplate getServiceTemplate() {
		return serviceTemplate;
	}

	public void setServiceTemplate(ServiceTemplate serviceTemplate) {
		this.serviceTemplate = serviceTemplate;
	}
	
	public void setServiceService(IServiceService serviceService) {
		this.serviceService = serviceService;
	}
	
	public String list(){
		return "list";
	}
	
	public String detail(){
		String id = request.getParameter("serviceTemplateId");
		serviceTemplate = serviceService.getServiceTemplate(id);
		logger.info("ServiceTemplate detail information, serviceTemplateId: " + id + "; detail: " + serviceTemplate);
		return "detail";
	}
	
	public String search(){
		try{
			logger.info("search...");
			List<ServiceTemplate> serviceList = serviceService.findServices();
			JSONObject json = new JSONObject();
			JSONArray arr = JSONArray.fromObject(serviceList);
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
	
	public String update() {
		logger.info("update...");
		serviceService.deleteAllServices();
		List<MeetingType> mts = ICMService.getMeetingTypes();
		int count = 0;
		for (int i = 0; mts != null && i < mts.size(); i++) {
			MeetingType mt = mts.get(i);
			if ("EMBEDDED".equals(mt.getBuiltInToken()) || "N/A".equals(mt.getServicePrefix()))
				continue;
			ServiceTemplate service = new ServiceTemplate();
			service.setServiceTemplateId(mt.getId());
			service.setServicePrefix(mt.getServicePrefix());
			service.setServiceTemplateName(mt.getName());
			service.setMatchingRate(mt.getBandwidth());
			service.setServiceTemplateDesc(mt.getDescription());
			service.setBuiltInToken(mt.getBuiltInToken());
			service.setSwitchingMode(mt.getSwitchingMode());
			serviceService.saveOrUpdate(service);
			logger.info("Service Template was downloaded from Platform and saved to VCM: " + service);
			count++;
		}
		logger.info(count + "Service Template were downloaded from Platform and saved to VCM!");
		return list();
	}

}
