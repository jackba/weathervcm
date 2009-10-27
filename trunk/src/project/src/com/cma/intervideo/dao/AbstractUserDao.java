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
import com.cma.intervideo.pojo.User;
import com.cma.intervideo.pojo.UserRole;
import com.cma.intervideo.pojo.UserRoleId;
import com.cma.intervideo.util.PageHolder;

public class AbstractUserDao extends AbstractDAO<User, String> implements IUserDao{
	private static Log logger = LogFactory.getLog(AbstractUserDao.class);
	/**
	 * 根据用户姓名查找用户
	 */
	public User findUserByName(String name){
		List userList = this.getHibernateTemplate().find("from User user where user.userName='"+name+"' and user.status="+DataDictStatus.normalStatus);
		if((userList!=null)&&(userList.size()!=0)){
			return (User)userList.get(0);
		}else
			return null;
	}
	/**
	 * 根据用户名查找状态为有效的用户
	 * @param userName
	 * @return
	 */
	public User findUserByLoginId(String loginId){
		List l = this.getHibernateTemplate().find("from User user where user.status=0 and user.loginId=?",loginId);
		if((l!=null)&&(l.size()>0)){
			return (User)l.get(0);
		}else
			return null;
	}
	
	/**
	 * 根据用户号查找用户拥有哪些url的权限
	 * @param userId
	 * @return
	 */
	public List<Privilege> findPrivilegesByUserId(String userId){
		List<Privilege> result = new ArrayList<Privilege>();
		List l = this.getHibernateTemplate().find("from Privilege p, RolePrivilege rp, UserRole ur where p.privilegeId=rp.id.privilegeId and rp.id.roleId=ur.id.roleId and ur.id.userId=?)",userId);
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
		Session s = this.getSession();
		Connection conn = s.connection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try{
			pstmt = conn.prepareStatement("select url from privilege where privilege_id in (select privilege_id from role_privilege where role_id in (select role_id from user_role where user_id=?))");
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
		if(ph.isGetCount()){
			ph.setResultSize(this.getCount("from User user where user.status!=1"));
		}
		List userList = this.find("from User user where user.status!=1", ph);
		return userList;
	}
	public List queryUsers(String username, String name, PageHolder ph){
		StringBuffer sb = new StringBuffer("from User user where user.status!=1");
		if((username!=null)&&(!username.equals(""))){
			sb.append(" and username like '%"+username+"%'");
		}
		if((name!=null)&&(!name.equals(""))){
			sb.append(" and name like '%"+name+"%'");
		}
		if(ph.isGetCount()){
			ph.setResultSize((int)this.getCount2(sb.toString()));
		}
		return this.find(sb.toString(), ph);
	}
	public List queryUsersAndStatus(String username, String name, Short status, PageHolder ph){
		StringBuffer sb = new StringBuffer("from User user where user.status!=2");
		if((username!=null)&&(!username.equals(""))){
			sb.append(" and login_id like '%"+username+"%'");
		}
		if((name!=null)&&(!name.equals(""))){
			sb.append(" and user_name like '%"+name+"%'");
		}
		if((status!=null)&&(!status.equals(""))){
			sb.append(" and status = '"+status+"'");
			
		}
		if(ph.isGetCount()){
			ph.setResultSize((int)this.getCount2(sb.toString()));
		}
		return this.find(sb.toString(), ph);
	}
	public void addUser(User user){
		this.getHibernateTemplate().save(user);
	}
	public List findAllRoles(PageHolder ph){
		if(ph.isGetCount()){
			ph.setResultSize(this.getCount("from Role role where role.status=0"));
		}
		List roleList = this.find("from Role role where role.status=0", ph);
		return roleList;
	}
	public List queryRoles(String roleName, PageHolder ph){
		StringBuffer sb = new StringBuffer("from Role role where role.status=0");
		if((roleName!=null)&&(!roleName.equals(""))){
			sb.append(" and role.name like '%"+roleName+"%'");
		}
		if(ph.isGetCount()){
			ph.setResultSize(this.getCount(sb.toString()));
		}
		return this.find(sb.toString(), ph);
	}
	public List findAllPrivileges(){
		return this.getHibernateTemplate().find("from Privilege privilege");
	}
	public List findAllPrivileges(PageHolder ph){
		if(ph.isGetCount()){
			ph.setResultSize(this.getCount("from Privilege privilege"));
		}
		List privileges = this.find("from Privilege privilege", ph);
		return privileges;
	}
	public void addRole(Role role){
		this.getHibernateTemplate().save(role);
	}
	public void addRolePrivilege(Integer roleId, Integer privilegeId){
		RolePrivilegeId rolePrivilegeId = new RolePrivilegeId();
		rolePrivilegeId.setRoleId(roleId);
		rolePrivilegeId.setPrivilegeId(privilegeId);
		RolePrivilege rolePrivilege = new RolePrivilege();
		rolePrivilege.setId(rolePrivilegeId);
		this.getHibernateTemplate().save(rolePrivilege);
	}
	
	public List findAllRoles(){
		return this.getHibernateTemplate().find("from Role role where role.status=0");
	}
	public void addUserRole(String userId, Integer roleId){
		UserRoleId userRoleId = new UserRoleId();
		userRoleId.setUserId(userId);
		userRoleId.setRoleId(roleId);
		UserRole userRole = new UserRole();
		userRole.setId(userRoleId);
		this.getHibernateTemplate().save(userRole);
	}
	/**
	 * 根据用户号查找用户具有的角色,返回列表的元素为Role对象
	 * @param userId
	 * @return
	 */
	public List findRolesByUserId(String userId){
		Session s = this.getSession();
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
		Session s = this.getSession();
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
		Session s = this.getSession();
		Connection conn = s.connection();
		PreparedStatement pstmt = null;
		try{
			pstmt = conn.prepareStatement("delete from user_role where user_id=?");
			pstmt.setString(1, userId);
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
	/**
	 * 删除角色的所有权限
	 * @param roleId
	 */
	public void deleteRolePrivilegeByRoleId(Integer roleId){
		Session s = this.getSession();
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
		this.getHibernateTemplate().update(user);
	}
	public void saveOrUpdateUser(User user){
		if (user.getUserId() ==null){
			this.getHibernateTemplate().saveOrUpdate(user);
		}else{
			this.getHibernateTemplate().merge(user);
		}
	}
	public void updateRole(Role role){
		this.getHibernateTemplate().update(role);
	}
	public User getUser(String userId){
		return (User)this.getHibernateTemplate().get(User.class, userId);
	}
	public Role getRole(Integer roleId){
		return (Role)this.getHibernateTemplate().get(Role.class, roleId);
	}
	public Role getRoleByName(String name){
		List l = this.getHibernateTemplate().find("from Role role where role.status=0 and role.name=?",name);
		if((l!=null)&&(l.size()>0)){
			return (Role)l.get(0);
		}else
			return null;
	}
	public List findUserRoleByRoleId(Integer roleId){
		return this.getHibernateTemplate().find("from UserRole userRole where userRole.roleid=?",roleId);
	}
}
