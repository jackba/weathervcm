
package com.radvision.icm.service;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for virtualRoomInfo complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="virtualRoomInfo">
 *   &lt;complexContent>
 *     &lt;extension base="{http://radvision.com/icm/service/scheduleservice}conferenceInfo">
 *       &lt;sequence>
 *         &lt;element name="gatewayInfo" type="{http://radvision.com/icm/service/scheduleservice}gatewayInfo" minOccurs="0"/>
 *         &lt;element name="name" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="public" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="virtualRoomNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "virtualRoomInfo", propOrder = {
    "gatewayInfo",
    "name",
    "_public",
    "virtualRoomNumber"
})
public class VirtualRoomInfo
    extends ConferenceInfo
{

    protected GatewayInfo gatewayInfo;
    protected String name;
    @XmlElement(name = "public")
    protected boolean _public;
    protected String virtualRoomNumber;

    /**
     * Gets the value of the gatewayInfo property.
     * 
     * @return
     *     possible object is
     *     {@link GatewayInfo }
     *     
     */
    public GatewayInfo getGatewayInfo() {
        return gatewayInfo;
    }

    /**
     * Sets the value of the gatewayInfo property.
     * 
     * @param value
     *     allowed object is
     *     {@link GatewayInfo }
     *     
     */
    public void setGatewayInfo(GatewayInfo value) {
        this.gatewayInfo = value;
    }

    /**
     * Gets the value of the name property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the value of the name property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setName(String value) {
        this.name = value;
    }

    /**
     * Gets the value of the public property.
     * 
     */
    public boolean isPublic() {
        return _public;
    }

    /**
     * Sets the value of the public property.
     * 
     */
    public void setPublic(boolean value) {
        this._public = value;
    }

    /**
     * Gets the value of the virtualRoomNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVirtualRoomNumber() {
        return virtualRoomNumber;
    }

    /**
     * Sets the value of the virtualRoomNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVirtualRoomNumber(String value) {
        this.virtualRoomNumber = value;
    }

}
