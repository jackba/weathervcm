package com.cma.intervideo.dao;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.cma.intervideo.dao.util.AbstractDAO;
import com.cma.intervideo.pojo.Terminal;
import com.cma.intervideo.util.PageHolder;
import com.cma.intervideo.util.ParamVo;

public class AbstractTerminalDao extends AbstractDAO<Terminal, String>
		implements ITerminalDao {
	private final static Log logger = LogFactory
			.getLog(AbstractTerminalDao.class);

	public List<Terminal> findTerminals() {
		String hql = "from Terminal t order by t.terminalId";
		List<Terminal> lst = getHibernateTemplate().find(hql);
		logger.info("Found " + (lst == null ? 0 : lst.size()) + " Terminals, HQL: " + hql);
		return lst;
	}
	
	public List<Terminal> findTerminals(List<ParamVo> params, PageHolder ph) {
		String hql = "from Terminal t order by t.terminalId";
		if(params!=null){
			for(int i=0;i<params.size();i++){
				ParamVo vo = params.get(i);
				if(vo.getParamName().equals("terminalName")){
					hql += " and t.terminalName like '%"+vo.getParamValue()+"%'";
				}
				if(vo.getParamName().equals("terminalNumber")){
					hql += " and t.terminalNumber='"+vo.getParamValue()+"'";
				}
			}
		}
		if(ph!=null && ph.isGetCount()){
			ph.setResultSize(this.getCount(hql));
		}
		List<Terminal> lst = getHibernateTemplate().find(hql);
		logger.info("Found " + (lst == null ? 0 : lst.size()) + " Terminal(s), HQL: " + hql);
		return lst;
	}

	public Terminal getTerminal(String terminalId) {
		Terminal t = (Terminal) getHibernateTemplate().get(Terminal.class, terminalId);
		if (t == null)
			logger.info("Didn't find Terminal, terminalId: " + terminalId);
		else
			logger.info("Found Terminal, terminalId: " + terminalId + "; terminalName: " + t.getTerminalName());
		return t;
	}
}