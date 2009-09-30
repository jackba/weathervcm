
package com.radvision.icm.service;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for scheduleInfo complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="scheduleInfo">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="DIDNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="dialableConferenceId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="dialingExpression" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="gateway" type="{http://radvision.com/icm/service/scheduleservice}gatewayInfo" minOccurs="0"/>
 *         &lt;element name="zonePrefix" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "scheduleInfo", propOrder = {
    "didNumber",
    "dialableConferenceId",
    "dialingExpression",
    "gateway",
    "zonePrefix"
})
public class ScheduleInfo {

    @XmlElement(name = "DIDNumber")
    protected String didNumber;
    protected String dialableConferenceId;
    protected String dialingExpression;
    protected GatewayInfo gateway;
    protected String zonePrefix;

    /**
     * Gets the value of the didNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDIDNumber() {
        return didNumber;
    }

    /**
     * Sets the value of the didNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDIDNumber(String value) {
        this.didNumber = value;
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
     * Gets the value of the dialingExpression property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDialingExpression() {
        return dialingExpression;
    }

    /**
     * Sets the value of the dialingExpression property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDialingExpression(String value) {
        this.dialingExpression = value;
    }

    /**
     * Gets the value of the gateway property.
     * 
     * @return
     *     possible object is
     *     {@link GatewayInfo }
     *     
     */
    public GatewayInfo getGateway() {
        return gateway;
    }

    /**
     * Sets the value of the gateway property.
     * 
     * @param value
     *     allowed object is
     *     {@link GatewayInfo }
     *     
     */
    public void setGateway(GatewayInfo value) {
        this.gateway = value;
    }

    /**
     * Gets the value of the zonePrefix property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getZonePrefix() {
        return zonePrefix;
    }

    /**
     * Sets the value of the zonePrefix property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setZonePrefix(String value) {
        this.zonePrefix = value;
    }

}
