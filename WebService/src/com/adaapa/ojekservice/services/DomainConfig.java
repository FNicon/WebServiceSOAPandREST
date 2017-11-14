package com.adaapa.ojekservice.services;

import java.io.InputStream;
import java.util.Properties;

public class DomainConfig {
  public static String IS_DOMAIN;
  private final String file_name = "/config/domain.properties";

  public DomainConfig() {
    try {
      Properties prop = new Properties();
      InputStream inp = this.getClass().getResourceAsStream(file_name);
      prop.load(inp);
      IS_DOMAIN = prop.getProperty("IDSERVICE_DOMAIN");
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
  public static String getISDomain() {
    if(IS_DOMAIN == null) {
      new DomainConfig();
    }
    return IS_DOMAIN;
  }
}
