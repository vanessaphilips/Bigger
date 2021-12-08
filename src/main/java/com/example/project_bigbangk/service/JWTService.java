// Created by Deek
// Creation date 12/3/2021

package com.example.project_bigbangk.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class JWTService implements ITokenService {

    private final Logger logger = LoggerFactory.getLogger(JWTService.class);
    private final long EXPIRATION_TIME = 600*1000;  //milliseconds
    private ISecretKeyService secretKeyService;
    private Algorithm ALGORITHM;

    @Autowired
    public JWTService(ISecretKeyService secretKeyService) {
        super();
        logger.info("New JWTService");
        this.secretKeyService = secretKeyService;
        ALGORITHM = Algorithm.HMAC256(this.secretKeyService.toString());
    }

    @Override
    public String getToken(String email, String firstName) {
        String token = null;
        try {
            Date experationDate = new Date(System.currentTimeMillis() + EXPIRATION_TIME);
            Map<String, String> payload = new HashMap<>();
            payload.put("klantId", email);
            payload.put("firstName", firstName);

            token = JWT.create()
                    .withIssuer("auth0")
                   .withExpiresAt(experationDate)
                   .withPayload(payload)
                    .sign(ALGORITHM);

        } catch (JWTCreationException exception) {
             logger.error(exception.getMessage());
        }
        return token;
    }

    @Override
    public boolean authenticateToken(String token) {

        try {
            JWTVerifier verifier = JWT.require(ALGORITHM)
                    .withIssuer("auth0")
                    .build(); //Reusable verifier instance
          DecodedJWT decodedJWT = verifier.verify(token);
          if(decodedJWT.getExpiresAt().after(new Date(System.currentTimeMillis()))){
               return true;
           }
        } catch (JWTVerificationException exception) {
            logger.error(exception.getMessage());
        }
        return false;
    }
}