package com.example.project_bigbangk.service;

import com.example.project_bigbangk.model.Asset;
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

    public void createNewWalletWithAssets() {
        Wallet wallet = new Wallet(ibanGeneratorService.getIban(), START_CAPITAL_NEW_USER);
    }

    public void updateWalletBalanceAndAsset(Wallet wallet, Asset asset) {
        rootRepository.updateWalletBalanceAndAsset(wallet, asset);
    }

    //TODO deze is uitgecoment omdat de methode in de rootrepository nog niet klaar is
    public Wallet findWalletWithAssetByIban(String iban){
        return rootRepository.findWalletWithAssetByIban(iban);
    }

}
