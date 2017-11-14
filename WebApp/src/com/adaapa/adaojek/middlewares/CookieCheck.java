package com.adaapa.adaojek.middlewares;

import com.adaapa.adaojek.stub.ApplicationCookie;
import com.google.gson.Gson;
import java.io.IOException;
import java.util.Base64;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CookieCheck {
  public static ApplicationCookie getCookie(HttpServletRequest req) {
    ApplicationCookie cookie = null;
    Gson gson = new Gson();
    for(Cookie item : req.getCookies()) {
      if(item.getName().equals("adaapa")) {
        cookie = gson.fromJson(new String(Base64.getDecoder().decode(item.getValue())),ApplicationCookie.class);
        break;
      }
    }
    return cookie;
  }
  public static Boolean redirectIfTokenNotFound(HttpServletRequest req, HttpServletResponse resp, String redirectTo) throws IOException {
    if(getCookie(req) == null) {
      resp.sendRedirect(redirectTo);
      return true;
    }
    return false;
  }
  public static Boolean redirectIfTokenFound(HttpServletRequest req, HttpServletResponse resp, String redirectTo) throws IOException{
    if(getCookie(req) != null) {
      resp.sendRedirect(redirectTo);
      return true;
    }
    return false;
  }
}
