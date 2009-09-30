
package com.radvision.icm.service;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for createConference complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="createConference">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="conferenceInfo" type="{http://radvision.com/icm/service/scheduleservice}conferenceInfo" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "createConference", propOrder = {
    "conferenceInfo"
})
public class CreateConference {

    protected ConferenceInfo conferenceInfo;

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

}
