package com.cma.intervideo.service;

import java.util.List;

import com.cma.intervideo.radGateway.socket.BaseNotification;
import com.cma.intervideo.radGateway.socket.Conf;
import com.cma.intervideo.radGateway.socket.ConferenceCreatedNotification;
import com.cma.intervideo.radGateway.socket.ConferenceTerminatedNotification;
import com.cma.intervideo.radGateway.socket.NewParticipantNotification;
import com.cma.intervideo.radGateway.socket.ParticipantDeletedNotification;
import com.cma.intervideo.radGateway.socket.ParticipantDisconnectedNotification;

public interface NotificationHandleService {
	public int handle(BaseNotification notification);
	public int handle(ConferenceCreatedNotification notification);
	public int handle(ConferenceTerminatedNotification notification);
	public int handle(NewParticipantNotification notification);
	public int handle(ParticipantDisconnectedNotification notification);
	public int handle(ParticipantDeletedNotification notification);
}
