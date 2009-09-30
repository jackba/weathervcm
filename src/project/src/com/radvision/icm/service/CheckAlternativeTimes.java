
package com.radvision.icm.service;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for checkAlternativeTimes complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="checkAlternativeTimes">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="conferenceInfo" type="{http://radvision.com/icm/service/scheduleservice}conferenceInfo" minOccurs="0"/>
 *         &lt;element name="startTime" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="endTime" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="cycleInterval" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "checkAlternativeTimes", propOrder = {
    "conferenceInfo",
    "startTime",
    "endTime",
    "cycleInterval"
})
public class CheckAlternativeTimes {

    protected ConferenceInfo conferenceInfo;
    protected long startTime;
    protected long endTime;
    protected long cycleInterval;

    /**
     * Gets the value of the conferenceInfo property.
     * 
     * @return
     *     possible object is
     *     {@link ConferenceInfo }
     *     
     */
    public ConferenceInfo getConferenceInfo() {
        return conferenceInfo;
    }

    /**
     * Sets the value of the conferenceInfo property.
     * 
     * @param value
     *     allowed object is
     *     {@link ConferenceInfo }
     *     
     */
    public void setConferenceInfo(ConferenceInfo value) {
        this.conferenceInfo = value;
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
     * Gets the value of the cycleInterval property.
     * 
     */
    public long getCycleInterval() {
        return cycleInterval;
    }

    /**
     * Sets the value of the cycleInterval property.
     * 
     */
    public void setCycleInterval(long value) {
        this.cycleInterval = value;
    }

}
