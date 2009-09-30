
package com.radvision.icm.service;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for view complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="view">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="initialVideoLayout" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="maxVideoLayout" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="viewId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "view", propOrder = {
    "initialVideoLayout",
    "maxVideoLayout",
    "viewId"
})
public class View {

    protected String initialVideoLayout;
    protected String maxVideoLayout;
    protected String viewId;

    /**
     * Gets the value of the initialVideoLayout property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getInitialVideoLayout() {
        return initialVideoLayout;
    }

    /**
     * Sets the value of the initialVideoLayout property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setInitialVideoLayout(String value) {
        this.initialVideoLayout = value;
    }

    /**
     * Gets the value of the maxVideoLayout property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMaxVideoLayout() {
        return maxVideoLayout;
    }

    /**
     * Sets the value of the maxVideoLayout property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMaxVideoLayout(String value) {
        this.maxVideoLayout = value;
    }

    /**
     * Gets the value of the viewId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getViewId() {
        return viewId;
    }

    /**
     * Sets the value of the viewId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setViewId(String value) {
        this.viewId = value;
    }

}
