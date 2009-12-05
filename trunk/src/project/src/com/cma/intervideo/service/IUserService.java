package com.cma.intervideo.service;

import java.util.List;

import com.cma.intervideo.exception.RoleExistsException;
import com.cma.intervideo.exception.RoleNotEmptyException;
import com.cma.intervideo.exception.UserExistsException;
import com.cma.intervideo.pojo.Privilege;
import com.cma.intervideo.pojo.Role;
import com.cma.intervideo.pojo.User;
import com.cma.intervideo.util.PageHolder;
import com.cma.intervideo.util.UserPrivilege;

public interface IUserService {
	public static final int SUCCESS = 0;
	public static final int INVALID_USERNAME = 1;
	public static final int INVALID_PASSWORD = 2;
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
	/**
	 * 根据用户号查找用户拥有哪些url的权限
	 * @param userId
	 * @return
	 */
	public List<Privilege> findPrivilegesByUserId(String userId);
	/**
	 * 根据用户号查找用户拥有哪些url的权限
	 * @param userid
	 * @return
	 */
	public List findUrlsByUserId(String userid);
	public int userLogin(String userName, String password, UserPrivilege up);
	public List findAllUsers(PageHolder ph);
	public void addUser(User user, String[] roles) throws UserExistsException;
	public List findAllRoles(PageHolder ph);
	public List findAllPrivileges();
	public void addRole(Role role, List privileges) throws RoleExistsException;
	public List findAllRoles();
	public List findAllPrivileges(PageHolder ph);
	public void updateUser(User user) throws UserExistsException ;
	public void updateUser(User user, String[] roles)throws UserExistsException;
	public void updateRole(Role role, List privileges);
	public void updateStatus(String id, short status);
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
	public User getUser(String userId);
	public Role getRole(Integer roleId);
	public boolean deleteUser(String userId);
	public int  deleteUsers(List<String> users);
	public void deleteRole(Integer roleId) throws RoleNotEmptyException;
	public void stopUser(String userId);
	public void resumeUser(String userId);
	
	public Role getRoleByName(String name);
	/**
	 * 修改用户密码
	 * @param userId
	 * @param oldPassword
	 * @param newPassword
	 * @return 0表示修改成功,1表示原密码错误
	 */
	public int modifyUserPassword(String userId, String oldPassword, String newPassword);
	/**
	 * 重置用户密码
	 * @param userId
	 * @param password
	 */
	public void resetUserPassword(String userId, String password);
	public void resetUserPassword(User user);
	public List queryUsers(String username, String name, PageHolder ph);
	public List queryUsersAndStatus(String username, String name, Short status, PageHolder ph);
	public List queryRoles(String roleName, PageHolder ph);
	
}
