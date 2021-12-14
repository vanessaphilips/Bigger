package com.example.project_bigbangk.repository;

import com.example.project_bigbangk.model.Asset;
import com.example.project_bigbangk.model.Wallet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

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
        String slq = "Update wallet Set balance = ? WHERE IBAN = ?";
        jdbcTemplate.update(slq, wallet.getBalance(), wallet.getIban());
    }

    public Wallet findWalletByIban(String iban) {
        String slq = "Select * From wallet Where IBAN = ?;";
        Wallet wallet = null;
        try {
            wallet = jdbcTemplate.queryForObject(slq, new walletRowMapper(), iban);
        } catch (DataAccessException dataAccessException) {
            System.err.println(dataAccessException.getMessage());
        }
        return wallet;
    }

    public void updateWalletBalanceAndAsset(Wallet wallet, Asset asset) {
        String sql = "Update wallet_has_asset Set amount = ? Where IBAN = ? And code = ?;";
        jdbcTemplate.update(sql, wallet.getIban(),asset.getCode());
    }

    public Map<String, Double> findAssetCodeWithAmount(String iban) {
        String sql = "Select * From wallet_has_asset Where IBAN = ?;";

        Map<String, Double> asset = null;
        try {
           asset = jdbcTemplate.queryForObject(sql, new wallet_has_assetRowMapper(), iban);
        }  catch (DataAccessException dataAccessException) {
            System.err.println(dataAccessException.getMessage());
        }
        return asset;
    }


    public class walletRowMapper implements RowMapper<Wallet> {

        @Override
        public Wallet mapRow(ResultSet resultSet, int rowNum) throws SQLException {
            return new Wallet( resultSet.getString("IBAN"), resultSet.getInt("balance"));
        }
    }

    public class wallet_has_assetRowMapper implements RowMapper<Map<String, Double>> {

        @Override
        public Map<String,Double> mapRow(ResultSet resultSet, int rowNum) throws SQLException {
            Map<String, Double> asset = new HashMap<>();
            asset.put(resultSet.getString("code"), resultSet.getDouble("amount"));
            return asset;
        }
    }

}
