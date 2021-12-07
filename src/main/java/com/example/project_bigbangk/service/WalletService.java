package com.example.project_bigbangk.service;

import com.example.project_bigbangk.model.Wallet;
import com.example.project_bigbangk.repository.JdbcWalletDAO;
import com.example.project_bigbangk.repository.RootRepository;
import org.springframework.stereotype.Service;

@Service
public class WalletService {

    private final int START_CAPITAL_NEW_USER = 10000;

    private JdbcWalletDAO jdbcWalletDAO;
    private RootRepository rootRepository;
    private IbanGeneratorService ibanGeneratorService;

    public WalletService(JdbcWalletDAO jdbcWalletDAO, RootRepository rootRepository) {
        this.jdbcWalletDAO = jdbcWalletDAO;
        this.rootRepository = rootRepository;
    }

    public void createNewWallet() {
        Wallet wallet = new Wallet(ibanGeneratorService.ibanGenerator(), START_CAPITAL_NEW_USER);
    }

    private void updateWalletBalanceAndAsset(Wallet wallet) {
    }

    private Wallet findWalletWithAssetByIban(String iban){
        return null;
    }

}
