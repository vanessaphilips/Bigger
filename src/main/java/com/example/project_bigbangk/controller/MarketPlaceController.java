// Created by Deek
// Creation date 12/20/2021

package com.example.project_bigbangk.controller;

import com.example.project_bigbangk.model.Asset;
import com.example.project_bigbangk.model.DTO.LoginDTO;
import com.example.project_bigbangk.service.MarketPlaceService;
import com.example.project_bigbangk.service.Security.AuthenticateService;
import com.fasterxml.jackson.core.*;
import com.fasterxml.jackson.core.json.JsonWriteContext;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mysql.cj.x.protobuf.Mysqlx;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpGet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

@RestController
public class MarketPlaceController {

    private final Logger logger = LoggerFactory.getLogger(MarketPlaceController.class);
    private AuthenticateService authenticateService;
    private MarketPlaceService marketPlaceService;

    public MarketPlaceController(AuthenticateService authenticateService, MarketPlaceService marketPlaceService) {
        super();
        this.authenticateService = authenticateService;
        this.marketPlaceService = marketPlaceService;
        logger.info("New MarketPlaceController");
    }

    @GetMapping("/marketplace")
    public ResponseEntity<String> login(@RequestHeader String authorization) {
      // String token = request.getLastHeader("Authorization").getValue();
        String token = authorization;
        //check of authorisation in orde is-- check token
        if (authenticateService.authenticate(token)) {
            List<Asset> assets = marketPlaceService.getAllAssets();
            ObjectMapper mapper = new ObjectMapper();
            //Converting the Object to JSONString
            String jsonAssets = null;
            try {
                jsonAssets = mapper.writeValueAsString(assets);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
            System.out.println(jsonAssets);
            return ResponseEntity.ok().body(jsonAssets);
        } else {
            return ResponseEntity.status(401).body("token expired");
        }
    }
}