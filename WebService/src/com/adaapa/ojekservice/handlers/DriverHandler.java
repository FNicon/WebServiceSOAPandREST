package com.adaapa.ojekservice.handlers;

import com.adaapa.bean.DriverBean;
import com.adaapa.bean.PreferredLocationBean;
import com.adaapa.bean.UserBean;
import com.adaapa.bean.WebServiceBean;
import com.adaapa.ojekservice.models.DriverModel;
import com.adaapa.ojekservice.models.UserModel;
import com.adaapa.ojekservice.services.TokenVerificationService;
import com.google.gson.Gson;
import java.sql.ResultSet;
import java.util.ArrayList;

public class DriverHandler {
  public static String doGetPreferredLocation(String access_token) {
    try {
      WebServiceBean response = new WebServiceBean();
      UserModel userModel = new UserModel();
      UserBean validUser = TokenVerificationService.verifyToken(access_token);
      if(validUser != null) {
        DriverModel driverModel = new DriverModel();
        ArrayList<PreferredLocationBean> preferredLocationArray = driverModel.findPreferredLocation(validUser.getUsername());
        response.setStatus(WebServiceBean.STATUS_VALID);
        response.setBody(new Gson().toJson(preferredLocationArray));
      } else {
        response.setStatus(WebServiceBean.STATUS_INVALID);
      }
      return new Gson().toJson(response);
    }catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }

  public static String doGetDriverProfile(String access_token) {
    try {
      WebServiceBean response = new WebServiceBean();
      UserModel userModel = new UserModel();
      UserBean validUser = TokenVerificationService.verifyToken(access_token);
      if(validUser != null) {
        DriverModel driverModel = new DriverModel();
        DriverBean driverBean = new DriverBean();
        driverBean.setVote(driverModel.countVote(validUser.username));
        driverBean.setName(validUser.getName());
        driverBean.setRating(driverModel.calculateRating(validUser.username));
        response.setStatus(WebServiceBean.STATUS_VALID);
        response.setBody(new Gson().toJson(driverBean));
      } else {
        response.setStatus(WebServiceBean.STATUS_INVALID);
      }
      return new Gson().toJson(response);
    }catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }

  public static String doAddPreferredLocation(String access_token, String location) {
    try {
      WebServiceBean response = new WebServiceBean();
      UserBean validUser = TokenVerificationService.verifyToken(access_token);
      if(validUser == null) {
        response.setStatus(WebServiceBean.STATUS_INVALID);
      } else {
        response.setStatus(WebServiceBean.STATUS_VALID);
        UserModel userModel = new UserModel();
        DriverModel driverModel = new DriverModel();
        UserBean wsUser = userModel.findUserByUsername(validUser.username);
        ResultSet rs = driverModel.query(String.format("SELECT MAX(position) as max FROM preferred_locations WHERE id= %d",wsUser.getId()));
        int curr = rs.next() ? rs.getInt("max") + 1 : 1;
        driverModel.queryUpdate(
            String.format("INSERT INTO preferred_locations VALUES (%d,%d,'%s')",
                wsUser.getId(), curr,location));
      }
      return new Gson().toJson(response);
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }

  public static String doDeletePreferredLocation(String access_token, Integer position) {
    try {
      WebServiceBean response = new WebServiceBean();
      UserBean validUser = TokenVerificationService.verifyToken(access_token);
      if(validUser == null) {
        response.setStatus(WebServiceBean.STATUS_INVALID);
      } else {
        response.setStatus(WebServiceBean.STATUS_VALID);
        UserModel userModel = new UserModel();
        DriverModel driverModel = new DriverModel();
        UserBean wsUser = userModel.findUserByUsername(validUser.getUsername());
        ResultSet rs = driverModel.queryUpdate(String.format(
            "DELETE FROM preferred_locations WHERE id=%d and position=%d",
            wsUser.getId(),position
        ));
      }
      return new Gson().toJson(response);
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }

  public static String doUpdatePreferredLocation(String access_token, Integer position, String location) {
    try {
      WebServiceBean response = new WebServiceBean();
      UserBean validUser = TokenVerificationService.verifyToken(access_token);
      if(validUser == null) {
        response.setStatus(WebServiceBean.STATUS_INVALID);
      } else {
        response.setStatus(WebServiceBean.STATUS_VALID);
        UserModel userModel = new UserModel();
        DriverModel driverModel = new DriverModel();
        UserBean wsUser = userModel.findUserByUsername(validUser.getUsername());
        ResultSet rs = driverModel.queryUpdate(String.format(
            "UPDATE preferred_locations SET location = '%s' WHERE id=%d AND position=%d",
            location,wsUser.getId(),position
        ));
      }
      return new Gson().toJson(response);
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }
  public static String doFindDriverByUsername(String access_token, String username) {
    try {
      UserBean validUser = TokenVerificationService.verifyToken(access_token);
      Gson gson =  new Gson();
      WebServiceBean response = new WebServiceBean();
      if(validUser != null) {
        response.setStatus(WebServiceBean.STATUS_VALID);
        response.setBody(gson.toJson(new UserModel().findUserByUsername(username)));
      } else {
        response.setStatus(WebServiceBean.STATUS_INVALID);
      }
      return gson.toJson(response);
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }

  }
}
