// Created by vip
// Creation date 14/12/2021

package com.example.project_bigbangk.model;

import java.time.LocalDateTime;

abstract class AbstractOrder {

    private int transactionId;
    private static final int DEFAULT_TRANSACTIONID = 0;
    private double coinAmount;
    private double transactionValue;
    private LocalDateTime transactionTime;
    private Asset assetCode;
}