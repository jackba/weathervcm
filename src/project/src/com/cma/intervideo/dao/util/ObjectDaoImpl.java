/**
 * 2009-6-17
 */
package com.cma.intervideo.dao.util;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.cma.intervideo.util.list.PageConditions;
import com.cma.intervideo.util.list.QueryParam;

/**
 * @author geyb
 */
@SuppressWarnings("unchecked")
public class ObjectDaoImpl extends HibernateDaoSupport implements ObjectDao {

	/* (non-Javadoc)
	 * @see com.asiainfo.idc.jbpm.dao.ObjectDao#getAll(java.lang.Class)
	 */
	public <T> List<T> getAll(Class<T> type) {
		return (List<T>)this.getHibernateTemplate().loadAll(type);
	}

	/* (non-Javadoc)
	 * @see com.asiainfo.idc.jbpm.dao.ObjectDao#getById(java.lang.Class, java.io.Serializable)
	 */
	public <T> T getById(Class<T> type, Serializable id) {
		return (T)this.getHibernateTemplate().get(type, id);
	}

	/* (non-Javadoc)
	 * @see com.asiainfo.idc.jbpm.dao.ObjectDao#remove(java.lang.Class, java.io.Serializable)
	 */
	public <T> void remove(Class<T> type, Serializable id) {
		this.getHibernateTemplate().delete(this.getById(type, id));
	}

	/* (non-Javadoc)
	 * @see com.asiainfo.idc.jbpm.dao.ObjectDao#remove(java.lang.Object)
	 */
	public void remove(Object obj) {
		this.getHibernateTemplate().delete(obj);
	}

	/* (non-Javadoc)
	 * @see com.asiainfo.idc.jbpm.dao.ObjectDao#merge(java.lang.Object)
	 */
	public Object merge(Object obj) {
		return this.getHibernateTemplate().merge(obj);
	}

	/* (non-Javadoc)
	 * @see com.asiainfo.idc.jbpm.dao.ObjectDao#save(java.lang.Object)
	 */
	public void save(Object obj) {
		this.getHibernateTemplate().saveOrUpdate(obj);
	}
	
	/* (non-Javadoc)
	 * @see com.asiainfo.idc.jbpm.dao.ObjectDao#initialize(java.lang.Object)
	 */
	public void initialize(Object obj) {
		this.getHibernateTemplate().initialize(obj);
	}

	/* (non-Javadoc)
	 * @see com.asiainfo.idc.jbpm.dao.ObjectDao#removeAll(java.lang.Class)
	 */
	public <T> void removeAll(Class<T> type) {
		this.getHibernateTemplate().bulkUpdate("delete from " + type.getName());
	}

	/* (non-Javadoc)
	 * @see com.asiainfo.adms.dao.util.ObjectDao#getAll(java.lang.Class, java.lang.String[], java.lang.Object[])
	 */
	public <T> List<T> get(Class<T> type, String param, Object value) {
		return this.getHibernateTemplate().find("from " + type.getSimpleName() + " t where t." + param + "=?", value);
	}

	/* (non-Javadoc)
	 * @see com.asiainfo.adms.dao.util.ObjectDao#getByCriteria(java.lang.Class, com.asiainfo.adms.util.PageConditions)
	 */
	public <T> List<T> getByCriteria(Class<T> type, PageConditions conditions) {
		List<Criterion> critList = QueryHelper.createQuery(conditions.getParams());
		DetachedCriteria criteria = QueryHelper.createCriteria(
				type, conditions, critList);
		return this.getHibernateTemplate().findByCriteria(criteria, 
				conditions.getStart(), conditions.getLimit());
	}

	/* (non-Javadoc)
	 * @see com.asiainfo.adms.dao.util.ObjectDao#getTotalCount(java.lang.Class, java.util.List)
	 */
	public <T> int getTotalCount(Class<T> type, List<QueryParam> list) {
		List<Criterion> critList = QueryHelper.createQuery(list);
		DetachedCriteria criteria = QueryHelper.createCriteria(
				type, critList);
		return (Integer)this.getHibernateTemplate().findByCriteria(
				criteria.setProjection(Projections.rowCount())).get(0);
	}

	/* (non-Javadoc)
	 * @see com.asiainfo.adms.dao.util.ObjectDao#get(java.lang.Class, com.asiainfo.adms.util.PageConditions)
	 */
	public <T> List<T> get(final Class<T> type, final PageConditions conditions) {
		return this.getHibernateTemplate().executeFind(new HibernateCallback() {
		    public Object doInHibernate(Session session)
		    		throws HibernateException, SQLException {
		    	Query query = session.createQuery(QueryHelper.createHql(type, conditions));
		    	for (QueryParam param : conditions.getParams()) {
		    		String name = param.getParamName().replaceAll("\\.", "") + param.getType();
		    		if (param.getType() == QueryParam.PARAM_TYPE_LIKE) {
		    			query.setParameter(name, "%" + param.getParamValue() + "%");
		    		} else if (param.getType() == QueryParam.PARAM_TYPE_LIKE_RIGHT) {
		    			query.setParameter(name, param.getParamValue() + "%");
		    		} else {
		    			query.setParameter(name, param.getParamValue());
		    		}
		    	}
		    	query.setMaxResults(conditions.getLimit());
		    	query.setFirstResult(conditions.getStart());
		    	return query.list();
		    }
		});
	}
	
	/* (non-Javadoc)
	 * @see com.asiainfo.adms.dao.util.ObjectDao#getTotalCount(java.lang.Class, com.asiainfo.adms.util.PageConditions)
	 */
	public <T> int getTotalCount(final Class<T> type, final PageConditions conditions) {
		Long result = (Long)this.getHibernateTemplate().executeFind(new HibernateCallback() {
		    public Object doInHibernate(Session session)
		    		throws HibernateException, SQLException {
		    	Query query = session.createQuery(QueryHelper.createCountHql(type, conditions));
		    	for (QueryParam param : conditions.getParams()) {
		    		String name = param.getParamName().replaceAll("\\.", "") + param.getType();
		    		if (param.getType() == QueryParam.PARAM_TYPE_LIKE) {
		    			query.setParameter(name, "%" + param.getParamValue() + "%");
		    		} else if (param.getType() == QueryParam.PARAM_TYPE_LIKE_RIGHT) {
		    			query.setParameter(name, param.getParamValue() + "%");
		    		} else {
		    			query.setParameter(name, param.getParamValue());
		    		}
		    	}
		    	return query.list();
		    }
		}).get(0);
		return result.intValue();
	}
	
	/* (non-Javadoc)
	 * @see com.asiainfo.adms.dao.util.ObjectDao#updateById(java.lang.Class, java.io.Serializable, java.lang.String, java.lang.Object)
	 */
	public <T> boolean updateById(Class<T> type, Serializable id, String param, Object value) {
		return this.getHibernateTemplate().bulkUpdate("update " + type.getSimpleName() + " t " +
				"set t." + param + "=? where t.id=?", new Object[] {value, id}) > 0;
	}

	/* (non-Javadoc)
	 * @see com.asiainfo.adms.dao.util.ObjectDao#getTotalCount(java.lang.String, com.asiainfo.adms.util.PageConditions)
	 */
	public int getTotalCount(final String countQuery, final PageConditions conditions) {
		Long result = (Long) this.getHibernateTemplate().find(
				QueryHelper.addQueryParam(countQuery, conditions),
				QueryHelper.getObjectParams(conditions))
				.get(0);
		return result.intValue();
	}

	/* (non-Javadoc)
	 * @see com.asiainfo.adms.dao.util.ObjectDao#get(java.lang.Class, com.asiainfo.adms.util.PageConditions)
	 */
	public List<?> get(final String query, final PageConditions conditions) {
		return this.getHibernateTemplate().executeFind(new HibernateCallback() {
		    public Object doInHibernate(Session session)
		    		throws HibernateException, SQLException {
		    	Query queryObject = session.createQuery(QueryHelper.createHql(query, conditions));
				Object[] values = QueryHelper.getObjectParams(conditions);
				if (values != null) {
					for (int i = 0; i < values.length; i++) {
						queryObject.setParameter(i, values[i]);
					}
				}
		    	
				queryObject.setMaxResults(conditions.getLimit());
				queryObject.setFirstResult(conditions.getStart());
		    	return queryObject.list();
		    }
		});
	}
}
