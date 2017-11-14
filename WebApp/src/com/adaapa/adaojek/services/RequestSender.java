package com.adaapa.adaojek.services;

import java.awt.color.ProfileDataException;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class RequestSender {

  public static String sendRequest(String url, String method, String contentType, String payload)
      throws IOException, ProfileDataException {
    URL obj = new URL(url);
    HttpURLConnection con = (HttpURLConnection) obj.openConnection();
    con.setRequestProperty("User-Agent", "JavaServlet");
    con.setRequestProperty("Content-Type", contentType);
    con.setDoOutput(true);
    con.setRequestMethod(method);
    DataOutputStream wr = new DataOutputStream(
        con.getOutputStream());
    wr.writeBytes(payload);
    wr.flush();
    wr.close();
    con.connect();
    StringBuffer response = new StringBuffer();
    BufferedReader in = new BufferedReader(
        new InputStreamReader(con.getInputStream()));
    String inputLine;
    while ((inputLine = in.readLine()) != null) {
      response.append(inputLine);
    }
    return response.toString();
  }
}