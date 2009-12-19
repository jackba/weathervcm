package com.cma.intervideo.dao;

import java.util.List;

import com.cma.intervideo.dao.util.DAO;
import com.cma.intervideo.pojo.Privilege;
import com.cma.intervideo.pojo.Role;
import com.cma.intervideo.pojo.User;
import com.cma.intervideo.util.PageHolder;

public interface IUserDao extends DAO<User, String>{
	/**
	 * 根据用户姓名查找用户
	 */
	public User findUserByName(String name);
	/**
	 * 根据用户名查找状态为有效的用户
	 * @param userName
	 * @return
	 */
	public User findUserByLoginId(String loginId);
	
	public List findAllUsers(PageHolder ph);
	public void addUser(User user);
	public List findAllRoles(PageHolder ph);
	public List findAllPrivileges();
	public void addRole(Role role);
	public void addRolePrivilege(Integer roleId, Integer privilegeId);
	public List findAllRoles();
	public void addUserRole(String userId, Integer roleId);
	public List findAllPrivileges(PageHolder ph);
	public List findUrlsByUserId(String userid);
	/**
	 * 根据用户号查找用户具有的角色,返回列表的元素为Role对象
	 * @param userId
	 * @return
	 */
	public List findRolesByUserId(String userId);
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
	public void deleteUserRoleByUserId(String userId);
	/**
	 * 删除角色的所有权限
	 * @param roleId
	 */
	public void deleteRolePrivilegeByRoleId(Integer roleId);
	public void updateUser(User user);
	public void saveOrUpdateUser(User user);
	public void updateRole(Role role);
	public User getUser(String userId);
	public Role getRole(Integer roleId);
	/**
	 * 根据角色名称查找状态正常的角色
	 * @param name
	 * @return
	 */
	public Role getRoleByName(String name);
	public List findUserRoleByRoleId(Integer roleId);
	public List queryUsers(String username, String name, PageHolder ph);
	public List queryUsersAndStatus(String username, String name, Short status, PageHolder ph);
	public List queryRoles(String roleName, PageHolder ph);
	/**
	 * 根据用户号查找用户拥有哪些url的权限
	 * @param userId
	 * @return
	 */
	public List<Privilege> findPrivilegesByUserId(String userId);
	/**
	 * 根据用户号查找用户可选的主会场,返回列表的元素为Unit对象
	 * @param userId
	 * @return
	 */
	public List findUnitsByUserId(String userId);
	/**
	 * 删除用户的所有可选会场
	 * @param userId
	 */
	public void deleteUserUnitByUserId(String userId);
	public void addUserUnit(String userId, Integer unitId);
	
}
