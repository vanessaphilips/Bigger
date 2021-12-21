package com.example.project_bigbangk.repository;

import com.example.project_bigbangk.model.Address;
import com.example.project_bigbangk.model.Client;
import com.example.project_bigbangk.model.Wallet;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import java.time.LocalDate;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Fail.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

/**
 * ClientDAO test created by Vanessa Philips.
 */

@SpringBootTest
@ActiveProfiles("test")
class JdbcClientDAOTest {

    private final JdbcClientDAO clientDAOTest;
    private final Wallet mockWallet = Mockito.mock(Wallet.class);
    private final Address mockAddress = Mockito.mock(Address.class);
    private Client newClient;
    private Client updateClient;

    @Autowired
    public JdbcClientDAOTest(JdbcClientDAO clientDAOTest) {
        super();
        this.clientDAOTest = clientDAOTest;
    }

    @BeforeEach
    public void setupTest() {
        assertThat(clientDAOTest).isNotNull();
    }

    // TODO onderstaande nog in te vullen!

    @Test
    void saveClient() {
        fail("Test not yet implemented");
    }

    @Test
    void findClientByEmail() {
        Client actual1 = clientDAOTest.findClientByEmail("sander@deboer.nl");
        Client expected1 = new Client("sander@deboer.nl", "Sander", "de",
                "Boer", LocalDate.of(1966,9,9), "123456789",
                "sanderdeboer", null, null);
        assertThat(expected1).isEqualTo(actual1);

        Client actual2 = clientDAOTest.findClientByEmail("nicole@wong.nl");
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