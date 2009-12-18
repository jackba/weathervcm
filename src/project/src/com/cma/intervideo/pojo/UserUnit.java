package com.cma.intervideo.pojo;

public class UserUnit implements java.io.Serializable{
	private UserUnitId id;
	public UserUnit(){
		
	}
	public UserUnit(UserUnitId id){
		this.id = id;
	}
	public UserUnitId getId() {
		return id;
	}
	public void setId(UserUnitId id) {
		this.id = id;
	}
	
}
