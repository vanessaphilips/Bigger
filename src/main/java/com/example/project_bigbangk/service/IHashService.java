package com.example.project_bigbangk.service;
/**
 * created by Pieter Jan Bleichrodt
 */
public interface IHashService {
    String hash(String password);

    Boolean hashCheck(String password, String hashedPassword);
}
