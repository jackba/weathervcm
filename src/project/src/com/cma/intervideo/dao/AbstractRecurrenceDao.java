package com.cma.intervideo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Session;

import com.cma.intervideo.dao.util.AbstractDAO;
import com.cma.intervideo.pojo.RecurrenceUnit;
import com.cma.intervideo.pojo.RecurringMeetingInfo;
import com.cma.intervideo.pojo.Unit;
import com.cma.intervideo.util.PageHolder;
import com.cma.intervideo.util.ParamVo;

public abstract class AbstractRecurrenceDao extends AbstractDAO<RecurringMeetingInfo, Integer> implements IRecurrenceDao {
	
	private static Log logger = LogFactory.getLog(AbstractRecurrenceDao.class);
	
	public void addRecurrenceUnit(Integer recurrenceId, Integer unitId) {
		RecurrenceUnit recurrUnit = new RecurrenceUnit();
		recurrUnit.setRecurrenceId(recurrenceId);
		recurrUnit.setUnitId(unitId);
		this.getHibernateTemplate().save(recurrUnit);
		logger.info("Added successfully Unit: unitId = " + unitId + " for the recurrence: recurrenceId = " + recurrenceId + ".");
	}
	
	public void deleteConferenceByRecurrenceId(Integer recurrenceId) {
		Session s = this.getSession();
		Connection conn = s.connection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try{
			pstmt = conn.prepareStatement("delete from RecurringMeetingInfo r where status="+RecurringMeetingInfo.status_upcoming + " and recurrence_id=?)");
			pstmt.setInt(1, recurrenceId);
			pstmt.executeUpdate();
		}catch(Exception e){
			logger.error(e.toString());
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
	}
	
	public List<RecurringMeetingInfo> findRecurrences(List<ParamVo> params, PageHolder ph){
		String hql = "from RecurringMeetingInfo r where status!="+RecurringMeetingInfo.status_cancel;
		if(params!=null){
			for(int i=0;i<params.size();i++){
				ParamVo vo = params.get(i);
				if(vo.getParamName().equals("subject")){
					hql += " and r.subject like '%"+vo.getParamValue()+"%'";
				}
				if(vo.getParamName().equals("serviceTemplate")){
					hql += " and r.serviceTemplate='"+vo.getParamValue()+"'";
				}
				if(vo.getParamName().equals("userId")){
					hql += " and r.userId="+vo.getParamValue();
				}
				if(vo.getParamName().equals("virtualConfId")){
					hql += " and r.virtualConfId='"+vo.getParamValue()+"'";
				}
				if(vo.getParamName().equals("status")){
					Short tmp = (Short)vo.getParamValue();
					hql += " and r.status="+tmp;
//					String tmp = (String)vo.getParamValue();
//					String[] values = tmp.split(",");
//					if (values != null) {
//						if (values.length == 1)
//							hql += " and r.status="+vo.getParamValue();
//						else {
//							hql += " and (r.status=" + values[0];
//							for (int ii = 1; ii < values.length; ii++)
//								hql += " or r.status=" + values[ii];
//							hql += ")";
//						}
//					}
				}
				if(vo.getParamName().equals("startTime")){
					hql += " and r.startTime>="+((Date)vo.getParamValue()).getTime();
				}
				if(vo.getParamName().equals("endTime")){
					hql += " and r.startTime<"+((Date)vo.getParamValue()).getTime();
				}
			}
		}
		hql += " order by r.startTime DESC";
		if(ph.isGetCount()){
			ph.setResultSize(this.getCount(hql));
		}
		return this.find(hql, ph);
	}
	
	public List<Unit> findUnitsByRecurrenceId(String recurrenceId, boolean selected){
		Session s = this.getSession();
		Connection conn = s.connection();
		List<Unit> result = new ArrayList<Unit>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try{
			if (selected)
				pstmt = conn.prepareStatement("select * from unit where unit_id in (select unit_id from recurrence_unit where recurrence_id=?)");
			else
				pstmt = conn.prepareStatement("select * from unit where unit_id not in (select unit_id from recurrence_unit where recurrence_id=?)");
			pstmt.setString(1, recurrenceId);
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
}
