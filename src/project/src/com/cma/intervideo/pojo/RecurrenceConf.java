package com.cma.intervideo.pojo;

// Generated Jan 17, 2010 1:18:25 PM by Hibernate Tools 3.2.4.GA

/**
 * RecurrenceConf generated by hbm2java
 */
public class RecurrenceConf implements java.io.Serializable {

	private Integer recurrenceConfId;
	private int recurrenceId;
	private int conferenceId;

	public RecurrenceConf() {
	}

	public RecurrenceConf(int recurrenceId, int conferenceId) {
		this.recurrenceId = recurrenceId;
		this.conferenceId = conferenceId;
	}

	public Integer getRecurrenceConfId() {
		return this.recurrenceConfId;
	}

	public void setRecurrenceConfId(Integer recurrenceConfId) {
		this.recurrenceConfId = recurrenceConfId;
	}

	public int getRecurrenceId() {
		return this.recurrenceId;
	}

	public void setRecurrenceId(int recurrenceId) {
		this.recurrenceId = recurrenceId;
	}

	public int getConferenceId() {
		return this.conferenceId;
	}

	public void setConferenceId(int conferenceId) {
		this.conferenceId = conferenceId;
	}

}
