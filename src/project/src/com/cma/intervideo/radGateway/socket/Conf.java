package com.cma.intervideo.radGateway.socket;

import java.util.ArrayList;
import java.util.List;

public class Conf{
	private String confGID;
	private String servicePrefix;
	private String name;
	private String numName;
	private String desktopPID = null;
	private List<Part> parts = new ArrayList<Part>();
	
	public String getDesktopPID() {
		return desktopPID;
	}
	public void setDesktopPID(String desktopPID) {
		this.desktopPID = desktopPID;
	}
	public String getConfGID() {
		return confGID;
	}
	public void setConfGID(String confGID) {
		this.confGID = confGID;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getNumName() {
		return numName;
	}
	public void setNumName(String numName) {
		this.numName = numName;
	}
	public String getServicePrefix() {
		return servicePrefix;
	}
	public void setServicePrefix(String servicePrefix) {
		this.servicePrefix = servicePrefix;
	}
	public List<Part> getParts() {
		return parts;
	}
	public void setParts(List<Part> parts) {
		this.parts = parts;
	}
	
}
