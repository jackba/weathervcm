
package com.radvision.icm.service;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for conferenceInfo complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="conferenceInfo">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="admitUnresolvedCalls" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="advancedInfo" type="{http://radvision.com/icm/service/scheduleservice}conferenceAdvancedInfo" minOccurs="0"/>
 *         &lt;element name="allowRecording" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="allowStreaming" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="autoExtend" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="blockDialin" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="classificationName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="conferenceId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="conferenceStatus" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="description" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="dialIn" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="dialableConferenceId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="endTime" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="events" type="{http://radvision.com/icm/service/scheduleservice}eventInfo" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="forcedDialableConferenceId" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="fullControlPassword" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="layouts" type="{http://radvision.com/icm/service/scheduleservice}layoutInfo" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="meetingTypeId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="orgID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="password" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="priority" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="recordMeetingWhenStart" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="recordingDuration" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="recordingQuality" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="recurrenceId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="requiredLevel" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="reservedIPPorts" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="reservedISDNPorts" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="reservedSPAudioOnlyPorts" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="reservedTPAudioOnlyPorts" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="rooms" type="{http://radvision.com/icm/service/scheduleservice}roomInfo" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="SDGInfo" type="{http://radvision.com/icm/service/scheduleservice}sdgInfo" minOccurs="0"/>
 *         &lt;element name="servicePrefix" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="startTime" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="streamingStatus" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="subject" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="terminals" type="{http://radvision.com/icm/service/scheduleservice}terminalInfo" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="testSchedule" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="timeZoneId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="userID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="waitingRoom" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "conferenceInfo", propOrder = {
    "admitUnresolvedCalls",
    "advancedInfo",
    "allowRecording",
    "allowStreaming",
    "autoExtend",
    "blockDialin",
    "classificationName",
    "conferenceId",
    "conferenceStatus",
    "description",
    "dialIn",
    "dialableConferenceId",
    "endTime",
    "events",
    "forcedDialableConferenceId",
    "fullControlPassword",
    "layouts",
    "meetingTypeId",
    "orgID",
    "password",
    "priority",
    "recordMeetingWhenStart",
    "recordingDuration",
    "recordingQuality",
    "recurrenceId",
    "requiredLevel",
    "reservedIPPorts",
    "reservedISDNPorts",
    "reservedSPAudioOnlyPorts",
    "reservedTPAudioOnlyPorts",
    "rooms",
    "sdgInfo",
    "servicePrefix",
    "startTime",
    "streamingStatus",
    "subject",
    "terminals",
    "testSchedule",
    "timeZoneId",
    "userID",
    "waitingRoom"
})
@XmlSeeAlso({
    VirtualRoomInfo.class
})
public class ConferenceInfo {

    protected boolean admitUnresolvedCalls;
    protected ConferenceAdvancedInfo advancedInfo;
    protected Boolean allowRecording;
    protected Boolean allowStreaming;
    protected boolean autoExtend;
    protected boolean blockDialin;
    protected String classificationName;
    protected String conferenceId;
    protected int conferenceStatus;
    protected String description;
    protected boolean dialIn;
    protected String dialableConferenceId;
    protected long endTime;
    @XmlElement(nillable = true)
    protected List<EventInfo> events;
    protected boolean forcedDialableConferenceId;
    protected String fullControlPassword;
    @XmlElement(nillable = true)
    protected List<LayoutInfo> layouts;
    protected String meetingTypeId;
    protected String orgID;
    protected String password;
    protected int priority;
    protected Boolean recordMeetingWhenStart;
    protected Integer recordingDuration;
    protected Integer recordingQuality;
    protected String recurrenceId;
    protected int requiredLevel;
    protected int reservedIPPorts;
    protected int reservedISDNPorts;
    protected int reservedSPAudioOnlyPorts;
    protected int reservedTPAudioOnlyPorts;
    @XmlElement(nillable = true)
    protected List<RoomInfo> rooms;
    @XmlElement(name = "SDGInfo")
    protected SdgInfo sdgInfo;
    protected String servicePrefix;
    protected long startTime;
    protected int streamingStatus;
    protected String subject;
    @XmlElement(nillable = true)
    protected List<TerminalInfo> terminals;
    protected boolean testSchedule;
    protected String timeZoneId;
    protected String userID;
    protected boolean waitingRoom;

    /**
     * Gets the value of the admitUnresolvedCalls property.
     * 
     */
    public boolean isAdmitUnresolvedCalls() {
        return admitUnresolvedCalls;
    }

    /**
     * Sets the value of the admitUnresolvedCalls property.
     * 
     */
    public void setAdmitUnresolvedCalls(boolean value) {
        this.admitUnresolvedCalls = value;
    }

    /**
     * Gets the value of the advancedInfo property.
     * 
     * @return
     *     possible object is
     *     {@link ConferenceAdvancedInfo }
     *     
     */
    public ConferenceAdvancedInfo getAdvancedInfo() {
        return advancedInfo;
    }

    /**
     * Sets the value of the advancedInfo property.
     * 
     * @param value
     *     allowed object is
     *     {@link ConferenceAdvancedInfo }
     *     
     */
    public void setAdvancedInfo(ConferenceAdvancedInfo value) {
        this.advancedInfo = value;
    }

    /**
     * Gets the value of the allowRecording property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isAllowRecording() {
        return allowRecording;
    }

    /**
     * Sets the value of the allowRecording property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setAllowRecording(Boolean value) {
        this.allowRecording = value;
    }

    /**
     * Gets the value of the allowStreaming property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isAllowStreaming() {
        return allowStreaming;
    }

    /**
     * Sets the value of the allowStreaming property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setAllowStreaming(Boolean value) {
        this.allowStreaming = value;
    }

    /**
     * Gets the value of the autoExtend property.
     * 
     */
    public boolean isAutoExtend() {
        return autoExtend;
    }

    /**
     * Sets the value of the autoExtend property.
     * 
     */
    public void setAutoExtend(boolean value) {
        this.autoExtend = value;
    }

    /**
     * Gets the value of the blockDialin property.
     * 
     */
    public boolean isBlockDialin() {
        return blockDialin;
    }

    /**
     * Sets the value of the blockDialin property.
     * 
     */
    public void setBlockDialin(boolean value) {
        this.blockDialin = value;
    }

    /**
     * Gets the value of the classificationName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getClassificationName() {
        return classificationName;
    }

    /**
     * Sets the value of the classificationName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setClassificationName(String value) {
        this.classificationName = value;
    }

    /**
     * Gets the value of the conferenceId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getConferenceId() {
        return conferenceId;
    }

    /**
     * Sets the value of the conferenceId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setConferenceId(String value) {
        this.conferenceId = value;
    }

    /**
     * Gets the value of the conferenceStatus property.
     * 
     */
    public int getConferenceStatus() {
        return conferenceStatus;
    }

    /**
     * Sets the value of the conferenceStatus property.
     * 
     */
    public void setConferenceStatus(int value) {
        this.conferenceStatus = value;
    }

    /**
     * Gets the value of the description property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the value of the description property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescription(String value) {
        this.description = value;
    }

    /**
     * Gets the value of the dialIn property.
     * 
     */
    public boolean isDialIn() {
        return dialIn;
    }

    /**
     * Sets the value of the dialIn property.
     * 
     */
    public void setDialIn(boolean value) {
        this.dialIn = value;
    }

    /**
     * Gets the value of the dialableConferenceId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDialableConferenceId() {
        return dialableConferenceId;
    }

    /**
     * Sets the value of the dialableConferenceId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDialableConferenceId(String value) {
        this.dialableConferenceId = value;
    }

    /**
     * Gets the value of the endTime property.
     * 
     */
    public long getEndTime() {
        return endTime;
    }

    /**
     * Sets the value of the endTime property.
     * 
     */
    public void setEndTime(long value) {
        this.endTime = value;
    }

    /**
     * Gets the value of the events property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the events property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getEvents().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link EventInfo }
     * 
     * 
     */
    public List<EventInfo> getEvents() {
        if (events == null) {
            events = new ArrayList<EventInfo>();
        }
        return this.events;
    }

    /**
     * Gets the value of the forcedDialableConferenceId property.
     * 
     */
    public boolean isForcedDialableConferenceId() {
        return forcedDialableConferenceId;
    }

    /**
     * Sets the value of the forcedDialableConferenceId property.
     * 
     */
    public void setForcedDialableConferenceId(boolean value) {
        this.forcedDialableConferenceId = value;
    }

    /**
     * Gets the value of the fullControlPassword property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFullControlPassword() {
        return fullControlPassword;
    }

    /**
     * Sets the value of the fullControlPassword property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFullControlPassword(String value) {
        this.fullControlPassword = value;
    }

    /**
     * Gets the value of the layouts property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the layouts property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getLayouts().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link LayoutInfo }
     * 
     * 
     */
    public List<LayoutInfo> getLayouts() {
        if (layouts == null) {
            layouts = new ArrayList<LayoutInfo>();
        }
        return this.layouts;
    }

    /**
     * Gets the value of the meetingTypeId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMeetingTypeId() {
        return meetingTypeId;
    }

    /**
     * Sets the value of the meetingTypeId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMeetingTypeId(String value) {
        this.meetingTypeId = value;
    }

    /**
     * Gets the value of the orgID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOrgID() {
        return orgID;
    }

    /**
     * Sets the value of the orgID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOrgID(String value) {
        this.orgID = value;
    }

    /**
     * Gets the value of the password property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the value of the password property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPassword(String value) {
        this.password = value;
    }

    /**
     * Gets the value of the priority property.
     * 
     */
    public int getPriority() {
        return priority;
    }

    /**
     * Sets the value of the priority property.
     * 
     */
    public void setPriority(int value) {
        this.priority = value;
    }

    /**
     * Gets the value of the recordMeetingWhenStart property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isRecordMeetingWhenStart() {
        return recordMeetingWhenStart;
    }

    /**
     * Sets the value of the recordMeetingWhenStart property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setRecordMeetingWhenStart(Boolean value) {
        this.recordMeetingWhenStart = value;
    }

    /**
     * Gets the value of the recordingDuration property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getRecordingDuration() {
        return recordingDuration;
    }

    /**
     * Sets the value of the recordingDuration property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setRecordingDuration(Integer value) {
        this.recordingDuration = value;
    }

    /**
     * Gets the value of the recordingQuality property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getRecordingQuality() {
        return recordingQuality;
    }

    /**
     * Sets the value of the recordingQuality property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setRecordingQuality(Integer value) {
        this.recordingQuality = value;
    }

    /**
     * Gets the value of the recurrenceId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRecurrenceId() {
        return recurrenceId;
    }

    /**
     * Sets the value of the recurrenceId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRecurrenceId(String value) {
        this.recurrenceId = value;
    }

    /**
     * Gets the value of the requiredLevel property.
     * 
     */
    public int getRequiredLevel() {
        return requiredLevel;
    }

    /**
     * Sets the value of the requiredLevel property.
     * 
     */
    public void setRequiredLevel(int value) {
        this.requiredLevel = value;
    }

    /**
     * Gets the value of the reservedIPPorts property.
     * 
     */
    public int getReservedIPPorts() {
        return reservedIPPorts;
    }

    /**
     * Sets the value of the reservedIPPorts property.
     * 
     */
    public void setReservedIPPorts(int value) {
        this.reservedIPPorts = value;
    }

    /**
     * Gets the value of the reservedISDNPorts property.
     * 
     */
    public int getReservedISDNPorts() {
        return reservedISDNPorts;
    }

    /**
     * Sets the value of the reservedISDNPorts property.
     * 
     */
    public void setReservedISDNPorts(int value) {
        this.reservedISDNPorts = value;
    }

    /**
     * Gets the value of the reservedSPAudioOnlyPorts property.
     * 
     */
    public int getReservedSPAudioOnlyPorts() {
        return reservedSPAudioOnlyPorts;
    }

    /**
     * Sets the value of the reservedSPAudioOnlyPorts property.
     * 
     */
    public void setReservedSPAudioOnlyPorts(int value) {
        this.reservedSPAudioOnlyPorts = value;
    }

    /**
     * Gets the value of the reservedTPAudioOnlyPorts property.
     * 
     */
    public int getReservedTPAudioOnlyPorts() {
        return reservedTPAudioOnlyPorts;
    }

    /**
     * Sets the value of the reservedTPAudioOnlyPorts property.
     * 
     */
    public void setReservedTPAudioOnlyPorts(int value) {
        this.reservedTPAudioOnlyPorts = value;
    }

    /**
     * Gets the value of the rooms property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the rooms property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getRooms().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link RoomInfo }
     * 
     * 
     */
    public List<RoomInfo> getRooms() {
        if (rooms == null) {
            rooms = new ArrayList<RoomInfo>();
        }
        return this.rooms;
    }

    /**
     * Gets the value of the sdgInfo property.
     * 
     * @return
     *     possible object is
     *     {@link SdgInfo }
     *     
     */
    public SdgInfo getSDGInfo() {
        return sdgInfo;
    }

    /**
     * Sets the value of the sdgInfo property.
     * 
     * @param value
     *     allowed object is
     *     {@link SdgInfo }
     *     
     */
    public void setSDGInfo(SdgInfo value) {
        this.sdgInfo = value;
    }

    /**
     * Gets the value of the servicePrefix property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getServicePrefix() {
        return servicePrefix;
    }

    /**
     * Sets the value of the servicePrefix property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setServicePrefix(String value) {
        this.servicePrefix = value;
    }

    /**
     * Gets the value of the startTime property.
     * 
     */
    public long getStartTime() {
        return startTime;
    }

    /**
     * Sets the value of the startTime property.
     * 
     */
    public void setStartTime(long value) {
        this.startTime = value;
    }

    /**
     * Gets the value of the streamingStatus property.
     * 
     */
    public int getStreamingStatus() {
        return streamingStatus;
    }

    /**
     * Sets the value of the streamingStatus property.
     * 
     */
    public void setStreamingStatus(int value) {
        this.streamingStatus = value;
    }

    /**
     * Gets the value of the subject property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSubject() {
        return subject;
    }

    /**
     * Sets the value of the subject property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSubject(String value) {
        this.subject = value;
    }

    /**
     * Gets the value of the terminals property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the terminals property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getTerminals().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link TerminalInfo }
     * 
     * 
     */
    public List<TerminalInfo> getTerminals() {
        if (terminals == null) {
            terminals = new ArrayList<TerminalInfo>();
        }
        return this.terminals;
    }

    /**
     * Gets the value of the testSchedule property.
     * 
     */
    public boolean isTestSchedule() {
        return testSchedule;
    }

    /**
     * Sets the value of the testSchedule property.
     * 
     */
    public void setTestSchedule(boolean value) {
        this.testSchedule = value;
    }

    /**
     * Gets the value of the timeZoneId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTimeZoneId() {
        return timeZoneId;
    }

    /**
     * Sets the value of the timeZoneId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTimeZoneId(String value) {
        this.timeZoneId = value;
    }

    /**
     * Gets the value of the userID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUserID() {
        return userID;
    }

    /**
     * Sets the value of the userID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUserID(String value) {
        this.userID = value;
    }

    /**
     * Gets the value of the waitingRoom property.
     * 
     */
    public boolean isWaitingRoom() {
        return waitingRoom;
    }

    /**
     * Sets the value of the waitingRoom property.
     * 
     */
    public void setWaitingRoom(boolean value) {
        this.waitingRoom = value;
    }

}
