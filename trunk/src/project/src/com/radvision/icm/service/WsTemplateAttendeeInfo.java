
package com.radvision.icm.service;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for wsTemplateAttendeeInfo complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="wsTemplateAttendeeInfo">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="areaCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="bandwidth" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="countryCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="dialIn" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="dual" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="e164" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="email" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="firstName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="for3G" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="host" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="ISDN" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="lastName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="memberID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="outsiderType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="owner" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="partyProtocolType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="terminalAlias" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="terminalAttendee" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="terminalId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="terminalNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="userID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="viewID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "wsTemplateAttendeeInfo", propOrder = {
    "areaCode",
    "bandwidth",
    "countryCode",
    "dialIn",
    "dual",
    "e164",
    "email",
    "firstName",
    "for3G",
    "host",
    "isdn",
    "lastName",
    "memberID",
    "outsiderType",
    "owner",
    "partyProtocolType",
    "terminalAlias",
    "terminalAttendee",
    "terminalId",
    "terminalNumber",
    "userID",
    "viewID"
})
public class WsTemplateAttendeeInfo {

    protected String areaCode;
    protected int bandwidth;
    protected String countryCode;
    protected boolean dialIn;
    protected boolean dual;
    protected String e164;
    protected String email;
    protected String firstName;
    protected boolean for3G;
    protected boolean host;
    @XmlElement(name = "ISDN")
    protected boolean isdn;
    protected String lastName;
    protected String memberID;
    protected String outsiderType;
    protected boolean owner;
    protected String partyProtocolType;
    protected String terminalAlias;
    protected boolean terminalAttendee;
    protected String terminalId;
    protected String terminalNumber;
    protected String userID;
    protected String viewID;

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
     * Gets the value of the bandwidth property.
     * 
     */
    public int getBandwidth() {
        return bandwidth;
    }

    /**
     * Sets the value of the bandwidth property.
     * 
     */
    public void setBandwidth(int value) {
        this.bandwidth = value;
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
     * Gets the value of the dialIn property.
     * 
     */
    public boolean isDialIn() {
        return dialIn;
    }

    /**
     * Sets the value of the dialIn property.
     * 
     */
    public void setDialIn(boolean value) {
        this.dialIn = value;
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
     * Gets the value of the email property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the value of the email property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEmail(String value) {
        this.email = value;
    }

    /**
     * Gets the value of the firstName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Sets the value of the firstName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFirstName(String value) {
        this.firstName = value;
    }

    /**
     * Gets the value of the for3G property.
     * 
     */
    public boolean isFor3G() {
        return for3G;
    }

    /**
     * Sets the value of the for3G property.
     * 
     */
    public void setFor3G(boolean value) {
        this.for3G = value;
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
     * Gets the value of the lastName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Sets the value of the lastName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLastName(String value) {
        this.lastName = value;
    }

    /**
     * Gets the value of the memberID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMemberID() {
        return memberID;
    }

    /**
     * Sets the value of the memberID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMemberID(String value) {
        this.memberID = value;
    }

    /**
     * Gets the value of the outsiderType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOutsiderType() {
        return outsiderType;
    }

    /**
     * Sets the value of the outsiderType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOutsiderType(String value) {
        this.outsiderType = value;
    }

    /**
     * Gets the value of the owner property.
     * 
     */
    public boolean isOwner() {
        return owner;
    }

    /**
     * Sets the value of the owner property.
     * 
     */
    public void setOwner(boolean value) {
        this.owner = value;
    }

    /**
     * Gets the value of the partyProtocolType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPartyProtocolType() {
        return partyProtocolType;
    }

    /**
     * Sets the value of the partyProtocolType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPartyProtocolType(String value) {
        this.partyProtocolType = value;
    }

    /**
     * Gets the value of the terminalAlias property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTerminalAlias() {
        return terminalAlias;
    }

    /**
     * Sets the value of the terminalAlias property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTerminalAlias(String value) {
        this.terminalAlias = value;
    }

    /**
     * Gets the value of the terminalAttendee property.
     * 
     */
    public boolean isTerminalAttendee() {
        return terminalAttendee;
    }

    /**
     * Sets the value of the terminalAttendee property.
     * 
     */
    public void setTerminalAttendee(boolean value) {
        this.terminalAttendee = value;
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
     * Gets the value of the userID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUserID() {
        return userID;
    }

    /**
     * Sets the value of the userID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUserID(String value) {
        this.userID = value;
    }

    /**
     * Gets the value of the viewID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getViewID() {
        return viewID;
    }

    /**
     * Sets the value of the viewID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setViewID(String value) {
        this.viewID = value;
    }

}
