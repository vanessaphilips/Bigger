// Created by Deek
// Creation date 12/7/2021

package com.example.project_bigbangk.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * created by Pieter Jan Bleichrodt
 */
public class LoginDTO {

    private final Logger logger = LoggerFactory.getLogger(LoginDTO.class);

    private String email;
    private String password;
    public LoginDTO(String email, String password ) {
        super();
        logger.info("New LoginDTO");
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


}