package com.cma.intervideo.dao;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.hibernate.Query;

import com.cma.intervideo.constant.DataDictStatus;
import com.cma.intervideo.dao.util.AbstractDAO;
import com.cma.intervideo.pojo.BulletinBoard;
import com.cma.intervideo.util.PageHolder;
import com.cma.intervideo.util.ParamVo;

public class AbstractBulletinDao extends AbstractDAO<BulletinBoard, Integer> implements IBulletinDao{
	public List<BulletinBoard> findBulletinByUserId(String userId){
		return this.getHibernateTemplate().find("from BulletinBoard b where b.userId=?",userId);
	}
	/**
	 * 返回第几条最新公告
	 * @param i
	 * @return
	 */
	public BulletinBoard getNews(int i){
		String hql = "from BulletinBoard b";
		Date d = Calendar.getInstance().getTime();
		String currentTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(d);
		hql += " where b.effectiveTime<='"+currentTime+"' and b.expiredTime>'"+currentTime+"'";
		int count = this.getCount(hql);
		if(count == 0){
			return null;
		}
		hql += " order by b.updateTime desc";
		Query query = this.getSession().createQuery(hql);
		query.setMaxResults(1);
		query.setFirstResult(i%count);
		List<BulletinBoard> l = query.list();
		if(l!=null && l.size()>0){
			return l.get(0);
		}else{
			return null;
		}
	}
	public List<BulletinBoard> findBulletin(List<ParamVo> params, PageHolder ph){
		String hql = null;
		String queryStr = "";
		if(params!=null){
			for(int i=0;i<params.size();i++){
				ParamVo vo = params.get(i);
				if(vo.getParamName().equals("title")){
					queryStr += " and bb.title like '%"+vo.getParamValue()+"%'";
				}
				if(vo.getParamName().equals("content")){
					queryStr += " and bb.content like '%"+vo.getParamValue()+"%'";
				}
				if(vo.getParamName().equals("startTime")){
					queryStr += " and bb.effectiveTime>='"+vo.getParamValue()+"'";
				}
				if(vo.getParamName().equals("endTime")){
					queryStr += " and bb.effectiveTime<'"+vo.getParamValue()+"'";
				}
			}
		}else{
			Date d = Calendar.getInstance().getTime();
			String currentTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(d);
			queryStr += " and bb.effectiveTime<='"+currentTime+"' and bb.expiredTime>'"+currentTime+"'";
		}
		if(ph!=null && ph.isGetCount()){
			ph.setResultSize(this.getCount("from BulletinBoard bb where bb.status="+DataDictStatus.bulletinNormalStatus+queryStr));
		}
		if(params!=null){
			hql = "select bb,u.loginId from BulletinBoard bb,User u where bb.status="+DataDictStatus.bulletinNormalStatus+" and u.userId=bb.userId";
			hql += queryStr+" order by bb.effectiveTime desc";
			List l = this.find(hql, ph);
			List result = new ArrayList();
			if(l!=null){
				for(int i=0;i<l.size();i++){
					Object[] o = (Object[])l.get(i);
					BulletinBoard bb = (BulletinBoard)o[0];
					bb.setLoginId((String)o[1]);
					result.add(bb);
				}
			}
			return result;
		}else{
			hql = "from BulletinBoard bb where bb.status="+DataDictStatus.bulletinNormalStatus;
			hql += queryStr;
			hql += " order by bb.effectiveTime desc";
			return this.find(hql, ph);
		}
	}
}
