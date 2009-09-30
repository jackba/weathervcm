
package com.radvision.icm.service;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for searchUsers complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="searchUsers">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="userSearchCondition" type="{http://radvision.com/icm/service/userservice}userSearchConditon" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "searchUsers", propOrder = {
    "userSearchCondition"
})
public class SearchUsers {

    protected UserSearchConditon userSearchCondition;

    /**
     * Gets the value of the userSearchCondition property.
     * 
     * @return
     *     possible object is
     *     {@link UserSearchConditon }
     *     
     */
    public UserSearchConditon getUserSearchCondition() {
        return userSearchCondition;
    }

    /**
     * Sets the value of the userSearchCondition property.
     * 
     * @param value
     *     allowed object is
     *     {@link UserSearchConditon }
     *     
     */
    public void setUserSearchCondition(UserSearchConditon value) {
        this.userSearchCondition = value;
    }

}
