package com.cma.intervideo.dao;

import java.util.List;

import com.cma.intervideo.dao.util.DAO;
import com.cma.intervideo.pojo.ConfTemplate;
import com.cma.intervideo.pojo.Unit;
import com.cma.intervideo.util.PageHolder;
import com.cma.intervideo.util.ParamVo;

public interface IConfTemplateDao extends DAO<ConfTemplate, Integer>{
	public List<ConfTemplate> findConfTemplates(List<ParamVo> params, PageHolder ph);
	public List<ConfTemplate> findConfTemplatesByUserId(String userId);
	public List<ConfTemplate> findConfTemplatesByVirtualConfId(String virtualConfId);
	public void merge(ConfTemplate confTemplate);
	public void addConfTemplateUnit(Integer confTemplateId, Integer unitId);
	public List<Unit> findUnitsByConfTemplateId(Integer confTemplateId, boolean selected);
	public List<Unit> findAllUnits();
	public void deleteConfTemplateUnitsByConfTemplateId(Integer confTemplateId);
}
