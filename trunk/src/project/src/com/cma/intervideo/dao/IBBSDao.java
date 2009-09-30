package com.cma.intervideo.dao;

import java.util.List;

import com.cma.intervideo.constant.DataDictStatus;
import com.cma.intervideo.dao.util.DAO;
import com.cma.intervideo.pojo.MessageBoard;
import com.cma.intervideo.util.PageHolder;
import com.cma.intervideo.util.ParamVo;

public interface IBBSDao extends DAO<MessageBoard, Integer>{
	public List<MessageBoard> findAllMessages();
	public List<MessageBoard> findMessagesByUserId(String userId);
	public List<MessageBoard> findMessages(List<ParamVo> params, PageHolder ph);
}
