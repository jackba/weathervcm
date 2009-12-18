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

import com.cma.intervideo.pojo.Conference;
import com.cma.intervideo.pojo.FieldDesc;
import com.cma.intervideo.pojo.FieldDescId;
import com.cma.intervideo.pojo.Unit;
import com.cma.intervideo.pojo.VirtualRoom;
import com.cma.intervideo.service.IConfService;
import com.cma.intervideo.service.ILogService;
import com.cma.intervideo.service.IRoomService;
import com.cma.intervideo.util.AbstractBaseAction;
import com.cma.intervideo.util.PageHolder;
import com.cma.intervideo.util.ParamVo;
import com.cma.intervideo.util.PropertiesHelper;
import com.cma.intervideo.util.UserPrivilege;
import com.cma.intervideo.util.VcmProperties;
import com.lowagie.text.Document;
import com.lowagie.text.Font;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

public class ConfAction extends AbstractBaseAction {
	private static Log logger = LogFactory.getLog(AbstractBaseAction.class);
	private IConfService confService;
	private IRoomService roomService;
	private ILogService logService;
	private Conference conf;
	private Integer conferenceId;
	
	public void setLogService(ILogService logService) {
		this.logService = logService;
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
	
	public String listRunning(){
		String ipport = VcmProperties.getProperty("vcm.icm.ipport");
		String monitorUrl = VcmProperties.getProperty("vcm.icm.monitorUrl");
		monitorUrl = "http://"+ipport+monitorUrl;
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
		String ipport = VcmProperties.getProperty("vcm.icm.ipport");
		String monitorUrl = VcmProperties.getProperty("vcm.icm.monitorUrl");
		monitorUrl = "http://"+ipport+monitorUrl;
		request.setAttribute("monitorUrl", monitorUrl);
		return "listCurrentDay";
	}
	public String listCurrentWeek(){
		String ipport = VcmProperties.getProperty("vcm.icm.ipport");
		String monitorUrl = VcmProperties.getProperty("vcm.icm.monitorUrl");
		monitorUrl = "http://"+ipport+monitorUrl;
		request.setAttribute("monitorUrl", monitorUrl);
		return "listCurrentWeek";
	}
	public String listCurrentMonth(){
		String ipport = VcmProperties.getProperty("vcm.icm.ipport");
		String monitorUrl = VcmProperties.getProperty("vcm.icm.monitorUrl");
		monitorUrl = "http://"+ipport+monitorUrl;
		request.setAttribute("monitorUrl", monitorUrl);
		return "listCurrentMonth";
	}
	public String listAll(){
		String ipport = VcmProperties.getProperty("vcm.icm.ipport");
		String monitorUrl = VcmProperties.getProperty("vcm.icm.monitorUrl");
		monitorUrl = "http://"+ipport+monitorUrl;
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
		String totalProperty = request.getParameter("totalProperty");
		ph.setFirstIndex(Integer.parseInt(start));
		ph.setPageSize(Integer.parseInt(limit));
		if (totalProperty != null && !totalProperty.equals("")) {
			ph.setResultSize(Integer.parseInt(totalProperty));
		}
		List<ParamVo> params = new ArrayList<ParamVo>();
		String subject = request.getParameter("subject");
		if (subject != null && !subject.equals("")) {
			ParamVo vo = new ParamVo();
			vo.setParamName("subject");
			vo.setParamValue(subject);
			params.add(vo);
		}
		String dialableNumber = request.getParameter("dialableNumber");
		if(dialableNumber != null && !dialableNumber.equals("")){
			ParamVo vo = new ParamVo();
			vo.setParamName("dialableNumber");
			vo.setParamValue(dialableNumber);
			params.add(vo);
		}
		String listType = request.getParameter("listType");
		if(listType!=null && !listType.equals("")){
			if("running".equals(listType)){
				ParamVo vo = new ParamVo();
				vo.setParamName("status");
				//vo.setParamValue(Conference.status_ongoing);
				vo.setParamValue(String.valueOf(Conference.status_ongoing));
				params.add(vo);
			}else if("currentDay".equals(listType)){
				Calendar c = Calendar.getInstance();
				DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
				try{
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
					c.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
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
		String personal = request.getParameter("psersonal");
		if(personal!=null&&!personal.equals("")){
			request.setAttribute("personal", personal);
		}
		return "reserveConf";
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
			logService.addLog(up.getUserId(), ILogService.type_reserve_conf, "预约会议"+conf.getRadConferenceId());
			outJson("{success:true, msg:'预约会议成功!'}");
		} catch (Exception e) {
			outJson("{success:true, msg:'预约会议失败'}");
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
			logService.addLog(up.getUserId(), ILogService.type_modify_conf, "修改会议"+oldConf.getRadConferenceId());
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
				cell.setCellValue(new HSSFRichTextString(c.getDialableNumber()));
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
				table.addCell(c.getDialableNumber());
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
}
