package com.example.project_bigbangk.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.project_bigbangk.model.Client;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class JWTService_SecretKeyService_IntegrationTest {

    ISecretKeyService secretKeyService = new SecretKeyService();
    ITokenService jwtService = new JWTService(secretKeyService);
    String token = jwtService.getToken("deek@deek.nl", "Deek");

    @Test
    void GetToken() {
        DecodedJWT decodedJWT = jwtService.decodeToken(token);
        System.out.println(decodedJWT.getExpiresAt());
        assertEquals("www.bigbangk.com", decodedJWT.getAudience().stream().findFirst().get());
        assertEquals("deek@deek.nl", decodedJWT.getSubject());
        assertEquals("Deek", decodedJWT.getClaim("firstName").asString());
        assertEquals("deek@deek.nl", decodedJWT.getClaim("email").asString());
       assertEquals("Client", decodedJWT.getClaim("role").asString());
    }

    @Test
    void AuthenticateFail() {
        String tokenFail =
                "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJhdXRoMCIsImlhdCI6MTYzODk2NDEwMywiZXhwIjoxNjM4OTY1MzIyLCJhdWQiOiJ3d3cuYmlnYmFuZ2suY29tIiwic3ViIjoiZGVla0BkZWVrLm5sIiwiZmlyc3ROYW1lIjoiRGVlayIsImVtYWlsIjoiZGVla0BkZWVrLm5sIiwicm9sZSI6IkNsaWVudCJ9.70WHKoPseGkbaOthNWiBQTadsI4GRsxI8ByXHJy6xlA";
        assertFalse(jwtService.authenticateToken(tokenFail));
    }

    @Test
    void getTokenAndAuthenticateSucces() {
        assertTrue(jwtService.authenticateToken(token));
    }
}