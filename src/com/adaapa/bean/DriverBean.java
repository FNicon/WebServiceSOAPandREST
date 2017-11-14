package com.adaapa.bean;

import java.io.Serializable;

public class DriverBean implements Serializable{
  private Integer id;
  private String name;
  private Double rating;
  private Integer vote;
  private String image;
  private String username;

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getImage() {
    return image;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public void setImage(String image) {
    this.image = image;
  }

  public String getName() {
    return name;
  }

  public Double getRating() {
    return rating;
  }

  public Integer getVote() {
    return vote;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setVote(Integer vote) {
    this.vote = vote;
  }

  public void setRating(Double rating) {
    this.rating = rating;
  }

}
