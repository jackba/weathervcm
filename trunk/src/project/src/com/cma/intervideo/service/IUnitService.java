package com.cma.intervideo.service;

import java.util.List;

import com.cma.intervideo.pojo.Unit;
import com.cma.intervideo.util.PageHolder;
import com.cma.intervideo.util.ParamVo;

public interface IUnitService {
	public List<Unit> findAllUnits();
	public List<Unit> findUnits(List<ParamVo> params, PageHolder ph);
	public void saveOrUpdate(Unit unit) throws Exception;
	public Unit getUnitById(String unitId);
	public int deleteUnits(List<String> units);
}
