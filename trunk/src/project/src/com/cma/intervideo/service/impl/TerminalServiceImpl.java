package com.cma.intervideo.service.impl;

import java.util.List;

import com.cma.intervideo.dao.ITerminalDao;
import com.cma.intervideo.pojo.Terminal;
import com.cma.intervideo.service.ITerminalService;
import com.cma.intervideo.util.PageHolder;
import com.cma.intervideo.util.ParamVo;

public class TerminalServiceImpl implements ITerminalService{
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
		int ret = lst == null ? 0 : lst.size();
		for (int i = 0; lst != null && i < lst.size(); i++) {
			terminalDao.removeObjectByID(lst.get(i).getTerminalId());
		}
		return ret;
	}
	public Terminal getTerminal(String terminalId) {
		return terminalDao.getTerminal(terminalId);
	}
}
