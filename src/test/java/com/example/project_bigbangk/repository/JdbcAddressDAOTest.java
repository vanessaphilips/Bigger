package com.example.project_bigbangk.repository;

import com.example.project_bigbangk.model.Address;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class JdbcAddressDAOTest {

    private Address address1 = new Address("3066JB", "Dijkstraat", 1, "Urk", "NL" );
    private Address address2 = new Address("1200AA", "Boserf", 12, "Groet", "NL");

    @BeforeEach
    void setUp() {
    }

    @Test
    void saveAddress() {
    }

    @Test
    void findAddressByEmail() {
    }

    @Test
    void findAllAddresses() {
    }

    @Test
    void findAddressByPostalcode() {
    }
}