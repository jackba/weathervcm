package com.cma.intervideo.dao;

import java.util.List;

import com.cma.intervideo.dao.util.DAO;
import com.cma.intervideo.pojo.Unit;
import com.cma.intervideo.util.PageHolder;
import com.cma.intervideo.util.ParamVo;

public interface IUnitDao extends DAO<Unit, Integer>{
	public List<Unit> findAllUnits();
	public List<Unit> findUnits(List<ParamVo> params, PageHolder ph);
}
