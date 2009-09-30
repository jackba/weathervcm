package com.cma.intervideo.service;

import java.util.List;

import com.cma.intervideo.exception.RoleExistsException;
import com.cma.intervideo.exception.RoleNotEmptyException;
import com.cma.intervideo.exception.UserExistsException;
import com.cma.intervideo.pojo.Role;
import com.cma.intervideo.pojo.User;
import com.cma.intervideo.util.PageHolder;
import com.cma.intervideo.util.UserPrivilege;

public interface IRoleService {
	public static final int SUCCESS = 0;
	public static final int INVALID_USERNAME = 1;
	public static final int INVALID_PASSWORD = 2;
//	根据角色名查找角色
	public Role findRoleByName(String name);
//	public int userLogin(String userName, String password, UserPrivilege up);
//	public List findAllUsers(PageHolder ph);
//  根据id删除角色
	public void deleteRole(Integer roleId) throws RoleNotEmptyException;
	public int deleteRoles(List<String> roles);
	public List findAllRoles(PageHolder ph);
	public List findAllPrivileges();
	public boolean addRole(Role role, String[] privilegeList) throws RoleExistsException;
	public List findAllRoles();
	public List findAllPrivileges(PageHolder ph);
	public int getPrivilegesByUserName(String userName, UserPrivilege up);
	public void updateUser(User user, String[] roles, String[] customerGroups,
			String[] positionGroups, String oldUsername) throws UserExistsException;
	public void updateRole(Role role, String[] privilegeList);
	/**
	 * 根据用户号查找用户具有的角色,返回列表的元素为Role对象
	 * @param userId
	 * @return
	 */

	/**
	 * 根据角色查找角色具有的权限
	 * @param roleId
	 * @return
	 */
	public List findPrivilegesByRoleId(Integer roleId);
//	public User getUser(Integer userId);
	public Role getRole(Integer roleId);
//	public void deleteUser(Integer userId);
	
//	public void stopUser(Integer userId);
//	public void resumeUser(Integer userId);
	
	public Role getRoleByName(String name);
	/**
	 * 修改用户密码
	 * @param userId
	 * @param oldPassword
	 * @param newPassword
	 * @return 0表示修改成功,1表示原密码错误
	 */
//	public int modifyUserPassword(int userId, String oldPassword, String newPassword);
	/**
	 * 重置用户密码
	 * @param userId
	 * @param password
	 */
//	public void resetUserPassword(int userId, String password);
//	public List queryUsers(String username, String name, PageHolder ph);
	public List queryRoles(String roleName, PageHolder ph);
	
	//显示角色列表
	public List listRoles(String roleName, PageHolder ph);

	public List queryRoles(String name, String remark, Short status,
			PageHolder ph);
	public boolean updateRole(Role role, String oldName);
	public boolean queryUserRoleByRoleId(Integer roleId);
	public void deleteUserRoleByRoleId(Integer roleId);
	public List findPrivilegesByRoleId__Exclud(Integer roleId);
	public boolean updateRole(Role role, String oldName, String[] privilegeList);



}
