package com.example.project_bigbangk.service;

import com.example.project_bigbangk.model.HashHelper;
import org.springframework.stereotype.Service;

@Service
public class HashService implements IHashService {
    private final PepperService pepperService;

    public HashService(PepperService pepperService) {
        this.pepperService = pepperService;
    }

    @Override
    public String hash(String password) {
        return HashHelper.hash(password, pepperService.getPeper());
    }

    @Override
    public Boolean hashCheck(String password, String hashedPassword){
        return HashHelper.hashCheck(password, hashedPassword);
    }
}
