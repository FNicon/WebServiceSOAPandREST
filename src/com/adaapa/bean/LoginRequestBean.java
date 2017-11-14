package com.adaapa.bean;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class LoginRequestBean implements Serializable {
  private String username;
  private String password;

  public LoginRequestBean (String username, String password) {
    this.username = username;
    this.password = password;
  }

  public String getURLParameter() throws UnsupportedEncodingException {
    return String.format(
        "username=%s&password=%s",
        URLEncoder.encode(username, "UTF-8"),
        URLEncoder.encode(password, "UTF-8")
    );
  }
}
