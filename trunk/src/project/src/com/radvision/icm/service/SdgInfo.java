
package com.radvision.icm.service;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for sdgInfo complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="sdgInfo">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="SDGAccessInfos" type="{http://radvision.com/icm/service/scheduleservice}sdgAccessInfo" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "sdgInfo", propOrder = {
    "sdgAccessInfos"
})
public class SdgInfo {

    @XmlElement(name = "SDGAccessInfos", nillable = true)
    protected List<SdgAccessInfo> sdgAccessInfos;

    /**
     * Gets the value of the sdgAccessInfos property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the sdgAccessInfos property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSDGAccessInfos().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SdgAccessInfo }
     * 
     * 
     */
    public List<SdgAccessInfo> getSDGAccessInfos() {
        if (sdgAccessInfos == null) {
            sdgAccessInfos = new ArrayList<SdgAccessInfo>();
        }
        return this.sdgAccessInfos;
    }

}
