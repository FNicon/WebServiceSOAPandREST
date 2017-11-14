package com.adaapa.ojekservice.models;

import com.adaapa.bean.DriverBean;
import com.adaapa.bean.PreferredLocationBean;
import com.adaapa.models.BaseModel;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Base64;

public class DriverModel extends BaseModel {
  public static String ORDER_TABLE = "orders";
  public static String USER_TABLE = "users";
  public static String PREFERRED_TABLE = "preferred_locations";
  public DriverModel() {
    super("users","id");
  }
  public Integer countVote(String username) {
    try {
      ResultSet rs = query(String.format(
          "SELECT COUNT(rating) as vote FROM  %s inner join %s on (%s.driver_id = %s.id) where users.username = '%s'",
          ORDER_TABLE, USER_TABLE, ORDER_TABLE, USER_TABLE, username));
      if(rs.next()) {
        return rs.getInt("vote");
      } else {
        return 0;
      }

    } catch (Exception e) {
      e.printStackTrace();
      return 0;
    }
  }

  public DriverBean parseDriverBean(ResultSet resultSet) throws SQLException {
    String username = resultSet.getString("username");
    DriverBean driverBean = new DriverBean();
    driverBean.setRating(calculateRating(username));
    driverBean.setName(resultSet.getString("name"));
    driverBean.setVote(countVote(username));
    driverBean.setId(resultSet.getInt("id"));
    driverBean.setUsername(username);
    try {
      driverBean.setImage("data:image/jpeg;charset=utf-8;base64,"+ Base64.getEncoder().encodeToString(resultSet.getBytes("image")));
    } catch (Exception e){
      System.out.println("User has no profile image");
    }
    return driverBean;
  }
  public Double calculateRating (String username) {
    try {
      ResultSet rs = query(String.format(
          "SELECT AVG(rating) as rating FROM  %s inner join %s on (%s.driver_id = %s.id) where users.username = '%s'",
          ORDER_TABLE, USER_TABLE, ORDER_TABLE, USER_TABLE, username));
      if(rs.next()) {
        return rs.getDouble("rating");
      } else {
        return 0.0;
      }
    } catch (Exception e) {
      e.printStackTrace();
      return 0.0;
    }
  }

  public ArrayList<PreferredLocationBean> findPreferredLocation (String username ) {
    ArrayList<PreferredLocationBean> preferredLocationBeanArray = new ArrayList<>();
    try {
      ResultSet rs = query(String.format(
          "SELECT position,location FROM %s NATURAL JOIN %s WHERE users.username = '%s'",
          PREFERRED_TABLE,USER_TABLE, username
      ));
      while (rs.next()) {
        PreferredLocationBean preferredLocationBean = new PreferredLocationBean();
        preferredLocationBean.setLocation(rs.getString("location"));
        preferredLocationBean.setPosition(rs.getInt("position"));
        preferredLocationBeanArray.add(preferredLocationBean);
      }
      return preferredLocationBeanArray;
    } catch (Exception e) {
      e.printStackTrace();
      return preferredLocationBeanArray;
    }
  }

  public ArrayList<DriverBean> findDriverByName(String username, String name) {
    ResultSet resultSet = null;
    ArrayList<DriverBean> driverBeans = new ArrayList<>();
    int i = 0;
    try {
      resultSet = query("SELECT * FROM users WHERE username != '"+username+"' AND name LIKE '%"+name+"%' and is_driver=1");
      while (resultSet.next()) {
        driverBeans.add(parseDriverBean(resultSet));
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return driverBeans;
  }


  public ArrayList<DriverBean> findDriverByPreferredLocation(String username, String pickup, String destination) {
    ResultSet resultSet = null;
    ArrayList<DriverBean> driverBeans = new ArrayList<>();
    int i = 0;
    try {
      resultSet = query(String.format(
          "SELECT DISTINCT users.name, users.username, users.id, users.image "
              + "FROM users NATURAL JOIN preferred_locations WHERE username!= '%s' and (location='%s' or location='%s')",
          username,pickup,destination
      ));
      while (resultSet.next()) {
        driverBeans.add(parseDriverBean(resultSet));
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return driverBeans;
  }

}
