package com.example.project_bigbangk.service;

public interface IHashService {
    String hash(String password);

    Boolean hashCheck(String password, String hashedPassword);
}
