package com.cma.intervideo.service;

import java.util.List;

import com.cma.intervideo.pojo.ServiceTemplate;
import com.cma.intervideo.util.PageHolder;
import com.cma.intervideo.util.ParamVo;

public interface IServiceService {
	public List<ServiceTemplate> findServices(List<ParamVo> params, PageHolder ph);
	public void saveOrUpdate(ServiceTemplate service);
	public int deleteAllServices();
	public ServiceTemplate getServiceTemplate(String serviceTemplateId);
}
