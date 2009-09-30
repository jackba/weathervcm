
package com.radvision.icm.service;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for meetingType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="meetingType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="bandwidth" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="builtInToken" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="description" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="layouts" type="{http://radvision.com/icm/service/resourceservice}layout" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="mcuService" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="name" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="resolution" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="servicePrefix" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="switchingMode" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="views" type="{http://radvision.com/icm/service/resourceservice}view" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "meetingType", propOrder = {
    "bandwidth",
    "builtInToken",
    "description",
    "id",
    "layouts",
    "mcuService",
    "name",
    "resolution",
    "servicePrefix",
    "switchingMode",
    "views"
})
public class MeetingType {

    protected int bandwidth;
    protected String builtInToken;
    protected String description;
    protected String id;
    @XmlElement(nillable = true)
    protected List<Layout> layouts;
    protected int mcuService;
    protected String name;
    protected String resolution;
    protected String servicePrefix;
    protected int switchingMode;
    @XmlElement(nillable = true)
    protected List<View> views;

    /**
     * Gets the value of the bandwidth property.
     * 
     */
    public int getBandwidth() {
        return bandwidth;
    }

    /**
     * Sets the value of the bandwidth property.
     * 
     */
    public void setBandwidth(int value) {
        this.bandwidth = value;
    }

    /**
     * Gets the value of the builtInToken property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBuiltInToken() {
        return builtInToken;
    }

    /**
     * Sets the value of the builtInToken property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBuiltInToken(String value) {
        this.builtInToken = value;
    }

    /**
     * Gets the value of the description property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the value of the description property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescription(String value) {
        this.description = value;
    }

    /**
     * Gets the value of the id property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getId() {
        return id;
    }

    /**
     * Sets the value of the id property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setId(String value) {
        this.id = value;
    }

    /**
     * Gets the value of the layouts property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the layouts property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getLayouts().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Layout }
     * 
     * 
     */
    public List<Layout> getLayouts() {
        if (layouts == null) {
            layouts = new ArrayList<Layout>();
        }
        return this.layouts;
    }

    /**
     * Gets the value of the mcuService property.
     * 
     */
    public int getMcuService() {
        return mcuService;
    }

    /**
     * Sets the value of the mcuService property.
     * 
     */
    public void setMcuService(int value) {
        this.mcuService = value;
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
     * Gets the value of the resolution property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getResolution() {
        return resolution;
    }

    /**
     * Sets the value of the resolution property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setResolution(String value) {
        this.resolution = value;
    }

    /**
     * Gets the value of the servicePrefix property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getServicePrefix() {
        return servicePrefix;
    }

    /**
     * Sets the value of the servicePrefix property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setServicePrefix(String value) {
        this.servicePrefix = value;
    }

    /**
     * Gets the value of the switchingMode property.
     * 
     */
    public int getSwitchingMode() {
        return switchingMode;
    }

    /**
     * Sets the value of the switchingMode property.
     * 
     */
    public void setSwitchingMode(int value) {
        this.switchingMode = value;
    }

    /**
     * Gets the value of the views property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the views property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getViews().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link View }
     * 
     * 
     */
    public List<View> getViews() {
        if (views == null) {
            views = new ArrayList<View>();
        }
        return this.views;
    }

}
