// Created by vip
// Creation date 16/12/2021

package com.example.project_bigbangk.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.time.LocalDateTime;

public class Limit_Buy extends AbstractOrder{

    private final Logger logger = LoggerFactory.getLogger(Limit_Buy.class);

    private double transactionFee;
    private Wallet buyerWallet;

    public Limit_Buy(int orderId, Asset asset, double requestedPrice, int numberOfAssets,
                     LocalDateTime date, double transactionFee, Wallet buyerWallet) {
        super(orderId, asset, requestedPrice, numberOfAssets, date);
        this.transactionFee = transactionFee;
        this.buyerWallet = buyerWallet;
        logger.info("New Limit_Buy");
    }

    public double getTransactionFee() {
        return transactionFee;
    }

    public void setTransactionFee(double transactionFee) {
        this.transactionFee = transactionFee;
    }

    public Wallet getBuyerWallet() {
        return buyerWallet;
    }

    public void setBuyerWallet(Wallet buyerWallet) {
        this.buyerWallet = buyerWallet;
    }

    @Override
    public String toString() {
        return super.toString() +
                "Limit_Buy{" +
                "transactionFee=" + transactionFee +
                ", buyerWallet=" + buyerWallet +
                '}';
    }
}