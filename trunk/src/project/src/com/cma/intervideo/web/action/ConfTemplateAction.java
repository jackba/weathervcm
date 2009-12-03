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

	public String save() throws IOException, ParseException, Exception {
		confTemplate.setStatus(DataDictStatus.normalStatus);
		UserPrivilege up = (UserPrivilege) session.get("userPrivilege");
		String userId = up != null ? up.getUserId() : PropertiesHelper.getIcmDefaultUserId();
		confTemplate.setUserId(userId);
		confTemplate.setMemberId(PropertiesHelper.getIcmDefaultMemberId());
		Date d = Calendar.getInstance().getTime();
		confTemplate.setCreateTime(d);
		response.setContentType("text/html;charset=utf-8");
		confTemplateService.saveOrUpdate(confTemplate);
		try {
			outJson("{success:true, msg:'虚拟房间添加成功!'}");
		} catch (Exception e) {
			outJson("{success:true, msg:'虚拟房间添加失败'}");
		}
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
		List<ConfTemplate> list = confTemplateService.findConfTemplates(userId);
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
	
	public String update() throws IOException, ParseException, Exception {
		ConfTemplate ct = confTemplateService.getConfTemplateById(confTemplate.getServiceTemplateId());
		ct.setControlPin(confTemplate.getControlPin());
		ct.setPassword(confTemplate.getPassword());
		ct.setDescription(confTemplate.getDescription());
		ct.setSubject(confTemplate.getSubject());
		ct.setConfTemplateName(confTemplate.getConfTemplateName());
		ct.setServiceTemplateId(confTemplate.getServiceTemplateId());
		response.setContentType("text/html;charset=utf-8");
		confTemplateService.saveOrUpdate(ct);
		try {
			outJson("{success:true, msg:'会议模板修改成功!'}");
		} catch (Exception e) {
			outJson("{success:true, msg:'会议模板修改失败'}");
		}
		return null;
	}

	public String detail() {
		String confTemplateId = request.getParameter("confTemplateId");
		confTemplate = confTemplateService.getConfTemplateById(confTemplateId);
		return "detail";
	}

}
