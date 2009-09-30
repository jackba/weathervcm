
package com.radvision.icm.service;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for scheduleResult complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="scheduleResult">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="conferenceId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="conferenceInfo" type="{http://radvision.com/icm/service/scheduleservice}conferenceInfo" minOccurs="0"/>
 *         &lt;element name="errorInfo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="errorStatus" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="localAccessUrl" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="publicAccessUrl" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="resourceDeviceType" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="resourceId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="resourceType" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="success" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "scheduleResult", propOrder = {
    "conferenceId",
    "conferenceInfo",
    "errorInfo",
    "errorStatus",
    "localAccessUrl",
    "publicAccessUrl",
    "resourceDeviceType",
    "resourceId",
    "resourceType",
    "success"
})
@XmlSeeAlso({
    ScheduleResultEx.class
})
public class ScheduleResult {

    protected String conferenceId;
    protected ConferenceInfo conferenceInfo;
    protected String errorInfo;
    protected int errorStatus;
    protected String localAccessUrl;
    protected String publicAccessUrl;
    protected int resourceDeviceType;
    protected String resourceId;
    protected int resourceType;
    protected boolean success;

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
     * Gets the value of the errorInfo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getErrorInfo() {
        return errorInfo;
    }

    /**
     * Sets the value of the errorInfo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setErrorInfo(String value) {
        this.errorInfo = value;
    }

    /**
     * Gets the value of the errorStatus property.
     * 
     */
    public int getErrorStatus() {
        return errorStatus;
    }

    /**
     * Sets the value of the errorStatus property.
     * 
     */
    public void setErrorStatus(int value) {
        this.errorStatus = value;
    }

    /**
     * Gets the value of the localAccessUrl property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLocalAccessUrl() {
        return localAccessUrl;
    }

    /**
     * Sets the value of the localAccessUrl property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLocalAccessUrl(String value) {
        this.localAccessUrl = value;
    }

    /**
     * Gets the value of the publicAccessUrl property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPublicAccessUrl() {
        return publicAccessUrl;
    }

    /**
     * Sets the value of the publicAccessUrl property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPublicAccessUrl(String value) {
        this.publicAccessUrl = value;
    }

    /**
     * Gets the value of the resourceDeviceType property.
     * 
     */
    public int getResourceDeviceType() {
        return resourceDeviceType;
    }

    /**
     * Sets the value of the resourceDeviceType property.
     * 
     */
    public void setResourceDeviceType(int value) {
        this.resourceDeviceType = value;
    }

    /**
     * Gets the value of the resourceId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getResourceId() {
        return resourceId;
    }

    /**
     * Sets the value of the resourceId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setResourceId(String value) {
        this.resourceId = value;
    }

    /**
     * Gets the value of the resourceType property.
     * 
     */
    public int getResourceType() {
        return resourceType;
    }

    /**
     * Sets the value of the resourceType property.
     * 
     */
    public void setResourceType(int value) {
        this.resourceType = value;
    }

    /**
     * Gets the value of the success property.
     * 
     */
    public boolean isSuccess() {
        return success;
    }

    /**
     * Sets the value of the success property.
     * 
     */
    public void setSuccess(boolean value) {
        this.success = value;
    }

}
