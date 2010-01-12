package com.cma.intervideo.web.action;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.cma.intervideo.pojo.Terminal;
import com.cma.intervideo.service.ITerminalService;
import com.cma.intervideo.util.AbstractBaseAction;
import com.cma.intervideo.util.PageHolder;
import com.cma.intervideo.util.ParamVo;

public class TerminalAction extends AbstractBaseAction {
	private static Log logger = LogFactory.getLog(TerminalAction.class);
	private ITerminalService terminalService;
	private Terminal terminal;

	public Terminal getTerminal() {
		return terminal;
	}

	public void setTerminal(Terminal terminal) {
		this.terminal = terminal;
	}

	public void setTerminalService(ITerminalService terminalService) {
		this.terminalService = terminalService;
	}

	public String list() {
		return "list";
	}

	public String add() {
		return "add";
	}
	
	public String detail() {
		String id = request.getParameter("terminalId");
		terminal = terminalService.getTerminal(id);
		logger.info("Terminal detail information, terminalId: " + id + "; detail: " + terminal);
		return "detail";
	}

	public String search() {
		logger.info("search...");
		if ("true".equals(request.getParameter("update"))) {
			terminalService.update();
		}
		
		String start = request.getParameter("start");
		String limit = request.getParameter("limit");
		String totalProperty = request.getParameter("totalProperty");
		PageHolder ph = new PageHolder();
		ph.setFirstIndex(Integer.parseInt(start));
		ph.setPageSize(Integer.parseInt(limit));
		if (totalProperty != null && !totalProperty.equals("")) {
			ph.setResultSize(Integer.parseInt(totalProperty));
		}
		List<ParamVo> params = new ArrayList<ParamVo>();
		String terminalName = request.getParameter("terminalName");
		String terminalNumber = request.getParameter("terminalNumber");
		if (terminalName != null && !terminalName.equals("")) {
			ParamVo vo = new ParamVo();
			vo.setParamName("terminalName");
			vo.setParamValue(terminalName);
			params.add(vo);
		}
		if (terminalNumber != null && terminalNumber.length() > 0) {
			ParamVo vo = new ParamVo();
			vo.setParamName("terminalNumber");
			vo.setParamValue(terminalNumber);
			params.add(vo);
		}
		List<Terminal> terminalList = terminalService.findTerminals(params, ph);
		try {
			JSONObject json = new JSONObject();
			json.put("totalProperty", ph.getResultSize());
			JSONArray arr = JSONArray.fromObject(terminalList);
			json.put("root", arr);
			System.out.println(json);
			response.setCharacterEncoding("utf-8");

			PrintWriter out = response.getWriter();
			out.print(json);
			out.flush();
			out.close();
		} catch (Exception e) {
			logger.error(e.toString());
		}
		return null;
	}
	
	public String searchAll() {
		try {
			logger.info("searchAll...");
			List<Terminal> terminalList = terminalService.findTerminals();
			JSONObject json = new JSONObject();
			JSONArray arr = JSONArray.fromObject(terminalList);
			json.put("root", arr);
			System.out.println(json);
			response.setCharacterEncoding("utf-8");

			PrintWriter out = response.getWriter();
			out.print(json);
			out.flush();
			out.close();
		} catch (Exception e) {
			logger.error(e.toString());
		}
		return null;
	}

}
