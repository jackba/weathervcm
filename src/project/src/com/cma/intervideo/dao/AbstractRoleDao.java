package com.cma.intervideo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;

import com.cma.intervideo.dao.util.AbstractDAO;
import com.cma.intervideo.pojo.Privilege;
import com.cma.intervideo.pojo.Role;
import com.cma.intervideo.pojo.RolePrivilege;
import com.cma.intervideo.pojo.RolePrivilegeId;
import com.cma.intervideo.pojo.User;
import com.cma.intervideo.util.PageHolder;

public class AbstractRoleDao extends AbstractDAO<User, Integer> implements IRoleDao{

	public void addRole(Role role) {
		this.getHibernateTemplate().save(role);
		
	}


	public void addRolePrivilege(Integer roleId, Integer privilegeId) {
		RolePrivilegeId rolePrivilegeId = new RolePrivilegeId();
		rolePrivilegeId.setRoleId(roleId);
		rolePrivilegeId.setPrivilegeId(privilegeId);
		RolePrivilege rolePrivilege = new RolePrivilege();
		rolePrivilege.setId(rolePrivilegeId);
		this.getHibernateTemplate().save(rolePrivilege);
	}
	
	

	public void deleteRolePrivilegeByRoleId(Integer roleId) {
		Session s = this.getSession();
		Connection conn = s.connection();
		PreparedStatement pstmt = null;
		try{
			pstmt = conn.prepareStatement("delete from role_privilege where role_id=?");
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

	public List findAllPrivileges() {
		return this.getHibernateTemplate().find("from Privilege privilege");
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
		Session s = this.getSession();
		Connection conn = s.connection();
		List result = new ArrayList();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try{
			pstmt = conn.prepareStatement("select * from privilege p where p.privilege_id in (select rp.privilege_id from role_privilege rp where rp.role_id=?)");
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
		System.out.println("resule.length="+result.size());
		return result;
	}

//	public List findRolesByUserId(Integer userId) {
//		Session s = this.getSession();
//		Connection conn = s.connection();
//		PreparedStatement pstmt = null;
//		try{
//			pstmt = conn.prepareStatement("delete from user_role where userid=?");
//			pstmt.setInt(1, userId.intValue());
//			pstmt.executeUpdate();
//		}catch(Exception e){
//			logger.error(e.toString());
//		}finally{
//			try{
//				if(pstmt!=null)
//					pstmt.close();
//			}catch(Exception ex){
//				logger.error(ex.toString());
//			}
//		}
//	}

	public List findUrlsByUserId(int userid) {
		// TODO Auto-generated method stub
		return null;
	}

	public Role getRole(Integer roleId) {
		return (Role)this.getHibernateTemplate().get(Role.class, roleId);
	}

	public Role getRoleByName(String name) {
		return null;
		
	}

	public void updateRole(Role role) {
		this.getHibernateTemplate().update(role);
		
	}
	public List queryRoles(String roleName, PageHolder ph){
		StringBuffer sb = new StringBuffer("from Role role ");
		
		return this.find(sb.toString(), ph);
	}
	public List listRoles(String roleName, PageHolder ph){
		StringBuffer sb = new StringBuffer("from Role role ");
		
		return this.find(sb.toString(), ph);
	}

	
	public Role findRoleByRoleName(String roleName){
		
		List l = this.getHibernateTemplate().find("from Role role where  role.roleName=?",roleName);
		if((l!=null)&&(l.size()>0)){
			return (Role)l.get(0);
		}else
			return null;
	}

	public void deleteRole(Integer roleId) {
		Session s = this.getSession();
		Connection conn = s.connection();
		PreparedStatement pstmt = null;
		try{
			pstmt = conn.prepareStatement("delete from role where role_id=?");
			pstmt.setInt(1, roleId.intValue());
			pstmt.executeUpdate();
			pstmt = conn.prepareStatement("delete from role_privilege where role_id=?");
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

	public List findRolesByUserId(Integer userId) {
		// TODO Auto-generated method stub
		return null;
	}
	public void modifyRole(Role role) {
		this.getHibernateTemplate().update(role);
	}

	public List queryRoles(String name, String remark, Short status,
			PageHolder ph) {
		StringBuffer sb = new StringBuffer("from Role r where 1=1");
System.out.println("=======status:"+status);		
		if((name!=null)&&(!name.equals(""))){
			sb.append(" and roleName like '%"+name+"%'");
		}
		if((remark!=null)&&(!remark.equals(""))){
			sb.append(" and description like '%"+remark+"%'");
		}
		if((status!=null)&&(!status.equals(""))){
			if(status==0){
				sb.append(" and status =0 ");
			} else if(status==1) {
				sb.append(" and status = 1");
			} 
		}
		
		if(ph.isGetCount()){
			ph.setResultSize((int)this.getCount2(sb.toString()));
		}
		System.out.println("---------sb="+sb);
		return this.find(sb.toString(), ph);
	}


	public boolean queryUserRoleByRoleId(Integer roleId) {
		Session s = this.getSession();
		Connection conn = s.connection();
//		List result = new ArrayList();
		boolean result =false;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try{
			pstmt = conn.prepareStatement("select * from user_role ur where ur.role_id = ?");
			pstmt.setInt(1, roleId.intValue());
			rs = pstmt.executeQuery();
			while(rs.next()){
				result = true;
			}
		}catch(Exception e){
			logger.error(e.toString());
			return false;
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


	public List findPrivilegesByRoleId_Exclud(Integer roleId) {
		Session s = this.getSession();
		Connection conn = s.connection();
		List result = new ArrayList();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try{
			pstmt = conn.prepareStatement("select * from privilege p where p.privilege_id not in (select rp.privilege_id from role_privilege rp where rp.role_id=?)");
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
		System.out.println("resule.length="+result.size());
		return result;
	}


	public void saveOrUpdateRole(Role newRole) {
		if (newRole.getRoleId() ==null){
			this.getHibernateTemplate().saveOrUpdate(newRole);
		}else{
			this.getHibernateTemplate().merge(newRole);
		}
		
	}
}
