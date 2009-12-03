package com.cma.intervideo.dao;

import java.util.List;

import com.cma.intervideo.dao.util.DAO;
import com.cma.intervideo.pojo.ConfTemplate;
import com.cma.intervideo.util.PageHolder;
import com.cma.intervideo.util.ParamVo;

public interface IConfTemplateDao extends DAO<ConfTemplate, String>{
	public List<ConfTemplate> findConfTemplates(List<ParamVo> params, PageHolder ph);
	public List<ConfTemplate> findConfTemplates(String userId);
}
