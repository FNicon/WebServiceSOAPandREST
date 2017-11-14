package com.adaapa.bean;

import java.io.Serializable;

public class LogoutResponseBean implements Serializable {
  private String status;

  public LogoutResponseBean() {
    this.status = status;
  }

  public LogoutResponseBean(String status) {
    this.status = status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public String getStatus() {
    return status;
  }
}
