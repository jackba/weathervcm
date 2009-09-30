
package com.radvision.icm.service;

import javax.xml.ws.WebFault;


/**
 * This class was generated by the JAXWS SI.
 * JAX-WS RI 2.1-02/02/2007 03:56 AM(vivekp)-FCS
 * Generated source version: 2.1
 * 
 */
@WebFault(name = "CapacityException", targetNamespace = "http://radvision.com/icm/service/licenseservice")
public class CapacityException_Exception
    extends Exception
{

    /**
     * Java type that goes as soapenv:Fault detail element.
     * 
     */
    private CapacityException faultInfo;

    /**
     * 
     * @param faultInfo
     * @param message
     */
    public CapacityException_Exception(String message, CapacityException faultInfo) {
        super(message);
        this.faultInfo = faultInfo;
    }

    /**
     * 
     * @param faultInfo
     * @param message
     * @param cause
     */
    public CapacityException_Exception(String message, CapacityException faultInfo, Throwable cause) {
        super(message, cause);
        this.faultInfo = faultInfo;
    }

    /**
     * 
     * @return
     *     returns fault bean: com.radvision.icm.service.CapacityException
     */
    public CapacityException getFaultInfo() {
        return faultInfo;
    }

}
