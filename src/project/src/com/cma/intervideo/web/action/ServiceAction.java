package com.cma.intervideo.web.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.cma.intervideo.pojo.ServiceTemplate;
import com.cma.intervideo.service.IServiceService;
import com.cma.intervideo.util.AbstractBaseAction;

public class ServiceAction extends AbstractBaseAction{
	
	private static Log logger = LogFactory.getLog(ServiceAction.class);
	
	private IServiceService serviceService;
	
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
	
	public String search() throws IOException {
		try{
			logger.info("search...");
			if ("true".equals(request.getParameter("update"))) {
				serviceService.update();
			}
			
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
	
	public String searchex(){
		try{
			logger.info("searchex...");
			List<ServiceTemplate> serviceList = serviceService.findServicesByClassification();
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

}
