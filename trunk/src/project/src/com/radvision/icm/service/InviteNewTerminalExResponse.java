
package com.radvision.icm.service;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for inviteNewTerminalExResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="inviteNewTerminalExResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="result" type="{http://radvision.com/icm/service/controlservice}controlResultEx" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "inviteNewTerminalExResponse", propOrder = {
    "result"
})
public class InviteNewTerminalExResponse {

    protected ControlResultEx result;

    /**
     * Gets the value of the result property.
     * 
     * @return
     *     possible object is
     *     {@link ControlResultEx }
     *     
     */
    public ControlResultEx getResult() {
        return result;
    }

    /**
     * Sets the value of the result property.
     * 
     * @param value
     *     allowed object is
     *     {@link ControlResultEx }
     *     
     */
    public void setResult(ControlResultEx value) {
        this.result = value;
    }

}
