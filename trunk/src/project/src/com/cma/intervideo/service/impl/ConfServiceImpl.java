package com.cma.intervideo.service.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.cma.intervideo.dao.IConfDao;
import com.cma.intervideo.service.IConfService;

public class ConfServiceImpl implements IConfService{
	private static final Log logger = LogFactory.getLog(ConfServiceImpl.class);
	private IConfDao confDao;
	public void setConfDao(IConfDao confDao) {
		this.confDao = confDao;
	}
	public int deleteReserves(List<String> reserves){
		return reserves.size();
	}
}
