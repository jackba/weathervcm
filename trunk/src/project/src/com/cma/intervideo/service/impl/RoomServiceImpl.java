package com.cma.intervideo.service.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.cma.intervideo.dao.IRoomDao;
import com.cma.intervideo.dao.IServiceDao;
import com.cma.intervideo.pojo.ServiceTemplate;
import com.cma.intervideo.pojo.VirtualRoom;
import com.cma.intervideo.service.IRoomService;
import com.cma.intervideo.util.PageHolder;
import com.cma.intervideo.util.ParamVo;
import com.radvision.icm.service.VirtualRoomResult;
import com.radvision.icm.service.vcm.ICMService;

public class RoomServiceImpl implements IRoomService {
	private final static Log logger = LogFactory.getLog(RoomServiceImpl.class);
	private IRoomDao roomDao;
	private IServiceDao serviceDao;

	public void setRoomDao(IRoomDao roomDao) {
		this.roomDao = roomDao;
	}
	
	public void setServiceDao(IServiceDao serviceDao) {
		this.serviceDao = serviceDao;
	}

	public List<VirtualRoom> findRooms(List<ParamVo> params, PageHolder ph) {
		List<VirtualRoom> listRoom = roomDao.findRooms(params, ph);
		for (int i = 0; i < listRoom.size(); i++)
			updateServiceTemplateInfo(listRoom.get(i));
		return listRoom;
	}
	
	public List<VirtualRoom> findRooms(String userId) {
		List<VirtualRoom> listRoom = roomDao.findRooms(userId);
		for (int i = 0; i < listRoom.size(); i++)
			updateServiceTemplateInfo(listRoom.get(i));
		return listRoom;
	}
	
	private void updateServiceTemplateInfo(VirtualRoom room) {
		if (room == null || room.getServiceTemplate() == null || room.getServiceTemplate().length() == 0)
			return;
		ServiceTemplate st = serviceDao.getServiceTemplate(room.getServiceTemplate());
		if (st == null)
			return;
		room.setServiceTemplateDesc(st.getServiceTemplateDesc());
		room.setServiceTemplateName(st.getServiceTemplateName());
	}

	public int deleteRooms(List<String> rooms) {
		int deleted = 0;
		for (int i = 0; i < rooms.size(); i++) {
			String tmp = rooms.get(i);
			String[] ids = tmp.split(",");
			if (ids == null)
				continue;
			String roomId, virConfId;
			if (ids.length == 1) {
				roomId = ids[0];
				virConfId = ids[0];
			} else {
				roomId = ids[0];
				virConfId = ids[1];
			}
			logger.info("to delete Virtual Room from VCM - roomId: " + roomId);
			try {
				roomDao.removeObjectByID(roomId);
			} catch (Exception e) {
				logger
						.info("Exception on deleting Virtual Room from VCM - roomId: "
								+ roomId + " - " + e.getMessage());
				continue;
			}

			logger
					.info("to delete Virtual Room from iCM platform - virtualConfId: "
							+ virConfId);
			ICMService.deleteVirtualRoom(virConfId);
			deleted++;
		}
		return deleted;
	}

	public void saveOrUpdate(VirtualRoom room) throws Exception {
		if (room.getRoomId() == null || room.getRoomId().length() == 0) {
			// save Virtual Room to ICM
			logger.info("to create Virtual Room to iCM platform......");
			VirtualRoomResult vrr = ICMService.createVirtualRoom(room);
			if (vrr == null || !vrr.isSuccess())
				throw new Exception("平台新建虚拟房间" + room.getVitualConfId()
						+ " 失败!");
			room.setRoomId(vrr.getVirtualRoomID());
		} else {
			// modify Virtual Room to ICM
			logger.info("to modify Virtual Room to iCM platform......");
			VirtualRoomResult vrr = ICMService.modifyVirtualRoom(room);
			if (vrr == null || !vrr.isSuccess())
				throw new Exception("平台修改虚拟房间" + room.getVitualConfId()
						+ " 失败!");
		}
		try {
			// save Virtual Room to VCM
			logger.info("to save Virtual Room to VCM......");
			roomDao.saveOrUpdate(room);
		} catch (Exception e) {
			logger.info("Exception on saving Virtual Room to VCM......");
			throw new Exception("系统保存虚拟房间" + room.getVitualConfId() + " 失败!");
		}
	}

	public VirtualRoom getRoomById(String roomId) {
		VirtualRoom room = roomDao.getObjectByID(roomId);
		updateServiceTemplateInfo(room);
		return room;
	}
}
