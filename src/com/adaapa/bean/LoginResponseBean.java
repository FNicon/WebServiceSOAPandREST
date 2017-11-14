package com.adaapa.bean;

import java.io.Serializable;

public class LoginResponseBean implements Serializable {
  public static Integer TOKEN_AGE_DEFAULT = 60*60*24*365;
  private String status;
  private String access_token;
  private UserBean user_bean;
  private Integer age;
  public LoginResponseBean(String status, String access_token,Integer age, UserBean userBean) {
    setStatus(status);
    setAge(age);
    setAccessToken(access_token);
    setUserBean(userBean);
  }

  public Integer getAge() {
    return age;
  }

  public void setAge(Integer age) {
    this.age = age;
  }
  public void setStatus(String status) {
    this.status = status;
  }
  public void setAccessToken(String access_token) {
    this.access_token = access_token;
  }
  public void setUserBean(UserBean userBean) {
    this.user_bean = userBean;
  }
  public String getStatus() {
    return status;
  }
  public String getAccessToken() {
    return access_token;
  }
  public UserBean getUserBean() {
    return user_bean;
  }
}
