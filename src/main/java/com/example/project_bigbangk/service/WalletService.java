package com.example.project_bigbangk.service;

import com.example.project_bigbangk.model.Asset;
import com.example.project_bigbangk.model.Wallet;
import com.example.project_bigbangk.repository.JdbcWalletDAO;
import com.example.project_bigbangk.repository.RootRepository;
import org.springframework.stereotype.Service;

@Service
public class WalletService {

    private final int START_CAPITAL_NEW_USER = 10000;

    private RootRepository rootRepository;
    private IbanGeneratorService ibanGeneratorService;

    public WalletService(JdbcWalletDAO jdbcWalletDAO, RootRepository rootRepository, IbanGeneratorService ibanGeneratorService) {
        this.rootRepository = rootRepository;
        this.ibanGeneratorService = ibanGeneratorService;
    }

    public void saveNewWallet() {
        Wallet wallet = new Wallet(ibanGeneratorService.getIban(), START_CAPITAL_NEW_USER);
        rootRepository.saveNewWallet(wallet);
    }

    public void updateWalletBalanceAndAsset(Wallet wallet, Asset asset, double amount) {
        rootRepository.updateWalletBalanceAndAsset(wallet, asset, amount);
    }

    public Wallet findWalletWithAssetByIban(String iban){
        return rootRepository.findWalletWithAssetByIban(iban);
    }

}
