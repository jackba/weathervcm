
package com.radvision.icm.service;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for searchConferences complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="searchConferences">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="condition" type="{http://radvision.com/icm/service/scheduleservice}conferenceInfoCondition" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "searchConferences", propOrder = {
    "condition"
})
public class SearchConferences {

    protected ConferenceInfoCondition condition;

    /**
     * Gets the value of the condition property.
     * 
     * @return
     *     possible object is
     *     {@link ConferenceInfoCondition }
     *     
     */
    public ConferenceInfoCondition getCondition() {
        return condition;
    }

    /**
     * Sets the value of the condition property.
     * 
     * @param value
     *     allowed object is
     *     {@link ConferenceInfoCondition }
     *     
     */
    public void setCondition(ConferenceInfoCondition value) {
        this.condition = value;
    }

}
