package com.cma.intervideo.dao;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.cma.intervideo.dao.util.AbstractDAO;
import com.cma.intervideo.pojo.ServiceTemplate;

public class AbstractServiceDao extends AbstractDAO<ServiceTemplate, String>
		implements IServiceDao {
	private final static Log logger = LogFactory
			.getLog(AbstractServiceDao.class);

	public List<ServiceTemplate> findServices() {
		String hql = "from ServiceTemplate s order by s.serviceTemplateId";
		List<ServiceTemplate> lst = getHibernateTemplate().find(hql);
		logger.info("AbstractServiceDao.findServices return " + lst.size()
				+ " ServiceTemplate, hql = " + hql);
		return lst;
	}

	public ServiceTemplate getServiceTemplate(String serviceTemplateId) {
		return (ServiceTemplate) getHibernateTemplate().get(
				ServiceTemplate.class, serviceTemplateId);
	}
}
