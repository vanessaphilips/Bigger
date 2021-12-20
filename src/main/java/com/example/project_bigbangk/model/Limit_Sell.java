// Created by vip
// Creation date 16/12/2021

package com.example.project_bigbangk.model;

import java.time.LocalDateTime;

public class Limit_Sell extends AbstractOrder{

    private Wallet sellerWallet;

    public Limit_Sell(int orderId, Asset asset, double requestedPrice, int numberOfAssets,
                      LocalDateTime date, double transactionFee, Wallet sellerWallet) {
        super(orderId, asset, requestedPrice, numberOfAssets, date, transactionFee);
        this.sellerWallet = sellerWallet;
    }

    public Wallet getSellerWallet() {
        return sellerWallet;
    }

    public void setSellerWallet(Wallet sellerWallet) {
        this.sellerWallet = sellerWallet;
    }

    @Override
    public String toString() {
        return super.toString() +
                "Limit_Sell{" +
                "sellerWallet=" + sellerWallet +
                '}';
    }
}