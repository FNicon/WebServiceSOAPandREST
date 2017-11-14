package com.adaapa.adaojek.servlets;

import com.adaapa.adaojek.middlewares.CookieCheck;
import com.adaapa.adaojek.services.DomainConfig;
import com.adaapa.adaojek.services.RequestSender;
import com.adaapa.adaojek.services.ServiceConnector;
import com.adaapa.adaojek.stub.ApplicationCookie;
import com.adaapa.bean.LoginResponseBean;
import com.adaapa.bean.UserBean;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.util.Base64;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RegisterServlet extends HttpServlet{
  private final String REGISTER_URL = "/register";
  private final String STATUS_ACCEPTED = "accepted";
  private final String STATUS_FAILED = "failed";
  DomainConfig domain = DomainConfig.getInstance();
  Gson gson = new Gson();

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    if(!CookieCheck.redirectIfTokenFound(req,resp,"/order")) {
      req.getRequestDispatcher("WEB-INF/signup.jsp").forward(req,resp);
    }
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    PrintWriter out = resp.getWriter();
    String name = req.getParameter("name");
    String username = req.getParameter("username");
    String email = req.getParameter("email");
    String password = req.getParameter("password");
    String confirmpassword = req.getParameter("confirm-password");
    String phoneNumber = req.getParameter("phoneNumber");
    Boolean isDriver = req.getParameter("isDriver") != null;
    UserBean wsUser = new UserBean();
    wsUser.name = name;
    wsUser.username = username;
    wsUser.email = email;
    wsUser.phoneNumber = phoneNumber;
    wsUser.isDriver = isDriver;
    String payload = String.format("name=%s&username=%s&email=%s&password=%s&confirmpassword=%s&phoneNumber=%s&isDriver=%b",
            URLEncoder.encode(name,"UTF-8"),URLEncoder.encode(username,"UTF-8"),URLEncoder.encode(email, "UTF-8"),
            URLEncoder.encode(password,"UTF-8"), URLEncoder.encode(confirmpassword,"UTF-8"),
            URLEncoder.encode(phoneNumber,"UTF-8"),isDriver);
    LoginResponseBean loginResponse = gson.fromJson(RequestSender.sendRequest(
            domain.getISDomain()+REGISTER_URL, "POST","application/x-www-form-urlencoded",
            payload
    ),LoginResponseBean.class);


    if (loginResponse.getStatus().equals(STATUS_ACCEPTED)) {
      Cookie cookie =
              new Cookie("adaapa", Base64.getEncoder().encodeToString(gson.toJson(new ApplicationCookie(loginResponse.getAccessToken(),loginResponse.getUserBean().getUsername())).getBytes()));
      cookie.setMaxAge(loginResponse.getAge());
      ServiceConnector.getServiceClass().addUser(loginResponse.getAccessToken(), gson.toJson(wsUser));
      resp.addCookie(cookie);
      if(isDriver) {
        resp.sendRedirect("/profile");
      }
      else {
        resp.sendRedirect("/index");
      }
    } else {
      req.setAttribute("errorMessage","Registrasi Gagal");
      req.getRequestDispatcher("WEB-INF/signup.jsp").forward(req,resp);
    }

  }
}
