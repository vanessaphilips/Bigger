package com.example.project_bigbangk.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles
class JdbcClientDAOTest {

    private IClientDAO clientDAOUnderTest;

    @Autowired
    public JdbcClientDAOTest(IClientDAO clientDAOUnderTest) {
        super();
        this.clientDAOUnderTest = clientDAOUnderTest;
    }

    @Test
    public void setupTest() {
        assertThat(clientDAOUnderTest).isNotNull();
    }

    // TODO onderstaande nog in te vullen!

    @Test
    void saveClient() {
    }

    @Test
    void findClientByEmail() {
    }

    @Test
    void findAllClients() {
    }

    @Test
    void updateClient() {
    }

    @Test
    void findClientByLastName() {
    }
}