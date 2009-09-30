
package com.radvision.icm.service;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for eventInfo complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="eventInfo">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="eventMemo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="eventType" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="relativeTime" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "eventInfo", propOrder = {
    "eventMemo",
    "eventType",
    "relativeTime"
})
public class EventInfo {

    protected String eventMemo;
    protected int eventType;
    protected long relativeTime;

    /**
     * Gets the value of the eventMemo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEventMemo() {
        return eventMemo;
    }

    /**
     * Sets the value of the eventMemo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEventMemo(String value) {
        this.eventMemo = value;
    }

    /**
     * Gets the value of the eventType property.
     * 
     */
    public int getEventType() {
        return eventType;
    }

    /**
     * Sets the value of the eventType property.
     * 
     */
    public void setEventType(int value) {
        this.eventType = value;
    }

    /**
     * Gets the value of the relativeTime property.
     * 
     */
    public long getRelativeTime() {
        return relativeTime;
    }

    /**
     * Sets the value of the relativeTime property.
     * 
     */
    public void setRelativeTime(long value) {
        this.relativeTime = value;
    }

}
