package com.example.project_bigbangk.repository;

import com.example.project_bigbangk.model.Asset;
import com.example.project_bigbangk.model.Wallet;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
import java.util.Map;

public interface IWalletDAO {

    public void createNewWallet(Wallet wallet);

    public void updateBalance(Wallet wallet);

    public Wallet findWalletByIban(String iban);

    public void updateWalletBalanceAndAsset(Wallet wallet, Asset asset);

    public Double findAmountOfAsset(String iban, String assetCode);
}
