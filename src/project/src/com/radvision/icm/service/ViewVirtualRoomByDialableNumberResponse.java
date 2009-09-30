
package com.radvision.icm.service;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for viewVirtualRoomByDialableNumberResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="viewVirtualRoomByDialableNumberResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="result" type="{http://radvision.com/icm/service/scheduleservice}virtualRoomInfo" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "viewVirtualRoomByDialableNumberResponse", propOrder = {
    "result"
})
public class ViewVirtualRoomByDialableNumberResponse {

    protected VirtualRoomInfo result;

    /**
     * Gets the value of the result property.
     * 
     * @return
     *     possible object is
     *     {@link VirtualRoomInfo }
     *     
     */
    public VirtualRoomInfo getResult() {
        return result;
    }

    /**
     * Sets the value of the result property.
     * 
     * @param value
     *     allowed object is
     *     {@link VirtualRoomInfo }
     *     
     */
    public void setResult(VirtualRoomInfo value) {
        this.result = value;
    }

}
