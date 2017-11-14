package com.adaapa.ojekservice;

import com.adaapa.ojekservice.handlers.DriverHandler;
import com.adaapa.ojekservice.handlers.OrderHandler;
import com.adaapa.ojekservice.handlers.ProfileHandler;
import com.google.gson.Gson;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

@SOAPBinding(style = SOAPBinding.Style.RPC)
@WebService()
public class OjekOnline {
  private Gson gson = new Gson();
  @WebMethod(operationName = "getProfile")
  public String getProfile(@WebParam(name = "access_token") String access_token)
  {
    return ProfileHandler.doGetProfile(access_token);
  }

  @WebMethod
  public String addUser(
      @WebParam(name = "access_token") String access_token,
      @WebParam(name = "user") String user) {
    //Redone registering user here, without password but with isdriver status
    return ProfileHandler.doAddUser(access_token, user);
  }

  @WebMethod
  public String getDriverProfile(@WebParam(name = "access_token") String access_token) {
    return DriverHandler.doGetDriverProfile(access_token);
  }

  @WebMethod(operationName = "getPreferredLocation")
  public String getPreferredLocations(
      @WebParam(name = "access_token") String access_token) {
    return DriverHandler.doGetPreferredLocation(access_token);
  }

  @WebMethod
  public String addPreferredLocation(
      @WebParam(name = "access_token") String access_token,
      @WebParam(name = "location") String location) {
    return DriverHandler.doAddPreferredLocation(access_token, location);
  }

  @WebMethod
  public String editProfile(
      @WebParam(name = "access_token") String access_token,
      @WebParam(name = "user") String user) {
    return ProfileHandler.doEditProfile(access_token,user);
  }

  @WebMethod
  public String findDriver (
      @WebParam(name = "access_token") String access_token,
      @WebParam(name = "driver_name") String driver_name) {
      return OrderHandler.doFindDriver(access_token, driver_name);
  }

  @WebMethod
  public String findDriverByPreferredLocation (
      @WebParam(name ="access_token") String access_token,
      @WebParam(name = "pickup") String pickup,
      @WebParam(name = "destination") String destination){
      return OrderHandler.doFindDriverByPreferredLocation(access_token, pickup, destination);
  }

  @WebMethod
  public String completeOrder(
      @WebParam(name = "access_token") String access_token,
      @WebParam(name = "order") String order) {
        return OrderHandler.doCompleteOrder(access_token, order);
  }

  @WebMethod
  public String getUserHistory(
      @WebParam(name = "access_token") String access_token
  ) {
        return OrderHandler.doGetUserHistory(access_token);
  }

  @WebMethod
  public String getDriverHistory(
      @WebParam(name = "access_token") String access_token
  ) {
        return OrderHandler.doGetDriverHistory(access_token);
  }

  @WebMethod
  public String hideDriverHistory(
      @WebParam(name = "access_token") String access_token,
      @WebParam(name = "order_id") Integer order_id) {
    return OrderHandler.doDriverHideHistory(access_token, order_id);

  }

  @WebMethod
  public String hideUserHistory(
      @WebParam(name = "access_token") String access_token,
      @WebParam(name = "order_id") Integer order_id) {
    return OrderHandler.doUserHideHistory(access_token, order_id);
  }

  @WebMethod
  public String deletePreferredLocation(
      @WebParam(name = "access_token") String access_token,
      @WebParam(name = "position") Integer position
  ) {
        return DriverHandler.doDeletePreferredLocation(access_token,position);
  }

  @WebMethod
  public String editPreferredLocation(
      @WebParam(name = "access_token") String access_token,
      @WebParam(name = "position") Integer position,
      @WebParam(name = "location") String location
  ) {
        return DriverHandler.doUpdatePreferredLocation(access_token, position, location);
  }

  @WebMethod
  public String findDriverByUsername(String access_token, String username) {
        return DriverHandler.doFindDriverByUsername(access_token, username);
  }
}


