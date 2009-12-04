package com.cma.intervideo.util;

import java.util.Hashtable;
import java.util.List;

import com.cma.intervideo.pojo.Privilege;



public class UserPrivilege {
	private String userId;
	private String loginId;
	private String userName;
	private List ownPrivileges;
	private List urls;
	private Hashtable urlsTable;
	private Hashtable codeTable;
	
	public String toString() {
		StringBuilder buf = new StringBuilder();
		
		String line = System.getProperty("line.separator");
		buf.append(line + "[UserPrivilege:( @" + hashCode() + ")" + line);
		buf.append(" userId = " + userId + ", loginId = " + loginId + line);
		buf.append(" userName = " + userName + ", ownPrivileges = " + ownPrivileges + line);
		buf.append(" urls = [" + line);
		for (int i = 0; urls != null && i < urls.size(); i++)
		{
			buf.append("    " + urls.get(i) + line);
		}
		buf.append(" ]" + line);
		buf.append("]." + line);
		return buf.toString();
	}
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public String getLoginId() {
		return loginId;
	}
	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}
	public boolean hasUrlPrivilege(String url, String method){
		if(loginId.equals("super")){
			return true;
		}
		String combinedUrl = url+"."+method;
		if(urlsTable.get(combinedUrl)!=null){
			return true;
		}
		return false;
	}
	/**
	 * 判断用户是否具有指定code对应的权限
	 * @param code
	 * @return
	 */
	public boolean hasCodePrivilege(String code){
		if(loginId.equals("super")){
			return true;
		}
		if(codeTable.get(code)!=null){
			return true;
		}else{
			return false;
		}
	}
	public List getOwnPrivileges() {
		return ownPrivileges;
	}
	public void setOwnPrivileges(List ownPrivileges) {
		this.ownPrivileges = ownPrivileges;
		if(ownPrivileges!=null && ownPrivileges.size()>0){
			codeTable = new Hashtable(ownPrivileges.size());
			for(int i=0;i<ownPrivileges.size();i++){
				Privilege privilege = (Privilege)ownPrivileges.get(i);
				if(privilege.getCode()!=null && !privilege.getCode().equals("")){
					codeTable.put(privilege.getCode(), privilege);
				}
			}
		}else{
			codeTable = new Hashtable();
		}
	}
	public List getUrls() {
		return urls;
	}
	public void setUrls(List urls) {
		this.urls = urls;
		urlsTable = new Hashtable(urls.size());
		for(int i=0;i<urls.size();i++){
			String url = (String)urls.get(i);
			if(url.startsWith("action:")){
				urlsTable.put(url.substring(7), url);
			}else if(url.startsWith("json:")){
				urlsTable.put(url.substring(5), url);
			}else if(url.startsWith("dwr:")){
				urlsTable.put(url.substring(4), url);
			}
		}
	}
	
}