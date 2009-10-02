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
import com.cma.intervideo.pojo.VirtualRoom;
import com.cma.intervideo.service.IRoomService;
import com.cma.intervideo.util.AbstractBaseAction;
import com.cma.intervideo.util.PageHolder;
import com.cma.intervideo.util.ParamVo;
import com.cma.intervideo.util.UserPrivilege;
import com.cma.intervideo.util.VcmProperties;

public class RoomAction extends AbstractBaseAction {
	private static Log logger = LogFactory.getLog(RoomAction.class);
	private IRoomService roomService;
	private VirtualRoom room;

	public VirtualRoom getRoom() {
		return room;
	}

	public void setRoom(VirtualRoom room) {
		this.room = room;
	}

	public void setRoomService(IRoomService roomService) {
		this.roomService = roomService;
	}

	public String list() {
		return "list";
	}

	public String add() {
		return "add";
	}

	public String save() throws IOException, ParseException, Exception {
		room.setStatus(DataDictStatus.normalStatus);
		UserPrivilege up = (UserPrivilege) session.get("userPrivilege");
		String userId = up != null ? up.getUserId() : VcmProperties
				.getICMDefaultUserId();
		room.setUserId(userId);
		room.setMemberId(VcmProperties.getICMDefaultMemberId());
		Date d = Calendar.getInstance().getTime();
		room.setCreateTime(d);
		room.setUpdateTime(d);
		// DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		// room.setRoomId(new SimpleDateFormat("yyyyMMddHHmmssSSS").format(d));
		// String startTime = request.getParameter("startTime");
		// if(startTime!=null && !startTime.equals("")){
		// room.setStartTime(df.parse(startTime).getTime());
		// }
		response.setContentType("text/html;charset=utf-8");
		roomService.saveOrUpdate(room);
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
		List<VirtualRoom> roomList = roomService.findRooms(params, ph);
		try {
			JSONObject json = new JSONObject();
			json.put("totalProperty", ph.getResultSize());
			JSONArray arr = JSONArray.fromObject(roomList);
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
		String roomId = request.getParameter("roomId");
		room = roomService.getRoomById(roomId);
		// Calendar c = Calendar.getInstance();
		// DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		// if(room.getStartTime()!=null){
		// c.setTimeInMillis(room.getStartTime());
		// request.setAttribute("startTime", df.format(c.getTime()));
		// }
		return "modify";
	}

	public String update() throws IOException, ParseException, Exception {
		VirtualRoom r = roomService.getRoomById(room.getRoomId());
		Date d = Calendar.getInstance().getTime();
		r.setUpdateTime(d);
		// DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		// String startTime = request.getParameter("startTime");
		// if(startTime!=null && !startTime.equals("")){
		// r.setStartTime(df.parse(startTime).getTime());
		// }
		r.setControlPin(room.getControlPin());
		r.setPassword(room.getPassword());
		r.setDescription(room.getDescription());
		r.setSubject(room.getSubject());
		r.setTemplateName(room.getTemplateName());
		r.setServiceTemplate(room.getServiceTemplate());
		response.setContentType("text/html;charset=utf-8");
		roomService.saveOrUpdate(r);
		try {
			outJson("{success:true, msg:'会议模板修改成功!'}");
		} catch (Exception e) {
			outJson("{success:true, msg:'会议模板修改失败'}");
		}
		return null;
	}

	public String detail() {
		String roomId = request.getParameter("roomId");
		room = roomService.getRoomById(roomId);
		// Calendar c = Calendar.getInstance();
		// DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		// if (room.getStartTime() != null) {
		// c.setTimeInMillis(room.getStartTime());
		// request.setAttribute("startTime", df.format(c.getTime()));
		// }
		return "detail";
	}
}
