package com.cma.intervideo.service;

import java.util.List;
import java.util.Map;

import com.cma.intervideo.pojo.ServiceTemplate;

public interface IServiceService {
	public List<ServiceTemplate> findServices();
	public List<ServiceTemplate> findServicesByClassification();
	public Map<String, List<ServiceTemplate>> classifyServices();
	public void saveOrUpdate(ServiceTemplate service);
	public ServiceTemplate getServiceTemplate(String serviceTemplateId);
	public void deleteServiceTemplatesByNewIds(List<String> newIds);
}
