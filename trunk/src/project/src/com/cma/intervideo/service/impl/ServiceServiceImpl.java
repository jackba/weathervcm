package com.cma.intervideo.service.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.cma.intervideo.dao.IServiceDao;
import com.cma.intervideo.pojo.ServiceTemplate;
import com.cma.intervideo.service.IServiceService;

public class ServiceServiceImpl implements IServiceService{
	
	private static Log logger = LogFactory.getLog(TerminalServiceImpl.class);
	
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
	
	public int deleteAllServices() {
		List<ServiceTemplate> lst = findServices();
		if (lst == null || lst.size() == 0)
			return 0;
		
		for (ServiceTemplate st : lst) {
			serviceDao.removeObjectByID(st.getServiceTemplateId());
			logger.info("Deleted successfully ServiceTemplate, serviceTemplateId: " + st.getServiceTemplateId() + "; serviceTemplateDesc: " + st.getServiceTemplateDesc());
		}
		return lst.size();
	}
	
	public ServiceTemplate getServiceTemplate(String serviceTemplateId) {
		return serviceDao.getServiceTemplate(serviceTemplateId);
	}
	
}
