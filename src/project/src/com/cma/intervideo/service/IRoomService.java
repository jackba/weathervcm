package com.cma.intervideo.service;

import java.util.List;

import com.cma.intervideo.pojo.VirtualRoom;
import com.cma.intervideo.util.PageHolder;
import com.cma.intervideo.util.ParamVo;

public interface IRoomService {
	public List<VirtualRoom> findRooms(List<ParamVo> params, PageHolder ph);
	public void saveOrUpdate(VirtualRoom room) throws Exception;
	public VirtualRoom getRoomById(String roomId);
}
