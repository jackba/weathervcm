package com.cma.intervideo.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.cma.intervideo.dao.ITerminalDao;
import com.cma.intervideo.pojo.Terminal;
import com.cma.intervideo.service.ITerminalService;
import com.cma.intervideo.util.PageHolder;
import com.cma.intervideo.util.ParamVo;
import com.radvision.icm.service.TerminalResource;
import com.radvision.icm.service.vcm.ICMService;

public class TerminalServiceImpl implements ITerminalService{
	
	private static Log logger = LogFactory.getLog(TerminalServiceImpl.class);
	
	private ITerminalDao terminalDao;
	
	public void setTerminalDao(ITerminalDao terminalDao) {
		this.terminalDao = terminalDao;
	}
	
	public List<Terminal> findTerminals() {
		return terminalDao.findTerminals();
	}
	
	public List<Terminal> findTerminals(List<ParamVo> params, PageHolder ph){
		return terminalDao.findTerminals(params, ph);
	}
	
	public void saveOrUpdate(Terminal terminal){
		terminalDao.saveOrUpdate(terminal);
	}

	public void deleteTerminalsByNewIds(List<String> newIds) {
		terminalDao.deleteTerminalsByNewIds(newIds);
	}

	public Terminal getTerminal(String terminalId) {
		return terminalDao.getTerminal(terminalId);
	}
	
	public void update() {
		logger.info("update...");
//		if (!RuntimeInfo.isIcmServiceConnected()) {
//			logger.warn("Cannot connect platform by web service, exit update!");
//			return;
//		}
		int count = 0;
		List<TerminalResource> trs = ICMService.getTerminals();
		List<String> newIds = new ArrayList<String>();
		for (int i = 0; trs != null && i < trs.size(); i++) {
			TerminalResource tr = trs.get(i);
			Terminal t = new Terminal();
			t.setTerminalId(tr.getTerminalId());
			t.setTerminalName(tr.getTerminalName());
			t.setTerminalNumber(tr.getTerminalNumber());
			t.setTerminalProtocol((short)tr.getTerminalProtocol());
			t.setTimeZoneId(t.getTimeZoneId());
			t.setZonePrefix(tr.getZonePrefix());
			t.setTerminalEmail(tr.getTerminalEmail());
			t.setStatusId(tr.getStatusId()==1? true : false);
			t.setRegisterGkid(tr.getRegisterGKId());
			t.setNodeId(tr.getNodeId());
			t.setMaxBandwidth(tr.getMaxBandwidth());
			t.setIsdnMaxBandwidth(tr.getIsdnMaxBandwidth());
			t.setIsVoiceOnly(tr.isIsVoiceOnly());
			t.setIp(tr.getIP());
			t.setE164(tr.getE164());
			t.setDetailProtocol((short)tr.getDetailProtocol());
			t.setDefaultRoomId(tr.getDefaultRoomId());
			t.setCountryCode(tr.getCountryCode());
			t.setAreaCode(tr.getAreaCode());
			this.saveOrUpdate(t);
			count++;
			newIds.add(tr.getTerminalId());
			logger.info("Terminal was downloaded from Platform and saved to VCM: " + t);
		}
		logger.info(count + " Terminals(s) were downloaded from Platform and saved to VCM!");
		this.deleteTerminalsByNewIds(newIds);
		logger.info("Deleted the terminal(s) that were not downloaded from Platform!");
	}
	
}
