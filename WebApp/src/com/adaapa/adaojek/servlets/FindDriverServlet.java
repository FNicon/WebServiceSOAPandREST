package com.adaapa.adaojek.servlets;

import com.adaapa.adaojek.middlewares.CookieCheck;
import com.adaapa.adaojek.services.ServiceConnector;
import com.adaapa.adaojek.stub.ApplicationCookie;
import com.adaapa.bean.DriverBean;
import com.adaapa.bean.WebServiceBean;
import com.adaapa.ojekservice.OjekOnline;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FindDriverServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        if(!CookieCheck.redirectIfTokenNotFound(req,resp,"login")) {
            ApplicationCookie cookie = CookieCheck.getCookie(req);
            req.setAttribute("user", cookie.getUsername());
            OjekOnline service = ServiceConnector.getServiceClass();
            PrintWriter out = resp.getWriter();
            String token = CookieCheck.getCookie(req).getToken();
            Gson gson = new Gson();
            String pickup = req.getParameter("pickUp");
            String destination = req.getParameter("destination");
            req.setAttribute("pickup",pickup);
            req.setAttribute("destination",destination);
            String preferredDriver = req.getParameter("preferredDriver");
            if(!preferredDriver.equals("")) {
                WebServiceBean webServiceBean = gson
                    .fromJson(service.findDriver(token, preferredDriver), WebServiceBean.class);
                if (webServiceBean.getStatus().equals(WebServiceBean.STATUS_INVALID)) {
                    resp.sendRedirect("logout");
                } else {
                    DriverBean[] driverBeans = gson.fromJson(webServiceBean.getBody(),DriverBean[].class);
                    req.setAttribute("preferredDrivers", driverBeans);
                    req.setAttribute("preferredDriversLength", driverBeans.length);

                }
            } else {
                req.setAttribute("preferredDriversLength", 0);
            }
            WebServiceBean wsBean = gson.
                fromJson(service.findDriverByPreferredLocation(cookie.getToken(),pickup,destination),
                    WebServiceBean.class);
            if(wsBean.getStatus().equals(WebServiceBean.STATUS_INVALID)) {
                resp.sendRedirect("logout");
            } else {
                DriverBean[] driverBeans= gson.fromJson(wsBean.getBody(),DriverBean[].class);
                req.setAttribute("foundDrivers", driverBeans);
                req.setAttribute("foundDriversLength", driverBeans.length);
                req.getRequestDispatcher("WEB-INF/find_driver.jsp").forward(req,resp);
            }
        }
    }
}
