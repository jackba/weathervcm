package com.cma.intervideo.dao;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.cma.intervideo.constant.DataDictStatus;
import com.cma.intervideo.dao.util.AbstractDAO;
import com.cma.intervideo.pojo.VirtualRoom;
import com.cma.intervideo.util.PageHolder;
import com.cma.intervideo.util.ParamVo;

public class AbstractRoomDao extends AbstractDAO<VirtualRoom, String> implements IRoomDao{
	private final static Log logger = LogFactory.getLog(AbstractRoomDao.class);
	public List<VirtualRoom> findRooms(List<ParamVo> params, PageHolder ph){
		String hql = "from VirtualRoom r where r.status="+DataDictStatus.normalStatus;
		if(params!=null){
			for(int i=0;i<params.size();i++){
				ParamVo vo = params.get(i);
				if(vo.getParamName().equals("templateName")){
					hql += " and r.templateName like '%"+vo.getParamValue()+"%'";
				}
				if(vo.getParamName().equals("serviceTemplate")){
					hql += " and r.serviceTemplate='"+vo.getParamValue()+"'";
				}
			}
		}
		if(ph!=null && ph.isGetCount()){
			ph.setResultSize(this.getCount(hql));
		}
		return this.getHibernateTemplate().find(hql+" order by r.createTime");
	}
}
