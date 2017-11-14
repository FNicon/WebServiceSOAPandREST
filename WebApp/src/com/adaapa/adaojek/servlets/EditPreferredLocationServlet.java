package com.adaapa.adaojek.servlets;

import com.adaapa.adaojek.middlewares.CookieCheck;
import com.adaapa.adaojek.services.ServiceConnector;
import com.adaapa.adaojek.stub.ApplicationCookie;
import com.adaapa.bean.WebServiceBean;
import com.adaapa.ojekservice.OjekOnline;
import com.google.gson.Gson;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class EditPreferredLocationServlet extends HttpServlet {

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    if(!CookieCheck.redirectIfTokenNotFound(req,resp,"login")) {
      OjekOnline service = ServiceConnector.getServiceClass();
      ApplicationCookie cookie = CookieCheck.getCookie(req);
      Integer position = Integer.parseInt(req.getParameter("position"));
      String location = req.getParameter("location");
      WebServiceBean wsBean = new Gson().fromJson(service.editPreferredLocation(cookie.getToken(), position, location),WebServiceBean.class);
      if(wsBean.getStatus().equals(WebServiceBean.STATUS_INVALID)) {
        resp.sendRedirect("logout");

      } else {
        resp.sendRedirect("preferred_location");
      }
    }

  }
}
