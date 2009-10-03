package com.cma.intervideo.web.action;

import java.io.PrintWriter;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.cma.intervideo.pojo.Unit;
import com.cma.intervideo.service.IUnitService;
import com.cma.intervideo.util.AbstractBaseAction;

public class UnitAction extends AbstractBaseAction{
	private static Log logger = LogFactory.getLog(UnitAction.class);
	private IUnitService unitService;
	public void setUnitService(IUnitService unitService) {
		this.unitService = unitService;
	}
	public String getAll(){
		List<Unit> unitList = unitService.findAllUnits();
		try{
			JSONObject json = new JSONObject();
			JSONArray arr = JSONArray.fromObject(unitList);
			json.put("root", arr);
			System.out.println(json);
			response.setCharacterEncoding("utf-8");
		
			PrintWriter out = response.getWriter();
			out.print(json);
			out.flush();
			out.close();
		}catch(Exception e){
			logger.error(e.toString());
		}
		return null;
	}
}
