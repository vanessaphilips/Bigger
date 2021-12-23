// Created by Deek
// Creation date 12/20/2021

package com.example.project_bigbangk.service;

import com.example.project_bigbangk.model.Asset;
import com.example.project_bigbangk.model.DTO.PriceHistoryDTO;
import com.example.project_bigbangk.model.PriceHistory;
import com.example.project_bigbangk.repository.RootRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class MarketPlaceService {

    private final Logger logger = LoggerFactory.getLogger(MarketPlaceService.class);
    private final RootRepository rootRepository;

    public MarketPlaceService(RootRepository rootRepository) {
        super();
        this.rootRepository = rootRepository;
        logger.info("New MarketPlaceService");
    }

    public List<Asset> getAllAssets( ){
        return rootRepository.getAllAssets();
    }

    public List<List<PriceHistoryDTO>> getAllAssetsWithPriceHistoryFromDate(LocalDateTime localDateTime){
        List<List<PriceHistory>> priceHistoriesByAssets = rootRepository.getAllPriceHistroriesByAssets(localDateTime);
        List<List<PriceHistoryDTO>> priceHistoriesByAssetsDTO = new ArrayList<>();

        for(List<PriceHistory> priceHistoriesOfAsset :priceHistoriesByAssets ){
             List<PriceHistoryDTO> priceHistoriesOfAssetDTO = new ArrayList<>();
            for(PriceHistory priceHistory : priceHistoriesOfAsset){
                priceHistoriesOfAssetDTO.add(new PriceHistoryDTO(priceHistory.getDateTime().toString(),
                        priceHistory.getPrice(),
                        priceHistory.getAsset()));
            }
            priceHistoriesByAssetsDTO.add(priceHistoriesOfAssetDTO);
        }
        return priceHistoriesByAssetsDTO;
    }
}