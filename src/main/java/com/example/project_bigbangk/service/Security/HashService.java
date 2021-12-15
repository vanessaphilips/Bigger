package com.example.project_bigbangk.service.Security;

import com.example.project_bigbangk.Utilities.HashHelper;
import org.springframework.stereotype.Service;

/**
 * Hier staat is de hashService die gebruikt kan worden om wachtwoorden te hashen
 * of het paswoord te controleren met een gehased paswoord
 *
 * @Author Kelly Speelman - de Jonge
 */

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
