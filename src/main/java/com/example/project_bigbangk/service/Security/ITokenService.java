package com.example.project_bigbangk.service.Security;

import com.auth0.jwt.interfaces.DecodedJWT;

/**
 * created by Pieter Jan Bleichrodt
 */
public interface ITokenService {
    String getToken(String email, String firstName);

    boolean authenticateToken(String token);

    public String getUserIdFromtoken(String token) ;
}
