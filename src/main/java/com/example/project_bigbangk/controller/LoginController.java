// Created by Deek
// Creation date 12/6/2021

package com.example.project_bigbangk.controller;

import com.example.project_bigbangk.model.DTO.LoginDTO;
import com.example.project_bigbangk.service.LoginService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * created by Pieter Jan Bleichrodt
 */
@RestController
public class LoginController {

    private final Logger logger = LoggerFactory.getLogger(LoginController.class);
    private LoginService loginService;

    public LoginController(LoginService loginService) {
        super();
        logger.info("New LoginController");
        this.loginService = loginService;
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginDTO loginDTO) {
        String token = loginService.login(loginDTO.getEmail(), loginDTO.getPassword());
        if (token != null) {
            return ResponseEntity.ok().header("Authorization", token).body("login succesfull");
        } else {
            return ResponseEntity.status(401).body("Username or password not valid");
        }
    }
}