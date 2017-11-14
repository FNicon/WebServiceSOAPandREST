package com.adaapa.identityservice.servlets;

import com.adaapa.bean.LogoutResponseBean;
import com.adaapa.identityservice.models.UserModel;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LogoutServlet extends HttpServlet {

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    String token = req.getParameter("access_token");
    UserModel userModel = new UserModel();
    PrintWriter out = resp.getWriter();
    LogoutResponseBean logoutResponse = new LogoutResponseBean();
    if(userModel.deleteToken(token)){
      logoutResponse.setStatus("accepted");
    } else {
      logoutResponse.setStatus("failed");
    }
    Gson gson = new Gson();
    out.println(gson.toJson(logoutResponse));
  }
}
