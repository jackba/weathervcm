package com.cma.intervideo.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.directwebremoting.WebContext;
import org.directwebremoting.WebContextFactory;

import com.cma.intervideo.dao.IConfDao;
import com.cma.intervideo.dao.ILogDao;
import com.cma.intervideo.dao.IServiceDao;
import com.cma.intervideo.dao.IUnitDao;
import com.cma.intervideo.pojo.Conference;
import com.cma.intervideo.pojo.FieldDesc;
import com.cma.intervideo.pojo.ServiceTemplate;
import com.cma.intervideo.pojo.Unit;
import com.cma.intervideo.service.IConfService;
import com.cma.intervideo.util.PageHolder;
import com.cma.intervideo.util.ParamVo;
import com.cma.intervideo.util.PropertiesHelper;
import com.cma.intervideo.util.UserPrivilege;
import com.radvision.icm.service.ControlResult;
import com.radvision.icm.service.ScheduleResult;
import com.radvision.icm.service.vcm.ICMService;

public class ConfServiceImpl implements IConfService {
	private static final Log logger = LogFactory.getLog(ConfServiceImpl.class);
	private IConfDao confDao;
	private IUnitDao unitDao;
	private ILogDao logDao;
	private IServiceDao serviceDao;
	
	public void setLogDao(ILogDao logDao) {
		this.logDao = logDao;
	}

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
		WebContext ctx = WebContextFactory.get();
		HttpSession s = ctx.getSession();
		UserPrivilege up = (UserPrivilege)s.getAttribute("userPrivilege");
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
		logger.info("Deleting the Conference from VCM: conferenceId = " + confId);
		try {
			Conference conf = confDao.getObjectByID(Integer.parseInt(confId));
			//conf.setStatus(Conference.status_history);
			conf.setStatus(Conference.status_cancel);
			confDao.deleteConfUnitsByConfId(Integer.parseInt(confId));
			confDao.saveOrUpdate(conf);
			logDao.addLog(up.getUserId(), ILogDao.type_delete_conf, "删除会议"+conf.getRadConferenceId());
		} catch (Exception e) {
			logger.info("Exception on deleting the conference from VCM: conferenceId = " + confId + " - " + e.getMessage());
			return;
		}

		logger.info("Deleting the Conference from platform: radConfId = " + radConfId);
		ICMService.cancelConference(radConfId);
		
		logger.info("Finished delete the conference: " + reserve + "!");
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
		logger.info("Before Scheduling a new meeting: " + conf);
		conf.setRadConferenceId(null);
		List<String> listTerminalId = getTerminalIdList(units);
		ScheduleResult sr = ICMService.createConference(conf, listTerminalId);
		if (sr == null || !sr.isSuccess()) 
		{
			logger.warn("Platform failed to schedule this new meeting!");
			throw new Exception("平台预约会议" + conf.getSubject() + " 失败!");
		}
		try {
			conf.setRadConferenceId(sr.getConferenceId());
			conf.setVirtualConfId(sr.getConferenceInfo().getDialableConferenceId());
			logger.info("Scheduled successfully a new meeting to platform: " + conf);
			
			logger.info("Creating a new conference to VCM...");
			confDao.saveOrUpdate(conf);
			if (units != null) {
				for (int i = 0; i < units.length; i++)
					confDao.addConfUnit(conf.getConferenceId(), new Integer(units[i]));
			}
			if(listTerminalId!=null){
				for(int i=0;i<listTerminalId.size();i++)
					confDao.addConfParty(conf.getConferenceId(), listTerminalId.get(i));
			}
			logger.info("Created successfully a new meeting in VCM.");
		} catch (Exception e) {
			logger.warn("ConfServiceImpl::createConf Excepton - " + e.toString());
			logger.info("Excepton on save user in VCM, So need to roll back to delete this conference from platform that was scheduled just now!!");
			ICMService.cancelConference(sr.getConferenceId());
			logger.warn("VCM failed to save the conference - " + conf.getSubject());
			throw new Exception("系统保存会议失败 - conference subject: " + conf.getSubject());
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
		logger.info("Before modifying the conference..." + conf);
		List<String> listTerminalId = getTerminalIdList(units);
		ScheduleResult sr = ICMService.modifyConference(conf, listTerminalId);
		if (sr == null || !sr.isSuccess())
		{
			logger.warn("Platform failed to modify this conference!");
			throw new Exception("平台修改会议" + conf.getRadConferenceId() + "失败!");
		}
		try {
			conf.setRadConferenceId(sr.getConferenceId());
			conf.setVirtualConfId(sr.getConferenceInfo().getDialableConferenceId());
			logger.info("Modified successfully the conference to platform: " + conf);
			
			logger.info("Modifying this conference in VCM...");
			confDao.saveOrUpdate(conf);
			confDao.deleteConfPartiesByConfId(conf.getConferenceId());
			logger.info("Deleted Parties for this conference.");
			confDao.deleteConfUnitsByConfId(conf.getConferenceId());
			logger.info("Deleted Units for this conference.");
			if (units != null) {
				for (int i = 0; i < units.length; i++)
					confDao.addConfUnit(conf.getConferenceId(), new Integer(units[i]));
			}
			if(listTerminalId!=null){
				for(int i=0;i<listTerminalId.size();i++)
					confDao.addConfParty(conf.getConferenceId(), listTerminalId.get(i));
			}
			logger.info("Modified successfully a new meeting to VCM.");
		} catch (Exception e) {
			logger.warn("ConfServiceImpl::modifyConf Excepton - " + e.toString());
			logger.info("if fail to save user in VCM, need to delete the user from iCM platform that was saved just now!!");
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
			logger.warn("VCM failed to modify the conference - " + conf.getSubject());
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
		if (conf == null || conf.getServiceTemplateId() == null || conf.getServiceTemplateId().length() == 0)
			return;
		ServiceTemplate st = serviceDao.getServiceTemplate(conf.getServiceTemplateId());
		if (st == null)
			return;
		conf.setServiceTemplateDesc(st.getServiceTemplateDesc());
		conf.setServiceTemplateName(st.getServiceTemplateName());
	}
	
	private void updateConfTypeInfo(Conference conf){
		if(conf == null || conf.getConfType() == null){
			return;
		}
		FieldDesc fd = confDao.getConfType(conf.getConfType());
		if(fd == null){
			return;
		}
		conf.setConfTypeDesc(fd.getId().getFieldDesc());
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
		updateConfTypeInfo(conf);
	}
	/**
	 * 供定时器调用定时检查会议状态，对不正常的状态进行处理
	 */
	public void checkConfs(){
		logger.warn("Start to check conferences status..., current time is " + Calendar.getInstance().getTime().toString());
		int maxConfPeriod = PropertiesHelper.getMaxConfPeroidHour();
		List<Conference> abnormalConfs = confDao.findAbnormalConfs();
		logger.warn(abnormalConfs.size()+" conferences with abnormal status!!");
		for(int i=0;i<abnormalConfs.size();i++){
			Conference c = abnormalConfs.get(i);
			c.setStatus(Conference.status_history);
			c.setUpdateTime(Calendar.getInstance().getTime());
			confDao.updateObject(c);
			// call platform API to terminate conference
			ControlResult r = ICMService.terminateLiveConference(c.getRadConferenceId());
			boolean b = r!=null && r.isSuccess();
			logger.warn("the innormal conference - confId:" + c.getConferenceId() + 
					"; rad confId:" + c.getRadConferenceId() + 
					" was changed to history status, and try to be terminated:" + b);
		}
		List<Conference> tooLongConfs = confDao.findTooLongConf(maxConfPeriod);
		logger.warn(tooLongConfs.size()+" conferences duration is too long!!");
		for(int i=0;i<tooLongConfs.size();i++){
			Conference c = tooLongConfs.get(i);
			c.setStatus(Conference.status_history);
			c.setUpdateTime(Calendar.getInstance().getTime());
			confDao.updateObject(c);
			// call platform API to terminate conference
			ControlResult r = ICMService.terminateLiveConference(c.getRadConferenceId());
			boolean b = r!=null && r.isSuccess();
			logger.warn("the conference with too long duration - confId:" + c.getConferenceId() + 
					"; rad confId:" + c.getRadConferenceId() + 
					" was changed to history status, and try to be terminated:" + b);
		}
	}
	/**
	 * 查找会议类型
	 * @return
	 */
	public List<FieldDesc> findConfTypes(){
		return confDao.findConfTypes();
	}
}
