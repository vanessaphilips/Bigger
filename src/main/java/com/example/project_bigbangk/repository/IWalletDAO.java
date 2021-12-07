package com.example.project_bigbangk.repository;

import com.example.project_bigbangk.model.Wallet;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public interface IWalletDAO {

public void createNewWallet(Wallet wallet);

public void updateBalance(Wallet wallet);

public Wallet findWalletByIban(String iban);

}
