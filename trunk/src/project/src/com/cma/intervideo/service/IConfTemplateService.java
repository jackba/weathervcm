package com.cma.intervideo.service;

import java.util.List;

import com.cma.intervideo.pojo.ConfTemplate;
import com.cma.intervideo.pojo.Unit;
import com.cma.intervideo.util.PageHolder;
import com.cma.intervideo.util.ParamVo;

public interface IConfTemplateService {
	public List<ConfTemplate> findConfTemplates(List<ParamVo> params, PageHolder ph);
	public void createConfTemplate(ConfTemplate confTemplate, String[] units) throws Exception;
	public void modifyConfTemplate(ConfTemplate confTemplate, String[] units) throws Exception;
	public ConfTemplate getConfTemplateById(String ConfTemplateId);
	public List<ConfTemplate> findConfTemplatesByVirtualConfId(String virtualConfId);
	public List<ConfTemplate> findConfTemplatesByUserId(String userId);
	public List<Unit> findUnitsByConfTemplateId(String confTempateId, boolean selected);
	public List<Unit> findAllUnits();
}
