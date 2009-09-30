package com.cma.intervideo.util;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts2.ServletActionContext;




public abstract class AbstractBaseAction extends AbstractAction{
	
	
	protected PageHolder websidePage(int pageSize){
		

		//PageHolder ph = new PageHolder(1,pageSize,0);
		
		return null;
	}
	protected void outMultiJson(String jsonString) throws IOException {


		response.setContentType("text/html");
		response.setCharacterEncoding("utf-8");
		response.getWriter().print(jsonString);
		
	}
	protected void outJson(String jsonString) throws IOException {
		response.setContentType("text/html");
		response.setCharacterEncoding("utf-8");
		response.getWriter().print(jsonString);
	}

	protected void outJson(JSONObject jsonObject) throws IOException {
		response.setContentType("text/html");
		response.setCharacterEncoding("utf-8");
		response.getWriter().print(jsonObject);
	}
	
	protected void saveJumpPageValues(String forwardAction,String successMsg ){
		request.setAttribute("forwardAction", forwardAction);
		request.setAttribute("successMsg", successMsg);
		
	}
	protected void saveJumpPageMsgValues(String successMsg ){
		
		request.setAttribute("successMsg", successMsg);
		
	}
	protected void saveJumpPageForwardValues(String forwardAction ){
		
		request.setAttribute("forwardAction", forwardAction);
		
	}

	/**
	 * 
	 * @param javaScriptFun
	 * @throws IOException
	 */
	protected void outJavaScript2(String javaScriptFun) throws IOException {
		response.setContentType("text/html;charset=GBK");
		PrintWriter out = response.getWriter();
		out.println("<HTML>");
		out.println("<title>te</title>");
		out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\""+request.getContextPath()+"/admin/common/KMessageBox.css\">");
		
		out.println("<script  src=\""+request.getContextPath()+"/admin/common/prototype.js\"></script>");
		out.println("<script  src=\""+request.getContextPath()+"/admin/common/KMessageBox.js\"></script>");
		//out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\""+request.getContextPath()+"/admin/common/alert.css\">");
		
		//out.println("<script src=\""+request.getContextPath()+"/admin/common/alert.js\"></script>");
		out.println("<script language=javascript>");
		
		out.println("function msg(){");
		//out.println(javaScriptFun);
		
		//out.println("KMessageBox.ShowInfo('','ϵͳ��ʾ','ȷ��Ҫɾ����?');");
		
		out.println("alert('test')");
		out.println("}");
		
		out.println("</script>");
		out.println("<body onload=\"msg()\">");
		out.println("</body>");
		
		
		out.println("</HTML>");
		out.flush();
		out.close();
	}
	/**
	 * 
	 * @param javaScriptFun
	 * @throws IOException
	 */
	protected void outJavaScript3(String javaScriptFun) throws IOException {
		response.setContentType("text/html;charset=GBK");
		PrintWriter out = response.getWriter();
		out.println("<HTML>");
		out.println("<title>te</title>");
		out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\""+request.getContextPath()+"/admin/common/alert.css\">");
		
		out.println("<script src=\""+request.getContextPath()+"/admin/common/alert.js\"></script>");
		out.println("<script language=javascript>");
		
		out.println("function msg(){");
		//out.println(javaScriptFun);
		
		out.println("alert('test')");
		out.println("}");
		
		out.println("</script>");
		out.println("<body onload=\"msg()\">");
		out.println("</body>");
		
		
		out.println("</HTML>");
		out.flush();
		out.close();
	}

	/**
	 * 
	 * @param javaScriptFun
	 * @throws IOException
	 */
	protected void outJavaScript(String javaScriptFun) throws IOException {
		response.setContentType("text/html;charset=gb2312");
		PrintWriter out = response.getWriter();
		out.println("<script language=JavaScript>");
		out.println(javaScriptFun);
		out.println("</script>");
		out.flush();
		out.close();
	}
	protected void setFilterOptions(List ori, String labelName, String valueName,
			String optionsName) {
		Map<String, String> options = new HashMap<String, String>();
		for (Object obj : ori) {

			try {
				final String label = BeanUtils.getProperty(obj, labelName);
				final String value = BeanUtils.getProperty(obj, valueName);
				options.put(value, label);
			} catch (Exception e) {
				log.error("", e);
				e.printStackTrace();
			}

		}
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setAttribute(optionsName, options);
		
		
	}
}