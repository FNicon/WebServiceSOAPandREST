package com.adaapa.identityservice.models;

import com.adaapa.bean.UserBean;
import com.adaapa.models.BaseModel;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

public class UserModel extends BaseModel {
  Connection conn;
  public UserModel(){
    super("users","id");
  }

  public UserBean parseResultSet(ResultSet rs) throws SQLException {
    UserBean res = new UserBean();
    res.id = rs.getInt("id");
    res.name = rs.getString("name");
    res.email = rs.getString("email");
    res.username = rs.getString("username");
    res.phoneNumber = rs.getString("phone_number");
    return res;
  }

  public UserBean findUser(Integer id) {
    UserBean res = null;

    try {
      ResultSet rs = find(id);
      if (rs.next()) {
        res = parseResultSet(rs);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return res;
  }
  public UserBean findUser(String username) {
    UserBean res = null;
    try {
      ResultSet rs = query(String.format("SELECT * FROM %s WHERE username = '%s'", tableName, username ));;
      if (rs.next()) {
        res = parseResultSet(rs);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return res;
  }
  public UserBean findUserByEmail(String email) {
    UserBean res = null;
    try {
      ResultSet rs = query(String.format("SELECT * FROM %s WHERE email = '%s'", tableName, email));
      ;
      if (rs.next()) {
        res = parseResultSet(rs);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return res;
  }

  /**
   *
   * @param username Username yang diterima oleh servlet
   * @param password Password yang diterima oleh servlet
   * @return UserBean object jika terautentikasi, null jika tidak
   */
  public UserBean authenticate(String username, String password) {
    UserBean ubean = null;
    try {
      ResultSet rs = query(String.format(
          "SELECT * FROM %s WHERE username = '%s' and password = '%s'",
          tableName, username, password));
      if(rs.next()) {
        ubean = parseResultSet(rs);
      }
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      return ubean;

    }
  }

  /**
   * Menyimpan token yang telah digenerate
   * @param token token yang sudah digenerate oleh generate token
   * @return true jika token berhasil disimpan, false jika tidak berhasil
   */
  public Boolean saveToken(Integer userId, String token) {
    try {
      ResultSet rs = queryUpdate(String.format(
          "UPDATE %s SET last_token='%s',token_expire=DATE_ADD(now(), INTERVAL 7 DAY) where id=%d", this.tableName, token, userId
      ));
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return true;
  }

  public Boolean deleteToken(String token) {
    try {
      ResultSet rs = queryUpdate(String.format(
         "UPDATE %s SET last_token=%s,token_expire=%s WHERE last_token='%s'",this.tableName,null,null,token
      ));
    } catch (SQLException e) {
      e.printStackTrace();
      return false;
    }
    return true;
  }


  /**
   *
   * @return Userbean dimana token ditemukan, null validasi gagal
   */
  public UserBean validateToken() {
    return null;

  }

  /**
   * Fungsi untuk menyimpan data user yang akan diregister ke database
   * @param user bean dari servlet
   * @param password password yang diterima oleh servlet
   * @return id yang didapatkan oleh user yang teregister, null jika register gagal
   */
  public Integer register(UserBean user, String password) {
    UserBean ubean = null;
    Integer res = null;
    UserBean ubeanuname = findUser(user.getUsername());
    UserBean ubeanemail = findUserByEmail(user.getEmail());
    if(ubeanuname == null && ubeanemail == null) {
      ResultSet resultSet = null;
      try {
        resultSet = queryUpdate(String.format(
                "INSERT INTO %s (name,email,username,password,phone_number)"
                        + "VALUES ('%s','%s','%s','%s','%s')",
                tableName,user.name,user.email,user.username, password, user.phoneNumber
        ));
      } catch (Exception e) {
        e.printStackTrace();
      }

      try {
        ResultSet temp = query(String.format(
                "SELECT * FROM %s WHERE username = '%s'", this.tableName, user.getUsername()));
        if(temp.next()) {
          ubean = parseResultSet(temp);
        }
      } catch (Exception e) {
        e.printStackTrace();
      }
      res = ubean.getId();
    }

    return res;
  }

  public UserBean findUserByToken(String token) {
    UserBean res = null;
    try {
      ResultSet rs = query(String.format("SELECT * FROM %s WHERE last_token = '%s'", tableName, token ));;
      if (rs.next()) {
        Timestamp timestamp = rs.getTimestamp("token_expire");
        Timestamp currentTimestamp = new Timestamp(System.currentTimeMillis());
        if(timestamp.after(currentTimestamp)) {
          res = parseResultSet(rs);
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return res;
  }
  public void updateTokenLifetime(Integer userId) {
    try {
      ResultSet rs = queryUpdate(String.format(
          "UPDATE %s SET token_expire=DATE_ADD(now(), INTERVAL 7 DAY) where id=%d", this.tableName, userId
      ));
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }
}