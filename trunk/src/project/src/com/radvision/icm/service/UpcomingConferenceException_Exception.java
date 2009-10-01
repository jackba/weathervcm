
package com.radvision.icm.service;

import javax.xml.ws.WebFault;


/**
 * This class was generated by the JAXWS SI.
 * JAX-WS RI 2.1-02/02/2007 03:56 AM(vivekp)-FCS
 * Generated source version: 2.1
 * 
 */
@WebFault(name = "UpcomingConferenceException", targetNamespace = "http://radvision.com/icm/service/scheduleservice")
public class UpcomingConferenceException_Exception
    extends Exception
{

    /**
     * Java type that goes as soapenv:Fault detail element.
     * 
     */
    private UpcomingConferenceException faultInfo;

    /**
     * 
     * @param faultInfo
     * @param message
     */
    public UpcomingConferenceException_Exception(String message, UpcomingConferenceException faultInfo) {
        super(message);
        this.faultInfo = faultInfo;
    }

    /**
     * 
     * @param faultInfo
     * @param message
     * @param cause
     */
    public UpcomingConferenceException_Exception(String message, UpcomingConferenceException faultInfo, Throwable cause) {
        super(message, cause);
        this.faultInfo = faultInfo;
    }

    /**
     * 
     * @return
     *     returns fault bean: com.radvision.icm.service.UpcomingConferenceException
     */
    public UpcomingConferenceException getFaultInfo() {
        return faultInfo;
    }

}