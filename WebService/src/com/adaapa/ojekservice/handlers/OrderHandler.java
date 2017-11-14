package com.adaapa.ojekservice.handlers;

import com.adaapa.bean.DriverBean;
import com.adaapa.bean.OrderBean;
import com.adaapa.bean.UserBean;
import com.adaapa.bean.WebServiceBean;
import com.adaapa.ojekservice.models.DriverModel;
import com.adaapa.ojekservice.models.UserModel;
import com.adaapa.ojekservice.services.TokenVerificationService;
import com.google.gson.Gson;
import java.util.ArrayList;

/**
 * Created by ireneedriadr on 11/5/17.
 */
public class OrderHandler {
  public static String doGetUserHistory(String access_token) {
    try {
      WebServiceBean response = new WebServiceBean();
      UserModel userModel = new UserModel();
      UserBean validUser = TokenVerificationService.verifyToken(access_token);
      if(validUser != null) {
        ArrayList<OrderBean> userHistoryArray = userModel.findPrevOrder(userModel.findOrderByUsername(validUser.getUsername()));
        response.setStatus(WebServiceBean.STATUS_VALID);
        response.setBody(new Gson().toJson(userHistoryArray));
      } else {
        response.setStatus(WebServiceBean.STATUS_INVALID);
      }
      return new Gson().toJson(response);
    }catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }

    public static String doFindDriver(String access_token, String driver_name){
        try {
            Gson gson = new Gson();
            WebServiceBean webServiceBean = new WebServiceBean();
            UserBean validUser = TokenVerificationService.verifyToken(access_token);
            if (validUser == null) {
                webServiceBean.setStatus(WebServiceBean.STATUS_INVALID);
                return gson.toJson(webServiceBean);
            } else {
                webServiceBean.setStatus(WebServiceBean.STATUS_VALID);
                UserModel userModel = new UserModel();
                DriverModel driverModel = new DriverModel();
                ArrayList<DriverBean> driverBeans = driverModel.findDriverByName(validUser.getUsername(),driver_name);
                webServiceBean.setBody(gson.toJson(driverBeans));
                return gson.toJson(webServiceBean);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
  public static String doGetDriverHistory(String access_token) {
    try {
      WebServiceBean response = new WebServiceBean();
      UserModel userModel = new UserModel();
      UserBean validUser = TokenVerificationService.verifyToken(access_token);
      if(validUser != null) {
        ArrayList<OrderBean> userHistoryArray = userModel.findPrevDriverOrder(userModel.findOrderByUsername(validUser.getUsername()));
        response.setStatus(WebServiceBean.STATUS_VALID);
        response.setBody(new Gson().toJson(userHistoryArray));
      } else {
        response.setStatus(WebServiceBean.STATUS_INVALID);
      }
      return new Gson().toJson(response);
    }catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }

  public static String doFindDriverByPreferredLocation(String access_token, String pickup, String destination){
    try {
      Gson gson = new Gson();
      WebServiceBean webServiceBean = new WebServiceBean();
      UserBean validUser = TokenVerificationService.verifyToken(access_token);
      if (validUser == null) {
        webServiceBean.setStatus(WebServiceBean.STATUS_INVALID);
        return gson.toJson(webServiceBean);
      } else {
        webServiceBean.setStatus(WebServiceBean.STATUS_VALID);
        UserModel userModel = new UserModel();
        DriverModel driverModel = new DriverModel();
        ArrayList<DriverBean> driverBeans = driverModel.findDriverByPreferredLocation(validUser.getUsername(), pickup, destination);
        webServiceBean.setBody(gson.toJson(driverBeans));
        return gson.toJson(webServiceBean);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }

  public static String doCompleteOrder(String access_token, String order) {
    try{
      Gson gson = new Gson();
      WebServiceBean webServiceBean = new WebServiceBean();
      UserBean validUser = TokenVerificationService.verifyToken(access_token);
      if (validUser == null) {
        webServiceBean.setStatus(WebServiceBean.STATUS_INVALID);
      } else {
        webServiceBean.setStatus(WebServiceBean.STATUS_VALID);
        OrderBean orderBean = gson.fromJson(order, OrderBean.class);
        DriverModel driverModel = new DriverModel();
        driverModel.queryUpdate(String.format(
            "INSERT INTO orders (user_id,driver_id,pickup,destination,rating,comment) VALUES"
                + "(%d,%d,'%s','%s','%d','%s')",
            validUser.getId(),orderBean.getDriverId(),orderBean.getPickup(),orderBean.getDestination(),
            orderBean.getRating().intValue(), orderBean.getComment()
        ));
      }
      return gson.toJson(webServiceBean);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }

  public static String doUserHideHistory(String access_token, Integer orderId) {
    try{
      Gson gson = new Gson();
      WebServiceBean webServiceBean = new WebServiceBean();
      UserBean validUser = TokenVerificationService.verifyToken(access_token);
      if (validUser == null) {
        webServiceBean.setStatus(WebServiceBean.STATUS_INVALID);
      } else {
        webServiceBean.setStatus(WebServiceBean.STATUS_VALID);
        UserModel userModel = new UserModel();
        userModel.queryUpdate(String.format("UPDATE orders SET user_hidden=1 WHERE id=%d",orderId));
      }
      return gson.toJson(webServiceBean);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }

  public static String doDriverHideHistory(String access_token, Integer orderId) {
    try{
      Gson gson = new Gson();
      WebServiceBean webServiceBean = new WebServiceBean();
      UserBean validUser = TokenVerificationService.verifyToken(access_token);
      if (validUser == null) {
        webServiceBean.setStatus(WebServiceBean.STATUS_INVALID);
      } else {
        webServiceBean.setStatus(WebServiceBean.STATUS_VALID);
        UserModel userModel = new UserModel();
        userModel.queryUpdate(String.format("UPDATE orders SET driver_hidden=1 WHERE id=%d",orderId));
      }
      return gson.toJson(webServiceBean);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }

}
