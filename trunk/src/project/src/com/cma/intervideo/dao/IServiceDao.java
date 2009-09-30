package com.cma.intervideo.dao;

import java.util.List;

import com.cma.intervideo.dao.util.DAO;
import com.cma.intervideo.pojo.ServiceTemplate;
import com.cma.intervideo.util.PageHolder;
import com.cma.intervideo.util.ParamVo;

public interface IServiceDao extends DAO<ServiceTemplate, String>{
	public List<ServiceTemplate> findServices(List<ParamVo> params, PageHolder ph);
	public ServiceTemplate getServiceTemplate(String serviceTemplateId);
}
