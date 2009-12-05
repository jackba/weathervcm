package com.cma.intervideo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Session;

import com.cma.intervideo.constant.DataDictStatus;
import com.cma.intervideo.dao.util.AbstractDAO;
import com.cma.intervideo.pojo.ConfTemplate;
import com.cma.intervideo.pojo.ConfTemplateXUnit;
import com.cma.intervideo.pojo.Unit;
import com.cma.intervideo.util.PageHolder;
import com.cma.intervideo.util.ParamVo;

public class AbstractConfTemplateDao extends AbstractDAO<ConfTemplate, Integer> implements
		IConfTemplateDao {
	private final static Log logger = LogFactory.getLog(AbstractConfTemplateDao.class);

	public List<ConfTemplate> findConfTemplates(List<ParamVo> params, PageHolder ph) {
		String hql = "from ConfTemplate r where r.status="
				+ DataDictStatus.normalStatus;
		if (params != null)
		{
			for (int i = 0; i < params.size(); i++)
			{
				ParamVo vo = params.get(i);
				if (vo.getParamName().equals("confTemplateName"))
					hql += " and r.confTemplateName like '%" + vo.getParamValue() + "%'";

				if (vo.getParamName().equals("serviceTemplateId"))
					hql += " and r.serviceTemplateId='" + vo.getParamValue() + "'";
			}
		}
		if (ph != null && ph.isGetCount())
			ph.setResultSize(this.getCount(hql));

		hql += " order by r.createTime";
		List<ConfTemplate> cts = this.getHibernateTemplate().find(hql);
		logger.info("AbstractConftemplateDao.findConfTemplates(List<ParamVo> params, PageHolder ph) - return "
						+ (cts == null ? "0" : cts.size())
						+ " ConfTemplates for HQL: " + hql);
		return cts;
	}

	public List<ConfTemplate> findConfTemplatesByUserId(String userId) {
		if (userId == null)
			return null;
		
		String hql = "from ConfTemplate r where r.status = " + DataDictStatus.normalStatus;
		hql += " and r.userId = '" + userId + "'";
		hql += " order by r.createTime";
		List<ConfTemplate> cts = this.getHibernateTemplate().find(hql);
		logger.info("AbstractRoomDao.findRooms(String userId) - return "
				+ (cts == null ? "0" : cts.size())
				+ " Virtual Rooms for HQL: " + hql);
		return cts;
	}
	
	public List<ConfTemplate> findConfTemplatesByVirtualConfId(String virtualConfId)
	{
		if (virtualConfId == null)
			return null;
		
		String hql = "from ConfTemplate r where r.status = " + DataDictStatus.normalStatus;
		hql += " and r.virtualConfId = '" + virtualConfId + "'";
		hql += " order by r.createTime";
		List<ConfTemplate> cts = this.getHibernateTemplate().find(hql);
		logger.info("AbstractRoomDao.findRooms(String userId) - return "
				+ (cts == null ? "0" : cts.size())
				+ " Virtual Rooms for HQL: " + hql);
		return cts;
	}
	
	public void merge(ConfTemplate confTemplate) {
		getHibernateTemplate().merge(confTemplate);	
	}

	public void addConfTemplateUnit(Integer confTemplateId, Integer unitId){
		ConfTemplateXUnit confTemplateUnit = new ConfTemplateXUnit();
		confTemplateUnit.setConfTemplateId(confTemplateId);
		confTemplateUnit.setUnitId(unitId);
		this.getHibernateTemplate().save(confTemplateUnit);
		logger.info("Creating new ConfTemplateXUnit: " + confTemplateUnit);
	}
	
	public List<Unit> findUnitsByConfTemplateId(Integer confTemplateId, boolean selected){
		Session s = this.getSession();
		Connection conn = s.connection();
		List<Unit> result = new ArrayList<Unit>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try{
			if (selected)
				pstmt = conn.prepareStatement("select * from unit where unit_id in (select unit_id from conf_template_x_unit where conf_template_id=?)");
			else
				pstmt = conn.prepareStatement("select * from unit where unit_id not in (select unit_id from conf_template_x_unit where conf_template_id=?)");
			pstmt.setInt(1, confTemplateId);
			rs = pstmt.executeQuery();
			while(rs.next()){
				Unit unit = new Unit();
				unit.setUnitId(new Integer(rs.getInt("unit_id")));
				unit.setUnitName(rs.getString("unit_name"));
				unit.setPartyId(rs.getString("party_id"));
				unit.setDescription(rs.getString("description"));
				result.add(unit);
			}
		}catch(Exception e){
			logger.error(e.toString());
			return null;
		}finally{
			try{
				if(rs!=null)
					rs.close();
				if(pstmt!=null)
					pstmt.close();
			}catch(Exception ex){
				logger.error(ex.toString());
			}
		}
		return result;
	}

	public List<Unit> findAllUnits(){
		return this.getHibernateTemplate().find("from Unit unit");
	}

	public void deleteConfTemplateUnitsByConfTemplateId(Integer confTemplateId) {
		Session s = this.getSession();
		Connection conn = s.connection();
		PreparedStatement pstmt = null;
		try{
			pstmt = conn.prepareStatement("delete from conf_template_x_unit where conf_template_id=?");
			pstmt.setInt(1, confTemplateId);
			pstmt.executeUpdate();
		}catch(Exception e){
			logger.error(e.toString());
		}finally{
			try{
				if(pstmt!=null)
					pstmt.close();
			}catch(Exception ex){
				logger.error(ex.toString());
			}
		}
	}
}
