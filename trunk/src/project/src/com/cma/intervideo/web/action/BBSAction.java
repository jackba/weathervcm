package com.cma.intervideo.web.action;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.cma.intervideo.constant.DataDictStatus;
import com.cma.intervideo.pojo.MessageBoard;
import com.cma.intervideo.service.IBBSService;
import com.cma.intervideo.util.AbstractBaseAction;
import com.cma.intervideo.util.PageHolder;
import com.cma.intervideo.util.ParamVo;
import com.cma.intervideo.util.UserPrivilege;

public class BBSAction extends AbstractBaseAction{
	private static Log logger = LogFactory.getLog(BBSAction.class);
	private IBBSService bbsService;
	private MessageBoard messageBoard;
	public void setBbsService(IBBSService bbsService) {
		this.bbsService = bbsService;
	}
	public MessageBoard getMessageBoard() {
		return messageBoard;
	}
	public void setMessageBoard(MessageBoard messageBoard) {
		this.messageBoard = messageBoard;
	}
	public String add(){
		return "add";
	}
	public String listPersonal(){
		PageHolder ph = new PageHolder(request);
		UserPrivilege up = (UserPrivilege)session.get("userPrivilege");
		List<ParamVo> params = new ArrayList<ParamVo>();
		ParamVo vo = new ParamVo();
		vo.setParamName("userId");
		vo.setParamValue(up.getUserId());
		params.add(vo);
//		List<MessageBoard> messageList = bbsService.findMessagesByUserId(up.getUserId());
		List<MessageBoard> messageList = bbsService.findMessages(params, ph);
		request.setAttribute("messageList", messageList);
		request.setAttribute("personal", "true");
		request.setAttribute("pageHolder", ph);
		return "list";
	}
	public String listAll(){
		PageHolder ph = new PageHolder(request);
		List<ParamVo> params = new ArrayList<ParamVo>();
//		List<MessageBoard> messageList = bbsService.findAllMessages();
		List<MessageBoard> messageList = bbsService.findMessages(params, ph);
		request.setAttribute("messageList", messageList);
		request.setAttribute("personal", "false");
		request.setAttribute("pageHolder", ph);
		return "list";
	}
	public String delete(){
		UserPrivilege up = (UserPrivilege)session.get("userPrivilege");
		String messageId = request.getParameter("messageId");
		String personal = request.getParameter("personal");
		MessageBoard m = bbsService.getMessageById(Integer.parseInt(messageId));
		if(personal.equals("true")){
			if(!m.getUserId().equals(up.getUserId())){
				request.setAttribute("msg", "删除失败!您没有删除该条留言的权限!");
				if(personal.equals("true")){
					return listPersonal();
				}else{
					return listAll();
				}
			}
		}
		m.setStatus(DataDictStatus.messageInvalidStatus);
		bbsService.save(m);
		request.setAttribute("msg", "删除成功!");
		
		if(personal.equals("true")){
			return listPersonal();
		}else{
			return listAll();
		}
	}
	public String search(){
		PageHolder ph = new PageHolder(request);
		UserPrivilege up = (UserPrivilege)session.get("userPrivilege");
		String personal = request.getParameter("personal");
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		String startTime = request.getParameter("startTime");
		String endTime = request.getParameter("endTime");
		List<ParamVo> params = new ArrayList<ParamVo>();
		ParamVo vo = null;
		if(personal.equals("true")){
			vo = new ParamVo();
			vo.setParamName("userId");
			vo.setParamValue(up.getUserId());
			params.add(vo);
		}
		if(title!=null && !title.equals("")){
			vo = new ParamVo();
			vo.setParamName("title");
			vo.setParamValue(title);
			params.add(vo);
			request.setAttribute("title", title);
		}
		if(content!=null && !content.equals("")){
			vo = new ParamVo();
			vo.setParamName("content");
			vo.setParamValue(content);
			params.add(vo);
			request.setAttribute("content", content);
		}
		if(startTime!=null&&!startTime.equals("")){
			vo = new ParamVo();
			vo.setParamName("startTime");
			vo.setParamValue(startTime);
			params.add(vo);
			request.setAttribute("startTime", startTime);
		}
		if(endTime!=null&&!endTime.equals("")){
			vo = new ParamVo();
			vo.setParamName("endTime");
			vo.setParamValue(endTime);
			params.add(vo);
			request.setAttribute("endTime", endTime);
		}
		List<MessageBoard> messageList = bbsService.findMessages(params, ph);
		request.setAttribute("messageList", messageList);
		request.setAttribute("personal", personal);
		request.setAttribute("pageHolder", ph);
		return "list";
	}
	public String save(){
		Date d = Calendar.getInstance().getTime();
		messageBoard.setCreateTime(d);
		messageBoard.setStatus(DataDictStatus.messageNormalStatus);
		PrintWriter pw = null;
		try{
			UserPrivilege up = (UserPrivilege)session.get("userPrivilege");
			if(up!=null){
				messageBoard.setUserId(up.getUserId());
			}
			bbsService.save(messageBoard);
		
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
}
