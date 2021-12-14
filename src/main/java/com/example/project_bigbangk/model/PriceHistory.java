// Created by Deek
// Creation date 12/12/2021

package com.example.project_bigbangk.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;

public class PriceHistory {

    private final Logger logger = LoggerFactory.getLogger(PriceHistory.class);

    public Asset getAsset() {
        return asset;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public double getPrice() {
        return price;
    }


    private Asset asset;
    private LocalDateTime dateTime;
    private double price;

    public PriceHistory(LocalDateTime dateTime, double price, Asset asset) {
        super();
        logger.info("New PriceHistory");
        this.dateTime = dateTime;
        this.asset = asset;
        this.price = price;
    }
    public PriceHistory(LocalDateTime dateTime, double price) {
        super();
        logger.info("New PriceHistory");
        this.dateTime = dateTime;
        this.price = price;
    }

}