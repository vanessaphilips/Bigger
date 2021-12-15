package com.example.project_bigbangk.repository;

import com.example.project_bigbangk.model.Bank;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
class JdbcBankDAOTest {

    private JdbcBankDAO jdbcBankDAOTest;

    @Autowired
    public JdbcBankDAOTest(JdbcBankDAO doaUnderTest) {
        super();
        this.jdbcBankDAOTest = doaUnderTest;
    }

    @Test
    void saveBank() {
    }

    @Test
    void findBank() {
        Bank actual = jdbcBankDAOTest.findBank("Big Bangk");
        Bank expected = new Bank("Big Bangk", "BGBK", 5, 1000.00);
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void findAllBank() {
        List<Bank> actual = jdbcBankDAOTest.findAllBank();
        List<Bank> expected = null;
        expected.add(new Bank("Big Bangk", "BGBK", 5, 1000.00));
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void updateBank() {
    }
}