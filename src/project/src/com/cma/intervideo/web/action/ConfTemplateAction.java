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
import com.cma.intervideo.pojo.Unit;
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
	
	public String modify() {
		String confTemplateId = request.getParameter("confTemplateId");
		confTemplate = confTemplateService.getConfTemplateById(confTemplateId);
		return "modify";
	}
	
	public String detail() {
		String confTemplateId = request.getParameter("confTemplateId");
		confTemplate = confTemplateService.getConfTemplateById(confTemplateId);
		logger.info("ConfTemplate detail information, confTemplateId: " + confTemplateId + "; detail: " + confTemplate);
		return "detail";
	}

	public String search() {
		logger.info("search...");
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
		if (templateName != null && !templateName.equals("")) {
			ParamVo vo = new ParamVo();
			vo.setParamName("confTemplateName");
			vo.setParamValue(templateName);
			params.add(vo);
		}
		
		String serviceTemplate = request.getParameter("serviceTemplateId");
		if (serviceTemplate != null && serviceTemplate.length() > 0) {
			ParamVo vo = new ParamVo();
			vo.setParamName("serviceTemplateId");
			vo.setParamValue(serviceTemplate);
			params.add(vo);
		}
		
		try {
			List<ConfTemplate> lst = confTemplateService.findConfTemplates(params, ph);
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
		} catch (Exception e)
		{
			logger.error("Exception on search ConfTemplate(s): " + e.getMessage());
		}
		return null;
	}
	
	public String getConfTemplatesByUser()
	{
		try
		{
			logger.info("getConfTemplatesByUser...");
			UserPrivilege up = (UserPrivilege) session.get("userPrivilege");
			String userId = up != null ? up.getUserId() : PropertiesHelper.getIcmDefaultUserId();
			
			List<ParamVo> params = new ArrayList<ParamVo>();
			ParamVo vo = new ParamVo();
			vo.setParamName("userId");
			vo.setParamValue(userId);
			params.add(vo);
			List<ConfTemplate> list = confTemplateService.findConfTemplates(params, null);
			
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
			logger.error("Exception on getConfTemplatesByUser: " + e.getMessage());
		}
		return null;
	}

	public String save() throws IOException, ParseException {
		try
		{
			logger.info("save...");
			String confTemplateName = confTemplate.getConfTemplateName();
			if (confTemplateName != null && confTemplateName.trim().length() > 0)
			{
				List<ParamVo> params = new ArrayList<ParamVo>();
				ParamVo vo = new ParamVo();
				vo.setParamName("confTemplateName");
				vo.setParamValue(confTemplateName);
				params.add(vo);
				List<ConfTemplate> lst = confTemplateService.findConfTemplates(params, null);
				if (lst != null && lst.size() > 0)
				{
					outJson("{success:false, msg:'表单模板名冲突!'}");
					return null;
				}
			}
			
//			String virtualConfId = confTemplate.getVirtualConfId();
//			if (virtualConfId != null && virtualConfId.trim().length() > 0)
//			{
//				List<ParamVo> params = new ArrayList<ParamVo>();
//				ParamVo vo = new ParamVo();
//				vo.setParamName("virtualConfId");
//				vo.setParamValue(virtualConfId);
//				params.add(vo);
//				List<ConfTemplate> lst = confTemplateService.findConfTemplates(params, null);
//				if (lst != null && lst.size() > 0)
//				{
//					outJson("{success:false, msg:'会议号冲突!'}");
//					return null;
//				}
//			}
			
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
	
	public String update() throws IOException, ParseException {
		try
		{
			logger.info("update...");
			ConfTemplate old = confTemplateService.getConfTemplateById(confTemplate.getConfTemplateId()+"");
			String confTemplateName = confTemplate.getConfTemplateName();
			if (confTemplateName != null && confTemplateName.trim().length() > 0)
			{
				List<ParamVo> params = new ArrayList<ParamVo>();
				ParamVo vo = new ParamVo();
				vo.setParamName("confTemplateName");
				vo.setParamValue(confTemplateName);
				params.add(vo);
				List<ConfTemplate> lst = confTemplateService.findConfTemplates(params, null);
				if (lst != null && lst.size() > 0 && confTemplate.getConfTemplateId() != lst.get(0).getConfTemplateId())
				{
					outJson("{success:false, msg:'表单模板名冲突!'}");
					return null;
				}
			}
			old.setConfTemplateName(confTemplateName);
			old.setServiceTemplateId(confTemplate.getServiceTemplateId());
			old.setVirtualConfId(confTemplate.getVirtualConfId());
			old.setTimeLong(confTemplate.getTimeLong());
			old.setDescription(confTemplate.getDescription());
			old.setPassword(confTemplate.getPassword());
			old.setControlPin(confTemplate.getControlPin());
			old.setSubject(confTemplate.getSubject());
			old.setInitUnit(confTemplate.getInitUnit());
			old.setMainUnit(confTemplate.getMainUnit());
			old.setPresider(confTemplate.getPresider());
			old.setPrincipalMobile(confTemplate.getPrincipalMobile());
			old.setReserveCode(confTemplate.getReserveCode());
			old.setContactMethod(confTemplate.getContactMethod());
			old.setPrincipal(confTemplate.getPrincipal());
			old.setIsDefault(confTemplate.getIsDefault());
			response.setContentType("text/html;charset=utf-8");

			String units = request.getParameter("confUnits");
			String[] unitList = null;
			if (units != null && !units.equals(""))
				unitList = units.split(",");

			confTemplateService.modifyConfTemplate(old, unitList);
			outJson("{success:true, msg:'创建表单模板成功!'}");
		} catch (Exception e)
		{
			logger.warn("Failed to create new ConfTemplate to VCM due to exception: " + e.toString());
			outJson("{success:false, msg:'创建表单模板失败'}");
		}
		return null;
	}
	
	public String getUnitsByConfTemplateId() {
		logger.info("getUnitsByConfTemplateId...");
		String id = request.getParameter("confTemplateId");
		boolean selected = "true".equalsIgnoreCase(request
				.getParameter("selected"));
		List<Unit> units = confTemplateService.findUnitsByConfTemplateId(id, selected);
		JSONObject json = new JSONObject();
		JSONArray arr = JSONArray.fromObject(units);
		json.put("root", arr);
		try {
			System.out.println(json);
			response.setCharacterEncoding("utf-8");

			PrintWriter out = response.getWriter();
			out.print(json);
			out.flush();
			out.close();
		} catch (Exception e) {
			logger.error("Exception on getUnitsByConfTemplateId: " + e.getMessage());
		}
		return null;
	}

}
