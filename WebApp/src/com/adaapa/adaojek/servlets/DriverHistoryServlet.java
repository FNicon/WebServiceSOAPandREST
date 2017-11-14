package com.adaapa.adaojek.servlets;

import com.adaapa.adaojek.middlewares.CookieCheck;
import com.adaapa.adaojek.services.ServiceConnector;
import com.adaapa.adaojek.stub.ApplicationCookie;
import com.adaapa.bean.OrderBean;
import com.adaapa.bean.WebServiceBean;
import com.adaapa.ojekservice.OjekOnline;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DriverHistoryServlet extends HttpServlet {
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {

    if(!CookieCheck.redirectIfTokenNotFound(req,resp,"login")) {
      ApplicationCookie cookie = CookieCheck.getCookie(req);
      req.setAttribute("user", cookie.getUsername());
      String access_token = cookie.getToken();
      OjekOnline service = ServiceConnector.getServiceClass();
      Gson gson = new Gson();
      WebServiceBean wsResponse = gson.fromJson(service.getDriverHistory(access_token),WebServiceBean.class);
      PrintWriter out = resp.getWriter();
      if(wsResponse.getStatus().equals(WebServiceBean.STATUS_VALID)) {
        if(wsResponse.getBody() != null) {
          OrderBean[] orderBeans = gson.fromJson(wsResponse.getBody(), OrderBean[].class);
          req.setAttribute("orderBeans", orderBeans);
        }

        req.getRequestDispatcher("WEB-INF/driver_history.jsp").forward(req, resp);
      } else
        resp.sendRedirect("logout");
    }
  }
}
