package com.cma.intervideo.service.impl;

import java.util.List;

import com.cma.intervideo.dao.IServiceDao;
import com.cma.intervideo.pojo.ServiceTemplate;
import com.cma.intervideo.service.IServiceService;

public class ServiceServiceImpl implements IServiceService{
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
		int ret = lst == null ? 0 : lst.size();
		for (int i = 0; lst != null && i < lst.size(); i++) {
			serviceDao.removeObjectByID(lst.get(i).getServiceTemplateId());
		}
		return ret;
	}
	public ServiceTemplate getServiceTemplate(String serviceTemplateId) {
		return serviceDao.getServiceTemplate(serviceTemplateId);
	}
}
