package com.example.project_bigbangk.service;

public interface ITokenService {
    String getToken(String email, String firstName);

    boolean authenticateToken(String token);
}
