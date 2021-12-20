package com.example.project_bigbangk.repository;

import com.example.project_bigbangk.model.Bank;
import com.example.project_bigbangk.model.Wallet;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
class JdbcBankDAOTest {

    private JdbcBankDAO jdbcBankDAOTest;
    private Bank mockBank = Mockito.mock(Bank.class);
    private Wallet mockWallet = Mockito.mock(Wallet.class);
    private Bank newBank;
    private Bank bigBangk;
    private Bank newBankUpdate;

    @BeforeEach
    public void startUp (){
        Mockito.when(mockWallet.getIban()).thenReturn("NL17 BGBK 7265511");
        Mockito.when(mockBank.getWallet()).thenReturn(mockWallet);
        newBank = new Bank("Test bank", "TSBK", 3.0, 210.00);
        bigBangk = new Bank("Big Bangk", "BGBK", 5.0, 1000.00);
        newBankUpdate = new Bank("Test bank aangepast", "TSBK", 9.0, 6710.00);
    }

    @Autowired
    public JdbcBankDAOTest(JdbcBankDAO doaUnderTest) {
        super();
        this.jdbcBankDAOTest = doaUnderTest;
    }

    @Test
    void saveBank() {
        newBank.setWallet(mockWallet);
        jdbcBankDAOTest.saveBank(newBank);
    }

    @Test
    void findBank() {
        Bank actual = jdbcBankDAOTest.findBank("Big Bangk");
        Bank expected = bigBangk;
        assertThat(actual).isEqualTo(expected);

        actual = jdbcBankDAOTest.findBank("Test bank");
        expected = newBank;
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void updateBank() {
        jdbcBankDAOTest.updateBank(newBankUpdate);
    }

    @Test
    void findAllBank() {
        List<Bank> actual = jdbcBankDAOTest.findAllBank();
        List<Bank> expected = new ArrayList<>();
        expected.add(bigBangk);
        expected.add(newBankUpdate);
        assertThat(actual).isEqualTo(expected);
    }
}