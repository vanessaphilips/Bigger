// Created by vip
// Creation date 16/12/2021

package com.example.project_bigbangk.model;

import java.time.LocalDateTime;

public class Stoploss_Sell extends AbstractOrder{

    private Wallet sellerWallet;
    private double lowerBound;

    public Stoploss_Sell(int orderId, Asset asset, double requestedPrice, int numberOfAssets, LocalDateTime date,
                         double transactionFee, Wallet sellerWallet, double lowerBound) {
        super(orderId, asset, requestedPrice, numberOfAssets, date, transactionFee);
        this.sellerWallet = sellerWallet;
        this.lowerBound = lowerBound;
    }

    public Wallet getSellerWallet() {
        return sellerWallet;
    }

    public void setSellerWallet(Wallet sellerWallet) {
        this.sellerWallet = sellerWallet;
    }

    public double getLowerBound() {
        return lowerBound;
    }

    public void setLowerBound(double lowerBound) {
        this.lowerBound = lowerBound;
    }

    @Override
    public String toString() {
        return super.toString() +
                "Stoploss_Sell{" +
                "sellerWallet=" + sellerWallet +
                ", lowerBound=" + lowerBound +
                '}';
    }
}