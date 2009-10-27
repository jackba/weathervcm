package com.cma.intervideo.util;

import java.lang.reflect.Method;
import java.util.Hashtable;
import java.util.List;

import org.springframework.aop.ClassFilter;
import org.springframework.aop.support.StaticMethodMatcherPointcut;

import com.cma.intervideo.dao.IUserDao;
import com.cma.intervideo.pojo.Privilege;

public class PrivilegePointcut extends StaticMethodMatcherPointcut{
	private IUserDao userDao;
	private Hashtable allprivileges = null;
	public PrivilegePointcut(IUserDao userDao){
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
	public boolean matches(Method method, Class cls) {
		// TODO Auto-generated method stub
		if(allprivileges.get(cls.getName()+"."+method.getName())!=null){
			return true;
		}
		return false;
	}
	public ClassFilter getClassFilter(){
		return new ClassFilter(){
			public boolean matches(Class cls){
				if(cls.getName().endsWith("Action")||(cls.getName().endsWith("ServiceImpl"))){
					return true;
				}else{
					return false;
				}
			}
		};
	}
}
