
package com.radvision.icm.service;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for sdgAccessInfo complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="sdgAccessInfo">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="SDGAccessURL" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "sdgAccessInfo", propOrder = {
    "sdgAccessURL"
})
public class SdgAccessInfo {

    @XmlElement(name = "SDGAccessURL")
    protected String sdgAccessURL;

    /**
     * Gets the value of the sdgAccessURL property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSDGAccessURL() {
        return sdgAccessURL;
    }

    /**
     * Sets the value of the sdgAccessURL property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSDGAccessURL(String value) {
        this.sdgAccessURL = value;
    }

}
