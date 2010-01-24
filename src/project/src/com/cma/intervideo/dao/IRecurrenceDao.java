package com.cma.intervideo.dao;

import java.util.List;

import com.cma.intervideo.dao.util.DAO;
import com.cma.intervideo.pojo.RecurringMeetingInfo;
import com.cma.intervideo.pojo.Unit;
import com.cma.intervideo.util.PageHolder;
import com.cma.intervideo.util.ParamVo;

public interface IRecurrenceDao extends DAO<RecurringMeetingInfo, Integer>{
	public void addRecurrenceUnit(Integer recurrenceId, Integer unitId);
	public List<RecurringMeetingInfo> findRecurrences(List<ParamVo> params, PageHolder ph);
	public List<Unit> findUnitsByRecurrenceId(String recurrenceId, boolean selected);
}
