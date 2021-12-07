package com.example.project_bigbangk.repository;

import com.example.project_bigbangk.model.Wallet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class JdbcWalletDAO {

    JdbcTemplate jdbcTemplate;

    @Autowired
    public JdbcWalletDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void createNewWallet(Wallet wallet) {
        String slq = "Insert into wallet values(?,?);";
        jdbcTemplate.update(slq, wallet.getIban(), wallet.getBalance());
    }

    public void updateBalance(Wallet wallet) {
        String slq = "Update wallet Set balance = ?";
        jdbcTemplate.update(slq, wallet.getBalance());
    }

    public Wallet findWalletByIban(String iban) {
        String slq = "Select * From wallet Where IBAN = ?;";
        return jdbcTemplate.queryForObject(slq, new walletRowMapper(), iban);
    }

    public List<Wallet> findAllWallets() {
        String slq = "Select * From wallet;";
        return jdbcTemplate.query(slq, new walletRowMapper());
    }

    public class walletRowMapper implements RowMapper<Wallet> {

        @Override
        public Wallet mapRow(ResultSet resultSet, int rowNum) throws SQLException {
            return new Wallet( resultSet.getString("IBAN"), resultSet.getInt("balance"));
        }
    }
}
