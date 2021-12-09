package com.example.project_bigbangk.service.Security;

import org.springframework.stereotype.Service;

@Service
public class PepperService {
    private static final String PEPPER = "HetEenIsHetAnderNietDezeZinVerzinJeNiet";

    public String getPeper() {
        return PEPPER;
    }
}
