package com.example.project_bigbangk.service;
/**
 * created by Pieter Jan Bleichrodt
 */
public interface ITokenService {
    String getToken(String email, String firstName);

    boolean authenticateToken(String token);
}
