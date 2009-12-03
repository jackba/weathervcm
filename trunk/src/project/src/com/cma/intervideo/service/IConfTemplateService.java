package com.cma.intervideo.service;

import java.util.List;

import com.cma.intervideo.pojo.ConfTemplate;
import com.cma.intervideo.util.PageHolder;
import com.cma.intervideo.util.ParamVo;

public interface IConfTemplateService {
	public List<ConfTemplate> findConfTemplates(List<ParamVo> params, PageHolder ph);
	public void saveOrUpdate(ConfTemplate ct) throws Exception;
	public ConfTemplate getConfTemplateById(String confTemplateId);
	public List<ConfTemplate> findConfTemplates(String userId);
}
