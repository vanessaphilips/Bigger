package com.example.project_bigbangk.service;

import com.auth0.jwt.interfaces.DecodedJWT;

public interface ITokenService {
    String getToken(String email, String firstName);

    DecodedJWT authenticateToken(String token);
}
