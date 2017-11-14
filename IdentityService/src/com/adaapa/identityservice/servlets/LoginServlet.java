package com.adaapa.identityservice.servlets;

import com.adaapa.bean.LoginResponseBean;
import com.adaapa.bean.UserBean;
import com.adaapa.identityservice.models.UserModel;
import com.adaapa.identityservice.services.TokenService;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by kennethhalim on 10/26/17.
 */

public class LoginServlet extends HttpServlet {
  private String message;


  protected void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    TokenService tokenService = new TokenService();
    String username = req.getParameter("username");
    String password = req.getParameter("password");
    PrintWriter out = resp.getWriter();
    Gson gson = new Gson();
    UserModel userModel = new UserModel();
    UserBean user= userModel.authenticate(username, password);
    UserBean responseUser = new UserBean();
    if(user != null) {
      //Call Generate token here, then save it to database via usermodel;
      responseUser.username = user.username;
      String access_token = tokenService.generateToken();
      userModel.saveToken(user.id, access_token);
      out.println(gson.toJson(new LoginResponseBean("accepted",access_token, LoginResponseBean.TOKEN_AGE_DEFAULT, responseUser)));
    } else  {
      out.println(gson.toJson(new LoginResponseBean("failed","",0,null)));
    }
  }

  public void destroy() {
    // do nothing.
  }
}
