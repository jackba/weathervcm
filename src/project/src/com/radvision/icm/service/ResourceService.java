
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
@WebServiceClient(name = "ResourceService", targetNamespace = "http://radvision.com/icm/service/resourceservice", wsdlLocation = "file:/D:/Radvision/Product/icmservice/training/wsimport/wsdl/ResourceService.wsdl")
public class ResourceService
    extends Service
{

    private final static URL RESOURCESERVICE_WSDL_LOCATION;

    static {
        URL url = null;
        try {
            url = new URL("file:/D:/Radvision/Product/icmservice/training/wsimport/wsdl/ResourceService.wsdl");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        RESOURCESERVICE_WSDL_LOCATION = url;
    }

    public ResourceService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public ResourceService() {
        super(RESOURCESERVICE_WSDL_LOCATION, new QName("http://radvision.com/icm/service/resourceservice", "ResourceService"));
    }

    /**
     * 
     * @return
     *     returns ResourceServicePortType
     */
    @WebEndpoint(name = "ResourceServicePort")
    public ResourceServicePortType getResourceServicePort() {
        return (ResourceServicePortType)super.getPort(new QName("http://radvision.com/icm/service/resourceservice", "ResourceServicePort"), ResourceServicePortType.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns ResourceServicePortType
     */
    @WebEndpoint(name = "ResourceServicePort")
    public ResourceServicePortType getResourceServicePort(WebServiceFeature... features) {
        return (ResourceServicePortType)super.getPort(new QName("http://radvision.com/icm/service/resourceservice", "ResourceServicePort"), ResourceServicePortType.class, features);
    }

}
