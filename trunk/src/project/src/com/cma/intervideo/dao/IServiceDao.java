package com.cma.intervideo.dao;

import java.util.List;

import com.cma.intervideo.dao.util.DAO;
import com.cma.intervideo.pojo.ServiceTemplate;

public interface IServiceDao extends DAO<ServiceTemplate, String>{
	public List<ServiceTemplate> findServices();
	public ServiceTemplate getServiceTemplate(String serviceTemplateId);
	public void deleteServiceTemplatesByNewIds(List<String> newIds);
}
