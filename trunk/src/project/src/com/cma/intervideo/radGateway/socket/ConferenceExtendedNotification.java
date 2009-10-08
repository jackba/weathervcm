package com.cma.intervideo.radGateway.socket;

import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.cma.intervideo.service.NotificationHandleService;

public class ConferenceExtendedNotification extends BaseNotification{
	private static Log logger = LogFactory.getLog(ConferenceExtendedNotification.class);
	private Date newEndTime;
	private long newUniversalEndTime;
	/**
	 * 扩展的时间，以毫秒为单位
	 */
	private int extendedTime;
	
	public int getExtendedTime() {
		return extendedTime;
	}

	public void setExtendedTime(int extendedTime) {
		this.extendedTime = extendedTime;
	}

	public Date getNewEndTime() {
		return newEndTime;
	}

	public void setNewEndTime(Date newEndTime) {
		this.newEndTime = newEndTime;
	}

	public long getNewUniversalEndTime() {
		return newUniversalEndTime;
	}

	public void setNewUniversalEndTime(long newUniversalEndTime) {
		this.newUniversalEndTime = newUniversalEndTime;
	}

	public int handle(NotificationHandleService service){
		return service.handle(this);
	}
}
