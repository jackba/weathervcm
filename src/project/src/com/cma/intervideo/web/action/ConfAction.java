package com.cma.intervideo.web.action;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.servlet.ServletOutputStream;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.BeanUtils;

import com.cma.intervideo.exception.ReserveCodeNotExistException;
import com.cma.intervideo.pojo.Conference;
import com.cma.intervideo.pojo.FieldDesc;
import com.cma.intervideo.pojo.FieldDescId;
import com.cma.intervideo.pojo.RecurringMeetingInfo;
import com.cma.intervideo.pojo.SendMessage;
import com.cma.intervideo.pojo.Unit;
import com.cma.intervideo.pojo.User;
import com.cma.intervideo.pojo.VirtualRoom;
import com.cma.intervideo.service.IConfService;
import com.cma.intervideo.service.ILogService;
import com.cma.intervideo.service.IRoomService;
import com.cma.intervideo.service.IUserService;
import com.cma.intervideo.util.AbstractBaseAction;
import com.cma.intervideo.util.PageHolder;
import com.cma.intervideo.util.ParamVo;
import com.cma.intervideo.util.PropertiesHelper;
import com.cma.intervideo.util.SMSUtil;
import com.cma.intervideo.util.UserPrivilege;
import com.cma.intervideo.util.VcmProperties;
import com.lowagie.text.Document;
import com.lowagie.text.Font;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.radvision.icm.service.vcm.ICMService;

public class ConfAction extends AbstractBaseAction {
	private static Log logger = LogFactory.getLog(AbstractBaseAction.class);
	private IConfService confService;
	private IRoomService roomService;
	private ILogService logService;
	private IUserService userService;
	private Conference conf;
	private RecurringMeetingInfo recurrence;
	private Integer conferenceId;
	private SMSUtil smsUtil;
	
	public void setSmsUtil(SMSUtil smsUtil) {
		this.smsUtil = smsUtil;
	}

	public void setLogService(ILogService logService) {
		this.logService = logService;
	}
	
	public void setUserService(IUserService userService) {
		this.userService = userService;
	}
	
	public RecurringMeetingInfo getRecurrence() {
		return recurrence;
	}

	public void setRecurrence(RecurringMeetingInfo recurrence) {
		this.recurrence = recurrence;
	}

	public Integer getConferenceId() {
		return conferenceId;
	}

	public void setConferenceId(Integer conferenceId) {
		this.conferenceId = conferenceId;
	}

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
	
	public String listRecurrence(){
		request.setAttribute("personal", "true");
		return "listRecurrence";
	}
	public String listRunning(){
		String monitorUrl = PropertiesHelper.getFullMonitorURL();
		request.setAttribute("monitorUrl", monitorUrl);
		return "listRunning";
	}
	public String searchRunnings(){
		PageHolder ph = new PageHolder();
		List<Conference> confList = getConfList(ph);
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
	public String listCurrentDay(){
		String monitorUrl = PropertiesHelper.getFullMonitorURL();
		request.setAttribute("monitorUrl", monitorUrl);
		return "listCurrentDay";
	}
	public String listCurrentWeek(){
		String monitorUrl = PropertiesHelper.getFullMonitorURL();
		request.setAttribute("monitorUrl", monitorUrl);
		return "listCurrentWeek";
	}
	public String listCurrentMonth(){
		String monitorUrl = PropertiesHelper.getFullMonitorURL();
		request.setAttribute("monitorUrl", monitorUrl);
		return "listCurrentMonth";
	}
	public String listAll(){
		String monitorUrl = PropertiesHelper.getFullMonitorURL();
		request.setAttribute("monitorUrl", monitorUrl);
		return "listAll";
	}
	public String searchCurrentDays(){
		PageHolder ph = new PageHolder();
		
		List<Conference> confList = getConfList(ph);
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
	public String searchCurrentWeeks(){
		PageHolder ph = new PageHolder();
		List<Conference> confList = getConfList(ph);
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
	public String searchCurrentMonths(){
		PageHolder ph = new PageHolder();
		List<Conference> confList = getConfList(ph);
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
	public String searchAlls(){
		PageHolder ph = new PageHolder();
		List<Conference> confList = getConfList(ph);
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
	public String generatePDF(){
		logger.info("generatePDF...");
		exportToPDF(getConfList(new PageHolder()));
		return null;
	}
	private List<Conference> getConfList(PageHolder ph){
		String start = request.getParameter("start");
		String limit = request.getParameter("limit");
		boolean flag = true;
		String personal = request.getParameter("personal");
		if(personal!=null && personal.equals("false")){
			flag = false;
		}
		String totalProperty = request.getParameter("totalProperty");
		ph.setFirstIndex(Integer.parseInt(start));
		ph.setPageSize(Integer.parseInt(limit));
		if (totalProperty != null && !totalProperty.equals("")) {
			ph.setResultSize(Integer.parseInt(totalProperty));
		}
		List<ParamVo> params = new ArrayList<ParamVo>();
		UserPrivilege up = (UserPrivilege)session.get("userPrivilege");
		
		String subject = request.getParameter("subject");
		if (subject != null && !subject.equals("")) {
			ParamVo vo = new ParamVo();
			vo.setParamName("subject");
			vo.setParamValue(subject);
			params.add(vo);
		}
		String virtualConfId = request.getParameter("virtualConfId");
		if(virtualConfId != null && !virtualConfId.equals("")){
			ParamVo vo = new ParamVo();
			vo.setParamName("virtualConfId");
			vo.setParamValue(virtualConfId);
			params.add(vo);
		}
		String listType = request.getParameter("listType");
		if(listType!=null && !listType.equals("")){
			if("all".equals(listType)){
				if(!up.hasCodePrivilege("0021")&&flag){
					ParamVo vo = new ParamVo();
					vo.setParamName("userId");
					vo.setParamValue(up.getUserId());
					params.add(vo);
				}
			}
			if("running".equals(listType)){
				ParamVo vo = new ParamVo();
				vo.setParamName("status");
				//vo.setParamValue(Conference.status_ongoing);
				vo.setParamValue(String.valueOf(Conference.status_ongoing));
				params.add(vo);
				if(!up.hasCodePrivilege("0017")&&flag){
					vo = new ParamVo();
					vo.setParamName("userId");
					vo.setParamValue(up.getUserId());
					params.add(vo);
				}
			}else if("currentDay".equals(listType)){
				
				Calendar c = Calendar.getInstance();
				
				DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
				try{
					String day = request.getParameter("day");
					if(day!=null && !day.equals("")){
						c.setTime(df.parse(day));
					}
					Date startTime = df.parse(df.format(c.getTime()));
					c.add(Calendar.DATE, 1);
					Date endTime = df.parse(df.format(c.getTime()));
					ParamVo vo = new ParamVo();
					vo.setParamName("startTime");
					vo.setParamValue(startTime);
					params.add(vo);
					vo = new ParamVo();
					vo.setParamName("endTime");
					vo.setParamValue(endTime);
					params.add(vo);
					if(!up.hasCodePrivilege("0018")&&flag){
						vo = new ParamVo();
						vo.setParamName("userId");
						vo.setParamValue(up.getUserId());
						params.add(vo);
					}
				}catch(Exception e){
					logger.error(e.toString());
				}
			} else if("specialDay".equals(listType)){
				DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
				String day = request.getParameter("day");
				Calendar c = Calendar.getInstance();
				try{
					c.setTime(df.parse(day));
					Date startTime = c.getTime();
					c.add(Calendar.DATE, 1);
					Date endTime = c.getTime();
					ParamVo vo = new ParamVo();
					vo.setParamName("startTime");
					vo.setParamValue(startTime);
					params.add(vo);
					vo = new ParamVo();
					vo.setParamName("endTime");
					vo.setParamValue(endTime);
					params.add(vo);
					
				}catch(Exception e){
					logger.error(e.toString());
				}
			} else if ("currentWeek".equals(listType)) {
				Calendar c = Calendar.getInstance();
				DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
				try{
					Date startTime = df.parse(df.format(c.getTime()));
					c.setTime(startTime);
					//c.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
					int weekday = c.get(Calendar.DAY_OF_WEEK);
					weekday = (weekday+5) % 7;
					c.add(Calendar.DATE, 0-weekday);
					startTime = df.parse(df.format(c.getTime()));
					c.add(Calendar.DATE, 7);
					Date endTime = df.parse(df.format(c.getTime()));
					ParamVo vo = new ParamVo();
					vo.setParamName("startTime");
					vo.setParamValue(startTime);
					params.add(vo);
					vo = new ParamVo();
					vo.setParamName("endTime");
					vo.setParamValue(endTime);
					params.add(vo);
					if(!up.hasCodePrivilege("0019")&&flag){
						vo = new ParamVo();
						vo.setParamName("userId");
						vo.setParamValue(up.getUserId());
						params.add(vo);
					}
				}catch(Exception e){
					logger.error(e.toString());
				}
			} else if ("currentMonth".equals(listType)) {
				Calendar c = Calendar.getInstance();
				DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
				try{
					Date startTime = df.parse(df.format(c.getTime()));
					c.setTime(startTime);
					c.set(Calendar.DAY_OF_MONTH, 1);
					startTime = df.parse(df.format(c.getTime()));
					c.add(Calendar.MONTH, 1);
					Date endTime = df.parse(df.format(c.getTime()));
					ParamVo vo = new ParamVo();
					vo.setParamName("startTime");
					vo.setParamValue(startTime);
					params.add(vo);
					vo = new ParamVo();
					vo.setParamName("endTime");
					vo.setParamValue(endTime);
					params.add(vo);
					if(!up.hasCodePrivilege("0020")&&flag){
						vo = new ParamVo();
						vo.setParamName("userId");
						vo.setParamValue(up.getUserId());
						params.add(vo);
					}
				}catch(Exception e){
					logger.error(e.toString());
				}
			}
		}
		List<Conference> confList = confService.findConfs(params, ph);
		return confList;
	}
	public String generateExcel(){
		logger.info("generateExcel...");
		exportToExcel(getConfList(new PageHolder()));
		return null;
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
		String personal = request.getParameter("personal");
		ParamVo vo = new ParamVo();
		vo.setParamName("status");
		vo.setParamValue(Conference.status_upcoming + "," + Conference.status_tobescheduled);
		params.add(vo);
		if(personal.equals("true")){
			UserPrivilege up = (UserPrivilege)session.get("userPrivilege");
			vo = new ParamVo();
			vo.setParamName("userId");
			vo.setParamValue(up.getUserId());
			params.add(vo);
		}
		if (subject != null && !subject.equals("")) {
			vo = new ParamVo();
			vo.setParamName("subject");
			vo.setParamValue(subject);
			params.add(vo);
		}
		if (serviceTemplate != null && serviceTemplate.length() > 0) {
			vo = new ParamVo();
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
	
	public String searchRecurrences() {
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
		String personal = request.getParameter("personal");
		ParamVo vo = new ParamVo();
		vo.setParamName("status");
		vo.setParamValue(RecurringMeetingInfo.status_upcoming);
		params.add(vo);
		if(personal.equals("true")){
			UserPrivilege up = (UserPrivilege)session.get("userPrivilege");
			vo = new ParamVo();
			vo.setParamName("userId");
			vo.setParamValue(up.getUserId());
		}
		if (subject != null && !subject.equals("")) {
			vo = new ParamVo();
			vo.setParamName("subject");
			vo.setParamValue(subject);
			params.add(vo);
		}
		if (serviceTemplate != null && serviceTemplate.length() > 0) {
			vo = new ParamVo();
			vo.setParamName("serviceTemplate");
			vo.setParamValue(serviceTemplate);
			params.add(vo);
		}
		//List<Conference> confList = confService.findConfs(params, ph);
		List<RecurringMeetingInfo> recurList = confService.findRecurrences(params, ph);
		try {
			JSONObject json = new JSONObject();
			json.put("totalProperty", ph.getResultSize());
			JSONArray arr = JSONArray.fromObject(recurList);
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
	
	public String searchConfType(){
		try{
			logger.info("search...");
			List<FieldDesc> l = confService.findConfTypes();
			List<FieldDescId> confTypeList = new ArrayList<FieldDescId>();
			for(int i=0;i<l.size();i++){
				confTypeList.add(l.get(i).getId());
			}
			JSONObject json = new JSONObject();
			JSONArray arr = JSONArray.fromObject(confTypeList);
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
	public String reserveConf() {
		String personal = request.getParameter("personal");
		if(personal!=null&&!personal.equals("")){
			request.setAttribute("personal", personal);
		}
		UserPrivilege up = (UserPrivilege)session.get("userPrivilege");
		User user = userService.getUser(up.getUserId());
		if(user!=null){
			request.setAttribute("defaultUnitId", user.getDefaultUnitId());
		}
		String defaultServiceTemplateId = PropertiesHelper.getDefaultServiceTemplateId();
		request.setAttribute("defaultServiceTemplateId", defaultServiceTemplateId);
		return "reserveConf";
	}
	
	public String reserveRecurrence(){
		request.setAttribute("recurrence", "true");
		return reserveConf();
	}

	public String save() throws IOException, ParseException {
		logger.info("save...");
		conf.setStatus(Conference.status_tobescheduled);
		UserPrivilege up = (UserPrivilege) session.get("userPrivilege");
		String userId = up != null ? up.getUserId() : PropertiesHelper.getIcmDefaultUserId();
		conf.setUserId(userId);
		conf.setMemberId(PropertiesHelper.getIcmDefaultMemberId());
		Date d = Calendar.getInstance().getTime();
		conf.setCreateTime(d);
		conf.setUpdateTime(d);
		long delayTime = VcmProperties.getPropertyByLong("vcm.delayAdhocStartTime", 20) * 1000;
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String startTime = request.getParameter("startTime");
		if (startTime != null && !startTime.equals("") && !"现在".equals(startTime)) {
			long st = df.parse(startTime).getTime();
			long now = d.getTime();
			if (now + delayTime >= st)
				st = now + delayTime;
			conf.setStartTime(st);
		} else {
			conf.setStartTime(d.getTime() + delayTime);
		}
		response.setContentType("text/html;charset=utf-8");

		String units = request.getParameter("confUnits");
		String[] unitList = null;
		if (units != null && !units.equals(""))
			unitList = units.split(",");

		try {
			conf.setStatus(Conference.status_upcoming);
			if(conf.getIsBroadcast()==null){
				conf.setIsBroadcast((short)0);
			}
			if(conf.getIsSupport()==null){
				conf.setIsSupport((short)0);
			}
			if(conf.getIsRecord()==null){
				conf.setIsRecord((short)0);
			}
			boolean validate = VcmProperties.getPropertyByBoolean("vcm.sms.validate", false);
			if(validate){
				String reserveCode = (String)session.get("reserveCode");
				Date reserveCodeExpiredTime = (Date)session.get("reserveCodeExpiredTime");
				if(reserveCode == null){
					throw new ReserveCodeNotExistException("预约码未生成");
				}else{
					if(conf.getReserveCode()!=null && !conf.getReserveCode().equals("") && conf.getReserveCode().equals(reserveCode) && (new Date()).before(reserveCodeExpiredTime)){
						logger.info("预约码输入正确!");
					}else{
						throw new ReserveCodeNotExistException("预约码输入错误或者已失效!");
					}
				}
			}
			confService.createConf(conf, unitList);
			logService.addLog(up.getUserId(), ILogService.type_reserve_conf, "预约会议"+conf.getRadConferenceId());
			outJson("{success:true, msg:'预约会议成功!'}");
		} catch (Exception e) {
			outJson("{success:false, msg:'"+"预约会议失败,"+e.getMessage()+"'}");
			return null;
		}
		try{
			String message = VcmProperties.getProperty("vcm.sms.reserve_notify_admin");
			String admins = VcmProperties.getProperty("vcm.sms.receive_notify_admin");
			if(admins!=null && !"".equals(admins)){
				User user = userService.getUser(userId);
				String[] arr = admins.split(",");
				for(int i=0;i<arr.length;i++){
					SendMessage sendMessage = new SendMessage();
					sendMessage.setMsisdn(arr[i]);
					sendMessage.setMessage(message.replaceFirst("\\{0\\}",df.format(d)).replaceFirst("\\{1\\}", user.getUserName())
							.replaceFirst("\\{2\\}", startTime).replaceFirst("\\{3\\}", conf.getSubject())
							.replaceFirst("\\{4\\}", conf.getTimeLong().toString()).replaceFirst("\\{5\\}", ""+unitList.length));
					smsUtil.sendMessage(sendMessage);
				}
			}
		}catch(Exception e){
			logger.error(e.toString());
		}
		return null;
	}
	
	public String saveRecurrence() throws IOException, ParseException {
		logger.info("save recurrence...");
		conf.setStatus(Conference.status_tobescheduled);
		UserPrivilege up = (UserPrivilege) session.get("userPrivilege");
		String userId = up != null ? up.getUserId() : PropertiesHelper.getIcmDefaultUserId();
		conf.setUserId(userId);
		conf.setMemberId(PropertiesHelper.getIcmDefaultMemberId());
		Date d = Calendar.getInstance().getTime();
		conf.setCreateTime(d);
		conf.setUpdateTime(d);
		long delayTime = VcmProperties.getPropertyByLong("vcm.delayAdhocStartTime", 20) * 1000;
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String startTime = request.getParameter("startTime");
		if (startTime != null && !startTime.equals("") && !"现在".equals(startTime)) {
			long st = df.parse(startTime).getTime();
			long now = d.getTime();
			if (now + delayTime >= st)
				st = now + delayTime;
			conf.setStartTime(st);
		} else {
			conf.setStartTime(d.getTime() + delayTime);
		}
		response.setContentType("text/html;charset=utf-8");

		String units = request.getParameter("confUnits");
		String[] unitList = null;
		if (units != null && !units.equals(""))
			unitList = units.split(",");

		try {
			conf.setStatus(Conference.status_upcoming);
			if(conf.getIsBroadcast()==null){
				conf.setIsBroadcast((short)0);
			}
			if(conf.getIsSupport()==null){
				conf.setIsSupport((short)0);
			}
			if(conf.getIsRecord()==null){
				conf.setIsRecord((short)0);
			}
			BeanUtils.copyProperties(conf, recurrence);
			String dayInterval = request.getParameter("dayInterval");
			String weekInterval = request.getParameter("weekInterval");
			String weekDay = request.getParameter("weekDay");
			String monthInterval = request.getParameter("monthInterval");
			String monthDay = request.getParameter("monthDay");
			String endDate = request.getParameter("endDate");
			String endAfterNumber = request.getParameter("endAfterNumber");
			if (recurrence.getRecurrenceType() == RecurringMeetingInfo.RECURRING_DAILY){
				//日例会
				recurrence.setDayInterval(Integer.parseInt(dayInterval));
			} else if (recurrence.getRecurrenceType() == RecurringMeetingInfo.RECURRING_WEEKLY){
				//周例会
				recurrence.setWeekInterval(Integer.parseInt(weekInterval));
				recurrence.setWeekDay(Integer.parseInt(weekDay));
			} else {
				//月例会
				recurrence.setMonthDay(Integer.parseInt(monthDay));
				recurrence.setMonthInterval(Integer.parseInt(monthInterval));
			}
			if (recurrence.getEndType() == RecurringMeetingInfo.RECURRING_ENDTYPE_DATE){
				DateFormat df1 = new SimpleDateFormat("yyyy-MM-dd HH:mm");
				recurrence.setEndDate(df1.parse(endDate).getTime());
			}else{
				recurrence.setEndAfterNumber(Integer.parseInt(endAfterNumber));
			}
			
			//confService.createConf(conf, unitList);
			confService.createRecurrence(recurrence, unitList);
			logService.addLog(up.getUserId(), ILogService.type_reserve_conf, "预约例会"+recurrence.getRadRecurrenceId());
			outJson("{success:true, msg:'预约例会成功!'}");
		} catch (Exception e) {
			outJson("{success:false, msg:'预约例会失败'}");
		}
		return null;
	}

	public String modifyReserve() {
		logger.info("modifyReserve...");
		String personal = request.getParameter("personal");
		if(personal!=null && !personal.equals("")){
			request.setAttribute("personal", personal);
		}
		String id = request.getParameter("conferenceId");
		conf = confService.getConfById(id);
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Calendar c = Calendar.getInstance();
		c.setTimeInMillis(conf.getStartTime());
		request.setAttribute("startTime", df.format(c.getTime()));
		return "modifyReserve";
	}
	
	public String modifyRecurrence(){
		logger.info("modifyRecurrence...");
		String personal = request.getParameter("personal");
		if(personal!=null && !personal.equals("")){
			request.setAttribute("personal", personal);
		}
		String id = request.getParameter("recurrenceId");
		//conf = confService.getConfById(id);
		recurrence = confService.getRecurrenceById(id);
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Calendar c = Calendar.getInstance();
		c.setTimeInMillis(recurrence.getStartTime());
		request.setAttribute("startTime", df.format(c.getTime()));
		request.setAttribute("recurrence", "true");
		conf = new Conference();
		BeanUtils.copyProperties(recurrence, conf);
		DateFormat df1 = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		if(recurrence.getEndType()==RecurringMeetingInfo.RECURRING_ENDTYPE_DATE){
			request.setAttribute("endDate", df1.format(recurrence.getEndDate()));
		}
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
			conf.setServiceTemplateId(room.getServiceTemplate());
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
		logger.info("update...");
		UserPrivilege up = (UserPrivilege)session.get("userPrivilege");
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
		oldConf.setServiceTemplateId(conf.getServiceTemplateId());
		oldConf.setConfType(conf.getConfType());
		oldConf.setSubject(conf.getSubject());
		String oldSubject = oldConf.getSubject();
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
		if(conf.getIsBroadcast()==null){
			oldConf.setIsBroadcast((short)0);
		}else{
			oldConf.setIsBroadcast(conf.getIsBroadcast());
		}
		if(conf.getIsSupport()==null){
			oldConf.setIsSupport((short)0);
		}else{
			oldConf.setIsSupport(conf.getIsSupport());
		}
		if(conf.getIsRecord()==null){
			oldConf.setIsRecord((short)0);
		}else{
			oldConf.setIsRecord(conf.getIsRecord());
		}
		response.setContentType("text/html;charset=utf-8");

		String units = request.getParameter("confUnits");
		String[] unitList = null;
		if (units != null && !units.equals(""))
			unitList = units.split(",");

		try {
			confService.modifyConf(oldConf, unitList);
			logService.addLog(up.getUserId(), ILogService.type_modify_conf, "修改会议"+oldConf.getRadConferenceId());
			outJson("{success:true, msg:'预约会议修改成功!'}");
		} catch (Exception e) {
			outJson("{success:false, msg:'预约会议修改失败!'}");
		}
		try{
			User user = userService.getUser(up.getUserId());
			if(up.getUserId().equals(oldConf.getUserId())){
				//自己修改自己的会议
				String message = VcmProperties.getProperty("vcm.sms.modify_notify_admin");
				String admins = VcmProperties.getProperty("vcm.sms.receive_notify_admin");
				if(admins!=null && !"".equals(admins)){
					
					String[] arr = admins.split(",");
					for(int i=0;i<arr.length;i++){
						SendMessage sendMessage = new SendMessage();
						sendMessage.setMsisdn(arr[i]);
						sendMessage.setMessage(message.replaceFirst("\\{0\\}",df.format(d)).replaceFirst("\\{1\\}", user.getUserName())
								.replaceFirst("\\{2\\}", oldSubject).replaceFirst("\\{3\\}", startTime)
								.replaceFirst("\\{4\\}", oldConf.getSubject()).replaceFirst("\\{5\\}", oldConf.getTimeLong().toString())
								.replaceFirst("\\{6\\}", ""+unitList.length));
						smsUtil.sendMessage(sendMessage);
					}
				}
			}else{
				//管理员修改用户的会议
				String message = VcmProperties.getProperty("vcm.sms.modify_notify_user");
				SendMessage sendMessage = new SendMessage();
				User oldUser = userService.getUser(oldConf.getUserId());
				sendMessage.setMsisdn(oldUser.getMobile());
				//sendMessage.setMsisdn(user.getMobile());
				sendMessage.setMessage(message.replaceFirst("\\{0\\}", oldUser.getUserName())
						.replaceFirst("\\{1\\}", startTime)
						.replaceFirst("\\{2\\}", oldSubject));
				smsUtil.sendMessage(sendMessage);
			}
		}catch(Exception e){
			logger.error(e.toString());
		}
		return null;
	}
	
	public String updateRecurrence() throws Exception {
		logger.info("update recurrence...");
		UserPrivilege up = (UserPrivilege)session.get("userPrivilege");
		//Conference oldConf = confService.getConfById(conf.getConferenceId()
		//		.toString());
		RecurringMeetingInfo oldRecur = confService.getRecurrenceById(recurrence.getRecurrenceId().toString());
		Date d = Calendar.getInstance().getTime();
		oldRecur.setUpdateTime(d);
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String startTime = request.getParameter("startTime");
		if (startTime != null && !startTime.equals("")) {
			oldRecur.setStartTime(df.parse(startTime).getTime());
		}
		oldRecur.setContactMethod(conf.getContactMethod());
		oldRecur.setServiceTemplateId(conf.getServiceTemplateId());
		oldRecur.setConfType(conf.getConfType());
		oldRecur.setSubject(conf.getSubject());
		oldRecur.setControlPin(conf.getControlPin());
		oldRecur.setPassword(conf.getPassword());
		oldRecur.setDialableNumber(conf.getDialableNumber());
		oldRecur.setInitUnit(conf.getInitUnit());
		oldRecur.setTimeLong(conf.getTimeLong());
		oldRecur.setMainUnit(conf.getMainUnit());
		oldRecur.setPresider(conf.getPresider());
		oldRecur.setPrincipal(conf.getPrincipal());
		oldRecur.setPrincipalMobile(conf.getPrincipalMobile());
		oldRecur.setReserveCode(conf.getReserveCode());
		oldRecur.setDescription(conf.getDescription());
		if(conf.getIsBroadcast()==null){
			oldRecur.setIsBroadcast((short)0);
		}else{
			oldRecur.setIsBroadcast(conf.getIsBroadcast());
		}
		if(conf.getIsSupport()==null){
			oldRecur.setIsSupport((short)0);
		}else{
			oldRecur.setIsSupport(conf.getIsSupport());
		}
		if(conf.getIsRecord()==null){
			oldRecur.setIsRecord((short)0);
		}else{
			oldRecur.setIsRecord(conf.getIsRecord());
		}
		String dayInterval = request.getParameter("dayInterval");
		String weekInterval = request.getParameter("weekInterval");
		String weekDay = request.getParameter("weekDay");
		String monthInterval = request.getParameter("monthInterval");
		String monthDay = request.getParameter("monthDay");
		String endDate = request.getParameter("endDate");
		String endAfterNumber = request.getParameter("endAfterNumber");
		oldRecur.setRecurrenceType(recurrence.getRecurrenceType());
		if(recurrence.getRecurrenceType() == RecurringMeetingInfo.RECURRING_DAILY){
			//日例会
			oldRecur.setDayInterval(Integer.parseInt(dayInterval));
		} else if (recurrence.getRecurrenceType() == RecurringMeetingInfo.RECURRING_WEEKLY){
			//周例会
			oldRecur.setWeekInterval(Integer.parseInt(weekInterval));
			oldRecur.setWeekDay(Integer.parseInt(weekDay));
		} else {
			//月例会
			oldRecur.setMonthInterval(Integer.parseInt(monthInterval));
			oldRecur.setMonthDay(Integer.parseInt(monthDay));
		}
		oldRecur.setEndType(recurrence.getEndType());
		if (recurrence.getEndType() == RecurringMeetingInfo.RECURRING_ENDTYPE_DATE){
			DateFormat df1 = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			oldRecur.setEndDate(df1.parse(endDate).getTime());
		}else{
			oldRecur.setEndAfterNumber(Integer.parseInt(endAfterNumber));
		}
		response.setContentType("text/html;charset=utf-8");

		String units = request.getParameter("confUnits");
		String[] unitList = null;
		if (units != null && !units.equals(""))
			unitList = units.split(",");

		try {
			//confService.modifyConf(oldConf, unitList);
			confService.modifyRecurrence(oldRecur, unitList);
			logService.addLog(up.getUserId(), ILogService.type_modify_conf, "修改例会"+oldRecur.getRadRecurrenceId());
			outJson("{success:true, msg:'预约例会修改成功!'}");
		} catch (Exception e) {
			outJson("{success:false, msg:'预约例会修改失败!'}");
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

	public String recurrenceDetail(){
		String recurId = request.getParameter("recurrenceId");
		recurrence = confService.getRecurrenceById(recurId);
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Calendar c = Calendar.getInstance();
		c.setTimeInMillis(recurrence.getStartTime());
		request.setAttribute("startTime", df.format(c.getTime()));
		BeanUtils.copyProperties(recurrence, conf);
		DateFormat df1 = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		if(recurrence.getEndType()==RecurringMeetingInfo.RECURRING_ENDTYPE_DATE){
			request.setAttribute("endDate", df1.format(recurrence.getEndDate()));
		}
		request.setAttribute("recurrence", "true");
		return "reserveDetail";
	}
	
	public String manageReserve() {
		request.setAttribute("personal", "false");
		return "listReserve";
	}
	
	public String manageRecurrence(){
		request.setAttribute("personal", "false");
		return "listRecurrence";
	}

	public String getUnitsByConfId() {
		UserPrivilege up = (UserPrivilege)session.get("userPrivilege");
		String userId = up.getUserId();
		String loginId = up.getLoginId();
		
		String id = request.getParameter("conferenceId");
		boolean selected = "true".equalsIgnoreCase(request
				.getParameter("selected"));
		List<Unit> units;
		if(!loginId.equals("user")&&!selected){
			units = confService.findUnitsByConfId(id, selected, userId);
		}else{
			units = confService.findUnitsByConfId(id, selected, null);
		}
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
		String userId = request.getParameter("userId");
		List<Unit> confUnits;
		if(userId!=null && !userId.equals("super")){
			confUnits = confService.findUnitsByConfId(id, false, userId);
		}else{
			confUnits = confService.findUnitsByConfId(id, false, null);
		}
		
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
	private void exportToExcel(List<Conference> l){
		try{
			response.setCharacterEncoding("utf-8");
			response.setContentType("application/vnd.ms-excel");
			OutputStream out = response.getOutputStream();
			//String fileName = new String("正在召开的会议.xls".getBytes("UTF-8"),"ISO8859-1");
			String fileName = new String("list.xls");
			response.addHeader("Content-Disposition", "attachment;filename="+fileName);
			HSSFWorkbook wb = new HSSFWorkbook();
			HSSFSheet sheet = wb.createSheet();
			wb.setSheetName(0, "正在召开的会议");
			HSSFRow row = sheet.createRow(0);
			HSSFCell cell = row.createCell(0);
			cell.setCellValue(new HSSFRichTextString("主题"));
			cell = row.createCell(1);
			cell.setCellValue(new HSSFRichTextString("会议号"));
			cell = row.createCell(2);
			cell.setCellValue(new HSSFRichTextString("组织单位"));
			cell = row.createCell(3);
			cell.setCellValue(new HSSFRichTextString("开始时间"));
			cell = row.createCell(4);
			cell.setCellValue(new HSSFRichTextString("时长"));
			cell = row.createCell(5);
			cell.setCellValue(new HSSFRichTextString("会议负责人"));
			cell = row.createCell(6);
			cell.setCellValue(new HSSFRichTextString("负责人手机"));
			cell = row.createCell(7);
			cell.setCellValue(new HSSFRichTextString("联系方式"));
			cell = row.createCell(8);
			cell.setCellValue(new HSSFRichTextString("主要议题"));
			HSSFCellStyle cellStyle = wb.createCellStyle();
			cellStyle.setDataFormat(HSSFDataFormat.getBuiltinFormat("m/d/yy h:mm"));
			for(int i=0;i<l.size();i++){
				Conference c = l.get(i);
				row = sheet.createRow(i+1);
				cell = row.createCell(0);
				cell.setCellValue(new HSSFRichTextString(c.getSubject()));
				cell = row.createCell(1);
				cell.setCellValue(new HSSFRichTextString(c.getVirtualConfId()));
				cell = row.createCell(2);
				cell.setCellValue(new HSSFRichTextString(c.getInitUnit()));
				cell = row.createCell(3);
				Date startTime = new Date(c.getStartTime());
				cell.setCellValue(startTime);
				cell.setCellStyle(cellStyle);
				cell = row.createCell(4);
				cell.setCellValue(c.getTimeLong());
				cell = row.createCell(5);
				cell.setCellValue(new HSSFRichTextString(c.getPrincipal()));
				cell = row.createCell(6);
				cell.setCellValue(new HSSFRichTextString(c.getPrincipalMobile()));
				cell = row.createCell(7);
				cell.setCellValue(new HSSFRichTextString(c.getContactMethod()));
				cell = row.createCell(8);
				cell.setCellValue(new HSSFRichTextString(c.getDescription()));
			}
			wb.write(out);
			out.flush();
			out.close();
		}catch(Exception e){
			logger.error(e.toString());
		}
	}
	private void exportToPDF(List<Conference> l){
		Document document = new Document();
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		try{
			PdfWriter.getInstance(document, baos);
			document.open();
			BaseFont bfChinese = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H",    
		            BaseFont.NOT_EMBEDDED);              
			Font fontChinese = new Font(bfChinese, 12, Font.NORMAL);    
			PdfPTable table = new PdfPTable(9);
			table.setWidthPercentage(100);
			float[] widths = {5f,3f,4f,4f,3f,4f,4f,6f,6f};
			table.setWidths(widths);
			PdfPCell cell = new PdfPCell(new Paragraph("正在召开的会议",fontChinese));
			cell.setColspan(9);
			table.addCell(cell);
			cell  = new PdfPCell(new Paragraph("主题",fontChinese));
			table.addCell(cell);
			cell  = new PdfPCell(new Paragraph("会议号",fontChinese));
			table.addCell(cell);
			cell  = new PdfPCell(new Paragraph("组织单位",fontChinese));
			table.addCell(cell);
			cell  = new PdfPCell(new Paragraph("开始时间",fontChinese));
			table.addCell(cell);
			cell  = new PdfPCell(new Paragraph("时长",fontChinese));
			table.addCell(cell);
			cell  = new PdfPCell(new Paragraph("会议负责人",fontChinese));
			table.addCell(cell);
			cell  = new PdfPCell(new Paragraph("负责人手机",fontChinese));
			table.addCell(cell);
			cell  = new PdfPCell(new Paragraph("联系方式",fontChinese));
			table.addCell(cell);
			cell  = new PdfPCell(new Paragraph("主要议题",fontChinese));
			table.addCell(cell);
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			for(int i=0;i<l.size();i++){
				Conference c = l.get(i);
				table.addCell(new Paragraph(c.getSubject(),fontChinese));
				table.addCell(c.getVirtualConfId());
				table.addCell(new Paragraph(c.getInitUnit(),fontChinese));
				Date startTime = new Date(c.getStartTime());
				table.addCell(df.format(startTime));
				table.addCell(c.getTimeLong().toString());
				table.addCell(new Paragraph(c.getPrincipal(),fontChinese));
				table.addCell(new Paragraph(c.getPrincipalMobile(),fontChinese));
				table.addCell(new Paragraph(c.getContactMethod(),fontChinese));
				table.addCell(new Paragraph(c.getDescription(),fontChinese));
			}
			document.add(table);
			document.close();
			response.setContentType("application/pdf");
			response.setCharacterEncoding("utf-8");
			response.setContentLength(baos.size());
			ServletOutputStream out = response.getOutputStream();
			baos.writeTo(out);
			out.flush();
			out.close();
		}catch(Exception e){
			logger.error(e.toString());
		}
	}
	public String getConnectStatus(){
		try{
			response.setCharacterEncoding("utf-8");

			PrintWriter out = response.getWriter();
			boolean b = ICMService.isConnected();
			if(b){
				out.print("已连接iView");
			}else{
				out.print("未连接iView");
			}
			out.flush();
			out.close();
		}catch(Exception e){
			logger.error(e.toString());
		}
		return null;
	}
	public String generateReserveCode(){
		logger.info("enter generateReserveCode()");
		PrintWriter out = null;
		try{
			response.setCharacterEncoding("utf-8");
			out = response.getWriter();
			String reserveCode = "";
			int i = (new Random()).nextInt(9999);
			if(i<1000){
				i = i+1000;
			}
			reserveCode += i;
			SendMessage sendMessage = new SendMessage();
			UserPrivilege up = (UserPrivilege)session.get("userPrivilege");
			User user = userService.getUser(up.getUserId());
			int delay = VcmProperties.getPropertyByInt("vcm.sms.delay", 5);
			//sendMessage.setMessage("您的预约码是:"+reserveCode+",有效时间为"+delay+"分钟.");
			//String message = VcmProperties.getProperty("vcm.sms.message", "您的预约码是:{0},有效时间为{1}分钟.");
			//sendMessage.setMessage(message.replaceFirst("\\{0\\}", reserveCode).replaceFirst("\\{1\\}", ""+delay));
			String message = VcmProperties.getProperty("vcm.sms.message", "{0},您的预约码是:{1}");
			sendMessage.setMessage(message.replaceFirst("\\{0\\}", user.getUserName()).replaceFirst("\\{1\\}", ""+reserveCode));
			
			sendMessage.setMsisdn(user.getMobile());
			
			session.put("reserveCode", reserveCode);
			Calendar c = Calendar.getInstance();
			c.add(Calendar.MINUTE, delay);
			session.put("reserveCodeExpiredTime", c.getTime());
			logger.info("reserveCode = "+reserveCode);
			boolean b = smsUtil.sendMessage(sendMessage);
			logger.info("b = "+b);
			if(b){
				out.print("{success:true,msg:'预约码已经发送到您的手机,请在"+delay+"分钟内输入您的预约码'}");
			}else{
				out.print("{success:false,msg:'生成预约码失败!'}");
			}
			
		}catch(Exception e){
			logger.error(e.toString());
			out.print("{success:false,msg:'生成预约码失败!'}");
		}finally{
			out.flush();
			out.close();
		}
		return null;
	}
	public String verifyReserveCode(){
		PrintWriter out = null;
		try{
			response.setCharacterEncoding("utf-8");
			out = response.getWriter();
			String reserveCode = (String)session.get("reserveCode");
			String value = request.getParameter("value");
			Date d = (Date)session.get("reserveCodeExpiredTime");
			if(Calendar.getInstance().getTime().before(d) && reserveCode.equals(value)){
				out.print("{success:true,msg:'验证预约码成功！'}");
			}else{
				out.print("{success:false,msg:'验证预约码失败！'}");
			}
		}catch(Exception e){
			logger.error(e.toString());
			out.print("{success:false,msg:'验证预约码失败！'}");
		}finally{
			out.flush();
			out.close();
		}
		return null;
	}
}
