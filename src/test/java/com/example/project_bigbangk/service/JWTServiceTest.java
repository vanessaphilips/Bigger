package com.example.project_bigbangk.service;

import com.example.project_bigbangk.service.Security.ISecretKeyService;
import com.example.project_bigbangk.service.Security.ITokenService;
import com.example.project_bigbangk.service.Security.JWTService;
import com.example.project_bigbangk.service.Security.SecretKeyService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author P.J.Bleichrodt
 * created 12/9/2021
 */
class JWTServiceTest {

    ISecretKeyService secretKeyService = new SecretKeyService();
    ITokenService jwtService = new JWTService(secretKeyService);
    String token = jwtService.getToken("deek@deek.nl", "Deek");

    @Test
    void GetToken() {
        String email = jwtService.getUserIdFromToken(token);
        assertEquals("deek@deek.nl", email);
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