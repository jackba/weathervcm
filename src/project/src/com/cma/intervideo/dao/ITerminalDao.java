package com.cma.intervideo.dao;

import java.util.List;

import com.cma.intervideo.dao.util.DAO;
import com.cma.intervideo.pojo.Terminal;
import com.cma.intervideo.util.PageHolder;
import com.cma.intervideo.util.ParamVo;

public interface ITerminalDao extends DAO<Terminal, String>{
	public List<Terminal> findTerminals(List<ParamVo> params, PageHolder ph);
	public Terminal getTerminal(String terminalId);
}
