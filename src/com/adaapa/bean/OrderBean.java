package com.adaapa.bean;

import java.sql.Timestamp;

public class OrderBean {
  private Integer id;
  private Integer userId;
  private Integer driverId;
  private Double rating;
  private String pickup;
  private String destination;
  private String comment;
  private String image;
  private String name;
  private String username;
  private Timestamp timestamp;

  public Timestamp getTimestamp() {
    return timestamp;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public void setTimestamp(Timestamp timestamp) {
    this.timestamp = timestamp;
  }

  public Double getRating() {
    return rating;
  }

  public Integer getDriverId() {
    return driverId;
  }

  public Integer getUserId() {
    return userId;
  }

  public String getComment() {
    return comment;
  }

  public String getDestination() {
    return destination;
  }

  public String getPickup() {
    return pickup;
  }

  public String getImage() { return image; }

  public String getName() { return name; }

  public String getUsername() { return username; }

  public void setRating(Double rating) {
    this.rating = rating;
  }

  public void setComment(String comment) {
    this.comment = comment;
  }

  public void setDestination(String destination) {
    this.destination = destination;
  }

  public void setDriverId(Integer driverId) {
    this.driverId = driverId;
  }

  public void setPickup(String pickup) {
    this.pickup = pickup;
  }

  public void setUserId(Integer userId) {
    this.userId = userId;
  }

  public void setImage(String image) { this.image = image;}

  public void setName(String name) { this.name = name;}

  public void setUsername(String name) { this.username = username;}
}
