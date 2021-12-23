package com.example.project_bigbangk.model.Orders;

import com.example.project_bigbangk.model.Asset;
import com.example.project_bigbangk.model.Wallet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.time.LocalDateTime;

/**
 * Model created by Vanessa Philips.
 * Object "Limit_Sell" > Sell transaction if the price reaches above the current level.
 */

public class Limit_Sell extends AbstractOrder{

    private final Logger logger = LoggerFactory.getLogger(Limit_Sell.class);

    private double transactionFee;
    private Wallet sellerWallet;

    public Limit_Sell(int orderId, double requestedPrice, int numberOfAssets,
                      LocalDateTime date, double transactionFee) {
        super(orderId, requestedPrice, numberOfAssets, date);
        this.transactionFee = transactionFee;
        logger.info("New Limit_Sell, without Asset and Wallet");
    }

    public Limit_Sell(Asset asset, double requestedPrice, int numberOfAssets, LocalDateTime date, double transactionFee, Wallet sellerWallet) {
        super(asset, requestedPrice, numberOfAssets, date);
        this.transactionFee = transactionFee;
        this.sellerWallet = sellerWallet;
        logger.info("New Limit_Sell, without id");
    }

    public double getTransactionFee() {
        return transactionFee;
    }

    public void setTransactionFee(double transactionFee) {
        this.transactionFee = transactionFee;
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
                "transactionFee=" + transactionFee +
                ", sellerWallet=" + sellerWallet +
                '}';
    }
}