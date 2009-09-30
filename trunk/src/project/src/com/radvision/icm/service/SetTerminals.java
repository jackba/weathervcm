
package com.radvision.icm.service;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for setTerminals complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="setTerminals">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="terminals" type="{http://radvision.com/icm/service/resourceservice}terminalResource" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="overwritten" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "setTerminals", propOrder = {
    "terminals",
    "overwritten"
})
public class SetTerminals {

    protected List<TerminalResource> terminals;
    protected boolean overwritten;

    /**
     * Gets the value of the terminals property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the terminals property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getTerminals().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link TerminalResource }
     * 
     * 
     */
    public List<TerminalResource> getTerminals() {
        if (terminals == null) {
            terminals = new ArrayList<TerminalResource>();
        }
        return this.terminals;
    }

    /**
     * Gets the value of the overwritten property.
     * 
     */
    public boolean isOverwritten() {
        return overwritten;
    }

    /**
     * Sets the value of the overwritten property.
     * 
     */
    public void setOverwritten(boolean value) {
        this.overwritten = value;
    }

}
