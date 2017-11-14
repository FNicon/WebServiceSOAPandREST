package com.adaapa.ojekservice.handlers;

import com.adaapa.bean.UserBean;
import com.adaapa.bean.WebServiceBean;
import com.adaapa.ojekservice.models.UserModel;
import com.adaapa.ojekservice.services.TokenVerificationService;
import com.google.gson.Gson;

public class ProfileHandler {
  static Gson gson = new Gson();
  public static String doGetProfile(String access_token){
    try {
      WebServiceBean webServiceBean = new WebServiceBean();
      UserBean resUser = TokenVerificationService.verifyToken(access_token);
      if (resUser == null) {
        webServiceBean.setStatus(WebServiceBean.STATUS_INVALID);
        return gson.toJson(webServiceBean);
      } else {
        webServiceBean.setStatus(WebServiceBean.STATUS_VALID);
        UserModel userModel = new UserModel();
        UserBean user = userModel.findUserByUsername(resUser.getUsername());
        if (user == null) {
          resUser.isDriver = false;
          userModel.saveUser(resUser);
          webServiceBean.setBody(gson.toJson(resUser));
        } else {
          webServiceBean.setBody(gson.toJson(user));
        }
        return gson.toJson(webServiceBean);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }
  public static String doEditProfile(String access_token, String user) {
    try {
      WebServiceBean webServiceBean = new WebServiceBean();
      UserBean resUser = TokenVerificationService.verifyToken(access_token);
      if (resUser == null) {
        webServiceBean.setStatus(WebServiceBean.STATUS_INVALID);
      } else {
        UserBean userBean = gson.fromJson(user, UserBean.class);
        userBean.username = resUser.username;
        UserModel userModel = new UserModel();
        userModel.updateUser(userBean);
        webServiceBean.setStatus(WebServiceBean.STATUS_VALID);
      }
      return gson.toJson(webServiceBean);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }

  public static String doAddUser(String access_token, String user) {
    try {
      WebServiceBean webServiceBean = new WebServiceBean();
      UserBean validUser = TokenVerificationService.verifyToken(access_token);
      if (validUser == null) {
        webServiceBean.setStatus(WebServiceBean.STATUS_INVALID);
      } else {
        UserBean userBean = gson.fromJson(user, UserBean.class);
        UserModel userModel = new UserModel();
        userModel.saveUser(userBean);
        webServiceBean.setStatus(WebServiceBean.STATUS_VALID);
      }
      return gson.toJson(webServiceBean);
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }
}
