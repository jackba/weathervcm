package com.cma.intervideo;

import javax.servlet.http.HttpServlet;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

public class VcmServlet extends HttpServlet {
	
	private ApplicationContext ctx = null;
	
	public ApplicationContext getCtx() {
		if (ctx == null)
			ctx = WebApplicationContextUtils.getRequiredWebApplicationContext(this.getServletContext());
		return ctx;
	}

	public void setCtx(ApplicationContext ctx) {
		this.ctx = ctx;
	}
	
	public Object getBean(String beanName) {
		ApplicationContext c = getCtx();
		if (c == null)
			return null;
		return c.getBean(beanName);
	}

}
