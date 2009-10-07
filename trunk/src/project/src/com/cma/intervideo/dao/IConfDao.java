package com.cma.intervideo.dao;

import java.util.List;

import com.cma.intervideo.dao.util.DAO;
import com.cma.intervideo.pojo.Conference;
import com.cma.intervideo.pojo.Unit;
import com.cma.intervideo.util.PageHolder;
import com.cma.intervideo.util.ParamVo;

public interface IConfDao extends DAO<Conference, Integer>{
	public List<Conference> findConfs(List<ParamVo> params, PageHolder ph);
	public void merge(Conference conf);
	public void addConfUnit(Integer confId, Integer unitId);
	public List<Unit> findUnitsByConfId(String confId, boolean selected);
	public List<Unit> findAllUnits();
	public void deleteConfUnitsByConfId(Integer confId);
	public void addConfParty(Integer confId, String partyId);
	public void deleteConfPartiesByConfId(Integer confId);
}
