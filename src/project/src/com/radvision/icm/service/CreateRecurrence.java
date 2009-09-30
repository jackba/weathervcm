
package com.radvision.icm.service;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for createRecurrence complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="createRecurrence">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="recurrenceInfo" type="{http://radvision.com/icm/service/scheduleservice}recurrenceInfo" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "createRecurrence", propOrder = {
    "recurrenceInfo"
})
public class CreateRecurrence {

    protected RecurrenceInfo recurrenceInfo;

    /**
     * Gets the value of the recurrenceInfo property.
     * 
     * @return
     *     possible object is
     *     {@link RecurrenceInfo }
     *     
     */
    public RecurrenceInfo getRecurrenceInfo() {
        return recurrenceInfo;
    }

    /**
     * Sets the value of the recurrenceInfo property.
     * 
     * @param value
     *     allowed object is
     *     {@link RecurrenceInfo }
     *     
     */
    public void setRecurrenceInfo(RecurrenceInfo value) {
        this.recurrenceInfo = value;
    }

}
