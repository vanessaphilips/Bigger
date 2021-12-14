package com.example.project_bigbangk.Utilities;

import org.springframework.security.crypto.bcrypt.BCrypt;

/**
 * Hier staat is de hashHelper die gebruikt kan worden om wachtwoorden te hashen
 * of het paswoord te controleren met een gehased paswoord
 *
 * @Author Kelly Speelman - de Jonge
 */

public class HashHelper {

    public static String hash(String password, String pepper) {
        return BCrypt.hashpw(password, saltCreate(pepper));
    }

    public static String saltCreate(String pepper){
        return BCrypt.gensalt()+pepper;
    }

    public static Boolean hashCheck(String password, String hashedPassword){
        return BCrypt.checkpw(password, hashedPassword);
    }
}
