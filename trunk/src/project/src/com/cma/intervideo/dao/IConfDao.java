package com.cma.intervideo.dao;

import java.util.List;

import com.cma.intervideo.dao.util.DAO;
import com.cma.intervideo.pojo.Conference;
import com.cma.intervideo.util.PageHolder;
import com.cma.intervideo.util.ParamVo;

public interface IConfDao extends DAO<Conference, Integer>{
	public List<Conference> findConfs(List<ParamVo> params, PageHolder ph);
}
