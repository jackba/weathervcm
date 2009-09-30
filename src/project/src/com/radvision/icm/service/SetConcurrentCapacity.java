
package com.radvision.icm.service;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for setConcurrentCapacity complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="setConcurrentCapacity">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="capacityStr" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "setConcurrentCapacity", propOrder = {
    "capacityStr"
})
public class SetConcurrentCapacity {

    protected String capacityStr;

    /**
     * Gets the value of the capacityStr property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCapacityStr() {
        return capacityStr;
    }

    /**
     * Sets the value of the capacityStr property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCapacityStr(String value) {
        this.capacityStr = value;
    }

}
