// Created by Deek
// Creation date 12/20/2021

package com.example.project_bigbangk.controller;

import com.example.project_bigbangk.model.Asset;
import com.example.project_bigbangk.model.DTO.AssetDTO;
import com.example.project_bigbangk.model.DTO.LoginDTO;
import com.example.project_bigbangk.service.MarketPlaceService;
import com.example.project_bigbangk.service.Security.AuthenticateService;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.core.*;
import com.fasterxml.jackson.core.json.JsonWriteContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.util.JSONPObject;
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

@CrossOrigin
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
    @ResponseBody
    public ResponseEntity<String> login(@RequestHeader String authorization) {
//       String token = request.getLastHeader("Authorization").getValue();
        String token = authorization;
        //check of authorisation in orde is-- check token
        if (authenticateService.authenticate(token)) {
            List<Asset> assets = marketPlaceService.getAllAssets();
            ObjectMapper mapper = new ObjectMapper();

            String jsonAssets = null;
            try {
                jsonAssets = mapper.writeValueAsString(assets);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
            return ResponseEntity.ok().body(jsonAssets);
        } else {
            //ToDo om refreshtoken vragen
            return ResponseEntity.status(401).body("token expired");
        }
    }

    @PostMapping("/trade")
    @ResponseBody
    public ResponseEntity<String> transaction(@RequestHeader String authorization, @RequestBody AssetDTO assetDTO) {
        if (authenticateService.authenticate(authorization)) {
            ObjectMapper mapper = new ObjectMapper();
            String json = null;
            try {
                json = mapper.writeValueAsString(assetDTO);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }

            return ResponseEntity.ok().body(json);
        }
        return ResponseEntity.badRequest().body("authorization failed");
    }
}