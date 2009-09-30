
package com.radvision.icm.service;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for extendLiveConference complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="extendLiveConference">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="conferenceId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="extendTime" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "extendLiveConference", propOrder = {
    "conferenceId",
    "extendTime"
})
public class ExtendLiveConference {

    protected String conferenceId;
    protected long extendTime;

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
     * Gets the value of the extendTime property.
     * 
     */
    public long getExtendTime() {
        return extendTime;
    }

    /**
     * Sets the value of the extendTime property.
     * 
     */
    public void setExtendTime(long value) {
        this.extendTime = value;
    }

}
