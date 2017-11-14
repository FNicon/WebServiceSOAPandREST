package com.adaapa.identityservice.services;

import java.util.Base64;
import java.util.Random;

public class TokenService {

  //TODO: generate Token

  /**
   * Generate token menghasilkan token string random
   * @return token yang telah digenerate
   */
  //TODO:Generate Token, validate token, regenerate token(bonus)
  public String generateToken(){
    byte [] r = new byte[96];
    Random rand = new Random();
    rand.nextBytes(r);
    String s = Base64.getEncoder().encodeToString(r);
    return s;
  }

}
