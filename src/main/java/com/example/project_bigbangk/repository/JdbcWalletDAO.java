package com.example.project_bigbangk.repository;

import com.example.project_bigbangk.model.Wallet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class JdbcWalletDAO implements IWalletDAO{

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
        Wallet wallet = null;
        try {
            wallet = jdbcTemplate.queryForObject(slq, new walletRowMapper(), iban);
        } catch (EmptyResultDataAccessException noResult) {
            System.err.println(noResult.getMessage());
        } catch (DataAccessException dataAccessException) {
            System.err.println(dataAccessException.getMessage());
        }
        return wallet;
    }

    public class walletRowMapper implements RowMapper<Wallet> {

        @Override
        public Wallet mapRow(ResultSet resultSet, int rowNum) throws SQLException {
            return new Wallet( resultSet.getString("IBAN"), resultSet.getInt("balance"));
        }
    }
}
