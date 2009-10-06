
package com.radvision.icm.service;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for mcuResourceResult complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="mcuResourceResult">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="confNums" type="{http://www.w3.org/2001/XMLSchema}int" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="errorCode" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="errorInfo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="infos" type="{http://radvision.com/icm/service/resourceservice}mcuResourceInfo" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="success" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "mcuResourceResult", propOrder = {
    "confNums",
    "errorCode",
    "errorInfo",
    "infos",
    "success"
})
public class McuResourceResult {

    @XmlElement(nillable = true)
    protected List<Integer> confNums;
    protected int errorCode;
    protected String errorInfo;
    @XmlElement(nillable = true)
    protected List<McuResourceInfo> infos;
    protected boolean success;

    /**
     * Gets the value of the confNums property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the confNums property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getConfNums().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Integer }
     * 
     * 
     */
    public List<Integer> getConfNums() {
        if (confNums == null) {
            confNums = new ArrayList<Integer>();
        }
        return this.confNums;
    }

    /**
     * Gets the value of the errorCode property.
     * 
     */
    public int getErrorCode() {
        return errorCode;
    }

    /**
     * Sets the value of the errorCode property.
     * 
     */
    public void setErrorCode(int value) {
        this.errorCode = value;
    }

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
     * Gets the value of the infos property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the infos property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getInfos().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link McuResourceInfo }
     * 
     * 
     */
    public List<McuResourceInfo> getInfos() {
        if (infos == null) {
            infos = new ArrayList<McuResourceInfo>();
        }
        return this.infos;
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

}
