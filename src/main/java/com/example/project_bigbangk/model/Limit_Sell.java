// Created by vip
// Creation date 16/12/2021

package com.example.project_bigbangk.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.time.LocalDateTime;

public class Limit_Sell extends AbstractOrder{

    private final Logger logger = LoggerFactory.getLogger(Limit_Sell.class);

    private Wallet sellerWallet;

    public Limit_Sell(int orderId, Asset asset, double requestedPrice, int numberOfAssets,
                      LocalDateTime date, double transactionFee, Wallet sellerWallet) {
        super(orderId, asset, requestedPrice, numberOfAssets, date, transactionFee);
        this.sellerWallet = sellerWallet;
        logger.info("New Limit_Sell");
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