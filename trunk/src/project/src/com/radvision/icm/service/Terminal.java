
package com.radvision.icm.service;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for terminal complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="terminal">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="audioOnlyPortCount" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="audioVideoPortCount" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="connectRate" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="desc" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="IP" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="isDialIn" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="isHost" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="isOutside" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="isVoiceOnly" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="phyId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="telePresence" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="terminalId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="terminalName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
@XmlType(name = "terminal", propOrder = {
    "audioOnlyPortCount",
    "audioVideoPortCount",
    "connectRate",
    "desc",
    "ip",
    "isDialIn",
    "isHost",
    "isOutside",
    "isVoiceOnly",
    "phyId",
    "telePresence",
    "terminalId",
    "terminalName",
    "terminalNumber",
    "videoOnlyPortCount"
})
public class Terminal {

    protected int audioOnlyPortCount;
    protected int audioVideoPortCount;
    protected int connectRate;
    protected String desc;
    @XmlElement(name = "IP")
    protected String ip;
    protected boolean isDialIn;
    protected boolean isHost;
    protected boolean isOutside;
    protected boolean isVoiceOnly;
    protected String phyId;
    protected boolean telePresence;
    protected String terminalId;
    protected String terminalName;
    protected String terminalNumber;
    protected int videoOnlyPortCount;

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
     * Gets the value of the connectRate property.
     * 
     */
    public int getConnectRate() {
        return connectRate;
    }

    /**
     * Sets the value of the connectRate property.
     * 
     */
    public void setConnectRate(int value) {
        this.connectRate = value;
    }

    /**
     * Gets the value of the desc property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDesc() {
        return desc;
    }

    /**
     * Sets the value of the desc property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDesc(String value) {
        this.desc = value;
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
     * Gets the value of the isDialIn property.
     * 
     */
    public boolean isIsDialIn() {
        return isDialIn;
    }

    /**
     * Sets the value of the isDialIn property.
     * 
     */
    public void setIsDialIn(boolean value) {
        this.isDialIn = value;
    }

    /**
     * Gets the value of the isHost property.
     * 
     */
    public boolean isIsHost() {
        return isHost;
    }

    /**
     * Sets the value of the isHost property.
     * 
     */
    public void setIsHost(boolean value) {
        this.isHost = value;
    }

    /**
     * Gets the value of the isOutside property.
     * 
     */
    public boolean isIsOutside() {
        return isOutside;
    }

    /**
     * Sets the value of the isOutside property.
     * 
     */
    public void setIsOutside(boolean value) {
        this.isOutside = value;
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
     * Gets the value of the phyId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPhyId() {
        return phyId;
    }

    /**
     * Sets the value of the phyId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPhyId(String value) {
        this.phyId = value;
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
