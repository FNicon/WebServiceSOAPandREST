package com.adaapa.adaojek.services;

import java.io.InputStream;
import java.util.Properties;

public class DomainConfig {
  private static final String file_name  = "/config/domain.properties";
  private static DomainConfig _instance = null;
  private String is_domain;
  private String ws_domain;

  public DomainConfig () {
    try {
      Properties prop = new Properties();
      InputStream inp = this.getClass().getResourceAsStream(file_name);
      prop.load(inp);
      is_domain = prop.getProperty("IDSERVICE_DOMAIN");
      ws_domain = prop.getProperty("WEBSERVICE_DOMAIN");
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public static DomainConfig getInstance() {
    if(_instance == null) {
      _instance =  new DomainConfig();
    }
    return _instance;
  }

  public String getISDomain() {
    return is_domain;
  }
  public String getWSDomain() {
    return ws_domain;
  }

}
