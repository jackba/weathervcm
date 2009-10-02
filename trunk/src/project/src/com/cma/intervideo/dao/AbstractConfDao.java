package com.cma.intervideo.dao;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.cma.intervideo.dao.util.AbstractDAO;
import com.cma.intervideo.pojo.Conference;

public abstract class AbstractConfDao extends AbstractDAO<Integer, Conference> implements IConfDao{
	private Log logger = LogFactory.getLog(AbstractConfDao.class);
}
