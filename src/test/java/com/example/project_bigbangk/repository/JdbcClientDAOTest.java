package com.example.project_bigbangk.repository;

import com.example.project_bigbangk.model.Client;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import java.time.LocalDate;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Fail.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

@SpringBootTest
@ActiveProfiles("test")
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
        fail("Test not yet implemented");
    }

    @Test
    void findClientByEmail() {
        Client actual1 = clientDAOUnderTest.findClientByEmail("sander@deboer.nl");
        Client expected1 = new Client("sander@deboer.nl", "Sander", "de",
                "Boer", LocalDate.of(1966,9,9), "123456789",
                "sanderdeboer", null, null);
        assertThat(expected1).isEqualTo(actual1);

        Client actual2 = clientDAOUnderTest.findClientByEmail("nicole@wong.nl");
        Client expected2 = new Client("nicole@wong.nl", "Nicole", "",
                "Wong", LocalDate.of(1973, 01, 01), "123456789",
                "nicolewong", null, null);
        assertEquals(expected2, actual2);
    }

    @Test
    void findAllClients() {
        fail("Test not yet implemented");
    }

    @Test
    void updateClient() {
        fail("Test not yet implemented");
    }

    @Test
    void findClientByLastName() {
        fail("Test not yet implemented");
    }
}