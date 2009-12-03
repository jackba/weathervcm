package com.cma.intervideo.service.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.cma.intervideo.dao.IConfTemplateDao;
import com.cma.intervideo.dao.IServiceDao;
import com.cma.intervideo.pojo.ConfTemplate;
import com.cma.intervideo.pojo.ServiceTemplate;
import com.cma.intervideo.service.IConfTemplateService;
import com.cma.intervideo.util.PageHolder;
import com.cma.intervideo.util.ParamVo;

public class ConfTemplateServiceImpl implements IConfTemplateService {
	private final static Log logger = LogFactory.getLog(ConfTemplateServiceImpl.class);
	private IConfTemplateDao confTemplateDao;
	private IServiceDao serviceDao;

	public void setConfTemplateDao(IConfTemplateDao confTemplateDao) {
		this.confTemplateDao = confTemplateDao;
	}
	
	public void setServiceDao(IServiceDao serviceDao) {
		this.serviceDao = serviceDao;
	}

	public List<ConfTemplate> findConfTemplates(List<ParamVo> params, PageHolder ph) {
		List<ConfTemplate> listConfTemplate = confTemplateDao.findConfTemplates(params, ph);
		for (int i = 0; i < listConfTemplate.size(); i++)
			updateServiceTemplateInfo(listConfTemplate.get(i));
		return listConfTemplate;
	}
	
	public List<ConfTemplate> findConfTemplates(String userId) {
		List<ConfTemplate> listConfTemplate = confTemplateDao.findConfTemplates(userId);
		for (int i = 0; i < listConfTemplate.size(); i++)
			updateServiceTemplateInfo(listConfTemplate.get(i));
		return listConfTemplate;
	}
	
	private void updateServiceTemplateInfo(ConfTemplate confTemplate) {
		if (confTemplate == null || confTemplate.getServiceTemplateId() == null 
				|| confTemplate.getServiceTemplateId().length() == 0)
			return;
		
		ServiceTemplate st = serviceDao.getServiceTemplate(confTemplate.getServiceTemplateId());
		if (st == null)
			return;
		
		confTemplate.setServiceTemplateDesc(st.getServiceTemplateDesc());
		confTemplate.setServiceTemplateName(st.getServiceTemplateName());
	}

	public int deleteConfTemplates(List<String> lst) {
		int deleted = 0;
		for (int i = 0; i < lst.size(); i++) {
			String confTemplateId = lst.get(i);
			logger.info("to delete Conference Template from VCM - roomId: " + confTemplateId);
			try {
				confTemplateDao.removeObjectByID(confTemplateId);
			} catch (Exception e) {
				logger.error("Exception on deleting Virtual Room from VCM - roomId: " 
						+ confTemplateId + " - " + e.getMessage());
				continue;
			}
			deleted++;
		}
		return deleted;
	}

	public void saveOrUpdate(ConfTemplate ct) throws Exception {
		try {
			logger.info("to save Virtual Room to VCM......");
			confTemplateDao.saveOrUpdate(ct);
		} catch (Exception e) {
			logger.info("Exception on saving Virtual Room to VCM......");
			throw new Exception("系统保存虚拟房间" + ct.getConfTemplateId() + " 失败!");
		}
	}

	public ConfTemplate getConfTemplateById(String confTemplateId) {
		ConfTemplate ct = confTemplateDao.getObjectByID(confTemplateId);
		updateServiceTemplateInfo(ct);
		return ct;
	}
}
