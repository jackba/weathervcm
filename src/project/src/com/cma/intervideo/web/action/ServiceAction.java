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
	public String search(){
		List<ServiceTemplate> serviceList = serviceService.findServices();
		try{
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
		serviceService.deleteAllServices();
		List<MeetingType> mts = ICMService.getMeetingTypes();
		for (int i = 0; mts != null && i < mts.size(); i++) {
			MeetingType mt = mts.get(i);
			ServiceTemplate service = new ServiceTemplate();
			service.setServiceTemplateId(mt.getId());
			service.setServicePrefix(mt.getServicePrefix());
			service.setServiceTemplateName(mt.getName());
			service.setMatchingRate(mt.getBandwidth());
			service.setServiceTemplateDesc(mt.getDescription());
			service.setBuiltInToken(mt.getBuiltInToken());
			service.setSwitchingMode(mt.getSwitchingMode());
			serviceService.saveOrUpdate(service);
		}
		return list();
	}
	public String detail(){
		String id = request.getParameter("serviceTemplateId");
		serviceTemplate = serviceService.getServiceTemplate(id);
		return "detail";
	}
}
