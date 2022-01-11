// Created by Deek
// Creation date 12/31/2021

package com.example.project_bigbangk.service.priceHistoryUpdate;

import com.example.project_bigbangk.BigBangkApplicatie;
import com.example.project_bigbangk.repository.PriceHistoryCache;
import com.example.project_bigbangk.repository.RootRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class PriceHistoryCacheUpdateService implements IPriceHistoryListener {


    private final Logger logger = LoggerFactory.getLogger(PriceHistoryCacheUpdateService.class);
    RootRepository rootRepository;
    PriceHistoryCache priceHistoryCache;
    PriceHistoryUpdateService priceHistoryUpdateService;

    public PriceHistoryCacheUpdateService(RootRepository rootRepository, PriceHistoryCache priceHistoryCache, PriceHistoryUpdateService priceHistoryUpdateService) {
        super();
        logger.info("New PriceHistoryCacheUpdateService");
        this.rootRepository = rootRepository;
        this.priceHistoryCache = priceHistoryCache;
        this.priceHistoryUpdateService = priceHistoryUpdateService;
        priceHistoryUpdateService.addPriceHistoryListener(this);
    }

    @Override
    public void onPriceHistoryUpdated() {
        priceHistoryCache.setPriceHistories(
                rootRepository.getAllPriceHistroriesByAssets(LocalDateTime.now().minusDays(BigBangkApplicatie.DAYS_OF_PRICEHISTORY_CACHE))
        );
        logger.info("PriceHistoryCache Updated");
    }
}