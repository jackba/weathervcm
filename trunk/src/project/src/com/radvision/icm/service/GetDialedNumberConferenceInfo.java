
package com.radvision.icm.service;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for getDialedNumberConferenceInfo complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="getDialedNumberConferenceInfo">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="dialableNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getDialedNumberConferenceInfo", propOrder = {
    "dialableNumber"
})
public class GetDialedNumberConferenceInfo {

    protected String dialableNumber;

    /**
     * Gets the value of the dialableNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDialableNumber() {
        return dialableNumber;
    }

    /**
     * Sets the value of the dialableNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDialableNumber(String value) {
        this.dialableNumber = value;
    }

}
