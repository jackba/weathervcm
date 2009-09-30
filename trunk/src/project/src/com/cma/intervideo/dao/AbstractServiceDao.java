package com.cma.intervideo.dao;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.cma.intervideo.dao.util.AbstractDAO;
import com.cma.intervideo.pojo.ServiceTemplate;
import com.cma.intervideo.util.PageHolder;
import com.cma.intervideo.util.ParamVo;

public class AbstractServiceDao extends AbstractDAO<ServiceTemplate, String>
		implements IServiceDao {
	private final static Log logger = LogFactory
			.getLog(AbstractServiceDao.class);

	public List<ServiceTemplate> findServices(List<ParamVo> params,
			PageHolder ph) {
		String hql = "from ServiceTemplate s order by s.serviceTemplateId";
		if (ph != null && ph.isGetCount())
			ph.setResultSize(getCount(hql));

		List<ServiceTemplate> lst = getHibernateTemplate().find(hql);

		logger.info("AbstractServiceDao.findServices return " + lst.size()
				+ " ServiceTemplate, hql = " + hql);

		return lst;
	}
	
	public ServiceTemplate getServiceTemplate(String serviceTemplateId) {
		return (ServiceTemplate)getHibernateTemplate().get(ServiceTemplate.class, serviceTemplateId);
	}
}
