
package com.radvision.icm.service;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for roomResource complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="roomResource">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="conferenceRoomId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="conferenceRoomLocation" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="conferenceRoomName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="status" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "roomResource", propOrder = {
    "conferenceRoomId",
    "conferenceRoomLocation",
    "conferenceRoomName",
    "status"
})
public class RoomResource {

    protected String conferenceRoomId;
    protected String conferenceRoomLocation;
    protected String conferenceRoomName;
    protected int status;

    /**
     * Gets the value of the conferenceRoomId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getConferenceRoomId() {
        return conferenceRoomId;
    }

    /**
     * Sets the value of the conferenceRoomId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setConferenceRoomId(String value) {
        this.conferenceRoomId = value;
    }

    /**
     * Gets the value of the conferenceRoomLocation property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getConferenceRoomLocation() {
        return conferenceRoomLocation;
    }

    /**
     * Sets the value of the conferenceRoomLocation property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setConferenceRoomLocation(String value) {
        this.conferenceRoomLocation = value;
    }

    /**
     * Gets the value of the conferenceRoomName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getConferenceRoomName() {
        return conferenceRoomName;
    }

    /**
     * Sets the value of the conferenceRoomName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setConferenceRoomName(String value) {
        this.conferenceRoomName = value;
    }

    /**
     * Gets the value of the status property.
     * 
     */
    public int getStatus() {
        return status;
    }

    /**
     * Sets the value of the status property.
     * 
     */
    public void setStatus(int value) {
        this.status = value;
    }

}
