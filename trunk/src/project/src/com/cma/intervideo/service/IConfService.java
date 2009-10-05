package com.cma.intervideo.service;

import java.util.List;

import com.cma.intervideo.pojo.Conference;
import com.cma.intervideo.pojo.Unit;
import com.cma.intervideo.pojo.VirtualRoom;
import com.cma.intervideo.util.PageHolder;
import com.cma.intervideo.util.ParamVo;

public interface IConfService {
	public List<Conference> findConfs(List<ParamVo> params, PageHolder ph);
	public void createConf(Conference conf, String[] units) throws Exception;
	public void modifyConf(Conference conf, String[] units) throws Exception;
	public Conference getConfById(String confId);
	public List<Unit> findUnitsByConfId(String confId, boolean selected);
	public List<Unit> findAllUnits();
	public VirtualRoom findVirtualRoomByRoomId(String roomId);
}
