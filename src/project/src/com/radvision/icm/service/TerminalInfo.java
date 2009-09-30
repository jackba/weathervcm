
package com.radvision.icm.service;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for terminalInfo complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="terminalInfo">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="areaCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="audioOnly" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="audioOnlyPortCount" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="audioVideoPortCount" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="bindingAreaCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="bindingCountryCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="bindingNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="countryCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="dual" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="host" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="ISDN" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="layouts" type="{http://radvision.com/icm/service/scheduleservice}terminalLayoutInfo" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="outSider" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="scheduleInfo" type="{http://radvision.com/icm/service/scheduleservice}scheduleInfo" minOccurs="0"/>
 *         &lt;element name="scheduledGWId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="telePresence" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="terminalId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="terminalNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="videoOnlyPortCount" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "terminalInfo", propOrder = {
    "areaCode",
    "audioOnly",
    "audioOnlyPortCount",
    "audioVideoPortCount",
    "bindingAreaCode",
    "bindingCountryCode",
    "bindingNumber",
    "countryCode",
    "dual",
    "host",
    "isdn",
    "layouts",
    "outSider",
    "scheduleInfo",
    "scheduledGWId",
    "telePresence",
    "terminalId",
    "terminalNumber",
    "videoOnlyPortCount"
})
public class TerminalInfo {

    protected String areaCode;
    protected boolean audioOnly;
    protected int audioOnlyPortCount;
    protected int audioVideoPortCount;
    protected String bindingAreaCode;
    protected String bindingCountryCode;
    protected String bindingNumber;
    protected String countryCode;
    protected boolean dual;
    protected boolean host;
    @XmlElement(name = "ISDN")
    protected boolean isdn;
    @XmlElement(nillable = true)
    protected List<TerminalLayoutInfo> layouts;
    protected boolean outSider;
    protected ScheduleInfo scheduleInfo;
    protected String scheduledGWId;
    protected boolean telePresence;
    protected String terminalId;
    protected String terminalNumber;
    protected int videoOnlyPortCount;

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
     * Gets the value of the audioOnly property.
     * 
     */
    public boolean isAudioOnly() {
        return audioOnly;
    }

    /**
     * Sets the value of the audioOnly property.
     * 
     */
    public void setAudioOnly(boolean value) {
        this.audioOnly = value;
    }

    /**
     * Gets the value of the audioOnlyPortCount property.
     * 
     */
    public int getAudioOnlyPortCount() {
        return audioOnlyPortCount;
    }

    /**
     * Sets the value of the audioOnlyPortCount property.
     * 
     */
    public void setAudioOnlyPortCount(int value) {
        this.audioOnlyPortCount = value;
    }

    /**
     * Gets the value of the audioVideoPortCount property.
     * 
     */
    public int getAudioVideoPortCount() {
        return audioVideoPortCount;
    }

    /**
     * Sets the value of the audioVideoPortCount property.
     * 
     */
    public void setAudioVideoPortCount(int value) {
        this.audioVideoPortCount = value;
    }

    /**
     * Gets the value of the bindingAreaCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBindingAreaCode() {
        return bindingAreaCode;
    }

    /**
     * Sets the value of the bindingAreaCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBindingAreaCode(String value) {
        this.bindingAreaCode = value;
    }

    /**
     * Gets the value of the bindingCountryCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBindingCountryCode() {
        return bindingCountryCode;
    }

    /**
     * Sets the value of the bindingCountryCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBindingCountryCode(String value) {
        this.bindingCountryCode = value;
    }

    /**
     * Gets the value of the bindingNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBindingNumber() {
        return bindingNumber;
    }

    /**
     * Sets the value of the bindingNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBindingNumber(String value) {
        this.bindingNumber = value;
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
     * Gets the value of the dual property.
     * 
     */
    public boolean isDual() {
        return dual;
    }

    /**
     * Sets the value of the dual property.
     * 
     */
    public void setDual(boolean value) {
        this.dual = value;
    }

    /**
     * Gets the value of the host property.
     * 
     */
    public boolean isHost() {
        return host;
    }

    /**
     * Sets the value of the host property.
     * 
     */
    public void setHost(boolean value) {
        this.host = value;
    }

    /**
     * Gets the value of the isdn property.
     * 
     */
    public boolean isISDN() {
        return isdn;
    }

    /**
     * Sets the value of the isdn property.
     * 
     */
    public void setISDN(boolean value) {
        this.isdn = value;
    }

    /**
     * Gets the value of the layouts property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the layouts property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getLayouts().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link TerminalLayoutInfo }
     * 
     * 
     */
    public List<TerminalLayoutInfo> getLayouts() {
        if (layouts == null) {
            layouts = new ArrayList<TerminalLayoutInfo>();
        }
        return this.layouts;
    }

    /**
     * Gets the value of the outSider property.
     * 
     */
    public boolean isOutSider() {
        return outSider;
    }

    /**
     * Sets the value of the outSider property.
     * 
     */
    public void setOutSider(boolean value) {
        this.outSider = value;
    }

    /**
     * Gets the value of the scheduleInfo property.
     * 
     * @return
     *     possible object is
     *     {@link ScheduleInfo }
     *     
     */
    public ScheduleInfo getScheduleInfo() {
        return scheduleInfo;
    }

    /**
     * Sets the value of the scheduleInfo property.
     * 
     * @param value
     *     allowed object is
     *     {@link ScheduleInfo }
     *     
     */
    public void setScheduleInfo(ScheduleInfo value) {
        this.scheduleInfo = value;
    }

    /**
     * Gets the value of the scheduledGWId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getScheduledGWId() {
        return scheduledGWId;
    }

    /**
     * Sets the value of the scheduledGWId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setScheduledGWId(String value) {
        this.scheduledGWId = value;
    }

    /**
     * Gets the value of the telePresence property.
     * 
     */
    public boolean isTelePresence() {
        return telePresence;
    }

    /**
     * Sets the value of the telePresence property.
     * 
     */
    public void setTelePresence(boolean value) {
        this.telePresence = value;
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
     * Gets the value of the videoOnlyPortCount property.
     * 
     */
    public int getVideoOnlyPortCount() {
        return videoOnlyPortCount;
    }

    /**
     * Sets the value of the videoOnlyPortCount property.
     * 
     */
    public void setVideoOnlyPortCount(int value) {
        this.videoOnlyPortCount = value;
    }

}
