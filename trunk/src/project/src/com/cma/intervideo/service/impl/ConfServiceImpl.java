package com.cma.intervideo.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.directwebremoting.WebContext;
import org.directwebremoting.WebContextFactory;
import org.springframework.beans.BeanUtils;

import com.cma.intervideo.dao.IConfDao;
import com.cma.intervideo.dao.ILogDao;
import com.cma.intervideo.dao.IRecurrenceDao;
import com.cma.intervideo.dao.IServiceDao;
import com.cma.intervideo.dao.IUnitDao;
import com.cma.intervideo.pojo.Conference;
import com.cma.intervideo.pojo.FieldDesc;
import com.cma.intervideo.pojo.RecurringMeetingInfo;
import com.cma.intervideo.pojo.SendMessage;
import com.cma.intervideo.pojo.ServiceTemplate;
import com.cma.intervideo.pojo.Unit;
import com.cma.intervideo.service.IConfService;
import com.cma.intervideo.util.PageHolder;
import com.cma.intervideo.util.ParamVo;
import com.cma.intervideo.util.PropertiesHelper;
import com.cma.intervideo.util.UserPrivilege;
import com.radvision.icm.service.ConferenceInfo;
import com.radvision.icm.service.ConferenceInfoCondition;
import com.radvision.icm.service.ControlResult;
import com.radvision.icm.service.ScheduleResult;
import com.radvision.icm.service.vcm.ICMService;

public class ConfServiceImpl implements IConfService {
	private static final Log logger = LogFactory.getLog(ConfServiceImpl.class);
	private static final int maxConfPeriod = PropertiesHelper.getMaxConfPeroidHour();
	
	private IConfDao confDao;
	private IRecurrenceDao recurrenceDao;
	private IUnitDao unitDao;
	private ILogDao logDao;
	private IServiceDao serviceDao;
	
	public void setLogDao(ILogDao logDao) {
		this.logDao = logDao;
	}

	public void setConfDao(IConfDao confDao) {
		this.confDao = confDao;
	}
	
	public void setRecurrenceDao(IRecurrenceDao recurrenceDao) {
		this.recurrenceDao = recurrenceDao;
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
	
	public List<RecurringMeetingInfo> findRecurrences(List<ParamVo> params, PageHolder ph) {
		List<RecurringMeetingInfo> listRecurrence = recurrenceDao.findRecurrences(params, ph);
		if (listRecurrence != null) {
			int size = listRecurrence.size();
			for (int i = 0; i < size; i++)
				updateAdditionalInfo(listRecurrence.get(i));
		}
		return listRecurrence;
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
	
	public List<Unit> findUnitsByRecurrenceId(String recurrenceId, boolean selected){
		return recurrenceDao.findUnitsByRecurrenceId(recurrenceId, selected);
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
	
	private void updateUnitInfo(RecurringMeetingInfo recur) {
		List<Unit> listUnit = findUnitsByRecurrenceId(recur.getRecurrenceId().toString(), true);
		if (listUnit == null || listUnit.size() == 0)
			return;
		String names = "";
		int unitSize = listUnit.size();
		for (int j = 0; j < unitSize-1; j++) {
			names += listUnit.get(j).getUnitName() + ", ";
		}
		names += listUnit.get(unitSize-1).getUnitName();
		recur.setConfUnitNames(names);
	}
	
	private void updateMainUnitInfo(Conference conf) {
		Unit unit = unitDao.getObjectByID(conf.getMainUnit());
		if (unit == null)
			return;
		conf.setMainUnitName(unit.getUnitName());
	}
	
	private void updateMainUnitInfo(RecurringMeetingInfo recur){
		Unit unit = unitDao.getObjectByID(recur.getMainUnit());
		if (unit == null)
			return;
		recur.setMainUnitName(unit.getUnitName());
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
	
	private void updateServiceTemplateInfo(RecurringMeetingInfo recur) {
		if (recur == null || recur.getServiceTemplateId() == null || recur.getServiceTemplateId().length() == 0)
			return;
		ServiceTemplate st = serviceDao.getServiceTemplate(recur.getServiceTemplateId());
		if (st == null)
			return;
		recur.setServiceTemplateDesc(st.getServiceTemplateDesc());
		recur.setServiceTemplateName(st.getServiceTemplateName());
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
	
	private void updateConfTypeInfo(RecurringMeetingInfo recur){
		if(recur == null || recur.getConfType() == null){
			return;
		}
		FieldDesc fd = confDao.getConfType(recur.getConfType());
		if(fd == null){
			return;
		}
		recur.setConfTypeDesc(fd.getId().getFieldDesc());
	}

	public Conference getConfById(String confId) {
		Conference conf = confDao.getObjectByID(new Integer(confId));
		updateAdditionalInfo(conf);
		return conf;
	}
	
	public RecurringMeetingInfo getRecurrenceById(String recurrenceId){
		RecurringMeetingInfo recurrence = recurrenceDao.getObjectByID(new Integer(recurrenceId));
		updateAdditionalInfo(recurrence);
		return recurrence;
	}
	
	private void updateAdditionalInfo(Conference conf) {
		updateUnitInfo(conf);
		updateMainUnitInfo(conf);
		updateServiceTemplateInfo(conf);
		updateConfTypeInfo(conf);
	}
	
	private void updateAdditionalInfo(RecurringMeetingInfo recur){
		updateUnitInfo(recur);
		updateMainUnitInfo(recur);
		updateServiceTemplateInfo(recur);
		updateConfTypeInfo(recur);
	}
	
	/**
	 * 供定时器调用定时检查会议状态，对不正常的状态进行处理
	 */
	public void checkConfs(){
		logger.warn("Start to check conferences status..., current time is " + Calendar.getInstance().getTime().toString());
		
		Date currDate = Calendar.getInstance().getTime();
		
		//
		List<Conference> vcmConfList = confDao.findNotFinishedConfs();
		if (vcmConfList != null && vcmConfList.size() > 0) {
			try {
				Map<String, ConferenceInfo> radHm = new HashMap<String, ConferenceInfo>();
				
				// 从平台查询未召开的会议
				ConferenceInfoCondition condition = new ConferenceInfoCondition();
				condition.setStartTime(-1);
				condition.setEndTime(-1);
				condition.setConferenceStatus(Conference.rad_status_upcoming);
				List<ConferenceInfo> radConfUpcomingList = ICMService.searchConferences(condition);
				if (radConfUpcomingList != null && radConfUpcomingList.size() > 0) {
					for (ConferenceInfo ci : radConfUpcomingList) {
						radHm.put(ci.getConferenceId(), ci);
						logger.info("Upcoming meeting in platform: conferenceId=" + ci.getConferenceId() + "; subject=" + ci.getSubject());
					}
				}
				
				// 从平台查询正在召开的会议
				condition.setConferenceStatus(Conference.rad_status_insession);
				List<ConferenceInfo> radConfInsessionList = ICMService.searchConferences(condition);
				if (radConfInsessionList != null && radConfInsessionList.size() > 0) {
					for (ConferenceInfo ci : radConfInsessionList) {
						radHm.put(ci.getConferenceId(), ci);
						logger.info("Insession meeting in platform: conferenceId=" + ci.getConferenceId() + "; subject=" + ci.getSubject());
					}
				}
				
				for (Conference conf : vcmConfList) {
					logger.info("Start to check conference in vcm: conferenceId=" + conf.getConferenceId() + 
							"; radConferenceId=" + conf.getRadConferenceId() + "; status=" + conf.getStatus());
					if (conf.getStatus() == Conference.status_tobescheduled)
						continue;
					
					String radConfId = conf.getRadConferenceId();
					ConferenceInfo ci = radHm.get(radConfId);
					if (ci == null) {
						conf.setStatus(Conference.status_history);
						conf.setUpdateTime(currDate);
						confDao.updateObject(conf);
						logger.warn("the conference cannot be found in platform - confId:" + conf.getConferenceId() + 
								"; rad confId:" + conf.getRadConferenceId() + " was changed to history status.");
					} else {
						Short vcmStatus = conf.getStatus();
						int radStatus = ci.getConferenceStatus();
						if (vcmStatus == Conference.status_upcoming) {
							if (radStatus == Conference.rad_status_insession) {
								conf.setStatus(Conference.status_ongoing);
								conf.setUpdateTime(currDate);
								confDao.updateObject(conf);
								logger.warn("the conference status is not started in VCM, but is in session in platform - confId:" + conf.getConferenceId() + 
										"; rad confId:" + conf.getRadConferenceId() + " was changed to on-going.");
							}
						} else if (vcmStatus == Conference.status_ongoing) {
							if (radStatus == Conference.rad_status_upcoming) {
								// impossible
							}
						}
					}
				}
			} catch (Exception e) {
				logger.warn("Exception on check the conferences between vcm and platform: " + e.getMessage());
				e.printStackTrace();
			}
		}
		
		// 查询直到预约的会议结束时间仍然未收到会议开始和结束消息的会议
		List<Conference> abnormalConfs = confDao.findAbnormalConfs();
		logger.warn(abnormalConfs.size()+" conferences with abnormal status!!");
		for(int i=0;i<abnormalConfs.size();i++){
			Conference c = abnormalConfs.get(i);
			c.setStatus(Conference.status_history);
			c.setUpdateTime(currDate);
			confDao.updateObject(c);
			// call platform API to terminate conference
			ControlResult r = ICMService.terminateLiveConference(c.getRadConferenceId());
			boolean b = r!=null && r.isSuccess();
			logger.warn("the innormal conference - confId:" + c.getConferenceId() + 
					"; rad confId:" + c.getRadConferenceId() + 
					" was changed to history status, and try to be terminated:" + b);
		}
		
		// 查询超长的会议
		List<Conference> tooLongConfs = confDao.findTooLongConf(maxConfPeriod);
		logger.warn(tooLongConfs.size()+" conferences duration is too long!!");
		for(int i=0;i<tooLongConfs.size();i++){
			Conference c = tooLongConfs.get(i);
			c.setStatus(Conference.status_history);
			c.setUpdateTime(currDate);
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
	
	public List<Conference> findNotFinishedConfs() {
		return confDao.findNotFinishedConfs();
	}

	public void cancelRecurrence(String[] ids) throws Exception {
		if (ids == null || ids.length == 0)
			return;
		
		for (String id : ids) {
			String[] tmp = id.split(",");
			if (tmp == null || tmp.length <= 1)
				continue;
			
			//delete recurrence_conf from vcm
			int recurrenceId = Integer.parseInt(tmp[0]);
			confDao.deleteRecurrConf(recurrenceId);
			
			//delete conferences from vcm
			recurrenceDao.deleteConferenceByRecurrenceId(recurrenceId);
			
			//delete recurrence from vcm
			RecurringMeetingInfo recurrence = recurrenceDao.getObjectByID(recurrenceId);
			recurrence.setStatus(Conference.status_cancel);
			recurrenceDao.saveOrUpdate(recurrence);
		
			// delete recurrence from platform
			ICMService.cancelRecurrence(tmp[1]);

		}
	}

	public void createRecurrence(RecurringMeetingInfo recurrence, String[] units)
			throws Exception {
		try {
			logger.info("Before creating a new recurrence: " + recurrence);
			recurrence.setRecurrenceId(null);
			List<String> listTerminalId = getTerminalIdList(units);
			List<ScheduleResult> srs = ICMService.createRecurrence(recurrence, listTerminalId);
			if (srs == null || srs.size() == 0) {
				logger.warn("Platform failed to schedule this new meeting!");
				throw new Exception("平台创建例会" + recurrence.getSubject() + " 失败!");
			}
			
			List<Integer> unitIds = new ArrayList<Integer>();
			if (units != null) {
				for (int i = 0; i < units.length; i++)
					unitIds.add(Integer.valueOf(units[i]));
			}
			List<String> terminalIds = new ArrayList<String>();
			if (listTerminalId != null){
				for (int i = 0; i < listTerminalId.size(); i++)
					terminalIds.add(listTerminalId.get(i));
			}
			List<Integer> newConfIds = new ArrayList<Integer>();
			for (ScheduleResult sr : srs) {
				if (!sr.isSuccess() || sr.getConferenceInfo() == null)
					continue;
				
				ConferenceInfo info = sr.getConferenceInfo();
				Conference newConf = convert(recurrence);
				newConf.setStartTime(info.getStartTime());
				newConf.setConferenceId(null);
				newConf.setRadConferenceId(info.getConferenceId());
				newConf.setVirtualConfId(info.getDialableConferenceId());
				
				logger.info("Creating a new conference to VCM...");
				confDao.saveOrUpdate(newConf);
				for (Integer unitId : unitIds)
					confDao.addConfUnit(newConf.getConferenceId(), unitId);
				
				for (String terminalId : terminalIds)
					confDao.addConfParty(newConf.getConferenceId(), terminalId);
				
				newConfIds.add(newConf.getConferenceId());
				
				if (recurrence.getRadRecurrenceId() == null)
					recurrence.setRadRecurrenceId(info.getRecurrenceId());
				
				logger.info("Created successfully a new meeting in VCM.");
			}
			
			if (newConfIds.size() > 0) {
				recurrenceDao.saveOrUpdate(recurrence);
				for (Integer unitId : unitIds) {
					recurrenceDao.addRecurrenceUnit(recurrence.getRecurrenceId(), unitId);
				}
				
				for (Integer newConfId : newConfIds) {
					confDao.addRecurrConf(recurrence.getRecurrenceId(), newConfId);
				}
			}
			
			logger.info("Created successfully a new recurrence in VCM.");
		} catch (Exception e) {
			logger.info("Failed to create a new recurrence in VCM: ");
			e.printStackTrace();
			throw e;
		}
	}
	
	private Conference convert(RecurringMeetingInfo recurrence) {
		Conference conf = new Conference();
		BeanUtils.copyProperties(recurrence, conf);
		return conf;
	}

	public void modifyRecurrence(RecurringMeetingInfo recurrence, String[] units)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

	/**
	 * 保存短信发送日志
	 * @param msg
	 */
	public void saveSmsLog(SendMessage msg){
		confDao.saveSmsLog(msg);
	}
}
