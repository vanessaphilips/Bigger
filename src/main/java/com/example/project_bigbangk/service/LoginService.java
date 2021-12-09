package com.example.project_bigbangk.service;

import com.example.project_bigbangk.model.Client;
import com.example.project_bigbangk.repository.RootRepository;
import com.example.project_bigbangk.service.Security.AuthenticateService;
import com.example.project_bigbangk.service.Security.ITokenService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * created by Pieter Jan Bleichrodt
 * Creation date 12/3/2021
 */
@Service
public class LoginService {
    private final String TOKEN_PREFIX = "Bearer ";
    private final Logger logger = LoggerFactory.getLogger(LoginService.class);
    RootRepository rootRepository;
    AuthenticateService authenticateService;
    ITokenService jwtService;

    @Autowired
    public LoginService(AuthenticateService authenticateService, ITokenService jwtService, RootRepository rootRepository) {
        super();
        logger.info("New LoginService");
        this.rootRepository = rootRepository;
        this.authenticateService = authenticateService;
        this.jwtService = jwtService;
    }

    public String login(String email, String password) {
        if ((email != null && password != null && authenticateService.authenticate(email, password))) {
            Client client = rootRepository.findClientByEmail(email);
            String token = jwtService.getToken(email, client.getFirstName());
            logger.info(String.format("Login user %s succesfull", client.getEmail()));
            return TOKEN_PREFIX + token;
        }
        logger.info(String.format("Wrong combination of email and password for %s", email));
        return null;

    }
}