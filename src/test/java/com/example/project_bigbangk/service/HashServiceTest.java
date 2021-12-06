package com.example.project_bigbangk.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCrypt;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;

class HashServiceTest {
    PepperService pepperService;
    HashService hashService;

    @BeforeEach
    void set(){
        pepperService = new PepperService();
        hashService = new HashService(pepperService);
    }

    @Test
    void hash() {
        String password = "DeString";
        String hash = hashService.hash(password);
        assertThat(hash).isNotNull();
        assertTrue(BCrypt.checkpw(password, hash));

        password = "AndereWorden";
        hash = hashService.hash(password);
        assertThat(hash).isNotNull();
        assertTrue(BCrypt.checkpw(password, hash));
    }

    @Test
    void hashCheackTest(){
        String password = "DeString";

        String hashedPassword = "$2a$10$GdlQ2J6MFlf4ECZR8C/4Y.BpT9rSanF5edYmr38JWCP9u2CsfcFC.";
        assertTrue(hashService.hashCheck(password, hashedPassword));
        hashedPassword = "$2a$10$uLLVDQ9NTrqWLcVr.JIa8eS1DVUAeTkT01AO9ePu.b1H1qYXylopm";
        assertTrue(hashService.hashCheck(password, hashedPassword));
        hashedPassword = "$2a$10$Ih.V9B4CN6IGUEye4WqGPeiLScPWaJww2IzTCHEkdRzpz.E03eHoC";
        assertTrue(hashService.hashCheck(password, hashedPassword));
        hashedPassword = "$2a$10$fn/WbW9Ika3xaMeGtvCMw.rmcnapZm7iJiaRQtYvrdTN2CBlyunp6";
        assertFalse(hashService.hashCheck(password, hashedPassword));

        password = "AndereWorden";

        hashedPassword = "$2a$10$4gdZeMtc7mCr7MXyvuonF.B954jm9Z1jElchdj1o3pRVoKe74F1ue";
        assertTrue(hashService.hashCheck(password, hashedPassword));
        hashedPassword = "$2a$10$p6A.N6i3zANEVyEC9MSBle/WStleP8pd3wBb9execO60BKPVNPtC2";
        assertTrue(hashService.hashCheck(password, hashedPassword));
        hashedPassword = "$2a$10$fn/WbW9Ika3xaMeGtvCMw.rmcnapZm7iJiaRQtYvrdTN2CBlyunp6";
        assertTrue(hashService.hashCheck(password, hashedPassword));
        hashedPassword = "$2a$10$Ih.V9B4CN6IGUEye4WqGPeiLScPWaJww2IzTCHEkdRzpz.E03eHoC";
        assertFalse(hashService.hashCheck(password, hashedPassword));
    }
}