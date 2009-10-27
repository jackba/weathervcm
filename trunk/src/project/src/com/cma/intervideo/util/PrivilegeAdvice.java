package com.cma.intervideo.util;

import java.io.PrintWriter;
import java.util.Hashtable;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.directwebremoting.WebContext;
import org.directwebremoting.WebContextFactory;

import com.cma.intervideo.dao.IUserDao;
import com.cma.intervideo.pojo.Privilege;

public class PrivilegeAdvice implements MethodInterceptor{
	private Hashtable allprivileges = null;
	private IUserDao userDao;
	public PrivilegeAdvice(IUserDao userDao){
		this.userDao = userDao;
		if(allprivileges == null){
			allprivileges = new Hashtable();
			List privileges = userDao.findAllPrivileges();
			String aurl = null;
			for(int i=0;i<privileges.size();i++){
				Privilege privilege = (Privilege)privileges.get(i);
				aurl = privilege.getUrl();
				String[] aurls = aurl.split(";");
				for(int j=0;j<aurls.length;j++){
					if(aurls[j].startsWith("action:")){
						aurls[j] = "action:com.cma.intervideo.web.action."+aurls[j].substring(7);
						allprivileges.put(aurls[j].substring(7), aurls[j]);
					}else if(aurls[j].startsWith("json:")){
						aurls[j] = "json:com.cma.intervideo.web.action."+aurls[j].substring(5);
						allprivileges.put(aurls[j].substring(5), aurls[j]);
					}else if(aurls[j].startsWith("dwr:")){
						aurls[j] = "dwr:com.cma.intervideo.service.impl."+aurls[j].substring(4);
						allprivileges.put(aurls[j].substring(4), aurls[j]);
					}
					
				}
			}
		}
	}
	public Object invoke(MethodInvocation invocation) throws Throwable {
		// TODO Auto-generated method stub
		System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
		String url = (String)allprivileges.get(invocation.getThis().getClass().getName()+"."+invocation.getMethod().getName());
		if(url.startsWith("json:")){
			AbstractAction a = (AbstractAction)(invocation.getThis());
			UserPrivilege up = (UserPrivilege)a.session.get("userPrivilege");
			if(up == null || !up.hasUrlPrivilege(invocation.getThis().getClass().getName(), invocation.getMethod().getName())){
				//System.out.println("##################################");
				HttpServletResponse res = a.response;
				res.setStatus(res.SC_UNAUTHORIZED);
				res.setContentType("text/html;charset=utf-8");
				PrintWriter out = res.getWriter();
				out.print("您没有该操作的权限，是否重新登陆");
				out.flush();
				out.close();
				//res.sendError(res.SC_UNAUTHORIZED, "您没有该操作的权限，是否重新登陆");
				return null;
			}
			Object retVal = invocation.proceed();
			return retVal;
		}else if(url.startsWith("action:")){
			AbstractAction a = (AbstractAction)(invocation.getThis());
			UserPrivilege up = (UserPrivilege)a.session.get("userPrivilege");
			if(up == null || !up.hasUrlPrivilege(invocation.getThis().getClass().getName(), invocation.getMethod().getName())){
				//a.response.sendRedirect(a.request.getContextPath()+"/login.jsp");
				return "NoPrivilege";
			}
			Object retVal = invocation.proceed();
			return retVal;
		}else if(url.startsWith("dwr:")){
			WebContext ctx = WebContextFactory.get();
			HttpSession session = ctx.getSession();
			UserPrivilege up = (UserPrivilege)session.getAttribute("userPrivilege");
			if(up == null || !up.hasUrlPrivilege(invocation.getThis().getClass().getName(), invocation.getMethod().getName())){
				throw new RuntimeException("您没有该操作的权限，是否重新登陆");
			}
			Object retVal = invocation.proceed();
			return retVal;
		}else{
			Object retVal = invocation.proceed();
			return retVal;
		}
	}
	
}
