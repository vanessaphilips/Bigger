package com.example.project_bigbangk.service;

import com.auth0.jwt.interfaces.DecodedJWT;

/**
 * created by Pieter Jan Bleichrodt
 */
public interface ITokenService {
    String getToken(String email, String firstName);

    boolean authenticateToken(String token);

    DecodedJWT decodeToken(String token);
}
