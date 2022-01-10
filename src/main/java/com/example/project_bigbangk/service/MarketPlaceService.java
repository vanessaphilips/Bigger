// Created by Deek
// Creation date 12/20/2021

package com.example.project_bigbangk.service;

import com.example.project_bigbangk.BigBangkApplicatie;
import com.example.project_bigbangk.model.Asset;
import com.example.project_bigbangk.model.DTO.PriceHistoryDTO;
import com.example.project_bigbangk.model.PriceHistory;
import com.example.project_bigbangk.repository.PriceHistoryCache;
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
    PriceHistoryCache priceHistoryCache;

    public MarketPlaceService(RootRepository rootRepository, PriceHistoryCache priceHistoryCache) {
        super();
        this.rootRepository = rootRepository;
        logger.info("New MarketPlaceService");
        this.priceHistoryCache = priceHistoryCache;
    }

    public List<Asset> getAllAssets() {
        return rootRepository.getAllAssets();
    }

    public List<List<PriceHistoryDTO>> getAllAssetsWithPriceHistoryFromDate(LocalDateTime localDateTime) {
        List<List<PriceHistory>> priceHistoriesByAssets;
        if (LocalDateTime.now().minusDays(BigBangkApplicatie.DAYS_OF_PRICEHISTORY_CACHE).isBefore(localDateTime)) {
            priceHistoriesByAssets = priceHistoryCache.getPriceHistoriesFromDate(localDateTime);
        } else {
          priceHistoriesByAssets  = rootRepository.getAllPriceHistroriesByAssets(localDateTime);
        }

        return convertPriceHistroyToDTO(priceHistoriesByAssets);
    }

    private List<List<PriceHistoryDTO>> convertPriceHistroyToDTO(List<List<PriceHistory>> priceHistoriesByAssets) {
        List<List<PriceHistoryDTO>> priceHistoriesByAssetsDTO = new ArrayList<>();

        for (List<PriceHistory> priceHistoriesOfAsset : priceHistoriesByAssets) {
            List<PriceHistoryDTO> priceHistoriesOfAssetDTO = new ArrayList<>();
            for (PriceHistory priceHistory : priceHistoriesOfAsset) {
                priceHistoriesOfAssetDTO.add(new PriceHistoryDTO(priceHistory.getDateTime().toString(),
                        priceHistory.getPrice(),
                        priceHistory.getAsset()));
            }
            priceHistoriesByAssetsDTO.add(priceHistoriesOfAssetDTO);
        }
        return priceHistoriesByAssetsDTO;
    }
}