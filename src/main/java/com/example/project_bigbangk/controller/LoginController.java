// Created by Deek
// Creation date 12/6/2021

package com.example.project_bigbangk.controller;

import com.example.project_bigbangk.model.LoginDTO;
import com.example.project_bigbangk.service.LoginService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.http.HttpResponse;

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
        if(token!=null){
            return ResponseEntity.ok(token);
        }else{
            return ResponseEntity.status(401).body("Combination of username and password unknown");
        }
    }
}