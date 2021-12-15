// Created by vip
// Creation date 14/12/2021

package com.example.project_bigbangk.model;

import java.time.LocalDateTime;

abstract class AbstractOrder {
    private Asset asset;
    private double requestedPrice;
    private int numberOfAssets;
    private LocalDateTime date;
    private double transactionFee;
    private static final double DEFAULT_TRANSACTIONFEE = 0.0025;

    //TODO deze klasse afmaken!


}