package com.adaapa.adaojek.servlets;


import com.adaapa.adaojek.middlewares.CookieCheck;
import com.adaapa.adaojek.services.DomainConfig;
import com.adaapa.adaojek.services.RequestSender;
import com.adaapa.adaojek.stub.ApplicationCookie;
import com.adaapa.bean.LoginRequestBean;
import com.adaapa.bean.LoginResponseBean;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Base64;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginServlet extends HttpServlet{
  private final String LOGIN_URL = "/login";
  private final String STATUS_ACCEPTED = "accepted";
  private final String STATUS_FAILED = "failed";
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
      if(!CookieCheck.redirectIfTokenFound(req,resp,"order")) {
      req.getRequestDispatcher("WEB-INF/login.jsp").forward(req, resp);
    }
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {

    DomainConfig domain = DomainConfig.getInstance();
    Gson gson = new Gson();
    //Send request to identity services
    PrintWriter out = resp.getWriter();
    String username = req.getParameter("username");
    String password = req.getParameter("password");
    LoginRequestBean loginBean = new LoginRequestBean(username, password);
    LoginResponseBean loginResponse = gson.fromJson(RequestSender.sendRequest(domain.getISDomain()+LOGIN_URL,
        "POST","application/x-www-form-urlencoded",
        loginBean.getURLParameter()), LoginResponseBean.class);
    if (loginResponse.getStatus().equals(STATUS_ACCEPTED)) {
      Cookie cookie =
          new Cookie("adaapa", Base64.getEncoder().encodeToString(gson.toJson(new ApplicationCookie(loginResponse.getAccessToken(),loginResponse.getUserBean().getUsername())).getBytes()));
      cookie.setMaxAge(loginResponse.getAge());
      resp.addCookie(cookie);
      resp.sendRedirect("index");
    } else {
      req.setAttribute("errorMessage","Login Gagal");
      req.getRequestDispatcher("WEB-INF/login.jsp").forward(req,resp);
    }
  }

}
