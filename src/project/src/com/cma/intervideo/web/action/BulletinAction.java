package com.cma.intervideo.web.action;

import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.cma.intervideo.constant.DataDictStatus;
import com.cma.intervideo.pojo.BulletinBoard;
import com.cma.intervideo.service.IBulletinService;
import com.cma.intervideo.util.AbstractBaseAction;
import com.cma.intervideo.util.PageHolder;
import com.cma.intervideo.util.ParamVo;
import com.cma.intervideo.util.UserPrivilege;
import com.radvision.icm.service.vcm.ICMService;

public class BulletinAction extends AbstractBaseAction{
	private static Log logger = LogFactory.getLog(BulletinAction.class);
	private IBulletinService bulletinService;
	private BulletinBoard bulletinBoard;
	
	public BulletinBoard getBulletinBoard() {
		return bulletinBoard;
	}
	public void setBulletinBoard(BulletinBoard bulletinBoard) {
		this.bulletinBoard = bulletinBoard;
	}
	public void setBulletinService(IBulletinService bulletinService) {
		this.bulletinService = bulletinService;
	}
	public String add(){
		return "add";
	}
	public String list(){
		PageHolder ph = new PageHolder(request);
		List<BulletinBoard> bulletinList = bulletinService.findBulletin(null, ph);
		request.setAttribute("bulletinList", bulletinList);
		request.setAttribute("pageHolder", ph);
		return "list";
	}
	public String manage(){
		
		return "manage";
	}
	public String search(){
		String start = request.getParameter("start");
		String limit = request.getParameter("limit");
		String totalProperty = request.getParameter("totalProperty");
		PageHolder ph = new PageHolder();
		ph.setFirstIndex(Integer.parseInt(start));
		ph.setPageSize(Integer.parseInt(limit));
		if(totalProperty!=null && !totalProperty.equals("")){
			ph.setResultSize(Integer.parseInt(totalProperty));
		}
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		String startTime = request.getParameter("startTime");
		String endTime = request.getParameter("endTime");
		List<ParamVo> params = new ArrayList<ParamVo>();
		ParamVo vo = null;
		if(title!=null&&!title.equals("")){
			vo = new ParamVo();
			vo.setParamName("title");
			vo.setParamValue(title);
			params.add(vo);
		}
		if(content!=null&&!content.equals("")){
			vo = new ParamVo();
			vo.setParamName("content");
			vo.setParamValue(content);
			params.add(vo);
		}
		if(startTime!=null&&!startTime.equals("")){
			vo = new ParamVo();
			vo.setParamName("startTime");
			vo.setParamValue(startTime);
			params.add(vo);;
		}
		if(endTime!=null&&!endTime.equals("")){
			vo = new ParamVo();
			vo.setParamName("endTime");
			vo.setParamValue(endTime);
			params.add(vo);
		}
		try{
			JSONObject json = new JSONObject();
			List bulletinList = bulletinService.findBulletin(params, ph);
			json.put("totalProperty", ph.getResultSize());
			JSONArray arr = JSONArray.fromObject(bulletinList);
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
	public String modify(){
		String bulletinId = request.getParameter("bulletinId");
		bulletinBoard = bulletinService.getBulletinBoardById(Integer.parseInt(bulletinId));
		return "modify";
	}
	public String update(){
		Date d = Calendar.getInstance().getTime();
		BulletinBoard b = bulletinService.getBulletinBoardById(bulletinBoard.getBulletinId());
		b.setUpdateTime(d);
		b.setTitle(bulletinBoard.getTitle());
		b.setContent(bulletinBoard.getContent());
		bulletinService.save(b);
		PrintWriter pw = null;
		response.setContentType("text/html;charset=utf-8");
		try{
			pw = response.getWriter();
			pw.print("{success:true,msg:'修改成功!'}");
			pw.flush();
			pw.close();
		}catch(Exception e){
			logger.error(e.toString());
			if(pw!=null){
				pw.print("{success:false,msg:'"+e.toString()+"'}");
				pw.flush();
				pw.close();
			}
		}
		return null;
	}
	public String save(){
		Date d = Calendar.getInstance().getTime();
		bulletinBoard.setCreateTime(d);
		bulletinBoard.setStatus(DataDictStatus.bulletinNormalStatus);
		bulletinBoard.setUpdateTime(d);
		if(bulletinBoard.getEffectiveTime()==null){
			bulletinBoard.setEffectiveTime(d);
		}
		PrintWriter pw = null;
		try{
			if(bulletinBoard.getExpiredTime()==null){
				bulletinBoard.setExpiredTime(new SimpleDateFormat("yyyy-MM-dd").parse("2999-01-01"));
			}
			UserPrivilege up = (UserPrivilege)session.get("userPrivilege");
			if(up!=null){
				bulletinBoard.setUserId(up.getUserId());
			}
			bulletinService.save(bulletinBoard);
		
			response.setContentType("text/html;charset=utf-8");
		
			pw = response.getWriter();
			pw.print("{success:true,msg:'发布成功!'}");
			pw.flush();
			pw.close();
		}catch(Exception e){
			logger.error(e.toString());
			if(pw!=null){
				pw.print("{success:false,msg:'"+e.toString()+"'}");
				pw.flush();
				pw.close();
			}
		}
		return null;
	}
	public String detail(){
		String bulletinId = request.getParameter("bulletinId");
		bulletinBoard = bulletinService.getBulletinBoardById(Integer.parseInt(bulletinId));
		return "detail";
	}
	public String getNews(){
		try{
			String index = request.getParameter("index");
			response.setCharacterEncoding("utf-8");

			PrintWriter out = response.getWriter();
			BulletinBoard b = bulletinService.getNews(Integer.parseInt(index));
			if(b==null){
				out.print("没有最新公告");
			}else{
				out.print("<a target=\"_blank\" href=\"bulletin_detail.do?bulletinId="+b.getBulletinId()+"\">"+b.getTitle()+"</a>");
			}
			out.flush();
			out.close();
		}catch(Exception e){
			logger.error(e.toString());
		}
		return null;
	}
}
