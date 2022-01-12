// Created by Deek
// Creation date 12/20/2021

package com.example.project_bigbangk.controller;

import com.example.project_bigbangk.BigBangkApplicatie;
import com.example.project_bigbangk.model.DTO.PriceHistoryDTO;
import com.example.project_bigbangk.service.MarketPlaceService;
import com.example.project_bigbangk.service.Security.AuthenticateService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
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


    @PostMapping("/marketplace")
    @ResponseBody
    public ResponseEntity<String> getPriceHistories(@RequestHeader String authorization, @RequestBody String date) {
        if (authenticateService.authenticate(authorization)) {
            LocalDateTime dateTime = LocalDateTime.parse(date);
            List<List<PriceHistoryDTO>> priceHistoriesByAssetDTO;
            priceHistoriesByAssetDTO = marketPlaceService.getAllAssetsWithPriceHistoryFromDate(dateTime);
            try {
                //String jsonPriceHistoriesByAsset = MAPPER.writeValueAsString(priceHistoriesByAssetDTO);
                ObjectNode jsonBody = MAPPER.createObjectNode();
                jsonBody.put("priceHistory", MAPPER.writeValueAsString(priceHistoriesByAssetDTO));
                jsonBody.put("updateInterval" ,String.valueOf(BigBangkApplicatie.UPDATE_INTERVAL_PRICEUPDATESERVICE));
                return ResponseEntity.ok().body(MAPPER.writeValueAsString(jsonBody));
            } catch (JsonProcessingException e) {
                logger.error(e.getMessage());
            }
        }
        return ResponseEntity.status(401).body("token expired");
    }
}