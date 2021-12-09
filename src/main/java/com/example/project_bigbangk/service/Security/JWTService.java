// Created by Deek
// Creation date 12/3/2021

package com.example.project_bigbangk.service.Security;
/**
 * created by Pieter Jan Bleichrodt
 */

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.project_bigbangk.model.Client;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class JWTService implements ITokenService {

    private final Logger logger = LoggerFactory.getLogger(JWTService.class);
    private final long EXPIRATION_TIME = 1200 * 1000;  //milliseconds
    private ISecretKeyService secretKeyService;
    private Algorithm ALGORITHM;
    private final String AUDIENCE = "https://www.bigbangk.com";

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
            Long timeNow = System.currentTimeMillis();
            Date issuedAt = new Date(timeNow);
            Date experationDate = new Date(timeNow + EXPIRATION_TIME);
            token = JWT.create().withIssuer("auth0").withIssuedAt(issuedAt)
                    .withExpiresAt(experationDate)
                    .withAudience(AUDIENCE)
                    .withSubject(email)
                    .withClaim("firstName", firstName)
                    .withClaim("email", email)
                    .withClaim("role", Client.class.getSimpleName())
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
            if (decodedJWT.getExpiresAt().after(new Date(System.currentTimeMillis()))) {
                return true;
            }
        } catch (JWTVerificationException exception) {
            logger.error(exception.getMessage());
        }
        return false;
    }

    /**
     *
     * @param token decode a JWT
     * @return email
     */
    @Override
    public String getUserIdFromtoken(String token) {
        try {
            JWTVerifier verifier = JWT.require(ALGORITHM)
                    .withIssuer("auth0")
                    .build();
           return verifier.verify(token).getClaim("email").asString();

        } catch (JWTVerificationException exception) {
            logger.error(exception.getMessage());
        }
        return null;
    }
}