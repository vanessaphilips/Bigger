// Created by vip
// Creation date 16/12/2021

package com.example.project_bigbangk.model;

import java.time.LocalDateTime;

public class Limit_Buy extends AbstractOrder{

    private Wallet buyerWallet;

    public Limit_Buy(int orderId, Asset asset, double requestedPrice, int numberOfAssets,
                     LocalDateTime date, double transactionFee, Wallet buyerWallet) {
        super(orderId, asset, requestedPrice, numberOfAssets, date, transactionFee);
        this.buyerWallet = buyerWallet;
    }

    public Wallet getBuyerWallet() {
        return buyerWallet;
    }

    public void setBuyerWallet(Wallet buyerWallet) {
        this.buyerWallet = buyerWallet;
    }

    @Override
    public String toString() {
        return "Limit_Buy{" +
                "buyerWallet=" + buyerWallet +
                '}';
    }
}