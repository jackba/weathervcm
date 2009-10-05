package com.cma.intervideo.dao;

import java.util.List;

import com.cma.intervideo.dao.util.DAO;
import com.cma.intervideo.pojo.VirtualRoom;
import com.cma.intervideo.util.PageHolder;
import com.cma.intervideo.util.ParamVo;

public interface IRoomDao extends DAO<VirtualRoom, String>{
	public List<VirtualRoom> findRooms(List<ParamVo> params, PageHolder ph);
	public List<VirtualRoom> findRooms(String userId);
}
