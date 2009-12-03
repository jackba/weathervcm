package com.cma.intervideo.dao;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.cma.intervideo.constant.DataDictStatus;
import com.cma.intervideo.dao.util.AbstractDAO;
import com.cma.intervideo.pojo.ConfTemplate;
import com.cma.intervideo.util.PageHolder;
import com.cma.intervideo.util.ParamVo;

public class AbstractConfTemplateDao extends AbstractDAO<ConfTemplate, String> implements
		IConfTemplateDao {
	private final static Log logger = LogFactory.getLog(AbstractConfTemplateDao.class);

	public List<ConfTemplate> findConfTemplates(List<ParamVo> params, PageHolder ph) {
		String hql = "from ConfTemplate r where r.status="
				+ DataDictStatus.normalStatus;
		if (params != null) {
			for (int i = 0; i < params.size(); i++) {
				ParamVo vo = params.get(i);
				if (vo.getParamName().equals("confTemplateName")) {
					hql += " and r.confTemplateName like '%" + vo.getParamValue()
							+ "%'";
				}
				if (vo.getParamName().equals("serviceTemplateId")) {
					hql += " and r.serviceTemplateId='" + vo.getParamValue()
							+ "'";
				}
			}
		}
		if (ph != null && ph.isGetCount()) {
			ph.setResultSize(this.getCount(hql));
		}
		hql += " order by r.createTime";
		List<ConfTemplate> cts = this.getHibernateTemplate().find(hql);
		logger.info("AbstractConftemplateDao.findConfTemplates(List<ParamVo> params, PageHolder ph) - return "
						+ (cts == null ? "0" : cts.size())
						+ " ConfTemplates for HQL: " + hql);
		return cts;
	}

	public List<ConfTemplate> findConfTemplates(String userId) {
		String hql = "from ConfTemplate r where r.status = "
				+ DataDictStatus.normalStatus;
		if (userId != null && userId.length() > 0)
			hql += " and r.userId = '" + userId + "'";
		hql += " order by r.createTime";
		List<ConfTemplate> cts = this.getHibernateTemplate().find(hql);
		logger.info("AbstractRoomDao.findRooms(String userId) - return "
				+ (cts == null ? "0" : cts.size())
				+ " Virtual Rooms for HQL: " + hql);
		return cts;
	}
}
