package com.cma.intervideo.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.cma.intervideo.dao.util.BaseDao;
import com.cma.intervideo.pojo.Conference;
import com.cma.intervideo.vo.ConfNumStatVo;
import com.cma.intervideo.vo.ConfTimeStatVo;
import com.cma.intervideo.vo.ConfTypeTimeStatVo;
import com.cma.intervideo.vo.UnitTimeStatVo;
import com.cma.intervideo.vo.UserReserveStatVo;

public class AbstractStatDao extends BaseDao implements IStatDao{
	private static final Log logger = LogFactory.getLog(AbstractStatDao.class);
	/**
	 * 统计用户使用次数排行，给出前20名
	 * @return
	 */
	public List<UserReserveStatVo> statUserReserve(){
		Connection conn = this.getSession().connection();
		Statement stmt = null;
		ResultSet rs = null;
		try{
			stmt = conn.createStatement();
			rs = stmt.executeQuery("select count(*) as num,u.user_id,u.user_name from user u,conference c where c.user_id=u.user_id group by u.user_id order by num desc limit 20");
			List<UserReserveStatVo> l = new ArrayList<UserReserveStatVo>();
			//显示长条的长度
			int length = 400;
			int firstNum = 1;
			while(rs.next()){
				UserReserveStatVo vo = new UserReserveStatVo();
				if(l.size()==0){
					firstNum = rs.getInt("num");
					vo.setLength(length);
				}else{
					vo.setLength(Math.round(rs.getInt("num")*length/(float)firstNum));
				}
				vo.setUserName(rs.getString("user_name"));
				vo.setNumber(rs.getInt("num"));
				vo.setIndex(l.size()+1);
				l.add(vo);
			}
			return l;
		}catch(Exception e){
			logger.error(e.toString());
			return null;
		}
	}
	
	public List<UserReserveStatVo> statUserReserve(String startDate, String endDate){
		Connection conn = this.getSession().connection();
		Statement stmt = null;
		ResultSet rs = null;
		try{
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			Date sd = df.parse(startDate);
			Date ed = df.parse(endDate);
			Calendar ec = Calendar.getInstance();
			ec.setTime(ed);
			ec.add(Calendar.DATE, 1);
			ed = ec.getTime();
			stmt = conn.createStatement();
			String strSQL = "select count(*) as num,u.user_id,u.user_name from user u,conference c" +
				" where c.user_id=u.user_id" +
				" and c.create_time>='" + startDate + "'" +
				" and c.create_time<='" + df.format(ed) + 
				" group by u.user_id order by num desc limit 20";
			rs = stmt.executeQuery(strSQL);
			List<UserReserveStatVo> l = new ArrayList<UserReserveStatVo>();
			//显示长条的长度
			int length = 400;
			int firstNum = 1;
			while(rs.next()){
				UserReserveStatVo vo = new UserReserveStatVo();
				if(l.size()==0){
					firstNum = rs.getInt("num");
					vo.setLength(length);
				}else{
					vo.setLength(Math.round(rs.getInt("num")*length/(float)firstNum));
				}
				vo.setUserName(rs.getString("user_name"));
				vo.setNumber(rs.getInt("num"));
				vo.setIndex(l.size()+1);
				l.add(vo);
			}
			return l;
		}catch(Exception e){
			logger.error(e.toString());
			return null;
		}
	}
	
	public List<ConfNumStatVo> statConfNum(String startDate, String endDate){
		Connection conn = this.getSession().connection();
		Statement stmt = null;
		ResultSet rs = null;
		try{
			
			stmt = conn.createStatement();
			String strSQL = "select count(*) as num,u.unit_name,f.field_desc from conference c, field_desc f, unit u" +
				" where c.main_unit=u.unit_id and c.conf_type=f.field_value and (c.status="+Conference.status_ongoing+" or c.status="+Conference.status_history+")";
			if(startDate!=null && !"".equals(startDate)){	
				strSQL += " and c.create_time>='" + startDate + "'";
			}
			if(endDate!=null && !"".equals(endDate)){
				DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
				Date ed = df.parse(endDate);
				Calendar ec = Calendar.getInstance();
				ec.setTime(ed);
				ec.add(Calendar.DATE, 1);
				ed = ec.getTime();
				strSQL += " and c.create_time<='" + df.format(ed) + "'";
			}
			strSQL += " group by f.field_desc,u.unit_name order by f.field_desc,u.unit_name";
			logger.info(strSQL);
			rs = stmt.executeQuery(strSQL);
			List<ConfNumStatVo> l = new ArrayList<ConfNumStatVo>();
			ConfNumStatVo preVo = null;
			while(rs.next()){
				ConfNumStatVo vo = new ConfNumStatVo();
				vo.setConfType(rs.getString("field_desc"));
				vo.setMainUnit(rs.getString("unit_name"));
				vo.setNum(rs.getInt("num"));
				if(preVo == null){
					vo.setColumnSpan(1);
					
				}else{
					if(vo.getConfType().equals(preVo.getConfType())){
						vo.setColumnSpan(0);
						preVo.setColumnSpan(preVo.getColumnSpan()+1);
					}else{
						vo.setColumnSpan(1);
					}
				}
				preVo = vo;
				l.add(vo);
			}
			return l;
		}catch(Exception e){
			logger.error(e.toString());
			return null;
		}
	}
	
	public List<UserReserveStatVo> statDayUserReserve(String currDate) {
		Connection conn = this.getSession().connection();
		Statement stmt = null;
		ResultSet rs = null;
		try{
			stmt = conn.createStatement();
			String strSQL = "select count(*) as num,u.user_id,u.user_name from user u,conference c" +
				" where c.user_id=u.user_id" +
				" and c.create_time>='" + currDate + "'" +
				" and c.create_time<='" + currDate + " 59:59:59 999'" + 
				" group by u.user_id order by num desc limit 20";
			rs = stmt.executeQuery(strSQL);
			List<UserReserveStatVo> l = new ArrayList<UserReserveStatVo>();
			//显示长条的长度
			int length = 400;
			int firstNum = 1;
			while(rs.next()){
				UserReserveStatVo vo = new UserReserveStatVo();
				if(l.size()==0){
					firstNum = rs.getInt("num");
					vo.setLength(length);
				}else{
					vo.setLength(Math.round(rs.getInt("num")*length/(float)firstNum));
				}
				vo.setUserName(rs.getString("user_name"));
				vo.setNumber(rs.getInt("num"));
				vo.setIndex(l.size()+1);
				l.add(vo);
			}
			return l;
		}catch(Exception e){
			logger.error(e.toString());
			return null;
		}
	}
	public List<UnitTimeStatVo> statUnitTime(String startDate, String endDate){
		Connection conn = this.getSession().connection();
		Statement stmt = null;
		ResultSet rs = null;
		try{
			
			stmt = conn.createStatement();
			String strSQL = "select sum(timestampdiff(SECOND,from_unixtime(c.start_time/1000),c.update_time)) as timeLong,u.unit_name from conference c, unit u" +
				" where c.main_unit=u.unit_id and c.status="+Conference.status_history;
			if(startDate!=null && !"".equals(startDate)){	
				strSQL += " and c.create_time>='" + startDate + "'";
			}
			if(endDate!=null && !"".equals(endDate)){
				DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
				Date ed = df.parse(endDate);
				Calendar ec = Calendar.getInstance();
				ec.setTime(ed);
				ec.add(Calendar.DATE, 1);
				ed = ec.getTime();
				strSQL += " and c.create_time<='" + df.format(ed) + "'";
			}
			strSQL += " group by u.unit_name";
			logger.info(strSQL);
			rs = stmt.executeQuery(strSQL);
			List<UnitTimeStatVo> l = new ArrayList<UnitTimeStatVo>();
			while(rs.next()){
				UnitTimeStatVo vo = new UnitTimeStatVo();
				vo.setTimeLong(rs.getInt("timeLong")/60);
				vo.setUnitName(rs.getString("unit_name"));
				l.add(vo);
			}
			return l;
		}catch(Exception e){
			logger.error(e.toString());
			return null;
		}
	}
	public List<ConfTypeTimeStatVo> statConfTypeTime(String startDate, String endDate){
		Connection conn = this.getSession().connection();
		Statement stmt = null;
		ResultSet rs = null;
		try{
			
			stmt = conn.createStatement();
			String strSQL = "select sum(timestampdiff(SECOND,from_unixtime(c.start_time/1000),c.update_time)) as timeLong,f.field_desc from conference c, field_desc f" +
				" where c.conf_type=f.field_value and c.status="+Conference.status_history;
			if(startDate!=null && !"".equals(startDate)){	
				strSQL += " and c.create_time>='" + startDate + "'";
			}
			if(endDate!=null && !"".equals(endDate)){
				DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
				Date ed = df.parse(endDate);
				Calendar ec = Calendar.getInstance();
				ec.setTime(ed);
				ec.add(Calendar.DATE, 1);
				ed = ec.getTime();
				strSQL += " and c.create_time<='" + df.format(ed) + "'";
			}
			strSQL += " group by f.field_desc";
			logger.info(strSQL);
			rs = stmt.executeQuery(strSQL);
			List<ConfTypeTimeStatVo> l = new ArrayList<ConfTypeTimeStatVo>();
			while(rs.next()){
				ConfTypeTimeStatVo vo = new ConfTypeTimeStatVo();
				vo.setTimeLong(rs.getInt("timeLong")/60);
				vo.setConfTypeDesc(rs.getString("field_desc"));
				l.add(vo);
			}
			return l;
		}catch(Exception e){
			logger.error(e.toString());
			return null;
		}
	}
	public List<ConfTimeStatVo> statConfTime(String startDate, String endDate){
		Connection conn = this.getSession().connection();
		Statement stmt = null;
		ResultSet rs = null;
		try{	
			stmt = conn.createStatement();
			String strSQL = "select timestampdiff(SECOND,from_unixtime(c.start_time/1000),c.update_time) as timeLong,c.subject from conference c" +
				" where c.status="+Conference.status_history;
			if(startDate!=null && !"".equals(startDate)){	
				strSQL += " and c.create_time>='" + startDate + "'";
			}
			if(endDate!=null && !"".equals(endDate)){
				DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
				Date ed = df.parse(endDate);
				Calendar ec = Calendar.getInstance();
				ec.setTime(ed);
				ec.add(Calendar.DATE, 1);
				ed = ec.getTime();
				strSQL += " and c.create_time<='" + df.format(ed) + "'";
			}
			strSQL += " order by timestampdiff(SECOND,from_unixtime(c.start_time/1000),c.update_time) desc limit 10";
			logger.info(strSQL);
			rs = stmt.executeQuery(strSQL);
			List<ConfTimeStatVo> l = new ArrayList<ConfTimeStatVo>();
			while(rs.next()){
				ConfTimeStatVo vo = new ConfTimeStatVo();
				vo.setTimeLong(rs.getInt("timeLong")/60);
				vo.setSubject(rs.getString("subject"));
				l.add(vo);
			}
			return l;
		}catch(Exception e){
			logger.error(e.toString());
			return null;
		}
	}
}
