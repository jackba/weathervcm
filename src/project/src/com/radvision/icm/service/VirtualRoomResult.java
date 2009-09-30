
package com.radvision.icm.service;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for virtualRoomResult complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="virtualRoomResult">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="errorInfo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="errorStatus" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="success" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="virtualRoomID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="virtualRoomInfo" type="{http://radvision.com/icm/service/scheduleservice}virtualRoomInfoEx" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "virtualRoomResult", propOrder = {
    "errorInfo",
    "errorStatus",
    "success",
    "virtualRoomID",
    "virtualRoomInfo"
})
public class VirtualRoomResult {

    protected String errorInfo;
    protected int errorStatus;
    protected boolean success;
    protected String virtualRoomID;
    protected VirtualRoomInfoEx virtualRoomInfo;

    /**
     * Gets the value of the errorInfo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getErrorInfo() {
        return errorInfo;
    }

    /**
     * Sets the value of the errorInfo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setErrorInfo(String value) {
        this.errorInfo = value;
    }

    /**
     * Gets the value of the errorStatus property.
     * 
     */
    public int getErrorStatus() {
        return errorStatus;
    }

    /**
     * Sets the value of the errorStatus property.
     * 
     */
    public void setErrorStatus(int value) {
        this.errorStatus = value;
    }

    /**
     * Gets the value of the success property.
     * 
     */
    public boolean isSuccess() {
        return success;
    }

    /**
     * Sets the value of the success property.
     * 
     */
    public void setSuccess(boolean value) {
        this.success = value;
    }

    /**
     * Gets the value of the virtualRoomID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVirtualRoomID() {
        return virtualRoomID;
    }

    /**
     * Sets the value of the virtualRoomID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVirtualRoomID(String value) {
        this.virtualRoomID = value;
    }

    /**
     * Gets the value of the virtualRoomInfo property.
     * 
     * @return
     *     possible object is
     *     {@link VirtualRoomInfoEx }
     *     
     */
    public VirtualRoomInfoEx getVirtualRoomInfo() {
        return virtualRoomInfo;
    }

    /**
     * Sets the value of the virtualRoomInfo property.
     * 
     * @param value
     *     allowed object is
     *     {@link VirtualRoomInfoEx }
     *     
     */
    public void setVirtualRoomInfo(VirtualRoomInfoEx value) {
        this.virtualRoomInfo = value;
    }

}
