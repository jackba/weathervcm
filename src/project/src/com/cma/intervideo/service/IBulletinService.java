package com.cma.intervideo.service;

import java.util.List;

import com.cma.intervideo.pojo.BulletinBoard;
import com.cma.intervideo.util.PageHolder;
import com.cma.intervideo.util.ParamVo;

public interface IBulletinService {
	public void save(BulletinBoard bulletinBoard);
	public List<BulletinBoard> findBulletin(List<ParamVo> params, PageHolder ph);
	public BulletinBoard getBulletinBoardById(int bulletinId);
}
