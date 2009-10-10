package com.cma.intervideo.dao.util;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.cma.intervideo.util.PageHolder;

public class BaseDao extends HibernateDaoSupport{
	private static final Log logger = LogFactory.getLog(BaseDao.class);

	/**
	 * 分页支持的查询
	 * 
	 * @param hql
	 * @param pageHolder
	 * @return
	 */
	public List find(String hql, PageHolder pageHolder) {
		//logger.debug("Find HQL:" + hql);
		Session session = this.getSession();
		Query query = session.createQuery(hql);
		if(pageHolder!=null){
			query.setMaxResults(pageHolder.getPageSize());
			query.setFirstResult(pageHolder.getFirstIndex());
		}
		return query.list();

	}

	/**
	 * 查询所有记录总数
	 * 
	 * @param hql
	 * @return
	 */
	public int getCount(String hql) {
		int amount = 0;

		hql = "select count(*) " + hql;
//		logger.debug("GetCount HQL:" + hql);
		Query query = this.getSession().createQuery(hql);
		if (!query.list().isEmpty()) {
			// amount = ((Integer) query.list().get(0)).intValue();
			amount = ((Integer) query.iterate().next()).intValue();
		}
		return amount;
	}
}
