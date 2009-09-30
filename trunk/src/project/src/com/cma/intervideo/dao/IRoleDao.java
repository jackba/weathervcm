package com.cma.intervideo.dao;

import java.util.List;

import com.cma.intervideo.dao.util.DAO;
import com.cma.intervideo.pojo.Role;
import com.cma.intervideo.pojo.User;
import com.cma.intervideo.util.PageHolder;

public interface IRoleDao extends DAO<User, Integer>{
//	public User findUserByName(String name);
//	public List findAllUsers(PageHolder ph);
//	public void addUser(User user);
	public List findAllRoles(PageHolder ph);
	public List findAllPrivileges();
	public void addRole(Role role);
	public void addRolePrivilege(Integer roleId, Integer privilegeId);
	public List findAllRoles();
//	public void addUserRole(Integer userId, Integer roleId);
	public List findAllPrivileges(PageHolder ph);
	public List findUrlsByUserId(int userid);
	/**
	 * 根据用户号查找用户具有的角色,返回列表的元素为Role对象
	 * @param userId
	 * @return
	 */
	public List findRolesByUserId(Integer userId);
	/**
	 * 根据角色查找角色具有的权限
	 * @param roleId
	 * @return
	 */
	public List findPrivilegesByRoleId(Integer roleId);
	/**
	 * 删除用户的所有角色
	 * @param userId
	 */
//	public void deleteUserRoleByUserId(Integer userId);
	/**
	 * 删除角色的所有权限
	 * @param roleId
	 */
	public void deleteRolePrivilegeByRoleId(Integer roleId);
//	public void updateUser(User user);
	public void updateRole(Role role);
//	public User getUser(Integer userId);
	public Role getRole(Integer roleId);
	/**
	 * 根据用户名查找状态为有效或者禁用的用户,不包括被删除的用户
	 * @param userName
	 * @return
	 */
//	public User findUserByUsername(String userName);
	/**
	 * 根据角色名称查找状态正常的角色
	 * @param name
	 * @return
	 */
	public Role getRoleByName(String name);
//	public List findUserRoleByRoleId(Integer roleId);
//	public List queryUsers(String username, String name, PageHolder ph);
	public List queryRoles(String roleName, PageHolder ph);
	public List listRoles(String roleName, PageHolder ph);
	public Role findRoleByRoleName(String name);
	public void deleteRole(Integer roleId);

	public void modifyRole(Role role);
	public List queryRoles(String name, String remark, Short status,
			PageHolder ph);
	public boolean queryUserRoleByRoleId(Integer roleId);
	public List findPrivilegesByRoleId_Exclud(Integer roleId);
	public void saveOrUpdateRole(Role newRole);

	

}
