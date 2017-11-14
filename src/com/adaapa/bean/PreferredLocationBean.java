package com.adaapa.bean;

import java.io.Serializable;

public class PreferredLocationBean implements Serializable {
  Integer position;
  String location;

  public Integer getPosition() {
    return position;
  }

  public String getLocation() {
    return location;
  }

  public void setLocation(String location) {
    this.location = location;
  }

  public void setPosition(Integer position) {
    this.position = position;
  }
}
