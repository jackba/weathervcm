
package com.radvision.icm.service;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for layoutInfo complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="layoutInfo">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="frameTitleShowedState" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="layoutId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="layoutType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="videoRole" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
@XmlType(name = "layoutInfo", propOrder = {
    "frameTitleShowedState",
    "layoutId",
    "layoutType",
    "videoRole",
    "viewId"
})
@XmlSeeAlso({
    ViewInfo.class
})
public class LayoutInfo {

    protected String frameTitleShowedState;
    protected String layoutId;
    protected String layoutType;
    protected String videoRole;
    protected String viewId;

    /**
     * Gets the value of the frameTitleShowedState property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFrameTitleShowedState() {
        return frameTitleShowedState;
    }

    /**
     * Sets the value of the frameTitleShowedState property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFrameTitleShowedState(String value) {
        this.frameTitleShowedState = value;
    }

    /**
     * Gets the value of the layoutId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLayoutId() {
        return layoutId;
    }

    /**
     * Sets the value of the layoutId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLayoutId(String value) {
        this.layoutId = value;
    }

    /**
     * Gets the value of the layoutType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLayoutType() {
        return layoutType;
    }

    /**
     * Sets the value of the layoutType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLayoutType(String value) {
        this.layoutType = value;
    }

    /**
     * Gets the value of the videoRole property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVideoRole() {
        return videoRole;
    }

    /**
     * Sets the value of the videoRole property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVideoRole(String value) {
        this.videoRole = value;
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
