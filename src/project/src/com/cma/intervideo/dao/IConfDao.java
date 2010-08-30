package com.cma.intervideo.dao;

import java.util.List;

import com.cma.intervideo.dao.util.DAO;
import com.cma.intervideo.pojo.Conference;
import com.cma.intervideo.pojo.FieldDesc;
import com.cma.intervideo.pojo.SendMessage;
import com.cma.intervideo.pojo.Unit;
import com.cma.intervideo.util.PageHolder;
import com.cma.intervideo.util.ParamVo;

public interface IConfDao extends DAO<Conference, Integer>{
	public List<Conference> findConfs(List<ParamVo> params, PageHolder ph);
	public void merge(Conference conf);
	public void addConfUnit(Integer confId, Integer unitId);
	public List<Unit> findUnitsByConfId(String confId, boolean selected, String userId);
	public List<Unit> findAllUnits();
	public void deleteConfUnitsByConfId(Integer confId);
	public void addConfParty(Integer confId, String partyId);
	public void deleteConfPartiesByConfId(Integer confId);
	public Conference findConfByRadConfId(String radConferenceId);
	public List<Conference> findNotFinishedConfs();
	public void addRecurrConf(Integer recurrId, Integer confId);
	public void deleteRecurrConf(Integer recurrId, Integer confId);
	public void deleteRecurrConf(Integer recurrId);
	
	/**
	 * 查询直到预约的会议结束时间仍然未收到会议开始和结束消息的会议
	 * @return
	 */
	public List<Conference> findAbnormalConfs();
	/**
	 * 查询超长的会议
	 * @param maxConfPeriod
	 * @return
	 */
	public List<Conference> findTooLongConf(int maxConfPeriod);
	/**
	 * 查找会议类型
	 * @return
	 */
	public List<FieldDesc> findConfTypes();
	
	public FieldDesc getConfType(short fieldValue);
	
	/**
	 * 保存短信发送日志
	 * @param msg
	 */
	public void saveSmsLog(SendMessage msg);
}
