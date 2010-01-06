package com.radvision.icm.service.vcm;

import com.radvision.icm.service.ConferenceInfoCondition;

public class ServiceUtil {

	public static String toString(ConferenceInfoCondition condition) {
		StringBuilder buf = new StringBuilder();
		buf.append("ConferenceInfoCondition: conferenceId = " + condition.getConferenceId() + ", conferenceStatus = " + condition.getConferenceStatus());
		buf.append(", startTime = " + condition.getStartTime() + ", endTime = " + condition.getEndTime());
		buf.append(", subject = " + condition.getSubject() + ", partyE164 = " + condition.getPartyE164());
		return buf.toString();
	}

}
