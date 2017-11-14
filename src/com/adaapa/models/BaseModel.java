package com.adaapa.models;

import com.adaapa.databases.DatabaseConnector;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public abstract class BaseModel {
  protected Connection db;
  protected String tableName;
  protected String primaryKey;

  public
  BaseModel(String tableName, String primaryKey) {
    db = DatabaseConnector.getInstance().getConn();
    this.tableName = tableName;
    this.primaryKey = primaryKey;
  }

  public ResultSet query(String query) throws SQLException {
    Statement stm = db.createStatement();
    stm.executeQuery(query);
    return stm.getResultSet();
  }
  public ResultSet queryUpdate(String query) throws SQLException {
    Statement stm = db.createStatement();
    stm.executeUpdate(query);
    return stm.getResultSet();
  }

  public ResultSet find(Integer id) throws SQLException
  {
    return query(String.format("SELECT * FROM %s WHERE %s = %d", tableName, primaryKey, id));
  }
}
