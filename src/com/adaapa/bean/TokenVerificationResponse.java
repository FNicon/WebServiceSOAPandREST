package com.adaapa.bean;

public class TokenVerificationResponse {
  public static final String TOKEN_VALID = "valid";
  public static final String TOKEN_INVALID = "invalid";

  private String status;
  private UserBean user;

  public String getStatus() {
    return status;
  }

  public UserBean getUser() {
    return user;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public void setUser(UserBean user) {
    this.user = user;
  }
}
