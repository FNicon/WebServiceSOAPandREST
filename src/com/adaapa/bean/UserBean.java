package com.adaapa.bean;

import java.io.Serializable;

public class UserBean implements Serializable{
  public Integer id;
  public String name;
  public String email;
  public String username;
  public String phoneNumber;

  public Boolean isDriver;
  public String image;

  public String getName() {
    return name;
  }
  public String getUsername(){
    return username;
  }
  public String getPhoneNumber(){
    return phoneNumber;
  }

  public Boolean getIsDriver() { return isDriver; }

  public String getEmail() {
    return email;
  }


  public String getImage() {
    return image;
  }

  public Integer getId() {
    return id;
  }
}
