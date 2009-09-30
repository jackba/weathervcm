package com.cma.intervideo.service.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.cma.intervideo.dao.IRoleDao;
import com.cma.intervideo.exception.RoleExistsException;
import com.cma.intervideo.exception.RoleNotEmptyException;
import com.cma.intervideo.exception.UserExistsException;
import com.cma.intervideo.pojo.Role;
import com.cma.intervideo.pojo.User;
import com.cma.intervideo.service.IRoleService;
import com.cma.intervideo.util.PageHolder;
import com.cma.intervideo.util.UserPrivilege;

public class RoleServiceImpl implements IRoleService{
	
	private static Log logger = LogFactory.getLog(UserServiceImpl.class);
	private IRoleDao roleDao;
	public int deleteRocordNums = 0;

	public boolean addRole(Role role, String[] privileges) throws RoleExistsException {
		
		Role tmp = roleDao.findRoleByRoleName(role.getRoleName());
		if(tmp!=null){
			return false;
		}
		roleDao.addRole(role);
		if (privileges != null) {
			for (int i = 0; i < privileges.length; i++) {
				roleDao.addRolePrivilege(role.getRoleId(), new Integer(privileges[i]));
			}
		}
		return true;
		
	}


//	public void deleteRole(Integer roleId) throws RoleNotEmptyException {
//		System.out.println("delete method exec");
//		//Firstly,query whether the roleId existed in table user_role
//		if (roleDao.queryUserRoleByRoleId(roleId)){
//			//return warning to client
//			System.out.println("--------------"+roleId+"can't be delete");
//			deleteRocordNums --;
//		} else {
//			System.out.println("--------------"+roleId+"can be delete");
//			//Secondly, if no user belong to this role, then the role can be removed.
//			Role role = roleDao.getRole(roleId);
//		//	roleDao.deleteRolePrivilegeByRoleId(roleId);
//			roleDao.deleteRole(roleId);	
//		}
//		
//	}
	

	public int deleteRoles(List<String> roles){
		deleteRocordNums = roles.size();
		for(int i=0;i<roles.size();i++){
			try {
				deleteRole(Integer.parseInt(roles.get(i)));
			} catch (NumberFormatException e) {
				e.printStackTrace();
			} catch (RoleNotEmptyException e) {
				e.printStackTrace();
			}
			//roleDao.removeObjectByID(Integer.parseInt(roles.get(i)));
		}
		
		return deleteRocordNums;		
	}

	public List findAllPrivileges() {
		return roleDao.findAllPrivileges();
	}

	public List findAllPrivileges(PageHolder ph) {
		// TODO Auto-generated method stub
		return null;
	}

	public List findAllRoles(PageHolder ph) {
		// TODO Auto-generated method stub
		return null;
	}

	public List findAllRoles() {
		// TODO Auto-generated method stub
		return null;
	}

	public List findPrivilegesByRoleId(Integer roleId) {
		return roleDao.findPrivilegesByRoleId(roleId);
	}

	public Role findRoleByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}



	public int getPrivilegesByUserName(String userName, UserPrivilege up) {
		// TODO Auto-generated method stub
		return 0;
	}

	public Role getRole(Integer roleId) {
		return roleDao.getRole(roleId);
	}

	public Role getRoleByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	public List queryRoles(String roleName, PageHolder ph) {
		return roleDao.queryRoles(roleName, ph);
	}
//add by shipeng
	public List queryRoles(String name, String remark, Short status,
			PageHolder ph) {
		return roleDao.queryRoles(name,remark,status, ph);
	}

	public void updateRole(Role role, String[] privileges) {
		System.out.println("=========before service updateRole====");
		roleDao.updateRole(role);
		System.out.println("=========after service updateRole=======");
		roleDao.deleteRolePrivilegeByRoleId(role.getRoleId());
		System.out.println("=========after service deleteRolePrivilegeByRoleId=======");
		for (int i = 0; i < privileges.length; i++) {
			String privilegeId = privileges[i];

			roleDao.addRolePrivilege(role.getRoleId(), new Integer(privilegeId));
			System.out.println("=========after service addRolePrivilege=======");
		}
		
		System.out.println("=======update service updateRole success");
		
	}

	public void updateUser(User user, String[] roles, String[] customerGroups,
			String[] positionGroups, String oldUsername)
			throws UserExistsException {
		// TODO Auto-generated method stub
		
	}

	public IRoleDao getRoleDao() {
		return roleDao;
	}

	public void setRoleDao(IRoleDao roleDao) {
		this.roleDao = roleDao;
	}

	public List listRoles(String roleName, PageHolder ph) {
		return roleDao.listRoles(roleName, ph);
	}

	public void addRole(Role role) throws RoleExistsException {
		// TODO Auto-generated method stub
		
	}




	public boolean updateRole(Role newRole, String oldName, String[] privileges) {
		newRole.setStatus((short)0);
		Role oldRole = roleDao.findRoleByRoleName(oldName);
		if(!oldName.equals(newRole.getRoleName())){		
			Role tmp = roleDao.findRoleByRoleName(newRole.getRoleName());
			if(tmp!=null){
				return false;
			} 		
		} 
		roleDao.deleteRolePrivilegeByRoleId(oldRole.getRoleId());
		if (privileges != null) {
			for (int i = 0; i < privileges.length; i++) {
				roleDao.addRolePrivilege(newRole.getRoleId(), new Integer(privileges[i]));
			}
		}
		roleDao.saveOrUpdateRole(newRole);
		return true;
	}


	public void deleteUserRoleByRoleId(Integer roleId) {

		
	}


	public boolean queryUserRoleByRoleId(Integer roleId) {
		return roleDao.queryUserRoleByRoleId(roleId);
	}


	public void deleteRole(Integer roleId) throws RoleNotEmptyException {
		roleDao.deleteRole(roleId);	
		
	}


	public List findPrivilegesByRoleId__Exclud(Integer roleId) {
		// TODO Auto-generated method stub
		return roleDao.findPrivilegesByRoleId_Exclud(roleId);
	}


	public boolean updateRole(Role role, String oldName) {
		// TODO Auto-generated method stub
		return false;
	}



	
}
