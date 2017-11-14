package com.adaapa.ojekservice.services;

import com.adaapa.bean.TokenVerificationResponse;
import com.adaapa.bean.UserBean;
import com.adaapa.bean.WebServiceBean;
import com.adaapa.tokenverifier.TokenVerifier;
import com.google.gson.Gson;
import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;

public class TokenVerificationService {
  private final String TOKEN_VERIFIER_PATH = "/verifytoken";
  private final String WSDL_PATH = "?wsdl";
  private static TokenVerifier serviceClass;
  TokenVerificationService() throws MalformedURLException {
    URL url = new URL(DomainConfig.getISDomain()+TOKEN_VERIFIER_PATH+WSDL_PATH);
    QName qname = new QName("tokenverifier.adaapa.com", "TokenVerifierService");
    Service service = Service.create(url, qname);
    serviceClass = service.getPort(TokenVerifier.class);
  }
  public static UserBean verifyToken(String access_token) throws MalformedURLException {
    if(serviceClass == null) {
      new TokenVerificationService();
    }
    String result = serviceClass.verifyToken(access_token);
    TokenVerificationResponse resultObj = new Gson().fromJson(result,TokenVerificationResponse.class);
    WebServiceBean response = new WebServiceBean();
    if(resultObj.getStatus().equals(TokenVerificationResponse.TOKEN_VALID)) {
      return resultObj.getUser();
    } else {
      return null;
    }
  }
}
