package com.example.project_bigbangk.service;

import com.example.project_bigbangk.model.Client;
import com.example.project_bigbangk.model.Wallet;
import com.example.project_bigbangk.service.Security.AuthenticateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author Kelly Speelman - de Jonge
 */

@Service
public class WalletService {
    private final AuthenticateService authenticateService;

    @Autowired
    public WalletService (AuthenticateService authenticateService) {
        super();
        this.authenticateService = authenticateService;
    }

    public Wallet getWalletClient(String token){
        Client client = authenticateService.getClientFromToken(token);
        return client.getWallet();
    }
}
