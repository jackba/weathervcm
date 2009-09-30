
package com.radvision.icm.service;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for modifyVirtualRoom complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="modifyVirtualRoom">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="virtualRoo" type="{http://radvision.com/icm/service/scheduleservice}virtualRoomInfoEx" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "modifyVirtualRoom", propOrder = {
    "virtualRoo"
})
public class ModifyVirtualRoom {

    protected VirtualRoomInfoEx virtualRoo;

    /**
     * Gets the value of the virtualRoo property.
     * 
     * @return
     *     possible object is
     *     {@link VirtualRoomInfoEx }
     *     
     */
    public VirtualRoomInfoEx getVirtualRoo() {
        return virtualRoo;
    }

    /**
     * Sets the value of the virtualRoo property.
     * 
     * @param value
     *     allowed object is
     *     {@link VirtualRoomInfoEx }
     *     
     */
    public void setVirtualRoo(VirtualRoomInfoEx value) {
        this.virtualRoo = value;
    }

}
