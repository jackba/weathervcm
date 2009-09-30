
package com.radvision.icm.service;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for controlResultEx complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="controlResultEx">
 *   &lt;complexContent>
 *     &lt;extension base="{http://radvision.com/icm/service/controlservice}controlResult">
 *       &lt;sequence>
 *         &lt;element name="phyConfId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "controlResultEx", propOrder = {
    "phyConfId"
})
public class ControlResultEx
    extends ControlResult
{

    protected String phyConfId;

    /**
     * Gets the value of the phyConfId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPhyConfId() {
        return phyConfId;
    }

    /**
     * Sets the value of the phyConfId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPhyConfId(String value) {
        this.phyConfId = value;
    }

}
