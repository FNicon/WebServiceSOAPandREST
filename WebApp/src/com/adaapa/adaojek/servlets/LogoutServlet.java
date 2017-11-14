package com.adaapa.adaojek.servlets;

import com.adaapa.adaojek.middlewares.CookieCheck;
import com.adaapa.adaojek.services.DomainConfig;
import com.adaapa.adaojek.services.RequestSender;
import com.adaapa.bean.LogoutResponseBean;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class LogoutServlet extends HttpServlet {
  private final String LOGOUT_URL = "/logout";
  private final String STATUS_ACCEPTED = "accepted";
  private final String STATUS_FAILED = "FAILED";
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    if(!CookieCheck.redirectIfTokenNotFound(req,resp,"/login")) {
      DomainConfig domain = DomainConfig.getInstance();
      Gson gson = new Gson();
      String response = RequestSender.sendRequest(
          domain.getISDomain() + LOGOUT_URL,
          "POST",
          "application/x-www-form-urlencoded",
          String.format("access_token=%s", URLEncoder.encode(CookieCheck.getCookie(req).getToken(), "UTF-8"))
      );
      PrintWriter out = resp.getWriter();
      LogoutResponseBean responseBean = gson.fromJson(response, LogoutResponseBean.class);
      if (responseBean.getStatus().equals(STATUS_ACCEPTED)) {
        Cookie cookie = new Cookie("adaapa", "");
        cookie.setMaxAge(0);
        resp.addCookie(cookie);
      } else {
        //Do nothing
      }
      resp.sendRedirect("/login");
    }
  }
}
