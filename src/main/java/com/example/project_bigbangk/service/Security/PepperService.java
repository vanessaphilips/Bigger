package com.example.project_bigbangk.service.Security;

import org.springframework.stereotype.Service;

/**
 * Hier staat de pepper die bij elk wachtwoord wordt gebruikt in de hash
 *
 * @Author Kelly Speelman - de Jonge
 */

@Service
public class PepperService {
    private static final String PEPPER = "HetEenIsHetAnderNietDezeZinVerzinJeNiet";

    public String getPeper() {
        return PEPPER;
    }
}
