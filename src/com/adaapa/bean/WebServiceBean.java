package com.adaapa.bean;

import java.io.Serializable;

public class WebServiceBean implements Serializable{
  public static final String STATUS_VALID = "valid" ;
  public static final String STATUS_INVALID = "invalid";
  private String status;
  private String body;

  public WebServiceBean(String status, String body) {
    setBody(body);
    setStatus(status);
  }
  public WebServiceBean(){
    //do nothing
  }
  public void setStatus(String status) {
    this.status = status;
  }

  public void setBody(String body) {
    this.body = body;
  }

  public String getStatus() {
    return status;

  }

  public String getBody() {
    return body;
  }
}
