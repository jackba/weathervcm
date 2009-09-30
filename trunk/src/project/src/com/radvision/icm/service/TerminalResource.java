
package com.radvision.icm.service;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for terminalResource complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="terminalResource">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="areaCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="classificationNames" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="countryCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="defaultRoomId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="detailProtocol" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="e164" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="IP" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ISDNLocations" type="{http://radvision.com/icm/service/resourceservice}isdnLocation" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="isVoiceOnly" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="isdnMaxBandwidth" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="maxBandwidth" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="nodeId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="registerGKId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="statusId" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="terminalEmail" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="terminalId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="terminalName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="terminalNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="terminalProtocol" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="timeZoneId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
@XmlType(name = "terminalResource", propOrder = {
    "areaCode",
    "classificationNames",
    "countryCode",
    "defaultRoomId",
    "detailProtocol",
    "e164",
    "ip",
    "isdnLocations",
    "isVoiceOnly",
    "isdnMaxBandwidth",
    "maxBandwidth",
    "nodeId",
    "registerGKId",
    "statusId",
    "terminalEmail",
    "terminalId",
    "terminalName",
    "terminalNumber",
    "terminalProtocol",
    "timeZoneId",
    "zonePrefix"
})
public class TerminalResource {

    protected String areaCode;
    @XmlElement(nillable = true)
    protected List<String> classificationNames;
    protected String countryCode;
    protected String defaultRoomId;
    protected int detailProtocol;
    protected String e164;
    @XmlElement(name = "IP")
    protected String ip;
    @XmlElement(name = "ISDNLocations", nillable = true)
    protected List<IsdnLocation> isdnLocations;
    protected boolean isVoiceOnly;
    protected int isdnMaxBandwidth;
    protected int maxBandwidth;
    protected String nodeId;
    protected String registerGKId;
    protected int statusId;
    protected String terminalEmail;
    protected String terminalId;
    protected String terminalName;
    protected String terminalNumber;
    protected int terminalProtocol;
    protected String timeZoneId;
    protected String zonePrefix;

    /**
     * Gets the value of the areaCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAreaCode() {
        return areaCode;
    }

    /**
     * Sets the value of the areaCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAreaCode(String value) {
        this.areaCode = value;
    }

    /**
     * Gets the value of the classificationNames property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the classificationNames property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getClassificationNames().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getClassificationNames() {
        if (classificationNames == null) {
            classificationNames = new ArrayList<String>();
        }
        return this.classificationNames;
    }

    /**
     * Gets the value of the countryCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCountryCode() {
        return countryCode;
    }

    /**
     * Sets the value of the countryCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCountryCode(String value) {
        this.countryCode = value;
    }

    /**
     * Gets the value of the defaultRoomId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDefaultRoomId() {
        return defaultRoomId;
    }

    /**
     * Sets the value of the defaultRoomId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDefaultRoomId(String value) {
        this.defaultRoomId = value;
    }

    /**
     * Gets the value of the detailProtocol property.
     * 
     */
    public int getDetailProtocol() {
        return detailProtocol;
    }

    /**
     * Sets the value of the detailProtocol property.
     * 
     */
    public void setDetailProtocol(int value) {
        this.detailProtocol = value;
    }

    /**
     * Gets the value of the e164 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getE164() {
        return e164;
    }

    /**
     * Sets the value of the e164 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setE164(String value) {
        this.e164 = value;
    }

    /**
     * Gets the value of the ip property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIP() {
        return ip;
    }

    /**
     * Sets the value of the ip property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIP(String value) {
        this.ip = value;
    }

    /**
     * Gets the value of the isdnLocations property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the isdnLocations property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getISDNLocations().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link IsdnLocation }
     * 
     * 
     */
    public List<IsdnLocation> getISDNLocations() {
        if (isdnLocations == null) {
            isdnLocations = new ArrayList<IsdnLocation>();
        }
        return this.isdnLocations;
    }

    /**
     * Gets the value of the isVoiceOnly property.
     * 
     */
    public boolean isIsVoiceOnly() {
        return isVoiceOnly;
    }

    /**
     * Sets the value of the isVoiceOnly property.
     * 
     */
    public void setIsVoiceOnly(boolean value) {
        this.isVoiceOnly = value;
    }

    /**
     * Gets the value of the isdnMaxBandwidth property.
     * 
     */
    public int getIsdnMaxBandwidth() {
        return isdnMaxBandwidth;
    }

    /**
     * Sets the value of the isdnMaxBandwidth property.
     * 
     */
    public void setIsdnMaxBandwidth(int value) {
        this.isdnMaxBandwidth = value;
    }

    /**
     * Gets the value of the maxBandwidth property.
     * 
     */
    public int getMaxBandwidth() {
        return maxBandwidth;
    }

    /**
     * Sets the value of the maxBandwidth property.
     * 
     */
    public void setMaxBandwidth(int value) {
        this.maxBandwidth = value;
    }

    /**
     * Gets the value of the nodeId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNodeId() {
        return nodeId;
    }

    /**
     * Sets the value of the nodeId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNodeId(String value) {
        this.nodeId = value;
    }

    /**
     * Gets the value of the registerGKId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRegisterGKId() {
        return registerGKId;
    }

    /**
     * Sets the value of the registerGKId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRegisterGKId(String value) {
        this.registerGKId = value;
    }

    /**
     * Gets the value of the statusId property.
     * 
     */
    public int getStatusId() {
        return statusId;
    }

    /**
     * Sets the value of the statusId property.
     * 
     */
    public void setStatusId(int value) {
        this.statusId = value;
    }

    /**
     * Gets the value of the terminalEmail property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTerminalEmail() {
        return terminalEmail;
    }

    /**
     * Sets the value of the terminalEmail property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTerminalEmail(String value) {
        this.terminalEmail = value;
    }

    /**
     * Gets the value of the terminalId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTerminalId() {
        return terminalId;
    }

    /**
     * Sets the value of the terminalId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTerminalId(String value) {
        this.terminalId = value;
    }

    /**
     * Gets the value of the terminalName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTerminalName() {
        return terminalName;
    }

    /**
     * Sets the value of the terminalName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTerminalName(String value) {
        this.terminalName = value;
    }

    /**
     * Gets the value of the terminalNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTerminalNumber() {
        return terminalNumber;
    }

    /**
     * Sets the value of the terminalNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTerminalNumber(String value) {
        this.terminalNumber = value;
    }

    /**
     * Gets the value of the terminalProtocol property.
     * 
     */
    public int getTerminalProtocol() {
        return terminalProtocol;
    }

    /**
     * Sets the value of the terminalProtocol property.
     * 
     */
    public void setTerminalProtocol(int value) {
        this.terminalProtocol = value;
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
