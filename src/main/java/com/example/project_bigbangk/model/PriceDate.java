// Created by Deek
// Creation date 1/13/2022

package com.example.project_bigbangk.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;

public class PriceDate implements Comparable<PriceDate> {


    private LocalDateTime dateTime;

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public double getPrice() {
        return price;
    }

    private double price;

    public PriceDate(LocalDateTime dateTime, double price) {
        this.price = price;
        this.dateTime = dateTime;
    }


    @Override
    public int compareTo(PriceDate o) {
        return this.getDateTime().compareTo(o.getDateTime());
    }

}