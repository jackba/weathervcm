package com.cma.intervideo.service;

import java.util.List;

import com.cma.intervideo.pojo.Terminal;
import com.cma.intervideo.util.PageHolder;
import com.cma.intervideo.util.ParamVo;

public interface ITerminalService {
	public List<Terminal> findTerminals(List<ParamVo> params, PageHolder ph);
	public List<Terminal> findTerminals();
	public void saveOrUpdate(Terminal terminal);
	public void deleteTerminalsByNewIds(List<String> newIds);
	public Terminal getTerminal(String terminalId);
	public void update();
}
