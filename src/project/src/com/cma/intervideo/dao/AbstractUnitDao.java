package com.cma.intervideo.dao;

import java.util.List;

import com.cma.intervideo.dao.util.AbstractDAO;
import com.cma.intervideo.pojo.Unit;

public abstract class AbstractUnitDao extends AbstractDAO<Unit, Integer> implements IUnitDao{
	public List<Unit> findAllUnits(){
		return this.getHibernateTemplate().find("from Unit unit");
	}
}
