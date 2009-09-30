package com.cma.intervideo.pojo;

// Generated 2009-8-20 4:02:54 by Hibernate Tools 3.2.4.GA

/**
 * Role generated by hbm2java
 */
public class Role implements java.io.Serializable {

	private Integer roleId;
	private String roleName;
	private String description;
	private Short status;

	public Role() {
	}

	public Role(String roleName, Short status) {
		this.roleName = roleName;
		this.status = status;
	}

	public Role(String roleName, String description, Short status) {
		this.roleName = roleName;
		this.description = description;
		this.status = status;
	}

	public Integer getRoleId() {
		return this.roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return this.roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Short getStatus() {
		return this.status;
	}

	public void setStatus(Short status) {
		this.status = status;
	}

}
