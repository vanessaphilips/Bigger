package com.example.project_bigbangk.service.Security;
/**
 * created by Pieter Jan Bleichrodt
 */
public interface ITokenService {
    String getToken(String email, String firstName);

    boolean authenticateToken(String token);

    public String getEmailFromToken(String token) ;
}
