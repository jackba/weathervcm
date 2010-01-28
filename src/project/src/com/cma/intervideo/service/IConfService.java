package com.cma.intervideo.service;

import java.util.List;

import com.cma.intervideo.pojo.Conference;
import com.cma.intervideo.pojo.FieldDesc;
import com.cma.intervideo.pojo.RecurringMeetingInfo;
import com.cma.intervideo.pojo.SendMessage;
import com.cma.intervideo.pojo.Unit;
import com.cma.intervideo.util.PageHolder;
import com.cma.intervideo.util.ParamVo;

public interface IConfService {
	public List<Conference> findConfs(List<ParamVo> params, PageHolder ph);
	public List<RecurringMeetingInfo> findRecurrences(List<ParamVo> params, PageHolder ph);
	public void createConf(Conference conf, String[] units) throws Exception;
	public void modifyConf(Conference conf, String[] units) throws Exception;
	public Conference getConfById(String confId);
	public List<Unit> findUnitsByConfId(String confId, boolean selected);
	public List<Unit> findAllUnits();
	public List<Conference> findNotFinishedConfs();
	
	public void createRecurrence(RecurringMeetingInfo recurrence, String[] units) throws Exception;
	public void modifyRecurrence(RecurringMeetingInfo recurrence, String[] units) throws Exception;
	public void cancelRecurrence(String recurrenceId) throws Exception;
	
	/**
	 * 查找会议类型
	 * @return
	 */
	public List<FieldDesc> findConfTypes();
	
	/**
	 * 保存短信发送日志
	 * @param msg
	 */
	public void saveSmsLog(SendMessage msg);
}
