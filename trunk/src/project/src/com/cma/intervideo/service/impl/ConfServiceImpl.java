package com.cma.intervideo.service.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.cma.intervideo.dao.IConfDao;
import com.cma.intervideo.pojo.Conference;
import com.cma.intervideo.service.IConfService;
import com.cma.intervideo.util.PageHolder;
import com.cma.intervideo.util.ParamVo;

public class ConfServiceImpl implements IConfService{
	private static final Log logger = LogFactory.getLog(ConfServiceImpl.class);
	private IConfDao confDao;
	public void setConfDao(IConfDao confDao) {
		this.confDao = confDao;
	}
	public int deleteReserves(List<String> reserves){
		return reserves.size();
	}
	public List<Conference> findConfs(List<ParamVo> params, PageHolder ph){
		return confDao.findConfs(params, ph);
	}
	public void saveOrUpdate(Conference conf){
		confDao.saveOrUpdate(conf);
	}
}
