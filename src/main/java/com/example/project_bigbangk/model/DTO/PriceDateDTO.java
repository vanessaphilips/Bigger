// Created by Deek
// Creation date 1/13/2022

package com.example.project_bigbangk.model.DTO;

import com.example.project_bigbangk.model.PriceDate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;

public class PriceDateDTO {

    private final Logger logger = LoggerFactory.getLogger(PriceDateDTO.class);

    public PriceDateDTO() {
        super();
        logger.info("New PriceDateDTO");
    }

    private String dateTime;

    public String getDateTime() {
        return dateTime;
    }

    public double getPrice() {
        return price;
    }

    private double price;

    public PriceDateDTO(String dateTime, double price) {
        this.price = price;
        this.dateTime = dateTime;
    }


}