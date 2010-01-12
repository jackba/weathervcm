package com.cma.intervideo.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.cma.intervideo.dao.IServiceDao;
import com.cma.intervideo.pojo.ServiceTemplate;
import com.cma.intervideo.service.IServiceService;
import com.radvision.icm.service.MeetingType;
import com.radvision.icm.service.vcm.ICMService;

public class ServiceServiceImpl implements IServiceService{
	
	private static Log logger = LogFactory.getLog(TerminalServiceImpl.class);
	
	private IServiceDao serviceDao;
	
	public void setServiceDao(IServiceDao serviceDao) {
		this.serviceDao = serviceDao;
	}
	
	public List<ServiceTemplate> findServices(){
		return serviceDao.findServices();
	}
	
	public List<ServiceTemplate> findServicesByClassification() {
		List<ServiceTemplate> lst = serviceDao.findServices();
		List<ServiceTemplate> lstRet = new ArrayList<ServiceTemplate>();
		if (lst == null || lst.size() == 0)
			return lstRet;
		
		Map<String, String> hm = new HashMap<String, String>();
		for (ServiceTemplate st : lst) {
			String classification = st.getServiceTemplateClassification();
			if (classification == null || classification.length() == 0 || hm.get(classification) != null)
				continue;
			
			lstRet.add(st);
			hm.put(classification, st.getServiceTemplateId());
		}
		
		return lstRet;

	}
	
	public Map<String, List<ServiceTemplate>> classifyServices() {
		Map<String, List<ServiceTemplate>> hm = new HashMap<String, List<ServiceTemplate>>();
		
		List<ServiceTemplate> lst = serviceDao.findServices();
		if (lst == null || lst.size() == 0)
			return hm;
		
		for (ServiceTemplate st : lst) {
			String classification = st.getServiceTemplateClassification();
			if (classification == null || classification.length() == 0)
				continue;
			
			List<ServiceTemplate> tmpLst = hm.get(classification);
			if (tmpLst == null) {
				tmpLst = new ArrayList<ServiceTemplate>();
				tmpLst.add(st);
				hm.put(classification, tmpLst);
			} else {
				tmpLst.add(st);
			}
		}
		
		return hm;
		
	}
	
	public void saveOrUpdate(ServiceTemplate service){
		serviceDao.saveOrUpdate(service);
	}
	
	public ServiceTemplate getServiceTemplate(String serviceTemplateId) {
		return serviceDao.getServiceTemplate(serviceTemplateId);
	}
	
	public void deleteServiceTemplatesByNewIds(List<String> newIds) {
		serviceDao.deleteServiceTemplatesByNewIds(newIds);
	}
	
	public void update() {
		logger.info("update...");
//		if (!RuntimeInfo.isIcmServiceConnected()) {
//			logger.warn("Cannot connect platform by web service, exit update!");
//			return;
//		}
		
		List<MeetingType> mts = ICMService.getMeetingTypes();
		int count = 0;
		List<String> newIds = new ArrayList<String>();
		for (int i = 0; mts != null && i < mts.size(); i++) {
			MeetingType mt = mts.get(i);
			if ("EMBEDDED".equals(mt.getBuiltInToken()) || "N/A".equals(mt.getServicePrefix()))
				continue;
			ServiceTemplate service = new ServiceTemplate();
			service.setServiceTemplateId(mt.getId());
			service.setServicePrefix(mt.getServicePrefix());
			service.setServiceTemplateName(mt.getName());
			service.setMatchingRate(mt.getBandwidth());
			service.setServiceTemplateDesc(mt.getDescription());
			service.setBuiltInToken(mt.getBuiltInToken());
			service.setSwitchingMode(mt.getSwitchingMode());
			service.setActiveFlag(true);
			this.saveOrUpdate(service);
			count++;
			newIds.add(mt.getId());
			logger.info("Service Template was downloaded from Platform and saved to VCM: " + service);
		}
		logger.info(count + " Service Template were downloaded from Platform and saved to VCM!");
		this.deleteServiceTemplatesByNewIds(newIds);
		logger.info("Deleted the Service Template(s) that were not downloaded from Platform!");
	}
	
}
