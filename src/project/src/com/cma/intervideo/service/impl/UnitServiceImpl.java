package com.cma.intervideo.service.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.cma.intervideo.dao.IUnitDao;
import com.cma.intervideo.pojo.Unit;
import com.cma.intervideo.service.IUnitService;

public class UnitServiceImpl implements IUnitService{
	private static Log logger = LogFactory.getLog(UnitServiceImpl.class);
	private IUnitDao unitDao;
	
	public void setUnitDao(IUnitDao unitDao) {
		this.unitDao = unitDao;
	}

	public List<Unit> findAllUnits(){
		return unitDao.findAllUnits();
	}
}
