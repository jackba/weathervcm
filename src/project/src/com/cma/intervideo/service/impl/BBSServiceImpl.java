package com.cma.intervideo.service.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.cma.intervideo.constant.DataDictStatus;
import com.cma.intervideo.dao.IBBSDao;
import com.cma.intervideo.pojo.MessageBoard;
import com.cma.intervideo.service.IBBSService;
import com.cma.intervideo.util.PageHolder;
import com.cma.intervideo.util.ParamVo;

public class BBSServiceImpl implements IBBSService{
	private static Log logger = LogFactory.getLog(BBSServiceImpl.class);
	private IBBSDao bbsDao;
	public void setBbsDao(IBBSDao bbsDao) {
		this.bbsDao = bbsDao;
	}
	public void save(MessageBoard messageBoard){
		bbsDao.saveOrUpdate(messageBoard);
	}
	public List<MessageBoard> findAllMessages(){
		return bbsDao.findAllMessages();
	}
	public List<MessageBoard> findMessagesByUserId(String userId){
		return bbsDao.findMessagesByUserId(userId);
	}
	public MessageBoard getMessageById(int messageId){
		return bbsDao.getObjectByID(messageId);
	}
	public List<MessageBoard> findMessages(List<ParamVo> params, PageHolder ph){
		return bbsDao.findMessages(params, ph);
	}
}
