package com.cma.intervideo.service.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.cma.intervideo.dao.IRoomDao;
import com.cma.intervideo.pojo.VirtualRoom;
import com.cma.intervideo.service.IRoomService;
import com.cma.intervideo.util.PageHolder;
import com.cma.intervideo.util.ParamVo;

public class RoomServiceImpl implements IRoomService{
	private final static Log logger = LogFactory.getLog(RoomServiceImpl.class);
	private IRoomDao roomDao;
	public void setRoomDao(IRoomDao roomDao) {
		this.roomDao = roomDao;
	}
	public List<VirtualRoom> findRooms(List<ParamVo> params, PageHolder ph){
		return roomDao.findRooms(params, ph);
	}
	public int deleteRooms(List<String> rooms){
		for(int i=0;i<rooms.size();i++){
			roomDao.removeObjectByID(rooms.get(i));
		}
		return rooms.size();
	}
	public void saveOrUpdate(VirtualRoom room){
		roomDao.saveOrUpdate(room);
	}
	public VirtualRoom getRoomById(String roomId){
		return roomDao.getObjectByID(roomId);
	}
}
