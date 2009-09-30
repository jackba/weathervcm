
package com.radvision.icm.service;

import javax.xml.ws.WebFault;


/**
 * This class was generated by the JAXWS SI.
 * JAX-WS RI 2.1-02/02/2007 03:56 AM(vivekp)-FCS
 * Generated source version: 2.1
 * 
 */
@WebFault(name = "UserNotExistedException", targetNamespace = "http://radvision.com/icm/service/userservice")
public class UserNotExistedException_Exception
    extends Exception
{

    /**
     * Java type that goes as soapenv:Fault detail element.
     * 
     */
    private UserNotExistedException faultInfo;

    /**
     * 
     * @param faultInfo
     * @param message
     */
    public UserNotExistedException_Exception(String message, UserNotExistedException faultInfo) {
        super(message);
        this.faultInfo = faultInfo;
    }

    /**
     * 
     * @param faultInfo
     * @param message
     * @param cause
     */
    public UserNotExistedException_Exception(String message, UserNotExistedException faultInfo, Throwable cause) {
        super(message, cause);
        this.faultInfo = faultInfo;
    }

    /**
     * 
     * @return
     *     returns fault bean: com.radvision.icm.service.UserNotExistedException
     */
    public UserNotExistedException getFaultInfo() {
        return faultInfo;
    }

}
