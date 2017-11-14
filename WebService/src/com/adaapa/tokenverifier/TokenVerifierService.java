
package com.adaapa.tokenverifier;

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
@WebServiceClient(name = "TokenVerifierService", targetNamespace = "tokenverifier.adaapa.com", wsdlLocation = "http://localhost:8001/verifytoken?wsdl")
public class TokenVerifierService
    extends Service
{

    private final static URL TOKENVERIFIERSERVICE_WSDL_LOCATION;
    private final static WebServiceException TOKENVERIFIERSERVICE_EXCEPTION;
    private final static QName TOKENVERIFIERSERVICE_QNAME = new QName("tokenverifier.adaapa.com", "TokenVerifierService");

    static {
        URL url = null;
        WebServiceException e = null;
        try {
            url = new URL("http://localhost:8001/verifytoken?wsdl");
        } catch (MalformedURLException ex) {
            e = new WebServiceException(ex);
        }
        TOKENVERIFIERSERVICE_WSDL_LOCATION = url;
        TOKENVERIFIERSERVICE_EXCEPTION = e;
    }

    public TokenVerifierService() {
        super(__getWsdlLocation(), TOKENVERIFIERSERVICE_QNAME);
    }

    public TokenVerifierService(WebServiceFeature... features) {
        super(__getWsdlLocation(), TOKENVERIFIERSERVICE_QNAME, features);
    }

    public TokenVerifierService(URL wsdlLocation) {
        super(wsdlLocation, TOKENVERIFIERSERVICE_QNAME);
    }

    public TokenVerifierService(URL wsdlLocation, WebServiceFeature... features) {
        super(wsdlLocation, TOKENVERIFIERSERVICE_QNAME, features);
    }

    public TokenVerifierService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public TokenVerifierService(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     * 
     * @return
     *     returns TokenVerifier
     */
    @WebEndpoint(name = "TokenVerifierPort")
    public TokenVerifier getTokenVerifierPort() {
        return super.getPort(new QName("tokenverifier.adaapa.com", "TokenVerifierPort"), TokenVerifier.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns TokenVerifier
     */
    @WebEndpoint(name = "TokenVerifierPort")
    public TokenVerifier getTokenVerifierPort(WebServiceFeature... features) {
        return super.getPort(new QName("tokenverifier.adaapa.com", "TokenVerifierPort"), TokenVerifier.class, features);
    }

    private static URL __getWsdlLocation() {
        if (TOKENVERIFIERSERVICE_EXCEPTION!= null) {
            throw TOKENVERIFIERSERVICE_EXCEPTION;
        }
        return TOKENVERIFIERSERVICE_WSDL_LOCATION;
    }

}