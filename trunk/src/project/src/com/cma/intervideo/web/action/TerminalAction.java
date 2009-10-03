package com.cma.intervideo.web.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.cma.intervideo.pojo.ServiceTemplate;
import com.cma.intervideo.pojo.Terminal;
import com.cma.intervideo.pojo.VirtualRoom;
import com.cma.intervideo.service.ITerminalService;
import com.cma.intervideo.util.AbstractBaseAction;
import com.cma.intervideo.util.PageHolder;
import com.cma.intervideo.util.ParamVo;
import com.radvision.icm.service.MeetingType;
import com.radvision.icm.service.TerminalResource;
import com.radvision.icm.service.vcm.ICMService;

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

	public String save() throws IOException, ParseException, Exception {
		return null;
	}

	public String search() {
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
		String templateName = request.getParameter("templateName");
		String serviceTemplate = request.getParameter("serviceTemplate");
		if (templateName != null && !templateName.equals("")) {
			ParamVo vo = new ParamVo();
			vo.setParamName("templateName");
			vo.setParamValue(templateName);
			params.add(vo);
		}
		if (serviceTemplate != null && serviceTemplate.length() > 0) {
			ParamVo vo = new ParamVo();
			vo.setParamName("serviceTemplate");
			vo.setParamValue(serviceTemplate);
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

	public String update() throws IOException, ParseException, Exception {
		terminalService.deleteAllTerminals();
		
		List<TerminalResource> trs = ICMService.getTerminals();
		for (int i = 0; trs != null && i < trs.size(); i++) {
			TerminalResource tr = trs.get(i);
			Terminal t = new Terminal();
			t.setTerminalId(tr.getTerminalId());
			t.setTerminalName(tr.getTerminalName());
			t.setTerminalNumber(tr.getTerminalNumber());
			t.setTerminalProtocol((short)tr.getTerminalProtocol());
			t.setTimeZoneId(t.getTimeZoneId());
			t.setZonePrefix(tr.getZonePrefix());
			t.setTerminalEmail(tr.getTerminalEmail());
			t.setStatusId(tr.getStatusId()==1? true : false);
			t.setRegisterGkid(tr.getRegisterGKId());
			t.setNodeId(tr.getNodeId());
			t.setMaxBandwidth(tr.getMaxBandwidth());
			t.setIsdnMaxBandwidth(tr.getIsdnMaxBandwidth());
			t.setIsVoiceOnly(tr.isIsVoiceOnly());
			t.setIp(tr.getIP());
			t.setE164(tr.getE164());
			t.setDetailProtocol((short)tr.getDetailProtocol());
			t.setDefaultRoomId(tr.getDefaultRoomId());
			t.setCountryCode(tr.getCountryCode());
			t.setAreaCode(tr.getAreaCode());
			terminalService.saveOrUpdate(t);
		}
		return list();
	}

	public String detail() {
		String id = request.getParameter("terminalId");
		terminal = terminalService.getTerminal(id);
		return "detail";
	}
}
