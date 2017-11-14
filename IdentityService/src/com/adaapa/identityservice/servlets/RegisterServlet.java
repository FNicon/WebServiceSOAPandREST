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

public class RegisterServlet extends HttpServlet{

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    //Implement Register Here
    UserBean user = new UserBean();
    UserModel userModel = new UserModel();
    UserModel usermodel = new UserModel();
    TokenService tokenService = new TokenService();
    //Parsing data
    PrintWriter out = resp.getWriter();
    user.name = req.getParameter("name");
    user.username = req.getParameter("username");
    user.email = req.getParameter("email");
    user.phoneNumber = req.getParameter("phoneNumber");
    String password = req.getParameter("password");
    Gson gson = new Gson();
    user.id = userModel.register(user,password);
    UserBean responseUser = new UserBean();
    if(user.id != null) {
      //Register berhasil
      //Generate token here, then save token
      String token = tokenService.generateToken();
      userModel.saveToken(user.id, token);
      responseUser.username = user.username;
      out.println(gson.toJson(new LoginResponseBean("accepted",token, LoginResponseBean.TOKEN_AGE_DEFAULT, responseUser)));
    } else  {
      out.println(gson.toJson(new LoginResponseBean("failed","",0,null)));
    }
  }
}