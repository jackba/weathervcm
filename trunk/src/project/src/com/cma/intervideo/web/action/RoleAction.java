package com.cma.intervideo.web.action;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Random;

import javax.servlet.ServletOutputStream;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.cma.intervideo.exception.RoleExistsException;
import com.cma.intervideo.exception.RoleNotEmptyException;
import com.cma.intervideo.pojo.Role;
import com.cma.intervideo.service.IRoleService;
import com.cma.intervideo.util.AbstractBaseAction;
import com.cma.intervideo.util.PageHolder;
import com.sun.image.codec.jpeg.ImageFormatException;
import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

public class RoleAction extends AbstractBaseAction{
	private static Log logger = LogFactory.getLog(RoleAction.class);
	private IRoleService roleService;
	private Role role;
	
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	public void setRoleService(IRoleService roleService) {
		this.roleService = roleService;
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
//	public String login(){
//		System.out.println("login success!");
//		User user = userService.findUserByName(request.getParameter("username"));
//		if(user==null || !user.getPassword().equals(request.getParameter("password"))){
//			return "login";
//		}else{
//			return "index";
//		}
//		try{
//			PrintWriter pw = response.getWriter();
//			if(user==null || !user.getPassword().equals(request.getParameter("password"))){
//				pw.print("{success:false,msg:'user not exist!'}");
//			}else{
//				pw.print("{success:true,msg:'ok'}");
//			}
//			pw.flush();
//			pw.close();
//		}catch(Exception e){
//			e.printStackTrace();
//		}
//		return null;
//	}
	public String list(){
		return "roleList";
	}
	public String detail(){
		String id = request.getParameter("roleId");
		role = roleService.getRole(Integer.parseInt(id));
		return "detail";
	}
	public String search(){
		System.out.println("---------search---------");
		String start = request.getParameter("start");
		String limit = request.getParameter("limit");
		String totalProperty = request.getParameter("totalProperty");
		PageHolder ph = new PageHolder();
		ph.setFirstIndex(Integer.parseInt(start));
		ph.setPageSize(Integer.parseInt(limit));
		if(totalProperty!=null && !totalProperty.equals("")){
			ph.setResultSize(Integer.parseInt(totalProperty));
		}
		String name = request.getParameter("name");
		String remark = request.getParameter("remark");
		String temp = request.getParameter("status");
		//以下代码对状态做判断处理
		Short status = -1;
		if((temp != null) && !temp.equals("")){
			 status = Short.parseShort(temp);
		} else if((temp != null) && temp.equals("")){
			status = null;
		}
		try{
			JSONObject json = new JSONObject();
			System.out.println("--------queryRole before");
			List roles = roleService.queryRoles(name,remark,status, ph);
//			List roles = roleService.queryRoles(name,remark, ph);
			json.put("totalProperty", ph.getResultSize());
			JSONArray arr = JSONArray.fromObject(roles);
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
		List roles = roleService.findAllRoles();
		request.setAttribute("roles", roles);
		return "roleAdd";
	}
	public String getAllRoles(){
		List roles = roleService.findAllRoles();
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
	public String getAllPrivilege(){
		List privileges = roleService.findAllPrivileges();
		JSONObject json = new JSONObject();
		JSONArray arr = JSONArray.fromObject(privileges);
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
	public String save() throws IOException{
		boolean result=false;
//		role.setName(role.getName());
//		role.setRemark(role.getRemark());
		role.setStatus((short)0);
//		role.setStatus(role.getStatus());
		response.setContentType("text/html;charset=utf-8");
//		Map map = new HashMap();
		String privilges = request.getParameter("privileges");
		String[] privilegeList = null;
		if(privilges!=null && !privilges.equals("")){
			privilegeList = privilges.split(",");
		} else {
			System.out.println("------before out-----");
			outJson("{success:false, msg:'至少要为角色选择一个权限!'}");
			return null;
			
		}
		System.out.println("--------after out------");
		try{
			result=roleService.addRole(role, privilegeList);
			if ( result == true ){
				outJson("{success:true, msg:'角色添加成功!'}");
			} else {
				outJson("{success:false, msg:'角色名重复!'}");
			}

		}catch(RoleExistsException ue){
			logger.debug(ue.toString());
		}
		return null;
	}
	
	public String modify(){
		String id = request.getParameter("roleId");
		role = roleService.getRole(Integer.parseInt(id));
		return "roleModify";
	}
	public String update() throws RoleExistsException, IOException{
		String privilges = request.getParameter("privileges");
		String[] privilegeList = null;
		if(privilges!=null && !privilges.equals("")){
			privilegeList = privilges.split(",");
		} else {
			outJson("{success:false, msg:'至少要为角色选择一个权限!'}");
			return null;
			
		}
		String oldName=request.getParameter("oldName");
		boolean result=true;	
		
		response.setContentType("text/html;charset=utf-8");
		result=roleService.updateRole(role,oldName,privilegeList);
		System.out.println("----result:"+result);
		if ( result == true ){
			outJson("{success:true, msg:'角色修改成功!'}");
		} else {
			outJson("{success:false, msg:'角色名已经存在!'}");
		}
	
	return null;		
	}
	
	public String findPrivilegeByRoleId(){
		String roleId = request.getParameter("roleId");
		List privileges = roleService.findPrivilegesByRoleId(Integer.parseInt(roleId));
		JSONObject json = new JSONObject();
		JSONArray arr = JSONArray.fromObject(privileges);
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
	
	public String delete() throws RoleNotEmptyException{
		String idList = request.getParameter("roleIdList");
		String[] ids = idList.split(",");


		int deleteRocordNums = ids.length;
		for(int i=0;i<ids.length;i++){
			try {
				boolean result = deleteRole(Integer.parseInt(ids[i]));	
				if ( result == false ){	
					return null;
				}
			} catch (NumberFormatException e) {
				e.printStackTrace();
			}
		}
		//String deleteNum = Integer.toString(deleteRocordNums);
		String str = "成功删除"+deleteRocordNums+"条角色!";
		try {
			outJson("{success:true, msg:'"+str+"'}");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;		
	}
	
	public boolean deleteRole(Integer roleId) throws RoleNotEmptyException{
		Role role = roleService.getRole(roleId);
		if (roleService.queryUserRoleByRoleId(roleId) == true){
			String roleName = role.getRoleName();
			String str = "角色"+roleName+"正在使用，不能删除!";
			try {
				outJson("{success:false, msg:'"+str+"'}");
			} catch (IOException e) {
				e.printStackTrace();
			}
			return false;	
		} else {
			roleService.deleteRole(roleId);
		}		
		return true;
	}
	
	public String getPrivilegesByRoleId(){
		String id = request.getParameter("roleId");
		Integer roleId = Integer.parseInt(id);
		List privileges = roleService.findPrivilegesByRoleId(roleId);
		JSONObject json = new JSONObject();
		JSONArray arr = JSONArray.fromObject(privileges);
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
	
	public String getAllPrivilegesExclud(){
		String id = request.getParameter("roleId");
		Integer roleId = Integer.parseInt(id);
		List rolePrivileges = roleService.findPrivilegesByRoleId__Exclud(roleId);
	
		JSONObject json = new JSONObject();
		JSONArray arr = JSONArray.fromObject(rolePrivileges);
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
