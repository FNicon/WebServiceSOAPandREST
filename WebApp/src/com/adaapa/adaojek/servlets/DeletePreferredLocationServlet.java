package com.adaapa.adaojek.servlets;

import com.adaapa.adaojek.middlewares.CookieCheck;
import com.adaapa.adaojek.services.ServiceConnector;
import com.adaapa.adaojek.stub.ApplicationCookie;
import com.adaapa.bean.WebServiceBean;
import com.adaapa.ojekservice.OjekOnline;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeletePreferredLocationServlet extends HttpServlet {

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    Integer position = Integer.parseInt(req.getParameter("position"));
    if(!CookieCheck.redirectIfTokenNotFound(req,resp,"login")){
      OjekOnline service = ServiceConnector.getServiceClass();
      ApplicationCookie cookie = CookieCheck.getCookie(req);
      WebServiceBean response = new Gson().fromJson(service.deletePreferredLocation(cookie.getToken(),position), WebServiceBean.class);
      if(response.getStatus().equals(WebServiceBean.STATUS_INVALID)) {
        resp.sendRedirect("logout");
      } else {
        resp.sendRedirect("preferred_location");
      }
    }
  }
}
