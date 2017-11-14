
package com.adaapa.tokenverifier;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.adaapa.tokenverifier package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _VerifyToken_QNAME = new QName("tokenverifier.adaapa.com", "verifyToken");
    private final static QName _VerifyTokenResponse_QNAME = new QName("tokenverifier.adaapa.com", "verifyTokenResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.adaapa.tokenverifier
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link VerifyToken }
     * 
     */
    public VerifyToken createVerifyToken() {
        return new VerifyToken();
    }

    /**
     * Create an instance of {@link VerifyTokenResponse }
     * 
     */
    public VerifyTokenResponse createVerifyTokenResponse() {
        return new VerifyTokenResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link VerifyToken }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "tokenverifier.adaapa.com", name = "verifyToken")
    public JAXBElement<VerifyToken> createVerifyToken(VerifyToken value) {
        return new JAXBElement<VerifyToken>(_VerifyToken_QNAME, VerifyToken.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link VerifyTokenResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "tokenverifier.adaapa.com", name = "verifyTokenResponse")
    public JAXBElement<VerifyTokenResponse> createVerifyTokenResponse(VerifyTokenResponse value) {
        return new JAXBElement<VerifyTokenResponse>(_VerifyTokenResponse_QNAME, VerifyTokenResponse.class, null, value);
    }

}
