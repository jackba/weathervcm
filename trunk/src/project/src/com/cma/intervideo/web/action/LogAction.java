package com.cma.intervideo.web.action;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.logging.LogFactory;

import com.cma.intervideo.pojo.Log;
import com.cma.intervideo.service.ILogService;
import com.cma.intervideo.util.AbstractBaseAction;
import com.cma.intervideo.util.PageHolder;
import com.cma.intervideo.util.ParamVo;

public class LogAction extends AbstractBaseAction{
	private static org.apache.commons.logging.Log logger = LogFactory.getLog(LogAction.class);
	private ILogService logService;
	public void setLogService(ILogService logService) {
		this.logService = logService;
	}
	public String list(){
		return "list";
	}
	public String search(){
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
		String userName = request.getParameter("userName");
		String logType = request.getParameter("logType");
		String startTime = request.getParameter("startTime");
		String endTime = request.getParameter("endTime");
		if(userName!=null && !userName.equals("")){
			ParamVo vo = new ParamVo();
			vo.setParamName("userName");
			vo.setParamValue(userName);
			params.add(vo);
		}
		if(logType!=null && !logType.equals("") && !logType.equals("-1")){
			ParamVo vo = new ParamVo();
			vo.setParamName("logType");
			vo.setParamValue(logType);
			params.add(vo);
		}
		if(startTime!=null && !startTime.equals("")){
			ParamVo vo = new ParamVo();
			vo.setParamName("startTime");
			vo.setParamValue(startTime);
			params.add(vo);
		}
		if(endTime!=null && !endTime.equals("")){
			ParamVo vo = new ParamVo();
			vo.setParamName("endTime");
			vo.setParamValue(endTime);
			params.add(vo);
		}
		List<Log> logList = logService.findLogs(params, ph);
		try {
			JSONObject json = new JSONObject();
			json.put("totalProperty", ph.getResultSize());
			JSONArray arr = JSONArray.fromObject(logList);
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
