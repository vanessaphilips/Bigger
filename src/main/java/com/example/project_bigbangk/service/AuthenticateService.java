// Created by Deek
// Creation date 12/3/2021

package com.example.project_bigbangk.service;


import com.example.project_bigbangk.model.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthenticateService {
    ClientService clientService;
    ITokenService tokenService;
    IHashService hashService;

    @Autowired
    public AuthenticateService(ITokenService tokenService, ClientService clientService, IHashService hashService) {
        super();
        this.clientService = clientService;
        this.tokenService = tokenService;
        this.hashService = hashService;
    }

    public boolean authenticate(String email, String password) {
        Client client = clientService.getClientByEmail(email);
        if (client != null) {
            return hashService.hashCheck(password, client.getPassWord());
        }
        return false;
    }

    public boolean authenticate(String token) {
        if (tokenService.authenticateToken(token)) {
            return true;
        }
        return false;
    }
}