package com.cma.intervideo.dao;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.cma.intervideo.dao.mysql.ConfDaoImpl;
import com.cma.intervideo.dao.util.AbstractDAO;
import com.cma.intervideo.pojo.Conference;
import com.cma.intervideo.util.PageHolder;
import com.cma.intervideo.util.ParamVo;

public abstract class AbstractConfDao extends AbstractDAO<Conference, Integer> implements IConfDao{
	private static Log logger = LogFactory.getLog(AbstractConfDao.class);
	public List<Conference> findConfs(List<ParamVo> params, PageHolder ph){
		String hql = "from Conference conference where 1=1";
		if(ph.isGetCount()){
			ph.setResultSize(this.getCount(hql));
		}
		return this.find(hql, ph);
	}
}
