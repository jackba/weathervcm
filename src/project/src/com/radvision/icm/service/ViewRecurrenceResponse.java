
package com.radvision.icm.service;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for viewRecurrenceResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="viewRecurrenceResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="result" type="{http://radvision.com/icm/service/scheduleservice}recurrenceInfo" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "viewRecurrenceResponse", propOrder = {
    "result"
})
public class ViewRecurrenceResponse {

    protected RecurrenceInfo result;

    /**
     * Gets the value of the result property.
     * 
     * @return
     *     possible object is
     *     {@link RecurrenceInfo }
     *     
     */
    public RecurrenceInfo getResult() {
        return result;
    }

    /**
     * Sets the value of the result property.
     * 
     * @param value
     *     allowed object is
     *     {@link RecurrenceInfo }
     *     
     */
    public void setResult(RecurrenceInfo value) {
        this.result = value;
    }

}
