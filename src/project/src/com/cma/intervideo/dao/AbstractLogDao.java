package com.cma.intervideo.dao;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.logging.LogFactory;

import com.cma.intervideo.dao.util.AbstractDAO;
import com.cma.intervideo.pojo.Log;
import com.cma.intervideo.pojo.User;
import com.cma.intervideo.util.PageHolder;
import com.cma.intervideo.util.ParamVo;

public abstract class AbstractLogDao extends AbstractDAO<Integer, Log> implements ILogDao{
	private static org.apache.commons.logging.Log logger = LogFactory.getLog(AbstractLogDao.class);
	
	/**
	 * 增加日志
	 * @param userId
	 * @param logType
	 * @param description
	 */
	public void addLog(String userId, short logType, String description){
		Log log = new Log();
		Date d = Calendar.getInstance().getTime();
		log.setCreateTime(d);
		log.setUserId(userId);
		log.setLogType(logType);
		log.setDescription(description);
		this.getHibernateTemplate().save(log);
	}
	public List<Log> findLogs(List<ParamVo> params, PageHolder ph){
		String hql = "from Log log,User user where log.userId=user.userId";
		if(params!=null){
			for(int i=0;i<params.size();i++){
				ParamVo vo = params.get(i);
				if(vo.getParamName().equals("userName")){
					hql += " and user.userName like '%"+vo.getParamValue()+"%'";
				}
				if(vo.getParamName().equals("logType")){
					hql += " and log.logType="+vo.getParamValue();
				}
				if(vo.getParamName().equals("startTime")){
					hql += " and log.createTime>='"+vo.getParamValue()+"'";
				}
				if(vo.getParamName().equals("endTime")){
					hql += " and log.createTime<'"+vo.getParamValue()+"'";
				}
			}
		}
		if(ph.isGetCount()){
			ph.setResultSize(this.getCount(hql));
		}
		List<Log> result = new ArrayList<Log>();
		List l = this.find("select log,user "+hql+" order by log.createTime desc", ph);
		if(l!=null && l.size()>0){
			for(int i=0;i<l.size();i++){
				Object[] o = (Object[])l.get(i);
				Log log = (Log)o[0];
				User user = (User)o[1];
				log.setLoginId(user.getLoginId());
				log.setUserName(user.getUserName());
				result.add(log);
			}
		}
		return result;
	}
}
