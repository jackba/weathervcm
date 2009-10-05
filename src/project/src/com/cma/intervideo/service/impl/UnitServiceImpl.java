package com.cma.intervideo.service.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.cma.intervideo.dao.ITerminalDao;
import com.cma.intervideo.dao.IUnitDao;
import com.cma.intervideo.pojo.Terminal;
import com.cma.intervideo.pojo.Unit;
import com.cma.intervideo.service.IUnitService;
import com.cma.intervideo.util.PageHolder;
import com.cma.intervideo.util.ParamVo;

public class UnitServiceImpl implements IUnitService {
	private static Log logger = LogFactory.getLog(UnitServiceImpl.class);
	private IUnitDao unitDao;
	private ITerminalDao terminalDao;

	public void setUnitDao(IUnitDao unitDao) {
		this.unitDao = unitDao;
	}
	
	public void setTerminalDao(ITerminalDao terminalDao) {
		this.terminalDao = terminalDao;
	}

	public List<Unit> findAllUnits() {
		List<Unit> listUnit = unitDao.findAllUnits();
		for (int i = 0; i < listUnit.size(); i++)
			updatePartyInfo(listUnit.get(i));
		return listUnit;
	}
	
	private void updatePartyInfo(Unit unit) {
		if (unit == null || unit.getPartyId() == null || unit.getPartyId().length() == 0)
			return;
		Terminal t = terminalDao.getTerminal(unit.getPartyId());
		if (t == null)
			return;
		unit.setPartyName(t.getTerminalName());
	}

	public List<Unit> findUnits(List<ParamVo> params, PageHolder ph) {
		List<Unit> listUnit = unitDao.findUnits(params, ph);
		for (int i = 0; i < listUnit.size(); i++)
			updatePartyInfo(listUnit.get(i));
		return listUnit;
	}

	public Unit getUnitById(String unitId) {
		Unit unit = unitDao.getObjectByID(new Integer(unitId));
		updatePartyInfo(unit);
		return unit;
	}

	public void saveOrUpdate(Unit unit) throws Exception {
		try {
			logger.info("to save Unit to VCM......");
			unitDao.saveOrUpdate(unit);
		} catch (Exception e) {
			logger.info("Exception on saving Unit to VCM......");
			throw new Exception("系统保存终端" + unit.getUnitName() + " 失败!");
		}
	}

	public int deleteUnits(List<String> units) {
		int deleted = 0;
		for (int i = 0; i < units.size(); i++) {
			logger.info("to delete Unit from VCM - unitId: " + units.get(i));
			try {
				unitDao.removeObjectByID(new Integer(units.get(i)));
			} catch (Exception e) {
				logger.info("Exception on deleting Unit from VCM - unitId: "
						+ units.get(i) + " - " + e.getMessage());
				continue;
			}
			deleted++;
		}
		return deleted;
	}
}
