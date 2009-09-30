package com.cma.intervideo.service;

import java.util.List;

import com.cma.intervideo.pojo.MessageBoard;
import com.cma.intervideo.util.PageHolder;
import com.cma.intervideo.util.ParamVo;

public interface IBBSService {
	public void save(MessageBoard messageBoard);
	public List<MessageBoard> findAllMessages();
	public List<MessageBoard> findMessagesByUserId(String userId);
	public MessageBoard getMessageById(int messageId);
	public List<MessageBoard> findMessages(List<ParamVo> params, PageHolder ph);
}
