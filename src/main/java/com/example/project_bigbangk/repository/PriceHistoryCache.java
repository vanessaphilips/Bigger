// Created by Deek
// Creation date 12/31/2021

package com.example.project_bigbangk.repository;

import com.example.project_bigbangk.model.PriceHistory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class PriceHistoryCache {

    public List<List<PriceHistory>> getPriceHistoriesFromDate(LocalDateTime localDateTime) {
        return priceHistories.stream().map(c->c.stream().filter(ph->ph.getDateTime().isAfter(localDateTime)).collect(Collectors.toList())).collect(Collectors.toList());
    }

    public void setPriceHistories(List<List<PriceHistory>> priceHistories) {
        this.priceHistories = priceHistories;
    }

    List<List<PriceHistory>> priceHistories;
    private final Logger logger = LoggerFactory.getLogger(PriceHistoryCache.class);

    public PriceHistoryCache() {
        super();
        logger.info("New PriceHistoryCache");
        this.priceHistories = new ArrayList<>();
    }
}