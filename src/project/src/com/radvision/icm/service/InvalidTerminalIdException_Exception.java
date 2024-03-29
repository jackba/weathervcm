
package com.radvision.icm.service;

import javax.xml.ws.WebFault;


/**
 * This class was generated by the JAXWS SI.
 * JAX-WS RI 2.1-02/02/2007 03:56 AM(vivekp)-FCS
 * Generated source version: 2.1
 * 
 */
@WebFault(name = "InvalidTerminalIdException", targetNamespace = "http://radvision.com/icm/service/scheduleservice")
public class InvalidTerminalIdException_Exception
    extends Exception
{

    /**
     * Java type that goes as soapenv:Fault detail element.
     * 
     */
    private InvalidTerminalIdException faultInfo;

    /**
     * 
     * @param faultInfo
     * @param message
     */
    public InvalidTerminalIdException_Exception(String message, InvalidTerminalIdException faultInfo) {
        super(message);
        this.faultInfo = faultInfo;
    }

    /**
     * 
     * @param faultInfo
     * @param message
     * @param cause
     */
    public InvalidTerminalIdException_Exception(String message, InvalidTerminalIdException faultInfo, Throwable cause) {
        super(message, cause);
        this.faultInfo = faultInfo;
    }

    /**
     * 
     * @return
     *     returns fault bean: com.radvision.icm.service.InvalidTerminalIdException
     */
    public InvalidTerminalIdException getFaultInfo() {
        return faultInfo;
    }

}
