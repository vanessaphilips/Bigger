package com.example.project_bigbangk.service;

import com.example.project_bigbangk.model.IbanGenerator;
import com.example.project_bigbangk.model.Wallet;
import com.example.project_bigbangk.repository.RootRepository;
import com.example.project_bigbangk.repository.WalletDAO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WalletService {

    private final int START_CAPITAL_NEW_USER = 10000;

    private WalletDAO walletDAO;
    private RootRepository rootRepository;
    private IbanGenerator ibanGenerator;

    public WalletService(WalletDAO walletDAO, RootRepository rootRepository) {
        this.walletDAO = walletDAO;
        this.rootRepository = rootRepository;
    }

    public Wallet createNewWallet() {
        return new Wallet(ibanGenerator.ibanGenerator(), START_CAPITAL_NEW_USER);
    }

    public Wallet findWalletByIban (String iban) {
        return walletDAO.findWalletByIban(iban);
    }

    public List<Wallet> getAllWallets() {
        return walletDAO.findAllWallets();
    }

    private void updateWalletBalance(Wallet wallet) {
        walletDAO.updateBalance(wallet);
    }
}
