
package com.radvision.icm.service;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for recurrenceInfo complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="recurrenceInfo">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="conferenceInfoTemplate" type="{http://radvision.com/icm/service/scheduleservice}conferenceInfo" minOccurs="0"/>
 *         &lt;element name="conferenceInfos" type="{http://radvision.com/icm/service/scheduleservice}conferenceInfo" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="recurInstanceInfos" type="{http://radvision.com/icm/service/scheduleservice}recurInstanceInfo" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="recurrenceId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "recurrenceInfo", propOrder = {
    "conferenceInfoTemplate",
    "conferenceInfos",
    "recurInstanceInfos",
    "recurrenceId"
})
public class RecurrenceInfo {

    protected ConferenceInfo conferenceInfoTemplate;
    @XmlElement(nillable = true)
    protected List<ConferenceInfo> conferenceInfos;
    @XmlElement(nillable = true)
    protected List<RecurInstanceInfo> recurInstanceInfos;
    protected String recurrenceId;

    /**
     * Gets the value of the conferenceInfoTemplate property.
     * 
     * @return
     *     possible object is
     *     {@link ConferenceInfo }
     *     
     */
    public ConferenceInfo getConferenceInfoTemplate() {
        return conferenceInfoTemplate;
    }

    /**
     * Sets the value of the conferenceInfoTemplate property.
     * 
     * @param value
     *     allowed object is
     *     {@link ConferenceInfo }
     *     
     */
    public void setConferenceInfoTemplate(ConferenceInfo value) {
        this.conferenceInfoTemplate = value;
    }

    /**
     * Gets the value of the conferenceInfos property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the conferenceInfos property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getConferenceInfos().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ConferenceInfo }
     * 
     * 
     */
    public List<ConferenceInfo> getConferenceInfos() {
        if (conferenceInfos == null) {
            conferenceInfos = new ArrayList<ConferenceInfo>();
        }
        return this.conferenceInfos;
    }

    /**
     * Gets the value of the recurInstanceInfos property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the recurInstanceInfos property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getRecurInstanceInfos().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link RecurInstanceInfo }
     * 
     * 
     */
    public List<RecurInstanceInfo> getRecurInstanceInfos() {
        if (recurInstanceInfos == null) {
            recurInstanceInfos = new ArrayList<RecurInstanceInfo>();
        }
        return this.recurInstanceInfos;
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

}
