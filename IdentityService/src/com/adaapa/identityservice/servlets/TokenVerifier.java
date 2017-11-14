package com.adaapa.identityservice.servlets;

import com.adaapa.bean.TokenVerificationResponse;
import com.adaapa.bean.UserBean;
import com.adaapa.identityservice.models.UserModel;
import com.google.gson.Gson;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

@WebService(targetNamespace = "tokenverifier.adaapa.com")
public class TokenVerifier {
  @WebMethod(operationName = "verifyToken")
  @WebResult(name = "userObjectInJSON")
  public String verifyToken(@WebParam(name = "access_token") String access_token) {
    UserModel userModel = new UserModel();
    UserBean userBean = userModel.findUserByToken(access_token);
    TokenVerificationResponse response = new TokenVerificationResponse();
    if(userBean != null){
      userModel.updateTokenLifetime(userBean.getId());
      response.setStatus(TokenVerificationResponse.TOKEN_VALID);
      response.setUser(userBean);
    } else {
      response.setStatus(TokenVerificationResponse.TOKEN_INVALID);
    }
    return (new Gson().toJson(response));
  }

}
