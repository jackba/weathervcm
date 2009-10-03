package com.cma.intervideo.dao;

import java.util.List;

import com.cma.intervideo.dao.util.DAO;
import com.cma.intervideo.pojo.Unit;

public interface IUnitDao extends DAO<Unit, Integer>{
	public List<Unit> findAllUnits();
}
