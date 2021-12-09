package com.example.project_bigbangk.service;

import org.springframework.stereotype.Service;

/**
 * created by Kelly Speelman - de Jonge
 */

@Service
public class PepperService {
    private static final String PEPPER = "HetEenIsHetAnderNietDezeZinVerzinJeNiet";

    public String getPeper() {
        return PEPPER;
    }
}
