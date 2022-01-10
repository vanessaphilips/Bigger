package com.example.project_bigbangk.repository;

import com.example.project_bigbangk.model.Asset;
import com.example.project_bigbangk.model.Wallet;
import org.springframework.dao.DataAccessException;

public interface IWalletDAO {

    public void saveNewWallet(Wallet wallet);

    public void updateBalance(Wallet wallet);

    public Wallet findWalletByIban(String iban);

    public void updateWalletAssets(Wallet wallet, Asset asset, double amount);

    public Double findAmountOfAsset(String iban, String assetCode);

    public Wallet findWalletByEmail(String email);

    public Wallet findWalletByBankCode(String bankCode);

}
