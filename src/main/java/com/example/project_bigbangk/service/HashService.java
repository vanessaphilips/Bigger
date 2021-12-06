package com.example.project_bigbangk.service;

import com.example.project_bigbangk.model.HashHelper;

public class HashService {
    private final PepperService pepperService;

    public HashService(PepperService pepperService) {
        this.pepperService = pepperService;
    }

    public String hash(String password) {
        return HashHelper.hash(password, pepperService.getPeper());
    }

    public Boolean hashCheck(String password, String hashedPassword){
        return HashHelper.hashCheck(password, hashedPassword);
    }
}
