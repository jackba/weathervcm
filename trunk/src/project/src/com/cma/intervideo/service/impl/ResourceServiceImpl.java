package com.cma.intervideo.service.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.cma.intervideo.dao.IResourceDao;
import com.cma.intervideo.service.IResourceService;

public class ResourceServiceImpl implements IResourceService{
	private static Log logger = LogFactory.getLog(ResourceServiceImpl.class);
	private IResourceDao resourceDao;
	public void setResourceDao(IResourceDao resourceDao) {
		this.resourceDao = resourceDao;
	}
	
}
