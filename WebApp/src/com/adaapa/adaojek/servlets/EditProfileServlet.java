package com.adaapa.adaojek.servlets;

import com.adaapa.adaojek.middlewares.CookieCheck;
import com.adaapa.adaojek.services.ServiceConnector;
import com.adaapa.adaojek.stub.ApplicationCookie;
import com.adaapa.bean.UserBean;
import com.adaapa.bean.WebServiceBean;
import com.adaapa.ojekservice.OjekOnline;
import com.google.gson.Gson;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Base64;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@MultipartConfig
public class EditProfileServlet extends HttpServlet{

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    OjekOnline service = ServiceConnector.getServiceClass();
    ApplicationCookie cookie = CookieCheck.getCookie(req);
    WebServiceBean wsBean = new Gson().fromJson(service.getProfile(cookie.getToken()), WebServiceBean.class);
    if(wsBean.getStatus().equals(WebServiceBean.STATUS_INVALID)) {
      resp.sendRedirect("/login");
    } else {
      UserBean ubean = new Gson().fromJson(wsBean.getBody(),UserBean.class);
      req.setAttribute("userProfile",ubean);
      req.getRequestDispatcher("WEB-INF/edit_profile.jsp").forward(req,resp);
    }
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    CookieCheck.redirectIfTokenNotFound(req, resp,"/login");
    PrintWriter out = resp.getWriter();
 //   out.println(req.getParameter("name"));
    UserBean userBean = new UserBean();
    userBean.name = req.getParameter("name");
    userBean.phoneNumber = req.getParameter("phoneNumber");
    String contentType = req.getPart("image").getContentType();
    userBean.isDriver = req.getParameter("isDriver") != null;
    if(contentType.equals("image/jpg") || contentType.equals("image/jpeg") || contentType.equals("image/png")) {
      InputStream filecontent = req.getPart("image").getInputStream();
      byte[] buffer = new byte[8192];
      int bytesRead;
      ByteArrayOutputStream output = new ByteArrayOutputStream();
      while ((bytesRead = filecontent.read(buffer)) != -1)
      {
        output.write(buffer, 0, bytesRead);
      }
      userBean.image = Base64.getEncoder().encodeToString(output.toByteArray());
    }
    OjekOnline service = ServiceConnector.getServiceClass();
    ApplicationCookie cookie = CookieCheck.getCookie(req);

    Gson gson = new Gson();
    service.editProfile(cookie.getToken(),gson.toJson(userBean));
    resp.sendRedirect("/profile");

  }
}
