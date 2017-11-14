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

public class DriverHideHistoryServlet extends HttpServlet {

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    if(!CookieCheck.redirectIfTokenNotFound(req,resp,"login")) {
      Integer orderId = Integer.parseInt(req.getParameter("orderId"));
      ApplicationCookie cookie = CookieCheck.getCookie(req);
      OjekOnline service = ServiceConnector.getServiceClass();
      WebServiceBean webServiceBean = new Gson().fromJson(service.hideDriverHistory(cookie.getToken(),orderId),WebServiceBean.class);
      if(webServiceBean.getStatus().equals(WebServiceBean.STATUS_VALID)) {
        resp.sendRedirect("driver_history");
      } else {
        resp.sendRedirect("logout");
      }

    }
  }
}
