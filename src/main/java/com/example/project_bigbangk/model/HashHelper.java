package com.example.project_bigbangk.model;

import com.example.project_bigbangk.repository.ClientDAO;
import org.springframework.security.crypto.bcrypt.BCrypt;

public class HashHelper {

    public static String hash(String password, String pepper) {
        return BCrypt.hashpw(password, saltCreate(pepper));
    }

    public static String saltCreate(String pepper){
        return BCrypt.gensalt()+pepper;
    }

    public static Boolean hashCheck(String password, String username, String pepper){
        //ClientDAO
        String hashedPassword = " "; // Haalt het wachtwoord uit de database doormiddel van de username
        return BCrypt.checkpw(password+pepper, hashedPassword);
    }
}
