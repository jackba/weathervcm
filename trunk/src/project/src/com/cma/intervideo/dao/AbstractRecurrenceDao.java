package com.cma.intervideo.dao;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.cma.intervideo.dao.util.AbstractDAO;
import com.cma.intervideo.pojo.RecurrenceUnit;
import com.cma.intervideo.pojo.RecurringMeetingInfo;

public abstract class AbstractRecurrenceDao extends AbstractDAO<RecurringMeetingInfo, Integer> implements IRecurrenceDao {
	
	private static Log logger = LogFactory.getLog(AbstractRecurrenceDao.class);
	
	public void addRecurrenceUnit(Integer recurrenceId, Integer unitId) {
		RecurrenceUnit recurrUnit = new RecurrenceUnit();
		recurrUnit.setRecurrenceId(recurrenceId);
		recurrUnit.setUnitId(unitId);
		this.getHibernateTemplate().save(recurrUnit);
		logger.info("Added successfully Unit: unitId = " + unitId + " for the recurrence: recurrenceId = " + recurrenceId + ".");
	}
	
}
