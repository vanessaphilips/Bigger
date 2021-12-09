// Created by Deek
// Creation date 12/3/2021

package com.example.project_bigbangk.service;

import com.example.project_bigbangk.model.Client;
import com.example.project_bigbangk.service.Security.AuthenticateService;
import com.example.project_bigbangk.service.Security.ITokenService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * created by Pieter Jan Bleichrodt
 */
@Service
public class LoginService {
    private final String TOKEN_PREFIX = "Bearer ";
    private final Logger logger = LoggerFactory.getLogger(LoginService.class);
    ClientService clientService;
    AuthenticateService authenticateService;
    ITokenService jwtService;

    @Autowired
    public LoginService(AuthenticateService authenticateService, ITokenService jwtService, ClientService clientService) {
        super();
        logger.info("New LoginService");
        this.clientService = clientService;
        this.authenticateService = authenticateService;
        this.jwtService = jwtService;
    }

    public String login(String email, String password) {
        if ((email != null && password != null && authenticateService.authenticate(email, password))) {
            Client client = clientService.getClientByEmail(email);
            String token = jwtService.getToken(email, client.getFirstName());
            logger.info(String.format("Login user %s succesfull", client.getEmail()));
            return TOKEN_PREFIX + token;
        }
        logger.info(String.format("Wrong combination of email and password for %s", email));
        return null;

    }
}