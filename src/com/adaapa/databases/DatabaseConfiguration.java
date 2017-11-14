package com.adaapa.databases;

import java.io.InputStream;
import java.util.Properties;

public class DatabaseConfiguration {
  private String db_name;
  private String db_host;
  private String db_user;
  private String db_pass;
  public DatabaseConfiguration() {
    try {
      Properties prop = new Properties();
      InputStream inp = this.getClass().getResourceAsStream("/config/db.properties");
      prop.load(inp);
      db_name = prop.getProperty("DB_NAME");
      db_host = prop.getProperty("DB_HOST");
      db_pass = prop.getProperty("DB_PASS");
      db_user = prop.getProperty("DB_USER");
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public String getHost() {
    return db_host;
  }

  public String getName() {
    return db_name;
  }

  public String getPass() {
    return db_pass;
  }

  public String getUser() {
    return db_user;
  }
}
