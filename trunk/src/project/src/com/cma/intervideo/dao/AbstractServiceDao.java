package com.cma.intervideo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Session;

import com.cma.intervideo.constant.DataDictStatus;
import com.cma.intervideo.dao.util.AbstractDAO;
import com.cma.intervideo.pojo.ServiceTemplate;

public class AbstractServiceDao 
						extends AbstractDAO<ServiceTemplate, String>
						implements IServiceDao {
	
	private final static Log logger = LogFactory.getLog(AbstractServiceDao.class);

	public List<ServiceTemplate> findServices() {
		String hql = "from ServiceTemplate s where s.activeFlag='" + DataDictStatus.EnableStatus + "' order by s.serviceTemplateId";
		List<ServiceTemplate> lst = getHibernateTemplate().find(hql);
		logger.info("Found " + (lst == null ? 0 : lst.size()) + " ServiceTemplate(s), HQL: " + hql);
		return lst;
	}

	public ServiceTemplate getServiceTemplate(String serviceTemplateId) 
	{
		ServiceTemplate st =(ServiceTemplate) getHibernateTemplate().get(ServiceTemplate.class, serviceTemplateId);
		if (st == null)
			logger.info("Didn't found ServiceTemplate, serviceTemplateId: " + serviceTemplateId);
		else
			logger.info("Found ServiceTemplate, serviceTemplateId: " + serviceTemplateId + "; serviceTemplateName: " + st.getServiceTemplateName());
		return st;
	}
	
	public void deleteServiceTemplatesByNewIds(List<String> newIds) {
		if (newIds == null || newIds.size() == 0)
			return;
		
		Session s = this.getSession();
		Connection conn = s.connection();
		PreparedStatement pstmt = null;
		try{
			String tmp = "'" + newIds.get(0) + "'";
			for (int i = 1; i < newIds.size(); i++)
				tmp += ", '" + newIds.get(i) + "'";
			String sql = "UPDATE service_template set active_flag=" + DataDictStatus.DisableStatus + " where service_template_id not in (" + tmp + ")";
			pstmt = conn.prepareStatement(sql);
			pstmt.executeUpdate();
			logger.info("Executed successfully SQL Statement: " + sql);
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
	
}
