package com.adaapa.adaojek.stub;

public class ApplicationCookie {

  private String access_token;
  private String username;

  public ApplicationCookie(String access_token, String username){
    this.access_token = access_token;
    this.username = username;
  }

  public String getUsername() {
    return username;
  }

  public String getToken() {
    return access_token;
  }

}
