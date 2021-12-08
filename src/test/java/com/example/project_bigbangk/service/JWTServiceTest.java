package com.example.project_bigbangk.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class JWTService_SecretKeyService_IntegrationTest {
    ITokenService jwtService;
//    @Override
//    public String getToken(String email, String firstName) {
//        String token = null;
//        try {
//            Date experationDate = new Date(System.currentTimeMillis() + EXPIRATION_TIME);
//            Map<String, String> payload = new HashMap<>();
//            payload.put("klantId", email);
//            payload.put("firstName", firstName);
//
//            token = JWT.create()
//                    .withIssuer("auth0")
//                    .withExpiresAt(experationDate)
//                    .withPayload(payload)
//                    .sign(ALGORITHM);
//
//        } catch (JWTCreationException exception) {
//            logger.error(exception.getMessage());
//        }
//        return token;
//    }
  @Test
    void getToken() {

    }
//    @Override
//    public boolean authenticateToken(String token) {
//
//        try {
//            JWTVerifier verifier = JWT.require(ALGORITHM)
//                    .withIssuer("auth0")
//                    .build(); //Reusable verifier instance
//            DecodedJWT decodedJWT = verifier.verify(token);
//            if(decodedJWT.getExpiresAt().after(new Date(System.currentTimeMillis()))){
//                return true;
//            }
//        } catch (JWTVerificationException exception) {
//            logger.error(exception.getMessage());
//        }
//        return false;
//    }


    @Test
    void authenticateToken() {
    }
}