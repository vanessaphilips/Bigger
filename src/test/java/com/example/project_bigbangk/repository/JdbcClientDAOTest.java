package com.example.project_bigbangk.repository;

import com.example.project_bigbangk.model.Client;
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

//    @Test
//    void findClientByEmail() {
//        Client actual = clientDAOUnderTest.findClientByEmail("sander@deboer.nl");
//        Client expected = new Client("Sander", "de", "Boer", "sander@deboer.nl",
//                "123456789", 1966-09-09, "sanderdeboer");
//        assertThat(expected).isEqualTo(actual);
//    }

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