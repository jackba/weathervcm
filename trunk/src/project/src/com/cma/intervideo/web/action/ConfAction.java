package com.cma.intervideo.web.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.cma.intervideo.pojo.Conference;
import com.cma.intervideo.pojo.Unit;
import com.cma.intervideo.pojo.VirtualRoom;
import com.cma.intervideo.service.IConfService;
import com.cma.intervideo.service.IRoomService;
import com.cma.intervideo.util.AbstractBaseAction;
import com.cma.intervideo.util.PageHolder;
import com.cma.intervideo.util.ParamVo;
import com.cma.intervideo.util.UserPrivilege;
import com.cma.intervideo.util.VcmProperties;

public class ConfAction extends AbstractBaseAction {
	private static Log logger = LogFactory.getLog(AbstractBaseAction.class);
	private IConfService confService;
	private IRoomService roomService;
	private Conference conf;

	public Conference getConf() {
		return conf;
	}

	public void setConf(Conference conf) {
		this.conf = conf;
	}

	public void setConfService(IConfService confService) {
		this.confService = confService;
	}

	public void setRoomService(IRoomService roomService) {
		this.roomService = roomService;
	}

	public String listReserve() {
		request.setAttribute("personal", "true");
		return "listReserve";
	}

	public String searchReserves() {
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
		String subject = request.getParameter("subject");
		String serviceTemplate = request.getParameter("serviceTemplate");
		if (subject != null && !subject.equals("")) {
			ParamVo vo = new ParamVo();
			vo.setParamName("subject");
			vo.setParamValue(subject);
			params.add(vo);
		}
		if (serviceTemplate != null && serviceTemplate.length() > 0) {
			ParamVo vo = new ParamVo();
			vo.setParamName("serviceTemplate");
			vo.setParamValue(serviceTemplate);
			params.add(vo);
		}
		List<Conference> confList = confService.findConfs(params, ph);
		try {
			JSONObject json = new JSONObject();
			json.put("totalProperty", ph.getResultSize());
			JSONArray arr = JSONArray.fromObject(confList);
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

	public String reserveConf() {
		return "reserveConf";
	}

	public String save() throws IOException, ParseException {
		conf.setStatus(Conference.status_tobescheduled);
		UserPrivilege up = (UserPrivilege) session.get("userPrivilege");
		String userId = up != null ? up.getUserId() : VcmProperties
				.getICMDefaultUserId();
		conf.setUserId(userId);
		conf.setMemberId(VcmProperties.getICMDefaultMemberId());
		Date d = Calendar.getInstance().getTime();
		conf.setCreateTime(d);
		conf.setUpdateTime(d);
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String startTime = request.getParameter("startTime");
		if (startTime != null && !startTime.equals("")) {
			conf.setStartTime(df.parse(startTime).getTime());
		}
		response.setContentType("text/html;charset=utf-8");

		String units = request.getParameter("confUnits");
		String[] unitList = null;
		if (units != null && !units.equals(""))
			unitList = units.split(",");

		try {
			conf.setStatus(Conference.status_upcoming);
			confService.createConf(conf, unitList);
			outJson("{success:true, msg:'预约会议成功!'}");
		} catch (Exception e) {
			outJson("{success:true, msg:'预约会议失败'}");
		}
		return null;
	}

	public String modifyReserve() {
		String id = request.getParameter("conferenceId");
		conf = confService.getConfById(id);
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Calendar c = Calendar.getInstance();
		c.setTimeInMillis(conf.getStartTime());
		request.setAttribute("startTime", df.format(c.getTime()));
		return "modifyReserve";
	}

	public String loadvm() {
		String roomId = request.getParameter("roomId");
		if (roomId == null)
			return "reserveConf";

		VirtualRoom room = roomService.getRoomById(roomId);
		if (room != null) {
			conf = new Conference();
			// conf.setSubject(room.getSubject());
			conf.setSubject(room.getTemplateName());
			conf.setServiceTemplate(room.getServiceTemplate());
			conf.setServiceTemplateName(room.getServiceTemplateName());
			conf.setServiceTemplateDesc(room.getServiceTemplateDesc());
			conf.setDescription(room.getDescription());
			conf.setPassword(room.getPassword());
			conf.setControlPin(room.getControlPin());
			conf.setDialableNumber(room.getVitualConfId());
		}
		return "reserveConf";
	}

	public String update() throws Exception {
		Conference oldConf = confService.getConfById(conf.getConferenceId()
				.toString());
		Date d = Calendar.getInstance().getTime();
		oldConf.setUpdateTime(d);
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String startTime = request.getParameter("startTime");
		if (startTime != null && !startTime.equals("")) {
			oldConf.setStartTime(df.parse(startTime).getTime());
		}
		oldConf.setContactMethod(conf.getContactMethod());
		oldConf.setSubject(conf.getSubject());
		oldConf.setControlPin(conf.getControlPin());
		oldConf.setPassword(conf.getPassword());
		oldConf.setDialableNumber(conf.getDialableNumber());
		oldConf.setInitUnit(conf.getInitUnit());
		oldConf.setTimeLong(conf.getTimeLong());
		oldConf.setMainUnit(conf.getMainUnit());
		oldConf.setPresider(conf.getPresider());
		oldConf.setPrincipal(conf.getPrincipal());
		oldConf.setPrincipalMobile(conf.getPrincipalMobile());
		oldConf.setReserveCode(conf.getReserveCode());
		oldConf.setDescription(conf.getDescription());
		response.setContentType("text/html;charset=utf-8");

		String units = request.getParameter("confUnits");
		String[] unitList = null;
		if (units != null && !units.equals(""))
			unitList = units.split(",");

		try {
			confService.modifyConf(oldConf, unitList);
			outJson("{success:true, msg:'预约会议修改成功!'}");
		} catch (Exception e) {
			outJson("{success:true, msg:'预约会议修改失败!'}");
		}
		return null;
	}

	public String reserveDetail() {
		String confId = request.getParameter("conferenceId");
		conf = confService.getConfById(confId);
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Calendar c = Calendar.getInstance();
		c.setTimeInMillis(conf.getStartTime());
		request.setAttribute("startTime", df.format(c.getTime()));
		return "reserveDetail";
	}

	public String manageReserve() {
		request.setAttribute("personal", "false");
		return "listReserve";
	}

	public String getUnitsByConfId() {
		String id = request.getParameter("conferenceId");
		boolean selected = "true".equalsIgnoreCase(request
				.getParameter("selected"));
		List<Unit> units = confService.findUnitsByConfId(id, selected);
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
			logger.error(e.toString());
		}
		return null;
	}

	public String getAllUnitsExclud() {
		List<Unit> allUnits = confService.findAllUnits();
		String id = request.getParameter("conferenceId");
		List<Unit> confUnits = confService.findUnitsByConfId(id, false);
		for (int i = 0; i < confUnits.size(); i++) {
			Unit unit = confUnits.get(i);
			for (int inneri = 0; inneri < allUnits.size(); inneri++) {
				Unit inunit = allUnits.get(inneri);
				if (unit.getUnitId().equals(inunit.getUnitId())) {
					allUnits.remove(inneri);
				}
			}
		}
		JSONObject json = new JSONObject();
		JSONArray arr = JSONArray.fromObject(allUnits);
		json.put("root", arr);
		try {
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
