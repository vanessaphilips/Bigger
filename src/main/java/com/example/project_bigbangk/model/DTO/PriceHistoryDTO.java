// Created by Deek
// Creation date 12/22/2021

package com.example.project_bigbangk.model.DTO;

import com.example.project_bigbangk.model.Asset;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;

public class PriceHistoryDTO {

       private Asset asset;
    private String dateTime;

    public Asset getAsset() {
        return asset;
    }

    public void setAsset(Asset asset) {
        this.asset = asset;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    private double price;

    public PriceHistoryDTO(String dateTime, double price, Asset asset) {
        super();
        this.dateTime = dateTime;
        this.asset = asset;
        this.price = price;
    }


}