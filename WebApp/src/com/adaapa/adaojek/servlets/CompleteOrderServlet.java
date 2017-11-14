package com.adaapa.adaojek.servlets;

import com.adaapa.adaojek.middlewares.CookieCheck;
import com.adaapa.adaojek.services.ServiceConnector;
import com.adaapa.adaojek.stub.ApplicationCookie;
import com.adaapa.bean.OrderBean;
import com.adaapa.bean.UserBean;
import com.adaapa.bean.WebServiceBean;
import com.adaapa.ojekservice.OjekOnline;
import com.google.gson.Gson;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CompleteOrderServlet extends HttpServlet{

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    if(!CookieCheck.redirectIfTokenNotFound(req,resp,"logout")) {
      String pickup = req.getParameter("pickUp");
      String destination = req.getParameter("destination");
      req.setAttribute("pickup", pickup);
      req.setAttribute("destination", destination);
      ApplicationCookie cookie = CookieCheck.getCookie(req);
      req.setAttribute("user", cookie.getUsername());
      Gson gson = new Gson();
      String response = ServiceConnector.getServiceClass().
          findDriverByUsername(cookie.getToken(),req.getParameter("driverId"));
      WebServiceBean wsBean = gson.fromJson(response,WebServiceBean.class);
      if(wsBean.getStatus().equals(WebServiceBean.STATUS_INVALID)) {
        resp.sendRedirect("logout");
      } else {
        UserBean ubean = gson.fromJson(wsBean.getBody(),UserBean.class);
        req.setAttribute("driverProfile", ubean);
        req.getRequestDispatcher("WEB-INF/complete_order.jsp").forward(req, resp);
      }
    }
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    if(!CookieCheck.redirectIfTokenNotFound(req,resp,"login")) {
      OrderBean orderBean = new OrderBean();
      orderBean.setDriverId(Integer.parseInt(req.getParameter("driverId")));
      orderBean.setPickup(req.getParameter("pickUp"));
      orderBean.setComment(req.getParameter("comment"));
      orderBean.setDestination(req.getParameter("destination"));
      orderBean.setRating(Double.parseDouble(req.getParameter("rating")));
      Gson gson = new Gson();
      ApplicationCookie cookie = CookieCheck.getCookie(req);
      OjekOnline service = ServiceConnector.getServiceClass();
      WebServiceBean wsBean = gson.fromJson(service.completeOrder(cookie.getToken(),gson.toJson(orderBean)), WebServiceBean.class);
      if(wsBean.getStatus().equals(WebServiceBean.STATUS_VALID)) {
        resp.sendRedirect("history");
      } else {
        resp.sendRedirect("logout");
      }
    }
  }
}
