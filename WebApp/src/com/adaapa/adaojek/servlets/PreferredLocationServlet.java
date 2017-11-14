package com.adaapa.adaojek.servlets;

import com.adaapa.adaojek.middlewares.CookieCheck;
import com.adaapa.adaojek.services.ServiceConnector;
import com.adaapa.adaojek.stub.ApplicationCookie;
import com.adaapa.bean.PreferredLocationBean;
import com.adaapa.bean.WebServiceBean;
import com.adaapa.ojekservice.OjekOnline;
import com.google.gson.Gson;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PreferredLocationServlet extends HttpServlet{

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    if(!CookieCheck.redirectIfTokenNotFound(req,resp,"/login")) {
      OjekOnline service = ServiceConnector.getServiceClass();
      ApplicationCookie cookie = CookieCheck.getCookie(req);
      Gson gson = new Gson();
      WebServiceBean wsBean = gson.fromJson(service.getPreferredLocation(cookie.getToken()),WebServiceBean.class);
      if(wsBean.getStatus().equals(WebServiceBean.STATUS_VALID)) {
        PreferredLocationBean[] preferredLocationArray = gson.fromJson(wsBean.getBody(), PreferredLocationBean[].class);
        req.setAttribute("preferredLocations",preferredLocationArray);
      } else {
        resp.sendRedirect("login");
      }
      req.getRequestDispatcher("WEB-INF/edit_preferences.jsp").forward(req, resp);
    }
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    if(!CookieCheck.redirectIfTokenNotFound(req,resp,"login")) {
      OjekOnline service = ServiceConnector.getServiceClass();
      ApplicationCookie cookie = CookieCheck.getCookie(req);
      String location = req.getParameter("location");
      Gson gson = new Gson();
      WebServiceBean wsBean = gson.fromJson(service.addPreferredLocation(cookie.getToken(), location),WebServiceBean.class);
      if(wsBean.getStatus().equals(WebServiceBean.STATUS_INVALID)) {
        resp.sendRedirect("logout");
      } else{
        resp.sendRedirect("/preferred_location");
      }
    }
  }
}
