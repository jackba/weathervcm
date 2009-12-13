package com.cma.intervideo.service.impl;

import java.util.List;

import com.cma.intervideo.dao.IServiceDao;
import com.cma.intervideo.pojo.ServiceTemplate;
import com.cma.intervideo.service.IServiceService;

public class ServiceServiceImpl implements IServiceService{
	
//	private static Log logger = LogFactory.getLog(TerminalServiceImpl.class);
	
	private IServiceDao serviceDao;
	
	public void setServiceDao(IServiceDao serviceDao) {
		this.serviceDao = serviceDao;
	}
	
	public List<ServiceTemplate> findServices(){
		return serviceDao.findServices();
	}
	
	public void saveOrUpdate(ServiceTemplate service){
		serviceDao.saveOrUpdate(service);
	}
	
	public ServiceTemplate getServiceTemplate(String serviceTemplateId) {
		return serviceDao.getServiceTemplate(serviceTemplateId);
	}
	
	public void deleteServiceTemplatesByNewIds(List<String> newIds) {
		serviceDao.deleteServiceTemplatesByNewIds(newIds);
	}
	
}
