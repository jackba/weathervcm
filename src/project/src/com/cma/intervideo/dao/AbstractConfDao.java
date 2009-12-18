package com.cma.intervideo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Session;

import com.cma.intervideo.dao.util.AbstractDAO;
import com.cma.intervideo.pojo.ConfParty;
import com.cma.intervideo.pojo.ConfUnit;
import com.cma.intervideo.pojo.Conference;
import com.cma.intervideo.pojo.FieldDesc;
import com.cma.intervideo.pojo.Unit;
import com.cma.intervideo.util.PageHolder;
import com.cma.intervideo.util.ParamVo;

public abstract class AbstractConfDao extends AbstractDAO<Conference, Integer> implements IConfDao{
	private static Log logger = LogFactory.getLog(AbstractConfDao.class);
	public List<Conference> findConfs(List<ParamVo> params, PageHolder ph){
		String hql = "from Conference conference where status!="+Conference.status_cancel;
		if(params!=null){
			for(int i=0;i<params.size();i++){
				ParamVo vo = params.get(i);
				if(vo.getParamName().equals("subject")){
					hql += " and conference.subject like '%"+vo.getParamValue()+"%'";
				}
				if(vo.getParamName().equals("serviceTemplate")){
					hql += " and conference.serviceTemplate='"+vo.getParamValue()+"'";
				}
				if(vo.getParamName().equals("userId")){
					hql += " and conference.userId="+vo.getParamValue();
				}
				if(vo.getParamName().equals("dialableNumber")){
					hql += " and conference.dialableNumber='"+vo.getParamValue()+"'";
				}
				if(vo.getParamName().equals("status")){
					String tmp = (String)vo.getParamValue();
					String[] values = tmp.split(",");
					if (values != null) {
						if (values.length == 1)
							hql += " and conference.status="+vo.getParamValue();
						else {
							hql += " and (conference.status=" + values[0];
							for (int ii = 1; ii < values.length; ii++)
								hql += " or conference.status=" + values[ii];
							hql += ")";
						}
					}
				}
				if(vo.getParamName().equals("startTime")){
					hql += " and conference.startTime>="+((Date)vo.getParamValue()).getTime();
				}
				if(vo.getParamName().equals("endTime")){
					hql += " and conference.startTime<"+((Date)vo.getParamValue()).getTime();
				}
			}
		}
		hql += " order by conference.startTime DESC";
		if(ph.isGetCount()){
			ph.setResultSize(this.getCount(hql));
		}
		return this.find(hql, ph);
	}
	
	public void merge(Conference conf) {
		getHibernateTemplate().merge(conf);	
	}
	
	public void addConfUnit(Integer confId, Integer unitId){
		ConfUnit confUnit = new ConfUnit();
		confUnit.setConferenceId(confId);
		confUnit.setUnitId(unitId);
		this.getHibernateTemplate().save(confUnit);
		logger.info("Added successfully Unit: unitId = " + unitId + " for the conference: conferenceId = " + confId + ".");
	}
	
	public void addConfParty(Integer confId, String partyId){
		ConfParty confParty = new ConfParty();
		confParty.setPartyId(partyId);
		confParty.setConferenceId(confId);
		this.getHibernateTemplate().save(confParty);
		logger.info("Added successfully Party: partyId = " + partyId + " for the conference: conferenceId = " + confId + ".");
	}
	
	public List<Unit> findUnitsByConfId(String confId, boolean selected){
		Session s = this.getSession();
		Connection conn = s.connection();
		List<Unit> result = new ArrayList<Unit>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try{
			if (selected)
				pstmt = conn.prepareStatement("select * from unit where unit_id in (select unit_id from conf_unit where conference_id=?)");
			else
				pstmt = conn.prepareStatement("select * from unit where unit_id not in (select unit_id from conf_unit where conference_id=?)");
			pstmt.setString(1, confId);
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
	
	public void deleteConfUnitsByConfId(Integer confId){
		Session s = this.getSession();
		Connection conn = s.connection();
		PreparedStatement pstmt = null;
		try{
			pstmt = conn.prepareStatement("delete from conf_unit where conference_id=?");
			pstmt.setInt(1, confId);
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
	public void deleteConfPartiesByConfId(Integer confId){
		Session s = this.getSession();
		Connection conn = s.connection();
		PreparedStatement pstmt = null;
		try{
			
		}catch(Exception e){
			logger.error(e.toString());
		}finally{
			try{
				pstmt = conn.prepareStatement("delete from conf_party where conference_id=?");
				pstmt.setInt(1, confId);
				pstmt.executeUpdate();
			}catch(Exception ex){
				logger.error(ex.toString());
			}
		}
	}
	public Conference findConfByRadConfId(String radConferenceId){
		List l = this.getHibernateTemplate().find("from Conference c where c.radConferenceId=?",radConferenceId);
		if(l!=null && l.size()>0){
			return (Conference)l.get(0);
		}else
			return null;
	}
	/**
	 * 查询直到预约的会议结束时间仍然未收到会议开始和结束消息的会议
	 * @return
	 */
	public List<Conference> findAbnormalConfs(){
		String hql = "from Conference c where c.status="+Conference.status_upcoming;
		hql += " and c.startTime+c.timeLong*60000<"+Calendar.getInstance().getTimeInMillis();
		return this.getHibernateTemplate().find(hql);
	}
	/**
	 * 查询超长的会议
	 * @param maxConfPeriod
	 * @return
	 */
	public List<Conference> findTooLongConf(int maxConfPeriod){
		String hql = "from Conference c where c.status="+Conference.status_ongoing;
		hql += " and c.startTime+c.timeLong*60000<"+Calendar.getInstance().getTimeInMillis();
		hql += " and c.startTime+"+maxConfPeriod*60*60000+"<"+Calendar.getInstance().getTimeInMillis();
		return this.getHibernateTemplate().find(hql);
	}
	/**
	 * 查找会议类型
	 * @return
	 */
	public List<FieldDesc> findConfTypes(){
		return this.getHibernateTemplate().find("from FieldDesc f where f.id.tableName='conference' and f.id.fieldName='conf_type'");
	}
	
	public FieldDesc getConfType(short fieldValue){
		List l = this.getHibernateTemplate().find("from FieldDesc f where f.id.tableName='conference' and f.id.fieldName='conf_type' and f.id.fieldValue=?",(int)fieldValue);
		if(l!=null && l.size()>0){
			return (FieldDesc)l.get(0);
		}else{
			return null;
		}
	}
	
}
