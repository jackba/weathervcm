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
}
