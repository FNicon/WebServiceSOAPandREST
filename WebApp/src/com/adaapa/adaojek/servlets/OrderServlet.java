package com.adaapa.adaojek.servlets;

import com.adaapa.adaojek.middlewares.CookieCheck;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class OrderServlet extends HttpServlet {

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    if(!CookieCheck.redirectIfTokenNotFound(req,resp,"/login")) {
      req.setAttribute("user", CookieCheck.getCookie(req).getUsername());
      req.getRequestDispatcher("WEB-INF/order.jsp").forward(req, resp);
    }
  }
}
