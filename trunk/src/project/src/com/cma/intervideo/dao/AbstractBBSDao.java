package com.cma.intervideo.dao;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;

import com.cma.intervideo.constant.DataDictStatus;
import com.cma.intervideo.dao.util.AbstractDAO;
import com.cma.intervideo.pojo.MessageBoard;
import com.cma.intervideo.util.PageHolder;
import com.cma.intervideo.util.ParamVo;

public class AbstractBBSDao extends AbstractDAO<MessageBoard, Integer> implements IBBSDao{
	public List<MessageBoard> findAllMessages(){
		List<MessageBoard> result = new ArrayList<MessageBoard>();
		List l = this.getHibernateTemplate().find("select m,u.userName from MessageBoard m,User u where u.userId=m.userId and m.status="+DataDictStatus.messageNormalStatus+"  order by m.omsgId desc, m.messageId asc");
		if(l!=null && l.size()>0){
			for(int i=0;i<l.size();i++){
				Object[] o = (Object[])l.get(i);
				MessageBoard m = (MessageBoard)o[0];
				m.setUserName((String)o[1]);
				result.add(m);
			}
			return result;
		}else{
			return null;
		}
	}
	public List<MessageBoard> findMessagesByUserId(String userId){
		List<MessageBoard> result = new ArrayList<MessageBoard>();
		List l = this.getHibernateTemplate().find("select m,u.userName from MessageBoard m,User u where u.userId=m.userId and m.status="+DataDictStatus.messageNormalStatus+" and (m.omsgId=m.messageId and m.userId=?) order by m.messageId desc",userId);
		if(l!=null && l.size()>0){
			for(int i=0;i<l.size();i++){
				Object[] o = (Object[])l.get(i);
				MessageBoard m = (MessageBoard)o[0];
				m.setUserName((String)o[1]);
				result.add(m);
				result.addAll(findReplies(m.getMessageId()));
			}
			return result;
		}else{
			return null;
		}
	}
	public List<MessageBoard> findMessages(List<ParamVo> params, PageHolder ph){
		List<MessageBoard> result = new ArrayList<MessageBoard>();
		try{
			if(ph.isGetCount()){
				Query q = this.getSession().createQuery("select count(*) from MessageBoard m where m.status="+DataDictStatus.messageNormalStatus+getQueryStr(params)+" and m.messageId=m.omsgId");
				setQueryStr(q, params);
				List list = q.list();
				if(!list.isEmpty())
				{
					ph.setResultSize(((Long)list.get(0)).intValue());
				}
			}
			Query hql = this.getSession().createQuery("select m,u.userName from MessageBoard m,User u where m.userId=u.userId and m.status="+DataDictStatus.messageNormalStatus+getQueryStr(params)+" and m.messageId=m.omsgId order by m.messageId desc");
		    setQueryStr(hql,params);
		    if(ph!=null){
				hql.setMaxResults(ph.getPageSize());
				hql.setFirstResult(ph.getFirstIndex());
			}
		    List l = hql.list();
		    if(l!=null && l.size()>0){
		    	for(int i=0;i<l.size();i++){
		    		Object[] o = (Object[])l.get(i);
		    		MessageBoard m = (MessageBoard)o[0];
		    		m.setUserName((String)o[1]);
		    		result.add(m);
		    		result.addAll(findReplies(m.getMessageId()));
		    	}
		    	return result;
		    }else{
		    	return null;
		    }
		}catch(Exception e){
			logger.error(e.toString());
			return null;
		}
	}
	private String getQueryStr(List<ParamVo> params){
		StringBuffer sb = new StringBuffer();
		for(int i=0;i<params.size();i++){
			ParamVo vo = params.get(i);
			if(vo.getParamName().equals("userId")){
				sb.append(" and m.userId=:userId");
			}
			if(vo.getParamName().equals("title")){
				sb.append(" and m.title like :title");
			}
			if(vo.getParamName().equals("content")){
				sb.append(" and m.content like :content");
			}
			if(vo.getParamName().equals("startTime")){
				sb.append(" and m.createTime>=:startTime");
			}
			if(vo.getParamName().equals("endTime")){
				sb.append(" and m.createTime<:endTime");
			}
		}
		return sb.toString();
	}
	private void setQueryStr(Query hql, List<ParamVo> params) throws ParseException{
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		for(int i=0;i<params.size();i++){
			ParamVo vo = params.get(i);
			if(vo.getParamName().equals("userId")){
				hql.setParameter("userId", vo.getParamValue());
			}
			if(vo.getParamName().equals("title")){
				hql.setParameter("title", "%"+vo.getParamValue()+"%");
			}
			if(vo.getParamName().equals("content")){
				hql.setParameter("content", "%"+vo.getParamValue()+"%");
			}
			if(vo.getParamName().equals("startTime")){
				hql.setParameter("startTime", df.parse(vo.getParamValue().toString()));
			}
			if(vo.getParamName().equals("endTime")){
				hql.setParameter("endTime", df.parse(vo.getParamValue().toString()));
			}
		}
	}
	private List<MessageBoard> findReplies(int messageId){
		List<MessageBoard> result = new ArrayList<MessageBoard>();
		List l = this.getHibernateTemplate().find("select m,u.userName from MessageBoard m,User u where u.userId=m.userId and m.status="+DataDictStatus.messageNormalStatus+"  and m.omsgId=? and m.omsgId!=m.messageId order by m.messageId asc",messageId);
		if(l!=null && l.size()>0){
			for(int i=0;i<l.size();i++){
				Object[] o = (Object[])l.get(i);
				MessageBoard m = (MessageBoard)o[0];
				m.setUserName((String)o[1]);
				result.add(m);
			}
		}
		return result;
	}
}
