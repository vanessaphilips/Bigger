package com.example.project_bigbangk.repository;

import com.example.project_bigbangk.model.Wallet;
import org.springframework.jdbc.core.JdbcTemplate;

public interface IWalletDAO {

public void saveWallet(Wallet wallet);

public void updateBalance(Wallet wallet);

public void findWalletByIban(String iban);

public void findAllWallets();
}
