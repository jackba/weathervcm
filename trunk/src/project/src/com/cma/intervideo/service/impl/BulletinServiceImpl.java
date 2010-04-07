package com.cma.intervideo.service.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.cma.intervideo.dao.IBulletinDao;
import com.cma.intervideo.pojo.BulletinBoard;
import com.cma.intervideo.service.IBulletinService;
import com.cma.intervideo.util.PageHolder;
import com.cma.intervideo.util.ParamVo;

public class BulletinServiceImpl implements IBulletinService{
	private static Log logger = LogFactory.getLog(BulletinServiceImpl.class);
	private IBulletinDao bulletinDao;
	public void setBulletinDao(IBulletinDao bulletinDao) {
		this.bulletinDao = bulletinDao;
	}
	public void save(BulletinBoard bulletinBoard){
		bulletinDao.saveOrUpdate(bulletinBoard);
	}
	public List<BulletinBoard> findBulletin(List<ParamVo> params, PageHolder ph){
		return bulletinDao.findBulletin(params, ph);
	}
	public BulletinBoard getBulletinBoardById(int bulletinId){
		return bulletinDao.getObjectByID(bulletinId);
	}
	public int deleteBulletins(List<String> bulletins){
		for(int i=0;i<bulletins.size();i++){
			bulletinDao.removeObjectByID(Integer.parseInt(bulletins.get(i)));
		}
		return bulletins.size();
	}
	/**
	 * 返回第几条最新公告
	 * @param i
	 * @return
	 */
	public BulletinBoard getNews(int i){
		return bulletinDao.getNews(i);
	}
}
