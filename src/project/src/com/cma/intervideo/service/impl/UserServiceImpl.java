package com.cma.intervideo.service.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.directwebremoting.WebContext;
import org.directwebremoting.WebContextFactory;

import com.cma.intervideo.constant.DataDictStatus;
import com.cma.intervideo.dao.ILogDao;
import com.cma.intervideo.dao.IUserDao;
import com.cma.intervideo.exception.RoleExistsException;
import com.cma.intervideo.exception.RoleNotEmptyException;
import com.cma.intervideo.exception.UserExistsException;
import com.cma.intervideo.pojo.Privilege;
import com.cma.intervideo.pojo.Role;
import com.cma.intervideo.pojo.User;
import com.cma.intervideo.service.IUserService;
import com.cma.intervideo.util.MD5;
import com.cma.intervideo.util.PageHolder;
import com.cma.intervideo.util.UserPrivilege;
import com.radvision.icm.service.UserInfo;
import com.radvision.icm.service.UserResult;
import com.radvision.icm.service.vcm.ICMService;

public class UserServiceImpl implements IUserService {
	private static Log logger = LogFactory.getLog(UserServiceImpl.class);
	private IUserDao userDao;
	private ILogDao logDao;
	
	/**
	 * 根据用户号查找用户拥有哪些url的权限
	 * @param userId
	 * @return
	 */
	public List<Privilege> findPrivilegesByUserId(String userId){
		return userDao.findPrivilegesByUserId(userId);
	}
	
	/**
	 * 根据用户号查找用户拥有哪些url的权限
	 * @param userid
	 * @return
	 */
	public List findUrlsByUserId(String userid){
		return userDao.findUrlsByUserId(userid);
	}
	
	public void setLogDao(ILogDao logDao) {
		this.logDao = logDao;
	}

	public void setUserDao(IUserDao userDao) {
		this.userDao = userDao;
	}

	/**
	 * 根据用户姓名查找用户
	 */
	public User findUserByName(String name) {
		return userDao.findUserByName(name);
	}

	/**
	 * 根据用户名查找状态为有效的用户
	 * 
	 * @param userName
	 * @return
	 */
	public User findUserByLoginId(String loginId) {
		return userDao.findUserByLoginId(loginId);
	}

	public int userLogin(String userName, String password, UserPrivilege up) {
		User user = userDao.findUserByName(userName);
		if (user == null) {
			return INVALID_USERNAME;
		} else {
			if (user.getPassword().equals(
					new com.cma.intervideo.util.MD5().getMD5ofStr(password))
					|| (user.getPassword().equals(password))) {
				up.setUserName(user.getUserName());
				up.setUserId(user.getUserId());
				return SUCCESS;
			} else {
				return INVALID_PASSWORD;
			}
		}
	}

	public List findAllUsers(PageHolder ph) {
		return userDao.findAllUsers(ph);
	}

	public List queryUsers(String username, String name, PageHolder ph) {
		return userDao.queryUsers(username, name, ph);
	}

	public List queryUsersAndStatus(String username, String name, Short status, PageHolder ph) {
		return userDao.queryUsersAndStatus(username, name, status, ph);
	}
	
	public int addSuperToPlatform() {
		UserInfo ui = ICMService.getUserByLoginId("super");
		if (ui != null && ui.getUserId() != null)
			return 0;
		User usr = userDao.findUserByLoginId("super");
		UserResult ur = ICMService.setUser(usr);
		if (ur == null || !ur.isSuccess())
			return -1;
		return 1;
	}

	public void addUser(User user, String[] roles, String[] units) throws UserExistsException
	{
		User tmp = userDao.findUserByLoginId(user.getLoginId());
		if (tmp != null)
			throw new UserExistsException("用户 " + user.getUserName() + " 已经存在!");
		
		// save user to ICM
		logger.info("First Adding user to iCM platform: " + user);
		user.setUserId(null);
		UserResult ur = ICMService.setUser(user);
		if (ur == null || !ur.isSuccess())
			throw new UserExistsException("平台保存用户" + user.getLoginId() + " 失败!");
		
		// save user to VCM
		try {
			String userId = ur.getUserInfos().get(0).getUserId(); 
			logger.info("Then Adding user to VCM, userId in platform: " + userId);
			user.setUserId(userId);
			userDao.addUser(user);
			if (roles != null)
			{
				for (int i = 0; i < roles.length; i++)
					userDao.addUserRole(user.getUserId(), new Integer(roles[i]));
			}
			if(units!=null){
				for(int i=0;i<units.length;i++){
					userDao.addUserUnit(user.getUserId(), new Integer(units[i]));
				}
			}
		} catch (Exception e)
		{
			logger.error(e.toString());
			logger.info("if fail to save user to VCM, need to delete the user from iCM platform that was saved just now!!");
			ICMService.deleteUser(ur.getUserInfos().get(0).getUserId());
			throw new UserExistsException("用户 " + user.getLoginId() + " 已经存在!");
		}
	}

	public List findAllRoles(PageHolder ph) {
		return userDao.findAllRoles(ph);
	}

	public List queryRoles(String roleName, PageHolder ph) {
		return userDao.queryRoles(roleName, ph);
	}

	public List findAllPrivileges() {
		return userDao.findAllPrivileges();
	}

	public List findAllPrivileges(PageHolder ph) {
		return userDao.findAllPrivileges(ph);
	}

	public void addRole(Role role, List privileges) throws RoleExistsException {
		Role tmp = userDao.getRoleByName(role.getRoleName());
		if (tmp != null)
			throw new RoleExistsException("角色" + role.getRoleName() + "已经存在！");
		userDao.addRole(role);
		for (int i = 0; i < privileges.size(); i++) {
			String privilegeId = (String) privileges.get(i);
			userDao
					.addRolePrivilege(role.getRoleId(),
							new Integer(privilegeId));
		}
	}

	public List findAllRoles() {
		return userDao.findAllRoles();
	}

	public void updateUser(User user) throws UserExistsException {
		try {
			logger.info("First Updating platform user: " + user);
			// save user to ICM
			UserResult ur = ICMService.setUser(user);
			if (ur == null || !ur.isSuccess())
				throw new UserExistsException("平台更新用户" + user.getLoginId()
						+ " 失败!");

			logger.info("Then Updating VCM user...");
			// save user to VCM
			userDao.saveOrUpdateUser(user);
		} catch (Exception e) {
			logger.error(e.toString());
			throw new UserExistsException("用户 " + user.getLoginId() + " 不存在!");
		}
	}

	public void updateUser(User user, String[] roles, String[] units)
			throws UserExistsException {
		try {
			logger.info("Starting to update user...");
			User u = userDao.getUser(user.getUserId());
			logger.info("User before updating: " + u);
			
			if ("super".equals(user.getLoginId()))
				addSuperToPlatform();
			
			u.setAddress(user.getAddress());
			u.setCompany(user.getCompany());
			u.setDescription(user.getDescription());
			u.setEmail(user.getEmail());
			u.setHomeTelephone(user.getHomeTelephone());
			u.setLoginId(user.getLoginId());
			u.setMobile(user.getMobile());
			u.setOfficeTelephone(user.getOfficeTelephone());
			u.setSex(user.getSex());
			u.setUserName(user.getUserName());
			u.setUserType(user.getUserType());
			if(user.getDefaultUnitId()>0){
				u.setDefaultUnitId(user.getDefaultUnitId());
			}
			Date d = Calendar.getInstance().getTime();
			u.setUpdateTime(d);

			// save user to ICM
			logger.info("User who will be after updating: " + u);
			
			logger.info("First updating platform user...");
			UserResult ur = ICMService.setUser(u);
			if (ur == null || !ur.isSuccess())
			{
				throw new UserExistsException("平台更新用户" + user.getLoginId()
						+ " 失败!");
			}
			
			// save user to VCM
			logger.info("Then updating VCM user...");
			userDao.deleteUserRoleByUserId(user.getUserId());
			if(units!=null)
				userDao.deleteUserUnitByUserId(user.getUserId());
			if (roles != null) {
				for (int i = 0; i < roles.length; i++) {
					userDao
							.addUserRole(user.getUserId(),
									new Integer(roles[i]));
				}
			}
			if (units != null){
				for(int i=0;i<units.length;i++){
					userDao.addUserUnit(user.getUserId(), new Integer(units[i]));
				}
			}
			userDao.saveOrUpdateUser(u);
		} catch (Exception e) {
			logger.error(e.toString());
			throw new RuntimeException("用户 " + user.getLoginId() + " 不存在!");
		}
	}

	
	public void stopUser(String userId) {
		User user = userDao.getUser(userId);
		user.setStatus(DataDictStatus.pauseStatus);
		userDao.updateUser(user);
	}

	public void resumeUser(String userId) {
		User user = userDao.getUser(userId);
		user.setStatus(DataDictStatus.normalStatus);
		userDao.updateUser(user);
	}

	public void deleteRole(Integer roleId) throws RoleNotEmptyException {
		List tmp = userDao.findUserRoleByRoleId(roleId);
		if ((tmp != null) && (tmp.size() > 0)) {
			throw new RoleNotEmptyException("角色正被用户使用，不能删除！");
		}
		Role role = userDao.getRole(roleId);
		role.setStatus(DataDictStatus.invalidateStatus);
		userDao.updateRole(role);
	}

	public void updateRole(Role role, List privileges) {
		userDao.updateRole(role);
		userDao.deleteRolePrivilegeByRoleId(role.getRoleId());
		for (int i = 0; i < privileges.size(); i++) {
			String privilegeId = (String) privileges.get(i);
			userDao
					.addRolePrivilege(role.getRoleId(),
							new Integer(privilegeId));
		}
	}

	/**
	 * 根据用户号查找用户具有的角色,返回列表的元素为Role对象
	 * 
	 * @param userId
	 * @return
	 */
	public List findRolesByUserId(String userId) {
		return userDao.findRolesByUserId(userId);
	}

	/**
	 * 根据角色查找角色具有的权限
	 * 
	 * @param roleId
	 * @return
	 */
	public List findPrivilegesByRoleId(Integer roleId) {
		return userDao.findPrivilegesByRoleId(roleId);
	}

	public User getUser(String userId) {
		return userDao.getUser(userId);
	}

	public Role getRole(Integer roleId) {
		return userDao.getRole(roleId);
	}

	public Role getRoleByName(String name) {
		return userDao.getRoleByName(name);
	}

	/**
	 * 修改用户密码
	 * 
	 * @param userId
	 * @param oldPassword
	 * @param newPassword
	 * @return 0表示修改成功,1表示原密码错误
	 */
	public int modifyUserPassword(String userId, String oldPassword,
			String newPassword) {
		User user = userDao.getUser(userId);
		MD5 md5 = new MD5();
		if ((user.getPassword().equals(oldPassword))
				|| (user.getPassword().equals(md5.getMD5ofStr(oldPassword)))) {
			user.setPassword(md5.getMD5ofStr(newPassword));
			userDao.updateUser(user);
			return 0;
		} else
			return 1;
	}

	/**
	 * 重置用户密码
	 * 
	 * @param userId
	 * @param password
	 */
	public void resetUserPassword(String userId, String password) {
		User user = userDao.getUser(userId);
		// MD5 md5 = new MD5();
		// user.setPassword(md5.getMD5ofStr(password));
		user.setPassword(password);

		userDao.saveOrUpdateUser(user);
	}

	public void resetUserPassword(User user) {
		try {
			userDao.saveOrUpdateUser(user);
		} catch (Exception e) {
			logger.error(e.toString());
			// throw new UserExistsException("用户 " + user.getUsername()+
			// " 不存在!");
		}
	}

	public boolean deleteUser(String userId) {
		logger.info("Deleting user - userId: " + userId);
		WebContext ctx = WebContextFactory.get();
		HttpSession s = ctx.getSession();
		UserPrivilege up = (UserPrivilege)s.getAttribute("userPrivilege");
		// first delete user from ICM
		logger.info("First Deleting platform user - userId: " + userId);
		UserResult ur = ICMService.deleteUser(userId);
		if (ur == null || !ur.isSuccess())
		{
			logger.warn("Failed to delete user - userId: " + userId + " due to fail to delete this user from platform!");
			return false;
		}
		
		// then delete user from VCM
		logger.info("Then Deleting VCM user - userId: " + userId + ", only set to invalid status and do not remove it physically.");
		User user = userDao.getUser(userId);
		user.setStatus(DataDictStatus.invalidateStatus);
		userDao.deleteUserRoleByUserId(userId);
		userDao.deleteUserUnitByUserId(userId);
		userDao.updateUser(user);
		logDao.addLog(up.getUserId(), ILogDao.type_delete_user, "删除用户"+user.getLoginId());
		
		logger.info("user - userId: " + userId + " is deleted from both platform and VCM.");
		
		return true;
		
	}

	public int deleteUsers(List<String> users)
	{
		for (int i = 0; i < users.size(); i++)
		{
			boolean b = deleteUser(users.get(i));
			if (!b)
				continue;
//			userDao.removeObjectByID(users.get(i));
		}
		return users.size();
	}

	public void updateStatus(String id, short status) {
		User user = userDao.getObjectByID(id);
		user.setStatus(status);
		userDao.updateObject(user);
		logger.info("Updated successfully user status - new status: " + status + "; " + user);
	}
	
	/**
	 * 根据用户号查找用户可选的主会场,返回列表的元素为Unit对象
	 * @param userId
	 * @return
	 */
	public List findUnitsByUserId(String userId){
		return userDao.findUnitsByUserId(userId);
	}
}
