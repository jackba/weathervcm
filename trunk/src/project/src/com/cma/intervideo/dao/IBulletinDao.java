package com.cma.intervideo.dao;

import java.util.List;

import com.cma.intervideo.dao.util.DAO;
import com.cma.intervideo.pojo.BulletinBoard;
import com.cma.intervideo.util.PageHolder;
import com.cma.intervideo.util.ParamVo;

public interface IBulletinDao extends DAO<BulletinBoard, Integer>{
	public List<BulletinBoard> findBulletinByUserId(String userId);
	public List<BulletinBoard> findBulletin(List<ParamVo> params, PageHolder ph);
}
