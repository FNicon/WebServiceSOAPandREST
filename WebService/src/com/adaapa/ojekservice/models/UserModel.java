package com.adaapa.ojekservice.models;

import com.adaapa.bean.OrderBean;
import com.adaapa.bean.UserBean;
import com.adaapa.models.BaseModel;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Base64;

public class UserModel extends BaseModel{
  public UserModel(){
    super("users","id");
  }

  public UserBean parseUserBean(ResultSet resultSet)throws SQLException {
    UserBean ubean = new UserBean();
    ubean.id = resultSet.getInt("id");
    ubean.email = resultSet.getString("email");
    ubean.phoneNumber = resultSet.getString("phone");
    ubean.name = resultSet.getString("name");
    ubean.isDriver = resultSet.getBoolean("is_driver");
    ubean.username = resultSet.getString("username");
    try {
      ubean.image = "data:image/jpeg;charset=utf-8;base64,"+Base64.getEncoder().encodeToString(resultSet.getBytes("image"));
    } catch (Exception e) {
      System.out.println("User has no image");
    }
    return ubean;
  }

  public OrderBean parseUserOrder(ResultSet resultSet)throws SQLException {
    OrderBean obean = new OrderBean();
    obean.setId(resultSet.getInt("id"));
    obean.setUserId(resultSet.getInt("user_id"));
    obean.setDriverId(resultSet.getInt("driver_id"));
    obean.setPickup(resultSet.getString("pickup"));
    obean.setDestination(resultSet.getString("destination"));
    obean.setRating(resultSet.getDouble("rating"));
    obean.setComment(resultSet.getString("comment"));
    obean.setName(resultSet.getString("name"));
    obean.setTimestamp(resultSet.getTimestamp("timestamp"));
    try {
      obean.setImage("data:image/jpeg;charset=utf-8;base64,"+Base64.getEncoder().encodeToString(resultSet.getBytes("image")));
    } catch (Exception e) {
      System.out.println("User has no image");
    }
    return obean;
  }

  public UserBean findUserByUsername(String username) {
    UserBean userBean = null;
    ResultSet resultSet = null;
    try {
      resultSet = query(String.format("SELECT * FROM %s WHERE username='%s'", tableName, username));
      if(resultSet.next()) {
        userBean = parseUserBean(resultSet);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return userBean;
  }

  public OrderBean findOrderByUsername(String username) {
    OrderBean orderBean = null;
    ResultSet resultSet = null;
    try {
      resultSet = query(String.format(
          "SELECT * FROM orders JOIN users ON  users.id = orders.driver_id WHERE username='%s'",
          username));
      if (resultSet.next()) {
        orderBean = parseUserOrder(resultSet);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return orderBean;
  }

  public Boolean saveUser(UserBean userBean) {
    ResultSet resultSet = null;
    try {
      resultSet = queryUpdate(String.format(
          "INSERT INTO %s (name,email,phone,username,is_driver)"
              + "VALUES ('%s','%s','%s','%s','%d')",
          tableName,userBean.name,userBean.email,userBean.phoneNumber,userBean.username,userBean.isDriver ? 1 : 0

      ));
      return true;
    } catch (Exception e) {
      e.printStackTrace();
      return false;
    }

  }
  public void updateImage(String username, String image) throws SQLException {
    PreparedStatement statement = db
        .prepareStatement("UPDATE users SET image=? WHERE username = ?");
    statement.setBytes(1, Base64.getDecoder().decode(image));
    statement.setString(2,username);
    statement.executeUpdate();
  }
  public void updateUser(UserBean updatedUser) {
    try {
      PreparedStatement statement = db
          .prepareStatement("UPDATE users SET name=?, phone=?,is_driver=? WHERE username = ?");
      statement.setString(1, updatedUser.getName());
      statement.setString(2, updatedUser.phoneNumber);
      statement.setBoolean(3,updatedUser.isDriver);
      statement.setString(4, updatedUser.getUsername());
      statement.executeUpdate();
      if(updatedUser.getImage() != null) {
        updateImage(updatedUser.username, updatedUser.image);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public ArrayList<OrderBean> findPrevOrder(OrderBean driver) {
    ArrayList<OrderBean> res = new ArrayList<>();

    try {
      ResultSet rs = query(String.format("SELECT * FROM orders JOIN users ON users.id = orders.driver_id WHERE user_id = '%s' and not user_hidden ORDER BY timestamp desc",driver.getDriverId()));
      while (rs.next()) {
          res.add(parseUserOrder(rs));
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return res;
  }

  public ArrayList<OrderBean> findPrevDriverOrder(OrderBean driver) {
    ArrayList<OrderBean> res = new ArrayList<>();

    try {
      ResultSet rs = query(String.format("SELECT * FROM orders JOIN users ON users.id = orders.user_id WHERE driver_id = '%s' and not driver_hidden ORDER BY timestamp desc",driver.getDriverId()));
      while (rs.next()) {
        res.add(parseUserOrder(rs));
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return res;
  }
}
