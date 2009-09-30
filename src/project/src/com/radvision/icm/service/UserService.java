
package com.radvision.icm.service;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceFeature;


/**
 * This class was generated by the JAXWS SI.
 * JAX-WS RI 2.1-02/02/2007 03:56 AM(vivekp)-FCS
 * Generated source version: 2.1
 * 
 */
@WebServiceClient(name = "UserService", targetNamespace = "http://radvision.com/icm/service/userservice", wsdlLocation = "file:/D:/Radvision/Product/icmservice/training/wsimport/wsdl/UserService.wsdl")
public class UserService
    extends Service
{

    private final static URL USERSERVICE_WSDL_LOCATION;

    static {
        URL url = null;
        try {
            url = new URL("file:/D:/Radvision/Product/icmservice/training/wsimport/wsdl/UserService.wsdl");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        USERSERVICE_WSDL_LOCATION = url;
    }

    public UserService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public UserService() {
        super(USERSERVICE_WSDL_LOCATION, new QName("http://radvision.com/icm/service/userservice", "UserService"));
    }

    /**
     * 
     * @return
     *     returns UserServicePortType
     */
    @WebEndpoint(name = "UserServicePort")
    public UserServicePortType getUserServicePort() {
        return (UserServicePortType)super.getPort(new QName("http://radvision.com/icm/service/userservice", "UserServicePort"), UserServicePortType.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns UserServicePortType
     */
    @WebEndpoint(name = "UserServicePort")
    public UserServicePortType getUserServicePort(WebServiceFeature... features) {
        return (UserServicePortType)super.getPort(new QName("http://radvision.com/icm/service/userservice", "UserServicePort"), UserServicePortType.class, features);
    }

}
