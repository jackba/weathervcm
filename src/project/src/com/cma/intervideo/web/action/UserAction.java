package com.cma.intervideo.web.action;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.cma.intervideo.exception.UserExistsException;
import com.cma.intervideo.pojo.BulletinBoard;
import com.cma.intervideo.pojo.Role;
import com.cma.intervideo.pojo.Unit;
import com.cma.intervideo.pojo.User;
import com.cma.intervideo.service.IBulletinService;
import com.cma.intervideo.service.ILogService;
import com.cma.intervideo.service.IUnitService;
import com.cma.intervideo.service.IUserService;
import com.cma.intervideo.util.AbstractBaseAction;
import com.cma.intervideo.util.PageHolder;
import com.cma.intervideo.util.UserPrivilege;
import com.sun.image.codec.jpeg.ImageFormatException;
import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;
public class UserAction extends AbstractBaseAction{
	private static Log logger = LogFactory.getLog(UserAction.class);
	private IUserService userService;
	private IUnitService unitService;
	private IBulletinService bulletinService;
	private ILogService logService;
	
	public void setLogService(ILogService logService) {
		this.logService = logService;
	}
	
	
	public void setUnitService(IUnitService unitService) {
		this.unitService = unitService;
	}


	private User user;
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public void setUserService(IUserService userService) {
		this.userService = userService;
	}
	
	public void setBulletinService(IBulletinService bulletinService) {
		this.bulletinService = bulletinService;
	}
	public String code(){
		System.out.println("进入code");
		String s = "";
		int intCount = 0;
		intCount = (new Random()).nextInt(9999);
		if (intCount < 1000)
			intCount += 1000;
		s = intCount + "";
		request.getSession().setAttribute("rand", s);
		response.setContentType("image/gif");
		ServletOutputStream out=null;
		try {
			out = response.getOutputStream();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		//在内存中创建图片
		   int width=60,height=20;
		   BufferedImage image=new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
		 //获取图形上下文
		   Graphics gra = image.getGraphics();
		// 
		gra.setColor(new Color(160,200,100));
		gra.fillRect(0,0,width,height);
		   //设置字体
		gra.setFont(new Font("Times New Roman",Font.PLAIN,18));
		// 
		Random random= new Random();
		char c;
		for (int i = 0; i < 4; i++) {
			c = s.charAt(i);
			 gra.setColor(new Color(20+random.nextInt(110),20+random.nextInt(110),20+random.nextInt(110)));
			gra.drawString(c + "", 13*i+6,16);
		}
		JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
		try {
			encoder.encode(image);
			out.close();
		} catch (ImageFormatException e) {
			
			e.printStackTrace();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		return null;
	}
	public String login(){
		String username = request.getParameter("username");
		logger.info("*********************************************************************************");
		logger.info("*********************************************************************************");
		logger.info("*********************************************************************************");
		logger.info("User trying to login VCM, username: " + username);
		User user = userService.findUserByLoginId(username);
//		if(user==null || !user.getPassword().equals(request.getParameter("password"))){
//			return "login";
//		}else{
//			System.out.println("login success!");
//			return "index";
//		}
		String rand = (String)session.get("rand");
		String validateCode = request.getParameter("validateCode");
		try{
			response.setCharacterEncoding("utf-8");
			PrintWriter pw = response.getWriter();
			if(username!=null && username.equals("guest")){
				validateCode = rand;
			}
			if(validateCode==null || validateCode.equals("")){
				pw.print("{success:true,msg:'验证码不能为空!'}");
			}else if(!validateCode.equals(rand)){
				pw.print("{success:true,msg:'验证码不正确!'}");
			}
			else if(user==null){
				logger.warn("Failed to login due to username " + username + " does not exist!!!!!");
				pw.print("{success:true,msg:'用户不存在!'}");
			}else if (!user.getPassword().equals(request.getParameter("password"))){
				logger.warn("Failed to login due to incorrect password!!!!!");
				pw.print("{success:true,msg:'密码不正确!'}");
			}else{
				UserPrivilege up = new UserPrivilege();
				up.setUserId(user.getUserId());
				up.setUserName(user.getUserName());
				up.setLoginId(user.getLoginId());
				
				up.setOwnPrivileges(userService.findPrivilegesByUserId(user.getUserId()));
				up.setUrls(userService.findUrlsByUserId(user.getUserId()));
				session.put("userPrivilege", up);
				logService.addLog(user.getUserId(), ILogService.type_login, "用户登陆");
				pw.print("{success:true,msg:'ok'}");
				
				logger.info("Successed to login VCM: " + up);
			}
			pw.flush();
			pw.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	public String logout(){
		UserPrivilege up = (UserPrivilege)session.get("userPrivilege");
		if (up != null)
			logService.addLog(up.getUserId(), ILogService.type_logout, "用户退出");

		HttpSession s = request.getSession(false);
		if (s != null)
			s.invalidate();
		
		logger.info("User logout: " 
				+ (up == null ? "None UserPrivilege" : up));
		logger.info("*********************************************************************************");
		logger.info("*********************************************************************************");
		logger.info("*********************************************************************************");
		
		return "login";
	}
	
	public String list(){
		return "userList";
	}
	
	public String detail(){
		String id = request.getParameter("userId");
		user = userService.getUser(id);
		return "detail";
	}
	
	public String modify(){
		String id = request.getParameter("userId");
		user = userService.getUser(id);
		request.setAttribute("personal", "false");
		return "modifyEdit";
	}
	
	public String personalModify(){
		UserPrivilege up = (UserPrivilege)session.get("userPrivilege");
		String id = up.getUserId();
		user = userService.getUser(id);
		request.setAttribute("personal", "true");
		return "modifyEdit";
	}
	
	public String beforResetPassword(){
		String id = request.getParameter("userId");
		user = userService.getUser(id);
		return "resetPassword";
	}
	
	public String resetPassword() throws IOException, UserExistsException{
		User u = userService.getUser(user.getUserId());
//		u.setPassword(new MD5().getMD5ofStr(user.getPassword()));
		u.setPassword(user.getPassword());
		response.setContentType("text/html;charset=utf-8");
		userService.updateUser(u);
		outJson("{success:true, msg:'重置操作员密码成功!'}");
		return null;
	}
	
	public String beforChangePassword(){
		return "changePassword";
	}
	
	public String changePassword() throws IOException, UserExistsException{
		String passwordPage = request.getParameter("passwordPage");
		UserPrivilege up = (UserPrivilege)session.get("userPrivilege");
		user = userService.getUser(up.getUserId());
		String passwordDB = user.getPassword();
		if(!(passwordDB.equals(passwordPage))){
			System.out.println("wrong");
			response.setContentType("text/html;charset=utf-8");
			outJson("{success:false, msg:'原密码输入错误!'}");
			return null;
		}else{
			String passwordNew = request.getParameter("user.password");
			user.setPassword(passwordNew);
			response.setContentType("text/html;charset=utf-8");
			userService.updateUser(user);
			outJson("{success:true, msg:'操作员修改密码成功!'}");
			return null;
		}		
	}

	public String search(){
		UserPrivilege up = (UserPrivilege)session.get("userPrivilege");
		String start = request.getParameter("start");
		String limit = request.getParameter("limit");
		String totalProperty = request.getParameter("totalProperty");
		PageHolder ph = new PageHolder();
		ph.setFirstIndex(Integer.parseInt(start));
		ph.setPageSize(Integer.parseInt(limit));
		if(totalProperty!=null && !totalProperty.equals("")){
			ph.setResultSize(Integer.parseInt(totalProperty));
		}
		String username = request.getParameter("username");
		String name = request.getParameter("name");
		String temp = request.getParameter("status");
		//以下代码对状态做判断处理
		Short status = -1;
		if((temp != null) && !temp.equals("")){
			 status = Short.parseShort(temp);
		} else if((temp != null) && temp.equals("")){
			status = null;
		}
//		System.out.println("status=" + status);
		try{
			JSONObject json = new JSONObject();
//			List users = userService.queryUsers(username, name, ph);
			List users = userService.queryUsersAndStatus(username, name, status, ph);
			for(int i=0;i<users.size();i++){
				User user = (User)users.get(i);
				if(user.getUserId().equals(up.getUserId())){
					users.remove(i);
					break;
				}
			}
			json.put("totalProperty", ph.getResultSize());
			JSONArray arr = JSONArray.fromObject(users);
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

	public String add(){
		List roles = userService.findAllRoles();
		List units = unitService.findAllUnits();
		request.setAttribute("units", units);
		request.setAttribute("roles", roles);
		return "userAdd";
	}
	public String getAllRoles(){
		List roles = userService.findAllRoles();
		JSONObject json = new JSONObject();
		JSONArray arr = JSONArray.fromObject(roles);
		json.put("root", arr);
		try{
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
	public String getAllRolesExclud(){
		List allRoles = userService.findAllRoles();
		String id = request.getParameter("userId");
		List userRoles = userService.findRolesByUserId(id);
		for(int i=0;i<userRoles.size();i++){
			Role role = (Role)userRoles.get(i);
			for (int inneri=0;inneri<allRoles.size();inneri++){
				Role inrole = (Role)allRoles.get(inneri);
				if (role.getRoleId().equals( inrole.getRoleId())){
					allRoles.remove(inneri);
				}
			}
		}
		JSONObject json = new JSONObject();
		JSONArray arr = JSONArray.fromObject(allRoles);
		json.put("root", arr);
		try{
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
//	public String modify(){
//		String id = request.getParameter("userId");
//		user = userService.getUser(Integer.parseInt(id));
//		return "modifyEdit";
//	}
	public String getRolesByUserId(){
		String id = request.getParameter("userId");
		List roles = userService.findRolesByUserId(id);
		JSONObject json = new JSONObject();
		JSONArray arr = JSONArray.fromObject(roles);
		json.put("root", arr);
		try{
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
	public String update() throws IOException{
		UserPrivilege up = (UserPrivilege)session.get("userPrivilege");
		response.setContentType("text/html;charset=utf-8");
		String roles = request.getParameter("roles");
		String units = request.getParameter("units");
		String[] roleList = null;
		String[] unitList = null;
		if(roles!=null && !roles.equals("")){
			roleList = roles.split(",");
		}else {
			outJson("{success:false, msg:'至少要为操作员选择一个角色!'}");
			return null;
		}
		if(units!=null && !units.equals("")){
			unitList = units.split(",");
		}else {
			outJson("{success:false, msg:'至少要为操作员选择一个主会场!'}");
			return null;
		}
		try{
			userService.updateUser(user, roleList, unitList);
			logService.addLog(up.getUserId(), ILogService.type_modify_user, "修改用户"+user.getLoginId());
			outJson("{success:true, msg:'操作员修改成功!'}");
		}catch(UserExistsException ue) {
			outJson("{success:false, msg:'操作员修改失败!'}");
		}
		return null;	
	}
	public String save() throws IOException{
		UserPrivilege up = (UserPrivilege)session.get("userPrivilege");
		//user.setPassword(new MD5().getMD5ofStr(user.getPassword()));
		user.setStatus((short)0);
		Date d = Calendar.getInstance().getTime();
		user.setCreateTime(d);
		user.setUpdateTime(d);
		response.setContentType("text/html;charset=utf-8");
		
		String roles = request.getParameter("roles");
		String units = request.getParameter("units");
		String[] roleList = null;
		String[] unitList = null;
		if(roles!=null && !roles.equals("")){
			roleList = roles.split(",");
		} else {
			outJson("{success:false, msg:'至少要为操作员选择一个角色!'}");
			return null;
		}
		if(units!=null && !units.equals("")){
			unitList = units.split(",");
		} else {
			outJson("{success:false, msg:'至少要为操作员选择一个主会场!'}");
			return null;
		}
		try{
			userService.addUser(user, roleList, unitList);
			logService.addLog(up.getUserId(), ILogService.type_create_user, "创建用户"+user.getLoginId());
				outJson("{success:true, msg:'操作员添加成功!'}");
			}catch(UserExistsException ue) {
				outJson("{success:false, msg:'操作员重复!'}");
			}

		return null;
	}
	public String main(){
		PageHolder ph = new PageHolder();
		ph.setFirstIndex(0);
		ph.setPageSize(20);
		List<BulletinBoard> bulletinList = bulletinService.findBulletin(null, ph);
		request.setAttribute("bulletinList", bulletinList);
		return "index";
	}
	
	public String getAllUnits(){
		List units = unitService.findAllUnits();
		JSONObject json = new JSONObject();
		JSONArray arr = JSONArray.fromObject(units);
		json.put("root", arr);
		try{
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
	public String getAllUnitsExclud(){
		List allUnits = unitService.findAllUnits();
		String id = request.getParameter("userId");
		List userUnits = userService.findUnitsByUserId(id);
		for(int i=0;i<userUnits.size();i++){
			Unit unit = (Unit)userUnits.get(i);
			for (int inneri=0;inneri<allUnits.size();inneri++){
				Unit inunit = (Unit)allUnits.get(inneri);
				if (unit.getUnitId().equals( inunit.getUnitId())){
					allUnits.remove(inneri);
				}
			}
		}
		JSONObject json = new JSONObject();
		JSONArray arr = JSONArray.fromObject(allUnits);
		json.put("root", arr);
		try{
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
	public String getUnitsByUserId(){
		String id = request.getParameter("userId");
		if(id==null || "".equals(id)){
			UserPrivilege up = (UserPrivilege)session.get("userPrivilege");
			id = up.getUserId();
		}
		User user = userService.getUser(id);
		if(user!=null && user.getLoginId().equals("super")){
			return getAllUnits();
		}
		List units = userService.findUnitsByUserId(id);
		JSONObject json = new JSONObject();
		JSONArray arr = JSONArray.fromObject(units);
		json.put("root", arr);
		try{
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