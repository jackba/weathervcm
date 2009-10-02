package com.cma.intervideo.service;

import java.util.List;

import com.cma.intervideo.pojo.Terminal;
import com.cma.intervideo.util.PageHolder;
import com.cma.intervideo.util.ParamVo;

public interface ITerminalService {
	public List<Terminal> findTerminals(List<ParamVo> params, PageHolder ph);
	public void saveOrUpdate(Terminal terminal);
	public int deleteAllTerminals();
	public Terminal getTerminal(String terminalId);
}
