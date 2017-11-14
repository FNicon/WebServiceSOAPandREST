
package com.adaapa.ojekservice;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceException;
import javax.xml.ws.WebServiceFeature;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.9-b130926.1035
 * Generated source version: 2.2
 * 
 */
@WebServiceClient(name = "OjekOnlineService", targetNamespace = "http://ojekservice.adaapa.com/", wsdlLocation = "http://localhost:8002/OjekOnline?wsdl")
public class OjekOnlineService
    extends Service
{

    private final static URL OJEKONLINESERVICE_WSDL_LOCATION;
    private final static WebServiceException OJEKONLINESERVICE_EXCEPTION;
    private final static QName OJEKONLINESERVICE_QNAME = new QName("http://ojekservice.adaapa.com/", "OjekOnlineService");

    static {
        URL url = null;
        WebServiceException e = null;
        try {
            url = new URL("http://localhost:8002/OjekOnline?wsdl");
        } catch (MalformedURLException ex) {
            e = new WebServiceException(ex);
        }
        OJEKONLINESERVICE_WSDL_LOCATION = url;
        OJEKONLINESERVICE_EXCEPTION = e;
    }

    public OjekOnlineService() {
        super(__getWsdlLocation(), OJEKONLINESERVICE_QNAME);
    }

    public OjekOnlineService(WebServiceFeature... features) {
        super(__getWsdlLocation(), OJEKONLINESERVICE_QNAME, features);
    }

    public OjekOnlineService(URL wsdlLocation) {
        super(wsdlLocation, OJEKONLINESERVICE_QNAME);
    }

    public OjekOnlineService(URL wsdlLocation, WebServiceFeature... features) {
        super(wsdlLocation, OJEKONLINESERVICE_QNAME, features);
    }

    public OjekOnlineService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public OjekOnlineService(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     * 
     * @return
     *     returns OjekOnline
     */
    @WebEndpoint(name = "OjekOnlinePort")
    public OjekOnline getOjekOnlinePort() {
        return super.getPort(new QName("http://ojekservice.adaapa.com/", "OjekOnlinePort"), OjekOnline.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns OjekOnline
     */
    @WebEndpoint(name = "OjekOnlinePort")
    public OjekOnline getOjekOnlinePort(WebServiceFeature... features) {
        return super.getPort(new QName("http://ojekservice.adaapa.com/", "OjekOnlinePort"), OjekOnline.class, features);
    }

    private static URL __getWsdlLocation() {
        if (OJEKONLINESERVICE_EXCEPTION!= null) {
            throw OJEKONLINESERVICE_EXCEPTION;
        }
        return OJEKONLINESERVICE_WSDL_LOCATION;
    }

}