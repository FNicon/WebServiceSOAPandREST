package com.adaapa.adaojek.services;

import com.adaapa.ojekservice.OjekOnline;
import com.adaapa.ojekservice.OjekOnlineService;
import java.net.MalformedURLException;

public class ServiceConnector {
  private static ServiceConnector instance;
  private static String CONTEXT_PATH = "/OjekOnline";
  private static String WSDL_PATH = "?wsdl";
  private static String SERVICE_NAMESPACE = "http://ojekservice.adaapa.com/";
  private static String LOCAL_PATH = "OjekOnlineService";
  private static OjekOnline serviceClass;
  public ServiceConnector() throws MalformedURLException{
    OjekOnlineService service = new OjekOnlineService();
    serviceClass = service.getOjekOnlinePort();
  }

  public static OjekOnline getServiceClass() throws MalformedURLException {
    if(serviceClass == null) {
      instance = new ServiceConnector();
    }
    return serviceClass;
  }
}
