package com.cma.intervideo.dao;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.cma.intervideo.dao.util.AbstractDAO;
import com.cma.intervideo.pojo.Unit;
import com.cma.intervideo.util.PageHolder;
import com.cma.intervideo.util.ParamVo;

public class AbstractUnitDao extends AbstractDAO<Unit, Integer> implements
		IUnitDao {
	private final static Log logger = LogFactory.getLog(AbstractRoomDao.class);

	public List<Unit> findUnits(List<ParamVo> params, PageHolder ph) {
		String hql = "from Unit unit where 1=1 ";
		if (params != null) {
			for (int i = 0; i < params.size(); i++) {
				ParamVo vo = params.get(i);
				if (vo.getParamName().equals("unitName")) {
					hql += " and unit.unitName like '%" + vo.getParamValue()
							+ "%'";
				}
				if (vo.getParamName().equals("partyId")) {
					hql += " and unit.partyId='" + vo.getParamValue() + "'";
				}
			}
		}
		if (ph != null && ph.isGetCount()) {
			ph.setResultSize(this.getCount(hql));
		}
		return this.getHibernateTemplate()
				.find(hql + " order by unit.unitName");
	}

	public List<Unit> findAllUnits() {
		return this.getHibernateTemplate().find("from Unit unit");
	}
}
