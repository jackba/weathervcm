package com.cma.intervideo.service.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.cma.intervideo.dao.IConfTemplateDao;
import com.cma.intervideo.dao.IServiceDao;
import com.cma.intervideo.dao.IUnitDao;
import com.cma.intervideo.pojo.ConfTemplate;
import com.cma.intervideo.pojo.ServiceTemplate;
import com.cma.intervideo.pojo.Unit;
import com.cma.intervideo.service.IConfTemplateService;
import com.cma.intervideo.util.PageHolder;
import com.cma.intervideo.util.ParamVo;

public class ConfTemplateServiceImpl implements IConfTemplateService {
	
	private final static Log logger = LogFactory.getLog(ConfTemplateServiceImpl.class);
	private IConfTemplateDao confTemplateDao;
	private IServiceDao serviceDao;
	private IUnitDao unitDao;

	public void setConfTemplateDao(IConfTemplateDao confTemplateDao) {
		this.confTemplateDao = confTemplateDao;
	}
	
	public void setServiceDao(IServiceDao serviceDao) {
		this.serviceDao = serviceDao;
	}
	
	public void setUnitDao(IUnitDao unitDao) {
		this.unitDao = unitDao;
	}

	public void createConfTemplate(ConfTemplate confTemplate, String[] units)
			throws Exception
	{
		logger.info("Creating new ConfTemplate to VCM: " + confTemplate);
		Integer id = confTemplateDao.addObject(confTemplate);
		
		if (units != null)
		{
			for (int i = 0; i < units.length; i++)
			{
				confTemplateDao.addConfTemplateUnit(id, new Integer(units[i]));
			}
		}
		logger.info("Created successfully new ConfTemplate to VCM, " +
				"confTemplateId: " + id + 
				"; confTemplateName" + confTemplate.getConfTemplateName() + 
				"; unit size: " + (units==null ? 0 : units.length));
	}

	public List<Unit> findAllUnits() {
		return confTemplateDao.findAllUnits();
	}

	public List<ConfTemplate> findConfTemplates(List<ParamVo> params, PageHolder ph) {
		List<ConfTemplate> lst = confTemplateDao.findConfTemplates(params, ph);
		if (lst != null)
		{
			int size = lst.size();
			for (int i = 0; i < size; i++)
				updateAdditionalInfo(lst.get(i));
		}
		return lst;
	}
	
	private void updateAdditionalInfo(ConfTemplate conf) {
		updateUnitInfo(conf);
		updateMainUnitInfo(conf);
		updateServiceTemplateInfo(conf);
	}
	
	private void updateUnitInfo(ConfTemplate conf) {
		if (conf == null)
			return;
		
		List<Unit> listUnit = findUnitsByConfTemplateId(conf.getConfTemplateId()+"", true);
		if (listUnit == null || listUnit.size() == 0)
			return;
		
		String names = "";
		int unitSize = listUnit.size();
		for (int j = 0; j < unitSize-1; j++) {
			names += listUnit.get(j).getUnitName() + ", ";
		}
		names += listUnit.get(unitSize-1).getUnitName();
		conf.setConfUnitNames(names);
	}
	
	private void updateMainUnitInfo(ConfTemplate conf) {
		if (conf == null || conf.getMainUnit() == null)
			return;
		
		Unit unit = unitDao.getObjectByID(conf.getMainUnit());
		if (unit == null)
			return;
		
		conf.setMainUnitName(unit.getUnitName());
	}
	
	private void updateServiceTemplateInfo(ConfTemplate conf) {
		if (conf == null || conf.getServiceTemplateId() == null || conf.getServiceTemplateId().length() == 0)
			return;
		
		ServiceTemplate st = serviceDao.getServiceTemplate(conf.getServiceTemplateId());
		if (st == null)
			return;
		
		conf.setServiceTemplateDesc(st.getServiceTemplateDesc());
		conf.setServiceTemplateName(st.getServiceTemplateName());
	}

	public List<Unit> findUnitsByConfTemplateId(String confTemplateId, boolean selected) {
		return confTemplateDao.findUnitsByConfTemplateId(Integer.parseInt(confTemplateId), selected);
	}

	public ConfTemplate getConfTemplateById(String confTemplateId) {
		ConfTemplate conf = confTemplateDao.getObjectByID(new Integer(confTemplateId));
		updateAdditionalInfo(conf);
		return conf;
	}
	
	public void modifyConfTemplate(ConfTemplate confTemplate, String[] units) throws Exception {
		logger.info("Modifying ConfTemplate in VCM: " + confTemplate);
		confTemplateDao.saveOrUpdate(confTemplate);
		logger.info("Deleting Units for ConfTemplate: " + confTemplate.getConfTemplateName());
		confTemplateDao.deleteConfTemplateUnitsByConfTemplateId(confTemplate.getConfTemplateId());
		if (units != null)
		{
			for (int i = 0; i < units.length; i++) {
				confTemplateDao.addConfTemplateUnit(confTemplate.getConfTemplateId(), new Integer(units[i]));
			}
		}
		logger.info("Created successfully new ConfTemplate to VCM, " +
				"confTemplateId: " + confTemplate.getConfTemplateId() + 
				"; confTemplateName" + confTemplate.getConfTemplateName() + 
				"; unit size: " + (units==null ? 0 : units.length));
//		} catch (Exception e) {
//			logger.warn("Failed to modify ConfTemplate due to excepton: " + e.toString());
//			throw new Exception("系统修改表单模板" + confTemplate.getConfTemplateId() + "失败 !");
//		}
	}

	public int deleteConfTemplates(List<String> confTemplates)
	{
		int deleted = 0;
		for (int i = 0; i < confTemplates.size(); i++)
		{
			boolean b = deleteConfTemplate(confTemplates.get(i));
			if (b)
				deleted++;
		}
		logger.info(deleted + " ConfTemplates were deleted successfully!");
		return deleted;
	}

	public boolean deleteConfTemplate(String confTemplateId)
	{
		try
		{
			confTemplateDao.removeObjectByID(Integer.parseInt(confTemplateId));
			logger.info("Deleted successfully ConfTemplate with confTemplateId:" + confTemplateId);
			return true;
		} catch (Exception e)
		{
			logger.error("Failed to delete ConfTemplate with confTemplateId:" + confTemplateId + " due to exception: " + e.getMessage());
			return false;
		}
	}
	
}
