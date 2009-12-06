package com.cma.intervideo.service.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.cma.intervideo.dao.ITerminalDao;
import com.cma.intervideo.pojo.Terminal;
import com.cma.intervideo.service.ITerminalService;
import com.cma.intervideo.util.PageHolder;
import com.cma.intervideo.util.ParamVo;

public class TerminalServiceImpl implements ITerminalService{
	
	private static Log logger = LogFactory.getLog(TerminalServiceImpl.class);
	
	private ITerminalDao terminalDao;
	
	public void setTerminalDao(ITerminalDao terminalDao) {
		this.terminalDao = terminalDao;
	}
	
	public List<Terminal> findTerminals() {
		return terminalDao.findTerminals();
	}
	
	public List<Terminal> findTerminals(List<ParamVo> params, PageHolder ph){
		return terminalDao.findTerminals(params, ph);
	}
	
	public void saveOrUpdate(Terminal terminal){
		terminalDao.saveOrUpdate(terminal);
	}
	
	public int deleteAllTerminals() {
		List<Terminal> lst = findTerminals(null, null);
		if (lst == null || lst.size() == 0)
			return 0;
		
		for (Terminal t : lst)
		{
			terminalDao.removeObjectByID(t.getTerminalId());
			logger.info("Deleted successfully Terminal, terminalId: " + t.getTerminalId() + "; terminalName: " + t.getTerminalName());
		}
		return lst.size();
	}
	
	public Terminal getTerminal(String terminalId) {
		return terminalDao.getTerminal(terminalId);
	}
	
}
