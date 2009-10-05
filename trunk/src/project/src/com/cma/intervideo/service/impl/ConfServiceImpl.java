package com.cma.intervideo.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.cma.intervideo.dao.IConfDao;
import com.cma.intervideo.dao.IServiceDao;
import com.cma.intervideo.dao.IUnitDao;
import com.cma.intervideo.pojo.Conference;
import com.cma.intervideo.pojo.ServiceTemplate;
import com.cma.intervideo.pojo.Unit;
import com.cma.intervideo.service.IConfService;
import com.cma.intervideo.util.PageHolder;
import com.cma.intervideo.util.ParamVo;
import com.radvision.icm.service.ScheduleResult;
import com.radvision.icm.service.vcm.ICMService;

public class ConfServiceImpl implements IConfService {
	private static final Log logger = LogFactory.getLog(ConfServiceImpl.class);
	private IConfDao confDao;
	private IUnitDao unitDao;
	private IServiceDao serviceDao;

	public void setConfDao(IConfDao confDao) {
		this.confDao = confDao;
	}
	
	public void setUnitDao(IUnitDao unitDao) {
		this.unitDao = unitDao;
	}
	
	public void setServiceDao(IServiceDao serviceDao) {
		this.serviceDao = serviceDao;
	}
		
	public int deleteReserves(List<String> reserves) {
		int deleted = 0;
		for (int i = 0; i < reserves.size(); i++) {
			try {
				deleteReserve(reserves.get(i));
			} catch (Exception e) {
				e.printStackTrace();
			}
			deleted++;
		}
		return deleted;
	}

	public void deleteReserve(String reserve) {
		String[] ids = reserve.split(",");
		if (ids == null)
			return;
		String confId, radConfId;
		if (ids.length == 1) {
			confId = ids[0];
			radConfId = ids[0];
		} else {
			confId = ids[0];
			radConfId = ids[1];
		}
		logger.info("to delete Conference from VCM - conferenceId: " + confId);
		try {
			confDao.deleteConfUnitsByConfId(confId);
			confDao.removeObjectByID(new Integer(confId));
		} catch (Exception e) {
			logger
					.info("Exception on deleting Conference from VCM - conferenceId: "
							+ confId + " - " + e.getMessage());
			return;
		}

		logger.info("to delete Conference from iCM platform - radConfId: "
				+ radConfId);
		ICMService.cancelConference(radConfId);
	}

	public List<Conference> findConfs(List<ParamVo> params, PageHolder ph) {
		List<Conference> listConf = confDao.findConfs(params, ph);
		if (listConf != null) {
			int confSize = listConf.size();
			for (int i = 0; i < confSize; i++)
				updateAdditionalInfo(listConf.get(i));
		}
		return listConf;
	}

	public void createConf(Conference conf, String[] units) throws Exception {
		logger
				.info("to create conference to iCM platform...... - conference subject: "
						+ conf.getSubject());
		conf.setRadConferenceId(null);
		List<String> listTerminalId = getTerminalIdList(units);
		ScheduleResult sr = ICMService.createConference(conf, listTerminalId);
		if (sr == null || !sr.isSuccess())
			throw new Exception("平台预约会议" + conf.getSubject() + " 失败!");
		try {
			logger.info("to create conference to VCM......");
			conf.setRadConferenceId(sr.getConferenceId());
			conf.setVirtualConfId(sr.getConferenceInfo()
					.getDialableConferenceId());
			confDao.saveOrUpdate(conf);
			if (units != null) {
				for (int i = 0; i < units.length; i++) {
					confDao.addConfUnit(conf.getConferenceId(), new Integer(
							units[i]));
				}
			}
		} catch (Exception e) {
			logger.error(e.toString());
			logger
					.info("if fail to save user to VCM, need to delete the user from iCM platform that was saved just now!!");
			ICMService.cancelConference(sr.getConferenceId());
			throw new Exception("系统保存会议失败 - conference subject: "
					+ conf.getSubject());
		}
	}
	
	private List<String> getTerminalIdList(String[] units) {
		if (units == null || units.length == 0)
			return null;
		List<String> listTerminalId = new ArrayList<String>();
		for (int i = 0; i < units.length; i++) {
			Unit unit = unitDao.getObjectByID(new Integer(units[i]));
			if (unit != null && unit.getPartyId() != null && unit.getPartyId().length() > 0)
				listTerminalId.add(unit.getPartyId());
		}
		return listTerminalId;
	}

	public void modifyConf(Conference conf, String[] units) throws Exception {
		logger
				.info("to modify conference to iCM platform...... - rad conference id: "
						+ conf.getRadConferenceId());
		List<String> listTerminalId = getTerminalIdList(units);
		ScheduleResult sr = ICMService.modifyConference(conf, listTerminalId);
		if (sr == null || !sr.isSuccess())
			throw new Exception("平台修改会议" + conf.getRadConferenceId() + "失败!");
		try {
			logger.info("to create conference to VCM......");
			conf.setRadConferenceId(sr.getConferenceId());
			conf.setVirtualConfId(sr.getConferenceInfo()
					.getDialableConferenceId());
			// confDao.saveOrUpdate(conf); //TODO: ERROR -
			// org.springframework.orm.hibernate3.HibernateSystemException: a
			// different object with the same identifier value was already
			// associated with the session:
			// [com.cma.intervideo.pojo.Conference#2]
			confDao.merge(conf);
		} catch (Exception e) {
			logger.error(e.toString());
			logger
					.info("if fail to save user to VCM, need to delete the user from iCM platform that was saved just now!!");
			Conference oldConf = getConfById(conf.getConferenceId().toString());
			List<Unit> oldUnits = findUnitsByConfId(conf.getConferenceId().toString(), true);
			List<String> oldListTerminalId = null;
			if (oldUnits != null && oldUnits.size() > 0) {
				for (int i = 0; i < oldUnits.size(); i++) {
					String partyId = oldUnits.get(i).getPartyId();
					if (partyId != null && partyId.length() > 0)
						listTerminalId.add(partyId);
				}
			}
			ICMService.modifyConference(oldConf, oldListTerminalId);
			throw new Exception("系统修改会议" + conf.getConferenceId() + "失败 !");
		}
	}

	public List<Unit> findAllUnits() {
		return confDao.findAllUnits();
	}
	
	public List<Unit> findUnitsByConfId(String confId, boolean selected) {
		return confDao.findUnitsByConfId(confId, selected);
	}
	
	private void updateUnitInfo(Conference conf) {
		List<Unit> listUnit = findUnitsByConfId(conf.getConferenceId().toString(), true);
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
	
	private void updateMainUnitInfo(Conference conf) {
		Unit unit = unitDao.getObjectByID(conf.getMainUnit());
		if (unit == null)
			return;
		conf.setMainUnitName(unit.getUnitName());
	}
	
	private void updateServiceTemplateInfo(Conference conf) {
		if (conf == null || conf.getServiceTemplate() == null || conf.getServiceTemplate().length() == 0)
			return;
		ServiceTemplate st = serviceDao.getServiceTemplate(conf.getServiceTemplate());
		if (st == null)
			return;
		conf.setServiceTemplateDesc(st.getServiceTemplateDesc());
		conf.setServiceTemplateName(st.getServiceTemplateName());
	}

	public Conference getConfById(String confId) {
		Conference conf = confDao.getObjectByID(new Integer(confId));
		updateAdditionalInfo(conf);
		return conf;
	}
	
	private void updateAdditionalInfo(Conference conf) {
		updateUnitInfo(conf);
		updateMainUnitInfo(conf);
		updateServiceTemplateInfo(conf);
	}
}
