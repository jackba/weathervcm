package com.cma.intervideo.service.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.cma.intervideo.dao.IConfDao;
import com.cma.intervideo.pojo.Conference;
import com.cma.intervideo.service.IConfService;
import com.cma.intervideo.util.PageHolder;
import com.cma.intervideo.util.ParamVo;
import com.radvision.icm.service.ScheduleResult;
import com.radvision.icm.service.vcm.ICMService;

public class ConfServiceImpl implements IConfService {
	private static final Log logger = LogFactory.getLog(ConfServiceImpl.class);
	private IConfDao confDao;

	public void setConfDao(IConfDao confDao) {
		this.confDao = confDao;
	}

	public int deleteReserves(List<String> reserves) {
		int deleted = 0;
		for (int i = 0; i < reserves.size(); i++) {
			String tmp = reserves.get(i);
			String[] ids = tmp.split(",");
			if (ids == null)
				continue;
			String confId, radConfId;
			if (ids.length == 1) {
				confId = ids[0];
				radConfId = ids[0];
			} else {
				confId = ids[0];
				radConfId = ids[1];
			}
			logger.info("to delete Conference from VCM - conferenceId: "
					+ confId);
			try {
				confDao.removeObjectByID(new Integer(confId));
			} catch (Exception e) {
				logger
						.info("Exception on deleting Conference from VCM - conferenceId: "
								+ confId + " - " + e.getMessage());
				continue;
			}

			logger
					.info("to delete Conference from iCM platform - radConfId: "
							+ radConfId);
			ICMService.cancelConference(radConfId);
			deleted++;
		}
		return deleted;
	}

	public List<Conference> findConfs(List<ParamVo> params, PageHolder ph) {
		return confDao.findConfs(params, ph);
	}

	public Conference getConfById(String confId) {
		return confDao.getObjectByID(new Integer(confId));
	}

	public void createConf(Conference conf) throws Exception {
		logger
				.info("to create conference to iCM platform...... - conference subject: "
						+ conf.getSubject());
		conf.setRadConferenceId(null);
		ScheduleResult sr = ICMService.createConference(conf);
		if (sr == null || !sr.isSuccess())
			throw new Exception("平台预约会议" + conf.getSubject() + " 失败!");
		try {
			logger.info("to create conference to VCM......");
			conf.setRadConferenceId(sr.getConferenceId());
			conf.setVirtualConfId(sr.getConferenceInfo()
					.getDialableConferenceId());
			confDao.saveOrUpdate(conf);
		} catch (Exception e) {
			logger.error(e.toString());
			logger
					.info("if fail to save user to VCM, need to delete the user from iCM platform that was saved just now!!");
			ICMService.cancelConference(sr.getConferenceId());
			throw new Exception("系统保存会议失败 - conference subject: "
					+ conf.getSubject());
		}
	}
	
	public void modifyConf(Conference conf) throws Exception {
		logger
				.info("to modify conference to iCM platform...... - rad conference id: "
						+ conf.getRadConferenceId());
		ScheduleResult sr = ICMService.modifyConference(conf);
		if (sr == null || !sr.isSuccess())
			throw new Exception("平台修改会议" + conf.getRadConferenceId() + "失败!");
		try {
			logger.info("to create conference to VCM......");
			conf.setRadConferenceId(sr.getConferenceId());
			conf.setVirtualConfId(sr.getConferenceInfo()
					.getDialableConferenceId());
//			confDao.saveOrUpdate(conf); 	//TODO: ERROR - org.springframework.orm.hibernate3.HibernateSystemException: a different object with the same identifier value was already associated with the session: [com.cma.intervideo.pojo.Conference#2]
			confDao.merge(conf);
		} catch (Exception e) {
			logger.error(e.toString());
			logger
					.info("if fail to save user to VCM, need to delete the user from iCM platform that was saved just now!!");
			Conference oldConf = getConfById(conf.getConferenceId().toString());
			ICMService.modifyConference(oldConf);
			throw new Exception("系统修改会议" + conf.getConferenceId() + "失败 !");
		}
	}
}
