
package com.radvision.icm.service;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for conferenceAdvancedInfo complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="conferenceAdvancedInfo">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="allowDynamicGrow" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="durationAfterLeft" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="enableMCUCascading" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="terminationCondition" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "conferenceAdvancedInfo", propOrder = {
    "allowDynamicGrow",
    "durationAfterLeft",
    "enableMCUCascading",
    "terminationCondition"
})
public class ConferenceAdvancedInfo {

    protected boolean allowDynamicGrow;
    protected int durationAfterLeft;
    protected boolean enableMCUCascading;
    protected int terminationCondition;

    /**
     * Gets the value of the allowDynamicGrow property.
     * 
     */
    public boolean isAllowDynamicGrow() {
        return allowDynamicGrow;
    }

    /**
     * Sets the value of the allowDynamicGrow property.
     * 
     */
    public void setAllowDynamicGrow(boolean value) {
        this.allowDynamicGrow = value;
    }

    /**
     * Gets the value of the durationAfterLeft property.
     * 
     */
    public int getDurationAfterLeft() {
        return durationAfterLeft;
    }

    /**
     * Sets the value of the durationAfterLeft property.
     * 
     */
    public void setDurationAfterLeft(int value) {
        this.durationAfterLeft = value;
    }

    /**
     * Gets the value of the enableMCUCascading property.
     * 
     */
    public boolean isEnableMCUCascading() {
        return enableMCUCascading;
    }

    /**
     * Sets the value of the enableMCUCascading property.
     * 
     */
    public void setEnableMCUCascading(boolean value) {
        this.enableMCUCascading = value;
    }

    /**
     * Gets the value of the terminationCondition property.
     * 
     */
    public int getTerminationCondition() {
        return terminationCondition;
    }

    /**
     * Sets the value of the terminationCondition property.
     * 
     */
    public void setTerminationCondition(int value) {
        this.terminationCondition = value;
    }

}
