package com.cma.intervideo.dao;

import com.cma.intervideo.dao.util.DAO;
import com.cma.intervideo.pojo.RecurringMeetingInfo;

public interface IRecurrenceDao extends DAO<RecurringMeetingInfo, Integer>{
	public void addRecurrenceUnit(Integer recurrenceId, Integer unitId);
}
