package com.cma.intervideo.service.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.cma.intervideo.dao.IConfDao;
import com.cma.intervideo.dao.util.ObjectDao;
import com.cma.intervideo.pojo.Conference;
import com.cma.intervideo.radGateway.socket.BaseNotification;
import com.cma.intervideo.radGateway.socket.ConferenceCreatedNotification;
import com.cma.intervideo.radGateway.socket.ConferenceExtendedNotification;
import com.cma.intervideo.radGateway.socket.ConferenceTerminatedNotification;
import com.cma.intervideo.radGateway.socket.DropParticipantRequest;
import com.cma.intervideo.radGateway.socket.DropParticipantResponse;
import com.cma.intervideo.radGateway.socket.NewParticipantNotification;
import com.cma.intervideo.radGateway.socket.ParticipantDeletedNotification;
import com.cma.intervideo.radGateway.socket.ParticipantDisconnectedNotification;
import com.cma.intervideo.radGateway.socket.SocketGateway;
import com.cma.intervideo.service.NotificationHandleService;

public class NotificationHandleServiceImpl implements NotificationHandleService{
	private static Log logger = LogFactory.getLog(NotificationHandleServiceImpl.class);
	private IConfDao confDao;
	
	public void setConfDao(IConfDao confDao) {
		this.confDao = confDao;
	}
	public int handle(BaseNotification notification){
		logger.info("begin to dispatch notification");
		return notification.handle(this);
	}
	public int handle(ConferenceCreatedNotification notification){
		logger.info("begin to handle conference_created_notification");
		Conference c = confDao.findConfByRadConfId(notification.getConfGID());
		if(c!=null){
			c.setStatus(c.status_ongoing);
			confDao.updateObject(c);
		}
		return 0;
	}
	public int handle(ConferenceTerminatedNotification notification){
		logger.info("begin to handle conference_terminated_notification");
		Conference c = confDao.findConfByRadConfId(notification.getConfGID());
		if(c!=null){
			c.setStatus(c.status_history);
			confDao.updateObject(c);
		}
		return 0;
	}
	public int handle(NewParticipantNotification notification){
		logger.info("begin to handle new_participant_notification");
		return 0;
	}
	public int handle(ParticipantDisconnectedNotification notification){
		logger.info("begin to handle participant_disconnected_notification");
		return 0;
	}
	public int handle(ConferenceExtendedNotification notification){
		logger.info("begin to handle conference_extended_notification");
		return 0;
	}
	public int handle(ParticipantDeletedNotification notification){
		logger.info("begin to handle participant_deleted_notification");
		return 0;
	}
	protected DropParticipantResponse dropParticipant(String confGID, String pid){
		DropParticipantRequest request = new DropParticipantRequest();
		request.setConfGID(confGID);
		request.setPid(pid);
		return (DropParticipantResponse)SocketGateway.getInstance().call(request);
	}
}
