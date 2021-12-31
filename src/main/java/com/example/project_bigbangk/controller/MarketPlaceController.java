// Created by Deek
// Creation date 12/20/2021

package com.example.project_bigbangk.controller;

import com.example.project_bigbangk.model.Asset;
import com.example.project_bigbangk.model.DTO.AssetDTO;
import com.example.project_bigbangk.model.DTO.PriceHistoryDTO;
import com.example.project_bigbangk.model.PriceHistory;
import com.example.project_bigbangk.service.MarketPlaceService;
import com.example.project_bigbangk.service.Security.AuthenticateService;
import com.fasterxml.jackson.core.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@CrossOrigin
@RestController
public class MarketPlaceController {

    private final Logger logger = LoggerFactory.getLogger(MarketPlaceController.class);
    private final AuthenticateService authenticateService;
    private final MarketPlaceService marketPlaceService;
    private final ObjectMapper MAPPER = new ObjectMapper();

    public MarketPlaceController(AuthenticateService authenticateService, MarketPlaceService marketPlaceService) {
        super();
        this.authenticateService = authenticateService;
        this.marketPlaceService = marketPlaceService;
        logger.info("New MarketPlaceController");
    }

    @GetMapping("/marketplace")
    @ResponseBody
    public ResponseEntity<String> getAllAssets(@RequestHeader String authorization) {
        if (authenticateService.authenticate(authorization)) {
            List<Asset> assets = marketPlaceService.getAllAssets();
            try {
                String jsonAssets = MAPPER.writeValueAsString(assets);
                return ResponseEntity.ok().body(jsonAssets);
            } catch (JsonProcessingException e) {
                logger.error(e.getMessage());
            }
        }
        return ResponseEntity.status(401).body("token expired");
    }

    @PostMapping("/trade")
    @ResponseBody
    public ResponseEntity<String> transaction(@RequestHeader String authorization, @RequestBody AssetDTO assetDTO) {
        if (authenticateService.authenticate(authorization)) {
            try {
                String json = MAPPER.writeValueAsString(assetDTO);
                return ResponseEntity.ok().body(json);
            } catch (JsonProcessingException e) {
                logger.error(e.getMessage());
            }
        }
        return ResponseEntity.status(401).body("token expired");
    }

    @PostMapping("/priceHistories")
    @ResponseBody
    public ResponseEntity<String> getPriceHistories(@RequestHeader String authorization, @RequestBody String date) {

        if (authenticateService.authenticate(authorization)) {
            LocalDateTime dateTime = LocalDateTime.parse(date);
            List<List<PriceHistoryDTO>> priceHistoriesByAssetDTO;
            priceHistoriesByAssetDTO = marketPlaceService.getAllAssetsWithPriceHistoryFromDate(dateTime);
            try {
                String jsonPriceHistoriesByAsset = MAPPER.writeValueAsString(priceHistoriesByAssetDTO);
                return ResponseEntity.ok().body(jsonPriceHistoriesByAsset);
            } catch (JsonProcessingException e) {
                logger.error(e.getMessage());
            }
        }
        return ResponseEntity.status(401).body("token expired");
    }
}