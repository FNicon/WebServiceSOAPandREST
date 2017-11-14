package com.adaapa.adaojek.servlets;

import com.adaapa.adaojek.middlewares.CookieCheck;
import com.adaapa.adaojek.services.ServiceConnector;
import com.adaapa.bean.DriverBean;
import com.adaapa.bean.PreferredLocationBean;
import com.adaapa.bean.UserBean;
import com.adaapa.bean.WebServiceBean;
import com.adaapa.ojekservice.OjekOnline;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ProfileServlet extends HttpServlet {
  Gson gson = new Gson();
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    if(!CookieCheck.redirectIfTokenNotFound(req,resp,"/login")) {
      OjekOnline service = ServiceConnector.getServiceClass();
      PrintWriter out = resp.getWriter();
      String token = CookieCheck.getCookie(req).getToken();
      //  out.println(service.getProfile(CookieCheck.getCookie(req).getToken()));
      WebServiceBean webServiceBean = gson
          .fromJson(service.getProfile(token),
              WebServiceBean.class);

      if (webServiceBean.getStatus().equals(WebServiceBean.STATUS_INVALID)) {
        resp.sendRedirect("/logout");
      } else {
        //  out.println(webServiceBean.getBody());
        UserBean ubean = gson.fromJson(webServiceBean.getBody(), UserBean.class);
        if(ubean.getIsDriver()) {
          WebServiceBean getDriverResponse = gson.fromJson(service.getDriverProfile(token), WebServiceBean.class);
          if (getDriverResponse.getStatus().equals(WebServiceBean.STATUS_VALID)) {
            DriverBean driverBean = gson.fromJson(getDriverResponse.getBody(),DriverBean.class);
            req.setAttribute("driverProfile",driverBean);
          } else {
            throw new ServletException();
          }
          WebServiceBean getPreferredLocationResponse = gson.fromJson(service.getPreferredLocation(token), WebServiceBean.class);
          if(getPreferredLocationResponse.getStatus().equals(WebServiceBean.STATUS_VALID)) {
            PreferredLocationBean[] preferredLocationBeanArray = gson.fromJson(getPreferredLocationResponse.getBody(),
                PreferredLocationBean[].class);
            req.setAttribute("preferredLocations", preferredLocationBeanArray);
          } else {
            throw new ServletException();
          }
        }
        req.setAttribute("user", CookieCheck.getCookie(req).getUsername());
        req.setAttribute("userProfile", ubean);
        req.getRequestDispatcher("WEB-INF/profile.jsp").forward(req, resp);
      }
    }

  }

}
