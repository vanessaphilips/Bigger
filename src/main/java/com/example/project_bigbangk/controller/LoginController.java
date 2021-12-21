package com.example.project_bigbangk.controller;

import com.example.project_bigbangk.model.DTO.LoginDTO;
import com.example.project_bigbangk.service.LoginService;
import org.apache.coyote.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.config.web.servlet.headers.HeadersSecurityMarker;
import org.springframework.web.bind.annotation.*;

/**
 * created by Pieter Jan Bleichrodt
 * Creation date 12/6/2021
 */
@CrossOrigin
@RestController


public class LoginController {

    private final Logger logger = LoggerFactory.getLogger(LoginController.class);
    private LoginService loginService;

    public LoginController(LoginService loginService) {
        super();
        logger.info("New LoginController");
        this.loginService = loginService;
    }

    @PostMapping(value = "/login", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity login(@RequestBody LoginDTO loginDTO) {
        String token = loginService.login(loginDTO.getEmail(), loginDTO.getPassword());
        if (token != null) {
            return ResponseEntity.ok().contentType(MediaType.valueOf("Application/jwt")).body(token);
        } else {
            return ResponseEntity.status(401).body("Username or password not valid");
        }
    }
}