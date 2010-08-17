package com.cma.intervideo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Session;

import com.cma.intervideo.constant.DataDictStatus;
import com.cma.intervideo.dao.util.AbstractDAO;
import com.cma.intervideo.pojo.Privilege;
import com.cma.intervideo.pojo.Role;
import com.cma.intervideo.pojo.RolePrivilege;
import com.cma.intervideo.pojo.RolePrivilegeId;
import com.cma.intervideo.pojo.Unit;
import com.cma.intervideo.pojo.User;
import com.cma.intervideo.pojo.UserRole;
import com.cma.intervideo.pojo.UserRoleId;
import com.cma.intervideo.pojo.UserUnit;
import com.cma.intervideo.pojo.UserUnitId;
import com.cma.intervideo.util.PageHolder;

public class AbstractUserDao extends AbstractDAO<User, String> implements IUserDao{
	private static Log logger = LogFactory.getLog(AbstractUserDao.class);
	private static String baseHql4ValidUser = "from User user where user.status!=" + DataDictStatus.invalidateStatus;
	private static String baseHql4NormalUser = "from User user where user.status=" + DataDictStatus.normalStatus;
	private static String baseHql4NormalRole = "from Role role where role.status=" + DataDictStatus.normalStatus;
	/**
	 * 根据用户姓名查找用户
	 */
	public User findUserByName(String name){
		List userList = getHibernateTemplate().find(baseHql4ValidUser + " and user.userName=?", name);
		if (userList != null && userList.size() > 0)
			return (User)userList.get(0);
		else
			return null;
	}
	/**
	 * 根据用户名查找状态为有效的用户
	 * @param userName
	 * @return
	 */
	public User findUserByLoginId(String loginId){
		List userList = getHibernateTemplate().find(baseHql4NormalUser + " and user.loginId=?", loginId);
		if (userList != null && userList.size() > 0)
			return (User)userList.get(0);
		else
			return null;
	}
	
	/**
	 * 根据用户号查找用户拥有哪些url的权限
	 * @param userId
	 * @return
	 */
	public List<Privilege> findPrivilegesByUserId(String userId){
		List<Privilege> result = new ArrayList<Privilege>();
		List l = getHibernateTemplate().find("from Privilege p, RolePrivilege rp, UserRole ur " +
				"where p.privilegeId=rp.id.privilegeId and rp.id.roleId=ur.id.roleId " +
				"and ur.id.userId=?",userId);
		if(l!=null){
			for(int i=0;i<l.size();i++){
				Object[] o = (Object[])l.get(i);
				result.add((Privilege)o[0]);
			}
		}
		return result;
	}
	/**
	 * 根据用户号查找用户拥有哪些url的权限
	 * @param userid
	 * @return
	 */
	public List findUrlsByUserId(String userid){
		List result = new ArrayList();
		Session s = getSession();
		Connection conn = s.connection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try{
			pstmt = conn.prepareStatement("select url from privilege " +
					"where privilege_id in (select privilege_id from role_privilege where role_id in (select role_id from user_role where user_id=?))");
			pstmt.setString(1, userid);
			rs = pstmt.executeQuery();
			String url = null;
			while(rs.next()){
				url = rs.getString(1);
				String[] urls = url.split(";");
				for(int i=0;i<urls.length;i++){
					if(urls[i].startsWith("action:")){
						urls[i] = "action:com.cma.intervideo.web.action."+urls[i].substring(7);
						result.add(urls[i]);
					}else if(urls[i].startsWith("json:")){
						urls[i] = "json:com.cma.intervideo.web.action."+urls[i].substring(5);
						result.add(urls[i]);
					}else if(urls[i].startsWith("dwr:")){
						urls[i] = "dwr:com.cma.intervideo.service.impl."+urls[i].substring(4);
						result.add(urls[i]);
					}
				}
			}
		}catch(Exception e){
			logger.error(e.toString());
		}finally{
			try{
				if(rs!=null)
					rs.close();
				if(pstmt!=null)
					pstmt.close();
			}catch(Exception ex){
				logger.error(ex.toString());
			}
		}
		return result;
	}
	
	public List findAllUsers(PageHolder ph){
		if(ph.isGetCount())
			ph.setResultSize(getCount(baseHql4ValidUser));
		return find(baseHql4ValidUser, ph);
	}
	
	public List<User> queryUsers(String username, String name, PageHolder ph){
		StringBuffer sb = new StringBuffer(baseHql4ValidUser);
		if((username!=null)&&(!username.equals("")))
			sb.append(" and username like '%"+username+"%'");
		if((name!=null)&&(!name.equals("")))
			sb.append(" and name like '%"+name+"%'");
		if(ph.isGetCount())
			ph.setResultSize((int)getCount2(sb.toString()));
		return find(sb.toString(), ph);
	}
	
	public List<User> queryUsersAndStatus(String username, String name, Short status, PageHolder ph){
		StringBuffer sb = new StringBuffer("from User user where user.status!="+DataDictStatus.invalidateStatus);
		if((username!=null)&&(!username.equals("")))
			sb.append(" and login_id like '%"+username+"%'");
		if((name!=null)&&(!name.equals("")))
			sb.append(" and user_name like '%"+name+"%'");
		if((status!=null)&&(!status.equals("")))
			sb.append(" and status = '"+status+"'");
		if(ph.isGetCount())
			ph.setResultSize((int)getCount2(sb.toString()));
		return find(sb.toString(), ph);
	}
	
	public void addUser(User user){
		getHibernateTemplate().save(user);
	}
	
	public List findAllRoles(PageHolder ph){
		if(ph.isGetCount())
			ph.setResultSize(getCount(baseHql4NormalRole));
		return find(baseHql4NormalRole, ph);
	}
	
	public List queryRoles(String roleName, PageHolder ph){
		StringBuffer sb = new StringBuffer(baseHql4NormalRole);
		if((roleName!=null)&&(!roleName.equals("")))
			sb.append(" and role.name like '%"+roleName+"%'");
		if(ph.isGetCount())
			ph.setResultSize(getCount(sb.toString()));
		return find(sb.toString(), ph);
	}
	
	public List findAllPrivileges(){
		return getHibernateTemplate().find("from Privilege privilege");
	}
	
	public List findAllPrivileges(PageHolder ph){
		if(ph.isGetCount()){
			ph.setResultSize(getCount("from Privilege privilege"));
		}
		List privileges = find("from Privilege privilege", ph);
		return privileges;
	}
	
	public void addRole(Role role){
		getHibernateTemplate().save(role);
	}
	
	public void addRolePrivilege(Integer roleId, Integer privilegeId){
		RolePrivilegeId rolePrivilegeId = new RolePrivilegeId();
		rolePrivilegeId.setRoleId(roleId);
		rolePrivilegeId.setPrivilegeId(privilegeId);
		RolePrivilege rolePrivilege = new RolePrivilege();
		rolePrivilege.setId(rolePrivilegeId);
		getHibernateTemplate().save(rolePrivilege);
	}
	
	public List findAllRoles(){
		return getHibernateTemplate().find(baseHql4NormalRole);
	}
	
	public void addUserRole(String userId, Integer roleId){
		UserRoleId userRoleId = new UserRoleId();
		userRoleId.setUserId(userId);
		userRoleId.setRoleId(roleId);
		UserRole userRole = new UserRole();
		userRole.setId(userRoleId);
		getHibernateTemplate().save(userRole);
	}
	
	public void addUserUnit(String userId, Integer unitId){
		UserUnitId userUnitId = new UserUnitId();
		userUnitId.setUserId(userId);
		userUnitId.setUnitId(unitId);
		UserUnit userUnit = new UserUnit();
		userUnit.setId(userUnitId);
		getHibernateTemplate().save(userUnit);
	}
	
	/**
	 * 根据用户号查找用户具有的角色,返回列表的元素为Role对象
	 * @param userId
	 * @return
	 */
	public List findRolesByUserId(String userId){
		Session s = getSession();
		Connection conn = s.connection();
		List result = new ArrayList();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try{
			pstmt = conn.prepareStatement("select * from role where status=0 and role_id in (select role_id from user_role where user_id=?)");
			pstmt.setString(1, userId);
			rs = pstmt.executeQuery();
			while(rs.next()){
				Role role = new Role();
				role.setRoleId(new Integer(rs.getInt("role_id")));
				role.setRoleName(rs.getString("role_name"));
				role.setDescription(rs.getString("description"));
				role.setStatus(new Short(rs.getShort("status")));
				result.add(role);
			}
		}catch(Exception e){
			logger.error(e.toString());
			return null;
		}finally{
			try{
				if(rs!=null)
					rs.close();
				if(pstmt!=null)
					pstmt.close();
			}catch(Exception ex){
				logger.error(ex.toString());
			}
		}
		return result;
	}
	
	/**
	 * 根据角色查找角色具有的权限
	 * @param roleId
	 * @return
	 */
	public List findPrivilegesByRoleId(Integer roleId){
		Session s = getSession();
		Connection conn = s.connection();
		List result = new ArrayList();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try{
			pstmt = conn.prepareStatement("select * from privilege where status=0 and privilege_id in (select privilege_id from role_privilege where role_id=?)");
			pstmt.setInt(1, roleId.intValue());
			rs = pstmt.executeQuery();
			while(rs.next()){
				Privilege privilege = new Privilege();
				privilege.setPrivilegeId(new Integer(rs.getInt("privilege_id")));
				privilege.setName(rs.getString("name"));
				privilege.setDescription(rs.getString("description"));
				privilege.setUrl(rs.getString("url"));
				result.add(privilege);
			}
		}catch(Exception e){
			logger.error(e.toString());
			return null;
		}finally{
			try{
				if(rs!=null)
					rs.close();
				if(pstmt!=null)
					pstmt.close();
			}catch(Exception ex){
				logger.error(ex.toString());
			}
		}
		return result;
	}
	
	/**
	 * 删除用户的所有角色
	 * @param userId
	 */
	public void deleteUserRoleByUserId(String userId){
		Session s = getSession();
		Connection conn = s.connection();
		PreparedStatement pstmt = null;
		try{
			pstmt = conn.prepareStatement("delete from user_role where user_id=?");
			pstmt.setString(1, userId);
			pstmt.executeUpdate();
			logger.info("Deleted successfully the role the user - userId: " + userId + " from VCM physically!");
		}catch(Exception e){
			logger.error(e.toString());
		}finally{
			try{
				if(pstmt!=null)
					pstmt.close();
			}catch(Exception ex){
				logger.error(ex.toString());
			}
		}
	}
	
	/**
	 * 删除用户的所有可选会场
	 * @param userId
	 */
	public void deleteUserUnitByUserId(String userId){
		Session s = getSession();
		Connection conn = s.connection();
		PreparedStatement pstmt = null;
		try{
			pstmt = conn.prepareStatement("delete from user_unit where user_id=?");
			pstmt.setString(1, userId);
			pstmt.executeUpdate();
			logger.info("Deleted successfully the unit the user - userId: " + userId + " from VCM physically!");
		}catch(Exception e){
			logger.error(e.toString());
		}finally{
			try{
				if(pstmt!=null)
					pstmt.close();
			}catch(Exception ex){
				logger.error(ex.toString());
			}
		}
	}
	
	/**
	 * 删除角色的所有权限
	 * @param roleId
	 */
	public void deleteRolePrivilegeByRoleId(Integer roleId){
		Session s = getSession();
		Connection conn = s.connection();
		PreparedStatement pstmt = null;
		try{
			pstmt = conn.prepareStatement("delete from role_privilege where roleid=?");
			pstmt.setInt(1, roleId.intValue());
			pstmt.executeUpdate();
		}catch(Exception e){
			logger.error(e.toString());
		}finally{
			try{
				if(pstmt!=null)
					pstmt.close();
			}catch(Exception ex){
				logger.error(ex.toString());
			}
		}
	}
	
	public void updateUser(User user){
		getHibernateTemplate().update(user);
	}
	
	public void saveOrUpdateUser(User user){
		if (user.getUserId() ==null){
			getHibernateTemplate().saveOrUpdate(user);
		}else{
			getHibernateTemplate().merge(user);
		}
	}
	
	public void updateRole(Role role){
		getHibernateTemplate().update(role);
	}
	
	public User getUser(String userId){
		return (User)getHibernateTemplate().get(User.class, userId);
	}
	
	public Role getRole(Integer roleId){
		return (Role)getHibernateTemplate().get(Role.class, roleId);
	}
	
	public Role getRoleByName(String name){
		List l = getHibernateTemplate().find(baseHql4NormalRole + " and role.name=?", name);
		if((l!=null)&&(l.size()>0))
			return (Role)l.get(0);
		else
			return null;
	}
	
	public List findUserRoleByRoleId(Integer roleId){
		return getHibernateTemplate().find("from UserRole userRole where userRole.roleid=?", roleId);
	}
	
	/**
	 * 根据用户号查找用户可选的主会场,返回列表的元素为Unit对象
	 * @param userId
	 * @return
	 */
	public List<Unit> findUnitsByUserId(String userId){
		Session s = getSession();
		Connection conn = s.connection();
		List<Unit> result = new ArrayList<Unit>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try{
			pstmt = conn.prepareStatement("select * from unit where unit_id in (select unit_id from user_unit where user_id=?)");
			pstmt.setString(1, userId);
			rs = pstmt.executeQuery();
			while(rs.next()){
				Unit unit = new Unit();
				unit.setUnitId(rs.getInt("unit_id"));
				unit.setPartyId(rs.getString("party_id"));
				unit.setUnitName(rs.getString("unit_name"));
				unit.setDescription(rs.getString("description"));
				result.add(unit);
			}
		}catch(Exception e){
			logger.error(e.toString());
			return null;
		}finally{
			try{
				if(rs!=null)
					rs.close();
				if(pstmt!=null)
					pstmt.close();
			}catch(Exception ex){
				logger.error(ex.toString());
			}
		}
		return result;
	}

}
