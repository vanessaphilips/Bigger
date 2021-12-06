package com.example.project_bigbangk.model;

import com.example.project_bigbangk.service.PepperService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;

class HashHelperTest {
    PepperService pepperService;

    @BeforeEach
    void set(){
        pepperService = new PepperService();
    }

    @Test
    void hashTest() {
        String pepper = pepperService.getPeper();

        String paswoord = "DeString";
        String hash = HashHelper.hash(paswoord,pepper);
        String hash2 = HashHelper.hash(paswoord,pepper);
        assertThat(hash).isNotEqualTo(hash2);
        assertTrue(HashHelper.hashCheck(paswoord, hash));
        assertTrue(HashHelper.hashCheck(paswoord, hash2));

        paswoord = "DeTestVanHash";
        hash = HashHelper.hash(paswoord,pepper);
        hash2 = HashHelper.hash(paswoord,pepper);
        assertThat(hash).isNotEqualTo(hash2);
        assertTrue(HashHelper.hashCheck(paswoord, hash));
        assertTrue(HashHelper.hashCheck(paswoord, hash2));

        paswoord = "HoeLangKanDeZinPaswoordZijn?LangerDanDit?";
        hash = HashHelper.hash(paswoord,pepper);
        hash2 = HashHelper.hash(paswoord,pepper);
        assertThat(hash).isNotEqualTo(hash2);
        assertTrue(HashHelper.hashCheck(paswoord, hash));
        assertTrue(HashHelper.hashCheck(paswoord, hash2));
    }

    @Test
    void saltCreateTest() {
        String salt1 = HashHelper.saltCreate(pepperService.getPeper());
        String salt2 = HashHelper.saltCreate(pepperService.getPeper());
        assertThat(salt1).isNotEqualTo(salt2).endsWith(pepperService.getPeper());

        salt1 = HashHelper.saltCreate(pepperService.getPeper());
        salt2 = HashHelper.saltCreate(pepperService.getPeper());
        assertThat(salt1).isNotEqualTo(salt2).endsWith(pepperService.getPeper());

        salt1 = HashHelper.saltCreate(pepperService.getPeper());
        salt2 = HashHelper.saltCreate(pepperService.getPeper());
        assertThat(salt1).isNotEqualTo(salt2).endsWith(pepperService.getPeper());
    }
}