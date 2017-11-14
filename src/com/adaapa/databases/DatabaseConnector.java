package com.adaapa.databases;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConnector {
  private static DatabaseConnector _instance;
  private Connection conn =null;

  private DatabaseConnector() {
    DatabaseConfiguration config;
    config = new DatabaseConfiguration();
    try {
      Class.forName("com.mysql.jdbc.Driver").newInstance();
      conn = DriverManager.getConnection(
          "jdbc:mysql://"+config.getHost()+"/"+config.getName(),config.getUser(),config.getPass());
    } catch (Exception e) {
      e.printStackTrace();
    }

  }
  public static DatabaseConnector getInstance() {
    if(_instance == null) {
      _instance =  new DatabaseConnector();
    }
    return _instance;
  }

  public Connection getConn() {
    return conn;
  }
}
