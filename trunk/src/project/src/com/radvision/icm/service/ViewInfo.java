
package com.radvision.icm.service;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for viewInfo complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="viewInfo">
 *   &lt;complexContent>
 *     &lt;extension base="{http://radvision.com/icm/service/scheduleservice}layoutInfo">
 *       &lt;sequence>
 *         &lt;element name="dynamic" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="initialLayoutID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="maxLayoutID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "viewInfo", propOrder = {
    "dynamic",
    "initialLayoutID",
    "maxLayoutID"
})
public class ViewInfo
    extends LayoutInfo
{

    protected boolean dynamic;
    protected String initialLayoutID;
    protected String maxLayoutID;

    /**
     * Gets the value of the dynamic property.
     * 
     */
    public boolean isDynamic() {
        return dynamic;
    }

    /**
     * Sets the value of the dynamic property.
     * 
     */
    public void setDynamic(boolean value) {
        this.dynamic = value;
    }

    /**
     * Gets the value of the initialLayoutID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getInitialLayoutID() {
        return initialLayoutID;
    }

    /**
     * Sets the value of the initialLayoutID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setInitialLayoutID(String value) {
        this.initialLayoutID = value;
    }

    /**
     * Gets the value of the maxLayoutID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMaxLayoutID() {
        return maxLayoutID;
    }

    /**
     * Sets the value of the maxLayoutID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMaxLayoutID(String value) {
        this.maxLayoutID = value;
    }

}
