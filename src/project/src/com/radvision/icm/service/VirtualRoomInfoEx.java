
package com.radvision.icm.service;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for virtualRoomInfoEx complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="virtualRoomInfoEx">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="attendees" type="{http://radvision.com/icm/service/scheduleservice}wsTemplateAttendeeInfo" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="blockDialIn" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="chairControlPassword" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="description" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="duration" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="isAutoExtend" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="memberID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="outsiders" type="{http://radvision.com/icm/service/scheduleservice}wsOutsiderInfo" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="password" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="recordMeetingWhenStart" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="referenceCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="reservedPorts" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="serviceID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="streamingRecording" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="streamingStatus" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="timeZone" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="userID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="virtualRoomID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="virtualRoomName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="virtualRoomNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "virtualRoomInfoEx", propOrder = {
    "attendees",
    "blockDialIn",
    "chairControlPassword",
    "description",
    "duration",
    "isAutoExtend",
    "memberID",
    "outsiders",
    "password",
    "recordMeetingWhenStart",
    "referenceCode",
    "reservedPorts",
    "serviceID",
    "streamingRecording",
    "streamingStatus",
    "timeZone",
    "userID",
    "virtualRoomID",
    "virtualRoomName",
    "virtualRoomNumber"
})
public class VirtualRoomInfoEx {

    @XmlElement(nillable = true)
    protected List<WsTemplateAttendeeInfo> attendees;
    protected boolean blockDialIn;
    protected String chairControlPassword;
    protected String description;
    protected int duration;
    protected boolean isAutoExtend;
    protected String memberID;
    @XmlElement(nillable = true)
    protected List<WsOutsiderInfo> outsiders;
    protected String password;
    protected boolean recordMeetingWhenStart;
    protected String referenceCode;
    protected int reservedPorts;
    protected String serviceID;
    protected boolean streamingRecording;
    protected int streamingStatus;
    protected String timeZone;
    protected String userID;
    protected String virtualRoomID;
    protected String virtualRoomName;
    protected String virtualRoomNumber;

    /**
     * Gets the value of the attendees property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the attendees property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAttendees().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link WsTemplateAttendeeInfo }
     * 
     * 
     */
    public List<WsTemplateAttendeeInfo> getAttendees() {
        if (attendees == null) {
            attendees = new ArrayList<WsTemplateAttendeeInfo>();
        }
        return this.attendees;
    }

    /**
     * Gets the value of the blockDialIn property.
     * 
     */
    public boolean isBlockDialIn() {
        return blockDialIn;
    }

    /**
     * Sets the value of the blockDialIn property.
     * 
     */
    public void setBlockDialIn(boolean value) {
        this.blockDialIn = value;
    }

    /**
     * Gets the value of the chairControlPassword property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getChairControlPassword() {
        return chairControlPassword;
    }

    /**
     * Sets the value of the chairControlPassword property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setChairControlPassword(String value) {
        this.chairControlPassword = value;
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
     * Gets the value of the duration property.
     * 
     */
    public int getDuration() {
        return duration;
    }

    /**
     * Sets the value of the duration property.
     * 
     */
    public void setDuration(int value) {
        this.duration = value;
    }

    /**
     * Gets the value of the isAutoExtend property.
     * 
     */
    public boolean isIsAutoExtend() {
        return isAutoExtend;
    }

    /**
     * Sets the value of the isAutoExtend property.
     * 
     */
    public void setIsAutoExtend(boolean value) {
        this.isAutoExtend = value;
    }

    /**
     * Gets the value of the memberID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMemberID() {
        return memberID;
    }

    /**
     * Sets the value of the memberID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMemberID(String value) {
        this.memberID = value;
    }

    /**
     * Gets the value of the outsiders property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the outsiders property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getOutsiders().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link WsOutsiderInfo }
     * 
     * 
     */
    public List<WsOutsiderInfo> getOutsiders() {
        if (outsiders == null) {
            outsiders = new ArrayList<WsOutsiderInfo>();
        }
        return this.outsiders;
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
     * Gets the value of the recordMeetingWhenStart property.
     * 
     */
    public boolean isRecordMeetingWhenStart() {
        return recordMeetingWhenStart;
    }

    /**
     * Sets the value of the recordMeetingWhenStart property.
     * 
     */
    public void setRecordMeetingWhenStart(boolean value) {
        this.recordMeetingWhenStart = value;
    }

    /**
     * Gets the value of the referenceCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReferenceCode() {
        return referenceCode;
    }

    /**
     * Sets the value of the referenceCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReferenceCode(String value) {
        this.referenceCode = value;
    }

    /**
     * Gets the value of the reservedPorts property.
     * 
     */
    public int getReservedPorts() {
        return reservedPorts;
    }

    /**
     * Sets the value of the reservedPorts property.
     * 
     */
    public void setReservedPorts(int value) {
        this.reservedPorts = value;
    }

    /**
     * Gets the value of the serviceID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getServiceID() {
        return serviceID;
    }

    /**
     * Sets the value of the serviceID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setServiceID(String value) {
        this.serviceID = value;
    }

    /**
     * Gets the value of the streamingRecording property.
     * 
     */
    public boolean isStreamingRecording() {
        return streamingRecording;
    }

    /**
     * Sets the value of the streamingRecording property.
     * 
     */
    public void setStreamingRecording(boolean value) {
        this.streamingRecording = value;
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
     * Gets the value of the timeZone property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTimeZone() {
        return timeZone;
    }

    /**
     * Sets the value of the timeZone property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTimeZone(String value) {
        this.timeZone = value;
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
     * Gets the value of the virtualRoomID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVirtualRoomID() {
        return virtualRoomID;
    }

    /**
     * Sets the value of the virtualRoomID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVirtualRoomID(String value) {
        this.virtualRoomID = value;
    }

    /**
     * Gets the value of the virtualRoomName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVirtualRoomName() {
        return virtualRoomName;
    }

    /**
     * Sets the value of the virtualRoomName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVirtualRoomName(String value) {
        this.virtualRoomName = value;
    }

    /**
     * Gets the value of the virtualRoomNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVirtualRoomNumber() {
        return virtualRoomNumber;
    }

    /**
     * Sets the value of the virtualRoomNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVirtualRoomNumber(String value) {
        this.virtualRoomNumber = value;
    }

}
