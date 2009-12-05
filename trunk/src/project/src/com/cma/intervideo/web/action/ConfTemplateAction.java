package com.cma.intervideo.web.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.cma.intervideo.constant.DataDictStatus;
import com.cma.intervideo.pojo.ConfTemplate;
import com.cma.intervideo.service.IConfTemplateService;
import com.cma.intervideo.util.AbstractBaseAction;
import com.cma.intervideo.util.PageHolder;
import com.cma.intervideo.util.ParamVo;
import com.cma.intervideo.util.PropertiesHelper;
import com.cma.intervideo.util.UserPrivilege;

public class ConfTemplateAction extends AbstractBaseAction {
	private static Log logger = LogFactory.getLog(ConfTemplateAction.class);
	private IConfTemplateService confTemplateService;
	private ConfTemplate confTemplate;

	public ConfTemplate getConfTemplate() {
		return confTemplate;
	}

	public void setConfTemplate(ConfTemplate confTemplate) {
		this.confTemplate = confTemplate;
	}

	public void setConfTemplateService(IConfTemplateService confTemplateService) {
		this.confTemplateService = confTemplateService;
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
		String templateName = request.getParameter("confTemplateName");
		String serviceTemplate = request.getParameter("serviceTemplateId");
		if (templateName != null && !templateName.equals("")) {
			ParamVo vo = new ParamVo();
			vo.setParamName("confTemplateName");
			vo.setParamValue(templateName);
			params.add(vo);
		}
		if (serviceTemplate != null && serviceTemplate.length() > 0) {
			ParamVo vo = new ParamVo();
			vo.setParamName("serviceTemplateId");
			vo.setParamValue(serviceTemplate);
			params.add(vo);
		}
		List<ConfTemplate> lst = confTemplateService.findConfTemplates(params, ph);
		try {
			JSONObject json = new JSONObject();
			json.put("totalProperty", ph.getResultSize());
			JSONArray arr = JSONArray.fromObject(lst);
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
	
	public String getConfTemplatesByUser() {
		UserPrivilege up = (UserPrivilege) session.get("userPrivilege");
		String userId = up != null ? up.getUserId() : PropertiesHelper.getIcmDefaultUserId();
		List<ConfTemplate> list = confTemplateService.findConfTemplatesByUserId(userId);
		try {
			JSONObject json = new JSONObject();
			JSONArray arr = JSONArray.fromObject(list);
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

	public String modify() {
		String confTemplateId = request.getParameter("confTemplateId");
		confTemplate = confTemplateService.getConfTemplateById(confTemplateId);
		return "modify";
	}
	
	public String detail() {
		String confTemplateId = request.getParameter("confTemplateId");
		confTemplate = confTemplateService.getConfTemplateById(confTemplateId);
		return "detail";
	}

	public String save() throws IOException, ParseException {
		try
		{
			String virtualConfId = confTemplate.getVirtualConfId();
			if (virtualConfId != null && virtualConfId.trim().length() > 0)
			{
				List<ConfTemplate> lst = confTemplateService.findConfTemplatesByVirtualConfId(virtualConfId);
				if (lst != null && lst.size() > 0)
				{
					outJson("{success:false, msg:'会议号码冲突!");
					return null;
				}
			}
			
			confTemplate.setStatus(DataDictStatus.normalStatus);
			UserPrivilege up = (UserPrivilege) session.get("userPrivilege");
			String userId = up != null ? up.getUserId() : PropertiesHelper.getIcmDefaultUserId();
			confTemplate.setUserId(userId);
			confTemplate.setMemberId(PropertiesHelper.getIcmDefaultMemberId());
			Date d = Calendar.getInstance().getTime();
			confTemplate.setCreateTime(d);
			response.setContentType("text/html;charset=utf-8");

			String units = request.getParameter("confUnits");
			String[] unitList = null;
			if (units != null && !units.equals(""))
				unitList = units.split(",");

			confTemplateService.createConfTemplate(confTemplate, unitList);
			outJson("{success:true, msg:'创建表单模板成功!'}");
		} catch (Exception e)
		{
			logger.warn("Failed to create new ConfTemplate to VCM due to exception: " + e.toString());
			outJson("{success:false, msg:'创建表单模板失败'}");
		}
		return null;
	}
}
