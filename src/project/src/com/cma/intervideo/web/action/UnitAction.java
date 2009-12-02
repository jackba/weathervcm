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

import com.cma.intervideo.pojo.Unit;
import com.cma.intervideo.service.IUnitService;
import com.cma.intervideo.util.AbstractBaseAction;
import com.cma.intervideo.util.PageHolder;
import com.cma.intervideo.util.ParamVo;

public class UnitAction extends AbstractBaseAction {
	private static Log logger = LogFactory.getLog(UnitAction.class);
	private IUnitService unitService;
	private Unit unit;

	public void setUnitService(IUnitService unitService) {
		this.unitService = unitService;
	}

	public Unit getUnit() {
		return unit;
	}

	public void setUnit(Unit unit) {
		this.unit = unit;
	}

	public String getAll() {
		List<Unit> unitList = unitService.findAllUnits();
		try {
			JSONObject json = new JSONObject();
			JSONArray arr = JSONArray.fromObject(unitList);
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

	public String list() {
		return "list";
	}

	public String add() {
		return "add";
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
		String unitName = request.getParameter("unitName");
		String partyId = request.getParameter("partyId");
		if (unitName != null && !unitName.equals("")) {
			ParamVo vo = new ParamVo();
			vo.setParamName("unitName");
			vo.setParamValue(unitName);
			params.add(vo);
		}
		if (partyId != null && partyId.length() > 0) {
			ParamVo vo = new ParamVo();
			vo.setParamName("partyId");
			vo.setParamValue(partyId);
			params.add(vo);
		}
		List<Unit> unitList = unitService.findUnits(params, ph);
		try {
			JSONObject json = new JSONObject();
			json.put("totalProperty", ph.getResultSize());
			JSONArray arr = JSONArray.fromObject(unitList);
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

	public String save() throws IOException, ParseException, Exception {
		try {
			response.setContentType("text/html;charset=utf-8");
			unitService.saveOrUpdate(unit);
			outJson("{success:true, msg:'单位添加成功!'}");
		} catch (Exception e) {
			outJson("{success:true, msg:'单位添加失败'}");
		}
		return null;
	}

	public String detail() {
		String unitId = request.getParameter("unitId");
		unit = unitService.getUnitById(unitId);
		return "detail";
	}

	public String update() throws IOException, ParseException, Exception {
		try {
			response.setContentType("text/html;charset=utf-8");
			unitService.saveOrUpdate(unit);
			outJson("{success:true, msg:'单位修改成功!'}");
		} catch (Exception e) {
			outJson("{success:true, msg:'单位修改失败'}");
		}
		return null;
	}
	
	public String modify() {
		String unitId = request.getParameter("unitId");
		unit = unitService.getUnitById(unitId);
		return "modify";
	}
}
