package com.example.project_bigbangk.repository;

import com.example.project_bigbangk.model.Bank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Hier wordt de bank informatie uit een Jdbc database gehaald.
 *
 * @Author Kelly Speelman - de Jonge
 */

@Repository
public class JdbcBankDAO implements IBankDAO{

    JdbcTemplate jdbcTemplate;

    @Autowired
    public JdbcBankDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void saveBank(Bank bank) {
        try {
            String sql = "INSERT INTO bank VALUES(?,?,?,?,?);";
            jdbcTemplate.update(sql, bank.getCode(), bank.getName(),
                    bank.getWallet().getIban(), bank.getStartingcapital(),
                    bank.getFeePercentage());
        } catch (Exception foutmelding){
            System.out.println(foutmelding.getMessage());
        }
    }

    public Bank findBank(String naam) {
        String sql = "SELECT * FROM bank WHERE name = ?;";
        Bank bank = null;
        try {
            bank = jdbcTemplate.queryForObject(sql, new BankRowMapper(), naam);
        } catch (DataAccessException emptyResult) {
            System.out.println(emptyResult.getMessage());
        }
        return bank;
    }

    public List<Bank> findAllBank(){
        String sql = "SELECT * FROM bank";
        return jdbcTemplate.query(sql, new BankRowMapper());
    }

    public void updateBank(Bank bank){
        try {
            String sql = "UPDATE bank SET name = ?, transactioncosts = ?, startingcapital = ? " +
                    "WHERE code = ?;";
            jdbcTemplate.update(sql, bank.getName(), bank.getFeePercentage(),
                    bank.getStartingcapital(), bank.getCode());
        } catch (Exception foutmelding){
            System.out.println(foutmelding.getMessage());
        }
    }

    private class BankRowMapper implements RowMapper<Bank> {
        @Override
        public Bank mapRow(ResultSet resultSet, int rowNumber) throws SQLException {
            return new Bank(resultSet.getString("name"),
                    resultSet.getString("code"),
                    resultSet.getDouble("transactioncosts"),
                    resultSet.getDouble("startingcapital"));
        }
    }
}
