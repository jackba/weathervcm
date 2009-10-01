package com.cma.intervideo.service;

import java.util.List;

import com.cma.intervideo.pojo.ServiceTemplate;

public interface IServiceService {
	public List<ServiceTemplate> findServices();
	public void saveOrUpdate(ServiceTemplate service);
	public int deleteAllServices();
	public ServiceTemplate getServiceTemplate(String serviceTemplateId);
}
